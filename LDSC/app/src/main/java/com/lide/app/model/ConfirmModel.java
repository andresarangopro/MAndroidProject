package com.lide.app.model;

import com.lide.app.listener.OnLoadFinishListener;
import com.lide.app.persistence.util.NetWorkUtil;
import com.lide.app.persistence.util.Utils;

/**
 * Created by lubin on 2016/11/19.
 */

public class ConfirmModel {

    /**
     * 审核出库单
     *
     * @param orderId 出库单ID
     */
    public void confirmOutBoundOrder(String orderId, final OnLoadFinishListener listener) throws Exception {
        String apiMethod = "/api/outboundOrder/" + orderId + "/confirm";
        String postUrl = Utils.apiUrl + apiMethod;

        NetWorkUtil.startNetWord("{}", postUrl, listener);
    }

    /**
     * 审核入库单
     *
     * @param orderId 入库单ID
     */
    public void confirmInboundOrder(String orderId, final OnLoadFinishListener listener) throws Exception {
        String method = "api/inboundOrder/" + orderId + "/confirm";
        String postUrl = Utils.apiUrl + method;

        NetWorkUtil.startNetWord("{}", postUrl, listener);
    }

}
