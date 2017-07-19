package com.lide.app.persistence.util;

import android.os.Handler;
import android.os.Message;

import com.lide.app.MApplication;
import com.lide.app.listener.OnLoadFinishListener;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lubin on 2016/12/21.
 */

public class NetWorkForGetUtil {

    private static final int LOAD_FINISH = 101;
    private static OnLoadFinishListener mListener;
    private static Handler handler;

    static {
        handler = new Handler(MApplication.getInstance().getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == LOAD_FINISH) {
                    mListener.OnLoadFinish((Map<String, String>) msg.obj);
                }
            }
        };
    }

    public static void setListener(final OnLoadFinishListener listener) {
        mListener = listener;
    }

    public static synchronized void startNetWord(String postUrl, final OnLoadFinishListener listener) {

        Map<String, String> map = new HashMap<>();

        map.put("postUrl", postUrl);

        //TODO 假API_KEY
        map.put("apiKey", Utils.getApiKey());
        //map.put("apiKey", "APP_KEYS " + "123");
        StartNetWork(map);
        setListener(listener);
    }

    public static synchronized void StartNetWork(final Map<String, String> map) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    String postUrl = map.get("postUrl");
                    String apiKey = map.get("apiKey");

                    OutputStreamWriter outputStreamWriter = null;
                    BufferedReader inReader = null;
                    String result = "";
                    HttpURLConnection conn = null;
                    try {
                        URL realUrl = new URL(postUrl);
                        // 打开和URL之间的连接
                        conn = (HttpURLConnection) realUrl.openConnection();
                        // 设置通用的请求属性
                        conn.setRequestMethod("GET");
                        conn.setRequestProperty("accept", "*/*");
                        conn.setRequestProperty("connection", "Keep-Alive");
                        conn.setRequestProperty("Cache-Control", "no-store");
                        conn.setRequestProperty("Cache-Control", "no-cache");
                        conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                        // MIME属性设置,类型=任意流
                        conn.setRequestProperty("Content-type", "application/json;charset=utf-8");
                        if (apiKey != null) {
                            conn.setRequestProperty("authorization", apiKey);
                        }
                        conn.setDoInput(true);

                        // InputStream输入流来读取URL的响应
                        InputStream inputStream = conn.getInputStream();
                        result = ConvertStream2String(inputStream);

                    } catch (Exception ex) {
                        if (conn != null) {

                            //获取http状态码
                            int statusCode = 0;
                            try {

                                statusCode = conn.getResponseCode();

                                map.put("statusCode", String.valueOf(statusCode));

                                //从连接错误流获取返回错误信息
                                String json = ConvertStream2String(conn.getErrorStream());
                                JSONObject jsonObject = new JSONObject(json);
                                result = jsonObject.getString("message");
                                //result = ConvertStream2String(conn.getErrorStream());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }

                    // 使用finally块来关闭输出流、输入流
                    finally {
                        try {
                            if (outputStreamWriter != null) {
                                outputStreamWriter.close();
                            }
                            if (inReader != null) {
                                inReader.close();
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    map.put("statusCode", String.valueOf(conn.getResponseCode()));
                    map.put("result", result);
                    Message.obtain(handler, LOAD_FINISH, map).sendToTarget();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private static String ConvertStream2String(InputStream inputStream) {
        String string = "";
        // ByteArrayOutputStream相当于内存输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        // 将输入流转移到内存输出流中
        try {
            while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
                out.write(buffer, 0, len);
            }
            // 将内存流转换为字符串
            string = new String(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return string;
    }
}
