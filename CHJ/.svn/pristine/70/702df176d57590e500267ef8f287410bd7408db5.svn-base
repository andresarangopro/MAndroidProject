package com.lubin.chj.modle.MInterface;

import com.lubin.chj.Listener.OnNetReqFinishListener;
import com.lubin.chj.bean.Light;
import com.lubin.chj.bean.jsonToBean.SetLightReturn;

import java.util.List;
import java.util.Map;

/**
 * Created by lubin on 2016/9/29.
 */

public interface ISetLightModel {
    //控制亮灯
    Map<String, Object> getHashMapForLight(List<Light> list);

    void setLight(Map<String, Object> map, OnNetReqFinishListener listener);

    List<SetLightReturn.ListBean> getLightListBean();

    Map<String, Object> getColor();

    void setColor(Map<String, Object> map, OnNetReqFinishListener listener);

}
