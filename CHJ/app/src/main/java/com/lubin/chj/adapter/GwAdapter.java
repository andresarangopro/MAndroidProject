package com.lubin.chj.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lubin.chj.R;
import com.lubin.chj.bean.Gw;

import java.util.List;

/**
 * Created by lubin on 2016/11/1.
 */

public class GwAdapter extends BaseListAdapter<Gw> {

    public GwAdapter(Context context, List<Gw> list) {
        super(context, list);
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_lv_gw, parent, false);
        }

        Gw gw = getList().get(position);
        TextView gwh = ViewHolder.get(convertView, R.id.tv_gwh);
        TextView status = ViewHolder.get(convertView, R.id.tv_status);

        gwh.setText(gw.getName());
        if (gw.isFinish())
            status.setText("空");
        else
            status.setText("");
        return convertView;
    }
}
