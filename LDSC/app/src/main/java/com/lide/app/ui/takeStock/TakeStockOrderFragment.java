package com.lide.app.ui.takeStock;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lide.app.R;
import com.lide.app.adapter.TakeStockOrderAdapter;
import com.lide.app.presenter.PInterface.ITakeStockOrderPresenter;
import com.lide.app.ui.VInterface.IShowDataView;
import com.lide.app.ui.VInterface.ITakeStockView;
import com.lide.app.ui.FragmentBase;
import com.lubin.bean.TakeStockOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author DaiJiCheng
 * @time 2016/7/20  9:23
 * @desc ${盘点单界面}
 */
public class TakeStockOrderFragment extends FragmentBase implements IShowDataView<List<TakeStockOrder>> {
    @BindView(R.id.lv_contain_order)
    ListView lvContainOrder;
    private View mView;
    private List<TakeStockOrder> takeStockOrders;

    private TakeStockOrderAdapter mAdapter;
    private ITakeStockOrderPresenter mPresenter;
    private ITakeStockView mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_takestock_order, container, false);
        ButterKnife.bind(this, mView);
        initView();
        setAdapter();
        return mView;
    }

    private void initView() {
        mActivity = (ITakeStockView) getActivity();
        mPresenter = mActivity.getOrderPresenter();
    }

    private void setAdapter() {
        takeStockOrders = new ArrayList<>();
        mAdapter = new TakeStockOrderAdapter(mContext, takeStockOrders);
        lvContainOrder.setAdapter(mAdapter);
        lvContainOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPresenter.bindCheckTask(mAdapter.getList().get(position));
            }
        });
    }

    @Override
    public void ShowData(final List<TakeStockOrder> takeStockOrders) {
        runOnUiThread(new TimerTask() {
            @Override
            public void run() {
                mAdapter.addAll(takeStockOrders);
            }
        });
    }
}