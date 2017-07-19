package com.lide.app.ui.entry;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lide.app.R;
import com.lide.app.config.Configs;
import com.lide.app.ui.FragmentBase;
import com.lide.app.ui.SearchActivity;
import com.lide.app.ui.outbound.ControlOutBoundActivity;
import com.lide.app.ui.outbound.ur.OutBoundOrderActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Li De on 2016/9/22.
 */
//发货界面
public class OutBoundEnterFragment extends FragmentBase {


    private View mView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_outbound_enter, container, false);
        ButterKnife.bind(this, mView);
        return mView;
    }


    @OnClick({R.id.download_goods_order,R.id.out_bound_by_create, R.id.out_bound_by_download})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.download_goods_order:
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("Flag", Configs.SearchOutBoundOrderFragment);
                startAnimActivity(intent);
                break;
            case R.id.out_bound_by_create:
                intent = new Intent(getActivity(), ControlOutBoundActivity.class);
                startAnimActivity(intent);
                break;
            case R.id.out_bound_by_download:
                intent = new Intent(getActivity(), OutBoundOrderActivity.class);
                startAnimActivity(intent);
                break;
        }
    }
}
