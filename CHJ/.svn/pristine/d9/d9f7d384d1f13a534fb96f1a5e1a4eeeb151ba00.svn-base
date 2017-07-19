package com.lubin.chj.bean.jsonToBean;

import com.google.gson.Gson;

import java.util.List;

/**
 * @author DaiJiCheng
 * @time 2016/9/22  14:02
 * @desc ${TODD}
 */
public class SetLightReturn {

    /**
     * returnCode : 0000
     * returnMsg : 信号灯接口通讯成功！
     * rightID :
     * list : [{"gwbh":"010101","isOpen":true,"rCode":"0003","rMsg":"信号灯控制成功！"}]
     */

    private String returnCode;
    private String returnMsg;
    private String rightID;
    /**
     * gwbh : 010101
     * isOpen : true
     * rCode : 0003
     * rMsg : 信号灯控制成功！
     */

    private List<ListBean> list;

    public static SetLightReturn objectFromData(String str) {

        return new Gson().fromJson(str, SetLightReturn.class);
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
        private boolean isOpen;
        private String rCode;
        private String rMsg;

        public static ListBean objectFromData(String str) {

            return new Gson().fromJson(str, ListBean.class);
        }

        public String getGwbh() {
            return gwbh;
        }

        public void setGwbh(String gwbh) {
            this.gwbh = gwbh;
        }

        public boolean isIsOpen() {
            return isOpen;
        }

        public void setIsOpen(boolean isOpen) {
            this.isOpen = isOpen;
        }

        public String getRCode() {
            return rCode;
        }

        public void setRCode(String rCode) {
            this.rCode = rCode;
        }

        public String getRMsg() {
            return rMsg;
        }

        public void setRMsg(String rMsg) {
            this.rMsg = rMsg;
        }
    }
}
