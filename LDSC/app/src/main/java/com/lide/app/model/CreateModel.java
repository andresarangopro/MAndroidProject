package com.lide.app.model;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.lide.app.listener.OnLoadFinishListener;
import com.lide.app.persistence.util.NetWorkUtil;
import com.lide.app.persistence.util.Utils;

/**
 * Created by lubin on 2016/11/18.
 */

public class CreateModel {

    /**
     * 新增出库单，返回单号
     *
     * @param toWarehouseId 收货仓库Id(收货仓库Id和收货往来单位Id不能同时为空)
     */
    public void createOutBoundOrder(String toWarehouseId, final OnLoadFinishListener listener) throws Exception {
        String apiMethod = "api/outboundOrder/create";
        String postUrl = Utils.apiUrl + apiMethod;
        LinkedTreeMap<Object, Object> createOutBoundOrder = new LinkedTreeMap<>();
        createOutBoundOrder.put("fromWarehouseId", Utils.getCurrentUser().getWarehouseId());
        createOutBoundOrder.put("toWarehouseId", toWarehouseId);
        String requestJsonData = new Gson().toJson(createOutBoundOrder);
        NetWorkUtil.startNetWord(requestJsonData, postUrl, listener);

    }

    /**
     * 根据商品条码获取多条码档案
     *
     * @param barcode 商品条码
     */
    public void createMultiBarcodeId(String barcode, final OnLoadFinishListener listener) throws Exception {
        String doMethod = "api/sku/" + barcode + "/multiBarcodes";
        String postUrl = Utils.apiUrl + doMethod;
        NetWorkUtil.StartNetWorkOnce(listener, postUrl, "{}");
    }
}
