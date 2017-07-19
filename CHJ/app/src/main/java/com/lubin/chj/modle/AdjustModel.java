package com.lubin.chj.modle;

import com.lubin.chj.Listener.OnNetReqFinishListener;
import com.lubin.chj.utils.GlobleConfig;
import com.lubin.chj.utils.SoapUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lubin on 2017/2/7.
 */

public class AdjustModel {

    public void QueryPc(String pchs, final OnNetReqFinishListener listener) {
        Map<String, Object> map = new HashMap<>();
        map.put("bhlx", "pch");
        map.put("bh", pchs);
        map.put("rightID", GlobleConfig.rightId);
        map.put("doMethod", "QueryPC");

        SoapUtil.GetWebServiceData(map, listener);
    }

    public void MovePC(String pchList, String qybh, final OnNetReqFinishListener listener) {
        Map<String, Object> map = new HashMap<>();
        map.put("pchList", pchList);
        map.put("qybh", qybh);
        map.put("rightID", GlobleConfig.rightId);
        map.put("doMethod", "MovePC");

        SoapUtil.GetWebServiceData(map, listener);
    }
}
