package com.lide.app.bean.JsonToBean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lubin on 2016/7/22.
 */
public class TakeStockOrderList {

    private int currentPage;
    private boolean hasNext;
    private int recordsPerPage;
    private int totalPages;
    private int totalRecords;
    /**
     * code : TS00016000002
     * confirmDateTime : 2016-01-21 15:31:39.791
     * confirmEmployeeCode : ADMIN
     * confirmEmployeeId : 1232BB62-A074-43E9-B8CB-E36E8BFD76FD
     * confirmEmployeeName : ADMIN
     * createEmployeeCode : jj
     * createEmployeeId : aaa66a3e-9b0b-4e38-961f-1eb0fe8eec1a
     * createEmployeeName : 林俊杰
     * created : 2016-01-19 14:30:33.714
     * description :
     * id : 000295ff-0189-469e-9efb-fb7005a402fa
     * lastModifyEmployeeCode : ADMIN
     * lastModifyEmployeeId : 1232BB62-A074-43E9-B8CB-E36E8BFD76FD
     * lastModifyEmployeeName : ADMIN
     * lastSyncDateTime : null
     * makeDateTime : 2016-01-19 14:30:33.714
     * modified : 2016-01-21 15:31:39.791
     * sourceOrderCode : null
     * status : FINISHED
     * statusDesc : 已完成
     * syncStatus : NONE
     * syncStatusDesc : 未回传
     * takeStockMode : FULL
     * takeStockModeDesc : 全盘
     * takeStockTypeCode : TS_DATE
     * takeStockTypeId : 41AB6E3E-F781-45F8-8934-3B498C2E100E
     * takeStockTypeName : 日盘
     * version : 3
     * warehouseCode : 000
     * warehouseId : 99c3b810-4cf9-464d-ac7b-53921ed351e8
     * warehouseName : UR正品仓
     */

    private List<DataBean> data;

    public static TakeStockOrderList objectFromData(String str) {

        return new Gson().fromJson(str, TakeStockOrderList.class);
    }

    public static TakeStockOrderList objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), TakeStockOrderList.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<TakeStockOrderList> arrayDemoFromData(String str) {

        Type listType = new TypeToken<ArrayList<TakeStockOrderList>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<TakeStockOrderList> arrayDemoFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<TakeStockOrderList>>() {
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
        private String code;
        private String confirmDateTime;
        private String confirmEmployeeCode;
        private String confirmEmployeeId;
        private String confirmEmployeeName;
        private String createEmployeeCode;
        private String createEmployeeId;
        private String createEmployeeName;
        private String created;
        private String description;
        private String id;
        private String lastModifyEmployeeCode;
        private String lastModifyEmployeeId;
        private String lastModifyEmployeeName;
        private Object lastSyncDateTime;
        private String makeDateTime;
        private String modified;
        private Object sourceOrderCode;
        private String status;
        private String statusDesc;
        private String syncStatus;
        private String syncStatusDesc;
        private String takeStockMode;
        private String takeStockModeDesc;
        private String takeStockTypeCode;
        private String takeStockTypeId;
        private String takeStockTypeName;
        private int version;
        private String warehouseCode;
        private String warehouseId;
        private String warehouseName;

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

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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

        public Object getSourceOrderCode() {
            return sourceOrderCode;
        }

        public void setSourceOrderCode(Object sourceOrderCode) {
            this.sourceOrderCode = sourceOrderCode;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatusDesc() {
            return statusDesc;
        }

        public void setStatusDesc(String statusDesc) {
            this.statusDesc = statusDesc;
        }

        public String getSyncStatus() {
            return syncStatus;
        }

        public void setSyncStatus(String syncStatus) {
            this.syncStatus = syncStatus;
        }

        public String getSyncStatusDesc() {
            return syncStatusDesc;
        }

        public void setSyncStatusDesc(String syncStatusDesc) {
            this.syncStatusDesc = syncStatusDesc;
        }

        public String getTakeStockMode() {
            return takeStockMode;
        }

        public void setTakeStockMode(String takeStockMode) {
            this.takeStockMode = takeStockMode;
        }

        public String getTakeStockModeDesc() {
            return takeStockModeDesc;
        }

        public void setTakeStockModeDesc(String takeStockModeDesc) {
            this.takeStockModeDesc = takeStockModeDesc;
        }

        public String getTakeStockTypeCode() {
            return takeStockTypeCode;
        }

        public void setTakeStockTypeCode(String takeStockTypeCode) {
            this.takeStockTypeCode = takeStockTypeCode;
        }

        public String getTakeStockTypeId() {
            return takeStockTypeId;
        }

        public void setTakeStockTypeId(String takeStockTypeId) {
            this.takeStockTypeId = takeStockTypeId;
        }

        public String getTakeStockTypeName() {
            return takeStockTypeName;
        }

        public void setTakeStockTypeName(String takeStockTypeName) {
            this.takeStockTypeName = takeStockTypeName;
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
    }
}
