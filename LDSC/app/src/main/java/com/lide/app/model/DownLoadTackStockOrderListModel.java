package com.lide.app.model;

import com.google.gson.Gson;
import com.lide.app.bean.BeanToJson.ForTaskOrderlist;
import com.lide.app.listener.OnLoadFinishListener;
import com.lide.app.persistence.util.NetWorkUtil;
import com.lide.app.persistence.util.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DaiJiCheng
 * @time 2017/2/23  16:44
 * @desc ${下载盘点单列表的界面}
 */
public class DownLoadTackStockOrderListModel {


    /**
     * 下载盘点单
     *
     * @param warehouseId    仓库ID
     * @param search         模糊查询盘点单的字段
     * @param isPageable     是否启用分页，默认为true，启用分页
     * @param currentPage    当前页码，默认为第1页
     * @param recordsPerPage 每页记录数，默认每页显示20行记录
     */
    public void downloadOrderDetail(String warehouseId, String search, boolean isPageable, int currentPage, int recordsPerPage, OnLoadFinishListener listener) throws Exception {

        Map<String, String> mapForOrderDetail = new HashMap<>();
        String method = "/api/takeStockOrder/list";
        String postUrl = Utils.apiUrl + method;

        ForTaskOrderlist forTaskOrderlist = new ForTaskOrderlist();
        if (!search.isEmpty())
            forTaskOrderlist.code = search;
        forTaskOrderlist.warehouseId = warehouseId;
        forTaskOrderlist.isPageable = isPageable;
        forTaskOrderlist.currentPage = currentPage;
        forTaskOrderlist.recordsPerPage = recordsPerPage;
        String requestJsonData = new Gson().toJson(forTaskOrderlist);

        mapForOrderDetail.put("postUrl", postUrl);
        mapForOrderDetail.put("requestJsonData", requestJsonData);
        mapForOrderDetail.put("apiKey", Utils.getApiKey());

        NetWorkUtil.startNetWord(requestJsonData, postUrl, listener);
    }

    /**
     * 下载盘点单下的任务
     *
     * @param orderId        盘点单ID
     * @param isPageable     是否启用分页，默认为true，启用分页
     * @param currentPage    当前页码，默认为第1页
     * @param recordsPerPage 每页记录数，默认每页显示20行记录
     */
    public void downLoadTaskList(String orderId, boolean isPageable, int currentPage, int recordsPerPage, OnLoadFinishListener listener) throws Exception {
        Map<String, String> mapForOrderDetail = new HashMap<>();
        String method = "api/takeStockOrder/" + orderId + "/task/list";
        String postUrl = Utils.apiUrl + method;

        ForTaskOrderlist forTaskOrderlist = new ForTaskOrderlist();
        forTaskOrderlist.isPageable = isPageable;
        forTaskOrderlist.currentPage = currentPage;
        forTaskOrderlist.recordsPerPage = recordsPerPage;

        String requestJsonData = new Gson().toJson(forTaskOrderlist);

        mapForOrderDetail.put("postUrl", postUrl);
        mapForOrderDetail.put("requestJsonData", requestJsonData);
        mapForOrderDetail.put("apiKey", Utils.getApiKey());

        NetWorkUtil.startNetWord(requestJsonData, postUrl, listener);
    }
}
