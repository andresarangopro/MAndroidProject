package com.lide.app.bean.JsonToBean.UR;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by HKR on 2017/3/1.
 */

public class TaskList {

    /**
     * currentPage : 1
     * data : [{"code":"0001","countingQty":0,"created":"2017-02-25 10:09:09.573","customPropertyCode":null,"customPropertyName":null,"customPropertyValues":[],"description":null,"deviceCode":null,"deviceId":null,"deviceName":null,"employeeCode":"0020","employeeId":"398c33d7-8b21-4710-a3cf-8ce3eba6a1df","employeeName":"BenBen","firstCountQty":4,"id":"de4dfc49-8999-4386-87f6-0895628e7055","limitByProperty":false,"limitByStoragePlace":false,"locationCode":null,"locationId":null,"locationName":null,"modified":"2017-02-25 10:07:59.400","propertyType":null,"secondCountQty":2,"sortIndex":"1","status":null,"storagePlaces":[],"takeStockOrderCode":"TS00017000027","takeStockOrderId":"90933afe-65a5-4f09-92f2-a3a2f7c3045b","takeStockOrderStatus":"FINISHED","version":21,"warehouseCode":"000","warehouseId":"99c3b810-4cf9-464d-ac7b-53921ed351e8","warehouseName":"UR正品仓"}]
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
    private List<DataBean> data;

    public static TaskList objectFromData(String str) {

        return new Gson().fromJson(str, TaskList.class);
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
         * code : 0001
         * countingQty : 0
         * created : 2017-02-25 10:09:09.573
         * customPropertyCode : null
         * customPropertyName : null
         * customPropertyValues : []
         * description : null
         * deviceCode : null
         * deviceId : null
         * deviceName : null
         * employeeCode : 0020
         * employeeId : 398c33d7-8b21-4710-a3cf-8ce3eba6a1df
         * employeeName : BenBen
         * firstCountQty : 4
         * id : de4dfc49-8999-4386-87f6-0895628e7055
         * limitByProperty : false
         * limitByStoragePlace : false
         * locationCode : null
         * locationId : null
         * locationName : null
         * modified : 2017-02-25 10:07:59.400
         * propertyType : null
         * secondCountQty : 2
         * sortIndex : 1
         * status : null
         * storagePlaces : []
         * takeStockOrderCode : TS00017000027
         * takeStockOrderId : 90933afe-65a5-4f09-92f2-a3a2f7c3045b
         * takeStockOrderStatus : FINISHED
         * version : 21
         * warehouseCode : 000
         * warehouseId : 99c3b810-4cf9-464d-ac7b-53921ed351e8
         * warehouseName : UR正品仓
         */

        private String code;
        private int countingQty;
        private String created;
        private Object customPropertyCode;
        private Object customPropertyName;
        private Object description;
        private Object deviceCode;
        private Object deviceId;
        private Object deviceName;
        private String employeeCode;
        private String employeeId;
        private String employeeName;
        private int firstCountQty;
        private String id;
        private boolean limitByProperty;
        private boolean limitByStoragePlace;
        private Object locationCode;
        private Object locationId;
        private Object locationName;
        private String modified;
        private Object propertyType;
        private int secondCountQty;
        private String sortIndex;
        private Object status;
        private String takeStockOrderCode;
        private String takeStockOrderId;
        private String takeStockOrderStatus;
        private int version;
        private String warehouseCode;
        private String warehouseId;
        private String warehouseName;
        private List<?> customPropertyValues;
        private List<?> storagePlaces;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getCountingQty() {
            return countingQty;
        }

        public void setCountingQty(int countingQty) {
            this.countingQty = countingQty;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public Object getCustomPropertyCode() {
            return customPropertyCode;
        }

        public void setCustomPropertyCode(Object customPropertyCode) {
            this.customPropertyCode = customPropertyCode;
        }

        public Object getCustomPropertyName() {
            return customPropertyName;
        }

        public void setCustomPropertyName(Object customPropertyName) {
            this.customPropertyName = customPropertyName;
        }

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
            this.description = description;
        }

        public Object getDeviceCode() {
            return deviceCode;
        }

        public void setDeviceCode(Object deviceCode) {
            this.deviceCode = deviceCode;
        }

        public Object getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(Object deviceId) {
            this.deviceId = deviceId;
        }

        public Object getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(Object deviceName) {
            this.deviceName = deviceName;
        }

        public String getEmployeeCode() {
            return employeeCode;
        }

        public void setEmployeeCode(String employeeCode) {
            this.employeeCode = employeeCode;
        }

        public String getEmployeeId() {
            return employeeId;
        }

        public void setEmployeeId(String employeeId) {
            this.employeeId = employeeId;
        }

        public String getEmployeeName() {
            return employeeName;
        }

        public void setEmployeeName(String employeeName) {
            this.employeeName = employeeName;
        }

        public int getFirstCountQty() {
            return firstCountQty;
        }

        public void setFirstCountQty(int firstCountQty) {
            this.firstCountQty = firstCountQty;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isLimitByProperty() {
            return limitByProperty;
        }

        public void setLimitByProperty(boolean limitByProperty) {
            this.limitByProperty = limitByProperty;
        }

        public boolean isLimitByStoragePlace() {
            return limitByStoragePlace;
        }

        public void setLimitByStoragePlace(boolean limitByStoragePlace) {
            this.limitByStoragePlace = limitByStoragePlace;
        }

        public Object getLocationCode() {
            return locationCode;
        }

        public void setLocationCode(Object locationCode) {
            this.locationCode = locationCode;
        }

        public Object getLocationId() {
            return locationId;
        }

        public void setLocationId(Object locationId) {
            this.locationId = locationId;
        }

        public Object getLocationName() {
            return locationName;
        }

        public void setLocationName(Object locationName) {
            this.locationName = locationName;
        }

        public String getModified() {
            return modified;
        }

        public void setModified(String modified) {
            this.modified = modified;
        }

        public Object getPropertyType() {
            return propertyType;
        }

        public void setPropertyType(Object propertyType) {
            this.propertyType = propertyType;
        }

        public int getSecondCountQty() {
            return secondCountQty;
        }

        public void setSecondCountQty(int secondCountQty) {
            this.secondCountQty = secondCountQty;
        }

        public String getSortIndex() {
            return sortIndex;
        }

        public void setSortIndex(String sortIndex) {
            this.sortIndex = sortIndex;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public String getTakeStockOrderCode() {
            return takeStockOrderCode;
        }

        public void setTakeStockOrderCode(String takeStockOrderCode) {
            this.takeStockOrderCode = takeStockOrderCode;
        }

        public String getTakeStockOrderId() {
            return takeStockOrderId;
        }

        public void setTakeStockOrderId(String takeStockOrderId) {
            this.takeStockOrderId = takeStockOrderId;
        }

        public String getTakeStockOrderStatus() {
            return takeStockOrderStatus;
        }

        public void setTakeStockOrderStatus(String takeStockOrderStatus) {
            this.takeStockOrderStatus = takeStockOrderStatus;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String getWarehouseCode() {
            return warehouseCode;
        }

        public void setWarehouseCode(String warehouseCode) {
            this.warehouseCode = warehouseCode;
        }

        public String getWarehouseId() {
            return warehouseId;
        }

        public void setWarehouseId(String warehouseId) {
            this.warehouseId = warehouseId;
        }

        public String getWarehouseName() {
            return warehouseName;
        }

        public void setWarehouseName(String warehouseName) {
            this.warehouseName = warehouseName;
        }

        public List<?> getCustomPropertyValues() {
            return customPropertyValues;
        }

        public void setCustomPropertyValues(List<?> customPropertyValues) {
            this.customPropertyValues = customPropertyValues;
        }

        public List<?> getStoragePlaces() {
            return storagePlaces;
        }

        public void setStoragePlaces(List<?> storagePlaces) {
            this.storagePlaces = storagePlaces;
        }
    }
}
