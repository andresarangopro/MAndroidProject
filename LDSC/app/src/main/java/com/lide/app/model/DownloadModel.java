package com.lide.app.model;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.lide.app.MApplication;
import com.lide.app.bean.BeanToJson.RequestForOrderDetail;
import com.lide.app.bean.JsonToBean.OrderDetail;
import com.lide.app.listener.OnLoadFinishListener;
import com.lide.app.persistence.util.DBOperator;
import com.lide.app.persistence.util.NetWorkUtil;
import com.lide.app.persistence.util.SPUtils;
import com.lide.app.persistence.util.Utils;
import com.lide.app.ui.inbound.InboundTransaction;
import com.lubin.bean.InBoundCase;
import com.lubin.bean.InBoundDetail;
import com.lubin.bean.InBoundOperate;
import com.lubin.bean.InBoundOrder;
import com.lubin.dao.InBoundCaseDao;
import com.lubin.dao.InBoundDetailDao;
import com.lubin.dao.InBoundOperateDao;
import com.lubin.dao.InBoundOrderDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by lubin on 2016/12/2.
 */

public class DownloadModel {

    /**
     *下载入库单的预装箱明细
     * @param orderId 单据ID
     */
    public void downloadIBOrderDetail(String orderId, OnLoadFinishListener listener) throws Exception {

        Map<String, String> mapForOrderDetail = new HashMap<>();
        String method = "api/inboundOrder/getPreboxing";
        String postUrl = Utils.apiUrl + method;

        RequestForOrderDetail requestForOrderDetail = new RequestForOrderDetail();
        requestForOrderDetail.orderByField = "barcode";
        requestForOrderDetail.orderByMethod = "ASC";
        requestForOrderDetail.orderId = orderId;
        requestForOrderDetail.isPageable = false;
        String requestJsonData = new Gson().toJson(requestForOrderDetail);

        mapForOrderDetail.put("postUrl", postUrl);
        mapForOrderDetail.put("requestJsonData", requestJsonData);
        mapForOrderDetail.put("apiKey", Utils.getApiKey());

        NetWorkUtil.startNetWord(new Gson().toJson(requestForOrderDetail), postUrl, listener);
    }

    /**
     * 将预装箱明细存入本地中
     * @param orderTitle 单头数据
     * @param result 预装箱明细
     * @return 存入本地是否成功
     * @throws Exception
     */
    public boolean saveIBOrderDetail(LinkedTreeMap orderTitle, String result) throws Exception {
        OrderDetail orderDetail = OrderDetail.objectFromData(result);
        if (orderDetail.getData().size() == 0) {
            return false;
        }

        DBOperator<InBoundOrderDao, InBoundOrder> orderDBOperator = InboundTransaction.getOrderDBOperator();
        DBOperator<InBoundCaseDao, InBoundCase> caseDBOperator = InboundTransaction.getCaseDBOperator();
        DBOperator<InBoundDetailDao, InBoundDetail> detailDBOperator = InboundTransaction.getDetailDBOperator();
        DBOperator<InBoundOperateDao, InBoundOperate> operateDBOperator = InboundTransaction.getOperateDBOperator();

        List<InBoundCase> cases = new LinkedList<>();
        List<InBoundDetail> links = new ArrayList<>();

        InBoundOrder order = new InBoundOrder();
        order.setCode(String.valueOf(orderTitle.get("code")));
        order.setOrderId(String.valueOf(orderTitle.get("id")));
        order.setDate(String.valueOf(orderTitle.get("created")));
        order.setFormWarehouseName(String.valueOf(orderTitle.get("fromWarehouseName")));
        order.setToWarehouseCode(String.valueOf(orderTitle.get("toWarehouseCode")));
        order.setDateAtLocation(new Date(System.currentTimeMillis()));
        order.setQty(0);
        order.setOperateQty(0);
        order.setInboundMode(String.valueOf(SPUtils.get(MApplication.getInstance(), "shopInboundModel", "PREBOXING")));
        switch (String.valueOf(orderTitle.get("status"))) {
            case "NEW":
                order.setStatus(0);
                break;
            case "PROCESSING":
                order.setStatus(1);
                break;
        }
        orderDBOperator.insertData(order);

        for (OrderDetail.DataBean data : orderDetail.getData()) {
            InBoundCase mCase = null;
            for (InBoundCase inBoundCase : cases) {
                if (inBoundCase.getCaseId().equals(data.getSourceCazeId())) {
                    mCase = inBoundCase;
                }
            }

            if (mCase == null) {
                mCase = new InBoundCase();
                mCase.setInBoundOrder(order);
                mCase.setCode(data.getSourceCazeCode());
                mCase.setCaseId(data.getSourceCazeId());
                mCase.setQty(0);
                mCase.setOperateQty(0);
                mCase.setStatus(0);
                cases.add(mCase);
                caseDBOperator.insertData(mCase);
            }

            InBoundDetail mDetail = null;
            for (InBoundDetail link : links) {
                if (link.getBarcode().equals(data.getBarcode())
                        && link.getInBoundCase().getCode().equals(data.getSourceCazeCode())) {
                    mDetail = link;
                }
            }

            if (mDetail == null) {
                mDetail = new InBoundDetail();
                mDetail.setInBoundOrder(order);
                mDetail.setInBoundCase(mCase);
                mDetail.setBarcode(data.getBarcode());
                mDetail.setQty(0);
                mDetail.setDate(new Date(System.currentTimeMillis()));
                mDetail.setSkuName(data.getSkuName());
                mDetail.setOperateQty(0);
                links.add(mDetail);
                detailDBOperator.insertData(mDetail);
            }

            if (data.getTagValue() != null && !data.getTagValue().toString().equals("")) {
                InBoundOperate inBoundOperate = new InBoundOperate();
                inBoundOperate.setEpc(data.getTagValue().toString());
                inBoundOperate.setIsUpload(false);
                inBoundOperate.setBarcode(data.getBarcode());
                inBoundOperate.setQty(1);
                inBoundOperate.setOperateQty(0);
                inBoundOperate.setInBoundDetail(mDetail);
                inBoundOperate.setInBoundOrder(order);
                inBoundOperate.setInBoundCase(mCase);
                operateDBOperator.insertData(inBoundOperate);
            } else {
                InBoundOperate inBoundOperate = new InBoundOperate();
                inBoundOperate.setIsUpload(false);
                inBoundOperate.setQty(data.getQty());
                inBoundOperate.setBarcode(data.getBarcode());
                inBoundOperate.setOperateQty(0);
                inBoundOperate.setInBoundDetail(mDetail);
                inBoundOperate.setInBoundOrder(order);
                inBoundOperate.setInBoundCase(mCase);
                operateDBOperator.insertData(inBoundOperate);
            }

            order.setQty(order.getQty() + data.getQty());
            mCase.setQty(mCase.getQty() + data.getQty());
            mDetail.setQty(mDetail.getQty() + data.getQty());

            order.update();
            mCase.update();
            mDetail.update();

        }
        return true;
    }
}
