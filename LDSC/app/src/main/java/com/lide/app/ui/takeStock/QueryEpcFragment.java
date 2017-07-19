package com.lide.app.ui.takeStock;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.lide.app.R;
import com.lide.app.adapter.QueryEpcAdapter;
import com.lide.app.bean.JsonToBean.EPCDiff;
import com.lide.app.listener.OnFinishListener;
import com.lide.app.persistence.view.xlist.XListView;
import com.lide.app.presenter.PInterface.IQueryDiffPresenter;
import com.lide.app.presenter.ScanPresenter;
import com.lide.app.presenter.takeStock.QueryDiffPresenterImpl;
import com.lide.app.ui.FragmentBase;
import com.lide.app.ui.VInterface.IDataFragmentView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author DaiJiCheng
 * @time 2016/7/21  10:52
 * @desc ${查询_Epc详情页}
 */
public class QueryEpcFragment extends FragmentBase implements IDataFragmentView<List<EPCDiff.DataBean>>, XListView.IXListViewListener {


    @BindView(R.id.lv_query_diff_epc)
    XListView lvQueryDiffEpc;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    private QueryEpcAdapter mAdapter;
    private IQueryDiffPresenter mPresenter;
    private String barcode;
    private List<EPCDiff.DataBean> mData = new ArrayList<>();
    private ScanPresenter scanPresenter;
    private boolean isHasNext = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_query_epc, container, false);
        ButterKnife.bind(this, root);
        initListView();
        return root;
    }

    private void initListView() {
        lvQueryDiffEpc.setPullLoadEnable(true);
        lvQueryDiffEpc.setPullRefreshEnable(false);
        lvQueryDiffEpc.setXListViewListener(this);
        lvQueryDiffEpc.setDividerHeight(0);
        mAdapter = new QueryEpcAdapter(getActivity(), mData);
        lvQueryDiffEpc.setAdapter(mAdapter);

        mPresenter = new QueryDiffPresenterImpl(this);
        scanPresenter = new ScanPresenter(this);

    }

    @Override
    public void ShowData(List<EPCDiff.DataBean> dataBeen) {
        mAdapter.addAll(dataBeen);
        lvQueryDiffEpc.stopRefresh();
        lvQueryDiffEpc.stopLoadMore();
    }

    @Override
    public void setData(Object o) {
        barcode = (String) o;
        mAdapter.clearAll();
        isHasNext = true;
        lvQueryDiffEpc.setPullLoadEnable(true);
        mPresenter.LoadEpc(barcode,true);
        if (llSearch.getVisibility() == View.VISIBLE) {
            llSearch.setVisibility(View.GONE);
        }
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        mPresenter.LoadEpc(barcode);
    }

    public void showDialog(final String result) {
        builder.setMessage(result);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (result.equals("没有数据啦~"))
                    lvQueryDiffEpc.setPullLoadEnable(false);
                isHasNext = false;
            }
        });
        builder.show();
    }

    @Override
    public void readOrClose() {
        int visibility = llSearch.getVisibility();
        switch (visibility) {
            case View.VISIBLE:
                llSearch.setVisibility(View.GONE);
                mPresenter.QueryEpc();
                scanPresenter.removeListener();
                if (isHasNext)
                    lvQueryDiffEpc.setPullLoadEnable(true);
                break;
            case View.GONE:
                llSearch.setVisibility(View.VISIBLE);
                etSearch.requestFocus();
                etSearch.setText("");
                scanPresenter.setMode(2);
                scanPresenter.setListener(new OnFinishListener() {
                    @Override
                    public void OnFinish(String data) {
                        if (data != null && llSearch.getVisibility() == View.VISIBLE) {
                            etSearch.setText(data);
                            mPresenter.queryDetailEpc(data);
                        }
                    }
                });
                lvQueryDiffEpc.setPullLoadEnable(false);
                break;
        }
    }

    @OnClick(R.id.btn_search)
    public void onClick() {
        String key = etSearch.getText().toString();
        if (!key.equals("")) {
            mPresenter.queryDetailEpc(key);
        }
    }
}
