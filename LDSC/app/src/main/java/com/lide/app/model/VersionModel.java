package com.lide.app.model;

import com.lide.app.listener.OnLoadFinishListener;
import com.lide.app.persistence.util.NetWorkForGetUtil;
import com.lide.app.persistence.util.Utils;

/**
 * Created by huangjianxionh on 2016/11/8.
 */

public class VersionModel {
    //查询版本信息的地址
    public void queryVersionUrl(final OnLoadFinishListener listener) throws Exception {

        String apiMethod = "api/system-setting/list";
        String postUrl = Utils.apiUrl + apiMethod;

        NetWorkForGetUtil.startNetWord(postUrl, listener);
    }
    //查询版本信息
    public void queryVersion(String url, final OnLoadFinishListener listener) throws Exception {
        NetWorkForGetUtil.startNetWord(url, listener);
    }

}


