package com.lide.app.ui.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.lide.app.R;
import com.lide.app.adapter.TaskAdapter;
import com.lide.app.config.Configs;
import com.lide.app.presenter.CheckTaskPresenterImple;
import com.lide.app.presenter.PInterface.ICheckTaskPresenter;
import com.lide.app.ui.StockTakingActivity;
import com.lide.app.ui.VInterface.ICheckTaskView;
import com.lide.app.view.DividerItemDecoration;
import com.lubin.bean.CheckTask;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CheckTaskFragment extends FragmentBase implements ICheckTaskView {

    @BindView(R.id.rv_check_task)
    RecyclerView mRvCheckTask;
    private View mView;
    private Context mContext;
    private List<CheckTask> mDatas;
    private TaskAdapter mAdapter;
    private ICheckTaskPresenter mPresenter;
    private final String TAG = "test";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_check_task, container, false);
        ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        init();
        setAdapter();
       // mPresenter.queryCheckTask("全部");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        mPresenter.getAllCheckTask();
        super.onResume();
    }

    private void init() {
        mContext = getActivity();
        mDatas = new ArrayList<>();
        mPresenter = new CheckTaskPresenterImple(this);
    }

    private void setAdapter() {
        mRvCheckTask.setLayoutManager(new LinearLayoutManager(mContext));
        //mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mAdapter = new TaskAdapter(mContext, mDatas, new TaskAdapter.IonSlidingViewClickListener() {

            //点击进入任务详情
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), StockTakingActivity.class);
                intent.putExtra(Configs.CheckTask,mAdapter.getCheckTask(position));
                startAnimActivity(intent);
            }

            //点击删除
            @Override
            public void onDeleteBtnCilck(View view, int position) {
                mPresenter.deleteCheckTask(mAdapter.getCheckTask(position).getName());
            }

            //djc
            @Override
            public void onModifyBtnCilck(View view, int position) {
                showModifyDialog(position);
            }
        });
        mRvCheckTask.setAdapter(mAdapter);
        mRvCheckTask.setItemAnimator(new DefaultItemAnimator());
        mRvCheckTask.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
    }

    //点击修改任务


    @Override
    public void showResult(int result) {
        ShowToast(mContext.getText(result).toString());
    }

    @Override
    public void showCheckTaskList(List<CheckTask> tasks) {
        mAdapter.refreshAllData(tasks);
    }

    @OnClick(R.id.btn_check_task_add)
    public void onClick(View view) {
        switch (view.getId()) {
            //点击添加任务按钮
            case R.id.btn_check_task_add:
                showAddDialog();
                break;
        }
    }

    //添加任务对话框
    private void showAddDialog() {

        View view = View.inflate(mContext, R.layout.dialog_add_task, null);

        final Dialog dialog = new Dialog(getActivity(), R.style.selectorDialog);
        dialog.setContentView(view);
        dialog.show();
        final EditText et_update_taskname = (EditText) view.findViewById(R.id.et_update_taskname);
        Button bt_submit = (Button) view.findViewById(R.id.bt_submit);
        Button bt_cancel = (Button) view.findViewById(R.id.bt_cancel);
        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String addName = et_update_taskname.getText().toString();
                mPresenter.addCheckTask(addName);
                dialog.dismiss();
            }
        });

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        WindowManager windowManager = getActivity().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = (int)(450); //设置宽度
        dialog.getWindow().setAttributes(lp);
    }

    public void showModifyDialog(final int position) {

        View view = View.inflate(mContext, R.layout.dialog_modify_task, null);

        final Dialog dialog = new Dialog(getActivity(), R.style.selectorDialog);
        dialog.setContentView(view);
        dialog.show();
        final EditText et_update_taskname = (EditText) view.findViewById(R.id.et_update_taskname);
        Button bt_submit = (Button) view.findViewById(R.id.bt_submit);
        Button bt_cancel = (Button) view.findViewById(R.id.bt_cancel);
        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取修改后的名称
                final String newName = et_update_taskname.getText().toString();
                String InvalidName = mAdapter.getCheckTask(position).getName();
                mPresenter.updateCheckTaskName(InvalidName, newName);
                mAdapter.closeMenu();

                dialog.dismiss();
            }
        });

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        WindowManager windowManager = getActivity().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = (int)(450); //设置宽度
        dialog.getWindow().setAttributes(lp);
    }
}
