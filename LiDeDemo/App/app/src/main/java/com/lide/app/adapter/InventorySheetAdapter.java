package com.lide.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lide.app.R;
import com.lide.app.bean.InventoryItem;

import java.util.List;

/**
 * @author DaiJiCheng
 * @time 2016/7/20  10:11
 * @desc ${盘点单界面listview适配器}
 */
public class InventorySheetAdapter extends BaseAdapter {
    private Context mContext;
    private List<InventoryItem> mData;
    public InventorySheetAdapter (Context con , List<InventoryItem> items){
            this.mContext = con;
            this.mData = items;
    }
    @Override
    public int getCount() {
        if(mData != null){
            return mData.size();
        }else{
            return 0;
        }

    }

    @Override
    public Object getItem(int i) {
        return mData.get(i).getNum();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
            if(convertView == null){
                convertView = View.inflate(mContext,R.layout.item_list_inventory_sheet,null);
            }
            ViewHolder holder = ViewHolder.getHolder(convertView);
        holder.tv_num.setText(mData.get(i).getNum());
        holder.tv_des.setText(mData.get(i).getDes());
        return convertView;
    }

    static class ViewHolder {
        public TextView tv_num , tv_des;

        public ViewHolder(View convertView){
            tv_num = (TextView) convertView.findViewById(R.id.tv_num);
            tv_des = (TextView) convertView.findViewById(R.id.tv_des);
        }

        public static ViewHolder getHolder(View convertView){
           ViewHolder holder = (ViewHolder) convertView.getTag();
            if(holder == null){
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }
            return  holder;
        }
    }
}
