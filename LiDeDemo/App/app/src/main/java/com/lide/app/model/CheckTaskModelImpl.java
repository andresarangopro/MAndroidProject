package com.lide.app.model;

import com.lide.app.bean.DBBean.CheckTask;
import com.lide.app.listener.OnSearchCheckTaskFinishListener;
import com.lide.app.model.MInterface.ICheckTaskModel;
import com.lide.app.util.DBUtil;

import java.util.List;

public class CheckTaskModelImpl implements ICheckTaskModel {

    @Override
    public Boolean addCheckTask(String taskName) {
        if(!DBUtil.haveCheckTask(taskName)){
            DBUtil.addCheckTask(taskName);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteCheckTask(String taskName) {
        if(DBUtil.haveCheckTask(taskName)){
            DBUtil.deleteCheckTask(taskName);
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateCheckTaskName(String InvalidName, String newName) {
        if(DBUtil.haveCheckTask(InvalidName)){
            DBUtil.updateCheckTask(InvalidName,newName);
            return true;
        }
        return false;
    }

    @Override
    public void queryCheckTask(String keyword, OnSearchCheckTaskFinishListener listener) {
        List<CheckTask> tasks = DBUtil.queryCheckTask(keyword);
        listener.OnSearchCheckTaskFinish(tasks);
    }

}
