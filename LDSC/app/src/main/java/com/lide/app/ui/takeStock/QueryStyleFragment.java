package com.lide.app.ui.takeStock;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lide.app.R;
import com.lide.app.adapter.QueryDiffAdapter;
import com.lide.app.bean.JsonToBean.ProductDiff;
import com.lide.app.config.Configs;
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
 * @time 2016/7/21  9:56
 * @desc ${查询界面_样式}
 */
public class QueryStyleFragment extends FragmentBase implements IDataFragmentView<List<ProductDiff.DataBean>>, XListView.IXListViewListener {


    @BindView(R.id.tv_sum_store)
    TextView mTvSumStore;
    @BindView(R.id.tv_sum_real)
    TextView mTvSumReal;
    @BindView(R.id.tv_sum_diff)
    TextView mTvSumDiff;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.lv_query_style)
    XListView lvQueryStyle;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;

    private QueryDiffAdapter mAdapter;
    private QueryDiffActivity mActivity;
    private IQueryDiffPresenter mPresenter;
    List<ProductDiff.DataBean> mData = new ArrayList<>();
    private ScanPresenter scanPresenter;
    private boolean isHasNext = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_query_style, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initListView();
    }

    private void initListView() {
        mAdapter = new QueryDiffAdapter(getActivity(), mData);
        lvQueryStyle.setAdapter(mAdapter);
        lvQueryStyle.setPullLoadEnable(true);
        lvQueryStyle.setPullRefreshEnable(false);
        lvQueryStyle.setXListViewListener(this);
        lvQueryStyle.setDividerHeight(0);
        lvQueryStyle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mActivity.mFragments.get(Configs.BAR_CODE_FRAGMENT)
                        .setData(mAdapter.getList().get(i - 1).getProductCode());
                mActivity.showFragment(Configs.BAR_CODE_FRAGMENT);
            }
        });
    }

    private void initView() {

        mActivity = (QueryDiffActivity) getActivity();
        mPresenter = new QueryDiffPresenterImpl(this);
        scanPresenter = new ScanPresenter(this);

        mPresenter.LoadProductDiff();
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        mPresenter.LoadProductDiff();
    }

    @Override
    public void ShowData(List<ProductDiff.DataBean> dataBeen) {
        int snapQty = 0;
        int countQty = 0;
        int diffQty = 0;

        if (dataBeen.size() != 0) {
            for (ProductDiff.DataBean data : dataBeen) {
                snapQty += data.getSnapQty();
                countQty += data.getCountQty();
                diffQty += data.getDiffQty();
            }
        }
        mTvSumStore.setText(String.valueOf(snapQty));
        mTvSumReal.setText(String.valueOf(countQty));
        mTvSumDiff.setText(String.valueOf(diffQty));
        mTvSumDiff.setTextColor(Color.RED);
        mAdapter.addAll(dataBeen);
        lvQueryStyle.stopRefresh();
        lvQueryStyle.stopLoadMore();
    }

    public void showDialog(final String result) {
        builder.setMessage(result);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (result.equals("没有数据啦~"))
                    lvQueryStyle.setPullLoadEnable(false);
                isHasNext = false;
            }
        });
        builder.show();
    }

    @Override
    public void showDataByType(String Type) {
        if (llSearch.getVisibility() == View.GONE) {
            mPresenter.QueryProductDiff(Type);
            mActivity.currentProductType = Type;
            mActivity.setText(Type);
            if (Type.equals("ALL") && isHasNext)
                lvQueryStyle.setPullLoadEnable(true);
            else
                lvQueryStyle.setPullLoadEnable(false);
        }
    }

    @Override
    public void readOrClose() {
        int visibility = llSearch.getVisibility();
        switch (visibility) {
            case View.VISIBLE:
                llSearch.setVisibility(View.GONE);
                if (isHasNext && mActivity.currentProductType.equals("ALL"))
                    lvQueryStyle.setPullLoadEnable(true);
                mPresenter.QueryProductDiff(mActivity.currentProductType);
                scanPresenter.removeListener();
                break;
            case View.GONE:
                etSearch.setText("");
                etSearch.requestFocus();
                llSearch.setVisibility(View.VISIBLE);
                lvQueryStyle.setPullLoadEnable(false);
                scanPresenter.setMode(2);
                scanPresenter.setListener(new OnFinishListener() {
                    @Override
                    public void OnFinish(String data) {
                        if (data != null && llSearch.getVisibility() == View.VISIBLE) {
                            etSearch.setText(data);
                            mPresenter.queryDetailProduct(data);
                        }
                    }
                });
                break;
        }
    }

    @OnClick(R.id.btn_search)
    public void onClick() {
        String key = etSearch.getText().toString();
        if (!key.equals("")) {
            mPresenter.queryDetailProduct(key);
        }
    }
}
