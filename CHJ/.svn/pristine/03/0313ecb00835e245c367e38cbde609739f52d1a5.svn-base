package com.lubin.chj.modle.MInterface.addInterface;

import com.lubin.chj.Listener.OnNetReqFinishListener;
import com.lubin.chj.bean.jsonToBean.GetPcWZReturnBean;

import java.util.List;
import java.util.Map;

/**
 * @author DaiJiCheng
 * @time 2017/1/22  11:28
 * @desc ${扫描批次时，实时读取其相应的存放位置}
 */
public interface IGetPcWZ {
    //读取其相应的存放位置
    void GetPcWZ(Map<String,Object> map, OnNetReqFinishListener listener);

    /**
     *
     * @param pchList   批次号字符串可以是多个,中间用","隔开
     * @return
     */
    Map<String, Object> getHashMapForGetPcWZ(String pchList);
    /**
     * 返回的批次信息集合
     * @return
     */
    List<GetPcWZReturnBean.ListBean> getPCWZListBean();
}
