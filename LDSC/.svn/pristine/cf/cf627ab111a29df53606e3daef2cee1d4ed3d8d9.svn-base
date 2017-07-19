package com.lide.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lide.app.R;
import com.lide.app.bean.JsonToBean.UR.TaskList;
import com.lide.app.persistence.util.DBOperator;
import com.lide.app.ui.takeStock.TakeStockTransaction;
import com.lubin.bean.TakeStockTask;
import com.lubin.dao.TakeStockTaskDao;

import java.util.List;

public class TaskOrderAdapter extends BaseListAdapter<TaskList.DataBean> {
    private DBOperator<TakeStockTaskDao, TakeStockTask> taskDBOperator;

    public TaskOrderAdapter(Context context, List<TaskList.DataBean> list) {
        super(context, list);
        taskDBOperator = TakeStockTransaction.getTaskDBOperator();
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_search_inbound_order, parent, false);
        }
        TaskList.DataBean data = getList().get(position);

        TextView order_name = (TextView) convertView.findViewById(R.id.order_name);
        TextView order_address = (TextView) convertView.findViewById(R.id.order_address);
        TextView order_audit_state = (TextView) convertView.findViewById(R.id.order_audit_state);
        TextView fromWarehouseCode = (TextView) convertView.findViewById(R.id.order_from_warehouseCode);

        order_name.setText(data.getCode());
        if (data.getModified().length() > 10) {
            String result = data.getModified().substring(0, 10);
            order_address.setText(result);
        }
        fromWarehouseCode.setText("来源单号：" + data.getTakeStockOrderCode());

        if (taskDBOperator.getItemByParameter(TakeStockTaskDao.Properties.CodeId, data.getId()).size() > 0) {
            order_audit_state.setText("已下载");
            order_audit_state.setTextColor(R.color.goods_delivery_title_bg);
        } else {
            order_audit_state.setText("下载");
            order_audit_state.setTextColor(R.color.black);
        }
        return convertView;
    }
}
