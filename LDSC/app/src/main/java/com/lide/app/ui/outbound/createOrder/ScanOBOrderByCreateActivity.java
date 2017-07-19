package com.lide.app.ui.outbound.createOrder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.lide.app.R;
import com.lide.app.adapter.ViewPagerAdapter;
import com.lide.app.config.Configs;
import com.lide.app.persistence.util.Utils;
import com.lide.app.persistence.view.LazyViewPager;
import com.lide.app.persistence.view.NoScrollViewPager;
import com.lide.app.ui.BaseActivity;
import com.lide.app.ui.FragmentBase;
import com.lide.app.ui.inbound.LS.ErrorResultsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lubin on 2016/11/21.、
 * 本类是展示单明细和错误信息、操作单明细的载体
 */

public class ScanOBOrderByCreateActivity extends BaseActivity {

    @BindView(R.id.tv_common)
    TextView tvCommon;
    @BindView(R.id.tb_common)
    Toolbar tbCommon;
    @BindView(R.id.vp_common)
    NoScrollViewPager vpCommon;

    public List<FragmentBase> mFragments = new ArrayList<>();
    public ViewPagerAdapter viewPagerAdapter;
    public Long orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        ButterKnife.bind(this);
        initData();
        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Utils.getCurrentUser() != null)
            tvCommon.setText(Utils.getCurrentUser().getWarehouseCode());
        tbCommon.setTitle("");
    }

    private void initData() {
        Intent intent = getIntent();
        orderId = intent.getLongExtra(Configs.OutBoundOrderId, 0);
    }

    private void initViews() {
        setSupportActionBar(tbCommon);
        vpCommon.setOnPageChangeListener(new LazyViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                invalidateOptionsMenu();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tbCommon.setNavigationIcon(R.mipmap.back_login);
        tbCommon.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFinish();
            }
        });
        tbCommon.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mangenment_upload: //上传
                        ((DetailOutBoundOrderByCreateFragment) mFragments.get(0)).upload();
                        break;
                }
                return true;
            }
        });

        mFragments.add(new DetailOutBoundOrderByCreateFragment());
        mFragments.add(new ScanningEpcInOrderByCreateFragment());
        mFragments.add(new ScanningBarcodeInOrderByCreateFragment());
        mFragments.add(new ShowTagListFragment());
        mFragments.add(new ErrorResultsFragment());

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragments);
        vpCommon.setAdapter(viewPagerAdapter);
    }

    public void setViewEnabled(boolean enabled) {
        if (enabled) {
            tbCommon.setVisibility(View.VISIBLE);
        } else {
            tbCommon.setVisibility(View.INVISIBLE);
        }
    }

    public void onFinish() {
        if (vpCommon.getCurrentItem() - 1 >= 0) {
            if (vpCommon.getCurrentItem() == 3 || vpCommon.getCurrentItem() == 4) {
                vpCommon.setCurrentItem(1);
            } else {
                vpCommon.setCurrentItem(0);
            }
        } else {
            finish();
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (vpCommon.getCurrentItem() == 0) {
            menu.findItem(R.id.mangenment_upload).setVisible(true);
        } else {
            menu.findItem(R.id.mangenment_upload).setVisible(false);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onFinish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_particulars, menu);
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 139 || keyCode == 120 || keyCode == 138) {
            if (event.getRepeatCount() == 0) {
                switch (vpCommon.getCurrentItem()) {
                    case 1:
                        mFragments.get(1).readOrClose();
                        break;
                    case 2:
                        mFragments.get(2).startScan();
                        break;
                }
                return true;
            }
        }else if(keyCode == 4){
            if(((ScanningEpcInOrderByCreateFragment)mFragments.get(1)).scanState.getText().toString().equals("点击结束")){
                ShowToast("请先停止RFID扫描");
                return true;
            }
        }

        return super.onKeyDown(keyCode, event);
    }

    public void setCurrentView(int currentView) {
        vpCommon.setCurrentItem(currentView);
    }
}
