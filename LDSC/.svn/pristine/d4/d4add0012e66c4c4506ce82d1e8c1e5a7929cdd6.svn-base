package com.lide.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lide.app.R;

import java.util.List;

/**
 * @author DaiJiCheng
 * @time 2016/7/20  10:11
 * @desc ${盘点单界面listview适配器}
 */
public class SearchFindProductAdapter extends BaseListAdapter<String> {

    public SearchFindProductAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_list_item, parent, false);
        }

        String bean = getList().get(position);
        TextView code = ViewHolder.get(convertView, R.id.tv_item_name);
        code.setText(bean);

        return convertView;
    }


}
