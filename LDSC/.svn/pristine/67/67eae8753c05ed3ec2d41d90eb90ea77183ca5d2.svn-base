package com.lide.app.model;

import android.extend.util.DateUtils;

import com.google.gson.Gson;
import com.lide.app.bean.BeanToJson.ForTaskOrderlist;
import com.lide.app.bean.JsonToBean.UR.OutBoundBarcodeListBean;
import com.lide.app.bean.JsonToBean.UR.OutBoundOrderListBean;
import com.lide.app.listener.OnLoadFinishListener;
import com.lide.app.persistence.util.DBOperator;
import com.lide.app.persistence.util.NetWorkUtil;
import com.lide.app.persistence.util.Utils;
import com.lide.app.ui.outbound.createOrder.OutboundTransaction;
import com.lubin.bean.OutBoundDetail;
import com.lubin.bean.OutBoundOperate;
import com.lubin.bean.OutBoundOrder;
import com.lubin.dao.OutBoundDetailDao;
import com.lubin.dao.OutBoundOperateDao;
import com.lubin.dao.OutBoundOrderDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by HKR on 2017/3/7.
 */

public class OutBoundModel {

    /**
     * 出库单单头列表
     *
     * @param isPageable     是否启用分页，默认为true，启用分页
     * @param currentPage    当前页码，默认为第1页
     * @param recordsPerPage 每页记录数，默认每页显示20行记录
     */
    public void getOutBoundOrderList(boolean isPageable, int currentPage, int recordsPerPage, String code, OnLoadFinishListener listener) throws Exception {
        Map<String, String> mapForOrderDetail = new HashMap<>();
        String method = "api/outboundOrder/list";
        String postUrl = Utils.apiUrl + method;

        ForTaskOrderlist forTaskOrderlist = new ForTaskOrderlist();
        forTaskOrderlist.isPageable = isPageable;
        forTaskOrderlist.currentPage = currentPage;
        forTaskOrderlist.recordsPerPage = recordsPerPage;
        forTaskOrderlist.code = "%" + code + "%";
        String requestJsonData = new Gson().toJson(forTaskOrderlist);

        mapForOrderDetail.put("postUrl", postUrl);
        mapForOrderDetail.put("requestJsonData", requestJsonData);
        mapForOrderDetail.put("apiKey", Utils.getApiKey());

        NetWorkUtil.startNetWord(requestJsonData, postUrl, listener);
    }

    /**
     * 获取出库单
     *
     * @param outboundOrderId 出库单ID
     */
    public void getOutBoundOrder(String outboundOrderId, OnLoadFinishListener listener) throws Exception {
        Map<String, String> mapForOrderDetail = new HashMap<>();
        String method = "api/outboundOrder/" + outboundOrderId + "/get";
        String postUrl = Utils.apiUrl + method;

        ForTaskOrderlist forTaskOrderlist = new ForTaskOrderlist();

        String requestJsonData = new Gson().toJson(forTaskOrderlist);

        mapForOrderDetail.put("postUrl", postUrl);
        mapForOrderDetail.put("requestJsonData", requestJsonData);
        mapForOrderDetail.put("apiKey", Utils.getApiKey());

        NetWorkUtil.startNetWord(requestJsonData, postUrl, listener);
    }

    /**
     * 获取出库单条码明细
     *
     * @param outboundOrderId 出库单ID
     * @param isPageable      是否启用分页，默认为true，启用分页
     * @param currentPage     当前页码，默认为第1页
     * @param recordsPerPage  每页记录数，默认每页显示20行记录
     */
    public void getOutBoundOrderLines(String outboundOrderId, boolean isPageable, int currentPage, int recordsPerPage, OnLoadFinishListener listener) throws Exception {
        Map<String, String> mapForOrderDetail = new HashMap<>();
        String method = "/api/outboundOrder/" + outboundOrderId + "/lines/list";
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

    public boolean saveIBOrderDetail(OutBoundOrderListBean.DataBean dataBean, OutBoundBarcodeListBean result) throws Exception {
        if (result.getData().size() == 0) {
            return false;
        }

        DBOperator<OutBoundOrderDao, OutBoundOrder> orderDBOperator = OutboundTransaction.getOrderDBOperator();
        DBOperator<OutBoundDetailDao, OutBoundDetail> detailDBOperator = OutboundTransaction.getDetailDBOperator();
        DBOperator<OutBoundOperateDao, OutBoundOperate> operateDBOperator = OutboundTransaction.getOperateDBOperator();

        List<OutBoundDetail> links = new ArrayList<>();

        OutBoundOrder order = new OutBoundOrder();
        order.setCode(String.valueOf(dataBean.getCode()));
        order.setOrderId(String.valueOf(dataBean.getId()));
        order.setCreateTime(DateUtils.strToDateLong(dataBean.getCreated()));
        order.setToWarehouseName(String.valueOf(dataBean.getFromWarehouseName()));
        order.setIsCreate(false);
        int qty = 0;
        for (Iterator<OutBoundBarcodeListBean.DataBean> iterator = result.getData().iterator(); iterator.hasNext(); ) {
            OutBoundBarcodeListBean.DataBean next = iterator.next();
            qty += next.getQty();
        }
        order.setQty(qty);
        order.setOperateQty(0);
        if (dataBean.getStatus() != null && dataBean.getStatus().equals("NEW")) {
            order.setStatus("新单");
        } else order.setStatus("处理中");

        orderDBOperator.insertData(order);

        for (Iterator<OutBoundBarcodeListBean.DataBean> iterator = result.getData().iterator(); iterator.hasNext(); ) {
            OutBoundBarcodeListBean.DataBean bean = iterator.next();

            OutBoundDetail mDetail = new OutBoundDetail();
            mDetail.setBarcode(bean.getBarcode());
            mDetail.setOperateQty(0);
            mDetail.setQty(bean.getQty());
            mDetail.setRefreshTime(new Date(System.currentTimeMillis()));
            mDetail.setSkuName(bean.getSkuName());
            mDetail.setOutBoundOrderId(order.getId());
            links.add(mDetail);
            detailDBOperator.insertData(mDetail);

            OutBoundOperate outBoundOperate = new OutBoundOperate();
            outBoundOperate.setBarcode(bean.getBarcode());
            outBoundOperate.setTagValue("");
            outBoundOperate.setDeviceId("");
            outBoundOperate.setOperateQty(0);
            outBoundOperate.setMultiBarcodeId("");
            outBoundOperate.setIsUpload(false);
            outBoundOperate.setOutBoundOrderId(order.getId());
            outBoundOperate.setOutBoundDetailId(mDetail.getId());
            operateDBOperator.insertData(outBoundOperate);
        }
        return true;
    }
}
