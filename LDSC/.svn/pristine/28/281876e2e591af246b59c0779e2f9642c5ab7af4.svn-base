package com.lide.app.presenter.inbound;

import com.google.gson.internal.LinkedTreeMap;
import com.lide.app.MApplication;
import com.lide.app.bean.JsonToBean.BaseContainerBean;
import com.lide.app.listener.OnLoadFinishListener;
import com.lide.app.model.CreateModel;
import com.lide.app.model.QueryModelImpl;
import com.lide.app.model.UploadModel;
import com.lide.app.persistence.util.FormatUtils;
import com.lide.app.persistence.util.SPUtils;
import com.lide.app.ui.VInterface.IDataFragmentView;
import com.lubin.bean.InBoundOperate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lubin on 2016/12/2.
 */

public class InBoundPresenter {

    private final CreateModel createModel;
    private final QueryModelImpl queryModel;
    IDataFragmentView view;
    private UploadModel uploadModel;

    private String orderId;
    List<InBoundOperate> list;
    private List<String> epcs;
    private List container;

    public InBoundPresenter(IDataFragmentView view) {
        this.view = view;
        uploadModel = new UploadModel();
        queryModel = new QueryModelImpl();
        createModel = new CreateModel();
    }

    public void SearchEpcList(final List<String> data) {
        try {
            epcs = data;
            container = new ArrayList<>();
            view.startProgressDialog("搜索中...");
            installEpcData(0);
        } catch (Exception e) {
            view.showDialog(e.getMessage());
        }
    }

    private void installEpcData(int num) throws Exception {
        List<String> container = new ArrayList<>();
        for (int i = num; i < epcs.size(); i++) {
            container.add(epcs.get(i));
            if (container.size() == 1000) {
                startSearch(container, i + 1, false);
                break;
            } else if (i + 1 == epcs.size()) {
                startSearch(container, i + 1, true);
                break;
            }
        }
    }

    /**
     * 检查epc集合是否存在后端数据库
     * @param Epcs 当前上传的一批epc集合
     * @param sum   当前上传数
     * @param flag 是否是最后一次
     * @throws Exception
     */
    private void startSearch(final List<String> Epcs, final int sum, final boolean flag) throws Exception {
        try {
            queryModel.queryEpcList(Epcs, new OnLoadFinishListener() {
                @Override
                public void OnLoadFinish(Map<String, String> map) {
                    if (map.get("statusCode").equals("200")) {
                        BaseContainerBean result = null;
                        try {
                            result = FormatUtils.resultToBean(map.get("result"), BaseContainerBean.class);
                            container.addAll(result.data);
                            if (flag) {
                                view.stopProgressDialog(null);
                                view.ShowData(container);
                            } else {
                                installEpcData(sum);
                            }
                        } catch (Exception e) {
                            view.showDialog(e.getMessage());
                            view.stopProgressDialog(null);
                        }
                    } else {
                        view.showDialog(map.get("result"));
                        view.stopProgressDialog(null);
                    }
                }
            });
        } catch (Exception e) {
            view.showDialog(e.toString());
        }
    }

    public void UploadInboundOperate(String orderId, final List<InBoundOperate> list) {
        try {
            this.orderId = orderId;
            this.list = list;
            installData(0);
        } catch (Exception e) {
            view.showDialog(e.getMessage());
        }
    }

    private void installData(int num) throws Exception {
        List<InBoundOperate> container = new ArrayList<>();
        for (int i = num; i < list.size(); i++) {
            container.add(list.get(i));
            if (container.size() == 1000) {
                startUpload(container, i + 1, false);
                break;
            } else if (i + 1 == list.size()) {
                startUpload(container, i + 1, true);
                break;
            }
        }
    }
    //开始上传
    private void startUpload(final List<InBoundOperate> data, final int sum, final boolean flag) throws Exception {
        view.startProgressDialog("上传中...");
        uploadModel.uploadInBoundOperates(orderId
                , ((String) SPUtils.get(MApplication.getInstance(), "deviceId", ""))
                , data
                , new OnLoadFinishListener() {
                    @Override
                    public void OnLoadFinish(Map<String, String> map) {
                        if (map.get("statusCode").equals("200")) {
                            try {
                                if (flag) {
                                    view.showDialog("上传成功~");
                                    view.stopProgressDialog(null);
                                } else {
                                    installData(sum);
                                }
                            } catch (Exception e) {
                                view.showDialog(e.getMessage());
                                view.stopProgressDialog(null);
                            }
                        } else if (map.get("result").equals("参数[detail]不能为空或者长度不能等于0")) {
                            view.showDialog("没有可上传数据~");
                            view.stopProgressDialog(null);
                        } else {
                            view.showDialog(map.get("result"));
                            view.stopProgressDialog(null);
                        }
                    }
                });
    }
    //生成多条ID
    public void createMultiBarcode(final InBoundOperate inBoundOperate) {
        try {
            createModel.createMultiBarcodeId(inBoundOperate.getBarcode(), new OnLoadFinishListener() {
                @Override
                public void OnLoadFinish(Map<String, String> map) {
                    if (map.get("statusCode").equals("200")) {
                        try {
                            List<LinkedTreeMap> result = FormatUtils.arrayFromData(map.get("result"));
                            inBoundOperate.setMultiBarcodeId(result.get(0).get("id").toString());
                            inBoundOperate.update();
                        } catch (Exception e) {
                            view.showDialog(e.toString());
                        }
                    } else {
                        view.showDialog(map.get("result"));
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
