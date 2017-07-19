package com.lubin.chj.bean;

/**
 * @author DaiJiCheng
 * @time 2016/9/12  14:37
 * @desc ${登陆后返回值对象}
 */
public class TArrayOfReturnInfo {

    public String retunCode;//返回值代码（0000：代表验证通过）
    public String returnMsg;//返回值代码描述
    public String returnID;//会话权限ID字符串（如验证通过，则会返回该值，否则该值为空）

    public TArrayOfReturnInfo() {
    }

    public TArrayOfReturnInfo(String retunCode, String returnMsg, String returnID) {
        this.retunCode = retunCode;
        this.returnMsg = returnMsg;
        this.returnID = returnID;
    }

    public String getRetunCode() {
        return retunCode;
    }

    public void setRetunCode(String retunCode) {
        this.retunCode = retunCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getReturnID() {
        return returnID;
    }

    public void setReturnID(String returnID) {
        this.returnID = returnID;
    }

    @Override
    public String toString() {
        return "TArrayOfReturnInfo{" +
                "retunCode='" + retunCode + '\'' +
                ", returnMsg='" + returnMsg + '\'' +
                ", returnID='" + returnID + '\'' +
                '}';
    }
}
