package com.lubin.chj.view.activity.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.lubin.chj.Listener.OnBroadCaseFinishListener;
import com.lubin.chj.Listener.OnNetReqFinishListener;
import com.lubin.chj.R;
import com.lubin.chj.modle.PickModel;
import com.lubin.chj.service.BarcodeReceiver;
import com.lubin.chj.view.activity.PickActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PickMainFragment extends FragmentBase {
    @BindView(R.id.et_picking_main)
    EditText etPickingMain;
    @BindView(R.id.rb_pick_certificate)
    RadioButton rbPickCertificate;
    @BindView(R.id.rb_pick_direct)
    RadioButton rbPickDirect;
    @BindView(R.id.ll_edit)
    LinearLayout llEdit;
    @BindView(R.id.rb_pick_invoice)
    RadioButton rbPickInvoice;
    @BindView(R.id.tv_label_for_edit)
    TextView tvLabelForEdit;

    private View mView;
    private PickActivity mActivity;
    private PickModel model;
    private Boolean mFlag = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_picking_main, container, false);
        ButterKnife.bind(this, mView);
        mActivity = (PickActivity) getActivity();
        model = new PickModel();
        initView();
        initService();
        return mView;
    }

    private void initView() {
        etPickingMain.requestFocus();
        rbPickCertificate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etPickingMain.requestFocus();
                    tvLabelForEdit.setText("凭证号：");
                    llEdit.setVisibility(View.VISIBLE);
                }
            }
        });
        rbPickDirect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    llEdit.setVisibility(View.GONE);
            }
        });
        rbPickInvoice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etPickingMain.requestFocus();
                    tvLabelForEdit.setText("配货单号：");
                    llEdit.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void initService() {
        BarcodeReceiver.setListener(new OnBroadCaseFinishListener() {
            @Override
            public void onBroadCaseFinish(String data) {
                if (etPickingMain.isEnabled())
                    etPickingMain.setText(data);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        rbPickInvoice.setChecked(true);
        etPickingMain.setText(" ");
    }

    @OnClick(R.id.btn_pick)
    public void onClick() {
        if (rbPickCertificate.isChecked()) {
            String s = etPickingMain.getText().toString();
            if (s.equals("")) return;
            mActivity.changeFragment(1);
            PickCertificateFragment fragment = (PickCertificateFragment) mActivity
                    .getSupportFragmentManager()
                    .findFragmentByTag("1");
            fragment.QueryPcByPzh(s);
            fragment.initService();
            mActivity.mFlag = "条码";
        } else if (rbPickDirect.isChecked()) {
            mActivity.changeFragment(2);
            PickDirectFragment fragment = (PickDirectFragment) mActivity
                    .getSupportFragmentManager()
                    .findFragmentByTag("2");
            fragment.initService();
            fragment.clearData();
            mActivity.mFlag = "条码";
        } else if (rbPickInvoice.isChecked()) {
            final String s = etPickingMain.getText().toString();
            if (s.equals(" ") || s.equals("")) {
                mActivity.changeFragment(3);
                PickInvoiceOderFragment fragment = (PickInvoiceOderFragment) mActivity
                        .getSupportFragmentManager()
                        .findFragmentByTag("3");
                fragment.queryList("zd");
            } else {
                if (!mFlag) {
                    mFlag = true;
                } else {
                    return;
                }
                model.GetPzhDetailResult("phdh", s, new OnNetReqFinishListener() {
                    @Override
                    public void OnNetReqFinish(Map<String, Object> hashMap) {
                        mFlag = false;
                        Object result = hashMap.get("result");
                        if (result == null) return;
                        String returnCode = null;
                        String returnMsg = null;
                        try {
                            JSONObject jsonObject = new JSONObject(result.toString());
                            returnCode = jsonObject.getString("returnCode");
                            returnMsg = jsonObject.getString("returnMsg");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (returnCode == null) {
                            return;
                        }
                        if (!returnCode.equals("0000")) {
                            ShowToast(returnMsg);
                            return;
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mActivity.changeFragment(4);
                                PickInvoiceFragment fragment = (PickInvoiceFragment) mActivity
                                        .getSupportFragmentManager()
                                        .findFragmentByTag("4");
                                fragment.initService();
                                fragment.startPickPhdh(s);
                                mActivity.mFlag = "条码";
                            }
                        });
                    }
                });
            }
        }
    }

    @Override
    public void finishByBackBtn() {
        getActivity().finish();
    }

    @Override
    public void finishByBackIcon() {
        getActivity().finish();
    }
}
