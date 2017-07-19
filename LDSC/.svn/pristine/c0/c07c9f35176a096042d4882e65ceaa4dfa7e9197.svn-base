package com.lide.app.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lide.app.MApplication;
import com.lide.app.bean.JsonToBean.ConfigList;
import com.lide.app.bean.JsonToBean.ForWareHouseID;
import com.lide.app.listener.OnLoadFinishListener;
import com.lide.app.model.LoginModel;
import com.lide.app.persistence.util.SPUtils;
import com.lide.app.persistence.util.Utils;
import com.lide.app.presenter.PInterface.ILoginPresenter;
import com.lide.app.ui.VInterface.ILoginView;
import com.lubin.bean.User;

import java.util.Map;

/**
 * Created by lubin on 2016/7/21.
 */
public class LoginPresenterImpl implements ILoginPresenter {

    ILoginView view;
    LoginModel model;

    public LoginPresenterImpl(ILoginView view) {
        this.view = view;
        this.model = new LoginModel();
    }

    @Override
    public void getNewestUser() {
        User newestUser = model.findNewestUser();
        if (newestUser != null)
            view.showNewestUser(newestUser);
    }

    @Override
    public void loginAtNet(Map<String, String> map) {
        //通过网络获取会话ID
        model.LoginAtNet(map, new OnLoadFinishListener() {
            @Override
            public void OnLoadFinish(Map<String, String> map) {
                if (map.get("statusCode").equals("200")) {
                    view.showResult("登录成功");
                    //登录成功后,把登录信息缓存在内存和存入本地中
                    model.saveUser(map);
                    //获取仓库ID
                    model.requestForWhereHouseID(model.getMapForWareHouseId(map.get("warehouseCode"))
                            , new OnLoadFinishListener() {
                                @Override
                                public void OnLoadFinish(Map<String, String> map) {
                                    if (map.get("statusCode").equals("200")) {
                                        try {
                                            String result1 = map.get("result");
                                            ForWareHouseID forWareHouseID = ForWareHouseID.objectFromData(result1);
                                            ForWareHouseID.DataBean dataBean = forWareHouseID.getData().get(0);
                                            Utils.setWareHouseName(dataBean.getName());
                                            String id = dataBean.getId();   //仓库ID
                                            Utils.getCurrentUser().setWarehouseId(id);
                                            Utils.getCurrentUser().update();
                                            //请求系统设置参数，
                                            // "shopInboundModel"：设置入库是按预装箱，还是混合方式入库，
                                            // "inboundAllowExceed"：是否允许入库超收，
                                            // "businessTakeStockFrom"：是否是UR盘点模式。
                                            model.queryConfigsList(new OnLoadFinishListener() {
                                                @Override
                                                public void OnLoadFinish(Map<String, String> map) {
                                                    try {
                                                        if (map.get("statusCode").equals("200")) {
                                                            String result2 = map.get("result");
                                                            Log.d("LoginPresenterImpl", result2);
                                                            ConfigList configList = new Gson().fromJson(result2, ConfigList.class);
                                                            String value1 = configList.getShopInboundMode().getValue1();
                                                            if (value1 != null) {
                                                                SPUtils.put(MApplication.getInstance(), "shopInboundModel", value1);
                                                            }
                                                            String value2 = configList.getInboundAllowExceed().getValue1();
                                                            if (value2 != null) {
                                                                SPUtils.put(MApplication.getInstance(), "inboundAllowExceed", value2);
                                                            }
                                                            String value3 = configList.getBusinessTakeStockFrom().getValue1();
                                                            if (value3 != null) {
                                                                SPUtils.put(MApplication.getInstance(), "businessTakeStockFrom", value3);
                                                            }
                                                            //跳转到入库界面
                                                            view.onLoginFinishAtNet();
                                                        }
                                                    } catch (JsonSyntaxException e) {
                                                        view.showDialog(e.getMessage());
                                                    }
                                                }
                                            });
                                        } catch (Exception e) {
                                            view.showDialog(e.getMessage());
                                        }
                                    }
                                }
                            });
                } else {
                    view.showDialog(map.get("result"));
                    view.showResult(null);
                }
            }
        });
    }

    @Override
    public void LoginAtOffLine(Map<String, String> map) {
        //离线登录模式
        if (model.LoginAtOffLine(map)) {
            //跳界面
            view.onLoginFinishAtOffLine();
        } else {
            //切换为在线登录模式
            view.changeMode();
        }
    }

    @Override
    public void saveWareHouseId(String wareHouseCode) {

    }

}
