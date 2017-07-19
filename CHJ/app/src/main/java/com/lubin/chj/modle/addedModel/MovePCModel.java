package com.lubin.chj.modle.addedModel;

import com.lubin.chj.Listener.OnNetReqFinishListener;
import com.lubin.chj.utils.GlobleConfig;
import com.lubin.chj.utils.SoapUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DaiJiCheng
 * @time 2017/1/22  13:43
 * @desc ${移柜管理}
 */
public class MovePCModel {
    public void MovePC(String pchList, String qybh, String ignore, final OnNetReqFinishListener listener) {
        Map<String, Object> map = new HashMap<>();
        map.put("pchList", pchList);
        map.put("qybh", qybh);
        map.put("ignore", ignore);
        map.put("rightID", GlobleConfig.rightId);
        map.put("doMethod", "MovePC");

        SoapUtil.GetWebServiceData(map, listener);
    }
}
