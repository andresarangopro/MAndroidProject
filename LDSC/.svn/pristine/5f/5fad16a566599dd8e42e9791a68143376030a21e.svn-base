package com.lide.app.persistence.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.lubin.bean.TakeStockTask;
import com.lubin.bean.User;

public class Utils {

    //api地址
    //TODO 作假
    public static String apiUrl = "http://192.168.8.253:8080/hdwapi-main/";
//    public static String apiUrl = "http://192.168.8.218:8887/";
    //public static String apiUrl = "";
    //public static String apiUrl = "http://139.224.195.208:7978/hdwapi/";
    //public static String apiUrl = "192.168.8.192:8048/JShdw/";
    //public static String apiUrl = "http://139.224.195.208:7978/hdwapi/";
    //public static String apiUrl = "http://192.168.8.201:8080/";

    /**
     * 获得屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    public static String getApiKey() {
        if (currentUser == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("APP_KEYS");
        sb.append(" ");
        sb.append(currentUser.getApiKey());
        return sb.toString();
    }


    //当前任务
    private static TakeStockTask currentTask;

    public static void setCurrentTask(TakeStockTask task) {
        currentTask = task;
    }

    public static TakeStockTask getCurrentTask() {
        return currentTask;
    }

    //当前用户
    private static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    private static String wareHouseName;

    public static String getWareHouseName() {
        return wareHouseName;
    }

    public static void setWareHouseName(String wareHouseName) {
        Utils.wareHouseName = wareHouseName;
    }

    public static void setCurrentUser(User currentUser) {
        Utils.currentUser = currentUser;
    }

}
