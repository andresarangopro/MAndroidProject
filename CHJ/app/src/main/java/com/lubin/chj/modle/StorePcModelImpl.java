package com.lubin.chj.modle;

import android.util.Log;

import com.google.gson.Gson;
import com.lubin.chj.Listener.OnNetReqFinishListener;
import com.lubin.chj.bean.PcInfo;
import com.lubin.chj.bean.jsonToBean.GetPcWZReturnBean;
import com.lubin.chj.bean.jsonToBean.StoreReturn;
import com.lubin.chj.modle.MInterface.IStorePcModel;
import com.lubin.chj.utils.GlobleConfig;
import com.lubin.chj.utils.SoapUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DaiJiCheng
 * @time 2016/9/21  16:30
 * @desc ${TODD}
 */
public class StorePcModelImpl implements IStorePcModel {


    private StoreReturn storeReturn;
    private GetPcWZReturnBean getPcWZReturnBean;
    public static boolean mIngore = false;

    @Override
    public void store(Map<String, Object> map, final OnNetReqFinishListener listener) {

        SoapUtil.GetWebServiceData(map, new OnNetReqFinishListener() {
            @Override
            public void OnNetReqFinish(Map<String, Object> hashMap) {
                Log.d("test", hashMap.toString());
                Object result = hashMap.get("result");
                if (result != null) {
                    storeReturn = StoreReturn.objectFromData(result.toString());
                    hashMap.put("returnCode", storeReturn.getReturnCode());
                    hashMap.put("returnMsg", storeReturn.getReturnMsg());
                } else {
                    hashMap.put("returnCode", "9999");
                    hashMap.put("returnMsg", "网络请求失败");
                }

                listener.OnNetReqFinish(hashMap);
            }
        });

    }

    public Map<String, Object> getHashMap(List<PcInfo> pcInfos, String qybh) {
        HashMap<String, Object> hashMap;
        for (PcInfo pc : pcInfos) {
            pc.setQybh(qybh);
        }

        String jsonList = new Gson().toJson(pcInfos);
        hashMap = new HashMap();
        hashMap.put("jsonPcInfoList", jsonList);
        hashMap.put("rightID", GlobleConfig.rightId);
        hashMap.put("ignore", mIngore);
        hashMap.put("doMethod", "StoragePC");
        return hashMap;
    }

    @Override
    public Map<String, Object> getHashMapPcMz(List<GetPcWZReturnBean.ListBean> pcInfos, String qyhm) {
        HashMap<String, Object> hashMap;
        for (GetPcWZReturnBean.ListBean pc : pcInfos) {
            pc.setQybh(qyhm);
        }

        String jsonList = new Gson().toJson(pcInfos);
        hashMap = new HashMap();
        hashMap.put("jsonPcInfoList", jsonList);
        hashMap.put("rightID", GlobleConfig.rightId);
        hashMap.put("ignore", mIngore);
        hashMap.put("doMethod", "StoragePC");
        return hashMap;
    }

    @Override
    public List<StoreReturn.ListBean> getListBean() {
        return storeReturn.getList();
    }

    @Override
    public void GetPcWZ(String pchList, final OnNetReqFinishListener listener) {
        Map<String, Object> map = new HashMap<>();
        map.put("pchList", pchList);
        map.put("rightID", GlobleConfig.rightId);
        map.put("doMethod", "GetPcWZ");

        SoapUtil.GetWebServiceData(map, new OnNetReqFinishListener() {
            @Override
            public void OnNetReqFinish(Map<String, Object> hashMap) {
                Log.d("test", hashMap.toString());
                Object result = hashMap.get("result");
                if (result != null) {
                    getPcWZReturnBean = GetPcWZReturnBean.objectFromData(result.toString());
                    hashMap.put("returnCode", getPcWZReturnBean.getReturnCode());
                    hashMap.put("returnMsg", getPcWZReturnBean.getReturnMsg());
                } else {
                    hashMap.put("returnCode", "9999");
                    hashMap.put("returnMsg", "网络请求失败");
                }

                listener.OnNetReqFinish(hashMap);
            }
        });
    }

    @Override
    public List<GetPcWZReturnBean.ListBean> getListPcWZBean() {
        return getPcWZReturnBean.getList();
    }
}
