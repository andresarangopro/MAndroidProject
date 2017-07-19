package com.lubin.chj.view.activity.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lubin.chj.R;
import com.lubin.chj.view.activity.InventoryActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author DaiJiCheng
 * @time 2016/9/27  16:17
 * @desc ${TODD}
 */
public class InventoryMainFragment extends FragmentBase {

    @BindView(R.id.btn_inventory_gw)
    Button mBtnInventoryGw;

    private View mView;
    private InventoryActivity mActivity;
    private int FRAGMETNT_INVERTORY_BY_GW_FIRST = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_inventory_main, container, false);
        ButterKnife.bind(this, mView);
        return mView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (InventoryActivity) getActivity();
    }

    @OnClick({R.id.btn_inventory_gw})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_inventory_gw:
                mActivity.changeFragment(FRAGMETNT_INVERTORY_BY_GW_FIRST);
                break;
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
