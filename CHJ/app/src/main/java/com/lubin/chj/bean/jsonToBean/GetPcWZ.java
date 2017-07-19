package com.lubin.chj.bean.jsonToBean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by lubin on 2017/2/7.
 */

public class GetPcWZ {

    /**
     * returnCode : 0000
     * returnMsg : 批次位置信息获取成功！
     * rightID :
     * list : [{"gtbh":"","gwbh":"","qybh":"","pch":"8215000016"},{"gtbh":"","gwbh":"","qybh":"","pch":"16014992"},{"gtbh":"","gwbh":"","qybh":"","pch":"8215000069"},{"gtbh":"","gwbh":"","qybh":"","pch":"8215000023"},{"gtbh":"","gwbh":"","qybh":"","pch":"16014993"},{"gtbh":"","gwbh":"","qybh":"","pch":"0000000000"},{"gtbh":"","gwbh":"","qybh":"","pch":"16014991"},{"gtbh":"","gwbh":"","qybh":"","pch":"16014994"},{"gtbh":"","gwbh":"","qybh":"","pch":"16014999"},{"gtbh":"","gwbh":"","qybh":"","pch":"16014985"}]
     */

    private String returnCode;
    private String returnMsg;
    private String rightID;
    private List<ListBean> list;

    public static GetPcWZ objectFromData(String str) {

        return new Gson().fromJson(str, GetPcWZ.class);
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
        /**
         * gtbh :
         * gwbh :
         * qybh :
         * pch : 8215000016
         */

        private String gtbh;
        private String gwbh;
        private String qybh;
        private String pch;

        public static ListBean objectFromData(String str) {

            return new Gson().fromJson(str, ListBean.class);
        }

        public String getGtbh() {
            return gtbh;
        }

        public void setGtbh(String gtbh) {
            this.gtbh = gtbh;
        }

        public String getGwbh() {
            return gwbh;
        }

        public void setGwbh(String gwbh) {
            this.gwbh = gwbh;
        }

        public String getQybh() {
            return qybh;
        }

        public void setQybh(String qybh) {
            this.qybh = qybh;
        }

        public String getPch() {
            return pch;
        }

        public void setPch(String pch) {
            this.pch = pch;
        }
    }
}
