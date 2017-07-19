package com.lubin.chj.utils;

/**
 * @author DaiJiCheng
 * @time 2016/9/19  18:27
 * @desc ${TODD}
 */
public class TouchUtils {
    private static long lastClickTime;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 2000) {

            return true;
        }
        lastClickTime = time;
        return false;

    }
}
