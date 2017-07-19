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
 * Created by lubin on 2016/11/22.
 */

public class ScanBarcodeInOutBoundOrderAdapter extends BaseListAdapter<OutBoundDetail> {

    public ScanBarcodeInOutBoundOrderAdapter(Context context, List<OutBoundDetail> list) {
        super(context, list);
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_scan_sku, parent, false);
        }
        OutBoundDetail data = getList().get(position);
        LinearLayout mLinearLayout = (LinearLayout) convertView.findViewById(R.id.stock_read_sku);

        if (position == 0) {
            mLinearLayout.setBackgroundResource(R.color.shadow);
        }

        TextView stockSku = (TextView) convertView.findViewById(R.id.scan_sku);
        TextView targetNum = (TextView) convertView.findViewById(R.id.scan_target_num);
        TextView stockScanNum = (TextView) convertView.findViewById(R.id.scan_scan_num);
        stockSku.setText(data.getBarcode());
        targetNum.setText(String.valueOf(data.getQty()));
        stockScanNum.setText(String.valueOf(data.getOperateQty()));
        return convertView;
    }
}
