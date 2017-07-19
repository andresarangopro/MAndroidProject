package com.lubin.chj.bean.jsonToBean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DaiJiCheng
 * @time 2017/1/22  13:48
 * @desc ${凭证号列表数据}
 */
public class GetMyPzhListBean {

    /**
     * returnCode : 0000
     * returnMsg : 凭证号列表数据获取成功！
     * rightID :
     * list : [{"zd":"01ST","dj":"A","djms":"","pzh":"0000000000","zsl":2,"yjsl":0},{"zd":"01ST","dj":"A","djms":"","pzh":"0000000001","zsl":2,"yjsl":0},{"zd":"01ST","dj":"A","djms":"","pzh":"0000000003","zsl":0,"yjsl":0}]
     */

    private String returnCode;
    private String returnMsg;
    private String rightID;
    /**
     * zd : 01ST
     * dj : A
     * djms :
     * pzh : 0000000000
     * zsl : 2
     * yjsl : 0
     */

    private List<ListBean> list;

    public static GetMyPzhListBean objectFromData(String str) {

        return new Gson().fromJson(str, GetMyPzhListBean.class);
    }

    public static List<GetMyPzhListBean> arrayGetMyPzhListBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<GetMyPzhListBean>>() {
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
        private String zd;
        private String dj;
        private String djms;
        private String pzh;
        private int zsl;
        private int yjsl;

        public static ListBean objectFromData(String str) {

            return new Gson().fromJson(str, ListBean.class);
        }

        public static List<ListBean> arrayListBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<ListBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public String getZd() {
            return zd;
        }

        public void setZd(String zd) {
            this.zd = zd;
        }

        public String getDj() {
            return dj;
        }

        public void setDj(String dj) {
            this.dj = dj;
        }

        public String getDjms() {
            return djms;
        }

        public void setDjms(String djms) {
            this.djms = djms;
        }

        public String getPzh() {
            return pzh;
        }

        public void setPzh(String pzh) {
            this.pzh = pzh;
        }

        public int getZsl() {
            return zsl;
        }

        public void setZsl(int zsl) {
            this.zsl = zsl;
        }

        public int getYjsl() {
            return yjsl;
        }

        public void setYjsl(int yjsl) {
            this.yjsl = yjsl;
        }
    }
}
