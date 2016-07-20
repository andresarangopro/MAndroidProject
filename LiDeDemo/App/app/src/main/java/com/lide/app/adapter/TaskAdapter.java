package com.lide.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lide.app.MApplication;
import com.lide.app.R;
import com.lide.app.util.Utils;
import com.lide.app.view.SlidingButtonView;
import com.lubin.bean.CheckTask;

import java.util.List;

/**
 * Created by daijicheng 2016/7/12.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> implements SlidingButtonView.IonSlidingButtonListener {

    private Context mContext;

    private IonSlidingViewClickListener mIDeleteBtnClickListener;

    private List<CheckTask> mDatas;
    private SlidingButtonView mMenu = null;

    //构造方法中设置数据

    public TaskAdapter(Context context, List<CheckTask> data, IonSlidingViewClickListener mIDeleteBtnClickListener) {
        this.mDatas = data;
        mContext = context;
        this.mIDeleteBtnClickListener = mIDeleteBtnClickListener;

    }


    @Override
    public int getItemCount() {
        if (mDatas != null) {
            return mDatas.size();
        } else {
            return 0;
        }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        CheckTask task = mDatas.get(position);

        holder.name.setText(task.getName());
        holder.warehouseNum.setText("Tx0001");
        if(task.getCompete()){

        }
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
                closeMenu();//关闭菜单
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

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_check_task, arg0, false);

        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView btn_Delete;
        public TextView btn_Modify;
        public TextView name;
        public TextView warehouseNum;
        public LinearLayout ll_task;
        public ViewGroup layout_content;
        public ImageView isComplete;

        public MyViewHolder(View itemView) {
            super(itemView);
            btn_Delete = (TextView) itemView.findViewById(R.id.tv_check_task_item_delete);
            btn_Modify = (TextView) itemView.findViewById(R.id.tv_check_task_item_modify);
            name = (TextView) itemView.findViewById(R.id.tv_check_task_item_name);
            warehouseNum = (TextView) itemView.findViewById(R.id.tv_check_task_item_warehouseNum);
            isComplete = (ImageView) itemView.findViewById(R.id.iv_check_task_item_updownload);
            ll_task = (LinearLayout) itemView.findViewById(R.id.ll_task);
            layout_content = (ViewGroup) itemView.findViewById(R.id.layout_content);
            ((SlidingButtonView) itemView).setSlidingButtonListener(TaskAdapter.this);
        }
    }

/*    public void addData(int position,String task) {
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
    }*/

    public void refreshAllData(List<CheckTask> tasks) {
        mDatas.clear();
        mDatas.addAll(tasks);
        this.notifyDataSetChanged();
    }

    public CheckTask getCheckTask(int position) {
        return mDatas.get(position);
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
     *
     * @param slidingButtonView
     */
    @Override
    public void onDownOrMove(SlidingButtonView slidingButtonView) {
        if (menuIsOpen()) {
            if (mMenu != slidingButtonView) {
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
        if (mMenu != null) {
            return true;
        }
        Log.i("asd", "mMenu为null");
        return false;
    }

    public interface IonSlidingViewClickListener {
        void onItemClick(View view, int position);

        void onDeleteBtnCilck(View view, int position);

        void onModifyBtnCilck(View view, int position);

    }
}