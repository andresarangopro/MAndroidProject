package com.lubin.chj.bean;

/**
 * @author DaiJiCheng
 * @time 2016/9/12  10:23
 * @desc ${柜位查询--区域类}
 */
public class QyInfo {
    public String qybh; //区域编号
    public String gwbh; //柜位编号
    public String gtbh; //柜台编号
    public String qyh; //区域号
    public int bzcfsl;  // 标准存放数量
    public int sjcfsl;  // 实际存放数量

    public QyInfo() {
    }

    public QyInfo(String qybh, int sjcfsl, String gtbh, String gwbh, int bzcfsl, String qyh) {
        this.qybh = qybh;
        this.sjcfsl = sjcfsl;
        this.gtbh = gtbh;
        this.gwbh = gwbh;
        this.bzcfsl = bzcfsl;
        this.qyh = qyh;
    }

    public String getQybh() {
        return qybh;
    }

    public void setQybh(String qybh) {
        this.qybh = qybh;
    }

    public int getSjcfsl() {
        return sjcfsl;
    }

    public void setSjcfsl(int sjcfsl) {
        this.sjcfsl = sjcfsl;
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

    public String getQyh() {
        return qyh;
    }

    public void setQyh(String qyh) {
        this.qyh = qyh;
    }

    public int getBzcfsl() {
        return bzcfsl;
    }

    public void setBzcfsl(int bzcfsl) {
        this.bzcfsl = bzcfsl;
    }

    @Override
    public String toString() {
        return "QyInfo{" +
                "qybh='" + qybh + '\'' +
                ", gwbh='" + gwbh + '\'' +
                ", gtbh='" + gtbh + '\'' +
                ", qyh='" + qyh + '\'' +
                ", bzcfsl=" + bzcfsl +
                ", sjcfsl=" + sjcfsl +
                '}';
    }
}
