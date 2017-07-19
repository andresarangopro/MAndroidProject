package com.lubin.chj.bean;

import java.util.List;

/**
 * @author DaiJiCheng
 * @time 2016/9/12  10:06
 * @desc ${柜位查询接口--返回值对象}
 */
public class TArrayOfQyInfo {
    public String returnCode; //返回值代码（0000：代表查询成功）
    public String returnMsg; //返回值代码描述
    public List<QyInfo> qyInfos;//区域类集合

    public TArrayOfQyInfo() {
    }

    public TArrayOfQyInfo(String returnCode, String returnMsg, List<QyInfo> qyInfos) {
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
        this.qyInfos = qyInfos;
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

    public List<QyInfo> getQyInfos() {
        return qyInfos;
    }

    public void setQyInfos(List<QyInfo> qyInfos) {
        this.qyInfos = qyInfos;
    }

    @Override
    public String toString() {
        return "TArrayOfQyInfo{" +
                "returnCode='" + returnCode + '\'' +
                ", returnMsg='" + returnMsg + '\'' +
                ", qyInfos=" + qyInfos +
                '}';
    }
}
