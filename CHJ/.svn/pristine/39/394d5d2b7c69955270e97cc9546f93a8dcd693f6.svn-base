package com.lubin.chj.bean.jsonToBean;

import com.google.gson.Gson;

import java.util.List;


public class CabinetReturn {

    /**
     * returnCode : 0000
     * returnMsg : 查询成功！
     * rightID :
     * list : [{"qybh":"01010101","gwbh":"010101","gtbh":"01","qyh":1,"bzcfsl":20,"sjcfsl":0},
     *         {"qybh":"01010102","gwbh":"010101","gtbh":"01","qyh":2,"bzcfsl":20,"sjcfsl":0},
     *         {"qybh":"01010103","gwbh":"010101","gtbh":"01","qyh":3,"bzcfsl":20,"sjcfsl":0},
     *         {"qybh":"01010104","gwbh":"010101","gtbh":"01","qyh":4,"bzcfsl":20,"sjcfsl":0},
     *         {"qybh":"01010105","gwbh":"010101","gtbh":"01","qyh":5,"bzcfsl":20,"sjcfsl":0}]
     */

    private String returnCode;
    private String returnMsg;
    private String rightID;
    /**
     * qybh : 01010101
     * gwbh : 010101
     * gtbh : 01
     * qyh : 1
     * bzcfsl : 20
     * sjcfsl : 0
     */

    private List<ListBean> list;

    public static CabinetReturn objectFromData(String str) {

        return new Gson().fromJson(str, CabinetReturn.class);
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
        private String qybh;
        private String gwbh;
        private String gtbh;
        private int qyh;
        private int kycfsl;
        private int bzcfsl;
        private int sjcfsl;

        public int getKycfsl() {
            return kycfsl;
        }

        public void setKycfsl(int kycfsl) {
            this.kycfsl = kycfsl;
        }


        public static ListBean objectFromData(String str) {
            return new Gson().fromJson(str, ListBean.class);
        }

        public String getQybh() {
            return qybh;
        }

        public void setQybh(String qybh) {
            this.qybh = qybh;
        }

        public String getGwbh() {
            return gwbh;
        }

        public void setGwbh(String gwbh) {
            this.gwbh = gwbh;
        }

        public String getGtbh() {
            return gtbh;
        }

        public void setGtbh(String gtbh) {
            this.gtbh = gtbh;
        }

        public int getQyh() {
            return qyh;
        }

        public void setQyh(int qyh) {
            this.qyh = qyh;
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
    }
}
