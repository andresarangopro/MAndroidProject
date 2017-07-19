package com.lubin.chj.presenter;

import com.lubin.chj.Listener.OnNetReqFinishListener;
import com.lubin.chj.bean.PcInfo;
import com.lubin.chj.modle.MInterface.IStorePcModel;
import com.lubin.chj.modle.StorePcModelImpl;
import com.lubin.chj.presenter.IPresenter.IStorePresenter;
import com.lubin.chj.view.activity.VInterface.IStoreView;

import java.util.List;
import java.util.Map;

/**
 * Created by lubin on 2016/9/22.
 */
public class StorePresenterImpl implements IStorePresenter {
    IStoreView view;
    IStorePcModel model;

    public StorePresenterImpl(IStoreView view) {
        this.view = view;
        this.model = new StorePcModelImpl();
    }

    @Override
    public void StoreEpc(List<PcInfo> pcInfos, String barcode) {
        model.store(model.getHashMap(pcInfos, barcode), new OnNetReqFinishListener() {
            @Override
            public void OnNetReqFinish(Map<String, Object> hashMap) {
                String returnCode = hashMap.get("returnCode").toString();
                String returnMsg = hashMap.get("returnMsg").toString();
                if (returnCode.equals("0001")) {
                    view.ShowContinueDialog(returnMsg);
                } else if (returnCode.equals("0000")) {
                    view.refreshData();
                    view.ShowDialog(returnMsg);
                } else {
                    view.showDialogerror(returnMsg);
                }
            }
        });
    }

    @Override
    public void GetPcWZ(String pchList) {
        model.GetPcWZ(pchList,new OnNetReqFinishListener() {
            @Override
            public void OnNetReqFinish(Map<String, Object> hashMap) {
                String returnCode = hashMap.get("returnCode").toString();
                String returnMsg = hashMap.get("returnMsg").toString();
                if (returnCode.equals("0000")) {
                    view.ShowStore(model.getListPcWZBean());
                } else {
                    view.ShowDialog(returnMsg);
                }
            }
        });
    }
}