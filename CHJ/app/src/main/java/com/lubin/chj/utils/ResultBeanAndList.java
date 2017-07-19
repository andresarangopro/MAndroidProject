package com.lubin.chj.utils;

import java.util.ArrayList;

/**
 * @author DaiJiCheng
 * @time 2016/9/13  9:32
 * @desc ${TODD}
 */
public class ResultBeanAndList<T> {
    public Object bean;

    public ArrayList<T> list;


    public Object getBean() {
        return bean;
    }
    public void setBean(Object bean) {
        this.bean = bean;
    }
        public ArrayList<T> getList() {
        return list;
    }
    public void setList(ArrayList<T> list) {
        this.list = list;
    }
    @Override
    public String toString() {
        return "ResultBeanAndList [bean=" + bean + ", list=" + list + "]";
    }


}