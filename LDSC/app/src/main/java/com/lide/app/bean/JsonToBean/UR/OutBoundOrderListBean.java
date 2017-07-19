package com.lide.app.bean.JsonToBean.UR;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by HKR on 2017/3/13.
 */

public class OutBoundOrderListBean {

    /**
     * currentPage : 2
     * data : [{"businessAmount":0,"code":"OTleadstore17000002","commonDataId":"3947fc11-c8a6-46f6-9c48-403e91efeeaa","confirmBusinessAmount":0,"confirmCostAmount":0,"confirmDateTime":null,"confirmEmployeeCode":null,"confirmEmployeeId":null,"confirmEmployeeName":null,"confirmQty":0,"confirmRetailAmount":0,"confirmedCazeCount":0,"costAmount":0,"createEmployeeCode":"078","createEmployeeId":"86f81261-c7b9-4871-a4e3-95f18b92bccb","createEmployeeName":"chen","created":"2017-02-22 13:17:48.637","customFieldValue01":null,"customFieldValue02":null,"customFieldValue03":null,"customFieldValue04":null,"customFieldValue05":null,"customFieldValue06":null,"customFieldValue07":null,"customFieldValue08":null,"customFieldValue09":null,"customFieldValue10":null,"customFieldValue11":null,"customFieldValue12":null,"customFieldValue13":null,"customFieldValue14":null,"customFieldValue15":null,"description":"test - test..","fromLocationCode":"lead001","fromLocationId":"665b865e-186e-455e-bcbb-d66defd44a5f","fromLocationName":"理德门店库位","fromWarehouseCode":"leadstore","fromWarehouseCustomFieldValue01":null,"fromWarehouseCustomFieldValue02":null,"fromWarehouseDesc":null,"fromWarehouseId":"30f0ae1c-a07f-4501-81e0-9a27992598d0","fromWarehouseName":"理德门店","hasSourceOrder":false,"id":"4d74a3f2-09ed-4638-b228-343b62e5a8c4","lastModifyEmployeeCode":"078","lastModifyEmployeeId":"86f81261-c7b9-4871-a4e3-95f18b92bccb","lastModifyEmployeeName":"chen","lastSyncDateTime":null,"makeDateTime":"2017-02-22 13:17:48.637","modified":"2017-02-22 13:17:48.637","operateBusinessAmount":0,"operateCostAmount":0,"operateQty":0,"operateRetailAmount":0,"orderSubTypeCode":"YC","orderSubTypeId":"805E8F19-B929-4C4B-8D12-6D0C997C7320","orderSubTypeName":"移仓","qty":0,"reason":"质量问题","retailAmount":0,"seedingPosition":null,"seedingStatus":"NEW","sourceOrderCode":null,"sourceOrderCreateEmployeeCode":null,"sourceOrderCreateEmployeeName":null,"sourceOrderLastModifyEmployeeCode":null,"sourceOrderLastModifyEmployeeName":null,"status":"NEW","statusCode":"NEW","statusDescription":"新单","syncStatus":"NONE","syncStatusCode":"NONE","syncStatusDescription":"未回传","toAddress":null,"toBusinessUnitCode":null,"toBusinessUnitId":null,"toBusinessUnitName":null,"toContact":null,"toLocationCode":"URCN0064-def-001","toLocationId":"67A7A578-5A19-4C7D-9764-0C3257509064","toLocationName":"默认货位","toTel":null,"toWarehouseCode":"URCN0064","toWarehouseCustomFieldValue01":null,"toWarehouseCustomFieldValue02":null,"toWarehouseDesc":null,"toWarehouseId":"2f6ca915-9d44-493b-973b-b3d3384007ce","toWarehouseName":"广州萝岗万达广场店","transferInQty":0,"version":1,"waveNumber":null},{"businessAmount":0,"code":"OTleadstore17000004","commonDataId":"b17b67f9-716b-4172-8b90-2dcdc808b436","confirmBusinessAmount":0,"confirmCostAmount":0,"confirmDateTime":"2017-03-01 11:21:15.663","confirmEmployeeCode":"078","confirmEmployeeId":"86f81261-c7b9-4871-a4e3-95f18b92bccb","confirmEmployeeName":"chen","confirmQty":70,"confirmRetailAmount":0,"confirmedCazeCount":0,"costAmount":0,"createEmployeeCode":"078","createEmployeeId":"86f81261-c7b9-4871-a4e3-95f18b92bccb","createEmployeeName":"chen","created":"2017-03-01 10:15:44.767","customFieldValue01":null,"customFieldValue02":null,"customFieldValue03":null,"customFieldValue04":null,"customFieldValue05":null,"customFieldValue06":null,"customFieldValue07":null,"customFieldValue08":null,"customFieldValue09":null,"customFieldValue10":null,"customFieldValue11":null,"customFieldValue12":null,"customFieldValue13":null,"customFieldValue14":null,"customFieldValue15":null,"description":"test-test;test-test;  test-test","fromLocationCode":"lead001","fromLocationId":"665b865e-186e-455e-bcbb-d66defd44a5f","fromLocationName":"理德门店库位","fromWarehouseCode":"leadstore","fromWarehouseCustomFieldValue01":null,"fromWarehouseCustomFieldValue02":null,"fromWarehouseDesc":null,"fromWarehouseId":"30f0ae1c-a07f-4501-81e0-9a27992598d0","fromWarehouseName":"理德门店","hasSourceOrder":false,"id":"9fa211f8-bcf5-47aa-9138-c561dc5b417b","lastModifyEmployeeCode":"078","lastModifyEmployeeId":"86f81261-c7b9-4871-a4e3-95f18b92bccb","lastModifyEmployeeName":"chen","lastSyncDateTime":"2017-03-10 22:02:07.770","makeDateTime":"2017-03-01 10:15:44.767","modified":"2017-03-01 11:21:11.797","operateBusinessAmount":0,"operateCostAmount":0,"operateQty":70,"operateRetailAmount":0,"orderSubTypeCode":"TOO","orderSubTypeId":"ae3624d8-46e5-43f8-972e-2e5e363d2dbc","orderSubTypeName":"店店调拨","qty":70,"reason":"7天无理由","retailAmount":0,"seedingPosition":null,"seedingStatus":"NEW","sourceOrderCode":null,"sourceOrderCreateEmployeeCode":null,"sourceOrderCreateEmployeeName":null,"sourceOrderLastModifyEmployeeCode":null,"sourceOrderLastModifyEmployeeName":null,"status":"FINISHED","statusCode":"FINISHED","statusDescription":"已完成","syncStatus":"FAIL","syncStatusCode":"FAIL","syncStatusDescription":"失败","toAddress":null,"toBusinessUnitCode":null,"toBusinessUnitId":null,"toBusinessUnitName":null,"toContact":null,"toLocationCode":"lead001","toLocationId":"665b865e-186e-455e-bcbb-d66defd44a5f","toLocationName":"理德门店库位","toTel":null,"toWarehouseCode":"leadstore","toWarehouseCustomFieldValue01":null,"toWarehouseCustomFieldValue02":null,"toWarehouseDesc":null,"toWarehouseId":"30f0ae1c-a07f-4501-81e0-9a27992598d0","toWarehouseName":"理德门店","transferInQty":0,"version":4,"waveNumber":null}]
     * hasNext : true
     * recordsPerPage : 2
     * totalPages : 3
     * totalRecords : 5
     */

    private int currentPage;
    private boolean hasNext;
    private int recordsPerPage;
    private int totalPages;
    private int totalRecords;
    private List<DataBean> data;

    public static OutBoundOrderListBean objectFromData(String str) {
        return new Gson().fromJson(str, OutBoundOrderListBean.class);
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
        /**
         * businessAmount : 0
         * code : OTleadstore17000002
         * commonDataId : 3947fc11-c8a6-46f6-9c48-403e91efeeaa
         * confirmBusinessAmount : 0
         * confirmCostAmount : 0
         * confirmDateTime : null
         * confirmEmployeeCode : null
         * confirmEmployeeId : null
         * confirmEmployeeName : null
         * confirmQty : 0
         * confirmRetailAmount : 0
         * confirmedCazeCount : 0
         * costAmount : 0
         * createEmployeeCode : 078
         * createEmployeeId : 86f81261-c7b9-4871-a4e3-95f18b92bccb
         * createEmployeeName : chen
         * created : 2017-02-22 13:17:48.637
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
         * description : test - test..
         * fromLocationCode : lead001
         * fromLocationId : 665b865e-186e-455e-bcbb-d66defd44a5f
         * fromLocationName : 理德门店库位
         * fromWarehouseCode : leadstore
         * fromWarehouseCustomFieldValue01 : null
         * fromWarehouseCustomFieldValue02 : null
         * fromWarehouseDesc : null
         * fromWarehouseId : 30f0ae1c-a07f-4501-81e0-9a27992598d0
         * fromWarehouseName : 理德门店
         * hasSourceOrder : false
         * id : 4d74a3f2-09ed-4638-b228-343b62e5a8c4
         * lastModifyEmployeeCode : 078
         * lastModifyEmployeeId : 86f81261-c7b9-4871-a4e3-95f18b92bccb
         * lastModifyEmployeeName : chen
         * lastSyncDateTime : null
         * makeDateTime : 2017-02-22 13:17:48.637
         * modified : 2017-02-22 13:17:48.637
         * operateBusinessAmount : 0
         * operateCostAmount : 0
         * operateQty : 0
         * operateRetailAmount : 0
         * orderSubTypeCode : YC
         * orderSubTypeId : 805E8F19-B929-4C4B-8D12-6D0C997C7320
         * orderSubTypeName : 移仓
         * qty : 0
         * reason : 质量问题
         * retailAmount : 0
         * seedingPosition : null
         * seedingStatus : NEW
         * sourceOrderCode : null
         * sourceOrderCreateEmployeeCode : null
         * sourceOrderCreateEmployeeName : null
         * sourceOrderLastModifyEmployeeCode : null
         * sourceOrderLastModifyEmployeeName : null
         * status : NEW
         * statusCode : NEW
         * statusDescription : 新单
         * syncStatus : NONE
         * syncStatusCode : NONE
         * syncStatusDescription : 未回传
         * toAddress : null
         * toBusinessUnitCode : null
         * toBusinessUnitId : null
         * toBusinessUnitName : null
         * toContact : null
         * toLocationCode : URCN0064-def-001
         * toLocationId : 67A7A578-5A19-4C7D-9764-0C3257509064
         * toLocationName : 默认货位
         * toTel : null
         * toWarehouseCode : URCN0064
         * toWarehouseCustomFieldValue01 : null
         * toWarehouseCustomFieldValue02 : null
         * toWarehouseDesc : null
         * toWarehouseId : 2f6ca915-9d44-493b-973b-b3d3384007ce
         * toWarehouseName : 广州萝岗万达广场店
         * transferInQty : 0
         * version : 1
         * waveNumber : null
         */

        private int businessAmount;
        private String code;
        private String commonDataId;
        private int confirmBusinessAmount;
        private int confirmCostAmount;
        private Object confirmDateTime;
        private Object confirmEmployeeCode;
        private Object confirmEmployeeId;
        private Object confirmEmployeeName;
        private int confirmQty;
        private int confirmRetailAmount;
        private int confirmedCazeCount;
        private int costAmount;
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
        private String fromLocationCode;
        private String fromLocationId;
        private String fromLocationName;
        private String fromWarehouseCode;
        private Object fromWarehouseCustomFieldValue01;
        private Object fromWarehouseCustomFieldValue02;
        private Object fromWarehouseDesc;
        private String fromWarehouseId;
        private String fromWarehouseName;
        private boolean hasSourceOrder;
        private String id;
        private String lastModifyEmployeeCode;
        private String lastModifyEmployeeId;
        private String lastModifyEmployeeName;
        private Object lastSyncDateTime;
        private String makeDateTime;
        private String modified;
        private int operateBusinessAmount;
        private int operateCostAmount;
        private int operateQty;
        private int operateRetailAmount;
        private String orderSubTypeCode;
        private String orderSubTypeId;
        private String orderSubTypeName;
        private int qty;
        private String reason;
        private int retailAmount;
        private Object seedingPosition;
        private String seedingStatus;
        private Object sourceOrderCode;
        private Object sourceOrderCreateEmployeeCode;
        private Object sourceOrderCreateEmployeeName;
        private Object sourceOrderLastModifyEmployeeCode;
        private Object sourceOrderLastModifyEmployeeName;
        private String status;
        private String statusCode;
        private String statusDescription;
        private String syncStatus;
        private String syncStatusCode;
        private String syncStatusDescription;
        private Object toAddress;
        private Object toBusinessUnitCode;
        private Object toBusinessUnitId;
        private Object toBusinessUnitName;
        private Object toContact;
        private String toLocationCode;
        private String toLocationId;
        private String toLocationName;
        private Object toTel;
        private String toWarehouseCode;
        private Object toWarehouseCustomFieldValue01;
        private Object toWarehouseCustomFieldValue02;
        private Object toWarehouseDesc;
        private String toWarehouseId;
        private String toWarehouseName;
        private int transferInQty;
        private int version;
        private Object waveNumber;

        public int getBusinessAmount() {
            return businessAmount;
        }

        public void setBusinessAmount(int businessAmount) {
            this.businessAmount = businessAmount;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCommonDataId() {
            return commonDataId;
        }

        public void setCommonDataId(String commonDataId) {
            this.commonDataId = commonDataId;
        }

        public int getConfirmBusinessAmount() {
            return confirmBusinessAmount;
        }

        public void setConfirmBusinessAmount(int confirmBusinessAmount) {
            this.confirmBusinessAmount = confirmBusinessAmount;
        }

        public int getConfirmCostAmount() {
            return confirmCostAmount;
        }

        public void setConfirmCostAmount(int confirmCostAmount) {
            this.confirmCostAmount = confirmCostAmount;
        }

        public Object getConfirmDateTime() {
            return confirmDateTime;
        }

        public void setConfirmDateTime(Object confirmDateTime) {
            this.confirmDateTime = confirmDateTime;
        }

        public Object getConfirmEmployeeCode() {
            return confirmEmployeeCode;
        }

        public void setConfirmEmployeeCode(Object confirmEmployeeCode) {
            this.confirmEmployeeCode = confirmEmployeeCode;
        }

        public Object getConfirmEmployeeId() {
            return confirmEmployeeId;
        }

        public void setConfirmEmployeeId(Object confirmEmployeeId) {
            this.confirmEmployeeId = confirmEmployeeId;
        }

        public Object getConfirmEmployeeName() {
            return confirmEmployeeName;
        }

        public void setConfirmEmployeeName(Object confirmEmployeeName) {
            this.confirmEmployeeName = confirmEmployeeName;
        }

        public int getConfirmQty() {
            return confirmQty;
        }

        public void setConfirmQty(int confirmQty) {
            this.confirmQty = confirmQty;
        }

        public int getConfirmRetailAmount() {
            return confirmRetailAmount;
        }

        public void setConfirmRetailAmount(int confirmRetailAmount) {
            this.confirmRetailAmount = confirmRetailAmount;
        }

        public int getConfirmedCazeCount() {
            return confirmedCazeCount;
        }

        public void setConfirmedCazeCount(int confirmedCazeCount) {
            this.confirmedCazeCount = confirmedCazeCount;
        }

        public int getCostAmount() {
            return costAmount;
        }

        public void setCostAmount(int costAmount) {
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

        public String getFromLocationCode() {
            return fromLocationCode;
        }

        public void setFromLocationCode(String fromLocationCode) {
            this.fromLocationCode = fromLocationCode;
        }

        public String getFromLocationId() {
            return fromLocationId;
        }

        public void setFromLocationId(String fromLocationId) {
            this.fromLocationId = fromLocationId;
        }

        public String getFromLocationName() {
            return fromLocationName;
        }

        public void setFromLocationName(String fromLocationName) {
            this.fromLocationName = fromLocationName;
        }

        public String getFromWarehouseCode() {
            return fromWarehouseCode;
        }

        public void setFromWarehouseCode(String fromWarehouseCode) {
            this.fromWarehouseCode = fromWarehouseCode;
        }

        public Object getFromWarehouseCustomFieldValue01() {
            return fromWarehouseCustomFieldValue01;
        }

        public void setFromWarehouseCustomFieldValue01(Object fromWarehouseCustomFieldValue01) {
            this.fromWarehouseCustomFieldValue01 = fromWarehouseCustomFieldValue01;
        }

        public Object getFromWarehouseCustomFieldValue02() {
            return fromWarehouseCustomFieldValue02;
        }

        public void setFromWarehouseCustomFieldValue02(Object fromWarehouseCustomFieldValue02) {
            this.fromWarehouseCustomFieldValue02 = fromWarehouseCustomFieldValue02;
        }

        public Object getFromWarehouseDesc() {
            return fromWarehouseDesc;
        }

        public void setFromWarehouseDesc(Object fromWarehouseDesc) {
            this.fromWarehouseDesc = fromWarehouseDesc;
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

        public int getOperateBusinessAmount() {
            return operateBusinessAmount;
        }

        public void setOperateBusinessAmount(int operateBusinessAmount) {
            this.operateBusinessAmount = operateBusinessAmount;
        }

        public int getOperateCostAmount() {
            return operateCostAmount;
        }

        public void setOperateCostAmount(int operateCostAmount) {
            this.operateCostAmount = operateCostAmount;
        }

        public int getOperateQty() {
            return operateQty;
        }

        public void setOperateQty(int operateQty) {
            this.operateQty = operateQty;
        }

        public int getOperateRetailAmount() {
            return operateRetailAmount;
        }

        public void setOperateRetailAmount(int operateRetailAmount) {
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

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public int getRetailAmount() {
            return retailAmount;
        }

        public void setRetailAmount(int retailAmount) {
            this.retailAmount = retailAmount;
        }

        public Object getSeedingPosition() {
            return seedingPosition;
        }

        public void setSeedingPosition(Object seedingPosition) {
            this.seedingPosition = seedingPosition;
        }

        public String getSeedingStatus() {
            return seedingStatus;
        }

        public void setSeedingStatus(String seedingStatus) {
            this.seedingStatus = seedingStatus;
        }

        public Object getSourceOrderCode() {
            return sourceOrderCode;
        }

        public void setSourceOrderCode(Object sourceOrderCode) {
            this.sourceOrderCode = sourceOrderCode;
        }

        public Object getSourceOrderCreateEmployeeCode() {
            return sourceOrderCreateEmployeeCode;
        }

        public void setSourceOrderCreateEmployeeCode(Object sourceOrderCreateEmployeeCode) {
            this.sourceOrderCreateEmployeeCode = sourceOrderCreateEmployeeCode;
        }

        public Object getSourceOrderCreateEmployeeName() {
            return sourceOrderCreateEmployeeName;
        }

        public void setSourceOrderCreateEmployeeName(Object sourceOrderCreateEmployeeName) {
            this.sourceOrderCreateEmployeeName = sourceOrderCreateEmployeeName;
        }

        public Object getSourceOrderLastModifyEmployeeCode() {
            return sourceOrderLastModifyEmployeeCode;
        }

        public void setSourceOrderLastModifyEmployeeCode(Object sourceOrderLastModifyEmployeeCode) {
            this.sourceOrderLastModifyEmployeeCode = sourceOrderLastModifyEmployeeCode;
        }

        public Object getSourceOrderLastModifyEmployeeName() {
            return sourceOrderLastModifyEmployeeName;
        }

        public void setSourceOrderLastModifyEmployeeName(Object sourceOrderLastModifyEmployeeName) {
            this.sourceOrderLastModifyEmployeeName = sourceOrderLastModifyEmployeeName;
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

        public Object getToAddress() {
            return toAddress;
        }

        public void setToAddress(Object toAddress) {
            this.toAddress = toAddress;
        }

        public Object getToBusinessUnitCode() {
            return toBusinessUnitCode;
        }

        public void setToBusinessUnitCode(Object toBusinessUnitCode) {
            this.toBusinessUnitCode = toBusinessUnitCode;
        }

        public Object getToBusinessUnitId() {
            return toBusinessUnitId;
        }

        public void setToBusinessUnitId(Object toBusinessUnitId) {
            this.toBusinessUnitId = toBusinessUnitId;
        }

        public Object getToBusinessUnitName() {
            return toBusinessUnitName;
        }

        public void setToBusinessUnitName(Object toBusinessUnitName) {
            this.toBusinessUnitName = toBusinessUnitName;
        }

        public Object getToContact() {
            return toContact;
        }

        public void setToContact(Object toContact) {
            this.toContact = toContact;
        }

        public String getToLocationCode() {
            return toLocationCode;
        }

        public void setToLocationCode(String toLocationCode) {
            this.toLocationCode = toLocationCode;
        }

        public String getToLocationId() {
            return toLocationId;
        }

        public void setToLocationId(String toLocationId) {
            this.toLocationId = toLocationId;
        }

        public String getToLocationName() {
            return toLocationName;
        }

        public void setToLocationName(String toLocationName) {
            this.toLocationName = toLocationName;
        }

        public Object getToTel() {
            return toTel;
        }

        public void setToTel(Object toTel) {
            this.toTel = toTel;
        }

        public String getToWarehouseCode() {
            return toWarehouseCode;
        }

        public void setToWarehouseCode(String toWarehouseCode) {
            this.toWarehouseCode = toWarehouseCode;
        }

        public Object getToWarehouseCustomFieldValue01() {
            return toWarehouseCustomFieldValue01;
        }

        public void setToWarehouseCustomFieldValue01(Object toWarehouseCustomFieldValue01) {
            this.toWarehouseCustomFieldValue01 = toWarehouseCustomFieldValue01;
        }

        public Object getToWarehouseCustomFieldValue02() {
            return toWarehouseCustomFieldValue02;
        }

        public void setToWarehouseCustomFieldValue02(Object toWarehouseCustomFieldValue02) {
            this.toWarehouseCustomFieldValue02 = toWarehouseCustomFieldValue02;
        }

        public Object getToWarehouseDesc() {
            return toWarehouseDesc;
        }

        public void setToWarehouseDesc(Object toWarehouseDesc) {
            this.toWarehouseDesc = toWarehouseDesc;
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

        public int getTransferInQty() {
            return transferInQty;
        }

        public void setTransferInQty(int transferInQty) {
            this.transferInQty = transferInQty;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public Object getWaveNumber() {
            return waveNumber;
        }

        public void setWaveNumber(Object waveNumber) {
            this.waveNumber = waveNumber;
        }
    }
}
