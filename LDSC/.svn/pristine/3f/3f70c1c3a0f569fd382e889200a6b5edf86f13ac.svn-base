package com.lide.app.ui;

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
import com.lide.app.persistence.view.NoScrollViewPager;
import com.lide.app.service.IScanService;
import com.lide.app.service.ScanServiceControl;
import com.lide.app.ui.findProduct.SearchFindProductFragment;
import com.lide.app.ui.inbound.SearchIBOrdersFragment;
import com.lide.app.ui.outbound.createOrder.SearchWarehousesFragment;
import com.lide.app.ui.outbound.ur.SearchOutOrderFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huangjianxionh on 2016/10/11.
 */
//搜索界面
public class SearchActivity extends BaseActivity {


    @BindView(R.id.tv_common)
    TextView tvCommon;
    @BindView(R.id.tb_common)
    Toolbar tbCommon;
    @BindView(R.id.vp_common)
    NoScrollViewPager vpCommon;

    private List<FragmentBase> fragments;
    private ViewPagerAdapter viewPagerAdapter;
    private IScanService scanService = ScanServiceControl.getScanService();
    private String flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        Intent intent = getIntent();
        flag = intent.getStringExtra("Flag");
        ButterKnife.bind(this);

        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        tbCommon.setTitle("");
    }

    @Override
    protected void onPause() {
        super.onPause();
        scanService.stopScanBarcode();
    }

    public void init() {

        fragments = new ArrayList<>();
        fragments.add(new SearchIBOrdersFragment());//订单搜索
        fragments.add(new SearchFindProductFragment());//找货搜索
        fragments.add(new SearchWarehousesFragment());//找仓库搜索
        fragments.add(new SearchOutOrderFragment());//找仓库搜索

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        vpCommon.setAdapter(viewPagerAdapter);

        tvCommon.setText("搜索");
        setSupportActionBar(tbCommon);
        tbCommon.setNavigationIcon(R.mipmap.back_login);
        tbCommon.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tbCommon.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_setting:
                        startAnimActivity(new Intent(SearchActivity.this, SettingActivity.class));
                        break;
                }
                return true;
            }
        });

        if (flag != null) {
            if (flag.equals(Configs.SearchInBoundOrderFragment)) {
                vpCommon.setCurrentItem(0);
            } else if (flag.equals(Configs.SearchFindProductFragment)) {
                vpCommon.setCurrentItem(1);
            } else if (flag.equals(Configs.SearchWarehousesFragment)) {
                vpCommon.setCurrentItem(2);
            }else if(flag.equals(Configs.SearchOutBoundOrderFragment)){
                vpCommon.setCurrentItem(3);
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 139) {
            if (event.getRepeatCount() == 0) {
                scanService.scanBarcode();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void setData(String data) {
        fragments.get(vpCommon.getCurrentItem()).setBarcode(data);
    }
}
