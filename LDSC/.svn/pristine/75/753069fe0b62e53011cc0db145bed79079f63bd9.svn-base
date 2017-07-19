package com.lide.app.bean.JsonToBean;

import com.google.gson.Gson;

import java.util.List;

/**
 * @author DaiJiCheng
 * @time 2016/10/9  9:48
 * @desc ${TODD}
 */
public class OrderDetail {

    private int currentPage;
    private boolean hasNext;
    private int recordsPerPage;
    private int totalPages;
    private int totalRecords;
    /**
     * barcode : 116209021200240829001
     * businessOrderCode : INSH70016000002
     * businessOrderId : df7f8fe2-3679-4273-8a50-0b7987f1e01c
     * businessOrderType : IN
     * created : 2016-09-14 13:51:27.977
     * exceptionTag : false
     * id : 0004B426-D03A-4506-AE54-D8F5739A6701
     * modified : 2016-09-14 13:51:27.977
     * operateQty : 0
     * qty : 2
     * skuId : 8fa2d894-7816-4801-a109-f51853435014
     * skuName : 无袖连衣裙&彩色&S
     * sourceCazeCode : OTSYJ00116000002*01
     * sourceCazeId : 4fc6a184-dedc-47f2-a9ef-159d381f215e
     * tagId : null
     * tagValue : null
     * version : 0
     */

    private List<DataBean> data;

    public static OrderDetail objectFromData(String str) {

        return new Gson().fromJson(str, OrderDetail.class);
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
        private String businessOrderCode;
        private String businessOrderId;
        private String businessOrderType;
        private String created;
        private boolean exceptionTag;
        private String id;
        private String modified;
        private int operateQty;
        private int qty;
        private String skuId;
        private String skuName;
        private String sourceCazeCode;
        private String sourceCazeId;
        private Object tagId;
        private Object tagValue;
        private int version;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public String getBarcode() {
            return barcode;
        }

        public void setBarcode(String barcode) {
            this.barcode = barcode;
        }

        public String getBusinessOrderCode() {
            return businessOrderCode;
        }

        public void setBusinessOrderCode(String businessOrderCode) {
            this.businessOrderCode = businessOrderCode;
        }

        public String getBusinessOrderId() {
            return businessOrderId;
        }

        public void setBusinessOrderId(String businessOrderId) {
            this.businessOrderId = businessOrderId;
        }

        public String getBusinessOrderType() {
            return businessOrderType;
        }

        public void setBusinessOrderType(String businessOrderType) {
            this.businessOrderType = businessOrderType;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public boolean isExceptionTag() {
            return exceptionTag;
        }

        public void setExceptionTag(boolean exceptionTag) {
            this.exceptionTag = exceptionTag;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getModified() {
            return modified;
        }

        public void setModified(String modified) {
            this.modified = modified;
        }

        public int getOperateQty() {
            return operateQty;
        }

        public void setOperateQty(int operateQty) {
            this.operateQty = operateQty;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
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

        public String getSourceCazeCode() {
            return sourceCazeCode;
        }

        public void setSourceCazeCode(String sourceCazeCode) {
            this.sourceCazeCode = sourceCazeCode;
        }

        public String getSourceCazeId() {
            return sourceCazeId;
        }

        public void setSourceCazeId(String sourceCazeId) {
            this.sourceCazeId = sourceCazeId;
        }

        public Object getTagId() {
            return tagId;
        }

        public void setTagId(Object tagId) {
            this.tagId = tagId;
        }

        public Object getTagValue() {
            return tagValue;
        }

        public void setTagValue(Object tagValue) {
            this.tagValue = tagValue;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }
    }
}
