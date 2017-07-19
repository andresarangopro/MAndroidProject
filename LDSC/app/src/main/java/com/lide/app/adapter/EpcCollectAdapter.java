package com.lide.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lide.app.R;
import com.lubin.bean.TakeStockEpcCollect;

import java.util.List;

/**
 * Created by lubin on 2016/8/15.
 */
public class EpcCollectAdapter extends BaseListAdapter<TakeStockEpcCollect> {

    public EpcCollectAdapter(Context context, List<TakeStockEpcCollect> list) {
        super(context, list);
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_epc_collect, parent, false);
        }
        TakeStockEpcCollect dataBean = getList().get(position);
        TextView num = ViewHolder.get(convertView, R.id.tv_item_epc_num);
        TextView epc = ViewHolder.get(convertView, R.id.tv_item_epc);
        TextView barcode = ViewHolder.get(convertView, R.id.tv_item_epc_barcode);
        barcode.setVisibility(View.GONE);

        num.setText(String.valueOf(position + 1));
        epc.setText(dataBean.getEpc());

        return convertView;
    }
}
