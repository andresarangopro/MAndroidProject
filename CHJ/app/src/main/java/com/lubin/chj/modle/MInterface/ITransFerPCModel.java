package com.lubin.chj.modle.MInterface;

import com.lubin.chj.Listener.OnNetReqFinishListener;
import com.lubin.chj.bean.EPC;
import com.lubin.chj.bean.PcInfo;
import com.lubin.chj.bean.jsonToBean.CabinetReturn;
import com.lubin.chj.bean.jsonToBean.QueryPcReturn;
import com.lubin.chj.bean.jsonToBean.StoreReturn;

import java.util.List;
import java.util.Map;

/**
 * @author DaiJiCheng
 * @time 2016/9/27  13:08
 * @desc ${货品调整}
 */
public interface ITransFerPCModel {

    //直接拣货查询
    void queryPc(Map<String, Object> map, OnNetReqFinishListener listener);

    Map<String, Object> getHashMapForQueryPc(String pchs);

    List<QueryPcReturn.ListBean> getQueryPcListBean();

    //拣货
    void fetchPC(Map<String, Object> map, OnNetReqFinishListener listener);

    Map<String, Object> getHashMapFechPc(List<PcInfo> list, String ckpzh);

    //柜位查询
    Map<String, Object> getHashMapForGwCX(String gw);

    void queryCarbinet(Map<String, Object> map, OnNetReqFinishListener listener);

    List<CabinetReturn.ListBean> getQueryGwListBean();

    //存储
    void storePC(Map<String, Object> map, OnNetReqFinishListener listener);

    Map<String, Object> getHashMapForStore(List<EPC> list, String qyhm);

    List<StoreReturn.ListBean> getStoreReturnListBean();
}