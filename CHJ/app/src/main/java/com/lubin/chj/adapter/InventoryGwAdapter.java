package com.lubin.chj.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lubin.chj.R;
import com.lubin.chj.bean.jsonToBean.QueryPcReturn;

import java.util.List;

/**
 * @author DaiJiCheng
 * @time 2016/9/28  13:08
 * @desc ${TODD}
 */
public class InventoryGwAdapter extends BaseListAdapter<QueryPcReturn.ListBean> {

    public InventoryGwAdapter(Context context, List<QueryPcReturn.ListBean> list) {
        super(context, list);
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_lv_inventory, parent, false);
        }
        QueryPcReturn.ListBean listBean = getList().get(position);
        TextView index = (TextView) convertView.findViewById(R.id.tv_index);
        TextView pch = (TextView) convertView.findViewById(R.id.textView_pch);
        TextView pdjg = (TextView) convertView.findViewById(R.id.textView_result);
        TextView zcgw = (TextView) convertView.findViewById(R.id.textView_zcgw);
        TextView qybh = (TextView) convertView.findViewById(R.id.textView_qybh);

        index.setText(String.valueOf(position + 1));
        pch.setText(listBean.getPch());
        if (listBean.getPdjg().toString().equals("")) {
            listBean.setPdjg("有账无实");
        }

        if (listBean.getPdjg().toString().equals("无账有实")) {
            pdjg.setTextColor(Color.RED);
        } else if (listBean.getPdjg().toString().equals("有账无实")) {
            pdjg.setTextColor(Color.BLACK);
        } else {
            pdjg.setTextColor(Color.BLUE);
        }

        pdjg.setText(listBean.getPdjg().toString());
        zcgw.setText(listBean.getGwbh().toString());
        String data = listBean.getQybh().toString();
        if (data.length() == 0) {
            qybh.setText("");
        } else {
            qybh.setText(data.substring(data.length() - 2, data.length()));
        }
        return convertView;
    }
}
