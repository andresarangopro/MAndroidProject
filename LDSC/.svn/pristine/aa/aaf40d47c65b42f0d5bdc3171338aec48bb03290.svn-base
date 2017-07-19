package com.lide.app.bean.BeanToJson;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by lubin on 2016/10/27.
 */

public class SaveInBounds {

    private List<DetailBean> detail;

    public static SaveInBounds objectFromData(String str) {

        return new Gson().fromJson(str, SaveInBounds.class);
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
         * operateQty : 0
         * deviceId :
         * tagValue :
         */

        private String cazeId;
        private String multiBarcodeId;
        private int operateQty;
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

        public int getOperateQty() {
            return operateQty;
        }

        public void setOperateQty(int operateQty) {
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
