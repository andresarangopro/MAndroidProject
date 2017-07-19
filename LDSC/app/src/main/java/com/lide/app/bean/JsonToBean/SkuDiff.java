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
public class SkuDiff {

    /**
     * currentPage : 1
     * data : [{"barcode":"1101084061202101","brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","countQty":0,"diffQty":36,"skuName":"男衬衫&素隐纹白浅&38/165","snapQty":-36},{"barcode":"1101084061202104","brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","countQty":6,"diffQty":6,"skuName":"男衬衫&素隐纹白浅&39/175","snapQty":0}]
     * hasNext : false
     * recordsPerPage : 20
     * totalPages : 1
     * totalRecords : 2
     */

    private int currentPage;
    private boolean hasNext;
    private int recordsPerPage;
    private int totalPages;
    private int totalRecords;
    /**
     * barcode : 1101084061202101
     * brandCode : 001
     * brandId : AA8E732D-7C0E-4552-97F6-2F9C4327EBF3
     * brandName : TEST
     * countQty : 0
     * diffQty : 36
     * skuName : 男衬衫&素隐纹白浅&38/165
     * snapQty : -36
     */

    private List<DataBean> data;

    public static SkuDiff objectFromData(String str) {

        return new Gson().fromJson(str, SkuDiff.class);
    }

    public static SkuDiff objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), SkuDiff.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<SkuDiff> arraySkuDiffFromData(String str) {

        Type listType = new TypeToken<ArrayList<SkuDiff>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<SkuDiff> arraySkuDiffFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<SkuDiff>>() {
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
        private int countQty;
        private int diffQty;
        private String skuName;
        private int snapQty;

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

        public int getCountQty() {
            return countQty;
        }

        public void setCountQty(int countQty) {
            this.countQty = countQty;
        }

        public int getDiffQty() {
            return diffQty;
        }

        public void setDiffQty(int diffQty) {
            this.diffQty = diffQty;
        }

        public String getSkuName() {
            return skuName;
        }

        public void setSkuName(String skuName) {
            this.skuName = skuName;
        }

        public int getSnapQty() {
            return snapQty;
        }

        public void setSnapQty(int snapQty) {
            this.snapQty = snapQty;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "barcode='" + barcode + '\'' +
                    ", brandCode='" + brandCode + '\'' +
                    ", brandId='" + brandId + '\'' +
                    ", brandName='" + brandName + '\'' +
                    ", countQty=" + countQty +
                    ", diffQty=" + diffQty +
                    ", skuName='" + skuName + '\'' +
                    ", snapQty=" + snapQty +
                    '}';
        }
    }
}
