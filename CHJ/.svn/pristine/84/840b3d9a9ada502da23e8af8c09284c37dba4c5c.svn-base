package com.lubin.chj.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.lubin.chj.R;
import com.lubin.chj.bean.jsonToBean.GetPzhDetailResult;
import com.lubin.chj.view.activity.Fragment.PickCertificateFragment;

import java.util.List;

public class PickByCertificateAdapter extends BaseListAdapter<GetPzhDetailResult.ListBean> {
    List<Boolean> flags;
    PickCertificateFragment fragment;

    public PickByCertificateAdapter(Context context, List<GetPzhDetailResult.ListBean> list, List<Boolean> flags, PickCertificateFragment fragment) {
        super(context, list);
        this.flags = flags;
        this.fragment = fragment;
    }

    @Override
    public View bindView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_lv_pick, parent, false);

        }
        final GetPzhDetailResult.ListBean listBean = getList().get(position);
        CheckBox cbQick = (CheckBox) convertView.findViewById(R.id.cb_isPick);
        TextView index = (TextView) convertView.findViewById(R.id.tv_index);
        TextView wlh = (TextView) convertView.findViewById(R.id.tv_wlh);
        TextView pch = (TextView) convertView.findViewById(R.id.tv_pch);
        TextView gwh = (TextView) convertView.findViewById(R.id.tv_gwh);
        TextView qybh = (TextView) convertView.findViewById(R.id.tv_qybh);
        if (flags.size() == position) {
            flags.add(false);
        }
        cbQick.setChecked(flags.get(position));
        index.setText(String.valueOf(position + 1));
        wlh.setText(listBean.getWlh());
        gwh.setText(listBean.getGwbh());
        qybh.setText(listBean.getQybh());
        pch.setText(listBean.getPch());

        String storeCode = fragment.storeCode.getText().toString();
        if (storeCode.equals("")) {
            String data = listBean.getFkxx().toString();
            String[] strings = data.split("/");
            if (strings.length == 4) {
                fragment.storeCode.setText(strings[0]);
                fragment.allNum.setText(strings[2]);
                fragment.pickNum.setText(strings[3]);
            }
        }


        return convertView;
    }

}
