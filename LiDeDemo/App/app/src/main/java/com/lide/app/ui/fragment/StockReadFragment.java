package com.lide.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lide.app.R;

/**
 * @author DaiJiCheng
 * @time 2016/7/19  9:32
 * @desc ${扫描标签信息界面}
 */
public class StockReadFragment extends FragmentBase {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stock_read, container, false);
        return view;
    }
}
