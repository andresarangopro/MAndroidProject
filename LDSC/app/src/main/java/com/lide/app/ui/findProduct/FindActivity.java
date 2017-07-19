package com.lide.app.ui.findProduct;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
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
 * @author DaiJiCheng
 * @time 2016/8/2  15:27
 * @desc ${找货主体Activity}
 */
public class FindActivity extends BaseActivity {


    @BindView(R.id.tv_common)
    public TextView tvCommon;
    @BindView(R.id.tb_common)
    public Toolbar tbCommon;
    @BindView(R.id.vp_common)
    public NoScrollViewPager vpCommon;

    private String mActivityName;
    public String mBarcode;
    public String epc;
    public List<FragmentBase> fragments;
    public ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        mActivityName = intent.getStringExtra("Activity");
        mBarcode = intent.getStringExtra("Barcode");
        epc = intent.getStringExtra("epc");
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Utils.getCurrentUser() != null)
            tvCommon.setText(Utils.getCurrentUser().getWarehouseCode());
        tbCommon.setTitle("");
    }

    private void initData() {

        tbCommon.setNavigationIcon(R.mipmap.back_login);
        tbCommon.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vpCommon.getCurrentItem() == 1) {
                    if (mActivityName.equals("SearchFindProductFragment")) {
                        vpCommon.setCurrentItem(0);
                    } else
                        finish();
                } else {
                    finish();
                }
            }
        });

        fragments = new ArrayList<>();
        fragments.add(new FindSkuFragment());
        fragments.add(new FindEPCFragment());

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        vpCommon.setAdapter(viewPagerAdapter);
        vpCommon.setOffscreenPageLimit(0);
        if (mActivityName != null && mActivityName.equals("SearchFindProductFragment")) {
            vpCommon.setCurrentItem(0);
        } else {
            vpCommon.setCurrentItem(1);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 120 || keyCode == 138) {
            if (event.getRepeatCount() == 0) {
                fragments.get(vpCommon.getCurrentItem()).readOrClose();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
