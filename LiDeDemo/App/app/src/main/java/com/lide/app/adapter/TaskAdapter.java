package com.lide.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lide.app.MApplication;
import com.lide.app.R;
import com.lide.app.util.Utils;
import com.lide.app.view.SlidingButtonView;

import java.util.List;

/**
 * Created by MJJ on 2015/7/25.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> implements SlidingButtonView.IonSlidingButtonListener {

    private Context mContext;

    private IonSlidingViewClickListener mIDeleteBtnClickListener;

    private List<String> mDatas;
    private SlidingButtonView mMenu = null;

    //构造方法中设置数据

    public TaskAdapter(Context context , List data,IonSlidingViewClickListener mIDeleteBtnClickListener) {
        this.mDatas = data;
        mContext = context;
        this.mIDeleteBtnClickListener = mIDeleteBtnClickListener;
        //djc
        //this.mModifyBtnClickListener =  mModifyBtnClickListener;

    }



    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.textView_up.setText(mDatas.get(position));
        holder.textView_down.setText("lala");
        //设置内容布局的宽为屏幕宽度
        holder.layout_content.getLayoutParams().width = Utils.getScreenWidth(MApplication.getInstance());
        //v1
        holder.ll_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否有删除菜单打开
                if (menuIsOpen()) {
                    closeMenu();//关闭菜单
                } else {
                    int n = holder.getLayoutPosition();
                    mIDeleteBtnClickListener.onItemClick(v, n);
                }

            }
        });
        holder.btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = holder.getLayoutPosition();
                mIDeleteBtnClickListener.onDeleteBtnCilck(v, n);
            }
        });

        //djc
        holder.btn_Modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = holder.getLayoutPosition();
             //   mModifyBtnClickListener.onModifyBtnCilck(v, n);
                mIDeleteBtnClickListener.onModifyBtnCilck(v, n);
            }
        });

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item, arg0,false);

        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }



    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView btn_Delete;
        //djc
        public TextView btn_Modify;
        public TextView textView_up;
        public TextView textView_down;
        public ViewGroup layout_content;
        public LinearLayout ll_task;
        public MyViewHolder(View itemView) {
            super(itemView);
            btn_Delete = (TextView) itemView.findViewById(R.id.tv_delete);
            btn_Modify = (TextView) itemView.findViewById(R.id.tv_modify);
            textView_up = (TextView) itemView.findViewById(R.id.text_up);
            textView_down = (TextView) itemView.findViewById(R.id.text_down);
            ll_task = (LinearLayout) itemView.findViewById(R.id.ll_task);
            layout_content = (ViewGroup) itemView.findViewById(R.id.layout_content);

            ((SlidingButtonView) itemView).setSlidingButtonListener(TaskAdapter.this);
        }
    }

    public void addData(int position,String task) {
        mDatas.add(position, task);
        notifyItemInserted(position);
    }

    public void removeData(int position){
        mDatas.remove(position);
        notifyItemRemoved(position);
    }
    //djc 修改
    public void modifyData(int position, String s){
        mDatas.set(position,s);
        //djc
        notifyItemChanged(position);
        //yuan ma
        // notifyItemRemoved(position);
    }


    /**
     * 删除菜单打开信息接收
     */
    @Override
    public void onMenuIsOpen(View view) {
        mMenu = (SlidingButtonView) view;
    }

    /**
     * 滑动或者点击了Item监听
     * @param slidingButtonView
     */
    @Override
    public void onDownOrMove(SlidingButtonView slidingButtonView) {
        if(menuIsOpen()){
            if(mMenu != slidingButtonView){
                closeMenu();
            }
        }
    }

    /**
     * 关闭菜单
     */
    public void closeMenu() {
        mMenu.closeMenu();
        mMenu = null;

    }
    /**
     * 判断是否有菜单打开
     */
    public Boolean menuIsOpen() {
        if(mMenu != null){
            return true;
        }
        Log.i("asd","mMenu为null");
        return false;
    }

    public interface IonSlidingViewClickListener {
        void onItemClick(View view, int position);
        void onDeleteBtnCilck(View view, int position);
        void onModifyBtnCilck(View view, int position);

    }
}