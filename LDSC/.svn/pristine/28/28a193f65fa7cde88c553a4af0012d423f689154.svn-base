package com.lide.app.ui.findProduct;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.lide.app.R;
import com.lide.app.adapter.SearchFindProductAdapter;
import com.lide.app.config.Configs;
import com.lide.app.presenter.PInterface.ISearchSkuListPresenter;
import com.lide.app.presenter.search.SearchSkuListListPresenterImpl;
import com.lide.app.ui.LoginActivity;
import com.lide.app.ui.VInterface.IDataFragmentView;
import com.lide.app.ui.FragmentBase;
import com.lide.app.persistence.util.Utils;
import com.lide.app.persistence.view.xlist.XListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//按订单收货界面
public class SearchFindProductFragment extends FragmentBase implements IDataFragmentView<List<String>>, XListView.IXListViewListener {


    @BindView(R.id.product_search_text)
    EditText productSearchText;
    @BindView(R.id.product_search_btn)
    TextView productSearchBtn;
    @BindView(R.id.find_product_layout)
    XListView findProductLayout;
    private SearchFindProductAdapter mAdapter;
    List<String> mData = new ArrayList<>();


    private View mView;
    private ISearchSkuListPresenter mPresenter;
    private String sText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_search_barcode, container, false);
        ButterKnife.bind(this, mView);
        mPresenter = new SearchSkuListListPresenterImpl(this);
        initView();
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
//        mPresenter.getSearchSku("0002201");
    }

    private void initView() {
        findProductLayout.setPullLoadEnable(false);
        findProductLayout.setPullRefreshEnable(false);
        findProductLayout.setXListViewListener(this);
        findProductLayout.setDividerHeight(0);
        mAdapter = new SearchFindProductAdapter(getActivity(), mData);
        findProductLayout.setAdapter(mAdapter);
        findProductLayout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), FindActivity.class);
                intent.putExtra("Activity", "SearchFindProductFragment");
                intent.putExtra("Barcode", mAdapter.getList().get(i - 1));
                startAnimActivity(intent);

            }
        });
        mAdapter.clearAll();

    }

    @Override
    public void setBarcode(String barcode) {
        super.setBarcode(barcode);
        mAdapter.clearAll();
        if (productSearchText != null) {
            productSearchText.setText(barcode);
            sText = productSearchText.getText().toString();
            if (Utils.getCurrentUser() != null) {
                mPresenter.getSearchSku(sText);
                findProductLayout.setPullLoadEnable(true);
            } else {
                //未登录
                Intent login = new Intent(getActivity(), LoginActivity.class);
                login.putExtra("isAtNet", true);
                startActivityForResult(login, Configs.LOGIN);
            }
        }
    }

    @OnClick(R.id.product_search_btn)
    public void onClick() {
        setBarcode(productSearchText.getText().toString());
    }

    @Override
    public void ShowData(List<String> strings) {
        mData.addAll(strings);
        mAdapter.notifyDataSetChanged();
        findProductLayout.stopRefresh();
        findProductLayout.stopLoadMore();
    }

    public void showDialog(final String result) {
        builder.setMessage(result);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (result.equals("没有数据啦~"))
                    findProductLayout.setPullLoadEnable(false);
            }
        });
        builder.show();
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        mPresenter.getSearchSku(sText);
    }
}
