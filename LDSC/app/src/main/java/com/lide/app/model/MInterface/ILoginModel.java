package com.lide.app.model.MInterface;

import com.lide.app.listener.OnLoadFinishListener;
import com.lubin.bean.User;

import java.util.Map;

/**
 * Created by lubin on 2016/7/21.
 */
public interface ILoginModel {

    User findNewestUser();

    void LoginAtNet(final Map<String, String> map, final OnLoadFinishListener listener);

    boolean LoginAtOffLine(final Map<String, String> map);

    void saveUser(Map<String, String> map);

    //请求仓库ID
    Map<String,String> getMapForWareHouseId(String wareHouseCode);
    void requestForWhereHouseID(Map<String,String>map,OnLoadFinishListener listener);

}