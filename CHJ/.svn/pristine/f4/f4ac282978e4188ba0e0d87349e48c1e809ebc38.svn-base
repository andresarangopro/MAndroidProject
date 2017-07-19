package com.lubin.chj.bean.jsonToBean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DaiJiCheng
 * @time 2017/1/22  11:22
 * @desc ${柜位查询结果}
 */
public class QueryGWListRetunBean {

    /**
     * returnCode : 0000
     * returnMsg : 柜位列表查询成功！
     * rightID :
     * list : [{"gwbh":"010101","bzcfsl":100,"sjcfsl":0,"kycfsl":100},{"gwbh":"010102","bzcfsl":100,"sjcfsl":0,"kycfsl":100},{"gwbh":"010103","bzcfsl":100,"sjcfsl":0,"kycfsl":100},{"gwbh":"010104","bzcfsl":100,"sjcfsl":0,"kycfsl":100},{"gwbh":"010105","bzcfsl":100,"sjcfsl":0,"kycfsl":100},{"gwbh":"010106","bzcfsl":100,"sjcfsl":0,"kycfsl":100},{"gwbh":"010107","bzcfsl":100,"sjcfsl":0,"kycfsl":100},{"gwbh":"010108","bzcfsl":100,"sjcfsl":0,"kycfsl":100},{"gwbh":"010109","bzcfsl":100,"sjcfsl":0,"kycfsl":100},{"gwbh":"010110","bzcfsl":100,"sjcfsl":0,"kycfsl":100}]
     */

    private String returnCode;
    private String returnMsg;
    private String rightID;
    /**
     * gwbh : 010101
     * bzcfsl : 100
     * sjcfsl : 0
     * kycfsl : 100
     */

    private List<ListBean> list;

    public static QueryGWListRetunBean objectFromData(String str) {

        return new Gson().fromJson(str, QueryGWListRetunBean.class);
    }

    public static List<QueryGWListRetunBean> arrayQueryGWListRetunBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<QueryGWListRetunBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getRightID() {
        return rightID;
    }

    public void setRightID(String rightID) {
        this.rightID = rightID;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private String gwbh;
        private int bzcfsl;
        private int sjcfsl;
        private int kycfsl;

        public static ListBean objectFromData(String str) {

            return new Gson().fromJson(str, ListBean.class);
        }

        public static List<ListBean> arrayListBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<ListBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public String getGwbh() {
            return gwbh;
        }

        public void setGwbh(String gwbh) {
            this.gwbh = gwbh;
        }

        public int getBzcfsl() {
            return bzcfsl;
        }

        public void setBzcfsl(int bzcfsl) {
            this.bzcfsl = bzcfsl;
        }

        public int getSjcfsl() {
            return sjcfsl;
        }

        public void setSjcfsl(int sjcfsl) {
            this.sjcfsl = sjcfsl;
        }

        public int getKycfsl() {
            return kycfsl;
        }

        public void setKycfsl(int kycfsl) {
            this.kycfsl = kycfsl;
        }
    }
}
