package com.lide.app.presenter.takeStock;

import com.lide.app.MApplication;
import com.lide.app.listener.OnLoadFinishListener;
import com.lide.app.model.UploadModel;
import com.lide.app.persistence.util.Utils;
import com.lide.app.presenter.ScanPresenter;
import com.lide.app.service.DbService;
import com.lide.app.ui.VInterface.IUploadCollectView;
import com.lide.app.ui.takeStock.UpLoadCollectActivity;
import com.lubin.bean.TakeStockEpcCollect;
import com.lubin.bean.TakeStockTask;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UploadCollectPresenterImpl {

    private final UploadModel uploadModel;
    private DbService db;
    IUploadCollectView view;

    int succeed = 0;
    int progress = 0;

    TakeStockTask currentTask = null;
    List<TakeStockEpcCollect> takeStockEpcCollects = null;

    public UploadCollectPresenterImpl(final IUploadCollectView view) {
        this.view = view;
        uploadModel = new UploadModel();
        db = DbService.getInstance(MApplication.getInstance());
    }

    public void UploadEpcList() {
        currentTask = Utils.getCurrentTask();
        if (UpLoadCollectActivity.isUploadAll) {
            //上传全部
            takeStockEpcCollects = db.queryCollect(currentTask.getId());
        } else {
            //上传未上传的epc
            takeStockEpcCollects = db.queryCollect(currentTask.getId(), false);
        }
        try {
            installData(0);
        } catch (Exception e) {
            view.showDialog(e.toString());
        }
    }
    //使用递归分批上传epc
    private void installData(int num) throws Exception {
        List<String> mData = new LinkedList<>();
        for (int i = num; i < takeStockEpcCollects.size(); i++) {
            mData.add(takeStockEpcCollects.get(i).getEpc());
            if (mData.size() == 1000) {
                startUpload(mData, i + 1, false);
                break;
            } else if (i + 1 == takeStockEpcCollects.size()) {
                startUpload(mData, i + 1, true);
                break;
            }
        }
    }
    //上传epc
    private void startUpload(final List<String> data, final int sum, final boolean flag) throws Exception {
        uploadModel.uploadTakeStockCollects(data
                , currentTask.getTakeStockOrder().getTakeStockId()
                , currentTask.getCodeId()
                , new OnLoadFinishListener() {
                    @Override
                    public void OnLoadFinish(Map<String, String> map) {
                        if (map.get("statusCode").equals("200")) {
                            try {

                                int result = Integer.parseInt(map.get("result"));
                                //成功数
                                succeed += result;
                                //上传进度
                                progress = sum * 100 / takeStockEpcCollects.size();

                                view.ShowProgress(progress);
                                view.ShowUploadNum(sum);
                                view.ShowNum(succeed, sum - succeed);

                                if (flag) {
                                    //上传完成，改变epc的未上传状态，并保存到本地
                                    currentTask.setComplete(true);
                                    List<TakeStockEpcCollect> changes = new ArrayList<>();
                                    for (TakeStockEpcCollect takeStockEpcCollect : takeStockEpcCollects) {
                                        takeStockEpcCollect.setIsUpload(true);
                                        changes.add(takeStockEpcCollect);
                                    }
                                    db.saveCollectLists(changes);
                                    db.saveCheckTask(currentTask);
                                    //清除读写器中的，新增的epc,已上传过的epc并不是新增的epc，避免重复上传
                                    ScanPresenter.getOut().clear();
                                } else {
                                    installData(sum);
                                }
                            } catch (Exception e) {
                                view.showDialog(e.toString());
                            }
                        } else {
                            view.showDialog(map.get("result"));
                        }
                    }
                });
    }

}
