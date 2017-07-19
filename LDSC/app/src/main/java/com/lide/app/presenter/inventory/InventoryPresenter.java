package com.lide.app.presenter.inventory;

import android.extend.util.LogUtil;

import com.google.gson.Gson;
import com.lide.app.bean.JsonToBean.InventoryBean;
import com.lide.app.listener.OnLoadFinishListener;
import com.lide.app.model.InventoryMode;
import com.lide.app.ui.VInterface.IDataFragmentView;

import java.util.Map;

/**
 * Created by huangjianxionh on 2017/1/9.
 */

public class InventoryPresenter {

    private final InventoryMode mode;
    IDataFragmentView view;

    public InventoryPresenter(IDataFragmentView view) {
        this.view = view;
        mode = new InventoryMode();
    }

    public void getSearchInventory(String barcode) {
        view.startProgressDialog("查询中...");
        try {
            mode.queryInventoryList(barcode, new OnLoadFinishListener() {
                @Override
                public void OnLoadFinish(Map<String, String> map) {
                    if (map.get("statusCode").equals("200")) {
                        InventoryBean result = new Gson().fromJson(map.get("result"), InventoryBean.class);
                        LogUtil.w("result", result.toString());
                        view.ShowData(result);
                    } else {
                        view.showDialog(map.get("result"));
                    }
                    view.stopProgressDialog(null);
                }
            });
        } catch (Exception e) {
            view.showDialog(e.getMessage());
        }

    }
}
