package com.lide.app.util;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lide.app.listener.OnLoadingFinishListener;

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
 * Created by lubin on 2016/7/20.
 */
public class HttpUtil {

    public static void login(RequestQueue requestQueue, final String body, final OnLoadingFinishListener listener) {

        String apiUrl = "http://192.168.8.253:8080/hdwapi-main/";

        String apiMethod = "api/security/login";

        String postUrl = apiUrl + apiMethod;

/*
        final JSONObject body = new JSONObject();
        try {
            body.put("businessModuleCode", "HDW");
            body.put("accountType", "EMPLOYEE");
            body.put("warehouseCode","0001");
            body.put("username", "admin");
            body.put("password", "admin");
        } catch (JSONException e) {
            e.printStackTrace();
        }
*/

        StringRequest request = new StringRequest(Request.Method.POST, postUrl, new Response.Listener<String>() {

            @Override
            public void onResponse(String result) {
                listener.onLoadingFinish(result);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                listener.onLoadingFinish("登陆失败");
            }
        }) {

            @Override
            public byte[] getBody() throws AuthFailureError {
                return body.getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("accept", "*/*");
                map.put("connection", "Keep-Alive");
                map.put("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                map.put("Content-type", "application/json;charset=utf-8");
                return super.getHeaders();
            }
        };
        requestQueue.add(request);
    }

    private static void takeStockOrder() throws Exception {

        //api地址
        String apiUrl = "http://192.168.8.253:8080/hdwapi-main/";

        //api方法
        String apiMethod = "api/takeStockOrder/list";

        String reqeust = "{\"orderByMethod\":\"DESC\"}";

        String postUrl = apiUrl + apiMethod;
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
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // MIME属性设置,类型=任意流
            conn.setRequestProperty("Content-type", "application/json;charset=utf-8");
            conn.setRequestProperty("authorization", "APP_KEYS 4347aa1b-956d-4dc4-9e0f-7302eaf9e59e");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            outputStreamWriter = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            // 发送请求参数
            outputStreamWriter.write(reqeust);
            // flush输出流的缓冲
            outputStreamWriter.flush();
            outputStreamWriter.close();

            // InputStream输入流来读取URL的响应
            InputStream inputStream = conn.getInputStream();
            result = "提交成功,响应信息:" + inStream2String(inputStream);
        } catch (IOException ex) {
            if (conn != null) {

                //获取http状态码
                int statusCode = conn.getResponseCode();

                //从连接错误流获取返回错误信息
                String errorMsg = inStream2String(conn.getErrorStream());

                result = "提交失败,错误状态码:" + statusCode + ",错误信息:" + errorMsg;

            } else {
                throw ex;
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

        System.out.println(result);
    }

    private static String inStream2String(InputStream is) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int len = -1;
        while ((len = is.read(buf)) != -1) {
            baos.write(buf, 0, len);
        }
        return new String(baos.toByteArray());
    }
}
