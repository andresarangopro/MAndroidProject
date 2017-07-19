package com.lide.app.model;

import com.google.gson.Gson;
import com.lide.app.bean.BeanToJson.requestForInventoryList;
import com.lide.app.listener.OnLoadFinishListener;
import com.lide.app.model.MInterface.IInventoryMode;
import com.lide.app.persistence.util.NetWorkUtil;
import com.lide.app.persistence.util.Utils;

/**
 * Created by huangjianxionh on 2017/1/9.
 */

public class InventoryMode {

    //查找,根据key查找sku列表
    public void queryInventoryList(String barcode, final OnLoadFinishListener listener) throws Exception{
        String apiMethod = "api/stock/get/stock";
//        String postUrl = "http://192.168.8.218:8887/" + apiMethod;
        String postUrl = Utils.apiUrl + apiMethod;

        requestForInventoryList requestForInventoryList = new requestForInventoryList();
//        requestForInventoryList.warehouseCode = "SH500";//"warehouseCode" : "SH500",
//        requestForInventoryList.productCode = "001";    // "productCode"   : "001",
//        requestForInventoryList.barcode = "00101S";     //"barcode"       : "00101S"
        requestForInventoryList.warehouseCode = Utils.getCurrentUser().getWarehouseCode();     //"barcode"       : "00101S"
//        requestForInventoryList.productCode = ;     //"barcode"       : "00101S"
        requestForInventoryList.barcode = barcode;

        String requestJsonData = new Gson().toJson(requestForInventoryList);

        NetWorkUtil.startNetWord(requestJsonData, postUrl, listener);
    }

}
