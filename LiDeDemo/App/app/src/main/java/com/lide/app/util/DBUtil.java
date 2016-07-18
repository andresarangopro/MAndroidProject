package com.lide.app.util;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.activeandroid.query.Update;
import com.lide.app.bean.DBBean.CheckTask;
import com.lide.app.config.Configs;

import java.util.List;

/**
 * Created by lubin on 2016/7/18.
 */
public class DBUtil {
    //任务列表数据表
    public static Boolean haveCheckTask(String name){
        int size = new Select().from(CheckTask.class).where("name=?",name).execute().size();
        if(size==0){
            return false;
        }
        return true;
    }

    public static List<CheckTask> queryCheckTask(String keyword){
        if(keyword.equals(Configs.All)){
            List<CheckTask> tasks = new Select().from(CheckTask.class).orderBy("RANDOM()").execute();
            return tasks;
        }
        List<CheckTask> tasks = new Select().from(CheckTask.class).where("name=?",keyword).execute();
        return tasks;
    }

    public static void deleteCheckTask(String name){
        new Delete().from(CheckTask.class).where("name=?",name).execute();
    }

    public static void addCheckTask(String name){
        CheckTask checkTask = new CheckTask(name,null,0,2);
        checkTask.save();
    }

    public static void updateCheckTask(String InvalidName,String newName){
        new Update(CheckTask.class).set("name="+newName).where("name=?",InvalidName).execute();
    }

}
