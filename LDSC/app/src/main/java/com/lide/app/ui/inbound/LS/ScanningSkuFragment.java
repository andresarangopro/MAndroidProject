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
import com.lide.app.persistence.util.PlaySoundPoolUtils;
import com.lide.app.persistence.util.SPUtils;
import com.lide.app.persistence.util.SoundUtils;
import com.lide.app.presenter.ScanPresenter;
import com.lide.app.presenter.inbound.InBoundPresenter;
import com.lide.app.ui.FragmentBase;
import com.lide.app.ui.VInterface.IDataFragmentView;
import com.lide.app.ui.inbound.InboundTransaction;
import com.lubin.bean.InBoundDetail;
import com.lubin.bean.InBoundOperate;
import com.lubin.dao.InBoundOperateDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lubin on 2016/12/1.
 */

public class ScanningSkuFragment extends FragmentBase implements IDataFragmentView<List<LinkedTreeMap>> {

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
    private DBOperator<InBoundOperateDao, InBoundOperate> operateDBOperator;
    private ScanPresenter scanPresenter;
    private InBoundPresenter inBoundPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_scan_barcode, container, false);
        ButterKnife.bind(this, mView);
        initView();
        operateDBOperator = InboundTransaction.getOperateDBOperator();
        return mView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            refreshData();
            initScanPresenter();
        } else {
            if (scanPresenter == null) return;
            scanPresenter.setMode(0);
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

    //刷新界面
    private void refreshData() {
        List<InBoundDetail> container = new ArrayList<>();
        List<InBoundOperate> operates;
        if (mActivity.orderId != 0) {
            operates = operateDBOperator.getItemsByParameters(null
                    , InBoundOperateDao.Properties.InBoundOrderId.eq(mActivity.orderId)
                    , InBoundOperateDao.Properties.Epc.isNull());

        } else {
            operates = operateDBOperator.getItemsByParameters(null
                    , InBoundOperateDao.Properties.InBoundCaseId.in(mActivity.getCasesIdList())
                    , InBoundOperateDao.Properties.Epc.isNull());
        }
        Collections.sort(operates, new Comparator<InBoundOperate>() {
            @Override
            public int compare(InBoundOperate lhs, InBoundOperate rhs) {
                return lhs.getInBoundDetail().getDate().compareTo(rhs.getInBoundDetail().getDate());
            }
        });
        mAdapter.addAll(container);
        etSku.setText("");
        etNum.setText("1");

        int qty = 0;
        int operateQty = 0;
        //如果有相同的sku明细，合并显示
        Iterator<InBoundOperate> iterator = operates.iterator();
        while (iterator.hasNext()) {
            InBoundOperate next = iterator.next();
            InBoundDetail data = null;
            for (InBoundDetail detail : container) {
                if (detail.getBarcode().equals(next.getBarcode())) {
                    data = detail;
                }
            }
            if (data == null) {
                data = new InBoundDetail();
                data.setQty(next.getQty());
                data.setOperateQty(next.getOperateQty());
                data.setDate(next.getInBoundDetail().getDate());
                data.setBarcode(next.getBarcode());
                data.setSkuName(next.getInBoundDetail().getSkuName());
                container.add(data);
            } else {
                data.setQty(data.getQty() + next.getQty());
                data.setOperateQty(data.getOperateQty() + next.getOperateQty());
                data.setDate(next.getInBoundDetail().getDate());
                data.setBarcode(next.getBarcode());
                data.setSkuName(next.getInBoundDetail().getSkuName());
            }
            qty += next.getQty();
            operateQty += next.getOperateQty();
        }
        tvAllNum.setText(qty + "/" + operateQty);
        mAdapter.addAll(container);
    }

    //初始化读写器
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
        updateDetail(num, barcode);
    }

    //刷新明细表的操作数，还有操作表的操作数
    private void updateDetail(int num, String barcode) {
        List<InBoundOperate> inBoundOperates;
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

        if (inBoundOperates.size() == 0) {
            showDialog("条码：" + barcode + "错误!");
            SoundUtils.playErrorSound();
            return;
        }

        int Qty = 0;
        int OperateQty = 0;
        for (InBoundOperate inBoundOperate : inBoundOperates) {
            Qty += inBoundOperate.getQty();
            OperateQty += inBoundOperate.getOperateQty();
        }
        //是否运行超收
        if (SPUtils.get(MApplication.getInstance(), "inboundAllowExceed", "false").toString().equals("true")) {
            if (OperateQty + num >= 0) {
                for (InBoundOperate inBoundOperate : inBoundOperates) {
                    if (num >= 0) {
                        int qty = inBoundOperate.getQty() - inBoundOperate.getOperateQty();
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
        } else {
            if (Qty >= OperateQty + num && OperateQty + num >= 0) {
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
