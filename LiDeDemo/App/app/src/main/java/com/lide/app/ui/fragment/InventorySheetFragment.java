package com.lide.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lide.app.R;
import com.lide.app.adapter.InventorySheetAdapter;
import com.lide.app.bean.InventoryItem;
import com.lide.app.config.Configs;
import com.lide.app.ui.StockTakingActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author DaiJiCheng
 * @time 2016/7/20  9:23
 * @desc ${盘点单界面}
 */
public class InventorySheetFragment extends FragmentBase{
    @BindView(R.id.lv_inventory_sheet)
    ListView lv_inventory_sheet;
    private  View mView;
    private List<InventoryItem> mInventoryItemList;
    private InventorySheetAdapter mAdapter;
   private StockTakingActivity mActivity ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_inventory_sheet,container,false);
        ButterKnife.bind(this,mView);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        setAdapter();
        mActivity.setReadingText("盘点单列表");
    }

    private void initData() {
        mInventoryItemList = new ArrayList<>();
        mActivity = (StockTakingActivity) getActivity();
        for(int i = 0 ; i < 10 ; i++){
            mInventoryItemList.add(new InventoryItem("A-"+i,"text"+i));
        }

        lv_inventory_sheet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mActivity.showFragmet(Configs.UPOLAD_FRAGMENT);
            }
        });
    }

    private void setAdapter() {
        mAdapter = new InventorySheetAdapter(getActivity(),mInventoryItemList);
        lv_inventory_sheet.setAdapter(mAdapter);
    }


}
