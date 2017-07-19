package com.lubin.chj.bean.jsonToBean;

import com.google.gson.Gson;

import java.util.List;

/**
 * @author DaiJiCheng
 * @time 2016/9/28  17:36
 * @desc ${保存盘点单返回数据}
 */
public class SavePDReturn {

    /**
     * returnCode : 0002
     * returnMsg : 盘点结果保存失败！
     * rightID :
     * list : []
     */

    private String returnCode;
    private String returnMsg;
    private String rightID;
    private List<?> list;

    public static SavePDReturn objectFromData(String str) {

        return new Gson().fromJson(str, SavePDReturn.class);
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

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
