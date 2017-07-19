package com.lubin.chj.bean;

/**
 * Created by lubin on 2016/11/1.
 */

public class Gw {
    private boolean isFinish;
    private String name;
    private boolean isLight;

    public boolean isLight() {
        return isLight;
    }

    public void setLight(boolean light) {
        isLight = light;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
