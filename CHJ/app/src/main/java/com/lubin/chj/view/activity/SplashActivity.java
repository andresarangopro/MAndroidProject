package com.lubin.chj.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.lubin.chj.MApplication;
import com.lubin.chj.R;
import com.lubin.chj.utils.SoapUtil;
import com.lubin.chj.utils.XmlUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Message msg = Message.obtain();
        msg.what = 1;
        String url = null;
        mHandler.sendMessageDelayed(msg, 2000);
        File file = new File(Environment.getExternalStorageDirectory(), "shareInfo_data/shareInfo_url.xml");
        try {
            Map<String, String> map = XmlUtil.getUrl(new FileInputStream(file));
            url = map.get("url");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (url != null) SoapUtil.URL = url;
    }


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    SplashActivity.this.finish();
                    break;
                case 2:
                    break;
            }
        }
    };
}
