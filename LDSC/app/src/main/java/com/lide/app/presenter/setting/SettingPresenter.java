package com.lide.app.presenter.setting;

import com.lide.app.MApplication;
import com.lide.app.bean.JsonToBean.DeviceInfoForID;
import com.lide.app.listener.OnLoadFinishListener;
import com.lide.app.model.QueryModelImpl;
import com.lide.app.ui.VInterface.IDataFragmentView;
import com.lide.app.persistence.util.FormatUtils;
import com.lide.app.persistence.util.SPUtils;

import java.util.Map;

/**
 * Created by lubin on 2016/12/2.
 */

public class SettingPresenter {

    private QueryModelImpl queryModel;
    IDataFragmentView view;


    public SettingPresenter(IDataFragmentView view) {
        this.view = view;
        queryModel = new QueryModelImpl();
    }

    public void queryDeviceList(final String DeviceCode) {
        try {
            //查找设备ID
            queryModel.queryDeviceList(DeviceCode, new OnLoadFinishListener() {
                @Override
                public void OnLoadFinish(Map<String, String> map) {
                    DeviceInfoForID result = null;
                    if (map.get("statusCode").equals("200")) {
                        try {
                            result = FormatUtils.resultToBean(map.get("result"), DeviceInfoForID.class);
                        } catch (Exception e) {
                            view.showDialog(e.toString());
                        }
                        if (result.getData().size() == 0) {
                            view.showDialog("该设备号不存在");
                            SPUtils.put(MApplication.getInstance(), "deviceCode", "");
                            SPUtils.put(MApplication.getInstance(), "deviceId", "");
                        } else {
                            SPUtils.put(MApplication.getInstance(), "deviceCode", DeviceCode);
                            SPUtils.put(MApplication.getInstance(), "deviceId", result.getData().get(0).getId());
                            view.ShowToast("设置成功");
                        }
                    } else {
                        view.showDialog(map.get("result"));
                    }
                }
            });
        } catch (Exception e) {
            view.showDialog(e.toString());
        }
    }

}
