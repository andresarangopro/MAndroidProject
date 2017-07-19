package com.lide.app.ui.entry;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lide.app.R;
import com.lide.app.config.Configs;
import com.lide.app.ui.SearchActivity;
import com.lide.app.ui.inbound.ControlInBoundOrderActivity;
import com.lide.app.ui.FragmentBase;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Li De on 2016/9/22.
 */
public class StockManagementEntryFragment extends FragmentBase {

    @BindView(R.id.receive_goods_order)
    Button mReceiveGoodsOrder;

    @BindView(R.id.receive_goods_box)
    Button mReceiveGoodsBox;

    @BindView(R.id.download_goods_order)
    Button downloadGoodsOrder;

    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_stock_management_entry_layout, container, false);
        ButterKnife.bind(this, mView);
        initView();
        return mView;
    }


    private void initView() {
        //下载单据
        downloadGoodsOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("Flag", Configs.SearchInBoundOrderFragment);
                startAnimActivity(intent);
            }
        });

        //按单收货
        mReceiveGoodsOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ControlInBoundOrderActivity.class);
                intent.putExtra("management", "0");
                startAnimActivity(intent);
            }
        });

        //按箱收货
        mReceiveGoodsBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ControlInBoundOrderActivity.class);
                intent.putExtra("management", "1");
                startAnimActivity(intent);
            }
        });
    }


}
