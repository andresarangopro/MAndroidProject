package com.lide.app.model;

import com.google.gson.Gson;
import com.lide.app.bean.BeanToJson.Binding;
import com.lide.app.bean.BeanToJson.EPCListForUpload;
import com.lide.app.bean.BeanToJson.SaveInBounds;
import com.lide.app.bean.BeanToJson.UploadOutBound;
import com.lide.app.listener.OnLoadFinishListener;
import com.lide.app.persistence.util.NetWorkUtil;
import com.lide.app.persistence.util.Utils;
import com.lubin.bean.InBoundOperate;
import com.lubin.bean.OutBoundOperate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lubin on 2016/11/26.
 */

public class UploadModel {

    public UploadModel() {

    }

    /**
     * 绑定操作
     * @param tags 未绑定标签集合
     * @param mutilBarcodeId 多条码ID
     * @param listener 回调
     * @throws Exception
     */
    public void binding(List<String> tags, String mutilBarcodeId, final OnLoadFinishListener listener) throws Exception {
        String apiMethod = "api/skuTag/binding";
        String postUrl = Utils.apiUrl + apiMethod;

        Binding binding = new Binding();
        binding.setBeforeBindingOperationsEnum("NONE");
        binding.setMutilBarcodeId(mutilBarcodeId);
        binding.setTags(tags);
        String requestJsonData = new Gson().toJson(binding);

        NetWorkUtil.startNetWord(requestJsonData, postUrl, listener);
    }

    /**
     * 上传入库操作明细
     * @param orderId 后端单号id
     * @param deviceId 设备id
     * @param operates 操作明细
     * @param listener 回调
     * @throws Exception
     */
    public void uploadInBoundOperates(String orderId, String deviceId, List<InBoundOperate> operates, OnLoadFinishListener listener) throws Exception {
        String doMethod = "api/inboundOrder/" + orderId + "/skuAndTags/batch-save";
        String postUrl = Utils.apiUrl + doMethod;

        SaveInBounds saveInBounds = new SaveInBounds();
        List<SaveInBounds.DetailBean> detailBeanList = new ArrayList<>();
        for (InBoundOperate inBoundOperate : operates) {
            if (inBoundOperate.getIsUpload()) continue;
            SaveInBounds.DetailBean detailBean = new SaveInBounds.DetailBean();
            detailBean.setCazeId(inBoundOperate.getInBoundCase().getCaseId());
            detailBean.setDeviceId(deviceId);
            detailBean.setOperateQty(inBoundOperate.getOperateQty());
            if (inBoundOperate.getEpc() == null) {
                if (inBoundOperate.getMultiBarcodeId() == null) continue;
                detailBean.setMultiBarcodeId(inBoundOperate.getMultiBarcodeId());
            } else {
                if (inBoundOperate.getOperateQty() == 0) continue;
                detailBean.setTagValue(inBoundOperate.getEpc());
            }
            detailBeanList.add(detailBean);
        }
        saveInBounds.setDetail(detailBeanList);

        String requestJsonData = new Gson().toJson(saveInBounds);

        NetWorkUtil.startNetWord(requestJsonData, postUrl, listener);
    }

    /**
     * 批量保存出库SKU和标签
     *
     * @param orderId  出库单ID
     * @param operates 单据SKU和标签明细
     */
    public void uploadOutBoundOperates(String orderId, List<OutBoundOperate> operates, OnLoadFinishListener listener) throws Exception {
        String doMethod = "api/outboundOrder/" + orderId + "/skuAndTags/batch-save";
        String postUrl = Utils.apiUrl + doMethod;

        UploadOutBound uploadOutBound = new UploadOutBound();
        List<UploadOutBound.DetailBean> list = new ArrayList<>();
        for (OutBoundOperate operate : operates) {
            UploadOutBound.DetailBean detailBean = new UploadOutBound.DetailBean();
            detailBean.setDeviceId(operate.getDeviceId());
            if (operate.getBarcode() != null) {
                detailBean.setOperateQty(operate.getOperateQty());
                detailBean.setMultiBarcodeId(operate.getMultiBarcodeId());
            } else {
                detailBean.setTagValue(operate.getTagValue());
                detailBean.setOperateQty(operate.getOperateQty());
            }
            list.add(detailBean);
        }
        uploadOutBound.setDetail(list);
        uploadOutBound.setSaveByOrder(true);

        String requestJsonData = null;
        try {
            requestJsonData = new Gson().toJson(uploadOutBound);
        } catch (Exception e) {
            e.printStackTrace();
        }

        NetWorkUtil.startNetWord(requestJsonData, postUrl, listener);
    }

    /**
     * 上传盘点单epc明细
     * @param collects epc集合
     * @param takeOrderId 盘点单id
     * @param taskCodeID 盘点任务id
     * @param listener 回调
     */
    public void uploadTakeStockCollects(List<String> collects, String takeOrderId, String taskCodeID, OnLoadFinishListener listener) {
        String apiMethod = "api/takeStockOrder/" + takeOrderId + "/task/" + taskCodeID + "/skuTag/append";
        String url = Utils.apiUrl + apiMethod;

        EPCListForUpload forUpload = new EPCListForUpload();

        forUpload.tagList.addAll(collects);

        String requestJsonData = new Gson().toJson(forUpload);

        NetWorkUtil.startNetWord(requestJsonData, url, listener);
    }

}
