package com.lide.app.presenter.PInterface;

/**
 * Created by huangjianxionh on 2016/11/1.
 */

public interface IFindProductForSkuPresenter {
    //获取查询的barcode数据
    void searchDetailEpcList(String search);

    void startScan();

    void stopScan();
}
