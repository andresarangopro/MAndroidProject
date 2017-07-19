package com.lide.app.ui.entry;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lide.app.R;
import com.lide.app.ui.FragmentBase;
import com.lide.app.ui.detection.DetectionActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lubin on 2016/12/5.
 */

public class DetectEnterFragment extends FragmentBase {

    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_detect_enter, container, false);
        ButterKnife.bind(this, mView);
        return mView;
    }

    @OnClick(R.id.btn_detect_epc_list)
    public void onClick() {
        startAnimActivity(new Intent(getActivity(), DetectionActivity.class));
    }
}
