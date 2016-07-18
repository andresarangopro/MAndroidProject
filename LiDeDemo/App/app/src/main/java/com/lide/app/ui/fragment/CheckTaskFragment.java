package com.lide.app.ui.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lide.app.R;
import com.lide.app.adapter.TaskAdapter;
import com.lide.app.bean.DBBean.CheckTask;
import com.lide.app.presenter.CheckTaskPresenterImple;
import com.lide.app.presenter.PInterface.ICheckTaskPresenter;
import com.lide.app.ui.VInterface.ICheckTaskView;
import com.lide.app.view.DividerItemDecoration;

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
        mView = inflater.inflate(R.layout.fragment_check_task, null);
        ButterKnife.bind(this, mView);

        return mView;
    }

    @Override
    public void onResume() {
        init();
        setAdapter();
        mPresenter.queryCheckTask("全部");
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
                Toast.makeText(mContext, "点击位置kkk：" + position, Toast.LENGTH_SHORT).show();
                Log.i(TAG, "点击项：" + position);
            }

            //点击删除
            @Override
            public void onDeleteBtnCilck(View view, int position) {
                Log.i(TAG, "deleteItemPosition：" + position);
                Toast.makeText(mContext, "删除项:" + position, Toast.LENGTH_SHORT).show();
                mAdapter.removeData(position);
            }

            //djc
            @Override
            public void onModifyBtnCilck(View view, int position) {
                Toast.makeText(mContext, "修改项:" + position, Toast.LENGTH_SHORT).show();
                showModifyDialog(position);
            }
        });
        mRvCheckTask.setAdapter(mAdapter);
        mRvCheckTask.setItemAnimator(new DefaultItemAnimator());
        mRvCheckTask.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
    }

    //点击修改任务
    public void showModifyDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        final AlertDialog dialog = builder.create();
        View view = View.inflate(mContext, R.layout.dialog_modify_task, null);
        dialog.setView(view, 0, 0, 0, 0);
        dialog.show();
        final EditText et_task_name = (EditText) view.findViewById(R.id.et_task_name);
        Button bt_submit = (Button) view.findViewById(R.id.bt_submit);
        Button bt_cancel = (Button) view.findViewById(R.id.bt_cancel);
        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取修改后的名称
                final String modify_name = et_task_name.getText().toString();
                Toast.makeText(mContext, position + modify_name, Toast.LENGTH_SHORT).show();
                mAdapter.modifyData(position, modify_name);

                dialog.dismiss();
            }
        });

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public void showResult(int result) {
        ShowToast(mContext.getText(result).toString());
    }

    @Override
    public void showCheckTaskList(List<CheckTask> tasks) {

    }

    @OnClick(R.id.btn_check_task_add)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_check_task_add:
                mPresenter.addCheckTask("test1");
                break;
        }
    }
}