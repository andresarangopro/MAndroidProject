package com.lide.app.ui.inbound.LS;

import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.lide.app.R;
import com.lide.app.listener.OnFinishListener;
import com.lide.app.persistence.util.DBOperator;
import com.lide.app.persistence.util.SoundUtils;
import com.lide.app.presenter.ScanPresenter;
import com.lide.app.ui.FragmentBase;
import com.lide.app.ui.VInterface.IDataFragmentView;
import com.lide.app.ui.inbound.InboundTransaction;
import com.lubin.bean.InBoundOperate;
import com.lubin.bean.InBoundOrder;
import com.lubin.dao.InBoundOperateDao;
import com.lubin.dao.InBoundOrderDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lubin on 2016/11/30.
 */

public class ScanningRFIDFragment extends FragmentBase implements IDataFragmentView<List<LinkedTreeMap>> {

    @BindView(R.id.scan_sum_title)
    TextView scanSumTitle;
    @BindView(R.id.scan_rotate)
    ImageView scanRotate;
    @BindView(R.id.sum_num)
    TextView sumNum;
    @BindView(R.id.scan_state)
    TextView scanState;
    @BindView(R.id.btn_scan_result)
    Button btnScanResult;

    private View mView;
    private ScanPresenter scanPresenter;
    private Animation anim;
    private int order;
    private Timer mTimer;
    private InboundActivity mActivity;
    private DBOperator<InBoundOrderDao, InBoundOrder> orderDBOperator;
    private DBOperator<InBoundOperateDao, InBoundOperate> operateDBOperator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_scan_rfid, container, false);
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
            initScanPresenter();
        } else {
            //第一次是没有初始化扫描器的
            if (scanPresenter == null) return;
            //离开界面时没有关闭读写器，关闭读写器
            if (scanState.getText().toString().equals("点击结束"))
                readOrClose();
            //移除回调
            scanPresenter.removeListener();
        }
    }

    private void initData() {
        //设置动画
        order = 0;
        anim = AnimationUtils.loadAnimation(getActivity(), R.anim.san_anim);
        anim.setInterpolator(new LinearInterpolator());
        anim.setFillAfter(true);
        anim.setFillBefore(false);
        anim.setFillEnabled(false);

        mActivity = ((InboundActivity) getActivity());
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
        //取出可操作的标签操作明细的单据数总和
        if (mActivity.orderId != 0) {
            List<InBoundOperate> list = operateDBOperator.getItemsByParameters(null
                    , InBoundOperateDao.Properties.InBoundOrderId.eq(mActivity.orderId)
                    , InBoundOperateDao.Properties.Epc.isNotNull());
            scanSumTitle.setText("单据数: " + String.valueOf(list.size()));
        } else {
            List<InBoundOperate> list = operateDBOperator.getItemsByParameters(null
                    , InBoundOperateDao.Properties.InBoundCaseId.in(mActivity.getCasesIdList())
                    , InBoundOperateDao.Properties.Epc.isNotNull());
            scanSumTitle.setText("单据数: " + String.valueOf(list.size()));
        }
    }

    @NonNull
    private List<String> refresh() {
        //取出已操作的数据
        List<String> datas = new ArrayList<>();
        List<InBoundOperate> operates;
        if (mActivity.orderId != 0) {
            operates = operateDBOperator.getItemsByParameters(null
                    , InBoundOperateDao.Properties.InBoundOrderId.eq(mActivity.orderId)
                    , InBoundOperateDao.Properties.Epc.isNotNull()
                    , InBoundOperateDao.Properties.OperateQty.eq(1));
        } else {
            operates = operateDBOperator.getItemsByParameters(null
                    , InBoundOperateDao.Properties.InBoundCaseId.in(mActivity.getCasesIdList())
                    , InBoundOperateDao.Properties.Epc.isNotNull()
                    , InBoundOperateDao.Properties.OperateQty.eq(1));
        }

        for (InBoundOperate inBoundOperate : operates) {
            datas.add(inBoundOperate.getEpc());
        }

        return datas;
    }
    //初始化读写器
    private void initScanPresenter() {
        List<String> datas = refresh();

        scanPresenter = new ScanPresenter(this);
        scanPresenter.initData();
        scanPresenter.setMode(1);
        scanPresenter.initMap(datas);
        scanPresenter.setCurrentSetting(ScanPresenter.Setting.stockRead);
        scanPresenter.setListenerProtectModel(new OnFinishListener() {
            @Override
            public void OnFinish(String data) {
                order++;
                if (data.isEmpty()) {
                    return;
                } else {
                    findOperate(data);
                }
            }
        });
        sumNum.setText(String.valueOf(datas.size()));
    }
    //设置动画转动速度
    private void setAnimDuration(int durationMillis) {
        if (anim.getDuration() != durationMillis) {
            anim.setDuration(durationMillis);
            anim.reset();
        }
        order = 0;
    }
    //开启关闭读写器
    public void readOrClose() {
        if (scanState.getText().toString().equals("点击开始")) {
            scanRotate.setAnimation(anim);
            scanRotate.setVisibility(View.VISIBLE);
            btnScanResult.setEnabled(false);
            btnScanResult.setBackground(greyBackground);
            mActivity.setViewEnabled(false);
            scanState.setText("点击结束");
            scanPresenter.startReadRfid();
        } else {
            scanRotate.setVisibility(View.INVISIBLE);
            scanRotate.clearAnimation();
            btnScanResult.setEnabled(true);
            btnScanResult.setBackground(commonBackground);
            mActivity.setViewEnabled(true);
            scanState.setText("点击开始");
            scanPresenter.stopReadRfid();
            mActivity.recount();
        }
    }

    private void findOperate(String data) {
        //判断采集到的标签是否与操作明细表里面的数据匹配
        List<InBoundOperate> operates;
        if (mActivity.orderId != 0) {
            operates = operateDBOperator.getItemsByParameters(null
                    , InBoundOperateDao.Properties.InBoundOrderId.eq(mActivity.orderId)
                    , InBoundOperateDao.Properties.Epc.eq(data)
                    , InBoundOperateDao.Properties.OperateQty.eq(0));
        } else {
            operates = operateDBOperator.getItemsByParameters(null
                    , InBoundOperateDao.Properties.InBoundCaseId.in(mActivity.getCasesIdList())
                    , InBoundOperateDao.Properties.Epc.eq(data)
                    , InBoundOperateDao.Properties.OperateQty.eq(0));
        }
        //不匹配
        if (operates.size() == 0) return;
        //匹配的数据操作数加一
        InBoundOperate operate = operates.get(0);
        operate.setOperateQty(1);
        operate.update();

        SoundUtils.play(1);

        sumNum.setText(String.valueOf(Integer.parseInt(sumNum.getText().toString()) + 1));
    }

    @Override
    public void ShowData(List<LinkedTreeMap> linkedTreeMaps) {

    }

    @OnClick({R.id.iv_close_open, R.id.btn_scan_result})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_close_open:
                readOrClose();
                break;
            case R.id.btn_scan_result:
                mActivity.setCurrentFragment(5);
                break;
        }
    }
}
