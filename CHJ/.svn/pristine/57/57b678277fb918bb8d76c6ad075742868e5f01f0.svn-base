package com.lubin.chj.bean.jsonToBean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DaiJiCheng
 * @time 2017/1/22  13:15
 * @desc ${获取批次位置信息返回结果}
 */
public class GetPcWZReturnBean {

    /**
     * returnCode : 0000
     * returnMsg : 批次位置信息获取成功！
     * rightID :
     * list : [{"gtbh":"","gwbh":"","qybh":"","pch":"1500616392"},{"gtbh":"","gwbh":"","qybh":"","pch":"1500616391"},{"gtbh":"","gwbh":"","qybh":"","pch":"1404155727"}]
     */

    private String returnCode;
    private String returnMsg;
    private String rightID;
    /**
     * gtbh :
     * gwbh :
     * qybh :
     * pch : 1500616392
     */

    private List<ListBean> list;

    public static GetPcWZReturnBean objectFromData(String str) {

        return new Gson().fromJson(str, GetPcWZReturnBean.class);
    }

    public static List<GetPcWZReturnBean> arrayGetPcWZReturnBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<GetPcWZReturnBean>>() {
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
        private String gtbh;
        private String gwbh;
        private String qybh;
        private String pch;

        public static ListBean objectFromData(String str) {

            return new Gson().fromJson(str, ListBean.class);
        }

        public static List<ListBean> arrayListBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<ListBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
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
