package com.lubin.chj.modle.MInterface.addInterface;

import com.lubin.chj.Listener.OnNetReqFinishListener;
import com.lubin.chj.bean.jsonToBean.GetPcWZReturnBean;

import java.util.List;
import java.util.Map;

/**
 * @author DaiJiCheng
 * @time 2017/1/22  11:28
 * @desc ${获取配货单列表}
 */
public interface IMovePC {
    //柜位列表查询接口
    void MovePC(Map<String, Object> map, OnNetReqFinishListener listener);

    /**
     *
     * @param pchList 批次号字符串（如果是多个批次号，则中间须用逗号隔开，如：XXX,XXX,XXX）
     * @param qybh  (目标)区域编号
     * @param ignore true:即使(目标)区域存放数量已满，仍继续存放；false：则不存放。
     * @return
     */
    Map<String, Object> getHashMapForMovePC(String pchList, String qybh, String ignore);
    /**
     * 返回的批次信息集合
     * @return
     */
    List<GetPcWZReturnBean.ListBean> GetMovePCBean();
}
