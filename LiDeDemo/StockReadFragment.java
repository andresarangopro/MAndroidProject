package com.lide.app.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lide.app.R;
import com.lide.app.config.Configs;
import com.lide.app.ui.StockTakingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author DaiJiCheng
 * @time 2016/7/19  9:32
 * @desc ${扫描标签信息界面}
 */
public class StockReadFragment extends FragmentBase {

    @BindView(R.id.iv_stock_read_result)
    ImageView ivStockReadResult;
    @BindView(R.id.tv_stock_read_number)
    TextView tvStockReadNumber;
    @BindView(R.id.btn_read_open_close)
    Button btnReadOpenClose;

    private ScanReceiver scanReceiver;
    private StockTakingActivity mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stock_read, container, false);
        ButterKnife.bind(this, view);
        mContext = (StockTakingActivity) getActivity();
        initReceiver();
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        stopScan();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopScan();
        mContext.unregisterReceiver(scanReceiver);
    }

    //注册广播接受器
    private void initReceiver() {
        scanReceiver = new ScanReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Configs.RECEIVE_TAG);
        mContext.registerReceiver(scanReceiver, intentFilter);
    }

    @OnClick({R.id.tv_stock_read_number, R.id.btn_read_open_close})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_stock_read_number:
                //TODO
                break;
            case R.id.btn_read_open_close:
                read();
                break;
        }
    }

    //按钮失效
    private void setViewEnabled(boolean enabled) {
        ivStockReadResult.setEnabled(enabled);
        tvStockReadNumber.setEnabled(enabled);
        mContext.setViewEnabled(enabled);
    }

    //开始扫描
    private void read() {
        if (btnReadOpenClose.getText().equals(mContext.getString(R.string.btInventory))) {
            setViewEnabled(false);
            Intent intent = new Intent(Configs.START_SCAN);
            mContext.sendBroadcast(intent);
            btnReadOpenClose.setText(mContext.getString(R.string.title_stop_Inventory));
        } else {
            setViewEnabled(true);
            Intent intent = new Intent(Configs.STOP_SCAN);
            mContext.sendBroadcast(intent);
            btnReadOpenClose.setText(mContext.getString(R.string.btInventory));
        }
    }

    //停止扫描
    private void stopScan() {
        Intent intent = new Intent(Configs.STOP_SCAN);
        mContext.sendBroadcast(intent);
    }

    //广播接受器类
    class ScanReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (Configs.RECEIVE_TAG.equals(action)) {

            }
        }
    }
}
