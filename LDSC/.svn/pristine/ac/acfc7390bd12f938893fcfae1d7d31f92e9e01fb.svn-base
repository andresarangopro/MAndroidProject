package com.lide.app.bean.JsonToBean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lubin on 2016/7/29.
 */
public class    ProductDiff {

    /**
     * currentPage : 1
     * data : [{"brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","countQty":0,"diffQty":-8,"productCode":"00000048","productName":"Ochirly欧时力绣花两件套长款连衣裙 ","snapQty":8},{"brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","countQty":0,"diffQty":-1,"productCode":"00000175","productName":"iittala魔幻森林蓝色马克杯","snapQty":1},{"brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","countQty":0,"diffQty":2,"productCode":"00012796","productName":"男人床位","snapQty":-2},{"brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","countQty":2,"diffQty":2,"productCode":"00012805","productName":"艾扬格瑜伽","snapQty":0},{"brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","countQty":0,"diffQty":-1,"productCode":"0004151741","productName":"衬衫","snapQty":1},{"brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","countQty":1,"diffQty":1,"productCode":"00048642","productName":"行为经济学讲义:演化论的视角","snapQty":0},{"brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","countQty":0,"diffQty":3,"productCode":"00054008","productName":"加德纳艺术通史","snapQty":-3},{"brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","countQty":0,"diffQty":-2,"productCode":"00064213","productName":"MVT斜摆长裙灰色M","snapQty":2},{"brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","countQty":0,"diffQty":-1,"productCode":"00064544","productName":"Cote&Ciel SPREE-13寸尼龙信使包 黑","snapQty":1},{"brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","countQty":0,"diffQty":-1,"productCode":"00064734","productName":"jeancard 15W杭州纸胶带/千岛湖","snapQty":1},{"brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","countQty":2,"diffQty":2,"productCode":"00083187","productName":"ApmMonaco RockChic系列戒指52","snapQty":0},{"brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","countQty":1,"diffQty":14,"productCode":"001","productName":"上衣","snapQty":-13},{"brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","countQty":1,"diffQty":-6,"productCode":"002","productName":"裤子","snapQty":7},{"brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","countQty":0,"diffQty":-1,"productCode":"003","productName":"aaa","snapQty":1},{"brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","countQty":0,"diffQty":-1,"productCode":"004","productName":"004","snapQty":1},{"brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","countQty":9,"diffQty":6,"productCode":"1101083001","productName":"男衬衫","snapQty":3},{"brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","countQty":1,"diffQty":8,"productCode":"1101083007","productName":"男衬衫","snapQty":-7},{"brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","countQty":6,"diffQty":42,"productCode":"1101084061","productName":"男衬衫","snapQty":-36},{"brandCode":"001","brandId":"AA8E732D-7C0E-4552-97F6-2F9C4327EBF3","brandName":"TEST","countQty":94,"diffQty":94,"productCode":"2701152005","productName":"衬衫","snapQty":0}]
     * hasNext : false
     * recordsPerPage : 20
     * totalPages : 1
     * totalRecords : 19
     */

    private int currentPage;
    private boolean hasNext;
    private int recordsPerPage;
    private int totalPages;
    private int totalRecords;
    /**
     * brandCode : 001
     * brandId : AA8E732D-7C0E-4552-97F6-2F9C4327EBF3
     * brandName : TEST
     * countQty : 0
     * diffQty : -8
     * productCode : 00000048
     * productName : Ochirly欧时力绣花两件套长款连衣裙
     * snapQty : 8
     */

    private List<DataBean> data;

    public static ProductDiff objectFromData(String str) {

        return new Gson().fromJson(str, ProductDiff.class);
    }

    public static ProductDiff objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), ProductDiff.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<ProductDiff> arrayProductDiffOrderFromData(String str) {

        Type listType = new TypeToken<ArrayList<ProductDiff>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<ProductDiff> arrayProductDiffOrderFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<ProductDiff>>() {
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
        private String brandCode;
        private String brandId;
        private String brandName;
        private int countQty;
        private int diffQty;
        private String productCode;
        private String productName;
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

        public String getProductCode() {
            return productCode;
        }

        public void setProductCode(String productCode) {
            this.productCode = productCode;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
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
                    "brandCode='" + brandCode + '\'' +
                    ", brandId='" + brandId + '\'' +
                    ", brandName='" + brandName + '\'' +
                    ", countQty=" + countQty +
                    ", diffQty=" + diffQty +
                    ", productCode='" + productCode + '\'' +
                    ", productName='" + productName + '\'' +
                    ", snapQty=" + snapQty +
                    '}';
        }
    }
}
