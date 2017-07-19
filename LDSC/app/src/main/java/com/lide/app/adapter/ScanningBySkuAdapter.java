package com.lide.app.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lide.app.R;
import com.lubin.bean.InBoundDetail;

import java.util.List;

/**
 * Created by huangjianxionh on 2016/10/20.
 */

public class ScanningBySkuAdapter extends BaseListAdapter<InBoundDetail> {


    public ScanningBySkuAdapter(Activity context, List<InBoundDetail> list) {
        super(context, list);
    }

    @Override
    public View bindView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_scan_sku, parent, false);
        }
        final InBoundDetail data = getList().get(position);
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
