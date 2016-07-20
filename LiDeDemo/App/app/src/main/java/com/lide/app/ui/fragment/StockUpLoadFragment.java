package com.lide.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lide.app.R;
import com.lide.app.ui.StockTakingActivity;

/**
 * @author DaiJiCheng
 * @time 2016/7/20  9:08
 * @desc ${上传盘点任务界面}
 */
public class StockUpLoadFragment extends FragmentBase{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stock_upload,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        StockTakingActivity activity = (StockTakingActivity) getActivity();
        activity.setReadingText("正在上传...");
    }
}
