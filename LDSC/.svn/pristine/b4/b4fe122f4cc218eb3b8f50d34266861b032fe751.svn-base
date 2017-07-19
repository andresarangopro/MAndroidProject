package com.lide.app.bean.JsonToBean;

import com.google.gson.Gson;

/**
 * Created by huangjianxionh on 2016/11/9.
 */
//{"configBiz":{"code":"HANDSET_APP_UPDATE_URL","description":"手持机程序升级地址","title":"手持机程序升级地址","value1":"http://192.168.8.253:8080/file-api","value2":null}}
public class CheckVersion {
    private ConfigBiz configBiz;
    public ConfigBiz getConfigBiz() {
        return configBiz;
    }

    public void setConfigBiz(ConfigBiz configBiz) {
        this.configBiz = configBiz;
    }
    public static CheckVersion objectFromData(String str) {
        return new Gson().fromJson(str, CheckVersion.class);
    }
    public static class ConfigBiz{
        private String code;
        private String description;
        private String title;
        private String value1;
        private String value2;

        public static ConfigBiz objectFromData(String str) {
            return new Gson().fromJson(str, ConfigBiz.class);
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

        public String getValue2() {
            return value2;
        }

        public void setValue2(String value2) {
            this.value2 = value2;
        }
    }
}
