package com.lide.app.ui.takeStock;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lide.app.R;
import com.lide.app.adapter.QuerySKUDiffAdapter;
import com.lide.app.bean.JsonToBean.SkuDiff;
import com.lide.app.config.Configs;
import com.lide.app.listener.OnFinishListener;
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
 * @time 2016/7/21  10:51
 * @desc ${查询_条码页面}
 */
public class QueryBarCodeFragment extends FragmentBase implements IDataFragmentView<List<SkuDiff.DataBean>> {

    @BindView(R.id.tv_sum_store)
    TextView mTvSumStore;
    @BindView(R.id.tv_sum_real)
    TextView mTvSumReal;
    @BindView(R.id.tv_sum_diff)
    TextView mTvSumDiff;
    @BindView(R.id.lv_query_diff_sku)
    ListView lvQueryDiffSku;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;


    private QuerySKUDiffAdapter mAdapter;
    private QueryDiffActivity mActivity;
    List<SkuDiff.DataBean> sku = new ArrayList<>();
    private IQueryDiffPresenter mPresenter;
    private ScanPresenter scanPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_query_bar_code, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {

        mActivity = (QueryDiffActivity) getActivity();
        mPresenter = new QueryDiffPresenterImpl(this);
        scanPresenter = new ScanPresenter(this);

        mAdapter = new QuerySKUDiffAdapter(getActivity(), sku);
        lvQueryDiffSku.setAdapter(mAdapter);
        lvQueryDiffSku.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mActivity.setText("唯一码");
                mActivity.showFragment(Configs.EPC_FRAGMENT);
                mActivity.mFragments.get(Configs.EPC_FRAGMENT).setData(mAdapter.getList().get(i).getBarcode());

            }
        });

        //长按删除根据条码删除标签
        lvQueryDiffSku.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                SkuDiff.DataBean dataBean = mAdapter.getList().get(position);
                showDialog(dataBean.getBarcode());
                return false;
            }
        });
    }

    @Override
    public void ShowData(List<SkuDiff.DataBean> dataBeen) {
        int snapQty = 0;
        int countQty = 0;
        int diffQty = 0;

        if (dataBeen.size() != 0) {
            for (SkuDiff.DataBean data : dataBeen) {
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
    }

    @Override
    public void setData(Object o) {
        String ProductCode = (String) o;
        mPresenter.LoadSkuDiff(ProductCode);
        mActivity.currentSkuType = "ALL";
        if (llSearch.getVisibility() == View.VISIBLE) {
            llSearch.setVisibility(View.GONE);
        }
    }

    @Override
    public void showDataByType(String Type) {
        if (llSearch.getVisibility() == View.GONE) {
            mPresenter.QuerySkuDiff(Type);
            mActivity.currentSkuType = Type;
            mActivity.setText(Type);
        }
    }

    @Override
    public void readOrClose() {
        int visibility = llSearch.getVisibility();
        switch (visibility) {
            case View.VISIBLE:
                llSearch.setVisibility(View.GONE);
                mPresenter.QuerySkuDiff(mActivity.currentSkuType);
                scanPresenter.removeListener();
                break;
            case View.GONE:
                etSearch.setText("");
                etSearch.requestFocus();
                llSearch.setVisibility(View.VISIBLE);
                scanPresenter.setMode(2);
                scanPresenter.setListener(new OnFinishListener() {
                    @Override
                    public void OnFinish(String data) {
                        if (data != null
                                && llSearch.getVisibility() == View.VISIBLE) {
                            etSearch.setText(data);
                            mPresenter.queryDetailSku(data);
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
            mPresenter.queryDetailSku(key);
        }
    }
}
