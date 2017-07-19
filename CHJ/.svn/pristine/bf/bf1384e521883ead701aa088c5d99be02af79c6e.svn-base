package com.lubin.chj.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lubin.chj.R;
import com.lubin.chj.bean.jsonToBean.QueryPcReturn;

import java.util.List;

/**
 * Created by lubin on 2016/9/22.
 */
public class QybhDetailsAdapter extends BaseListAdapter<QueryPcReturn.ListBean> {

    public QybhDetailsAdapter(Context context, List<QueryPcReturn.ListBean> list) {
        super(context, list);
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_lv_details_qubh, parent, false);
        }
        QueryPcReturn.ListBean listBean = getList().get(position);
        TextView gwh = (TextView) convertView.findViewById(R.id.tv_gwh);
        TextView qy = (TextView) convertView.findViewById(R.id.tv_qybh);
        TextView tv_qty = (TextView) convertView.findViewById(R.id.tv_qty);
        TextView tv_oper_qty = (TextView) convertView.findViewById(R.id.tv_oper_qty);
        TextView tv_unoccupied_num = (TextView) convertView.findViewById(R.id.tv_unoccupied_num);

        gwh.setText(listBean.getPch());

        String qybh = (String) listBean.getQybh();
        qy.setText(qybh.substring(qybh.length() - 2, qybh.length()));

        qy.setVisibility(View.GONE);
        tv_qty.setVisibility(View.GONE);
        tv_oper_qty.setVisibility(View.GONE);
        tv_unoccupied_num.setVisibility(View.GONE);
        return convertView;
    }
}
