package com.lide.app.ui.outbound.ur;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.lide.app.R;
import com.lide.app.adapter.ViewPagerAdapter;
import com.lide.app.persistence.view.NoScrollViewPager;
import com.lide.app.ui.BaseActivity;
import com.lide.app.ui.FragmentBase;
import com.lide.app.ui.outbound.createOrder.OutboundTransaction;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HKR on 2017/3/7.
 */

public class OutBoundOrderActivity extends BaseActivity {
    @BindView(R.id.tv_common)
    TextView tvCommon;
    @BindView(R.id.tb_common)
    Toolbar tbCommon;
    @BindView(R.id.vp_common)
    NoScrollViewPager vpCommon;

    public List<FragmentBase> mFragments;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        ButterKnife.bind(this);
        OutboundTransaction.init(this);
        initView();
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

        mFragments = new ArrayList<>();
        mFragments.add(new OutBoundOrderFragment());

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragments);
        vpCommon.setAdapter(viewPagerAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        tvCommon.setText("按单出库");
        tbCommon.setTitle("");
    }

}
