package com.lide.app.ui.takeStock;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.lide.app.R;
import com.lide.app.adapter.ViewPagerAdapter;
import com.lide.app.config.Configs;
import com.lide.app.persistence.view.LazyViewPager;
import com.lide.app.persistence.view.NoScrollViewPager;
import com.lide.app.ui.BaseActivity;
import com.lide.app.ui.FragmentBase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author DaiJiCheng
 * @time 2016/7/21  9:06
 * @desc ${查询Activity}
 */
public class QueryDiffActivity extends BaseActivity {

    @BindView(R.id.tv_query_diff_title)
    TextView tbQueryDiffTitle;
    @BindView(R.id.tb_query_diff)
    Toolbar tbQueryDiff;
    @BindView(R.id.vp_query_diff)
    public NoScrollViewPager vpQueryDiff;

    public List<FragmentBase> mFragments;
    private ViewPagerAdapter mAdapter;

    public String productCode;
    public String barcode;

    public String currentProductType;
    public String currentSkuType;

    private static final int STYLE_FRAGMENT = 0;
    private static final int BAR_CODE_FRAGMENT = 1;
    private static final int EPC_FRAGMENT = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_diff);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        tbQueryDiff.setTitle("");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_diff, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (vpQueryDiff.getCurrentItem() != 2) {
            menu.findItem(R.id.menu_diff).setVisible(true);
            menu.findItem(R.id.action_all).setVisible(true);
            menu.findItem(R.id.action_diff).setVisible(true);
            menu.findItem(R.id.action_no_diff).setVisible(true);
        } else {
            menu.findItem(R.id.menu_diff).setVisible(false);
            menu.findItem(R.id.action_all).setVisible(false);
            menu.findItem(R.id.action_diff).setVisible(false);
            menu.findItem(R.id.action_no_diff).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }


    private void initData() {
        currentProductType = "ALL";
        currentSkuType = "ALL";
        mFragments = new ArrayList<>();
    }

    private void initView() {

        mFragments.add(new QueryStyleFragment());
        mFragments.add(new QueryBarCodeFragment());
        mFragments.add(new QueryEpcFragment());

        mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragments);
        vpQueryDiff.setAdapter(mAdapter);
        vpQueryDiff.setOffscreenPageLimit(3);
        vpQueryDiff.setOnPageChangeListener(new LazyViewPager.OnPageChangeListener() {
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

        setSupportActionBar(tbQueryDiff);
        tbQueryDiff.setNavigationIcon(R.mipmap.back_login);
        tbQueryDiff.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int followItem = vpQueryDiff.getCurrentItem() - 1;
                if (followItem >= 0) {
                    showFragment(followItem);
                } else {
                    finish();
                }
            }
        });

        tbQueryDiff.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_all:
                        queryDiff(item);
                        break;
                    case R.id.action_diff:
                        queryDiff(item);
                        break;
                    case R.id.action_no_diff:
                        queryDiff(item);
                        break;
                    case R.id.diff_search:
                        mFragments.get(vpQueryDiff.getCurrentItem()).readOrClose();
                        break;
                }
                return true;
            }

        });

    }

    public void queryDiff(MenuItem item) {
        String type = null;
        if (item != null) {
            type = item.getTitleCondensed().toString();
        } else {
            return;
        }
        mFragments.get(vpQueryDiff.getCurrentItem()).showDataByType(type);
    }

    //界面切换
    public void showFragment(int FRAGMENT) {
        switch (FRAGMENT) {
            case STYLE_FRAGMENT:
                vpQueryDiff.setCurrentItem(Configs.STYLE_FRAGMENT);
                setText(currentProductType);
                break;
            case BAR_CODE_FRAGMENT:
                vpQueryDiff.setCurrentItem(Configs.BAR_CODE_FRAGMENT);
                setText(currentSkuType);
                break;
            case EPC_FRAGMENT:
                vpQueryDiff.setCurrentItem(Configs.EPC_FRAGMENT);
                break;
        }
    }

    //设置文本字体
    public void setText(String titleText) {
        if (titleText.equals("ALL")) {
            tbQueryDiffTitle.setText("全部");
        } else if (titleText.equals("DIFF")) {
            tbQueryDiffTitle.setText("有差异");
        } else if (titleText.equals("UN_DIFF")) {
            tbQueryDiffTitle.setText("无差异");
        } else {
            tbQueryDiffTitle.setText(titleText);
        }
    }

}
