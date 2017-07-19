package com.lide.app.ui.outbound.ur;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

import com.lide.app.R;
import com.lide.app.adapter.OutBoundOrderAdapter;
import com.lide.app.config.Configs;
import com.lide.app.persistence.widget.view.SwipeListView;
import com.lide.app.presenter.outbound.ControlOBOrderByCreatePresenter;
import com.lide.app.ui.FragmentBase;
import com.lide.app.ui.VInterface.IDataFragmentView;
import com.lide.app.ui.outbound.createOrder.ScanOBOrderByCreateActivity;
import com.lubin.bean.OutBoundOrder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HKR on 2017/3/7.
 */
public class OutBoundOrderFragment extends FragmentBase implements IDataFragmentView<List<OutBoundOrder>> {

    @BindView(R.id.lv_contain_out_bound_case)
    SwipeListView lvContainOutBoundCase;
    @BindView(R.id.btn_create_out_bound_case)
    Button btnCreateOutBoundCase;

    private List<OutBoundOrder> mOrders = new ArrayList<>();
    private View mView;
    private ControlOBOrderByCreatePresenter mPresenter;
    private OutBoundOrderAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_create_out_bound_order, container, false);
        ButterKnife.bind(this, mView);
        mPresenter = new ControlOBOrderByCreatePresenter(this);
        init();
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadAllOutBoundOrder();
    }

    public void init() {
        btnCreateOutBoundCase.setVisibility(View.GONE);
        mAdapter = new OutBoundOrderAdapter(getActivity(), mOrders, mPresenter);
        lvContainOutBoundCase.setAdapter(mAdapter);
        lvContainOutBoundCase.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ScanOBOrderByCreateActivity.class);
                intent.putExtra(Configs.OutBoundOrderId, mOrders.get(position).getId());
                startAnimActivity(intent);
            }
        });
    }

    @Override
    public void ShowData(List<OutBoundOrder> outBoundOrders) {
        List<OutBoundOrder> outBoundOrderList = new ArrayList<>();
        for (Iterator<OutBoundOrder> iterator = outBoundOrders.iterator(); iterator.hasNext(); ) {
            OutBoundOrder next = iterator.next();
            if (!next.getIsCreate()) {
                outBoundOrderList.add(next);
            }
        }
        mAdapter.addAll(outBoundOrderList);
    }

    @Override
    public void ShowToast(String text) {
        if (text.equals("审核成功")) {
            mAdapter.notifyDataSetChanged();
        }
    }
}
