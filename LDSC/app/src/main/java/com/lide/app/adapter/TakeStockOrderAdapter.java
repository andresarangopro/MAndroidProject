package com.lide.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lide.app.R;
import com.lubin.bean.TakeStockOrder;

import java.util.List;

public class TakeStockOrderAdapter extends BaseListAdapter<TakeStockOrder> {
    public TakeStockOrderAdapter(Context context, List<TakeStockOrder> list) {
        super(context, list);
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_list_tack_stock_order, parent, false);
        }
        TakeStockOrder data = getList().get(position);

        TextView name = ViewHolder.get(convertView, R.id.tv_item_order_name);
        TextView warehouseName = ViewHolder.get(convertView, R.id.tv_item_order_warehouseName);

        name.setText("单号：" + data.getCode());
        warehouseName.setText("仓库：" + data.getWarehouseName());

        return convertView;
    }
}
