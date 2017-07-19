package com.lubin.chj.modle.addedModel;

import com.lubin.chj.Listener.OnNetReqFinishListener;
import com.lubin.chj.utils.GlobleConfig;
import com.lubin.chj.utils.SoapUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lubin on 2017/2/9.
 */

public class CheckGtGwQyModel {

    public void CheckGtGwQy(String bh, String type, final OnNetReqFinishListener listener) {
        Map<String, Object> map = new HashMap<>();
        map.put("bh", bh);
        map.put("type", type);
        map.put("rightID", GlobleConfig.rightId);
        map.put("doMethod", "CheckGtGwQy");

        SoapUtil.GetWebServiceData(map, listener);
    }
}
