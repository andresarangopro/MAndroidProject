package com.lide.app.presenter.outbound;

import com.lide.app.bean.JsonToBean.UR.OutBoundBarcodeListBean;
import com.lide.app.bean.JsonToBean.UR.OutBoundOrderListBean;
import com.lide.app.listener.OnLoadFinishListener;
import com.lide.app.model.OutBoundModel;
import com.lide.app.persistence.util.DBOperator;
import com.lide.app.ui.VInterface.IDataFragmentView;
import com.lide.app.ui.outbound.createOrder.OutboundTransaction;
import com.lubin.bean.OutBoundDetail;
import com.lubin.bean.OutBoundOperate;
import com.lubin.bean.OutBoundOrder;
import com.lubin.dao.OutBoundDetailDao;
import com.lubin.dao.OutBoundOperateDao;
import com.lubin.dao.OutBoundOrderDao;

import java.util.List;
import java.util.Map;

/**
 * Created by HKR on 2017/3/7.
 */

public class OutBoundOrderPresenter {

    private final DBOperator<OutBoundOrderDao, OutBoundOrder> orderDBOperator;
    private final DBOperator<OutBoundDetailDao, OutBoundDetail> detailDBOperator;
    private final DBOperator<OutBoundOperateDao, OutBoundOperate> operateDBOperator;
    private IDataFragmentView view;
    private OutBoundModel model;

    public OutBoundOrderPresenter(IDataFragmentView view) {
        this.view = view;
        this.model = new OutBoundModel();
        orderDBOperator = OutboundTransaction.getOrderDBOperator();
        detailDBOperator = OutboundTransaction.getDetailDBOperator();
        operateDBOperator = OutboundTransaction.getOperateDBOperator();
    }

    /**
     * 出库单单头列表
     *
     * @param isPageable     是否启用分页，默认为true，启用分页
     * @param currentPage    当前页码，默认为第1页
     * @param recordsPerPage 每页记录数，默认每页显示20行记录
     */
    public void getOutBoundOrderList(boolean isPageable, int currentPage, int recordsPerPage, String code) {
        try {
            model.getOutBoundOrderList(isPageable, currentPage, recordsPerPage, code, new OnLoadFinishListener() {
                @Override
                public void OnLoadFinish(Map<String, String> map) {
                    if (map.get("statusCode").equals("200")) {
                        try {
                            OutBoundOrderListBean result = OutBoundOrderListBean.objectFromData(map.get("result"));
                            if (result == null || result.getData() == null || result.getData().size() == 0) {
                                view.ShowToast("根据搜索到0条数据！");
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadOrderDetail(final OutBoundOrderListBean.DataBean dataBean) {
        List<OutBoundOrder> list = orderDBOperator.getItemByParameter(OutBoundOrderDao.Properties.OrderId, dataBean.getId());
        if (list.size() > 0) {
            view.ShowToast("该单已下载过！");
        } else {
            view.startProgressDialog("下载中...");
            try {
                model.getOutBoundOrderLines(dataBean.getId(), false, 0, 0, new OnLoadFinishListener() {
                    @Override
                    public void OnLoadFinish(Map<String, String> map) {
                        if (map.get("statusCode").equals("200")) {
                            OutBoundBarcodeListBean result = OutBoundBarcodeListBean.objectFromData(map.get("result"));
                            boolean isComplete = false;
                            if (result != null) {
                                try {
                                    isComplete = model.saveIBOrderDetail(dataBean,result);
                                } catch (Exception e) {
                                    view.showDialog(e.getMessage());
                                }
                            }
                            if (isComplete) {
                                view.ShowToast("下载完成");
                            } else {
                                view.ShowToast("下载失败");
                            }
                        } else {
                            view.showDialog(map.get("result"));
                        }
                        view.stopProgressDialog(null);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
