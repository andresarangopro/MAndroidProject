package com.lide.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.lide.app.R;
import com.lide.app.adapter.GrideViewAdaprer;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author DaiJiCheng
 * @time 2016/7/18  13:08
 * @desc ${TODD}
 */
public class ChannelFragment extends FragmentBase {
    @BindView(R.id.gv_channel)
    GridView mGridView;
    GrideViewAdaprer mAdapter ;
    private ArrayList<String> mTitleStrs;
    private ArrayList<Integer> mDrawableIds;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_channel,container,false);
        ButterKnife.bind(this,root);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initView();
       getActivity().setTitle("功能列表");
    }
    private void initView() {
        mAdapter = new GrideViewAdaprer(getActivity(),mTitleStrs,mDrawableIds);
        mGridView.setAdapter(mAdapter);
    }

    private void initData() {
        //准备数据(文字(组),图片(张))
        mTitleStrs = new ArrayList<>();
        mTitleStrs.add("盘点");
        mTitleStrs.add("找货");
        mTitleStrs.add("登录");
        mTitleStrs.add("盘点2");
        mTitleStrs.add("找货2");
        mTitleStrs.add("登录2");
        mTitleStrs.add("盘点3");
        mTitleStrs.add("找货3");
        mTitleStrs.add("登录3");
        mDrawableIds = new ArrayList<>();
        mDrawableIds.add(R.drawable.a);
        mDrawableIds.add(R.drawable.b);
        mDrawableIds.add(R.drawable.c);
        mDrawableIds.add(R.drawable.a);
        mDrawableIds.add(R.drawable.b);
        mDrawableIds.add(R.drawable.c);
        mDrawableIds.add(R.drawable.a);
        mDrawableIds.add(R.drawable.b);
        mDrawableIds.add(R.drawable.c);

    }

    @Override
    public void onDestroyView() {
       getActivity().setTitle("");
        super.onDestroyView();
    }
}