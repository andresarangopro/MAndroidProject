package com.lide.app.model;

import com.lide.app.MApplication;
import com.lide.app.model.MInterface.ICheckTaskModel;
import com.lide.app.persistence.util.StringUtils;
import com.lide.app.persistence.util.Utils;
import com.lide.app.service.DbService;
import com.lubin.bean.TakeStockTask;
import com.lubin.bean.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CheckTaskModelImpl implements ICheckTaskModel {

    public DbService db;

    public CheckTaskModelImpl() {
        db = DbService.getInstance(MApplication.getInstance());
    }

    /**
     * 根据任务名，在本地新增一条盘点任务数据
     * @param taskName 任务名
     * @param mode 采集模式：条码模式、标签模式
     * @return
     */
    @Override
    public Boolean addCheckTask(String taskName, int mode) {
        User currentUser = Utils.getCurrentUser();
        if (queryCheckTask(taskName).size() == 0) {
            StringBuilder upperCase = new StringBuilder();
            for (int n = 0; n < taskName.length(); n++) {
                String str = taskName.substring(n, n + 1);
                upperCase.append(StringUtils.getFirstChar(str));
            }
            String s = upperCase.toString();

            TakeStockTask task = new TakeStockTask();

            task.setName(taskName);
            task.setComplete(false);
            task.setMode(mode);
            task.setDate(new Date(System.currentTimeMillis()));
            task.setUpperCase(s);
            task.setLowerCase(s.toLowerCase());
            if (currentUser != null) {
                task.setUserId(currentUser.getId());
                task.setUser(currentUser);
                task.setWarehouseCode(currentUser.getWarehouseCode());
            }
            db.saveCheckTask(task);
            Utils.setCurrentTask(task);
            return true;
        }
        return false;
    }

    /**
     * 根据任务名删除本地一条盘点任务数据
     * @param taskName 盘点任务名
     * @return
     */
    @Override
    public Boolean deleteCheckTask(String taskName) {
        List<TakeStockTask> tasks = queryCheckTask(taskName);
        TakeStockTask takeStockTask = tasks.get(0);
        if (tasks.size() == 1) {
            db.deleteCheckTask(takeStockTask.getId());
            db.deleteCollectLists(db.queryCollect(takeStockTask.getId()));
            db.deleteSkuCollectList(db.querySkuCollectByTaskCode(takeStockTask.getId()));
            return true;
        }
        return false;
    }

    /**
     * 根据盘点任务id,删除本地盘点任务的明细
     * @param codeId
     * @return
     */
    public Boolean deleteCheckTaskCodeId(String codeId) {
        List<TakeStockTask> tasks = queryCheckTaskCodeId(codeId);
        TakeStockTask takeStockTask = tasks.get(0);
        if (tasks.size() == 1) {
            db.deleteCheckTask(takeStockTask.getId());
            db.deleteCollectLists(db.queryCollect(takeStockTask.getId()));
            db.deleteSkuCollectList(db.querySkuCollectByTaskCode(takeStockTask.getId()));
            return true;
        }
        return false;
    }

    /**
     * 根据盘点任务名，修改本地久的盘点任务名
     * @param InvalidName 旧的盘点任务名
     * @param newName 新的盘点任务名
     * @return
     */
    @Override
    public Boolean updateCheckTaskName(String InvalidName, String newName) {
        List<TakeStockTask> tasks = queryCheckTask(InvalidName);
        if (tasks.size() == 1) {
            tasks.get(0).setName(newName);
            db.saveCheckTask(tasks.get(0));
            return true;
        }
        return false;
    }

    /***
     * 从本地中加载出属于用户登录时填写的仓库
     * @return 盘点任务list
     */
    @Override
    public List<TakeStockTask> getAllCheckTask() {
        User currentUser = Utils.getCurrentUser();
        if (currentUser == null) {
            return db.getAllCheckTask();
        } else {
            String warehouseCode = currentUser.getWarehouseCode();
            if (warehouseCode.equals("")) {
                return db.getAllCheckTask();
            } else {
                return db.queryCheckTaskByWarehouseCode(currentUser.getWarehouseCode());
            }
        }
    }

    /**
     * 根据盘点任务名，查询相似的盘点任务对象
     * @param taskName
     * @return
     */
    @Override
    public List<TakeStockTask> queryCheckTask(String taskName) {
        return db.queryCheckTask(taskName);
    }

    /**
     * 根据盘点任务id,查询所属的盘点任务对象
     * @param codeId
     * @return
     */
    public List<TakeStockTask> queryCheckTaskCodeId(String codeId) {
        return db.queryCheckTaskCodeId(codeId);
    }

    /**
     * 根据盘点任务名，查询与之相联系的盘点任务明细
     * @param keyword
     * @return 盘点任务明细
     */
    @Override
    public List<TakeStockTask> detailSearch(String keyword) {
        List<TakeStockTask> tasks = db.getAllCheckTask();
        ArrayList<TakeStockTask> result = new ArrayList<>();
        for (TakeStockTask task : tasks) {
            String name = task.getName();
            for (int i = 0; i < name.length(); i++) {
                if (keyword.equals(task.getName().subSequence(0, i)) ||
                        keyword.equals(task.getLowerCase().substring(0, i)) ||
                        keyword.equals(task.getUpperCase().substring(0, i))) {
                    result.add(task);
                }
            }
        }
        return result;
    }

}
