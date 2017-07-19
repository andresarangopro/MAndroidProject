package com.lide.app.bean.JsonToBean;

import com.google.gson.Gson;

import java.util.List;

/**
 * @author DaiJiCheng
 * @time 2016/10/17  9:11
 * @desc ${TODD}
 */
public class DeviceInfoForID {

    /**
     * currentPage : 1
     * data : [{"code":"001","created":"2015-01-13 09:21:25.413","description":"sfsdfs","deviceType":"HAND_SET","deviceTypeName":"手持机","disable":false,"id":"3ed920c3-11da-4262-b47a-27e385e018ea","modified":"2015-01-13 09:21:25.413","name":"001","readerIds":["16e7bb4e-198c-4340-b0ed-5c494d88cf17","546c1eee-4605-4c8b-bd08-3949fd021ff6","791578fb-81d5-45fb-8e00-784372938870","d444393a-8b0e-4805-9721-9d223e54caab"],"version":1},{"code":"001","created":"2016-01-22 13:50:34.977","description":null,"deviceType":"PACKAGING_DESK","deviceTypeName":"打包平台","disable":false,"id":"45c06a7d-073e-4fc4-ae7c-d552bd27a35f","modified":"2016-01-22 13:50:34.977","name":"001","readerIds":[],"version":1}]
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
     * code : 001
     * created : 2015-01-13 09:21:25.413
     * description : sfsdfs
     * deviceType : HAND_SET
     * deviceTypeName : 手持机
     * disable : false
     * id : 3ed920c3-11da-4262-b47a-27e385e018ea
     * modified : 2015-01-13 09:21:25.413
     * name : 001
     * readerIds : ["16e7bb4e-198c-4340-b0ed-5c494d88cf17","546c1eee-4605-4c8b-bd08-3949fd021ff6","791578fb-81d5-45fb-8e00-784372938870","d444393a-8b0e-4805-9721-9d223e54caab"]
     * version : 1
     */

    private List<DataBean> data;

    public static DeviceInfoForID objectFromData(String str) {

        return new Gson().fromJson(str, DeviceInfoForID.class);
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
        private String created;
        private String description;
        private String deviceType;
        private String deviceTypeName;
        private boolean disable;
        private String id;
        private String modified;
        private String name;
        private int version;
        private List<String> readerIds;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
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

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public String getDeviceTypeName() {
            return deviceTypeName;
        }

        public void setDeviceTypeName(String deviceTypeName) {
            this.deviceTypeName = deviceTypeName;
        }

        public boolean isDisable() {
            return disable;
        }

        public void setDisable(boolean disable) {
            this.disable = disable;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public List<String> getReaderIds() {
            return readerIds;
        }

        public void setReaderIds(List<String> readerIds) {
            this.readerIds = readerIds;
        }
    }
}
