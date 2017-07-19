package com.lide.app.bean.JsonToBean;

import com.google.gson.Gson;

import java.util.List;

/**
 * @author DaiJiCheng
 * @time 2016/10/8  17:48
 * @desc ${TODD}
 */
public class OrderForId {

    /**
     * currentPage : 1
     * data : [{"businessAmount":0,"code":"20161015130701","confirmBusinessAmount":0,"confirmCostAmount":0,"confirmDateTime":"2016-10-17 09:42:49.827","confirmEmployeeCode":"ApiDefaultAdmin","confirmEmployeeId":"034E83FC-6932-43EC-90FF-D6B5363C751E","confirmEmployeeName":"系统内部管理员","confirmQty":40,"confirmRetailAmount":1277.7,"costAmount":0,"createEmployeeCode":"ApiDefaultAdmin","createEmployeeId":"034E83FC-6932-43EC-90FF-D6B5363C751E","createEmployeeName":"系统内部管理员","created":"2016-10-15 13:03:00.127","customFieldValue01":null,"customFieldValue02":null,"customFieldValue03":null,"customFieldValue04":null,"customFieldValue05":null,"customFieldValue06":null,"customFieldValue07":null,"customFieldValue08":null,"customFieldValue09":null,"customFieldValue10":null,"customFieldValue11":null,"customFieldValue12":null,"customFieldValue13":null,"customFieldValue14":null,"customFieldValue15":null,"description":"","fromBusinessUnitCode":"HAILY","fromBusinessUnitId":"a0cae75f-5b5b-40d2-8cac-bdd438ffacf7","fromBusinessUnitName":"hailyzhang","fromLocationCode":null,"fromLocationId":null,"fromLocationName":null,"fromWarehouseCode":"SH700","fromWarehouseId":"075938cd-2b8b-4787-9c36-b94efa672abb","fromWarehouseName":"SH700","hasSourceOrder":true,"id":"9623CBF7-DD26-43E2-AF34-B725F9203D44","lastModifyEmployeeCode":"ADMIN","lastModifyEmployeeId":"1232BB62-A074-43E9-B8CB-E36E8BFD76FD","lastModifyEmployeeName":"ADMIN","lastSyncDateTime":null,"makeDateTime":"2016-09-14 13:40:00","modified":"2016-10-17 09:42:47.880","operateBusinessAmount":0,"operateCostAmount":0,"operateQty":40,"operateRetailAmount":1277.7,"orderSubTypeCode":"PF","orderSubTypeId":"7289F561-8977-4CAE-B392-2AAB56C228FB","orderSubTypeName":"批发","qty":300,"retailAmount":11777,"sourceOrderCode":"HAILYAS16000011111","status":"FINISHED","statusCode":"FINISHED","statusDescription":"已完成","syncStatus":"NONE","syncStatusCode":"NONE","syncStatusDescription":"未回传","toLocationCode":null,"toLocationId":null,"toLocationName":null,"toWarehouseCode":"A","toWarehouseId":"9cc8b631-83c0-415d-94ff-168e619ad909","toWarehouseName":"A仓","version":8}]
     * hasNext : false
     * recordsPerPage : 20
     * totalPages : 1
     * totalRecords : 1
     */

    private int currentPage;
    private boolean hasNext;
    private int recordsPerPage;
    private int totalPages;
    private int totalRecords;
    /**
     * businessAmount : 0.0
     * code : 20161015130701
     * confirmBusinessAmount : 0.0
     * confirmCostAmount : 0.0
     * confirmDateTime : 2016-10-17 09:42:49.827
     * confirmEmployeeCode : ApiDefaultAdmin
     * confirmEmployeeId : 034E83FC-6932-43EC-90FF-D6B5363C751E
     * confirmEmployeeName : 系统内部管理员
     * confirmQty : 40.0
     * confirmRetailAmount : 1277.7
     * costAmount : 0.0
     * createEmployeeCode : ApiDefaultAdmin
     * createEmployeeId : 034E83FC-6932-43EC-90FF-D6B5363C751E
     * createEmployeeName : 系统内部管理员
     * created : 2016-10-15 13:03:00.127
     * customFieldValue01 : null
     * customFieldValue02 : null
     * customFieldValue03 : null
     * customFieldValue04 : null
     * customFieldValue05 : null
     * customFieldValue06 : null
     * customFieldValue07 : null
     * customFieldValue08 : null
     * customFieldValue09 : null
     * customFieldValue10 : null
     * customFieldValue11 : null
     * customFieldValue12 : null
     * customFieldValue13 : null
     * customFieldValue14 : null
     * customFieldValue15 : null
     * description :
     * fromBusinessUnitCode : HAILY
     * fromBusinessUnitId : a0cae75f-5b5b-40d2-8cac-bdd438ffacf7
     * fromBusinessUnitName : hailyzhang
     * fromLocationCode : null
     * fromLocationId : null
     * fromLocationName : null
     * fromWarehouseCode : SH700
     * fromWarehouseId : 075938cd-2b8b-4787-9c36-b94efa672abb
     * fromWarehouseName : SH700
     * hasSourceOrder : true
     * id : 9623CBF7-DD26-43E2-AF34-B725F9203D44
     * lastModifyEmployeeCode : ADMIN
     * lastModifyEmployeeId : 1232BB62-A074-43E9-B8CB-E36E8BFD76FD
     * lastModifyEmployeeName : ADMIN
     * lastSyncDateTime : null
     * makeDateTime : 2016-09-14 13:40:00
     * modified : 2016-10-17 09:42:47.880
     * operateBusinessAmount : 0.0
     * operateCostAmount : 0.0
     * operateQty : 40.0
     * operateRetailAmount : 1277.7
     * orderSubTypeCode : PF
     * orderSubTypeId : 7289F561-8977-4CAE-B392-2AAB56C228FB
     * orderSubTypeName : 批发
     * qty : 300.0
     * retailAmount : 11777.0
     * sourceOrderCode : HAILYAS16000011111
     * status : FINISHED
     * statusCode : FINISHED
     * statusDescription : 已完成
     * syncStatus : NONE
     * syncStatusCode : NONE
     * syncStatusDescription : 未回传
     * toLocationCode : null
     * toLocationId : null
     * toLocationName : null
     * toWarehouseCode : A
     * toWarehouseId : 9cc8b631-83c0-415d-94ff-168e619ad909
     * toWarehouseName : A仓
     * version : 8
     */

    private List<DataBean> data;

    public static OrderForId objectFromData(String str) {

        return new Gson().fromJson(str, OrderForId.class);
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
        private double businessAmount;
        private String code;
        private double confirmBusinessAmount;
        private double confirmCostAmount;
        private String confirmDateTime;
        private String confirmEmployeeCode;
        private String confirmEmployeeId;
        private String confirmEmployeeName;
        private double confirmQty;
        private double confirmRetailAmount;
        private double costAmount;
        private String createEmployeeCode;
        private String createEmployeeId;
        private String createEmployeeName;
        private String created;
        private Object customFieldValue01;
        private Object customFieldValue02;
        private Object customFieldValue03;
        private Object customFieldValue04;
        private Object customFieldValue05;
        private Object customFieldValue06;
        private Object customFieldValue07;
        private Object customFieldValue08;
        private Object customFieldValue09;
        private Object customFieldValue10;
        private Object customFieldValue11;
        private Object customFieldValue12;
        private Object customFieldValue13;
        private Object customFieldValue14;
        private Object customFieldValue15;
        private String description;
        private String fromBusinessUnitCode;
        private String fromBusinessUnitId;
        private String fromBusinessUnitName;
        private Object fromLocationCode;
        private Object fromLocationId;
        private Object fromLocationName;
        private String fromWarehouseCode;
        private String fromWarehouseId;
        private String fromWarehouseName;
        private String inboundMode;
        private boolean hasSourceOrder;
        private String id;
        private String lastModifyEmployeeCode;
        private String lastModifyEmployeeId;
        private String lastModifyEmployeeName;
        private Object lastSyncDateTime;
        private String makeDateTime;
        private String modified;
        private double operateBusinessAmount;
        private double operateCostAmount;
        private double operateQty;
        private double operateRetailAmount;
        private String orderSubTypeCode;
        private String orderSubTypeId;
        private String orderSubTypeName;
        private double qty;
        private double retailAmount;
        private String sourceOrderCode;
        private String status;
        private String statusCode;
        private String statusDescription;
        private String syncStatus;
        private String syncStatusCode;
        private String syncStatusDescription;
        private Object toLocationCode;
        private Object toLocationId;
        private Object toLocationName;
        private String toWarehouseCode;
        private String toWarehouseId;
        private String toWarehouseName;
        private int version;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public double getBusinessAmount() {
            return businessAmount;
        }

        public void setBusinessAmount(double businessAmount) {
            this.businessAmount = businessAmount;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public double getConfirmBusinessAmount() {
            return confirmBusinessAmount;
        }

        public void setConfirmBusinessAmount(double confirmBusinessAmount) {
            this.confirmBusinessAmount = confirmBusinessAmount;
        }

        public double getConfirmCostAmount() {
            return confirmCostAmount;
        }

        public void setConfirmCostAmount(double confirmCostAmount) {
            this.confirmCostAmount = confirmCostAmount;
        }

        public String getConfirmDateTime() {
            return confirmDateTime;
        }

        public void setConfirmDateTime(String confirmDateTime) {
            this.confirmDateTime = confirmDateTime;
        }

        public String getConfirmEmployeeCode() {
            return confirmEmployeeCode;
        }

        public void setConfirmEmployeeCode(String confirmEmployeeCode) {
            this.confirmEmployeeCode = confirmEmployeeCode;
        }

        public String getConfirmEmployeeId() {
            return confirmEmployeeId;
        }

        public void setConfirmEmployeeId(String confirmEmployeeId) {
            this.confirmEmployeeId = confirmEmployeeId;
        }

        public String getConfirmEmployeeName() {
            return confirmEmployeeName;
        }

        public void setConfirmEmployeeName(String confirmEmployeeName) {
            this.confirmEmployeeName = confirmEmployeeName;
        }

        public double getConfirmQty() {
            return confirmQty;
        }

        public void setConfirmQty(double confirmQty) {
            this.confirmQty = confirmQty;
        }

        public double getConfirmRetailAmount() {
            return confirmRetailAmount;
        }

        public void setConfirmRetailAmount(double confirmRetailAmount) {
            this.confirmRetailAmount = confirmRetailAmount;
        }

        public double getCostAmount() {
            return costAmount;
        }

        public void setCostAmount(double costAmount) {
            this.costAmount = costAmount;
        }

        public String getCreateEmployeeCode() {
            return createEmployeeCode;
        }

        public void setCreateEmployeeCode(String createEmployeeCode) {
            this.createEmployeeCode = createEmployeeCode;
        }

        public String getCreateEmployeeId() {
            return createEmployeeId;
        }

        public void setCreateEmployeeId(String createEmployeeId) {
            this.createEmployeeId = createEmployeeId;
        }

        public String getCreateEmployeeName() {
            return createEmployeeName;
        }

        public void setCreateEmployeeName(String createEmployeeName) {
            this.createEmployeeName = createEmployeeName;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public Object getCustomFieldValue01() {
            return customFieldValue01;
        }

        public void setCustomFieldValue01(Object customFieldValue01) {
            this.customFieldValue01 = customFieldValue01;
        }

        public Object getCustomFieldValue02() {
            return customFieldValue02;
        }

        public void setCustomFieldValue02(Object customFieldValue02) {
            this.customFieldValue02 = customFieldValue02;
        }

        public Object getCustomFieldValue03() {
            return customFieldValue03;
        }

        public void setCustomFieldValue03(Object customFieldValue03) {
            this.customFieldValue03 = customFieldValue03;
        }

        public Object getCustomFieldValue04() {
            return customFieldValue04;
        }

        public void setCustomFieldValue04(Object customFieldValue04) {
            this.customFieldValue04 = customFieldValue04;
        }

        public Object getCustomFieldValue05() {
            return customFieldValue05;
        }

        public void setCustomFieldValue05(Object customFieldValue05) {
            this.customFieldValue05 = customFieldValue05;
        }

        public Object getCustomFieldValue06() {
            return customFieldValue06;
        }

        public void setCustomFieldValue06(Object customFieldValue06) {
            this.customFieldValue06 = customFieldValue06;
        }

        public Object getCustomFieldValue07() {
            return customFieldValue07;
        }

        public void setCustomFieldValue07(Object customFieldValue07) {
            this.customFieldValue07 = customFieldValue07;
        }

        public Object getCustomFieldValue08() {
            return customFieldValue08;
        }

        public void setCustomFieldValue08(Object customFieldValue08) {
            this.customFieldValue08 = customFieldValue08;
        }

        public Object getCustomFieldValue09() {
            return customFieldValue09;
        }

        public void setCustomFieldValue09(Object customFieldValue09) {
            this.customFieldValue09 = customFieldValue09;
        }

        public Object getCustomFieldValue10() {
            return customFieldValue10;
        }

        public void setCustomFieldValue10(Object customFieldValue10) {
            this.customFieldValue10 = customFieldValue10;
        }

        public Object getCustomFieldValue11() {
            return customFieldValue11;
        }

        public void setCustomFieldValue11(Object customFieldValue11) {
            this.customFieldValue11 = customFieldValue11;
        }

        public Object getCustomFieldValue12() {
            return customFieldValue12;
        }

        public void setCustomFieldValue12(Object customFieldValue12) {
            this.customFieldValue12 = customFieldValue12;
        }

        public Object getCustomFieldValue13() {
            return customFieldValue13;
        }

        public void setCustomFieldValue13(Object customFieldValue13) {
            this.customFieldValue13 = customFieldValue13;
        }

        public Object getCustomFieldValue14() {
            return customFieldValue14;
        }

        public void setCustomFieldValue14(Object customFieldValue14) {
            this.customFieldValue14 = customFieldValue14;
        }

        public Object getCustomFieldValue15() {
            return customFieldValue15;
        }

        public void setCustomFieldValue15(Object customFieldValue15) {
            this.customFieldValue15 = customFieldValue15;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getFromBusinessUnitCode() {
            return fromBusinessUnitCode;
        }

        public void setFromBusinessUnitCode(String fromBusinessUnitCode) {
            this.fromBusinessUnitCode = fromBusinessUnitCode;
        }

        public String getFromBusinessUnitId() {
            return fromBusinessUnitId;
        }

        public void setFromBusinessUnitId(String fromBusinessUnitId) {
            this.fromBusinessUnitId = fromBusinessUnitId;
        }

        public String getFromBusinessUnitName() {
            return fromBusinessUnitName;
        }

        public void setFromBusinessUnitName(String fromBusinessUnitName) {
            this.fromBusinessUnitName = fromBusinessUnitName;
        }

        public Object getFromLocationCode() {
            return fromLocationCode;
        }

        public void setFromLocationCode(Object fromLocationCode) {
            this.fromLocationCode = fromLocationCode;
        }

        public Object getFromLocationId() {
            return fromLocationId;
        }

        public void setFromLocationId(Object fromLocationId) {
            this.fromLocationId = fromLocationId;
        }

        public Object getFromLocationName() {
            return fromLocationName;
        }

        public void setFromLocationName(Object fromLocationName) {
            this.fromLocationName = fromLocationName;
        }

        public String getFromWarehouseCode() {
            return fromWarehouseCode;
        }

        public void setFromWarehouseCode(String fromWarehouseCode) {
            this.fromWarehouseCode = fromWarehouseCode;
        }

        public String getFromWarehouseId() {
            return fromWarehouseId;
        }

        public void setFromWarehouseId(String fromWarehouseId) {
            this.fromWarehouseId = fromWarehouseId;
        }

        public String getFromWarehouseName() {
            return fromWarehouseName;
        }

        public void setFromWarehouseName(String fromWarehouseName) {
            this.fromWarehouseName = fromWarehouseName;
        }

        public boolean isHasSourceOrder() {
            return hasSourceOrder;
        }

        public void setHasSourceOrder(boolean hasSourceOrder) {
            this.hasSourceOrder = hasSourceOrder;
        }


        public String getInboundMode() {
            return inboundMode;
        }

        public void setInboundMode(String inboundMode) {
            this.inboundMode = inboundMode;
        }


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLastModifyEmployeeCode() {
            return lastModifyEmployeeCode;
        }

        public void setLastModifyEmployeeCode(String lastModifyEmployeeCode) {
            this.lastModifyEmployeeCode = lastModifyEmployeeCode;
        }

        public String getLastModifyEmployeeId() {
            return lastModifyEmployeeId;
        }

        public void setLastModifyEmployeeId(String lastModifyEmployeeId) {
            this.lastModifyEmployeeId = lastModifyEmployeeId;
        }

        public String getLastModifyEmployeeName() {
            return lastModifyEmployeeName;
        }

        public void setLastModifyEmployeeName(String lastModifyEmployeeName) {
            this.lastModifyEmployeeName = lastModifyEmployeeName;
        }

        public Object getLastSyncDateTime() {
            return lastSyncDateTime;
        }

        public void setLastSyncDateTime(Object lastSyncDateTime) {
            this.lastSyncDateTime = lastSyncDateTime;
        }

        public String getMakeDateTime() {
            return makeDateTime;
        }

        public void setMakeDateTime(String makeDateTime) {
            this.makeDateTime = makeDateTime;
        }

        public String getModified() {
            return modified;
        }

        public void setModified(String modified) {
            this.modified = modified;
        }

        public double getOperateBusinessAmount() {
            return operateBusinessAmount;
        }

        public void setOperateBusinessAmount(double operateBusinessAmount) {
            this.operateBusinessAmount = operateBusinessAmount;
        }

        public double getOperateCostAmount() {
            return operateCostAmount;
        }

        public void setOperateCostAmount(double operateCostAmount) {
            this.operateCostAmount = operateCostAmount;
        }

        public double getOperateQty() {
            return operateQty;
        }

        public void setOperateQty(double operateQty) {
            this.operateQty = operateQty;
        }

        public double getOperateRetailAmount() {
            return operateRetailAmount;
        }

        public void setOperateRetailAmount(double operateRetailAmount) {
            this.operateRetailAmount = operateRetailAmount;
        }

        public String getOrderSubTypeCode() {
            return orderSubTypeCode;
        }

        public void setOrderSubTypeCode(String orderSubTypeCode) {
            this.orderSubTypeCode = orderSubTypeCode;
        }

        public String getOrderSubTypeId() {
            return orderSubTypeId;
        }

        public void setOrderSubTypeId(String orderSubTypeId) {
            this.orderSubTypeId = orderSubTypeId;
        }

        public String getOrderSubTypeName() {
            return orderSubTypeName;
        }

        public void setOrderSubTypeName(String orderSubTypeName) {
            this.orderSubTypeName = orderSubTypeName;
        }

        public double getQty() {
            return qty;
        }

        public void setQty(double qty) {
            this.qty = qty;
        }

        public double getRetailAmount() {
            return retailAmount;
        }

        public void setRetailAmount(double retailAmount) {
            this.retailAmount = retailAmount;
        }

        public String getSourceOrderCode() {
            return sourceOrderCode;
        }

        public void setSourceOrderCode(String sourceOrderCode) {
            this.sourceOrderCode = sourceOrderCode;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(String statusCode) {
            this.statusCode = statusCode;
        }

        public String getStatusDescription() {
            return statusDescription;
        }

        public void setStatusDescription(String statusDescription) {
            this.statusDescription = statusDescription;
        }

        public String getSyncStatus() {
            return syncStatus;
        }

        public void setSyncStatus(String syncStatus) {
            this.syncStatus = syncStatus;
        }

        public String getSyncStatusCode() {
            return syncStatusCode;
        }

        public void setSyncStatusCode(String syncStatusCode) {
            this.syncStatusCode = syncStatusCode;
        }

        public String getSyncStatusDescription() {
            return syncStatusDescription;
        }

        public void setSyncStatusDescription(String syncStatusDescription) {
            this.syncStatusDescription = syncStatusDescription;
        }

        public Object getToLocationCode() {
            return toLocationCode;
        }

        public void setToLocationCode(Object toLocationCode) {
            this.toLocationCode = toLocationCode;
        }

        public Object getToLocationId() {
            return toLocationId;
        }

        public void setToLocationId(Object toLocationId) {
            this.toLocationId = toLocationId;
        }

        public Object getToLocationName() {
            return toLocationName;
        }

        public void setToLocationName(Object toLocationName) {
            this.toLocationName = toLocationName;
        }

        public String getToWarehouseCode() {
            return toWarehouseCode;
        }

        public void setToWarehouseCode(String toWarehouseCode) {
            this.toWarehouseCode = toWarehouseCode;
        }

        public String getToWarehouseId() {
            return toWarehouseId;
        }

        public void setToWarehouseId(String toWarehouseId) {
            this.toWarehouseId = toWarehouseId;
        }

        public String getToWarehouseName() {
            return toWarehouseName;
        }

        public void setToWarehouseName(String toWarehouseName) {
            this.toWarehouseName = toWarehouseName;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }
    }
}
