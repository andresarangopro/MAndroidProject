package com.lubin.chj.view.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.lubin.chj.R;
import com.lubin.chj.adapter.QybhDetailsAdapter;
import com.lubin.chj.bean.jsonToBean.QueryPcReturn;
import com.lubin.chj.presenter.QuYuPresenterImpl;
import com.lubin.chj.view.activity.VInterface.IQuYuView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HKR on 2017/2/21.
 */

public class QuYuDetailsActivity extends BaseActivity implements IQuYuView {
    @BindView(R.id.tb_common)
    Toolbar mTbCommon;
    @BindView(R.id.common_title)
    AppBarLayout mCommonTitle;
    @BindView(R.id.lv_query_qybh)
    ListView mLvQueryQybh;
    @BindView(R.id.tv_sum)
    TextView tvSum;
    private QybhDetailsAdapter mQybhAdapter;
    private QuYuPresenterImpl mQuYuPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_quyu);
        ButterKnife.bind(this);
        initHeader();
        init();
    }

    private void init() {
        mQuYuPresenter = new QuYuPresenterImpl(this);
        String qybh = this.getIntent().getStringExtra("qybh");
        mQuYuPresenter.QueryPCbyQybh(qybh);
    }

    private void initHeader() {
        mTbCommon.setNavigationIcon(R.mipmap.back);
        mTbCommon.setTitle("区域信息");
        mTbCommon.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //展示区域信息 将柜位信息换成区域信息修改头部信息
    @Override
    public void ShowQyMsg(final List<QueryPcReturn.ListBean> list) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Collections.sort(list, new Comparator<QueryPcReturn.ListBean>() {
                    @Override
                    public int compare(QueryPcReturn.ListBean user1, QueryPcReturn.ListBean user2) {
                        String id1 = user1.getPch();
                        String id2 = user2.getPch();
                        //可以按User对象的其他属性排序，只要属性支持compareTo方法
                        return id1.compareTo(id2);
                    }
                });
                tvSum.setText(String.valueOf(list.size()));
                mQybhAdapter = new QybhDetailsAdapter(QuYuDetailsActivity.this, list);
                mLvQueryQybh.setAdapter(mQybhAdapter);

            }
        });
    }

    @Override
    public void ShowDialog(String result) {
        super.ShowDialog(result);
    }
}
