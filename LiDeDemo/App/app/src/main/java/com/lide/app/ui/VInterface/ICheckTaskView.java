package com.lide.app.ui.VInterface;

import com.lubin.bean.CheckTask;

import java.util.List;

/**
 * Created by lubin on 2016/7/18.
 */
public interface ICheckTaskView {
    //显示处理后结果,result=Id
    void showResult(int result);
    //tasks=数据库里的数据源;
    void showCheckTaskList(List<CheckTask> tasks);
}
