package com.lide.app.listener;

import com.lubin.bean.CheckTask;

import java.util.List;

/**
 * Created by lubin on 2016/7/18.
 */
public interface OnSearchCheckTaskFinishListener {
    void  OnSearchCheckTaskFinish(List<CheckTask> tasks);
}
