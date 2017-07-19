package com.lide.app.model.MInterface;

import com.lide.app.bean.JsonToBean.DeviceInfoForID;
import com.lide.app.bean.JsonToBean.OrderForId;
import com.lide.app.listener.OnLoadFinishListener;
import com.lubin.bean.InBoundOperate;
import com.lubin.bean.InBoundCase;
import com.lubin.bean.InBoundOrder;

import java.util.List;
import java.util.Map;

/**
 * @author DaiJiCheng
 * @time 2016/10/8  11:30
 * @desc ${按单号入库}
 */
public interface IInBoundOrderModel {
    //根据单号 获取ID
    Map<String, String> getMapForId(String order);

    void searchInBoundOrder(Map<String, String> map, OnLoadFinishListener listener);

    List<OrderForId.DataBean> getOrderForIdListBeans();

    //根据ID获取单号详细信息
    Map<String, String> getMapForOrderDetail(String Order);

    void getOrderDetail(Map<String, String> map, OnLoadFinishListener listener);

    List<InBoundOrder> getAllInBoundOrder();

    boolean saveData(OrderForId.DataBean bean);

    boolean loadDataForDB(String tag, String key);

    InBoundCase getInBoundCase();

    InBoundOrder getInBoundOrder();

    //根据设备编码获取设备ID
    Map<String, String> getMapForDeviceID(String deviceCode);

    void getDeviceList(Map<String, String> map, OnLoadFinishListener listener);

    List<DeviceInfoForID.DataBean> getDeviceInfoListBean();

    //根据收货单ID审核收货单
    Map<String, String> getMapForConfirmOrder(String ID);

    void confirmOrder(Map<String, String> map, OnLoadFinishListener listener);

    void deleteInBoundOrder(String orderId);

    //-----------------------------------------------------------------------------
    Map<String, String> getMapForMultiBarcodeId(String barCode);

    void getMultiBarcodeIdByBarcode(Map<String, String> map, final OnLoadFinishListener listener);

    List<String> getMultiBarcodeFirstID(String result);

    //-----------------------------------------------------------------------------
    Map<String, String> getMapForSaveInBounds(String inboundOrderId, String deviceId, List<InBoundOperate> list);

    void saveInBounds(Map<String, String> map, final OnLoadFinishListener listener);
}
