package com.lide.app.persistence.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.lide.app.listener.OnLoadFinishListener;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by lubin on 2016/10/8.
 */

public class NetWorkUtil {

    //下载任务执行完毕后发送的message的what
    public static final int LOAD_FINISH = 101;
    public static final int NEW_TASK = 102;

    //线程池中线程的数量
    //(这个数量应该与设备cpu的核数相同)
    public static int Thread_Count;
    //(双向)任务队列
    //未来可以根据需求选择是FIFO还是LIFO
    public static LinkedBlockingDeque<Runnable> taskQueue;
    //线程池
    public static ExecutorService exec;
    //从任务队列中取下载任务的handler
    public static Handler pollHandler;
    //任务下载完毕后，用来刷新ListView条目显示下载图片的Handler
    public static Handler uiHandler;
    //"养活"pollHandler
    public static Thread pollThread;

    public static OnLoadFinishListener mListener;
    //标示的作用，用来确保ImageLoader只要进行一次
    //初始化操作即可
    public static boolean isFirstTime = true;

    //---请求失败
    private static final int CONNECT_FAILER = 110;

    public static void setListener(final OnLoadFinishListener listener) {
        mListener = listener;
    }

    public static synchronized void init() {

        if (!isFirstTime) {
            return;
        }
        //获取cpu的核数为Thread_Count赋值
        Thread_Count = getNumberOfCores();
        //初始化任务队列
        taskQueue = new LinkedBlockingDeque<Runnable>();
        exec = Executors.newFixedThreadPool(Thread_Count);
        //pollHandler, uiHandler
        uiHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {

                if (msg.what == LOAD_FINISH) {
                    mListener.OnLoadFinish((Map<String, String>) msg.obj);
                } else if (msg.what == CONNECT_FAILER) {
                    mListener.OnLoadFinish((Map<String, String>) msg.obj);
                } else {
                    super.handleMessage(msg);
                }
            }
        };

        pollThread = new Thread() {
            @Override
            public void run() {
                Looper.prepare();

                pollHandler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        if (msg.what == NEW_TASK) {
                            //现在任务队列中被放入了新“下载任务”
                            //去队列中取任务
                            try {
                                Runnable task = taskQueue.takeFirst();
                                //将取出的任务放到线程池中执行
                                exec.execute(task);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            super.handleMessage(msg);
                        }
                    }

                };
                Looper.loop();
            }

        };

        pollThread.start();

        isFirstTime = false;

    }


    public static synchronized void StartNetWork(final Map<String, String> map) {
        if (isFirstTime) {
            throw new RuntimeException("NetWorkUtil未做初始化");
        }

        taskQueue.add(new Runnable() {

            @Override
            public void run() {

                try {
                    String postUrl = map.get("postUrl");
                    String apiKey = map.get("apiKey");
                    String requestJsonData = map.get("requestJsonData");

                    OutputStreamWriter outputStreamWriter = null;
                    BufferedReader inReader = null;
                    String result = "";
                    HttpURLConnection conn = null;
                    try {
                        URL realUrl = new URL(postUrl);
                        // 打开和URL之间的连接
                        conn = (HttpURLConnection) realUrl.openConnection();
                        // 设置通用的请求属性
                        conn.setRequestMethod("POST");
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
                        // 发送POST请求必须设置如下两行
                        conn.setDoOutput(true);
                        conn.setDoInput(true);
                        // 获取URLConnection对象对应的输出流
                        outputStreamWriter = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
                        // 发送请求参数
                        outputStreamWriter.write(requestJsonData);
                        // flush输出流的缓冲
                        outputStreamWriter.flush();
                        outputStreamWriter.close();

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
                    Message.obtain(uiHandler, LOAD_FINISH, map).sendToTarget();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Message.obtain(pollHandler, NEW_TASK).sendToTarget();
    }

    public static void StartNetWorkOnce(final OnLoadFinishListener listener, final String postUrl, final String requestJsonData) {
        new Thread() {
            @Override
            public void run() {
                Map<String, String> map = new HashMap<>();
                map.put("postUrl", postUrl);
                map.put("requestJsonData", requestJsonData);
                map.put("apiKey", Utils.getApiKey());

                try {
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
                        conn.setRequestMethod("POST");
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
                        // 发送POST请求必须设置如下两行
                        conn.setDoOutput(true);
                        conn.setDoInput(true);
                        // 获取URLConnection对象对应的输出流
                        outputStreamWriter = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
                        // 发送请求参数
                        outputStreamWriter.write(requestJsonData);
                        // flush输出流的缓冲
                        outputStreamWriter.flush();
                        outputStreamWriter.close();

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
                    listener.OnLoadFinish(map);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private static int getNumberOfCores() {
        File file = new File("/sys/devices/system/cpu/");
        File[] files = file.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String filename) {

                if (filename.matches("cpu[0-9]")) {
                    return true;
                } else {
                    return false;
                }
            }
        });

        if (files.length > 0) {
            return files.length;
        } else {
            return 1;
        }

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

    public static synchronized void startNetWord(String requestJsonData, String postUrl, final OnLoadFinishListener listener) {

        setListener(listener);

        Map<String, String> map = new HashMap<>();

        map.put("postUrl", postUrl);
        map.put("requestJsonData", requestJsonData);

        //TODO 假API_KEY
        map.put("apiKey", Utils.getApiKey());
//        map.put("apiKey", "APP_KEYS A6A58CCF-7273-4091-B7D4-9C28FD4D633B");
        //map.put("apiKey", "APP_KEYS " + "123");
        StartNetWork(map);
    }
}
