package com.lubin.chj.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

public class XmlUtil {

    public static Map<String, String> getUrl(InputStream xml) throws Exception {
        XmlPullParser pullParser = Xml.newPullParser();
        pullParser.setInput(xml, "UTF-8"); //为Pull解释器设置要解析的XML数据
        int event = pullParser.getEventType();
        Map<String, String> map = new HashMap<>();
        while (event != XmlPullParser.END_DOCUMENT) {

            switch (event) {
                case XmlPullParser.START_TAG:
                    String name = pullParser.getName();
                    String value = pullParser.nextText();
                    if ("url".equals(name)) {
                        map.put("url", value);
                    } else {
                        SoapUtil.outTimes.put(name, Integer.parseInt(value));
                    }
                    break;
            }
            event = pullParser.next();
        }

        return map;
    }


}