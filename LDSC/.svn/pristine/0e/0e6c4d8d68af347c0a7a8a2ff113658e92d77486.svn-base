package com.lide.app.bean.JsonToBean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by lubin on 2016/11/3.
 */

public class SkuList {

    /**
     * currentPage : 1
     * data : [{"barcode":"1234567893210","brandCode":null,"brandName":null,"created":"2015-12-09 18:19:51.193","currentPrice":0,"disable":false,"id":"00001913-c706-4f8f-af98-0d491c8df18a","imagePath":null,"imageThumbnailPath":null,"imageThumbnailUrl":null,"imageUrl":null,"modified":"2015-12-22 13:59:09.997","name":"衬衫&宽条灰/蓝浅灰&48/185","productCode":"2701152005","productColorCode":"775","productColorId":"14d86605-916c-4937-a6d9-188379de2bb7","productColorName":"宽条灰/蓝浅灰","productColorRgb":null,"productId":"d1dfef98-6c63-49c8-8658-56b6500e89fa","productName":"衬衫","productSizeCode":"122","productSizeId":"cf703025-0dc2-47a5-b997-4c81044f5d3f","productSizeName":"48/185","retailPrice":0,"sourceCode":"111111111111111","version":2}]
     * hasNext : true
     * recordsPerPage : 1
     * totalPages : 1678
     * totalRecords : 1678
     */

    private int currentPage;
    private boolean hasNext;
    private int recordsPerPage;
    private int totalPages;
    private int totalRecords;

    private List<DataBean> data;

    public static SkuList objectFromData(String str) {

        return new Gson().fromJson(str, SkuList.class);
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

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public String getBarcode() {
            return barcode;
        }

        public void setBarcode(String barcode) {
            this.barcode = barcode;
        }
    }
}
