package com.lubin.chj.utils;

import android.util.Log;

import com.lubin.chj.Listener.OnLoginFinshListener;
import com.lubin.chj.Listener.OnNetReqFinishListener;
import com.lubin.chj.bean.User;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DaiJiCheng
 * @time 2016/9/19  9:56
 * @desc ${网络请求工具类}
 */
public class SoapUtil {

    public static String nameSpace = "http://tempuri.org/";
    //public static String URL = "http://218.16.206.4/CHJStockWS/CHJStock.asmx";
    public static String URL = "http://183.234.53.251:8902/CHJStockWS/CHJStock.asmx";
  // public static String URL = "";
    public static Map<String, Integer> outTimes = new HashMap<>();

    public static void Login(final User user, final String equipmentID, final OnLoginFinshListener listener) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                String doMethod = "Login";
                String SOAP_ACTION = nameSpace + doMethod;
                Object result = null;
                try {
                    SoapObject request = new SoapObject(nameSpace, doMethod);
                    SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
                    //添加header
                    soapEnvelope.headerOut = new Element[1];
                    soapEnvelope.headerOut[0] = buildAuthHeader(user);
                    soapEnvelope.dotNet = true;
                    request.addPropertyIfValue("equipmentID", equipmentID);
                    soapEnvelope.setOutputSoapObject(request);
                    HttpTransportSE se = null;
                    if (outTimes.get(doMethod) == null) {
                        se = new HttpTransportSE(URL, 10000);
                    } else {
                        se = new HttpTransportSE(URL, outTimes.get(doMethod));
                    }
                    se.call(SOAP_ACTION, soapEnvelope);
                    result = soapEnvelope.getResponse();

                } catch (IOException e) {
                    Log.e("IOException", e.toString());
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    Log.e("XmlPullParserException", e.toString());
                    e.printStackTrace();
                }
                listener.onLoginFinish(result);
            }

        }.start();
    }

    private static Element buildAuthHeader(User user) {
        Element h = new Element().createElement(nameSpace, "UserInfo");
        Element username = new Element().createElement(nameSpace, "userID");
        username.addChild(Node.TEXT, user.userName);
        h.addChild(Node.ELEMENT, username);
        Element pass = new Element().createElement(nameSpace, "pwd");
        pass.addChild(Node.TEXT, user.passWord);
        h.addChild(Node.ELEMENT, pass);
        return h;
    }


    public static void GetWebServiceData(final Map<String, Object> mMap, final OnNetReqFinishListener listener) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                Object result = null;
                String doMethod = mMap.get("doMethod").toString();
                String soapAction = nameSpace + doMethod;
                // 2得到KSOAP2的核心对象,并将参数传递给SoapObject
                SoapObject request = new SoapObject(nameSpace, doMethod);
                if (mMap != null) {
                    for (String k : mMap.keySet()) {
                        request.addPropertyIfValue(k, mMap.get(k));
                    }
                }
                //类型要注意
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
                envelope.setOutputSoapObject(request);

                //服务器是.net做的
                envelope.dotNet = true;
                HttpTransportSE se = null;
                if (outTimes.get(doMethod) == null) {
                    se = new HttpTransportSE(URL, 10000);
                } else {
                    se = new HttpTransportSE(URL, outTimes.get(doMethod));
                }
                try {
                    se.call(soapAction, envelope);
                    Object response = envelope.getResponse();
                    if (response != null) {
                        result = response;
                    }
                } catch (Exception e) {
                    Log.e("=WebServiceUtil=", e.toString());
                    e.printStackTrace();
                }
                se.debug = true;
                mMap.put("result", result);
                listener.OnNetReqFinish(mMap);
            }

        }.start();
    }

}
