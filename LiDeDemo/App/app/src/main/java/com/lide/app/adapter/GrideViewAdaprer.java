package com.lide.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lide.app.R;

import java.util.List;

/**
 * @author DaiJiCheng
 * @time 2016/7/18  11:09
 * @desc ${主功能界面中,填充grideVeiw}
 */
public class GrideViewAdaprer extends BaseAdapter {

    private Context mContext;
    private List<String> mTitleStrs;
    private List<Integer> mDrawableIds;

    public GrideViewAdaprer(Context context, List<String> channelTitle, List<Integer> channelIcon) {
        this.mContext = context;
        this.mTitleStrs = channelTitle;
        this.mDrawableIds = channelIcon;

    }

    @Override
    public int getCount() {
        //条目的总数	文字组数 == 图片张数
        return mTitleStrs.size();
    }

    @Override
    public Object getItem(int position) {
        return mTitleStrs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=View.inflate(mContext, R.layout.gridview_item, null);
        }
        GrideViewHolder holder = GrideViewHolder.getHolder(convertView);

        holder.tv_title.setText(mTitleStrs.get(position));
        holder.iv_icon.setBackgroundResource(mDrawableIds.get(position));
        return convertView;
    }


    static class GrideViewHolder {

        public TextView tv_title;
        public ImageView iv_icon;

        public GrideViewHolder (View convertView) {
            tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
        }
        public static GrideViewHolder getHolder(View convertView){
            GrideViewHolder holder = (GrideViewHolder) convertView.getTag();
            if(holder==null){
                holder = new GrideViewHolder(convertView);
                convertView.setTag(holder);
            }
            return holder;
        }
    }
}

