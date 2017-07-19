package com.lide.app.presenter.PInterface;

/**
 * Created by lubin on 2016/7/18.
 */
public interface ICheckTaskPresenter {
    //用任务名添加
    void addCheckTask(String taskName,int mode);
    //跟据任务名删除
    void deleteCheckTask(String taskName);
    //跟据任务CodeID删除
    void deleteCheckTaskByCodeId(String codeId);
    //lapsedName=被修改的名字 newName="新的名字"
    void updateCheckTaskName(String lapsedName,String newName);
    //详细搜索
    void detailSearch(String keyword);
    //得到全部的任务
    void getAllCheckTask();
}