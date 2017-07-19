package com.lide.app.presenter.takeStock;

import com.lide.app.listener.OnLoadFinishListener;
import com.lide.app.model.ConfigTakeStockTackModel;
import com.lide.app.ui.VInterface.IDataFragmentView;

import java.util.Map;

/**
 * Created by HKR on 2017/3/2.
 * 盘点明细
 */

public class ConfigTakeStockTackPresenterImpl {

    IDataFragmentView view;
    ConfigTakeStockTackModel mConfigTakeStockTackModel;

    public ConfigTakeStockTackPresenterImpl(IDataFragmentView view) {
        this.view = view;
        mConfigTakeStockTackModel = new ConfigTakeStockTackModel();
    }

    //回传
    public void getConfigTakeStockTack(String orderId, String taskId) {
        try {
            mConfigTakeStockTackModel.configTackStockTask(orderId, taskId, new OnLoadFinishListener() {
                @Override
                public void OnLoadFinish(Map<String, String> map) {
                    if (map.get("statusCode").equals("200")) {
                        view.showDialog("回传成功");
                    } else {
                        view.showDialog(map.get("result"));
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //重置
    public void getClearSkuTags(String orderId, String taskId) {
        try {
            mConfigTakeStockTackModel.clearSkuTags(orderId, taskId, new OnLoadFinishListener() {
                @Override
                public void OnLoadFinish(Map<String, String> map) {
                    if (map.get("statusCode").equals("200")) {
                        view.ShowData("S");
                        view.showDialog("清除成功");
                    } else {
                        view.showDialog(map.get("result"));
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //修改清点数
    public void updateTakeStockTask(String orderId, String taskId, int countingQty) {
        try {
            mConfigTakeStockTackModel.updateTakeStockTask(orderId, taskId, countingQty, new OnLoadFinishListener() {
                @Override
                public void OnLoadFinish(Map<String, String> map) {
                    if (map.get("statusCode").equals("200")) {
                        view.showDialog("清除成功");
                    } else {
                        String result = map.get("result");
                        view.showDialog(result);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
