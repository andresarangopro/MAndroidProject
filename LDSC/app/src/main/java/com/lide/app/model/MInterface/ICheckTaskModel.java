package com.lide.app.model.MInterface;

import com.lubin.bean.TakeStockTask;

import java.util.List;

/**
 * Created by lubin on 2016/7/18.
 */
public interface ICheckTaskModel {
    Boolean addCheckTask(String taskName,int mode);
    Boolean deleteCheckTask(String taskName);
    Boolean deleteCheckTaskCodeId(String codeId);
    Boolean updateCheckTaskName(String InvalidName,String newName);
    List<TakeStockTask> getAllCheckTask();
    List<TakeStockTask> queryCheckTask(String taskName);
    List<TakeStockTask> detailSearch(String keyword);

}
