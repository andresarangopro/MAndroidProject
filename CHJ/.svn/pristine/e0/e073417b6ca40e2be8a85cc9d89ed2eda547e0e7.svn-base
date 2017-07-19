package com.lubin.chj.modle.MInterface.addInterface;

import com.lubin.chj.Listener.OnNetReqFinishListener;
import com.lubin.chj.bean.jsonToBean.QueryGWListRetunBean;

import java.util.List;
import java.util.Map;

/**
 * @author DaiJiCheng
 * @time 2017/1/22  9:09
 * @desc ${柜位查询接口}
 */
public interface IQueryGW {
    //柜位列表查询接口
    void QueryGWList(Map<String,Object> map, OnNetReqFinishListener listener);

    /**
     *
     * @param cfsl  存放数量
     * @param gwdx   柜位大小
     * @return
     */
    Map<String, Object> getHashMapForGwCX(int cfsl,String gwdx);

    /**
     * 返回的柜位集合
     * @return
     */
    List<QueryGWListRetunBean.ListBean> getQueryGwListBean();
}
