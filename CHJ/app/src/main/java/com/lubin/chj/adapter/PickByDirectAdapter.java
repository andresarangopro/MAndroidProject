package com.lubin.chj.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.lubin.chj.R;
import com.lubin.chj.bean.jsonToBean.QueryPcReturn;

import java.util.List;

public class PickByDirectAdapter extends BaseListAdapter<QueryPcReturn.ListBean> {
    List<Boolean> flags;

    public PickByDirectAdapter(Context context, List<QueryPcReturn.ListBean> list, List<Boolean> flags) {
        super(context, list);
        this.flags = flags;
    }

    @Override
    public View bindView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_lv_pick, parent, false);

        }
        final QueryPcReturn.ListBean listBean = getList().get(position);
        CheckBox cbQick = (CheckBox) convertView.findViewById(R.id.cb_isPick);
        TextView index = (TextView) convertView.findViewById(R.id.tv_index);
        TextView wlh = (TextView) convertView.findViewById(R.id.tv_wlh);
        TextView pch = (TextView) convertView.findViewById(R.id.tv_pch);
        TextView gwh = (TextView) convertView.findViewById(R.id.tv_gwh);
        TextView qybh = (TextView) convertView.findViewById(R.id.tv_qybh);
        if (flags.size() == position) {
            flags.add(true);
        }
        cbQick.setChecked(flags.get(position));
        index.setText(String.valueOf(position + 1));
        if (listBean.getWlh() == null) {
            wlh.setText("");
        } else {
            wlh.setText(listBean.getWlh().toString());
        }
        if (listBean.getGwbh() == null) {
            gwh.setText("");
        } else {
            gwh.setText(listBean.getGwbh().toString());
        }
        if (listBean.getQybh() == null) {
            qybh.setText("");
        } else {
            String qy = listBean.getQybh().toString();
            qybh.setText(qy.substring(qy.length() - 2, qy.length()));
        }

        pch.setText(listBean.getPch());

        return convertView;
    }

}
