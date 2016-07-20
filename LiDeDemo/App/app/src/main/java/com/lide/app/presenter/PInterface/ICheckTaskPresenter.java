package com.lide.app.presenter.PInterface;

/**
 * Created by lubin on 2016/7/18.
 */
public interface ICheckTaskPresenter {
    //用任务名添加
    void addCheckTask(String taskName);
    //跟据任务名删除
    void deleteCheckTask(String taskName);
    //lapsedName=被修改的名字 newName="新的名字"
    void updateCheckTaskName(String lapsedName,String newName);
    //keyword 比如:"全部","用户输入的"
    void queryCheckTask(String keyword);
    //得到全部的任务
    void getAllCheckTask();
}