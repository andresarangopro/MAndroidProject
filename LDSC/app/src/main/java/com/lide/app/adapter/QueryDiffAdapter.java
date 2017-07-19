package com.lide.app.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lide.app.R;
import com.lide.app.bean.JsonToBean.ProductDiff;

import java.util.List;

/**
 * @author DaiJiCheng
 * @time 2016/7/20  10:11
 * @desc ${盘点单界面listview适配器}
 */
public class QueryDiffAdapter extends BaseListAdapter<ProductDiff.DataBean> {

    public QueryDiffAdapter(Context context, List<ProductDiff.DataBean> list) {
        super(context, list);
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_query_list, parent, false);
        }

        ProductDiff.DataBean bean = getList().get(position);

        TextView code = ViewHolder.get(convertView, R.id.tv_diff_Code);
        TextView snapQty = ViewHolder.get(convertView, R.id.tv_diff_snapQty);
        TextView countQty = ViewHolder.get(convertView, R.id.tv_diff_countQty);
        TextView diffQty = ViewHolder.get(convertView, R.id.tv_diff_diffQty);


        snapQty.setText(String.valueOf(bean.getSnapQty()));
        countQty.setText(String.valueOf(bean.getCountQty()));
        diffQty.setText(String.valueOf(bean.getDiffQty()));
        diffQty.setTextColor(Color.RED);
        code.setText(bean.getProductCode());

        return convertView;
    }


}
