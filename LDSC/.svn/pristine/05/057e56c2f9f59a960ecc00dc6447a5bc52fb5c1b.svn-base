package com.lide.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lide.app.R;
import com.lide.app.adapter.ViewPagerAdapter;
import com.lide.app.persistence.util.Utils;
import com.lide.app.persistence.view.LazyViewPager;
import com.lide.app.persistence.view.NoScrollViewPager;
import com.lide.app.ui.entry.BindEnterFragment;
import com.lide.app.ui.entry.CheckTaskFragment;
import com.lide.app.ui.entry.FindProductEnterFragment;
import com.lide.app.ui.entry.OutBoundEnterFragment;
import com.lide.app.ui.entry.StockManagementEntryFragment;
import com.lide.app.ui.inbound.InboundTransaction;
import com.lide.app.ui.outbound.createOrder.OutboundTransaction;
import com.lide.app.ui.takeStock.TakeStockTransaction;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tb_main)
    Toolbar tbMain;
    @BindView(R.id.fab_main)
    FloatingActionButton fabMain;
    @BindView(R.id.lf_main)
    NavigationView lfMain;
    @BindView(R.id.dl_main)
    DrawerLayout dlMain;
    @BindView(R.id.vp_main)
    NoScrollViewPager vpMain;
    @BindView(R.id.et_check_task_name)
    EditText mEtCheckTaskName;
    @BindView(R.id.tv_tb)
    TextView tvTb;


    private List<FragmentBase> mfragments;
    private ViewPagerAdapter viewPagerAdapter;

    private String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        InboundTransaction.init(this);
        TakeStockTransaction.init(this);
        OutboundTransaction.init(this);
        item = getIntent().getStringExtra("item");

        initViews();
        intiEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTitleName();
    }

    String[] titles = {"收货", "发货", "盘点", "绑定", "找货"};

    private void setTitleName() {
        String title = titles[Integer.parseInt(item)];
        tbMain.setTitle(null);
        tvTb.setText(title);
        if (Utils.getCurrentUser() != null) {
            tvTb.setText(title + "(" + Utils.getCurrentUser().getWarehouseCode() + ")");
        }
    }

    private void initViews() {
        setSupportActionBar(tbMain);
        tbMain.setNavigationIcon(R.mipmap.back_login);
        tbMain.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mfragments = new ArrayList<>();
        mfragments.add(new StockManagementEntryFragment());         //收货界面
        mfragments.add(new OutBoundEnterFragment());                //发货界面
        mfragments.add(new CheckTaskFragment());                    //盘点界面
        mfragments.add(new BindEnterFragment());                    //绑定界面
        mfragments.add(new FindProductEnterFragment());             //找货界面

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mfragments);
        vpMain.setAdapter(viewPagerAdapter);


        vpMain.setOnPageChangeListener(new LazyViewPager.OnPageChangeListener() {
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

        tbMain.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_search:
                        showEtSearch();
                        break;
                    case R.id.action_setting:
                        startAnimActivity(new Intent(MainActivity.this, SettingActivity.class));
                        break;
                }
                return true;
            }
        });
    }

    public void showEtSearch() {
        int visibility = mEtCheckTaskName.getVisibility();
        switch (visibility) {
            case 0:
                mEtCheckTaskName.setVisibility(View.GONE);
                break;
            case 8:
                mEtCheckTaskName.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void intiEvent() {
        vpMain.setCurrentItem(Integer.parseInt(item));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
//        if (vpMain.getCurrentItem() == 0) {
//            menu.findItem(R.id.action_search).setVisible(true);
//        } else {
//            menu.findItem(R.id.action_search).setVisible(false);
//        }
        menu.findItem(R.id.action_search).setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }
}
