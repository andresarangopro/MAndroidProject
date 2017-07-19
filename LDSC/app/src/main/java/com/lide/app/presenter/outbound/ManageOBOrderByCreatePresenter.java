package com.lide.app.presenter.outbound;

import com.google.gson.internal.LinkedTreeMap;
import com.lide.app.bean.JsonToBean.BaseContainerBean;
import com.lide.app.bean.JsonToBean.UR.ParticularByMultiBarcodeBean;
import com.lide.app.listener.OnLoadFinishListener;
import com.lide.app.model.CreateModel;
import com.lide.app.model.QueryModelImpl;
import com.lide.app.model.UploadModel;
import com.lide.app.persistence.util.DBOperator;
import com.lide.app.persistence.util.FormatUtils;
import com.lide.app.ui.VInterface.IDataFragmentView;
import com.lide.app.ui.outbound.createOrder.OutboundTransaction;
import com.lubin.bean.OutBoundOperate;
import com.lubin.dao.OutBoundOperateDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lubin on 2016/12/13.
 */

public class ManageOBOrderByCreatePresenter {

    private String orderId;
    private List<OutBoundOperate> list;
    private List<String> epcs;
    private List container;
    private final DBOperator<OutBoundOperateDao, OutBoundOperate> operateDBOperator;
    private IDataFragmentView view;
    private UploadModel uploadModel;
    private final CreateModel createModel;
    private final QueryModelImpl queryModel;

    public ManageOBOrderByCreatePresenter(IDataFragmentView view) {
        this.view = view;
        uploadModel = new UploadModel();
        queryModel = new QueryModelImpl();
        createModel = new CreateModel();
        operateDBOperator = OutboundTransaction.getOperateDBOperator();
    }

    public void SearchEpcList(final List<String> data) {
        try {
            epcs = data;
            container = new ArrayList();
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

    /*
    * 商品标签列表
    * */
    private void startSearch(final List<String> Epcs, final int sum, final boolean flag) throws Exception {
        try {
            queryModel.queryEpcList(Epcs, new OnLoadFinishListener() {
                @Override
                public void OnLoadFinish(Map<String, String> map) {
                    if (map.get("statusCode").equals("200")) {
                        try {
                            BaseContainerBean result = null;
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

    /*
    * 根据商品条码获取多条码档案
    * */
    public void createMultiBarcode(final OutBoundOperate outBoundOperate) {
        try {
            createModel.createMultiBarcodeId(outBoundOperate.getBarcode(), new OnLoadFinishListener() {
                @Override
                public void OnLoadFinish(Map<String, String> map) {
                    if (map.get("statusCode").equals("200")) {
                        try {
                            List<LinkedTreeMap> result = FormatUtils.arrayFromData(map.get("result"));
                            if (result.size() == 0) return;
                            outBoundOperate.setMultiBarcodeId(result.get(0).get("id").toString());
                            outBoundOperate.update();
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

    /**
     * 商品条码列表
     */
    public void SearchSkuName(final String barcode) {
        queryModel.querySku(barcode, new OnLoadFinishListener() {
            @Override
            public void OnLoadFinish(Map<String, String> map) {
                if (map.get("statusCode").equals("200")) {
                    try {
                        BaseContainerBean result = null;
                        result = FormatUtils.resultToBean(map.get("result"), BaseContainerBean.class);
                        if (result == null || result.data.size() == 0) {
                            view.ShowToast("该条码不存在~");
                        } else {
                            view.ShowData(result.data);
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

    /**
     * 根据多条码获取商品条码详细信息档案列表
     */
    public void findParticularByMultiBarcode(String multiBarcode) {
        queryModel.findParticularByMultiBarcode(multiBarcode, new OnLoadFinishListener() {
            @Override
            public void OnLoadFinish(Map<String, String> map) {
                if (map.get("statusCode").equals("200")) {
                    try {
                        String result1 = dataResult(map.get("result"));
                        ParticularByMultiBarcodeBean result = ParticularByMultiBarcodeBean.objectFromData(result1);
                        if (result == null) {
                            view.ShowToast("该条码不存在~");
                        } else if(result.data[0].isEnableUniqueCode()){
                            view.ShowToast("该条码是启用唯一码条码");
                        } else {
                            view.ShowData(result);
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

    public static final String dataResult(String result) {
        StringBuffer sb = new StringBuffer();
        sb.append("{").append('"').append("data").append('"').append(":").append(result).append("}");
        return sb.toString();
    }

    public void UploadOutboundOperate(String orderId, final List<OutBoundOperate> list) {
        try {
            view.startProgressDialog("上传中...");
            this.orderId = orderId;
            this.list = list;
            installData(0);
        } catch (Exception e) {
            view.showDialog(e.getMessage());
        }
    }

    private void installData(int num) throws Exception {
        List<OutBoundOperate> container = new ArrayList<>();
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

    /**
     * 批量保存出库SKU和标签
     *
     * @param data 单据SKU和标签明细
     */
    private void startUpload(final List<OutBoundOperate> data, final int sum, final boolean flag) throws Exception {
        uploadModel.uploadOutBoundOperates(this.orderId, data, new OnLoadFinishListener() {
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
                } else {
                    view.showDialog(map.get("result"));
                    view.stopProgressDialog(null);
                }
            }
        });
    }
}
