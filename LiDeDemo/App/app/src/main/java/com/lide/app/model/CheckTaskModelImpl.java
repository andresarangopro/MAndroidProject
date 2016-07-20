package com.lide.app.model;

import com.lide.app.MApplication;
import com.lide.app.listener.OnSearchCheckTaskFinishListener;
import com.lide.app.model.MInterface.ICheckTaskModel;
import com.lide.app.service.DbService;
import com.lubin.bean.CheckTask;

import java.util.Date;
import java.util.List;

public class CheckTaskModelImpl implements ICheckTaskModel {

    public DbService db ;

    public CheckTaskModelImpl() {
        db = DbService.getInstance(MApplication.getInstance());
    }

    @Override
    public Boolean addCheckTask(String taskName) {
        if (queryCheckTask(taskName).size()==0) {
            CheckTask task = new CheckTask();
            task.setName(taskName);
            task.setCompete(false);
            task.setNumber(0);
            task.setDate(new Date(System.currentTimeMillis()));
            db.saveCheckTask(task);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteCheckTask(String taskName) {
        List<CheckTask> tasks = queryCheckTask(taskName);
        if (tasks.size()==1) {
            tasks.get(0).delete();
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateCheckTaskName(String InvalidName, String newName) {
        List<CheckTask> tasks = queryCheckTask(InvalidName);
        if (tasks.size()==1) {
            tasks.get(0).setName(newName);
            db.saveCheckTask(tasks.get(0));
            return true;
        }
        return false;
    }

    @Override
    public void getAllCheckTask(OnSearchCheckTaskFinishListener listener) {
        listener.OnSearchCheckTaskFinish(db.getAllCheckTask());
    }

    @Override
    public List<CheckTask> queryCheckTask(String keyword) {
        return db.queryCheckTask(keyword);
    }

}
