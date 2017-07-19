package com.lide.app.ui;

import android.content.Intent;
import android.os.Bundle;

import com.lide.app.R;
import com.lide.app.persistence.util.NetWorkUtil;
import com.lide.app.persistence.util.SPUtils;
import com.lide.app.persistence.util.ShortCutUtils;
import com.lide.app.persistence.util.Utils;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!ShortCutUtils.hasShortcut(this)) {
            ShortCutUtils.addShortCut(this, getString(R.string.app_name), R.drawable.ic_launcher, SplashActivity.class);
        }
        setContentView(R.layout.activity_splash);
        //初始化网络访问器
        NetWorkUtil.init();
        //清除内存中的用户信息
        Utils.setCurrentUser(null);
        //查看是否是第一次登录
        initData();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initData() {
        String url = SPUtils.get(this, "url", "").toString();
        if (!url.equals("")) {
            Utils.apiUrl = url;
        }
        if (((boolean) SPUtils.get(this, "first", true))) {
            SPUtils.put(this, "first", false);
            startAnimActivity(EnterActivity.class);
        } else {
            if (((boolean) SPUtils.get(this, "isProtect", false))) {
                Intent intent = new Intent(this, LoginActivity.class);
                intent.putExtra("isAtNet", true);
                intent.putExtra("offline", "offline");
                startAnimActivity(intent);
            } else {
                startAnimActivity(EnterActivity.class);
            }
            finish();
        }
    }

}
