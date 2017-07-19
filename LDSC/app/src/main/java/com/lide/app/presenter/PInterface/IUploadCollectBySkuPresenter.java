package com.lide.app.presenter.PInterface;


import com.lubin.bean.TakeStockSkuCollect;

public interface IUploadCollectBySkuPresenter {
    void getAllSkuCollectByTaskCode();
    void getAllSkuCollectByOrderCode(String orderCode);
    void addSkuCollect(TakeStockSkuCollect takeStockSkuCollect);
    void deleteSkuCollect(TakeStockSkuCollect takeStockSkuCollect);
    void updateSkuCollect(TakeStockSkuCollect takeStockSkuCollect);
    void uploadAllSkuCollectByTaskCode();

}
