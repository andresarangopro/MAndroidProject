package com.lide.app.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lide.app.R;
import com.lubin.bean.TakeStockSkuCollect;

import java.util.List;

/**
 * Created by huangjianxionh on 2016/10/10.
 */

public class StockReadBySkuAdapter extends BaseListAdapter<TakeStockSkuCollect> {

    public StockReadBySkuAdapter(Activity context, List<TakeStockSkuCollect> list) {
        super(context, list);
    }

    @Override
    public View bindView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_stockread_sku, parent, false);
        }
        final TakeStockSkuCollect data = getList().get(position);
        TextView stockSku = (TextView) convertView.findViewById(R.id.stock_sku);
        TextView stockSkuNum = (TextView) convertView.findViewById(R.id.stock_target_num);
        Log.w("=======", "data.getBarcode():" + data.getBarcode() + ",data.getNum()" + data.getNum());
        stockSku.setText(data.getBarcode());
        stockSkuNum.setText(String.valueOf(data.getNum()));

        return convertView;
    }

}
