package com.lide.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lide.app.R;
import com.lubin.bean.OutBoundDetail;

import java.util.List;

/**
 * Created by lubin on 2016/11/21.
 */

public class DetailOutBoundOrderAdapter extends BaseListAdapter<OutBoundDetail> {


    public DetailOutBoundOrderAdapter(Context context, List<OutBoundDetail> list) {
        super(context, list);
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_detail_of_out_bound_order, parent, false);
        }

        OutBoundDetail outBoundDetail = getList().get(position);

        TextView skuIndex = (TextView) ViewHolder.get(convertView, R.id.tv_sku_index);
        TextView barcode = (TextView) ViewHolder.get(convertView, R.id.tv_barcode);
        TextView skuName = (TextView) ViewHolder.get(convertView, R.id.tv_sku_name);
        TextView qty = (TextView) ViewHolder.get(convertView, R.id.tv_sku_qty);
        TextView operateQty = (TextView) ViewHolder.get(convertView, R.id.tv_sku_operate_qty);
        View shadow = ViewHolder.get(convertView, R.id.view_shadow);
        LinearLayout layout = (LinearLayout) ViewHolder.get(convertView, R.id.ll_detail_sku);


        skuIndex.setText(String.valueOf(position + 1));
        barcode.setText(outBoundDetail.getBarcode());
        skuName.setText(outBoundDetail.getSkuName());
        qty.setText(String.valueOf(outBoundDetail.getQty()));
        operateQty.setText(String.valueOf(outBoundDetail.getOperateQty()));

        if (outBoundDetail.getQty() == outBoundDetail.getOperateQty()) {
            layout.setBackgroundColor(mContext.getResources().getColor(R.color.success_text));
            shadow.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            operateQty.setTextColor(mContext.getResources().getColor(R.color.goods_delivery_title_bg));
        } else {
            layout.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            shadow.setBackgroundColor(mContext.getResources().getColor(R.color.shadow));
            operateQty.setTextColor(mContext.getResources().getColor(R.color.goods_del_bg));
        }

        return convertView;
    }
}
