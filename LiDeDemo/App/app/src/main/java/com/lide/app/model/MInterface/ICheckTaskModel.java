package com.lide.app.model.MInterface;

import com.lide.app.listener.OnSearchCheckTaskFinishListener;

/**
 * Created by lubin on 2016/7/18.
 */
public interface ICheckTaskModel {
    Boolean addCheckTask(String taskName);
    Boolean deleteCheckTask(String taskName);
    Boolean updateCheckTaskName(String InvalidName,String newName);
    void queryCheckTask(String keyword, OnSearchCheckTaskFinishListener listener);
}
