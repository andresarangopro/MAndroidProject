package com.lide.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lide.app.R;
import com.lide.app.bean.JsonToBean.EPCDiff;

import java.util.List;

/**
 * @author DaiJiCheng
 * @time 2016/7/20  10:11
 * @desc ${盘点单界面listview适配器
 */
public class QueryEpcAdapter extends BaseListAdapter<EPCDiff.DataBean> {

    public QueryEpcAdapter(Context context, List<EPCDiff.DataBean> list) {
        super(context, list);
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_query_epc_list, parent, false);
        }
        EPCDiff.DataBean data = getList().get(position);
        TextView epc = ViewHolder.get(convertView, R.id.tv_epc_num);
        TextView sku = ViewHolder.get(convertView, R.id.tv_epc_sku);
        TextView index = ViewHolder.get(convertView, R.id.tv_index);

        index.setText(String.valueOf(position + 1));
        epc.setText(data.getEpc());
        sku.setText(data.getBarcode());
        return convertView;
    }
}
