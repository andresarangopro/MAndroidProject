package com.lide.app.bean.BeanToJson;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by lubin on 2016/12/16.
 */

public class UploadOutBound {

    /**
     * detail : [{"cazeId":"","multiBarcodeId":"","operateQty":0,"deviceId":"","tagValue":""}]
     * saveByOrder : false
     */

    private boolean saveByOrder;
    private List<DetailBean> detail;

    public static UploadOutBound objectFromData(String str) {

        return new Gson().fromJson(str, UploadOutBound.class);
    }

    public boolean isSaveByOrder() {
        return saveByOrder;
    }

    public void setSaveByOrder(boolean saveByOrder) {
        this.saveByOrder = saveByOrder;
    }

    public List<DetailBean> getDetail() {
        return detail;
    }

    public void setDetail(List<DetailBean> detail) {
        this.detail = detail;
    }

    public static class DetailBean {
        /**
         * cazeId :
         * multiBarcodeId :
         * operateQty : 0.0
         * deviceId :
         * tagValue :
         */

        private String cazeId;
        private String multiBarcodeId;
        private double operateQty;
        private String deviceId;
        private String tagValue;

        public static DetailBean objectFromData(String str) {

            return new Gson().fromJson(str, DetailBean.class);
        }

        public String getCazeId() {
            return cazeId;
        }

        public void setCazeId(String cazeId) {
            this.cazeId = cazeId;
        }

        public String getMultiBarcodeId() {
            return multiBarcodeId;
        }

        public void setMultiBarcodeId(String multiBarcodeId) {
            this.multiBarcodeId = multiBarcodeId;
        }

        public double getOperateQty() {
            return operateQty;
        }

        public void setOperateQty(double operateQty) {
            this.operateQty = operateQty;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getTagValue() {
            return tagValue;
        }

        public void setTagValue(String tagValue) {
            this.tagValue = tagValue;
        }
    }
}
