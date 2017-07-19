package com.lide.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lide.app.R;
import com.lide.app.bean.JsonToBean.OrderForId;
import com.lide.app.model.MInterface.IInBoundOrderModel;
import com.lide.app.ui.SearchActivity;

import java.util.List;

/**
 * Created by huangjianxionh on 2016/10/10.
 */

public class SearchOrderAdapter extends BaseListAdapter<OrderForId.DataBean> {

    private TextView order_name;
    private TextView order_address;
    private TextView order_audit_state;

    private IInBoundOrderModel model;
    private SearchActivity mActivity;
    private TextView fromWarehouseCode;

    public SearchOrderAdapter(Context context, List<OrderForId.DataBean> list, IInBoundOrderModel model, SearchActivity mActivity) {
        super(context, list);
        this.model = model;
        this.mActivity = mActivity;
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_search_inbound_order, parent, false);
        }
        OrderForId.DataBean data = getList().get(position);

        order_name = (TextView) convertView.findViewById(R.id.order_name);
        order_address = (TextView) convertView.findViewById(R.id.order_address);
        order_audit_state = (TextView) convertView.findViewById(R.id.order_audit_state);
        fromWarehouseCode = (TextView) convertView.findViewById(R.id.order_from_warehouseCode);

        order_name.setText(data.getCode());
        String result = data.getModified().substring(0, data.getModified().lastIndexOf("."));
        order_address.setText(result);
        fromWarehouseCode.setText("来源单号：" + data.getSourceOrderCode());
        if (model.loadDataForDB("order", data.getId())) {
            order_audit_state.setText("已下载");
            order_audit_state.setTextColor(mActivity.getResources().getColor(R.color.goods_delivery_title_bg));
        }

        return convertView;
    }

}
