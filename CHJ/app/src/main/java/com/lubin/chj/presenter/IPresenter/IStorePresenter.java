package com.lubin.chj.presenter.IPresenter;

import com.lubin.chj.bean.PcInfo;

import java.util.List;

/**
 * Created by lubin on 2016/9/22.
 */
public interface IStorePresenter {
    void StoreEpc(List<PcInfo> pcInfos, String barcode);
    void GetPcWZ(String pchList);
}
