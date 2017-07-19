package com.lide.app.ui.outbound.ur;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.lide.app.R;
import com.lide.app.adapter.BaseListAdapter;
import com.lide.app.bean.JsonToBean.UR.OutBoundOrderListBean;
import com.lide.app.config.Configs;
import com.lide.app.persistence.util.DBOperator;
import com.lide.app.persistence.util.Utils;
import com.lide.app.persistence.view.xlist.XListView;
import com.lide.app.presenter.outbound.OutBoundOrderPresenter;
import com.lide.app.ui.FragmentBase;
import com.lide.app.ui.LoginActivity;
import com.lide.app.ui.VInterface.IDataFragmentView;
import com.lide.app.ui.outbound.createOrder.OutboundTransaction;
import com.lubin.bean.OutBoundOrder;
import com.lubin.dao.OutBoundOrderDao;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by HKR on 2017/3/13.
 */

public class SearchOutOrderFragment extends FragmentBase implements XListView.IXListViewListener, IDataFragmentView<OutBoundOrderListBean> {
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.lv_orders)
    XListView lvOrders;
    private View mView;

    private SearchOutOrders mAdapter;
    private List<OutBoundOrderListBean.DataBean> mList = new ArrayList<>();
    private String orderCode;

    private OutBoundOrderPresenter mPresenter;
    private DBOperator<OutBoundOrderDao, OutBoundOrder> orderDBOperator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_search_ib_orders, container, false);
        ButterKnife.bind(this, mView);
        mPresenter = new OutBoundOrderPresenter(this);
        orderDBOperator = OutboundTransaction.getOrderDBOperator();
        initView();
        initListView();
        return mView;
    }

    private void initView() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAdapter.clearAll();
                lvOrders.stopRefresh();
                lvOrders.stopLoadMore();
                lvOrders.setPullLoadEnable(false);
                lvOrders.setPullRefreshEnable(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initListView() {
        lvOrders.setPullLoadEnable(false);
        lvOrders.setPullRefreshEnable(false);
        lvOrders.setXListViewListener(this);
        mAdapter = new SearchOutOrders(getActivity(), mList);
        lvOrders.setAdapter(mAdapter);
        lvOrders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPresenter.loadOrderDetail(mList.get(position - 1));
            }
        });
    }

    @OnClick({R.id.btn_search, R.id.btn_download_all})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_search:
                orderCode = etSearch.getText().toString();
                startNetWork(orderCode);
                break;
            case R.id.btn_download_all:
//                mPresenter.loadOrderListDetail(mList);
                break;
        }
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        index++;
        mPresenter.getOutBoundOrderList(true, index, 10, orderCode);
    }

    int index = 1;

    private void startNetWork(String orderCode) {
        if (Utils.getCurrentUser() != null) {
            lvOrders.setPullLoadEnable(true);
            index = 1;
            mPresenter.getOutBoundOrderList(true, index, 10, orderCode);
        } else {
            //未登录
            Intent login = new Intent(getActivity(), LoginActivity.class);
            login.putExtra("isAtNet", true);
            startActivityForResult(login, Configs.LOGIN);
        }
    }

    @Override
    public void setBarcode(String barcode) {
        if (barcode == null || barcode.isEmpty()) {
            return;
        }
        if (etSearch != null) {
            orderCode = barcode;
            etSearch.setText(barcode);
            startNetWork(barcode);
            mAdapter.clearAll();
        }
    }


    @Override
    public void ShowData(final OutBoundOrderListBean outBoundOrderListBean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (index > outBoundOrderListBean.getCurrentPage()) {
                    showDialog("已经是最后一页!");
                    lvOrders.setPullLoadEnable(false);
                } else {
                    if (index == 1) mList.clear();
                    mList.addAll(outBoundOrderListBean.getData());
                    mAdapter.notifyDataSetChanged();
                    lvOrders.setPullLoadEnable(true);
                    lvOrders.stopRefresh();
                    lvOrders.stopLoadMore();
                }
            }
        });
    }

    class SearchOutOrders extends BaseListAdapter<OutBoundOrderListBean.DataBean> {

        public SearchOutOrders(Context context, List<OutBoundOrderListBean.DataBean> list) {
            super(context, list);
        }

        @Override
        public View bindView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_search_inbound_order, parent, false);
            }
            OutBoundOrderListBean.DataBean data = getList().get(position);

            TextView order_name = (TextView) convertView.findViewById(R.id.order_name);
            TextView order_address = (TextView) convertView.findViewById(R.id.order_address);
            TextView order_audit_state = (TextView) convertView.findViewById(R.id.order_audit_state);
            TextView fromWarehouseCode = (TextView) convertView.findViewById(R.id.order_from_warehouseCode);

            String code = data.getCode();
            if (code != null) {
                order_name.setText(code);
            } else {
                order_name.setText("");
            }
            if (data.getModified() != null) {
                String modified = data.getModified();
                String result = modified.substring(0, modified.lastIndexOf("."));
                order_address.setText(result);
            } else {
                order_address.setText("");
            }
            if (data.getSourceOrderCode() != null) {
                fromWarehouseCode.setText(String.valueOf("来源单号：" + data.getSourceOrderCode()));
            } else {
                fromWarehouseCode.setText("");
            }
            List<OutBoundOrder> list = orderDBOperator.getItemByParameter(OutBoundOrderDao.Properties.Code, code);
            if (list.size() == 1) {
                order_audit_state.setText("已下载");
                order_audit_state.setTextColor(mContext.getResources().getColor(R.color.goods_delivery_title_bg));
            } else {
                order_audit_state.setText("下载");
                order_audit_state.setTextColor(Color.BLACK);
            }

            return convertView;
        }

    }

    @Override
    public void ShowToast(String text) {
        super.ShowToast(text);
        mAdapter.notifyDataSetChanged();
    }
}
