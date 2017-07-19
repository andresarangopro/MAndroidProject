package com.lide.app.bean.JsonToBean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by lubin on 2016/11/3.
 */

public class EpcList {

    /**
     * currentPage : 1
     * data : [{"allowUnbind":false,"alreadyBinded":true,"barcode":"1234567893210","created":"2016-03-04 13:34:41.173","disableAlert":false,"epc":"EPC22701152005490119","flowNumber":"0","id":"00002817-BB6F-41F2-B80C-9451EEB96622","inWarehouse":true,"isDeleted":true,"modified":"2016-03-04 13:34:41.173","productCode":"2701152005","productId":"d1dfef98-6c63-49c8-8658-56b6500e89fa","productName":"衬衫","recyclable":true,"skuId":"00001913-c706-4f8f-af98-0d491c8df18a","skuName":"衬衫&宽条灰/蓝浅灰&48/185","tid":"384aac7a-c3e7-450b-b95b-25bd9760a481","uniqueCode":null,"version":1},{"allowUnbind":false,"alreadyBinded":true,"barcode":"1234567893210","created":"2016-03-03 11:28:20.633","disableAlert":false,"epc":"EPC00064734","flowNumber":"0","id":"00006DAD-6CDD-4E13-BD83-2CEE6A6B3913","inWarehouse":true,"isDeleted":true,"modified":"2016-03-03 11:28:20.633","productCode":"2701152005","productId":"d1dfef98-6c63-49c8-8658-56b6500e89fa","productName":"衬衫","recyclable":true,"skuId":"00001913-c706-4f8f-af98-0d491c8df18a","skuName":"衬衫&宽条灰/蓝浅灰&48/185","tid":"TID00064734","uniqueCode":null,"version":1},{"allowUnbind":false,"alreadyBinded":true,"barcode":"1234567893210","created":"2016-03-03 11:28:20.633","disableAlert":false,"epc":"EPC2701152005931103","flowNumber":"0","id":"0000D758-DDDA-4972-8B8A-3E2FB3336633","inWarehouse":true,"isDeleted":false,"modified":"2016-03-03 11:28:20.633","productCode":"2701152005","productId":"d1dfef98-6c63-49c8-8658-56b6500e89fa","productName":"衬衫","recyclable":true,"skuId":"00001913-c706-4f8f-af98-0d491c8df18a","skuName":"衬衫&宽条灰/蓝浅灰&48/185","tid":"TID2701152005931103","uniqueCode":null,"version":1},{"allowUnbind":true,"alreadyBinded":true,"barcode":"1234567893210","created":"2016-03-03 11:28:20.633","disableAlert":false,"epc":"EPC00047791","flowNumber":"0","id":"0000E6CF-0C25-4374-BF5D-D3A12D4A8B42","inWarehouse":false,"isDeleted":false,"modified":"2016-03-03 11:28:20.633","productCode":"2701152005","productId":"d1dfef98-6c63-49c8-8658-56b6500e89fa","productName":"衬衫","recyclable":true,"skuId":"00001913-c706-4f8f-af98-0d491c8df18a","skuName":"衬衫&宽条灰/蓝浅灰&48/185","tid":"TID00047791","uniqueCode":null,"version":1},{"allowUnbind":true,"alreadyBinded":true,"barcode":"1234567893210","created":"2016-03-30 14:05:41.127","disableAlert":false,"epc":"123","flowNumber":null,"id":"24d3facc-fd3e-4465-b134-e8c05d2d51a4","inWarehouse":false,"isDeleted":false,"modified":"2016-03-30 14:05:41.127","productCode":"2701152005","productId":"d1dfef98-6c63-49c8-8658-56b6500e89fa","productName":"衬衫","recyclable":true,"skuId":"00001913-c706-4f8f-af98-0d491c8df18a","skuName":"衬衫&宽条灰/蓝浅灰&48/185","tid":null,"uniqueCode":"33333","version":1}]
     * hasNext : false
     * recordsPerPage : 20
     * totalPages : 1
     * totalRecords : 5
     */

    private int currentPage;
    private boolean hasNext;
    private int recordsPerPage;
    private int totalPages;
    private int totalRecords;
    /**
     * allowUnbind : false
     * alreadyBinded : true
     * barcode : 1234567893210
     * created : 2016-03-04 13:34:41.173
     * disableAlert : false
     * epc : EPC22701152005490119
     * flowNumber : 0
     * id : 00002817-BB6F-41F2-B80C-9451EEB96622
     * inWarehouse : true
     * isDeleted : true
     * modified : 2016-03-04 13:34:41.173
     * productCode : 2701152005
     * productId : d1dfef98-6c63-49c8-8658-56b6500e89fa
     * productName : 衬衫
     * recyclable : true
     * skuId : 00001913-c706-4f8f-af98-0d491c8df18a
     * skuName : 衬衫&宽条灰/蓝浅灰&48/185
     * tid : 384aac7a-c3e7-450b-b95b-25bd9760a481
     * uniqueCode : null
     * version : 1
     */

    private List<DataBean> data;

    public static EpcList objectFromData(String str) {

        return new Gson().fromJson(str, EpcList.class);
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
        private String epc;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public String getEpc() {
            return epc;
        }

        public void setEpc(String epc) {
            this.epc = epc;
        }
    }
}
