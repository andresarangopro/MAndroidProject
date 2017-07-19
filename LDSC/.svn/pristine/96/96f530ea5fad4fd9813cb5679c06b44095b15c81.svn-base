package com.lide.app.bean.JsonToBean;

/**
 * Created by lubin on 2016/12/21.
 */

public class VersionUrl {

    /**
     * androidAppUpdateUrl1 : {"code":"ANDROID_APP_UPDATE_URL_1","description":"安卓手持机升级地址","title":"安卓手持机升级地址","value1":"http://192.168.8.253:8080/hdw-android/version.json","value2":null}
     * androidAppUpdateUrl2 : {"code":"ANDROID_APP_UPDATE_URL_2","description":"电子标签手持机升级地址","title":"电子标签手持机升级地址","value1":"http://192.168.8.200:8080/file-api","value2":null}
     * diAppUrl : {"code":"DI_APP_URL","description":"数据接口程序地址","title":"数据接口程序地址","value1":"http://127.0.0.1:8081","value2":null}
     * handsetAppUpdateUrl : {"code":"HANDSET_APP_UPDATE_URL","description":"手持机程序升级地址","title":"手持机程序升级地址","value1":"http://192.168.8.253:8080/file-api","value2":null}
     * imageServer : {"code":"IMAGE_SERVER","description":"图片服务器地址","title":"图片服务器地址","value1":"http://192.168.8.253:8080/file-api","value2":null}
     * middlewareAppUpdateUrl : {"code":"MIDDLEWARE_APP_UPDATE_URL","description":"中间件程序升级地址","title":"中间件程序升级地址","value1":"http://192.168.8.211:8080/Mid","value2":null}
     */

    public AndroidAppUpdateUrl1Bean androidAppUpdateUrl1;

    public static class AndroidAppUpdateUrl1Bean {
        /**
         * code : ANDROID_APP_UPDATE_URL_1
         * description : 安卓手持机升级地址
         * title : 安卓手持机升级地址
         * value1 : http://192.168.8.253:8080/hdw-android/version.json
         * value2 : null
         */

        private String code;
        private String description;
        private String title;
        private String value1;
        private Object value2;

        public static AndroidAppUpdateUrl1Bean objectFromData(String str) {

            return new com.google.gson.Gson().fromJson(str, AndroidAppUpdateUrl1Bean.class);
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getValue1() {
            return value1;
        }

        public void setValue1(String value1) {
            this.value1 = value1;
        }

        public Object getValue2() {
            return value2;
        }

        public void setValue2(Object value2) {
            this.value2 = value2;
        }
    }
}
