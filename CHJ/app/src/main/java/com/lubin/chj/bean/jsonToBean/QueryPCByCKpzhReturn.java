package com.lubin.chj.bean.jsonToBean;

import com.google.gson.Gson;

import java.util.List;

/**
 * @author DaiJiCheng
 * @time 2016/9/26  10:41
 * @desc ${TODD}
 */
public class QueryPCByCKpzhReturn {

    /**
     * returnCode : 0000
     * returnMsg : 出库凭证号信息查询成功！
     * rightID :
     * list : [{"gtbh":null,"gwbh":null,"pch":"1500317348","wlh":"AA0001014000","rkpzh":null,"qybh":null,"cpzt":null,"cpx":"","cpxms":"","ks":"","ksms":"","pl":"","plms":"","hlhx":"","hlhxms":"","zchicun":"","ztjtf":0,"cjry":null,"cjsj":"/Date(-62135596800000)/","xgry":null,"xgsj":"/Date(-62135596800000)/","wqhyy":null,"fkxx":"批次号 1500317348 包含于SAP出库单下，但在本系统库存中查询不到相关信息，可能该批次号尚未入库！","xnpzh":null,"pdjg":null,"dqqybh":null}]
     */

    private String returnCode;
    private String returnMsg;
    private String rightID;
    /**
     * gtbh : null
     * gwbh : null
     * pch : 1500317348
     * wlh : AA0001014000
     * rkpzh : null
     * qybh : null
     * cpzt : null
     * cpx :
     * cpxms :
     * ks :
     * ksms :
     * pl :
     * plms :
     * hlhx :
     * hlhxms :
     * zchicun :
     * ztjtf : 0
     * cjry : null
     * cjsj : /Date(-62135596800000)/
     * xgry : null
     * xgsj : /Date(-62135596800000)/
     * wqhyy : null
     * fkxx : 批次号 1500317348 包含于SAP出库单下，但在本系统库存中查询不到相关信息，可能该批次号尚未入库！
     * xnpzh : null
     * pdjg : null
     * dqqybh : null
     */

    private List<ListBean> list;

    public static QueryPCByCKpzhReturn objectFromData(String str) {

        return new Gson().fromJson(str, QueryPCByCKpzhReturn.class);
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
        private Object gtbh;
        private Object gwbh;
        private String pch;
        private String wlh;
        private Object rkpzh;
        private Object qybh;
        private Object cpzt;
        private String cpx;
        private String cpxms;
        private String ks;
        private String ksms;
        private String pl;
        private String plms;
        private String hlhx;
        private String hlhxms;
        private String zchicun;
        private int ztjtf;
        private Object cjry;
        private String cjsj;
        private Object xgry;
        private String xgsj;
        private Object wqhyy;
        private String fkxx;
        private Object xnpzh;
        private Object pdjg;
        private Object dqqybh;

        public static ListBean objectFromData(String str) {

            return new Gson().fromJson(str, ListBean.class);
        }

        public Object getGtbh() {
            return gtbh;
        }

        public void setGtbh(Object gtbh) {
            this.gtbh = gtbh;
        }

        public Object getGwbh() {
            return gwbh;
        }

        public void setGwbh(Object gwbh) {
            this.gwbh = gwbh;
        }

        public String getPch() {
            return pch;
        }

        public void setPch(String pch) {
            this.pch = pch;
        }

        public String getWlh() {
            return wlh;
        }

        public void setWlh(String wlh) {
            this.wlh = wlh;
        }

        public Object getRkpzh() {
            return rkpzh;
        }

        public void setRkpzh(Object rkpzh) {
            this.rkpzh = rkpzh;
        }

        public Object getQybh() {
            return qybh;
        }

        public void setQybh(Object qybh) {
            this.qybh = qybh;
        }

        public Object getCpzt() {
            return cpzt;
        }

        public void setCpzt(Object cpzt) {
            this.cpzt = cpzt;
        }

        public String getCpx() {
            return cpx;
        }

        public void setCpx(String cpx) {
            this.cpx = cpx;
        }

        public String getCpxms() {
            return cpxms;
        }

        public void setCpxms(String cpxms) {
            this.cpxms = cpxms;
        }

        public String getKs() {
            return ks;
        }

        public void setKs(String ks) {
            this.ks = ks;
        }

        public String getKsms() {
            return ksms;
        }

        public void setKsms(String ksms) {
            this.ksms = ksms;
        }

        public String getPl() {
            return pl;
        }

        public void setPl(String pl) {
            this.pl = pl;
        }

        public String getPlms() {
            return plms;
        }

        public void setPlms(String plms) {
            this.plms = plms;
        }

        public String getHlhx() {
            return hlhx;
        }

        public void setHlhx(String hlhx) {
            this.hlhx = hlhx;
        }

        public String getHlhxms() {
            return hlhxms;
        }

        public void setHlhxms(String hlhxms) {
            this.hlhxms = hlhxms;
        }

        public String getZchicun() {
            return zchicun;
        }

        public void setZchicun(String zchicun) {
            this.zchicun = zchicun;
        }

        public int getZtjtf() {
            return ztjtf;
        }

        public void setZtjtf(int ztjtf) {
            this.ztjtf = ztjtf;
        }

        public Object getCjry() {
            return cjry;
        }

        public void setCjry(Object cjry) {
            this.cjry = cjry;
        }

        public String getCjsj() {
            return cjsj;
        }

        public void setCjsj(String cjsj) {
            this.cjsj = cjsj;
        }

        public Object getXgry() {
            return xgry;
        }

        public void setXgry(Object xgry) {
            this.xgry = xgry;
        }

        public String getXgsj() {
            return xgsj;
        }

        public void setXgsj(String xgsj) {
            this.xgsj = xgsj;
        }

        public Object getWqhyy() {
            return wqhyy;
        }

        public void setWqhyy(Object wqhyy) {
            this.wqhyy = wqhyy;
        }

        public String getFkxx() {
            return fkxx;
        }

        public void setFkxx(String fkxx) {
            this.fkxx = fkxx;
        }

        public Object getXnpzh() {
            return xnpzh;
        }

        public void setXnpzh(Object xnpzh) {
            this.xnpzh = xnpzh;
        }

        public Object getPdjg() {
            return pdjg;
        }

        public void setPdjg(Object pdjg) {
            this.pdjg = pdjg;
        }

        public Object getDqqybh() {
            return dqqybh;
        }

        public void setDqqybh(Object dqqybh) {
            this.dqqybh = dqqybh;
        }
    }
}
