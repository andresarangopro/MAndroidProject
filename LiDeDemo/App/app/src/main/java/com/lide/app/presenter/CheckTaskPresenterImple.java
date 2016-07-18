package com.lide.app.presenter;

import com.lide.app.R;
import com.lide.app.bean.DBBean.CheckTask;
import com.lide.app.config.Configs;
import com.lide.app.listener.OnSearchCheckTaskFinishListener;
import com.lide.app.model.CheckTaskModelImpl;
import com.lide.app.model.MInterface.ICheckTaskModel;
import com.lide.app.presenter.PInterface.ICheckTaskPresenter;
import com.lide.app.ui.VInterface.ICheckTaskView;

import java.util.List;

public class CheckTaskPresenterImple implements ICheckTaskPresenter {
    ICheckTaskView view;
    ICheckTaskModel model;

    public CheckTaskPresenterImple(ICheckTaskView view){
        this.view  = view;
        this.model = new CheckTaskModelImpl();
    }
    @Override
    public void addCheckTask(String taskName) {
        if(taskName!=null){
            if(model.addCheckTask(taskName)){
                queryCheckTask(Configs.All);
                view.showResult(R.string.add_check_task_succeed);
            }else{
                view.showResult(R.string.add_check_task_fail);
            }
        }
        return;
    }

    @Override
    public void deleteCheckTask(String taskName) {
        if(taskName!=null){
            if(model.deleteCheckTask(taskName)){
                queryCheckTask(Configs.All);
                view.showResult(R.string.delete_check_task_succeed);
            }else{
                view.showResult(R.string.delete_check_task_fail);
            }
        }
    }

    @Override
    public void updateCheckTaskName(String InvalidName, String newName) {
        if(InvalidName!=null&&newName!=null){
            if(model.updateCheckTaskName(InvalidName,newName)){
                queryCheckTask(Configs.All);
                view.showResult(R.string.update_check_task_succeed);
            }else{
                view.showResult(R.string.update_check_task_fail);
            }
        }
    }

    @Override
    public void queryCheckTask(String keyword) {
        if(keyword!=null){
            model.queryCheckTask(keyword, new OnSearchCheckTaskFinishListener() {
                @Override
                public void OnSearchCheckTaskFinish(List<CheckTask> tasks) {
                    view.showCheckTaskList(tasks);
                }
            });
        }
    }
}
