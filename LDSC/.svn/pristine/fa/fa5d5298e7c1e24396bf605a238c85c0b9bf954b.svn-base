package com.lide.app.model.MInterface;

import com.lide.app.listener.OnLoadFinishListener;
import com.lubin.bean.TakeStockOrder;

import java.util.List;
import java.util.Map;

/**
 * Created by lubin on 2016/7/18.
 */
public interface ITakeStockOrderModel {

    void bindCheckTask(Map<String, String> map,OnLoadFinishListener listener);

    List<TakeStockOrder> getOrders(String result);

    void getTakeStockOrders(Map<String, String> map, final OnLoadFinishListener listener);

    Map<String, String> getHashMapForOrders();

    Map<String, String> getHashMapForBing(TakeStockOrder takeStockOrder);

}