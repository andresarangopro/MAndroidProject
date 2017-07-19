package com.lide.app.bean.JsonToBean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by lubin on 2016/10/22.
 */

public class EpcID {

    /**
     * currentPage : 1
     * data : [{"allowUnbind":false,"alreadyBinded":true,"barcode":"SE62051400106","created":"2016-10-21 14:42:59.293","disableAlert":false,"epc":"821500003400","flowNumber":null,"id":"e26f25a1-9999-4b9a-90d3-a904c94f5677","inWarehouse":true,"isDeleted":false,"modified":"2016-10-21 14:42:59.293","productCode":"SE620514","productId":"5112201b-5c5b-4521-ad00-9477158cf66a","productName":"女西装新款","recyclable":true,"skuId":"9e2a1ebb-c31e-4ad2-85ed-e4b2ce8c7789","skuName":"女西装新款&混色&L","tid":null,"uniqueCode":null,"version":1},{"allowUnbind":false,"alreadyBinded":true,"barcode":"SE62051400106","created":"2016-10-21 14:42:59.293","disableAlert":false,"epc":"821500004314","flowNumber":null,"id":"099abdd9-e7ce-4f20-9d33-0f6fcfb36582","inWarehouse":true,"isDeleted":false,"modified":"2016-10-21 14:42:59.293","productCode":"SE620514","productId":"5112201b-5c5b-4521-ad00-9477158cf66a","productName":"女西装新款","recyclable":true,"skuId":"9e2a1ebb-c31e-4ad2-85ed-e4b2ce8c7789","skuName":"女西装新款&混色&L","tid":null,"uniqueCode":null,"version":1},{"allowUnbind":false,"alreadyBinded":true,"barcode":"SE62051400106","created":"2016-10-21 14:42:59.293","disableAlert":false,"epc":"821500000470","flowNumber":null,"id":"478d9254-b974-4142-a695-83b7c4fdd359","inWarehouse":true,"isDeleted":false,"modified":"2016-10-21 14:42:59.293","productCode":"SE620514","productId":"5112201b-5c5b-4521-ad00-9477158cf66a","productName":"女西装新款","recyclable":true,"skuId":"9e2a1ebb-c31e-4ad2-85ed-e4b2ce8c7789","skuName":"女西装新款&混色&L","tid":null,"uniqueCode":null,"version":1},{"allowUnbind":false,"alreadyBinded":true,"barcode":"SE62051400103","created":"2016-10-21 14:45:25.797","disableAlert":false,"epc":"821500007338","flowNumber":null,"id":"f0661486-05d7-4e35-9d51-e9f18fee2394","inWarehouse":true,"isDeleted":false,"modified":"2016-10-21 14:45:25.797","productCode":"SE620514","productId":"5112201b-5c5b-4521-ad00-9477158cf66a","productName":"女西装新款","recyclable":true,"skuId":"1e37b539-88ae-4e7d-ab08-5df46e3a1d24","skuName":"女西装新款&混色&XS","tid":null,"uniqueCode":null,"version":1},{"allowUnbind":false,"alreadyBinded":true,"barcode":"SE62051400103","created":"2016-10-21 14:45:25.797","disableAlert":false,"epc":"821500006234","flowNumber":null,"id":"67c6613b-c3b2-48ef-87ee-6ab5368814bc","inWarehouse":true,"isDeleted":false,"modified":"2016-10-21 14:45:25.797","productCode":"SE620514","productId":"5112201b-5c5b-4521-ad00-9477158cf66a","productName":"女西装新款","recyclable":true,"skuId":"1e37b539-88ae-4e7d-ab08-5df46e3a1d24","skuName":"女西装新款&混色&XS","tid":null,"uniqueCode":null,"version":1},{"allowUnbind":false,"alreadyBinded":true,"barcode":"SE62051400103","created":"2016-10-21 14:45:25.797","disableAlert":false,"epc":"821500003366","flowNumber":null,"id":"daddfc79-1906-4db8-8b17-46da123ffc5a","inWarehouse":true,"isDeleted":false,"modified":"2016-10-21 14:45:25.797","productCode":"SE620514","productId":"5112201b-5c5b-4521-ad00-9477158cf66a","productName":"女西装新款","recyclable":true,"skuId":"1e37b539-88ae-4e7d-ab08-5df46e3a1d24","skuName":"女西装新款&混色&XS","tid":null,"uniqueCode":null,"version":1}]
     * hasNext : false
     * recordsPerPage : 20
     * totalPages : 1
     * totalRecords : 6
     */

    private int currentPage;
    private boolean hasNext;
    private int recordsPerPage;
    private int totalPages;
    private int totalRecords;
    /**
     * allowUnbind : false
     * alreadyBinded : true
     * barcode : SE62051400106
     * created : 2016-10-21 14:42:59.293
     * disableAlert : false
     * epc : 821500003400
     * flowNumber : null
     * id : e26f25a1-9999-4b9a-90d3-a904c94f5677
     * inWarehouse : true
     * isDeleted : false
     * modified : 2016-10-21 14:42:59.293
     * productCode : SE620514
     * productId : 5112201b-5c5b-4521-ad00-9477158cf66a
     * productName : 女西装新款
     * recyclable : true
     * skuId : 9e2a1ebb-c31e-4ad2-85ed-e4b2ce8c7789
     * skuName : 女西装新款&混色&L
     * tid : null
     * uniqueCode : null
     * version : 1
     */

    private List<DataBean> data;

    public static EpcID objectFromData(String str) {

        return new Gson().fromJson(str, EpcID.class);
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
        private boolean allowUnbind;
        private boolean alreadyBinded;
        private String barcode;
        private String created;
        private boolean disableAlert;
        private String epc;
        private Object flowNumber;
        private String id;
        private boolean inWarehouse;
        private boolean isDeleted;
        private String modified;
        private String productCode;
        private String productId;
        private String productName;
        private boolean recyclable;
        private String skuId;
        private String skuName;
        private Object tid;
        private Object uniqueCode;
        private int version;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public boolean isAllowUnbind() {
            return allowUnbind;
        }

        public void setAllowUnbind(boolean allowUnbind) {
            this.allowUnbind = allowUnbind;
        }

        public boolean isAlreadyBinded() {
            return alreadyBinded;
        }

        public void setAlreadyBinded(boolean alreadyBinded) {
            this.alreadyBinded = alreadyBinded;
        }

        public String getBarcode() {
            return barcode;
        }

        public void setBarcode(String barcode) {
            this.barcode = barcode;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public boolean isDisableAlert() {
            return disableAlert;
        }

        public void setDisableAlert(boolean disableAlert) {
            this.disableAlert = disableAlert;
        }

        public String getEpc() {
            return epc;
        }

        public void setEpc(String epc) {
            this.epc = epc;
        }

        public Object getFlowNumber() {
            return flowNumber;
        }

        public void setFlowNumber(Object flowNumber) {
            this.flowNumber = flowNumber;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isInWarehouse() {
            return inWarehouse;
        }

        public void setInWarehouse(boolean inWarehouse) {
            this.inWarehouse = inWarehouse;
        }

        public boolean isIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(boolean isDeleted) {
            this.isDeleted = isDeleted;
        }

        public String getModified() {
            return modified;
        }

        public void setModified(String modified) {
            this.modified = modified;
        }

        public String getProductCode() {
            return productCode;
        }

        public void setProductCode(String productCode) {
            this.productCode = productCode;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public boolean isRecyclable() {
            return recyclable;
        }

        public void setRecyclable(boolean recyclable) {
            this.recyclable = recyclable;
        }

        public String getSkuId() {
            return skuId;
        }

        public void setSkuId(String skuId) {
            this.skuId = skuId;
        }

        public String getSkuName() {
            return skuName;
        }

        public void setSkuName(String skuName) {
            this.skuName = skuName;
        }

        public Object getTid() {
            return tid;
        }

        public void setTid(Object tid) {
            this.tid = tid;
        }

        public Object getUniqueCode() {
            return uniqueCode;
        }

        public void setUniqueCode(Object uniqueCode) {
            this.uniqueCode = uniqueCode;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }
    }
}
