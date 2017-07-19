package com.lide.app.presenter.PInterface;

import com.lubin.bean.InBoundOrder;

/**
 * Created by lubin on 2016/7/18.
 */
public interface IInBoundOrderPresenter {
    //获取收货单数据
    void getInBoundOrders();

    void loadDetailOrder(String orderId);

    void getSelectData();

    void initScanPresenter();

    void loadDetailBox(String caseCode);

    void startScan();

    void stopScan();

    void reset();

    void delete(String orderId);

    void confirm(Long orderId);

    void getDeviceId(String DeviceCode);

    void getNum();

    void saveInBounds();

    void getMultiBarcodeId(final String barcode);
}