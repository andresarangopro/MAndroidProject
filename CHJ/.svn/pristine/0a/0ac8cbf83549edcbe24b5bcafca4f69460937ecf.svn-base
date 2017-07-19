package com.lubin.chj.modle.addedModel;

import android.util.Log;

import com.lubin.chj.Listener.OnNetReqFinishListener;
import com.lubin.chj.bean.jsonToBean.GetPcWZReturnBean;
import com.lubin.chj.utils.GlobleConfig;
import com.lubin.chj.utils.SoapUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DaiJiCheng
 * @time 2017/1/22  11:32
 * @desc ${获取批次位置信息}
 */
public class GetPcWZModel {

    public void GetPcWZ(String pchList, final OnNetReqFinishListener listener) {
        Map<String, Object> map = new HashMap<>();
        map.put("pchList", pchList);
        map.put("rightID", GlobleConfig.rightId);
        map.put("doMethod", "GetPcWZ");

        SoapUtil.GetWebServiceData(map,listener);
    }

}
