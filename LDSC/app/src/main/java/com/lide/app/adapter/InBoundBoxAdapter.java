package com.lide.app.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lide.app.R;
import com.lubin.bean.InBoundCase;

import java.util.List;

/**
 * Created by huangjianxionh on 2016/10/10.
 */

public class InBoundBoxAdapter extends BaseListAdapter<InBoundCase> {

    private Activity context;

    public InBoundBoxAdapter(Activity context, List<InBoundCase> list) {
        super(context, list);
        this.context = context;
    }

    @Override
    public View bindView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.receive_box_list, parent, false);
        }
        final InBoundCase data = getList().get(position);

        TextView name = (TextView) convertView.findViewById(R.id.name);
        name.setText(data.getCode());
        TextView num = (TextView) convertView.findViewById(R.id.num);
        num.setText("共 " + data.getQty() + " 件  " + status(data.getStatus()));
        return convertView;
    }

    public String status(int flag) {
        String name = null;
        switch (flag) {
            case 0:
                name = "未处理";
                break;
            case 1:
                name = "处理中";
                break;
            case 2:
                name = "已完成";
                break;
        }
        return name;
    }

}
