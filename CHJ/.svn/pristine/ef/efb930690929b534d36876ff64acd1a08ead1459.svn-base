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
public class QybhAdapter extends BaseListAdapter<CabinetReturn.ListBean> {

    public QybhAdapter(Context context, List<CabinetReturn.ListBean> list) {
        super(context, list);
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_lv_qubh, parent, false);
        }
        CabinetReturn.ListBean listBean = getList().get(position);
        TextView gwh = (TextView) convertView.findViewById(R.id.tv_gwh);
        TextView qy = (TextView) convertView.findViewById(R.id.tv_qybh);
        TextView tv_qty = (TextView) convertView.findViewById(R.id.tv_qty);
        TextView tv_oper_qty = (TextView) convertView.findViewById(R.id.tv_oper_qty);
        TextView tv_unoccupied_num = (TextView) convertView.findViewById(R.id.tv_unoccupied_num);

        gwh.setText(listBean.getGwbh());

        String qybh = listBean.getQybh();
        qy.setText(qybh.substring(qybh.length() - 2, qybh.length()));

        int bzcfsl = listBean.getBzcfsl();
        int sjcfsl = listBean.getSjcfsl();
        tv_qty.setText(String.valueOf(bzcfsl));
        tv_oper_qty.setText(String.valueOf(sjcfsl));
        tv_unoccupied_num.setText(String.valueOf(bzcfsl - sjcfsl));
        return convertView;
    }
}
