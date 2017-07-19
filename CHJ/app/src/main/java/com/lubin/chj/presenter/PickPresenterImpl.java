package com.lubin.chj.presenter;

import com.lubin.chj.Listener.OnNetReqFinishListener;
import com.lubin.chj.bean.Light;
import com.lubin.chj.bean.PcInfo;
import com.lubin.chj.bean.jsonToBean.GetMyPzhListBean;
import com.lubin.chj.bean.jsonToBean.GetPzhDetailResult;
import com.lubin.chj.modle.MInterface.ISetLightModel;
import com.lubin.chj.modle.PickModel;
import com.lubin.chj.modle.SetLightModelImpl;
import com.lubin.chj.presenter.IPresenter.IPickPresenter;
import com.lubin.chj.view.activity.VInterface.IPickView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;


public class PickPresenterImpl implements IPickPresenter {
    private final PickModel model;
    IPickView view;
    ISetLightModel setLightModel;

    public PickPresenterImpl(IPickView view) {
        this.setLightModel = new SetLightModelImpl();
        model = new PickModel();
        this.view = view;
    }

    @Override
    public void QueryPc(String pcs) {
        model.queryPc(model.getHashMap(pcs), new OnNetReqFinishListener() {
            @Override
            public void OnNetReqFinish(Map<String, Object> hashMap) {
                String returnCode = hashMap.get("returnCode").toString();
                String returnMsg = hashMap.get("returnMsg").toString();
                if (returnCode.equals("0000")) {
                    view.ShowPc(model.getQueryPcListBean());
                }
            }
        });
    }

    public void fetchPC(final List<PcInfo> pcInfos, String pzh, String pzlx) {
        if (pcInfos.size() == 0) {
            return;
        }
        model.fetchPC(pcInfos, pzh, pzlx, new OnNetReqFinishListener() {
            @Override
            public void OnNetReqFinish(Map<String, Object> hashMap) {
                if (hashMap.get("result") != null) {
                    String returnCode = null;
                    String returnMsg = null;
                    try {
                        JSONObject result = new JSONObject(hashMap.get("result").toString());
                        returnCode = result.getString("returnCode");
                        returnMsg = result.getString("returnMsg");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (returnCode.equals("0000")) {
                        view.RemovePC(pcInfos);
                    }
                    view.ShowDialog(returnMsg);
                } else {
                    view.ShowDialog("网络请求失败!");
                }
            }
        });
    }

    public void QueryPcByPzh(String pzh) {
        model.queryPCByCkpzh(model.getHashMapForPZH(pzh), new OnNetReqFinishListener() {
            @Override
            public void OnNetReqFinish(Map<String, Object> hashMap) {
                String returnCode = hashMap.get("returnCode").toString();
                String returnMsg = hashMap.get("returnMsg").toString();
                if (returnCode.equals("0000")) {
                    view.ShowPc(model.getQueryPcBypzhListBean());
                } else {
                    view.ShowDialog(returnMsg);
                }
            }
        });
    }

    public void SetLight(final List<Light> lights) {
        if (lights.size() == 0) {
            return;
        }
        setLightModel.setLight(setLightModel.getHashMapForLight(lights), new OnNetReqFinishListener() {
            @Override
            public void OnNetReqFinish(Map<String, Object> hashMap) {
                String returnCode = hashMap.get("returnCode").toString();
                String returnMsg = hashMap.get("returnMsg").toString();
                if (returnCode.equals("0000")) {
                    if (lights.get(0).isOpen) {
                        view.ShowToast("柜位" + ":" + lights.get(0).gwbh + "亮灯成功！");
                    } else {
                        view.ShowToast("柜位" + ":" + lights.get(0).gwbh + "灭灯成功！");
                    }
                    view.changeBtnView();
                } else {
                    view.ShowToast("柜位" + ":" + lights.get(0).gwbh + returnMsg);
                    if (lights.get(0).gwbh.equals("空"))
                        view.voluntarilyLight("空");
                }

            }
        });
    }

    public void setVoluntarilyLight(final List<Light> lights, final int index, final int postion) {
        if (lights.size() == 0) {
            return;
        }
        setLightModel.setLight(setLightModel.getHashMapForLight(lights), new OnNetReqFinishListener() {
            @Override
            public void OnNetReqFinish(Map<String, Object> hashMap) {
                String returnCode = hashMap.get("returnCode").toString();
                String returnMsg = hashMap.get("returnMsg").toString();
                if (returnCode.equals("0000")) {
                    if (lights.get(0).isOpen) {
                        view.ShowToast("柜位" + ":" + lights.get(0).gwbh + "亮灯成功！");
                    } else {
                        view.ShowToast("柜位" + ":" + lights.get(0).gwbh + "灭灯成功！");
                        getIndex(index, postion);
                    }
                    if (index != 2)
                        view.changeBtnView();
                } else if (returnCode.equals("0006")) {
                    view.voluntarilyLight("0006");
                } else {
                    view.ShowToast("柜位" + ":" + lights.get(0).gwbh + returnMsg);
                    if (lights.get(0).gwbh.equals("空"))
                        view.voluntarilyLight("空");
                }

            }
        });
    }

    public void getIndex(int index, int postion) {
        switch (index) {
            case 1:
                view.voluntarilyLight("灭灯成功");
                break;
            case 2:
                view.voluntarilyLight("2", postion);
                break;
            case 3:
                view.voluntarilyLight("3");
                break;
        }
    }

    @Override
    public void nofetchPc(List<PcInfo> pcInfos, String ckpzh, String wqhyy) {
        if (pcInfos.size() == 0) {
            return;
        }
        model.noFetching(model.getHashMapNofetching(pcInfos, ckpzh, wqhyy), new OnNetReqFinishListener() {
            @Override
            public void OnNetReqFinish(Map<String, Object> hashMap) {
                String returnCode = hashMap.get("returnCode").toString();
                String returnMsg = hashMap.get("returnMsg").toString();
                if (returnCode.equals("0000")) {
                    view.ActivityFinish();
                } else {
                    view.ShowDialog(returnMsg);
                }
            }
        });
    }

    @Override
    public void getOrderList(String orderType, String pzlx) {
        model.GetMyPzhList(pzlx, orderType, new OnNetReqFinishListener() {
            @Override
            public void OnNetReqFinish(Map<String, Object> hashMap) {
                String result = null;
                if (hashMap.get("result") == null) {
                    view.ShowDialog("网络请求失败！");
                } else {
                    result = hashMap.get("result").toString();
                }
                if (result != null) {
                    GetMyPzhListBean data = GetMyPzhListBean.objectFromData(result);
                    if (data.getReturnCode().equals("0000")) {
                        view.ShowPc(data);
                    } else {
                        view.ShowDialog(data.getReturnMsg());
                    }
                }
            }
        });
    }

    @Override
    public void getOrderDetail(String orderCode, String pzlx) {
        model.GetPzhDetailResult(pzlx, orderCode, new OnNetReqFinishListener() {
            @Override
            public void OnNetReqFinish(Map<String, Object> hashMap) {
                String result = null;
                if (hashMap.get("result") == null) {
                    view.ShowDialog("网络请求失败！");
                } else {
                    result = hashMap.get("result").toString();
                }
                if (result != null) {
                    GetPzhDetailResult data = GetPzhDetailResult.objectFromData(result);
                    if (data.getReturnCode().equals("0000")) {
                        view.ShowPc(data);
                    } else {
                        view.ShowDialog(data.getReturnMsg());
                    }
                }
            }
        });
    }


}
