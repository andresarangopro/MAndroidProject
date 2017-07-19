package com.lubin.chj.modle.MInterface.addInterface;

import com.lubin.chj.Listener.OnNetReqFinishListener;
import com.lubin.chj.bean.jsonToBean.GetMyPzhListBean;

import java.util.List;
import java.util.Map;

/**
 * @author DaiJiCheng
 * @time 2017/1/22  11:28
 * @desc ${获取配货单列表}
 */
public interface IGetMyPzhList {
    //柜位列表查询接口
    void GetMyPzhList(Map<String, Object> map, OnNetReqFinishListener listener);

    /**
     *
     * @param pzlx      凭证类型（目前固定传入“phdh”）
     * @param orderType 排序类型（值包括“zd、dj、pzh”三种类型，分别代表“按专店、按等级、按凭证号”排序）
     * @return
     */
    Map<String, Object> getHashMapForGetMyPzhList(String pzlx,String orderType);
    /**
     * 返回的批次信息集合
     * @return
     */
    List<GetMyPzhListBean.ListBean> GetMyPzhListBean();
}
