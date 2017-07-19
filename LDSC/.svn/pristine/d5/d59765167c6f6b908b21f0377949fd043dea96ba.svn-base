package com.lide.app.ui.outbound.createOrder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;

import com.google.gson.internal.LinkedTreeMap;
import com.lide.app.R;
import com.lide.app.adapter.SearchWarehouseAdapter;
import com.lide.app.config.Configs;
import com.lide.app.persistence.util.Utils;
import com.lide.app.persistence.view.xlist.XListView;
import com.lide.app.presenter.PInterface.ISearchWarehouseListPresenter;
import com.lide.app.presenter.search.SearchWarehouseListPresenterImpl;
import com.lide.app.ui.FragmentBase;
import com.lide.app.ui.LoginActivity;
import com.lide.app.ui.VInterface.IDataFragmentView;
import com.lubin.bean.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lubin on 2016/11/17.
 */
public class SearchWarehousesFragment extends FragmentBase implements IDataFragmentView<List<LinkedTreeMap>>, XListView.IXListViewListener {

    @BindView(R.id.edit_search)
    EditText editSearch;
    @BindView(R.id.lv_contain_warehouse)
    XListView lvContainWarehouse;

    private View mView;
    private List<LinkedTreeMap> mData = new ArrayList<>();
    private ISearchWarehouseListPresenter mPresenter;
    private SearchWarehouseAdapter mAdatper;
    private String mCode = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_search_warehouse, container, false);
        ButterKnife.bind(this, mView);
        mPresenter = new SearchWarehouseListPresenterImpl(this);
        initView();
        return mView;
    }

    private void initView() {
        lvContainWarehouse.setPullLoadEnable(false);
        lvContainWarehouse.setPullRefreshEnable(false);
        lvContainWarehouse.setXListViewListener(this);
        lvContainWarehouse.setDividerHeight(0);
        mAdatper = new SearchWarehouseAdapter(getActivity(), mData);
        lvContainWarehouse.setAdapter(mAdatper);
        lvContainWarehouse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User currentUser = Utils.getCurrentUser();
                LinkedTreeMap linkedTreeMap = mData.get(position - 1);
                if (!currentUser.getWarehouseCode().equals(linkedTreeMap.get("code"))) {
                    //根据用户选择的仓库生成出库单
                    mPresenter.CreateOutBoundOrder(linkedTreeMap);
                } else {
                    showDialog("发货方跟目的地相同~");
                }
            }
        });

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String code = s.toString();
                if (mCode == null) {
                    mCode = code;
                } else {
                    if (!code.equals(mCode)) {
                        mCode = code;
                        mAdatper.clearAll();
                        lvContainWarehouse.setPullLoadEnable(false);
                    }
                }
            }
        });
    }

    @Override
    public void setBarcode(String barcode) {
        if (editSearch != null) {
            editSearch.setText(barcode);
            if (Utils.getCurrentUser() != null) {
                //根据用户输入的或者是用户扫描到的barcode搜索仓库列表
                mPresenter.SearchWarehouseList(editSearch.getText().toString());
            } else {
                //未登录
                Intent login = new Intent(getActivity(), LoginActivity.class);
                login.putExtra("isAtNet", true);
                startActivityForResult(login, Configs.LOGIN);
            }
        }
    }

    @Override
    public void showDialog(String result) {
        super.showDialog(result);
        if (result == null) {
            getActivity().finish();
        }
    }

    @Override
    public void ShowData(List<LinkedTreeMap> linkedTreeMaps) {
        mData.addAll(linkedTreeMaps);
        mAdatper.notifyDataSetChanged();
        lvContainWarehouse.setPullLoadEnable(true);
        lvContainWarehouse.stopRefresh();
        lvContainWarehouse.stopLoadMore();
    }

    @Override
    public void ShowToast(String text) {
        super.ShowToast(text);
        if (text.equals("没有数据啦！") || text.equals("根据搜索到0条数据！")) {
            lvContainWarehouse.setPullLoadEnable(false);
        }
    }

    @OnClick(R.id.btn_search)
    public void onClick() {
        String key = editSearch.getText().toString();
        setBarcode(key);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        String key = editSearch.getText().toString();
        setBarcode(key);
    }
}
