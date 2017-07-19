package com.lide.app.bean.JsonToBean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lubin on 2016/8/2.
 */
public class EPCDiff {

    /**
     * currentPage : 1
     * data : [{"barcode":"001","brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","epc":"821500000399","qty":1,"skuName":"001发"},{"barcode":"001","brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","epc":"20140901000000000069","qty":1,"skuName":"001发"},{"barcode":"001","brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","epc":"821500002089","qty":1,"skuName":"001发"},{"barcode":"001","brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","epc":"821500003549","qty":1,"skuName":"001发"},{"barcode":"001","brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","epc":"821500007145","qty":1,"skuName":"001发"},{"barcode":"001","brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","epc":"000000000000000000000151","qty":1,"skuName":"001发"},{"barcode":"001","brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","epc":"821500006986","qty":1,"skuName":"001发"},{"barcode":"001","brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","epc":"821500007423","qty":1,"skuName":"001发"},{"barcode":"001","brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","epc":"821500005121","qty":1,"skuName":"001发"},{"barcode":"001","brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","epc":"821500004304","qty":1,"skuName":"001发"},{"barcode":"001","brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","epc":"821500000051","qty":1,"skuName":"001发"},{"barcode":"001","brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","epc":"821500006421","qty":1,"skuName":"001发"},{"barcode":"001","brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","epc":"20140901000000000020","qty":1,"skuName":"001发"},{"barcode":"001","brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","epc":"821500009301","qty":1,"skuName":"001发"},{"barcode":"001","brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","epc":"20140901000000000113","qty":1,"skuName":"001发"},{"barcode":"001","brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","epc":"20140901000000000161","qty":1,"skuName":"001发"},{"barcode":"001","brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","epc":"821500004774","qty":1,"skuName":"001发"},{"barcode":"001","brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","epc":"821500001137","qty":1,"skuName":"001发"},{"barcode":"001","brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","epc":"56785678567856785678","qty":1,"skuName":"001发"},{"barcode":"001","brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","epc":"821500004083","qty":1,"skuName":"001发"}]
     * hasNext : true
     * recordsPerPage : 20
     * totalPages : 2
     * totalRecords : 30
     */

    private int currentPage;
    private boolean hasNext;
    private int recordsPerPage;
    private int totalPages;
    private int totalRecords;
    /**
     * barcode : 001
     * brandCode : 001
     * brandId : AA8E732D-7C0E-4552-97F6-2F9C4327EBF3
     * brandName : TEST
     * epc : 821500000399
     * qty : 1
     * skuName : 001发
     */

    private List<DataBean> data;

    public static EPCDiff objectFromData(String str) {

        return new Gson().fromJson(str, EPCDiff.class);
    }

    public static EPCDiff objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), EPCDiff.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<EPCDiff> arrayEPCDiffFromData(String str) {

        Type listType = new TypeToken<ArrayList<EPCDiff>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<EPCDiff> arrayEPCDiffFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<EPCDiff>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public int getRecordsPerPage() {
        return recordsPerPage;
    }

    public void setRecordsPerPage(int recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String barcode;
        private String brandCode;
        private String brandId;
        private String brandName;
        private String epc;
        private int qty;
        private String skuName;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public static DataBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), DataBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<DataBean> arrayDataBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<DataBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public static List<DataBean> arrayDataBeanFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<DataBean>>() {
                }.getType();

                return new Gson().fromJson(jsonObject.getString(str), listType);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();


        }

        public String getBarcode() {
            return barcode;
        }

        public void setBarcode(String barcode) {
            this.barcode = barcode;
        }

        public String getBrandCode() {
            return brandCode;
        }

        public void setBrandCode(String brandCode) {
            this.brandCode = brandCode;
        }

        public String getBrandId() {
            return brandId;
        }

        public void setBrandId(String brandId) {
            this.brandId = brandId;
        }

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        public String getEpc() {
            return epc;
        }

        public void setEpc(String epc) {
            this.epc = epc;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public String getSkuName() {
            return skuName;
        }

        public void setSkuName(String skuName) {
            this.skuName = skuName;
        }
    }
}
