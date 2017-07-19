package com.lide.app.ui.takeStock;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.lide.app.R;
import com.lide.app.adapter.EpcCollectAdapter;
import com.lide.app.presenter.takeStock.EPCPresenter;
import com.lide.app.ui.BaseActivity;
import com.lide.app.ui.VInterface.IDataFragmentView;
import com.lubin.bean.TakeStockEpcCollect;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
//展示任务里面的标签明细
public class EpcCollectActivity extends BaseActivity implements IDataFragmentView<List<TakeStockEpcCollect>> {
    @BindView(R.id.lv_epc)
    ListView lvEpc;
    List<TakeStockEpcCollect> mData;
    EpcCollectAdapter mAdapter;
    EPCPresenter mPresenter;
    @BindView(R.id.tb_epc)
    Toolbar tbEpc;
    @BindView(R.id.et_search_epc)
    EditText etSearchEpc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_epc);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    @Override
    protected void onResume() {
        mPresenter.showEPCList();
        tbEpc.setTitle("");
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_epc, menu);
        return true;
    }

    private void initData() {
        mData = new ArrayList<>();
        mAdapter = new EpcCollectAdapter(this, mData);
        mPresenter = new EPCPresenter(this);
        lvEpc.setAdapter(mAdapter);
    }

    private void initView() {
        setSupportActionBar(tbEpc);
        tbEpc.setNavigationIcon(R.mipmap.back_login);
        tbEpc.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tbEpc.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_search:
                        showEtSearch();
                        break;
                    case R.id.action_synchronization:
                        break;
                }
                return true;
            }

        });

        etSearchEpc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mPresenter.searchDetailEpc(s.toString());
            }
        });
    }

    private void showEtSearch() {
        int visibility = etSearchEpc.getVisibility();
        switch (visibility) {
            case 0:
                etSearchEpc.setVisibility(View.GONE);
                break;
            case 8:
                etSearchEpc.setVisibility(View.VISIBLE);
                break;
        }

    }

    @Override
    public void ShowData(List<TakeStockEpcCollect> takeStockEpcCollects) {
        if (takeStockEpcCollects.size() > 0) {
            mAdapter.addAll(takeStockEpcCollects);
        }
    }
}

