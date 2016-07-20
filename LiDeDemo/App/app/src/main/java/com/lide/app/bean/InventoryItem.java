package com.lide.app.bean;

/**
 * @author DaiJiCheng
 * @time 2016/7/20  10:21
 * @desc ${TODD}
 */
public class InventoryItem {
    private  String  num;
    private  String  des;

    public InventoryItem(String num, String des) {
        this.num = num;
        this.des = des;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "InventoryItem{" +
                "num='" + num + '\'' +
                ", des='" + des + '\'' +
                '}';
    }
}
