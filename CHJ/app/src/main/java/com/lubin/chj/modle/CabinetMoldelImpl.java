package com.lubin.chj.modle;

import android.util.Log;

import com.lubin.chj.Listener.OnNetReqFinishListener;
import com.lubin.chj.bean.jsonToBean.CabinetReturn;
import com.lubin.chj.modle.MInterface.ICabinetModel;
import com.lubin.chj.utils.GlobleConfig;
import com.lubin.chj.utils.SoapUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DaiJiCheng
 * @time 2016/9/22  11:31
 * @desc ${TODD}
 */
public class CabinetMoldelImpl implements ICabinetModel {

    private CabinetReturn mCabinetReturn;

    @Override
    public Map<String, Object> getHashMap(String gw) {
        Map<String, Object> map = new HashMap<>();
        map.put("gwbh", gw);
        map.put("rightID", GlobleConfig.rightId);
        map.put("doMethod", "QueryGW");
        return map;
    }

    @Override
    public void queryCarbinet(Map<String, Object> map, final OnNetReqFinishListener listener) {
        SoapUtil.GetWebServiceData(map, new OnNetReqFinishListener() {
            @Override
            public void OnNetReqFinish(Map<String, Object> hashMap) {
                Log.d("test", hashMap.toString());
                Object result = hashMap.get("result");
                if (result != null) {
                    mCabinetReturn = CabinetReturn.objectFromData(result.toString());
                    hashMap.put("returnCode", mCabinetReturn.getReturnCode());
                    hashMap.put("returnMsg", mCabinetReturn.getReturnMsg());
                } else {
                    hashMap.put("returnCode", "9999");
                    hashMap.put("returnMsg", "网络请求失败");
                }
                listener.OnNetReqFinish(hashMap);
            }
        });
    }

    @Override
    public List<CabinetReturn.ListBean> getListBean() {
        return mCabinetReturn.getList();
    }


    //柜位查询
    public void QueryGWList(int cfsl,String gwdx, final OnNetReqFinishListener listener) {
        Map<String, Object> map = new HashMap<>();
        map.put("cfsl", cfsl);
        map.put("gwdx", gwdx);
        map.put("rightID", GlobleConfig.rightId);
        map.put("doMethod", "QueryGWList");

        SoapUtil.GetWebServiceData(map, new OnNetReqFinishListener() {
            @Override
            public void OnNetReqFinish(Map<String, Object> hashMap) {
                Log.d("test", hashMap.toString());
                Object result = hashMap.get("result");
                if (result != null) {
                    mCabinetReturn = CabinetReturn.objectFromData(result.toString());
                    hashMap.put("returnCode", mCabinetReturn.getReturnCode());
                    hashMap.put("returnMsg", mCabinetReturn.getReturnMsg());
                } else {
                    hashMap.put("returnCode", "9999");
                    hashMap.put("returnMsg", "网络请求失败");
                }
                listener.OnNetReqFinish(hashMap);
            }
        });
    }

}
