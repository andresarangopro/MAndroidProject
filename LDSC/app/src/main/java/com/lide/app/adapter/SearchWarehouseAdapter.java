package com.lide.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.gson.internal.LinkedTreeMap;
import com.lide.app.R;
import com.lide.app.bean.JsonToBean.Container.Warehouse;

import java.util.List;

/**
 * Created by lubin on 2016/11/17.
 */

public class SearchWarehouseAdapter extends BaseListAdapter<LinkedTreeMap> {

    public SearchWarehouseAdapter(Context context, List<LinkedTreeMap> list) {
        super(context, list);
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_list_item, parent, false);
        }

        TextView code = ViewHolder.get(convertView, R.id.tv_item_name);
        code.setText(getList().get(position).get("code").toString());
        return convertView;
    }
}
