package android.extend.util.service;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.extend.R;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * 更新APP的帮助类
 */

public class VersionUtils {

    public static int loading_process;
    public static Context context;
    private ProgressBar pb;
    private TextView tv;

    public VersionUtils(Context context) {
        this.context = context;
    }

    public void version(final String url) {
        LinearLayout ll = (LinearLayout) LayoutInflater.from(
                context).inflate(R.layout.layout_loadapk,
                null);
        pb = (ProgressBar) ll.findViewById(R.id.down_pb);
        tv = (TextView) ll.findViewById(R.id.tv);

        new AlertDialog.Builder(context)
                .setIcon(R.drawable.ic_launcher)
                .setTitle("进度提示")
                .setView(ll)
                .setCancelable(false).show();

        new Thread() {
            public void run() {
                StringBuffer sb = new StringBuffer();
                if (!url.isEmpty() && url.contains("/")) {
                    String[] split = url.split("/");
                    apk = split[split.length - 1];
                    loadFile(url, apk);
                }
            }
        }.start();
    }

    String apk;

    // 从IP地址下载文件到本地
    public void loadFile(String url, String apk) {
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);
        HttpResponse response;
        try {
            response = client.execute(get);

            HttpEntity entity = response.getEntity();
            float length = entity.getContentLength();

            InputStream is = entity.getContent();
            FileOutputStream fileOutputStream = null;
            if (is != null) {
                File file = new File(Environment.getExternalStorageDirectory(),
                        apk);
                fileOutputStream = new FileOutputStream(file);
                byte[] buf = new byte[1024];
                int ch = -1;
                float count = 0;
                while ((ch = is.read(buf)) != -1) {
                    fileOutputStream.write(buf, 0, ch);
                    count += ch;
                    sendMsg(1, (int) (count * 100 / length));
                }
            }
            sendMsg(2, 0);
            fileOutputStream.flush();
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (Exception e) {
            sendMsg(-1, 0);
        }
    }

    private void sendMsg(int flag, int c) {
        Message msg = new Message();
        msg.what = flag;
        msg.arg1 = c;
        handler.sendMessage(msg);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {// 定义一个Handler，用于处理下载线程与UI间通讯
            if (!Thread.currentThread().isInterrupted()) {
                switch (msg.what) {
                    case 1:
                        pb.setProgress(msg.arg1);
                        loading_process = msg.arg1;
                        tv.setText("已为您加载了：" + loading_process + "%");
                        break;
                    case 2:
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.fromFile(new File(Environment
                                        .getExternalStorageDirectory(), apk)),
                                "application/vnd.android.package-archive");
                        context.startActivity(intent);
                        break;
                    case -1:
                        String error = msg.getData().getString("error");
                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
            super.handleMessage(msg);
        }
    };


    /**
     * 返回当前程序版本名
     */
    public static Version getAppVersionName(Context context) {
        Version version = new Version();
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            version.versionName = pi.versionName;
            version.versioncode = pi.versionCode;
            if (version.versionName.isEmpty()) {
                return null;
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return version;
    }

    /**
     * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0
     *
     * @param version1
     * @param version2
     * @return
     */
    public static int compareVersion(String version1, String version2)
            throws Exception {
        if (version1 == null || version2 == null) {
            throw new Exception("compareVersion error:illegal params.");
        }
        String[] versionArray1 = version1.split("\\.");// 注意此处为正则匹配，不能用"."；
        String[] versionArray2 = version2.split("\\.");
        int idx = 0;
        int minLength = Math.min(versionArray1.length, versionArray2.length);// 取最小长度值
        int diff = 0;
        while (idx < minLength
                && (diff = versionArray1[idx].length()
                - versionArray2[idx].length()) == 0// 先比较长度
                && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {// 再比较字符
            ++idx;
        }
        // 如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }

    public static class Version {
        public String versionName;
        public int versioncode;
    }

}
