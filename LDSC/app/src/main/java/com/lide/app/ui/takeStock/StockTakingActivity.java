package com.lide.app.ui.takeStock;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.lide.app.R;
import com.lide.app.adapter.ViewPagerAdapter;
import com.lide.app.config.Configs;
import com.lide.app.presenter.PInterface.ITakeStockOrderPresenter;
import com.lide.app.presenter.takeStock.TakeStockPresenterImpl;
import com.lide.app.ui.BaseActivity;
import com.lide.app.ui.FragmentBase;
import com.lide.app.ui.LoginActivity;
import com.lide.app.ui.VInterface.IShowDataView;
import com.lide.app.ui.VInterface.ITakeStockView;
import com.lide.app.persistence.util.Utils;
import com.lide.app.persistence.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author DaiJiCheng
 * @time 2016/7/19  9:10
 * @desc ${盘点控制}
 */
public class StockTakingActivity extends BaseActivity implements ITakeStockView {


    @BindView(R.id.tv_common)
    TextView tvCommon;
    @BindView(R.id.tb_common)
    Toolbar tbCommon;
    @BindView(R.id.vp_common)
    NoScrollViewPager vpCommon;

    private ViewPagerAdapter mAdapter;
    private List<FragmentBase> mFragments;

    private static final int READ_FRAGMENT = 0;
    private static final int ORDER_FRAGMENT = 1;
    private Intent target;

    public static int LOGIN = 1;
    public static int DIFF = 3;
    private TakeStockPresenterImpl mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        mPresenter = new TakeStockPresenterImpl(this);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        tbCommon.setTitle("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_takestock, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LOGIN) {
            if (RESULT_OK == resultCode) {
                startNewActivity(null);
            }
        }
    }

    private void initView() {
        setSupportActionBar(tbCommon);
        tbCommon.setNavigationIcon(R.mipmap.back_login);
        tbCommon.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vpCommon.getCurrentItem() - 1 >= 0) {
                    vpCommon.setCurrentItem(vpCommon.getCurrentItem() - 1);
                } else {
                    finish();
                }
            }
        });

        tbCommon.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_upload:
                        ((StockReadFragment) mFragments.get(0)).showDialog();
                        break;
                }
                return true;
            }
        });
    }

    private void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(new StockReadFragment());
        mFragments.add(new TakeStockOrderFragment());
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragments);
        vpCommon.setAdapter(mAdapter);
        vpCommon.setOffscreenPageLimit(2);
    }


    //界面切换
    public void showFragment(int FRAGMENT) {
        switch (FRAGMENT) {
            case READ_FRAGMENT:
                vpCommon.setCurrentItem(Configs.READ_FRAGMENT);
                setTitleText("采集界面");
                break;
            case ORDER_FRAGMENT:
                vpCommon.setCurrentItem(Configs.ORDER_FRAGMENT);
                mPresenter.getTakeStockOrders();
                setTitleText("盘点单");
                break;
        }
    }

    //根据任务欠缺的条件，跳转界面
    public void startNewActivity(Intent intent) {
        if (intent != null) {
            this.target = intent;
        }
        if (Utils.getCurrentUser() != null) {
            //已经登录;
            //判断当前任务是否已经绑定盘点单
            if (Utils.getCurrentTask().getOrderId() != null) {
                if (target.getStringExtra("tag").equals("upload")) {
                    startAnimActivity(target);
                } else {
                    startActivityForResult(target, DIFF);
                }
            } else {
                showFragment(ORDER_FRAGMENT);
            }
        } else {
            //未登录
            Intent login = new Intent(StockTakingActivity.this, LoginActivity.class);
            login.putExtra("isAtNet", true);
            startActivityForResult(login, LOGIN);
        }
    }

    //设置头部文本内容
    public void setTitleText(String text) {
        tvCommon.setText(text);
    }

    //按钮失效
    public void setViewEnabled(boolean enabled) {
        if (enabled) {
            tbCommon.setVisibility(View.VISIBLE);
        } else {
            tbCommon.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        if (firstPress + 2000 > System.currentTimeMillis()) {
            //2秒钟内连续按下"back"键
            super.onBackPressed();
            finish();
        } else {
            ShowToast("再按一次退出");
            firstPress = System.currentTimeMillis();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 139 || keyCode == 120 || keyCode == 138) {
            if (event.getRepeatCount() == 0) {
                StockReadFragment mFragments = (StockReadFragment) this.mFragments.get(0);
                mFragments.read();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public ITakeStockOrderPresenter getOrderPresenter() {
        return mPresenter;
    }
    //有P传来的值，分发到fragment中
    @Override
    public <T> void ShowData(T t) {
        ((IShowDataView) mFragments.get(1)).ShowData(t);
    }
}