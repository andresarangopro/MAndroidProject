package com.lide.app.presenter.takeStock;

import com.lide.app.config.Configs;
import com.lide.app.listener.OnLoadFinishListener;
import com.lide.app.model.MInterface.ITakeStockOrderModel;
import com.lide.app.model.TakeStockOrderModelImpl;
import com.lide.app.presenter.PInterface.ITakeStockOrderPresenter;
import com.lide.app.ui.VInterface.ITakeStockView;
import com.lubin.bean.TakeStockOrder;

import java.util.Map;

public class TakeStockPresenterImpl implements ITakeStockOrderPresenter {

    ITakeStockView view;
    ITakeStockOrderModel model;

    public TakeStockPresenterImpl(ITakeStockView view) {
        this.view = view;
        this.model = new TakeStockOrderModelImpl();

    }

    //获取盘点单列表
    @Override
    public void getTakeStockOrders() {
        view.startProgressDialog("加载中...");
        model.getTakeStockOrders(model.getHashMapForOrders(), new OnLoadFinishListener() {
            @Override
            public void OnLoadFinish(Map<String, String> map) {
                if (map.get("statusCode").equals("200")) {
                    view.ShowData(model.getOrders(map.get("result")));
                    view.stopProgressDialog(null);
                } else {
                    view.showDialog(map.get("result"));
                    view.stopProgressDialog(null);
                }
            }
        });
    }
    //盘点任务绑定盘点单，并生成任务ID，保存本地
    @Override
    public void bindCheckTask(TakeStockOrder takeStockOrder) {
        model.bindCheckTask(model.getHashMapForBing(takeStockOrder), new OnLoadFinishListener() {
            @Override
            public void OnLoadFinish(Map<String, String> map) {
                if (map.get("statusCode").equals("200")) {
                    //跳到上传界面
                    view.startNewActivity(null);
                    //本界面切换展示标签采集界面
                    view.showFragment(Configs.READ_FRAGMENT);
                } else {
                    view.showDialog(map.get("result"));
                }
            }
        });
    }

}
