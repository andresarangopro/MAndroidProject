package com.lide.app.model;

import com.google.gson.Gson;
import com.lide.app.bean.BeanToJson.ForTaskOrderlist;
import com.lide.app.listener.OnLoadFinishListener;
import com.lide.app.persistence.util.NetWorkUtil;
import com.lide.app.persistence.util.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HKR on 2017/3/2.
 * 盘点单任务操作，重置，完成，修改清点数
 */

public class ConfigTakeStockTackModel {


    //完成任务
    public void configTackStockTask(String orderId, String taskId, OnLoadFinishListener listener) throws Exception {
        Map<String, String> mapForOrderDetail = new HashMap<>();
        String method = "api/takeStockOrder/" + orderId + "/task/" + taskId + "/confirm";
        String postUrl = Utils.apiUrl + method;

        ForTaskOrderlist forTaskOrderlist = new ForTaskOrderlist();
        String requestJsonData = new Gson().toJson(forTaskOrderlist);

        mapForOrderDetail.put("postUrl", postUrl);
        mapForOrderDetail.put("requestJsonData", requestJsonData);
        mapForOrderDetail.put("apiKey", Utils.getApiKey());

        NetWorkUtil.startNetWord(requestJsonData, postUrl, listener);
    }

    //清除盘点任务
    public void clearSkuTags(String orderId, String taskId, OnLoadFinishListener listener) throws Exception {
        Map<String, String> mapForOrderDetail = new HashMap<>();
        String method = "api/takeStockOrder/" + orderId + "/task/" + taskId + "/skuTag/clear";
        String postUrl = Utils.apiUrl + method;

        ForTaskOrderlist forTaskOrderlist = new ForTaskOrderlist();
        String requestJsonData = new Gson().toJson(forTaskOrderlist);

        mapForOrderDetail.put("postUrl", postUrl);
        mapForOrderDetail.put("requestJsonData", requestJsonData);
        mapForOrderDetail.put("apiKey", Utils.getApiKey());

        NetWorkUtil.startNetWord(requestJsonData, postUrl, listener);
    }

    //修改清点数
    public void updateTakeStockTask(String orderId, String taskId, int countingQty, OnLoadFinishListener listener)  throws Exception{
        Map<String, String> mapForOrderDetail = new HashMap<>();
        String method = "api/takeStockOrder/" + orderId + "/task/" + taskId + "/update";
        String postUrl = Utils.apiUrl + method;

        ForTaskOrderlist forTaskOrderlist = new ForTaskOrderlist();
        forTaskOrderlist.countingQty = countingQty;
        String requestJsonData = new Gson().toJson(forTaskOrderlist);

        mapForOrderDetail.put("postUrl", postUrl);
        mapForOrderDetail.put("requestJsonData", requestJsonData);
        mapForOrderDetail.put("apiKey", Utils.getApiKey());

        NetWorkUtil.startNetWord(requestJsonData, postUrl, listener);
    }
}
