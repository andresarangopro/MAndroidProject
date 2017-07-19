package com.lide.app.bean.JsonToBean;

import com.google.gson.Gson;

/**
 * Created by lubin on 2017/1/16.
 */

public class ConfigList {

    /**
     * inboundAllowExceed : {"code":"BUSINESS_INBOUND_ALLOW_EXCEED","description":"入库允许超入","title":"入库允许超入","value1":"true","value2":null}
     * shopInboundMode : {"code":"SHOP_INBOUND_MODE","description":"门店收货方式（ONLY_SKU-按SKU收货，MIX-混合收货，PREBOXING-根据预装箱）","title":"门店收货方式","value1":"MIX","value2":null}
     * businessTakeStockFrom: {"code": "BUSINESS_TAKE_STOCK_FROM","description": "盘点单盘点账存来源","title": "盘点单盘点账存来源","value1": "LOCAL","value2": null}
     */

    private InboundAllowExceedBean inboundAllowExceed;
    private ShopInboundModeBean shopInboundMode;
    private BusinessTakeStockFrom businessTakeStockFrom;

    public BusinessTakeStockFrom getBusinessTakeStockFrom() {
        return businessTakeStockFrom;
    }

    public void setBusinessTakeStockFrom(BusinessTakeStockFrom businessTakeStockFrom) {
        this.businessTakeStockFrom = businessTakeStockFrom;
    }

    public static ConfigList objectFromData(String str) {

        return new Gson().fromJson(str, ConfigList.class);
    }

    public InboundAllowExceedBean getInboundAllowExceed() {
        return inboundAllowExceed;
    }

    public void setInboundAllowExceed(InboundAllowExceedBean inboundAllowExceed) {
        this.inboundAllowExceed = inboundAllowExceed;
    }

    public ShopInboundModeBean getShopInboundMode() {
        return shopInboundMode;
    }

    public void setShopInboundMode(ShopInboundModeBean shopInboundMode) {
        this.shopInboundMode = shopInboundMode;
    }

    public static class InboundAllowExceedBean {
        /**
         * code : BUSINESS_INBOUND_ALLOW_EXCEED
         * description : 入库允许超入
         * title : 入库允许超入
         * value1 : true
         * value2 : null
         */

        private String code;
        private String description;
        private String title;
        private String value1;
        private Object value2;

        public static InboundAllowExceedBean objectFromData(String str) {

            return new Gson().fromJson(str, InboundAllowExceedBean.class);
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getValue1() {
            return value1;
        }

        public void setValue1(String value1) {
            this.value1 = value1;
        }

        public Object getValue2() {
            return value2;
        }

        public void setValue2(Object value2) {
            this.value2 = value2;
        }
    }

    public static class ShopInboundModeBean {
        /**
         * code : SHOP_INBOUND_MODE
         * description : 门店收货方式（ONLY_SKU-按SKU收货，MIX-混合收货，PREBOXING-根据预装箱）
         * title : 门店收货方式
         * value1 : MIX
         * value2 : null
         */

        private String code;
        private String description;
        private String title;
        private String value1;
        private Object value2;

        public static ShopInboundModeBean objectFromData(String str) {

            return new Gson().fromJson(str, ShopInboundModeBean.class);
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getValue1() {
            return value1;
        }

        public void setValue1(String value1) {
            this.value1 = value1;
        }

        public Object getValue2() {
            return value2;
        }

        public void setValue2(Object value2) {
            this.value2 = value2;
        }
    }

    public static class BusinessTakeStockFrom {
        /**
         * code: BUSINESS_TAKE_STOCK_FROM,
         * description: 盘点单盘点账存来源",
         * title: 盘点单盘点账存来源,
         * value1: LOCAL,
         * value2: null
         */

        private String code;
        private String description;
        private String title;
        private String value1;
        private Object value2;

        public static InboundAllowExceedBean objectFromData(String str) {

            return new Gson().fromJson(str, InboundAllowExceedBean.class);
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getValue1() {
            return value1;
        }

        public void setValue1(String value1) {
            this.value1 = value1;
        }

        public Object getValue2() {
            return value2;
        }

        public void setValue2(Object value2) {
            this.value2 = value2;
        }
    }
}
