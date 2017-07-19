package com.lide.app.ui.inbound.LS;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.internal.LinkedTreeMap;
import com.lide.app.MApplication;
import com.lide.app.R;
import com.lide.app.adapter.ScanningBySkuAdapter;
import com.lide.app.listener.OnFinishListener;
import com.lide.app.persistence.util.DBOperator;
import com.lide.app.persistence.util.SPUtils;
import com.lide.app.persistence.util.SoundUtils;
import com.lide.app.presenter.ScanPresenter;
import com.lide.app.presenter.inbound.InBoundPresenter;
import com.lide.app.ui.FragmentBase;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lubin on 2016/12/1.
 */

public class ScanningSkuForMixFragment extends FragmentBase implements IDataFragmentView<List<LinkedTreeMap>> {

    @BindView(R.id.scan_et_sku)
    EditText etSku;
    @BindView(R.id.scan_et_num)
    EditText etNum;
    @BindView(R.id.all_num)
    TextView tvAllNum;
    @BindView(R.id.scan_lv_sku)
    ListView scanLvSku;

    private View mView;
    private InboundActivity mActivity;
    private ScanningBySkuAdapter mAdapter;
    private List<InBoundDetail> Details = new ArrayList<>();
    private DBOperator<InBoundCaseDao, InBoundCase> caseDBOperator;
    private DBOperator<InBoundOrderDao, InBoundOrder> orderDBOperator;
    private DBOperator<InBoundDetailDao, InBoundDetail> detailDBOperator;
    private DBOperator<InBoundOperateDao, InBoundOperate> operateDBOperator;
    private ScanPresenter scanPresenter;
    private InBoundPresenter inBoundPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_scan_barcode, container, false);
        ButterKnife.bind(this, mView);
        initView();
        caseDBOperator = InboundTransaction.getCaseDBOperator();
        orderDBOperator = InboundTransaction.getOrderDBOperator();
        detailDBOperator = InboundTransaction.getDetailDBOperator();
        operateDBOperator = InboundTransaction.getOperateDBOperator();
        return mView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //刷新界面数据
            refreshData();
            //初始化读写器
            initScanPresenter();
        } else {
            //第一次扫描器没有没有初始化
            if (scanPresenter == null) return;
            scanPresenter.setMode(0);
            //移除回调
            scanPresenter.removeListener();
        }
    }

    private void initView() {
        etSku.setImeOptions(EditorInfo.IME_ACTION_SEND);
        etSku.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event != null)
                    if (event.getAction() == KeyEvent.ACTION_UP) return true;
                if (actionId == EditorInfo.IME_ACTION_SEND || actionId == 0) {
                    setEtSku(etSku.getText().toString());
                    return true;
                }
                return false;
            }
        });
        etNum.setImeOptions(EditorInfo.IME_ACTION_SEND);
        etNum.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event != null)
                    if (event.getAction() == KeyEvent.ACTION_UP) return true;
                if (actionId == EditorInfo.IME_ACTION_SEND || actionId == 0) {
                    setEtSku(etSku.getText().toString());
                    return true;
                }
                return false;
            }
        });

        mAdapter = new ScanningBySkuAdapter(getActivity(), Details);
        scanLvSku.setAdapter(mAdapter);

        mActivity = (InboundActivity) getActivity();
    }

    //刷新界面数据
    private void refreshData() {
        List<InBoundDetail> container = new ArrayList<>();
        if (mActivity.orderId != 0) {
            Iterator<InBoundDetail> iterator = detailDBOperator.getItemsByParameters("date DESC"
                    , InBoundDetailDao.Properties.InBoundOrderId.eq(mActivity.orderId)).iterator();
            while (iterator.hasNext()) {
                InBoundDetail next = iterator.next();
                boolean flag = false;
                for (InBoundDetail inBoundDetail : container) {
                    if (inBoundDetail.getBarcode().equals(next.getBarcode())) {
                        inBoundDetail.setQty(inBoundDetail.getQty() + next.getQty());
                        inBoundDetail.setOperateQty(inBoundDetail.getOperateQty() + next.getOperateQty());
                        flag = true;
                    }
                }
                if (!flag) {
                    InBoundDetail inBoundDetail = new InBoundDetail();
                    inBoundDetail.setQty(next.getQty());
                    inBoundDetail.setOperateQty(next.getOperateQty());
                    inBoundDetail.setBarcode(next.getBarcode());
                    inBoundDetail.setSkuName(next.getSkuName());
                    inBoundDetail.setDate(next.getDate());
                    container.add(inBoundDetail);
                }
            }
            InBoundOrder inBoundOrder = orderDBOperator.getItemByID(mActivity.orderId);
            tvAllNum.setText(inBoundOrder.getQty() + "/" + inBoundOrder.getOperateQty());
        } else {
            Iterator<InBoundDetail> iterator = detailDBOperator
                    .getItemsByCondition(InBoundDetailDao.Properties.InBoundCaseId.in(mActivity.getCasesIdList()))
                    .iterator();
            while (iterator.hasNext()) {
                InBoundDetail next = iterator.next();
                boolean flag = false;
                for (InBoundDetail inBoundDetail : container) {
                    if (inBoundDetail.getBarcode().equals(next.getBarcode())) {
                        inBoundDetail.setQty(inBoundDetail.getQty() + next.getQty());
                        inBoundDetail.setOperateQty(inBoundDetail.getOperateQty() + next.getOperateQty());
                        flag = true;
                    }
                }
                if (!flag) {
                    InBoundDetail inBoundDetail = new InBoundDetail();
                    inBoundDetail.setQty(next.getQty());
                    inBoundDetail.setOperateQty(next.getOperateQty());
                    inBoundDetail.setBarcode(next.getBarcode());
                    inBoundDetail.setSkuName(next.getSkuName());
                    inBoundDetail.setDate(next.getDate());
                    container.add(inBoundDetail);
                }
            }
            int qty = caseDBOperator.rawQueryForSum(mActivity.getCasesId()
                    , "_ID in (" + mActivity.getParameter() + ") "
                    , "QTY", " IN_BOUND_CASE ");
            int operateQty = caseDBOperator.rawQueryForSum(mActivity.getCasesId()
                    , "_ID in (" + mActivity.getParameter() + ") "
                    , "OPERATE_QTY", " IN_BOUND_CASE ");
            tvAllNum.setText(qty + "/" + operateQty);
        }
        mAdapter.addAll(container);
        etSku.setText("");
        etNum.setText("1");
    }

    //开启读写器
    private void initScanPresenter() {
        scanPresenter = new ScanPresenter(this);
        scanPresenter.initData();
        scanPresenter.setMode(2);
        scanPresenter.setListener(new OnFinishListener() {
            @Override
            public void OnFinish(String data) {
                SoundUtils.play(1);
                setEtSku(data);
            }
        });

        inBoundPresenter = new InBoundPresenter(this);
    }

    public void setEtSku(String barcode) {
        etSku.setText(barcode);
        int num = Integer.parseInt(etNum.getText().toString());
        if (barcode.isEmpty()) {
            showDialog("条码不能为空");
            return;
        }
        updateDetailForMixed(num, barcode);
    }

    //采集到的条码，匹配成功后，更新操作表的操作记录
    private void updateDetailForMixed(int num, String barcode) {
        List<InBoundOperate> inBoundOperates;
        //判断操作表里面有没有条码匹配的数据
        if (mActivity.orderId != 0) {
            inBoundOperates = operateDBOperator.getItemsByParameters(null
                    , InBoundOperateDao.Properties.Barcode.eq(barcode)
                    , InBoundOperateDao.Properties.Epc.isNull()
                    , InBoundOperateDao.Properties.InBoundOrderId.eq(mActivity.orderId));
        } else {
            inBoundOperates = operateDBOperator.getItemsByParameters(null
                    , InBoundOperateDao.Properties.Barcode.eq(barcode)
                    , InBoundOperateDao.Properties.Epc.isNull()
                    , InBoundOperateDao.Properties.InBoundCaseId.in(mActivity.getCasesIdList()));
        }
        //没有弹窗报错，并发错警告声音
        if (inBoundOperates.size() == 0) {
            showDialog("条码：" + barcode + "错误!");
            SoundUtils.playErrorSound();
            return;
        }
        //计算操作数的总和
        int OperateQty = 0;
        for (InBoundOperate inBoundOperate : inBoundOperates) {
            OperateQty += inBoundOperate.getOperateQty();
        }
        //是否运行超收
        if (SPUtils.get(MApplication.getInstance(), "inboundAllowExceed", "false").toString().equals("true")) {
            //用户减去收货数不能超过已收货数
            if (OperateQty + num >= 0) {
                for (InBoundOperate inBoundOperate : inBoundOperates) {
                    if (num >= 0) {
                        //获取本条操作数据能够收的数量
                        int qty = inBoundOperate.getQty() - inBoundOperate.getOperateQty();
                        //判断是否能过收玩目标值
                        if (num - qty > 0) {
                            if (inBoundOperates.indexOf(inBoundOperate) == (inBoundOperates.size() - 1)) {
                                inBoundOperate.setOperateQty(inBoundOperate.getOperateQty() + num);
                                num = 0;
                            } else {
                                inBoundOperate.setOperateQty(inBoundOperate.getOperateQty() + qty);
                                num -= qty;
                            }
                        } else {
                            inBoundOperate.setOperateQty(inBoundOperate.getOperateQty() + num);
                            num = 0;
                        }
                    } else {
                        //获取本条数据已收货数
                        Integer qty = inBoundOperate.getOperateQty();
                        //判断能否一次减完
                        if (num + qty <= 0) {
                            inBoundOperate.setOperateQty(0);
                            num += qty;
                        } else {
                            inBoundOperate.setOperateQty(inBoundOperate.getOperateQty() + num);
                            num = 0;
                        }
                    }
                    InBoundDetail inBoundDetail = inBoundOperate.getInBoundDetail();
                    inBoundDetail.setDate(new Date(System.currentTimeMillis()));
                    inBoundDetail.update();
                    inBoundOperate.setIsUpload(false);
                    inBoundOperate.update();
                    inBoundPresenter.createMultiBarcode(inBoundOperate);
                }
            } else {
                ShowToast("数量超出~");
                SoundUtils.playErrorSound();
                return;
            }
        } else {
            //计算这个条码操作数的，还有单据数的总和
            int detailQty;
            int detailOperateQty;
            if (mActivity.orderId != 0) {
                detailQty = detailDBOperator.rawQueryForSum(new String[]{String.valueOf(mActivity.orderId), barcode}, "IN_BOUND_ORDER_ID in (?) AND BARCODE = ?", "QTY", "IN_BOUND_DETAIL");
                detailOperateQty = detailDBOperator.rawQueryForSum(new String[]{String.valueOf(mActivity.orderId), barcode}, "IN_BOUND_ORDER_ID in (?) AND BARCODE = ?", "OPERATE_QTY", "IN_BOUND_DETAIL");
            } else {
                String[] casesId = mActivity.getCasesId();
                String[] container = new String[casesId.length + 1];
                for (int i = 0; i < casesId.length; i++) {
                    container[i] = casesId[i];
                }
                container[container.length - 1] = barcode;
                detailQty = detailDBOperator.rawQueryForSum(container, "IN_BOUND_CASE_ID in (" + mActivity.getParameter() + ") AND BARCODE = ?", "QTY", "IN_BOUND_DETAIL");
                detailOperateQty = detailDBOperator.rawQueryForSum(container, "IN_BOUND_CASE_ID in (" + mActivity.getParameter() + ") AND BARCODE = ?", "OPERATE_QTY", "IN_BOUND_DETAIL");
            }
            //判断收货是否超出范围
            if (detailQty >= detailOperateQty + num && detailOperateQty + num >= 0) {
                for (InBoundOperate inBoundOperate : inBoundOperates) {
                    if (num >= 0) {
                        Integer qty = inBoundOperate.getQty() - inBoundOperate.getOperateQty();
                        if (num - qty > 0) {
                            inBoundOperate.setOperateQty(inBoundOperate.getOperateQty() + qty);
                            num -= qty;
                        } else {
                            inBoundOperate.setOperateQty(inBoundOperate.getOperateQty() + num);
                            num = 0;
                        }
                    } else {
                        Integer qty = inBoundOperate.getOperateQty();
                        if (num + qty <= 0) {
                            inBoundOperate.setOperateQty(0);
                            num += qty;
                        } else {
                            inBoundOperate.setOperateQty(inBoundOperate.getOperateQty() + num);
                            num = 0;
                        }
                    }
                    InBoundDetail inBoundDetail = inBoundOperate.getInBoundDetail();
                    inBoundDetail.setDate(new Date(System.currentTimeMillis()));
                    inBoundDetail.update();
                    inBoundOperate.setIsUpload(false);
                    inBoundOperate.update();
                    inBoundPresenter.createMultiBarcode(inBoundOperate);
                }
            } else {
                ShowToast("数量超出~");
                SoundUtils.playErrorSound();
                return;
            }
        }
        mActivity.recount();
        refreshData();
    }

    @Override
    public void startScan() {
        scanPresenter.startScanBarcode();
    }

    @Override
    public void ShowData(List<LinkedTreeMap> linkedTreeMaps) {

    }
}
