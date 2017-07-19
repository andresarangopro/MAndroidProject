package com.lubin.chj.bean;

import java.util.List;

/**
 * @author DaiJiCheng
 * @time 2016/9/12  10:42
 * @desc ${2.2 批次(产品)查询接口--返回值对象 TArrayOfPcInfo}
 */
public class TArrayOfPcInfo {
    public String returnCode;//返回值代码（0000：代表查询成功）
    public String returnMsg;//返回值代码描述
    public List<PcInfo> pcinfos;    //批次(产品)类集合

    public TArrayOfPcInfo() {
    }

    public TArrayOfPcInfo(String returnCode, List<PcInfo> pcinfos, String returnMsg) {
        this.returnCode = returnCode;
        this.pcinfos = pcinfos;
        this.returnMsg = returnMsg;
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

    public List<PcInfo> getPcinfos() {
        return pcinfos;
    }

    public void setPcinfos(List<PcInfo> pcinfos) {
        this.pcinfos = pcinfos;
    }

    @Override
    public String toString() {
        return "TArrayOfPcInfo{" +
                "returnCode='" + returnCode + '\'' +
                ", returnMsg='" + returnMsg + '\'' +
                ", pcinfos=" + pcinfos +
                '}';
    }
}
