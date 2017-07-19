package com.lide.app.ui.inventory;

import android.content.Intent;
import android.extend.util.ResourceUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.lide.app.R;
import com.lide.app.bean.JsonToBean.InventoryBean;
import com.lide.app.config.Configs;
import com.lide.app.listener.OnFinishListener;
import com.lide.app.persistence.util.SoundUtils;
import com.lide.app.persistence.util.Utils;
import com.lide.app.presenter.ScanPresenter;
import com.lide.app.presenter.inventory.InventoryPresenter;
import com.lide.app.ui.FragmentBase;
import com.lide.app.ui.LoginActivity;
import com.lide.app.ui.VInterface.IDataFragmentView;

import java.util.Iterator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import recycler.AbsAdapterItem;
import recycler.BaseRecyclerAdapter;
import recycler.GridRecyclerView;

/**
 * Created by huangjianxionh on 2017/1/9.
 */
public class InventoryFragment extends FragmentBase implements IDataFragmentView<InventoryBean> {

    @BindView(R.id.inventory_query_edit)
    EditText inventoryQueryEdit;
    @BindView(R.id.inventory_query_layout)
    ScrollView inventoryQueryLayout;
    @BindView(R.id.all_num)
    TextView allNum;
    private GridRecyclerView inventoryQueryListView;
    public BaseRecyclerAdapter inventoryQueryListAdapter;

    private View mView;

    private ScanPresenter scanPresenter;
    private InventoryPresenter inventoryPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.inventory_layout, container, false);
        ButterKnife.bind(this, mView);
        inventoryPresenter = new InventoryPresenter(this);
        initView();
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        initScanPresenter();
    }

    @Override
    public void onPause() {
        super.onPause();
        scanPresenter.removeListener();
    }

    private void initView() {
        inventoryQueryEdit.setFocusable(true);
        inventoryQueryEdit.setFocusableInTouchMode(true);
        inventoryQueryEdit.requestFocus();
        inventoryQueryEdit.findFocus();


        inventoryQueryListView = new GridRecyclerView(getActivity());
        ((ViewGroup) inventoryQueryLayout).addView(inventoryQueryListView, 0, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        inventoryQueryListView.setSpanCount(1);
        inventoryQueryListView.setItemMargin(0);
        inventoryQueryListAdapter = new BaseRecyclerAdapter();
        inventoryQueryListView.setAdapter(inventoryQueryListAdapter);
        inventoryQueryListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        inventoryQueryListAdapter.clear();
    }

    @OnClick(R.id.inventory_order_btn)
    public void onClick() {
        String sText = inventoryQueryEdit.getText().toString();
        if (sText.isEmpty()) {
            showDialog("查询条件不能为空!");
        } else {
            if (Utils.getCurrentUser() != null) {
                inventoryPresenter.getSearchInventory(sText);
            } else {
                //未登录
                Intent login = new Intent(getActivity(), LoginActivity.class);
                login.putExtra("isAtNet", true);
                startActivityForResult(login, Configs.LOGIN);
            }
        }
    }

    private void initScanPresenter() {
        scanPresenter = new ScanPresenter(this);
        scanPresenter.initData();
        scanPresenter.setMode(2);
        scanPresenter.setListener(new OnFinishListener() {
            @Override
            public void OnFinish(String data) {
                if (data.isEmpty()) {
                    showDialog("查询条件不能为空!");
                } else {
                    if (Utils.getCurrentUser() != null) {
                        SoundUtils.play(1);
                        inventoryQueryEdit.setText(data);
                        inventoryPresenter.getSearchInventory(data);
                    } else {
                        //未登录
                        Intent login = new Intent(getActivity(), LoginActivity.class);
                        login.putExtra("isAtNet", true);
                        startActivityForResult(login, Configs.LOGIN);
                    }
                }
            }
        });
    }
    int num = 0;

    @Override
    public void ShowData(InventoryBean inventoryBean) {
        if (inventoryBean.getData().size() > 0) {
            for (Iterator<InventoryBean.Inventory> iterator = inventoryBean.getData().iterator(); iterator.hasNext(); ) {
                InventoryBean.Inventory next = iterator.next();
                inventoryQueryListAdapter.addItem(new InventoryItem(next),0);
            }
            num = num +inventoryBean.getData().size();
            allNum.setText("查询总数：" + num);
        } else showDialog("没有该条码~");
    }

    private class InventoryItem extends AbsAdapterItem {
        InventoryBean.Inventory next;

        public InventoryItem(InventoryBean.Inventory next) {
            this.next = next;
        }

        @Override
        public View onCreateView(ViewGroup parent, int position) {
            int layout = ResourceUtil.getLayoutId(getActivity(), "inventory_item");
            View view = View.inflate(getActivity(), layout, null);
            return view;
        }

        @Override
        public void onBindView(BaseRecyclerAdapter.BaseViewHolder holder, View view, int position) {
            int id = ResourceUtil.getId(getActivity(), "inventory_item_prodcode");
            TextView prodCode = (TextView) view.findViewById(id);

            id = ResourceUtil.getId(getActivity(), "inventory_item_qty");
            TextView qty = (TextView) view.findViewById(id);

            id = ResourceUtil.getId(getActivity(), "inventory_item_lstprice");
            TextView lstPrice = (TextView) view.findViewById(id);

            id = ResourceUtil.getId(getActivity(), "inventory_item_name");
            TextView name = (TextView) view.findViewById(id);

            id = ResourceUtil.getId(getActivity(), "inventory_item_colorname");
            TextView colorName = (TextView) view.findViewById(id);

            prodCode.setText(next.getBarcode());
            qty.setText(String.valueOf(next.getQty()));
            lstPrice.setText(next.getProductCode());
            name.setText(next.getSkuName());
            colorName.setText(next.getWarehouseCode());

        }

        @Override
        public void onViewAttachedToWindow(BaseRecyclerAdapter.BaseViewHolder holder, View view) {

        }
    }
}
