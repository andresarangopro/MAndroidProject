package com.lubin.chj.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.lubin.chj.R;
import com.lubin.chj.bean.PcInfo;
import com.lubin.chj.service.ScanServiceWithUHF;
import com.lubin.chj.view.activity.Fragment.AdjustPickFragment;
import com.lubin.chj.view.activity.Fragment.AdjustStoreFragment;
import com.lubin.chj.view.activity.Fragment.FragmentBase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdjustActivity extends BaseActivity {
    @BindView(R.id.tb_common)
    Toolbar tbCommon;
    private List<FragmentBase> mFragments;

    private int current;
    List<PcInfo> pcInfos = new ArrayList<>();
    private String mFlag = "条码";
    public ScanServiceWithUHF mService = ScanServiceWithUHF.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_pick);
        ButterKnife.bind(this);
        initHeader();
        initFragment();
    }

    @Override
    protected void onResume() {
        tbCommon.setTitle("货品移柜管理");
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_common, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle().equals("条码")) {
            mFlag = "电子标签";
            item.setTitle("电子标签");
        } else {
            mFlag = "条码";
            item.setTitle("条码");
        }
        return super.onOptionsItemSelected(item);
    }

    private void initHeader() {
        setSupportActionBar(tbCommon);
        tbCommon.setNavigationIcon(R.mipmap.back);
        tbCommon.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragments.get(current).finishByBackIcon();
            }
        });
    }

    private void initFragment() {
        current = 0;
        mFragments = new ArrayList<>();
        mFragments.add(new AdjustPickFragment());
        mFragments.add(new AdjustStoreFragment());

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, mFragments.get(0), "0")
                .add(R.id.fragment_container, mFragments.get(1), "1")
                .show(mFragments.get(0))
                .hide(mFragments.get(1))
                .commit();
    }

    public void changeFragment(int index) {
        getSupportFragmentManager()
                .beginTransaction()
                .show(mFragments.get(index))
                .hide(mFragments.get(current))
                .commit();

        switch (index) {
            case 0:
                tbCommon.setTitle("拣货");
                mFragments.get(0).setBarcode(null);
                break;
            case 1:
                tbCommon.setTitle("存放");
                break;
        }
        current = index;

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getRepeatCount() == 0) {
            if (275 == keyCode) {
                if (mFlag.equals("电子标签"))
                    mService.inventory();
                else
                    mService.scanBarcode();
            }
        }
        return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (event.getRepeatCount() == 0) {
            if (275 == keyCode) {
                if (mFlag.equals("电子标签"))
                    mService.pause();
                else
                    mService.stopScan();
            }
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        mFragments.get(current).finishByBackBtn();
    }

    public void setPcInfo(List<PcInfo> pcInfos) {
        this.pcInfos.addAll(pcInfos);
    }

    public void clearPcInfo() {
        pcInfos.clear();
    }

    public List<PcInfo> getPcInfos() {
        return pcInfos;
    }


}
