package com.lide.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.internal.LinkedTreeMap;
import com.lide.app.R;

import java.util.List;

/**
 * Created by lubin on 2016/11/26.
 */

public class BindingAdapter extends BaseListAdapter<LinkedTreeMap> {

    public BindingAdapter(Context context, List<LinkedTreeMap> list) {
        super(context, list);
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_binding, parent, false);
        }
        LinkedTreeMap epcData = getList().get(position);

        TextView num = ViewHolder.get(convertView, R.id.tv_index);
        TextView epc = ViewHolder.get(convertView, R.id.tv_item_epc);
        TextView status = ViewHolder.get(convertView, R.id.tv_item_status);

        num.setText(String.valueOf(position + 1));
        epc.setText(epcData.get("epc").toString());
        if (epcData.get("barcode") != null) {
            status.setVisibility(View.VISIBLE);
            status.setText("已绑定");
        } else {
            status.setVisibility(View.GONE);
        }

        return convertView;
    }
}
