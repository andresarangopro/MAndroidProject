package com.lubin.chj.view.activity.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.lubin.chj.Listener.OnNetReqFinishListener;
import com.lubin.chj.R;
import com.lubin.chj.modle.addedModel.CheckGtGwQyModel;
import com.lubin.chj.view.activity.BaseActivity;
import com.lubin.chj.view.activity.InventoryActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author DaiJiCheng
 * @time 2016/9/28  9:24
 * @desc ${TODD}
 */
public class InventoryByGwFragmentFirst extends FragmentBase {

    @BindView(R.id.bt_next)
    Button mBtNext;
    @BindView(R.id.ll_inventory_first)
    LinearLayout mLlInventoryFirst;
    @BindView(R.id.et_gwbh)
    EditText etGwbh;
    private View mView;
    private InventoryActivity mActivity;
    private InputMethodManager mImm;
    public static String GWBH;
    private final static String SCAN_ACTION = "scan.rcv.message";
    private CheckGtGwQyModel model;
    private boolean mFlag = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.inventory_mode_first, container, false);
        ButterKnife.bind(this, mView);
        model = new CheckGtGwQyModel();
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (InventoryActivity) getActivity();
        mImm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        etGwbh.requestFocus();
    }

    @Override
    public void setBarcode(String barcode) {
        if (barcode.length() != 6) {
            ShowToast("请输入正确的柜位!");
            return;
        }
        if (etGwbh.isEnabled())
            etGwbh.setText(barcode);
    }

    @OnClick(R.id.bt_next)
    public void onClick() {
        if (!mFlag) {
            mFlag = true;
        } else {
            return;
        }
        GWBH = etGwbh.getText().toString();
        model.CheckGtGwQy(GWBH, "gwbh", new OnNetReqFinishListener() {

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
                        InventoryByGwFragment fragment = (InventoryByGwFragment) mActivity.getSupportFragmentManager().findFragmentByTag("2");
                        fragment.clearData();
                        fragment.QueryPcByGw(GWBH);
                        mImm.hideSoftInputFromWindow(mBtNext.getWindowToken(), 0);
                        mActivity.changeFragment(2);
                    }
                });
            }
        });
    }

    @Override
    public void finishByBackBtn() {
        getActivity().finish();
    }

    @Override
    public void finishByBackIcon() {
        ((BaseActivity) getActivity()).changeFragment(0);
    }


}
