package com.lide.app.presenter;

import android.text.TextUtils;

import com.lide.app.R;
import com.lide.app.listener.OnSearchCheckTaskFinishListener;
import com.lide.app.model.CheckTaskModelImpl;
import com.lide.app.model.MInterface.ICheckTaskModel;
import com.lide.app.presenter.PInterface.ICheckTaskPresenter;
import com.lide.app.ui.VInterface.ICheckTaskView;
import com.lubin.bean.CheckTask;

import java.util.List;

public class CheckTaskPresenterImple implements ICheckTaskPresenter {
    ICheckTaskView view;
    ICheckTaskModel model;

    public CheckTaskPresenterImple(ICheckTaskView view) {
        this.view = view;
        this.model = new CheckTaskModelImpl();
    }

    @Override
    public void addCheckTask(String taskName) {
        if (!TextUtils.isEmpty(taskName)) {
            if (model.addCheckTask(taskName)) {
                getAllCheckTask();
                view.showResult(R.string.add_check_task_succeed);
            } else {
                view.showResult(R.string.add_check_task_fail);
            }
        } else {
            view.showResult(R.string.empty_data);
        }
    }

    @Override
    public void deleteCheckTask(String taskName) {

        if (model.deleteCheckTask(taskName)) {
            getAllCheckTask();
            view.showResult(R.string.delete_check_task_succeed);
        } else {
            view.showResult(R.string.delete_check_task_fail);
        }
    }

    @Override
    public void updateCheckTaskName(String InvalidName, String newName) {
        if (!TextUtils.isEmpty(newName)) {
            if (model.updateCheckTaskName(InvalidName, newName)) {
                getAllCheckTask();
                view.showResult(R.string.update_check_task_succeed);
            } else {
                view.showResult(R.string.update_check_task_fail);
            }
        } else {
            view.showResult(R.string.empty_data);
        }
    }

    @Override
    public void queryCheckTask(String keyword) {
        if (keyword != null) {
            view.showCheckTaskList(model.queryCheckTask(keyword));
        }
    }

    @Override
    public void getAllCheckTask() {
        model.getAllCheckTask(new OnSearchCheckTaskFinishListener() {
            @Override
            public void OnSearchCheckTaskFinish(List<CheckTask> tasks) {
                view.showCheckTaskList(tasks);
            }
        });
    }
}
