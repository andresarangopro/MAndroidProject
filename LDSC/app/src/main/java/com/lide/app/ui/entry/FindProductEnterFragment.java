package com.lide.app.ui.entry;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.gson.internal.LinkedTreeMap;
import com.lide.app.R;
import com.lide.app.config.Configs;
import com.lide.app.listener.OnFinishListener;
import com.lide.app.presenter.ScanPresenter;
import com.lide.app.ui.FragmentBase;
import com.lide.app.ui.SearchActivity;
import com.lide.app.ui.VInterface.IDataFragmentView;
import com.lide.app.ui.findProduct.FindActivity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Li De on 2016/9/22.
 */
public class FindProductEnterFragment extends FragmentBase implements IDataFragmentView<List<LinkedTreeMap>> {

    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_find_goods, container, false);
        ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void ShowData(List<LinkedTreeMap> linkedTreeMaps) {

    }

    @OnClick({R.id.find_bound_epc, R.id.find_bound_sku})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.find_bound_epc:
                Intent intent1 = new Intent(getActivity(), FindActivity.class);
                intent1.putExtra("Activity", "FindProductFragment");
                startAnimActivity(intent1);
                break;
            case R.id.find_bound_sku:
                Intent intent2 = new Intent(getActivity(), SearchActivity.class);
                intent2.putExtra("Flag", Configs.SearchFindProductFragment);
                startAnimActivity(intent2);
                break;
        }
    }
}
