package com.lubin.chj.modle;

import android.util.Log;

import com.google.gson.Gson;
import com.lubin.chj.Listener.OnNetReqFinishListener;
import com.lubin.chj.bean.EPC;
import com.lubin.chj.bean.PcInfo;
import com.lubin.chj.bean.jsonToBean.CabinetReturn;
import com.lubin.chj.bean.jsonToBean.QueryPcReturn;
import com.lubin.chj.bean.jsonToBean.StoreReturn;
import com.lubin.chj.modle.MInterface.ITransFerPCModel;
import com.lubin.chj.utils.GlobleConfig;
import com.lubin.chj.utils.SoapUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DaiJiCheng
 * @time 2016/9/27  13:27
 * @desc ${TODD}
 */
public class TransFerPCModelImpl implements ITransFerPCModel {

    private QueryPcReturn mQueryPcReturn;
    private CabinetReturn  mCabinetReturn;
    private StoreReturn storeReturn;
    public static boolean mIngore = false;

    //查询
    @Override
    public void queryPc(Map<String, Object> map, final OnNetReqFinishListener listener) {
        SoapUtil.GetWebServiceData(map, new OnNetReqFinishListener() {
            @Override
            public void OnNetReqFinish(Map<String, Object> hashMap) {
                if (hashMap.get("result") != null) {
                    mQueryPcReturn = new QueryPcReturn().objectFromData(hashMap.get("result").toString());
                    hashMap.put("returnCode", mQueryPcReturn.getReturnCode());
                    hashMap.put("returnMsg", mQueryPcReturn.getReturnMsg());
                } else {
                    hashMap.put("returnCode", "9999");
                    hashMap.put("returnMsg", "网络请求失败");
                }
                listener.OnNetReqFinish(hashMap);
            }
        });
    }

    @Override
    public Map<String, Object> getHashMapForQueryPc(String pchs) {
        Map<String, Object> map = new HashMap<>();

        map.put("bhlx", "pch");
        map.put("bh", pchs);
        map.put("rightID", GlobleConfig.rightId);
        map.put("doMethod", "QueryPC");
        return map;
    }

    @Override
    public List<QueryPcReturn.ListBean> getQueryPcListBean() {
        List<QueryPcReturn.ListBean> list = mQueryPcReturn.getList();
        List<QueryPcReturn.ListBean> container = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            QueryPcReturn.ListBean listBean = list.get(i);
            if (listBean.getQybh() != null)
                container.add(listBean);
        }
        //按照区域编号对数据集合排序
        Collections.sort(container, new Comparator<QueryPcReturn.ListBean>() {
            @Override
            public int compare(QueryPcReturn.ListBean t1, QueryPcReturn.ListBean t2) {
                int i = Integer.parseInt((String) t1.getQybh());
                int j = Integer.parseInt((String) t2.getQybh());
                if (i > j) {
                    return 1;
                }
                if (i == j) {
                    return 0;
                }
                return -1;
            }
        });
        return container;
    }

    //拣货
    @Override
    public void fetchPC(Map<String, Object> map, final OnNetReqFinishListener listener) {
        SoapUtil.GetWebServiceData(map, new OnNetReqFinishListener() {
            @Override
            public void OnNetReqFinish(Map<String, Object> hashMap) {
                if (hashMap.get("result") != null) {
                    JSONObject result = null;
                    String returnCode = null;
                    String returnMsg = null;
                    try {
                        result = new JSONObject(hashMap.get("result").toString());
                        returnCode = result.getString("returnCode");
                        returnMsg = result.getString("returnMsg");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    hashMap.put("returnCode", returnCode);
                    hashMap.put("returnMsg", returnMsg);
                } else {
                    hashMap.put("returnCode", "9999");
                    hashMap.put("returnMsg", "网络请求失败");
                }
                listener.OnNetReqFinish(hashMap);
            }
        });
    }
    /**
     * @param list
     * @param ckpzh 直接拣货直接传空
     * @return
     */
    @Override
    public Map<String, Object> getHashMapFechPc(List<PcInfo> list, String ckpzh) {
        Map<String, Object> map = new HashMap<>();
        String jsonPcInfoList = new Gson().toJson(list);
        map.put("jsonPcInfoList", jsonPcInfoList);
        map.put("ckpzh", ckpzh);
        map.put("rightID", GlobleConfig.rightId);
        map.put("doMethod", "FetchPC ");
        return map;
    }

    //柜位查询

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
    public Map<String, Object> getHashMapForGwCX(String gw) {
        Map<String, Object> map = new HashMap<>();
        map.put("gwbh", gw);
        map.put("rightID", GlobleConfig.rightId);
        map.put("doMethod", "QueryGW");
        return map;
    }

    @Override
    public List<CabinetReturn.ListBean> getQueryGwListBean() {
        return mCabinetReturn.getList();
    }


    //存储货品

    @Override
    public void storePC(Map<String, Object> map, final OnNetReqFinishListener listener) {

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

    @Override
    public Map<String, Object> getHashMapForStore(List<EPC> list, String qyhm) {
        HashMap<String, Object> hashMap;
        List<PcInfo> listPcInfo = new ArrayList<>();
       /* for (EPC epc : list) {
            String e = epc.getEpc();
            String s = null;
            if (e.length() >= 10) {
                s = e.subSequence(e.length() - 10, e.length()).toString();
            } else {
                s = e;
            }
                pc.setQybh(qyhm);
            pc.setPch(s);
            listPcInfo.add(pc);
        }*/
        //TODO 作假的
        int i = 1500517307;
        for(int j = 0 ; j < 10 ; j++){
            PcInfo pc = new PcInfo();
            pc.setQybh("01010101");
            pc.setPch(String.valueOf(i++));
            listPcInfo.add(pc);
        }

        String jsonList = new Gson().toJson(listPcInfo);
        hashMap = new HashMap();
        hashMap.put("jsonPcInfoList", jsonList);
        hashMap.put("rightID", GlobleConfig.rightId);
        hashMap.put("ignore", mIngore);
        hashMap.put("doMethod", "StoragePC");
        return hashMap;
    }

    @Override
    public List<StoreReturn.ListBean> getStoreReturnListBean() {
        return storeReturn.getList();
    }
}
