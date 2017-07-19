package com.lubin.chj.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.lubin.chj.R;
import com.lubin.chj.bean.jsonToBean.QueryPcReturn;

import java.util.List;

/**
 * Created by lubin on 2016/11/8.
 */

public class SearchAdapter extends BaseListAdapter<QueryPcReturn.ListBean> {
    List<Boolean> flags;

    public SearchAdapter(Context context, List<QueryPcReturn.ListBean> list, List<Boolean> flags) {
        super(context, list);
        this.flags = flags;
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_lv_search, parent, false);

        }
        final QueryPcReturn.ListBean listBean = getList().get(position);
        CheckBox cb_selection = ViewHolder.get(convertView, R.id.cb_isSelection);
        TextView pch = (TextView) convertView.findViewById(R.id.tv_pch);
        TextView gwh = (TextView) convertView.findViewById(R.id.tv_gwh);
        TextView qybh = (TextView) convertView.findViewById(R.id.tv_qybh);

        cb_selection.setChecked(false);
        cb_selection.setChecked(flags.get(position));
        if (listBean.getGwbh() == null) {
            gwh.setText("");
        } else {
            gwh.setText(listBean.getGwbh().toString());
        }
        if (listBean.getQybh() == null) {
            qybh.setText("");
        } else {
            qybh.setText(listBean.getQybh().toString());
        }

        pch.setText(listBean.getPch());

        return convertView;
    }
}
