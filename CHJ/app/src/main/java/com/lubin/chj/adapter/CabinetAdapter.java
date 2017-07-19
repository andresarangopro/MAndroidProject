package com.lubin.chj.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lubin.chj.R;
import com.lubin.chj.bean.jsonToBean.CabinetReturn;

import java.util.List;

/**
 * Created by lubin on 2016/9/22.
 */
public class CabinetAdapter extends BaseListAdapter<CabinetReturn.ListBean> {

    public CabinetAdapter(Context context, List<CabinetReturn.ListBean> list) {
        super(context, list);
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_lv_cabinet, parent, false);
        }
        CabinetReturn.ListBean listBean = getList().get(position);
        TextView qybh = (TextView) convertView.findViewById(R.id.tv_qybh);
        TextView sjsl = (TextView) convertView.findViewById(R.id.tv_sjsl);
        TextView kysl = (TextView) convertView.findViewById(R.id.tv_kysl);
        qybh.setText(listBean.getQybh() + "");
        sjsl.setText(listBean.getSjcfsl() + "");
        kysl.setText((listBean.getBzcfsl() - listBean.getSjcfsl()) + "");
        return convertView;
    }
}
