package com.lide.app.ui.inbound.LS;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.internal.LinkedTreeMap;
import com.lide.app.MApplication;
import com.lide.app.R;
import com.lide.app.config.Configs;
import com.lide.app.listener.OnFinishListener;
import com.lide.app.persistence.util.DBOperator;
import com.lide.app.persistence.util.SPUtils;
import com.lide.app.persistence.util.SoundUtils;
import com.lide.app.persistence.util.Utils;
import com.lide.app.presenter.ScanPresenter;
import com.lide.app.presenter.inbound.InBoundPresenter;
import com.lide.app.ui.FragmentBase;
import com.lide.app.ui.LoginActivity;
import com.lide.app.ui.VInterface.IDataFragmentView;
import com.lide.app.ui.inbound.InboundTransaction;
import com.lubin.bean.InBoundCase;
import com.lubin.bean.InBoundDetail;
import com.lubin.bean.InBoundOperate;
import com.lubin.bean.InBoundOrder;
import com.lubin.dao.InBoundCaseDao;
import com.lubin.dao.InBoundDetailDao;
import com.lubin.dao.InBoundOperateDao;
import com.lubin.dao.InBoundOrderDao;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lubin on 2016/11/30.
 */

public class ScanningRFIDForMixFragment extends FragmentBase implements IDataFragmentView<List<LinkedTreeMap>> {

    @BindView(R.id.tv_order_sum)
    TextView tvOrderSum;
    @BindView(R.id.scan_rotate)
    ImageView scanRotate;
    @BindView(R.id.iv_close_open)
    ImageView ivCloseOpen;
    @BindView(R.id.sum_num)
    TextView sumNum;
    @BindView(R.id.scan_state)
    TextView scanState;
    @BindView(R.id.btn_scan_result)
    Button btnScanResult;
    @BindView(R.id.tv_scan_operate_sum)
    TextView tvScanOperateSum;
    @BindView(R.id.tv_scan_error_sum)
    TextView tvScanErrorSum;
    @BindView(R.id.btn_error_result)
    Button btnErrorResult;

    private View mView;
    private ScanPresenter scanPresenter;
    private Animation anim;
    private int order;
    private Timer mTimer;
    private InboundActivity mActivity;
    final List<Map> mData = new ArrayList<>();
    private List<String> tags = new LinkedList<>();
    private DBOperator<InBoundCaseDao, InBoundCase> caseDBOperator;
    private DBOperator<InBoundOrderDao, InBoundOrder> orderDBOperator;
    private DBOperator<InBoundOperateDao, InBoundOperate> operateDBOperator;
    private InBoundPresenter inBoundPresenter;
    private DBOperator<InBoundDetailDao, InBoundDetail> detailDBOperator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_scan_rfid_ib, container, false);
        ButterKnife.bind(this, mView);
        initData();
        initTask();
        initView();
        return mView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //展示单据数还有操作数
            showQtyAndOperateQty();
            //初始化读写器
            initScanPresenter();
        } else {
            if (scanPresenter == null) return;
            scanPresenter.setMode(0);
            scanPresenter.stopReadRfid();
            scanPresenter.removeListener();
        }
    }

    private void initData() {
        order = 0;
        anim = AnimationUtils.loadAnimation(getActivity(), R.anim.san_anim);
        anim.setInterpolator(new LinearInterpolator());
        anim.setFillAfter(true);
        anim.setFillBefore(false);
        anim.setFillEnabled(false);

        mActivity = ((InboundActivity) getActivity());

        caseDBOperator = InboundTransaction.getCaseDBOperator();
        detailDBOperator = InboundTransaction.getDetailDBOperator();
        orderDBOperator = InboundTransaction.getOrderDBOperator();
        operateDBOperator = InboundTransaction.getOperateDBOperator();
    }

    private void initTask() {
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (order > 10) {
                    setAnimDuration(1000);
                } else if (order >= 0 && order <= 10) {
                    setAnimDuration(5000);
                }
            }
        }, 0, 500);
    }

    private void initView() {
        showQtyAndOperateQty();
        clear();
    }

    private void initScanPresenter() {
        //将已采集的epc操作数据，去除放入内存中排重
        List<String> datas = new ArrayList<>();
        List<InBoundOperate> operates;
        if (mActivity.orderId != 0) {
            operates = operateDBOperator.getItemsByParameters(null
                    , InBoundOperateDao.Properties.InBoundOrderId.eq(mActivity.orderId)
                    , InBoundOperateDao.Properties.Barcode.isNull()
                    , InBoundOperateDao.Properties.OperateQty.eq(1));
        } else {
            operates = operateDBOperator.getItemsByParameters(null
                    , InBoundOperateDao.Properties.InBoundCaseId.in(mActivity.getCasesIdList())
                    , InBoundOperateDao.Properties.Barcode.isNull()
                    , InBoundOperateDao.Properties.OperateQty.eq(1));
        }
        for (InBoundOperate inBoundOperate : operates) {
            if (inBoundOperate.getBarcode() == null) {
                datas.add(inBoundOperate.getEpc());
            }
        }
        tags.addAll(datas);
        //初始化读写器
        scanPresenter = new ScanPresenter(this);
        scanPresenter.initData();
        scanPresenter.setMode(1);
        scanPresenter.setCurrentSetting(ScanPresenter.Setting.stockRead);
        scanPresenter.setListener(new OnFinishListener() {
            @Override
            public void OnFinish(String data) {
                if (!tags.contains(data)) {
                    //采集到的频次加一
                    order++;
                    //采集到的epc放入内存中
                    tags.add(data);
                    //声音回馈
                    SoundUtils.play(1);
                    //本次采集的总数
                    sumNum.setText(String.valueOf(tags.size()));
                }
            }
        });
        inBoundPresenter = new InBoundPresenter(this);
    }

    private void setAnimDuration(int durationMillis) {
        if (anim.getDuration() != durationMillis) {
            anim.setDuration(durationMillis);
            anim.reset();
        }
        order = 0;
    }

    public void readOrClose() {
        if (scanState.getText().toString().equals("点击开始")) {
            scanRotate.setAnimation(anim);
            scanRotate.setVisibility(View.VISIBLE);
            btnScanResult.setEnabled(false);
            btnScanResult.setBackground(greyBackground);
            btnErrorResult.setEnabled(false);
            btnErrorResult.setBackground(greyBackground);
            mActivity.setViewEnabled(false);
            scanState.setText("点击结束");
            scanPresenter.startReadRfid();
            //清理上次扫描epc集合，还有结果
            clear();
        } else {
            scanRotate.setVisibility(View.INVISIBLE);
            scanRotate.clearAnimation();
            btnScanResult.setEnabled(true);
            btnScanResult.setBackground(commonBackground);
            btnErrorResult.setEnabled(true);
            btnErrorResult.setBackground(commonBackground);
            mActivity.setViewEnabled(true);
            scanState.setText("点击开始");
            scanPresenter.stopReadRfid();
            //将本次采集到的epc进行处理
            saveData();
        }
    }

    //清理本次采集的结果
    public void clear() {
        tvScanOperateSum.setText("成功数：0");
        tvScanErrorSum.setText("异常数：0");
        sumNum.setText("0");
        tags.clear();
        mData.clear();
    }

    //开始搜索采集到的标签集合，返回携带的数据
    private void saveData() {
        if (tags.size() == 0) return;
        if (Utils.getApiKey() == null) {
            Intent login = new Intent(getActivity(), LoginActivity.class);
            login.putExtra("isAtNet", true);
            startActivityForResult(login, Configs.LOGIN);
        } else {
            inBoundPresenter.SearchEpcList(tags);
        }
    }

    /**
     * 处理返回的结果
     *
     * @param linkedTreeMaps 采集到的标签集合，通过后台查询后，返回的集合，其中每个标签都有携带后端中关联的信息
     */
    @Override
    public void ShowData(final List<LinkedTreeMap> linkedTreeMaps) {
        startProgressDialog("生成中...");
        runOnWorkThread(new Runnable() {
            @Override
            public void run() {
                int succeed = 0;
                for (LinkedTreeMap linkedTreeMap : linkedTreeMaps) {
                    String epc = String.valueOf(linkedTreeMap.get("epc"));
                    String barcode = String.valueOf(linkedTreeMap.get("barcode"));
                    //如果这个标签，存在数据库，但没有绑定，生成一个分析结果的对象，放入集合中
                    if (barcode.equals("null")) {
                        Map<String, String> map = new HashMap<>();
                        map.put("epc", epc);
                        map.put("status", "没有绑定");
                        mData.add(map);
                        tags.remove(epc);
                        continue;
                    }
                    //判断本地数据库里面有没有已经采集过
                    List<InBoundOperate> operates;
                    if (mActivity.orderId != 0) {
                        operates = operateDBOperator.getItemsByParameters(null
                                , InBoundOperateDao.Properties.InBoundOrderId.eq(mActivity.orderId)
                                , InBoundOperateDao.Properties.Epc.eq(epc));
                    } else {
                        operates = operateDBOperator.getItemsByParameters(null
                                , InBoundOperateDao.Properties.InBoundCaseId.in(mActivity.getCasesIdList())
                                , InBoundOperateDao.Properties.Epc.eq(epc));
                    }
                    if (operates.size() > 0) {
                        Map<String, String> map = new HashMap<>();
                        map.put("barcode", barcode);
                        map.put("epc", epc);
                        map.put("status", "已存在");
                        mData.add(map);
                        tags.remove(epc);
                        continue;
                    }
                    //查询标签关联展示明细表的数据，是否有还没有单据数没有超过操作数的数据
                    List<InBoundDetail> details;
                    if (mActivity.orderId != 0) {
                        details = detailDBOperator.getItemsByParameters(null
                                , InBoundDetailDao.Properties.InBoundOrderId.eq(mActivity.orderId)
                                , InBoundDetailDao.Properties.Barcode.eq(barcode)
                                , new WhereCondition.StringCondition("T.QTY > T.OPERATE_QTY"));
                    } else {
                        details = detailDBOperator.getItemsByParameters(null
                                , InBoundDetailDao.Properties.InBoundCaseId.in(mActivity.getCasesIdList())
                                , InBoundDetailDao.Properties.Barcode.eq(barcode)
                                , new WhereCondition.StringCondition("T.QTY > T.OPERATE_QTY"));
                    }
                    //如果在允许超收的情况下，不存在单据数没有超过操作数的数据，就看需要查询单据数大于或者等于操作数的数据
                    if (String.valueOf(SPUtils.get(MApplication.getInstance(), "inboundAllowExceed", "false")).equals("true")
                            && details.size() == 0) {

                        if (mActivity.orderId != 0) {
                            details = detailDBOperator.getItemsByParameters(null
                                    , InBoundDetailDao.Properties.InBoundOrderId.eq(mActivity.orderId)
                                    , InBoundDetailDao.Properties.Barcode.eq(barcode)
                                    , new WhereCondition.StringCondition("T.QTY <= T.OPERATE_QTY"));
                        } else {
                            details = detailDBOperator.getItemsByParameters(null
                                    , InBoundDetailDao.Properties.InBoundCaseId.in(mActivity.getCasesIdList())
                                    , InBoundDetailDao.Properties.Barcode.eq(barcode)
                                    , new WhereCondition.StringCondition("T.QTY <= T.OPERATE_QTY"));
                        }
                    }
                    //如果没有存在可以关联的数据，判断这个标签不存在收货的范围之内，或者是已收满的情况下，又采集到这个条码绑定的标签
                    if (details.size() == 0) {
                        Map<String, String> map = new HashMap<>();
                        map.put("barcode", barcode);
                        map.put("epc", epc);
                        map.put("status", "超出");
                        mData.add(map);
                        tags.remove(epc);
                        continue;
                    }
                    //将采集到的标签，写入数据库中，改变所关联的表的数据
                    InBoundDetail detail = details.get(0);
                    InBoundOperate inBoundOperate = new InBoundOperate();
                    inBoundOperate.setQty(0);
                    inBoundOperate.setOperateQty(1);
                    inBoundOperate.setIsUpload(false);
                    inBoundOperate.setEpc(epc);
                    inBoundOperate.setInBoundDetail(detail);
                    inBoundOperate.setInBoundCase(detail.getInBoundCase());
                    inBoundOperate.setInBoundOrder(detail.getInBoundOrder());
                    //展示明细的操作数增加一
                    detail.setOperateQty(detail.getOperateQty() + 1);
                    detail.update();
                    operateDBOperator.insertData(inBoundOperate);
                    //生成成功的信息对象
                    Map<String, String> map = new HashMap<>();
                    map.put("barcode", barcode);
                    map.put("epc", epc);
                    map.put("status", "正常");
                    mData.add(map);
                    tags.remove(epc);
                    succeed++;
                }
                //将不存后端数据库中的标签，生成错误信息
                for (String tag : tags) {
                    Map<String, String> map = new HashMap<>();
                    map.put("epc", tag);
                    map.put("status", "异常");
                    mData.add(map);
                }
                updateTextView(succeed);
                stopProgressDialog(null);
            }
        });
    }

    private void updateTextView(final int size) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //操作表里面的操作数发生变化，所有关联的表的操作也需要重新计算，并且清理缓存中的久的数据
                mActivity.recount();
                //刷新界面中展示的操作数和单据数
                showQtyAndOperateQty();
                //展示本次采集的结果
                tvScanOperateSum.setText("成功数：" + String.valueOf(size));
                tvScanErrorSum.setText("异常数：" + String.valueOf(Integer.parseInt(sumNum.getText().toString()) - size));
            }
        });
    }

    @OnClick({R.id.iv_close_open, R.id.btn_scan_result, R.id.btn_error_result})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_close_open:
                readOrClose();
                break;
            case R.id.btn_scan_result:
                mActivity.setCurrentFragment(5);
                break;
            case R.id.btn_error_result:
                mActivity.setCurrentFragment(6);
                ((ErrorResultsFragment) InboundActivity.fragments.get(6)).showErrorResultsData(mData);
                break;
        }
    }

    //展示给界面最新的单据数和操作数
    private void showQtyAndOperateQty() {
        if (mActivity.orderId != 0) {
            InBoundOrder inBoundOrder = orderDBOperator.getItemByID(mActivity.orderId);
            tvOrderSum.setText(inBoundOrder.getQty() + "/" + inBoundOrder.getOperateQty());
        } else {
            int qty = caseDBOperator.rawQueryForSum(mActivity.getCasesId()
                    , "_ID in (" + mActivity.getParameter() + ") "
                    , "QTY", " IN_BOUND_CASE ");
            int operateQty = caseDBOperator.rawQueryForSum(mActivity.getCasesId()
                    , "_ID in (" + mActivity.getParameter() + ") "
                    , "OPERATE_QTY", " IN_BOUND_CASE ");
            tvOrderSum.setText(qty + "/" + operateQty);
        }
    }
    //重新连接读写器，因为登录之后会本fragment 会失去连接
    public void resetScanPresenter() {
        scanPresenter.setListener(new OnFinishListener() {
            @Override
            public void OnFinish(String data) {
                if (!tags.contains(data)) {
                    order++;
                    tags.add(data);
                    SoundUtils.play(1);
                    sumNum.setText(String.valueOf(tags.size()));
                }
            }
        });
    }
}
