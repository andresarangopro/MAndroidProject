package com.lide.app.ui.inventory;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.lide.app.R;
import com.lide.app.adapter.ViewPagerAdapter;
import com.lide.app.persistence.util.Utils;
import com.lide.app.persistence.view.NoScrollViewPager;
import com.lide.app.ui.BaseActivity;
import com.lide.app.ui.FragmentBase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lubin on 2016/12/5.
 */

public class InventoryActivity extends BaseActivity {
    @BindView(R.id.tv_common)
    TextView tvCommon;
    @BindView(R.id.tb_common)
    Toolbar tbCommon;
    @BindView(R.id.vp_common)
    NoScrollViewPager vpCommon;

    public List<FragmentBase> mFragments = new ArrayList<>();
    public ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvCommon.setText("库存查询");
        if (Utils.getCurrentUser() != null)
            tvCommon.setText("库存查询(" + Utils.getCurrentUser().getWarehouseCode() + ")");
        tbCommon.setTitle(null);
    }

    private void initView() {
        setSupportActionBar(tbCommon);

        tbCommon.setNavigationIcon(R.mipmap.back_login);
        tbCommon.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mFragments.add(new InventoryFragment());
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragments);
        vpCommon.setAdapter(viewPagerAdapter);
    }

    public void setCurrentView(int currentView) {
        vpCommon.setCurrentItem(currentView);
    }
}
