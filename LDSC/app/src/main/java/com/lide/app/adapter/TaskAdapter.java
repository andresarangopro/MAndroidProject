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
import com.lide.app.persistence.util.Utils;
import com.lide.app.persistence.view.SlidingButtonView;
import com.lubin.bean.TakeStockTask;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by daijicheng 2016/7/12.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> implements SlidingButtonView.IonSlidingButtonListener {

    private Context mContext;

    private IonSlidingViewClickListener mIDeleteBtnClickListener;

    private List<TakeStockTask> mDatas;
    private SlidingButtonView mMenu = null;

    //构造方法中设置数据

    public TaskAdapter(Context context, List<TakeStockTask> data, IonSlidingViewClickListener mIDeleteBtnClickListener) {
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
        TakeStockTask task = mDatas.get(position);
        SimpleDateFormat format = new SimpleDateFormat("MM-dd", Locale.CANADA);

        if (task.getComplete()) {
            holder.isComplete.setVisibility(View.VISIBLE);
        } else {
            holder.isComplete.setVisibility(View.INVISIBLE);
        }

        if (task.getTakeStockOrder() != null) {
            holder.orderCode.setText(task.getTakeStockOrder().getCode());
        } else {
            holder.orderCode.setText("");
        }
        switch (task.getMode()) {
            case 0:
                holder.taskMode.setText("RFID");
                break;
            case 1:
                holder.taskMode.setText("条码");
                break;
        }

        holder.time.setText(format.format(task.getDate()));
        holder.name.setText(task.getName());

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
                mIDeleteBtnClickListener.onDeleteBtnClick(v, n);
                closeMenu();//关闭菜单
            }
        });

        //djc
        holder.btn_Modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = holder.getLayoutPosition();

                mIDeleteBtnClickListener.onModifyBtnClick(v, n);
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
        public TextView orderCode;
        public TextView time;
        public LinearLayout ll_task;
        public ViewGroup layout_content;
        public ImageView isComplete;
        public TextView taskMode;

        public MyViewHolder(View itemView) {
            super(itemView);
            btn_Delete = (TextView) itemView.findViewById(R.id.tv_check_task_delete);
            btn_Modify = (TextView) itemView.findViewById(R.id.tv_check_task_modify);
            name = (TextView) itemView.findViewById(R.id.tv_check_task_name);
            orderCode = (TextView) itemView.findViewById(R.id.tv_check_task_order_code);
            time = (TextView) itemView.findViewById(R.id.tv_check_task_time);
            isComplete = (ImageView) itemView.findViewById(R.id.iv_check_task_updownload);
            ll_task = (LinearLayout) itemView.findViewById(R.id.ll_task);
            layout_content = (ViewGroup) itemView.findViewById(R.id.layout_content);
            taskMode = (TextView) itemView.findViewById(R.id.task_mode);
            ((SlidingButtonView) itemView).setSlidingButtonListener(TaskAdapter.this);
        }
    }

    public void refreshAllData(List<TakeStockTask> tasks) {
        mDatas.clear();
        mDatas.addAll(tasks);
        this.notifyDataSetChanged();
    }

    public TakeStockTask getCheckTask(int position) {
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

        void onDeleteBtnClick(View view, int position);

        void onModifyBtnClick(View view, int position);

    }
}