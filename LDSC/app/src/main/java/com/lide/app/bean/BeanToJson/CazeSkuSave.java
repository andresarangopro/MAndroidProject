package com.lide.app.bean.BeanToJson;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by lubin on 2016/10/24.
 */

public class CazeSkuSave {

    /**
     * cazeId :
     * deviceId :
     * multibarcodeId :
     * qty : 0
     */

    private List<CazeSkuDetailBean> cazeSkuDetail;

    public static CazeSkuSave objectFromData(String str) {

        return new Gson().fromJson(str, CazeSkuSave.class);
    }

    public List<CazeSkuDetailBean> getCazeSkuDetail() {
        return cazeSkuDetail;
    }

    public void setCazeSkuDetail(List<CazeSkuDetailBean> cazeSkuDetail) {
        this.cazeSkuDetail = cazeSkuDetail;
    }

    public static class CazeSkuDetailBean {
        private String cazeId;
        private String deviceId;
        private String multibarcodeId;
        private int qty;

        public static CazeSkuDetailBean objectFromData(String str) {

            return new Gson().fromJson(str, CazeSkuDetailBean.class);
        }

        public String getCazeId() {
            return cazeId;
        }

        public void setCazeId(String cazeId) {
            this.cazeId = cazeId;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getMultibarcodeId() {
            return multibarcodeId;
        }

        public void setMultibarcodeId(String multibarcodeId) {
            this.multibarcodeId = multibarcodeId;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }
    }
}
