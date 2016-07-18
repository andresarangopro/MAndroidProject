package com.lide.app.ui.fragment;

import android.app.AlertDialog;
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
import com.lide.app.view.DividerItemDecoration;

import java.util.ArrayList;

public class TaskFragment extends FragmentBase {

    private RecyclerView mRecyclerView;
    private ArrayList<String> mDatas = new ArrayList<>();
    private TaskAdapter mAdapter;
    private final String TAG = "test";
    private Button mBt_add;
    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_check_task,null);
        return mView;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initView();
        setAdapter();
        getActivity().setTitle("盘点任务");
    }

    private void initData() {
        for (int i = 0; i < 30; i++) {
            mDatas.add("task:"+i);
        }
    }

    private void initView(){
        mRecyclerView = (RecyclerView)mView.findViewById(R.id.rv_check_task);
        mBt_add = (Button)mView.findViewById(R.id.btn_check_task_add);
        //点击添加按钮新的任务(实际弹出对话框输入输入数据)
        mBt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用添加方法
                mAdapter.addData(0,"task_new");
            }
        });
    }

    private void setAdapter(){

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mAdapter = new TaskAdapter(getActivity(), mDatas, new TaskAdapter.IonSlidingViewClickListener() {

            //点击进入任务详情
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(),"点击位置kkk："+position,Toast.LENGTH_SHORT).show();
                Log.i(TAG,"点击项："+position);
            }
            //点击删除
            @Override
            public void onDeleteBtnCilck(View view, int position) {
                Log.i(TAG,"deleteItemPosition："+position);
                Toast.makeText(getActivity(),"删除项:"+position,Toast.LENGTH_SHORT).show();
                mAdapter.removeData(position);
            }
            //djc
            @Override
            public void onModifyBtnCilck(View view, int position) {
                Toast.makeText(getActivity(),"修改项:"+position,Toast.LENGTH_SHORT).show();
                showModifyDialog(position);

            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST));

    }


    //点击修改任务
    public void showModifyDialog(final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final AlertDialog dialog = builder.create();
        View view = View.inflate(getActivity(), R.layout.dialog_modify_task, null);
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
                Toast.makeText(getActivity(),position+modify_name,Toast.LENGTH_SHORT).show();
                mAdapter.modifyData(position,modify_name);

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

}
