package com.lide.app.presenter.takeStock;


import android.extend.util.DateUtils;

import com.lide.app.bean.JsonToBean.TakeStockOrderList;
import com.lide.app.bean.JsonToBean.UR.TaskList;
import com.lide.app.listener.OnLoadFinishListener;
import com.lide.app.model.DownLoadTackStockOrderListModel;
import com.lide.app.persistence.util.DBOperator;
import com.lide.app.persistence.util.Utils;
import com.lide.app.ui.VInterface.IDataFragmentView;
import com.lide.app.ui.takeStock.TakeStockTransaction;
import com.lubin.bean.TakeStockOrder;
import com.lubin.bean.TakeStockTask;
import com.lubin.dao.TakeStockOrderDao;
import com.lubin.dao.TakeStockTaskDao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by HKR on 2017/3/1.
 */

public class DownLoadTackStockOrderListPresenterImpl {

    private DBOperator<TakeStockOrderDao, TakeStockOrder> orderDBOperator;
    private DBOperator<TakeStockTaskDao, TakeStockTask> taskDBOperator;
    IDataFragmentView view;
    DownLoadTackStockOrderListModel mDownLoadTackStockOrderListModel;

    public DownLoadTackStockOrderListPresenterImpl(IDataFragmentView view) {
        this.view = view;
        mDownLoadTackStockOrderListModel = new DownLoadTackStockOrderListModel();
        orderDBOperator = TakeStockTransaction.getOrderDBOperator();
        taskDBOperator = TakeStockTransaction.getTaskDBOperator();
    }

    /**
     * 请求盘点单
     *
     * @param warehouseId    仓库ID
     * @param search         模糊查询盘点单的字段
     * @param isPageable     是否分页
     * @param currentPage    当前页
     * @param recordsPerPage 每页显示数目
     */
    public void downLoadTackStockOrderList(String warehouseId, String search, boolean isPageable, final int currentPage, int recordsPerPage) {
        try {
            mDownLoadTackStockOrderListModel.downloadOrderDetail(warehouseId, search, isPageable, currentPage, recordsPerPage, new OnLoadFinishListener() {
                @Override
                public void OnLoadFinish(Map<String, String> map) {
                    if (map.get("statusCode").equals("200")) {
                        String result = map.get("result");
                        if (result != null && !result.isEmpty()) {
                            view.ShowData(getOrders(result, currentPage));
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<TakeStockOrderList.DataBean> getOrders(String result, int currentPage) {
        List<TakeStockOrderList.DataBean> takeStockOrderLists = new ArrayList<>();
        TakeStockOrderList data = TakeStockOrderList.objectFromData(result);
        if (data == null || data.getData() == null) {
            return takeStockOrderLists;
        }
        if (data.getCurrentPage() < currentPage) {
            return takeStockOrderLists;
        }
        return data.getData();
    }

    /**
     * 下载盘点单下的任务
     *
     * @param orderId        盘点单ID
     * @param isPageable     是否启用分页，默认为true，启用分页
     * @param currentPage    当前页码，默认为第1页
     * @param recordsPerPage 每页记录数，默认每页显示20行记录
     */
    public void downLoadTaskList(String orderId, boolean isPageable, final int currentPage, int recordsPerPage) {
        try {
            mDownLoadTackStockOrderListModel.downLoadTaskList(orderId, isPageable, currentPage, recordsPerPage, new OnLoadFinishListener() {
                @Override
                public void OnLoadFinish(Map<String, String> map) {
                    if (map.get("statusCode").equals("200")) {
                        String result = map.get("result");
                        if (result != null && !result.isEmpty()) {
                            view.ShowData(getTasks(result, currentPage));
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<TaskList.DataBean> getTasks(String result, int currentPage) {
        List<TaskList.DataBean> tasks = new ArrayList<>();
        TaskList data = TaskList.objectFromData(result);
        if (data == null || data.getData() == null) {
            return tasks;
        }
        if (data.getCurrentPage() < currentPage) {
            return tasks;
        }
        return data.getData();
    }

    public void loadTaskDetail(TaskList.DataBean dataBean) {
        if (dataBean != null) {
            if (taskDBOperator.getItemByParameter(TakeStockTaskDao.Properties.CodeId, dataBean.getId()).size() == 0) {
                view.startProgressDialog("下载中...");
                saveTask(dataBean);
                view.stopProgressDialog(null);
                view.showDialog("下载完成");
            } else {
                view.ShowToast("该任务已下载过！");
            }
        }
    }

    private void saveTask(TaskList.DataBean dataBean) {
        TakeStockOrder mOrder = null;
        if (orderDBOperator.getItemByParameter(TakeStockOrderDao.Properties.Code, dataBean.getTakeStockOrderCode()).size() == 0) {
            mOrder = new TakeStockOrder();
            mOrder.setCode(dataBean.getTakeStockOrderCode());
            mOrder.setTakeStockId(dataBean.getTakeStockOrderId());
            mOrder.setWarehouseName(dataBean.getWarehouseName());
            orderDBOperator.insertData(mOrder);
        } else {
            mOrder = orderDBOperator.getItemByParameter(TakeStockOrderDao.Properties.Code, dataBean.getTakeStockOrderCode()).get(0);
        }
        TakeStockTask mCheckTask = new TakeStockTask();
        mCheckTask.setName(dataBean.getCode());
        mCheckTask.setWarehouseCode(dataBean.getWarehouseCode());
        mCheckTask.setCodeId(dataBean.getId());
        mCheckTask.setComplete(false);
        mCheckTask.setDate(DateUtils.strToDateLong(dataBean.getModified()));
        mCheckTask.setMode(0);
        mCheckTask.setUserId(Utils.getCurrentUser().getId());
        mCheckTask.setOrderId(mOrder.getId());
        mCheckTask.setRealPoint(0);
        taskDBOperator.insertData(mCheckTask);
    }

    public void loadAllTaskDetail(List<TaskList.DataBean> beanList) {
        if (beanList != null && beanList.size() > 0) {
            view.startProgressDialog("下载中...");
            for (Iterator<TaskList.DataBean> iterator = beanList.iterator(); iterator.hasNext(); ) {
                TaskList.DataBean bean = iterator.next();
                if (taskDBOperator.getItemByParameter(TakeStockTaskDao.Properties.CodeId, bean.getId()).size() == 0) {
                    saveTask(bean);
                }
            }
            view.stopProgressDialog(null);
            view.showDialog("下载完成");
        } else {
            view.ShowToast("没有任务可以下载");
        }
    }
}
