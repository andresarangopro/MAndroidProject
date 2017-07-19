package com.lubin.chj.modle.MInterface;

import com.lubin.chj.Listener.OnNetReqFinishListener;
import com.lubin.chj.bean.jsonToBean.CabinetReturn;

import java.util.List;
import java.util.Map;

/**
 * @author DaiJiCheng
 * @time 2016/9/22  10:39
 * @desc ${柜位查询方法接口}
 */
public interface ICabinetModel {
    //柜位查询
    Map<String, Object> getHashMap(String gw);
    void queryCarbinet(Map<String,Object> map, OnNetReqFinishListener listener);
    List<CabinetReturn.ListBean> getListBean();

    void QueryGWList(int cfsl,String gwdx, final OnNetReqFinishListener listener);


}
