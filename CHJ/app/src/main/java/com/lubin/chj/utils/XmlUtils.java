package com.lubin.chj.utils;


import android.util.Log;

import org.xmlpull.v1.XmlPullParser;

import java.lang.reflect.Field;
import java.util.ArrayList;


/**
 * @author DaiJiCheng
 * @time 2016/9/13  9:29
 * @desc ${解析Xml工具类}
 */
public class XmlUtils {
    /**
     * 解析一个bean和一个list的xml文件结构的方法
     * @param parser    解析者
     * @param listRoot  内层ListBean需要实例化对象的一个标识
     * @param listClazz ListBean.class
     * @param beanRoot  外层Bean需要实例化对象的一个标识
     * @param beanClazz Bean.class
     * @return 一个bean和一个list的结果
     * @throws Exception
     */
    public static <T, T1> ResultBeanAndList<T> getBeanByParseXml(XmlPullParser parser, String listRoot, Class<T> listClazz, String beanRoot, Class<T1> beanClazz) throws Exception {
        //最后结果
        ResultBeanAndList<T> result = null;
        ArrayList<T> list = null;
        //内层ListBean
        T t = null;
        //外层Bean
        T1 bean = null;
        //一个计数器
        int count = 0;
        try {
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    //如果是xml文件开始标签，则初始化一些数据
                    case XmlPullParser.START_DOCUMENT:
                        //最后的结果
                        result = new ResultBeanAndList<T>();
                        list = new ArrayList<T>();
                        result.setList(list);
                        break;
                    //开始标签
                    case XmlPullParser.START_TAG:
                        //获得标签的名字
                        String tagName = parser.getName();
                        Log.e("tagName", tagName);
                        //如果内层的ListBean已经实例化出来的话
                        if (t != null) {
                            Log.e("t======>>>", t.toString());
                            try {
                                //判断当前标签在没在ListBean的属性中
                                Field field = listClazz.getField(tagName);
                                Log.e("field", field + ".........");
                                //如果ListBean中有当前标签
                                if (field != null) {
                                    //计数器+1
                                    count++;
                                    Log.e("field", field.toString() + count);
                                    field.set(t, parser.nextText());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            //如果外层的Bean已经实例化出来的话
                        } else if (bean != null) {
                            try {
                                Field field = beanClazz.getField(tagName);
                                //如果Bean中有当前标签
                                if (field != null) {
                                    //计数器+1
                                    count++;
                                    field.set(bean, parser.nextText());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        if (tagName.equals(listRoot)) {
                            //将ListBean实例化出来
                            t = listClazz.newInstance();
                        }
                        if (tagName.equals(beanRoot)) {
                            //将Bean实例化出来
                            bean = beanClazz.newInstance();
                        }
                        break;
                    //结束标签
                    case XmlPullParser.END_TAG:
                        //如果当前标签为</item>
                        if (listRoot.equalsIgnoreCase(parser.getName())) {
                            //如果ListBean不为空
                            if (t != null) {
                                list.add(t);
                                t = null;
                            }
                            //如果当前标签为</root>
                        } else if (beanRoot.equalsIgnoreCase(parser.getName())) {
                            //将Bean保存到result中
                            result.setBean(bean);
                        }
                        break;
                }
                //移动到下一个标签
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (count == 0) {
            //将result置空就可以了
            result = null;
        }
        //将result返回
        return result;
    }
}
