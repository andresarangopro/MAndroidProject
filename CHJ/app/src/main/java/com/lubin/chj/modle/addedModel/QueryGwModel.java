package com.lubin.chj.modle.addedModel;

import android.util.Log;

import com.lubin.chj.Listener.OnNetReqFinishListener;
import com.lubin.chj.bean.jsonToBean.QueryGWListRetunBean;
import com.lubin.chj.modle.MInterface.addInterface.IQueryGW;
import com.lubin.chj.utils.GlobleConfig;
import com.lubin.chj.utils.SoapUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DaiJiCheng
 * @time 2017/1/22  9:13
 * @desc ${柜位查询的model}
 */
public class QueryGwModel {

   //柜位查询
    public void QueryGWList(int cfsl,String gwdx, final OnNetReqFinishListener listener) {
        Map<String, Object> map = new HashMap<>();
        map.put("cfsl", cfsl);
        map.put("gwdx", gwdx);
        map.put("rightID", GlobleConfig.rightId);
        map.put("doMethod", "QueryGWList");

        SoapUtil.GetWebServiceData(map,listener);
    }

}
