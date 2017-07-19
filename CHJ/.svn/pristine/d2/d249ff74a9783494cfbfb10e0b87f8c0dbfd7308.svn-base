package com.lubin.chj.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.PowerManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.lubin.chj.R;

import java.util.TimerTask;

/**
 * 基类
 *
 * @author smile
 * @ClassName: BaseActivity
 */
public class BaseActivity extends AppCompatActivity {

    AlertDialog.Builder builder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        acquireWakeLock(this);
        SetScreenPorait(this);
        builder = new AlertDialog.Builder(BaseActivity.this);
    }

    Toast mToast;

    @Override
    protected void onPause() {
        super.onPause();
        builder = null;
    }

    public void ShowToast(final String text) {
        if (!TextUtils.isEmpty(text)) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (mToast == null) {
                        mToast = Toast.makeText(getApplicationContext(), text,
                                Toast.LENGTH_LONG);
                    } else {
                        mToast.setText(text);
                    }
                    mToast.show();
                }
            });

        }
    }

    public void ShowToast(final int resId) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (mToast == null) {
                    mToast = Toast.makeText(BaseActivity.this.getApplicationContext(), resId,
                            Toast.LENGTH_LONG);
                } else {
                    mToast.setText(resId);
                }
                mToast.show();
            }
        });
    }

    /**
     * 打Log
     * ShowLog
     *
     * @return void
     * @throws
     */
    public void ShowLog(String msg) {

    }

    public void startAnimActivity(Class<?> cla) {
        this.startActivity(new Intent(this, cla));
    }

    public void startAnimActivity(Intent intent) {
        this.startActivity(intent);
    }

    /**
     * 判断用户在EditText中输入的信息是否完整
     *
     * @param editTexts
     * @return false 意味着用户输入的是完整的
     * true  意味着至少有一个EditText用户未输入内容
     */
    public boolean isEmpty(EditText... editTexts) {

        for (EditText et : editTexts) {
            if (TextUtils.isEmpty(et.getText().toString())) {

                String string = "请输入完整!";

                ShowToast(string);
                return true;
            }
        }

        return false;
    }

    long firstPress;

    @Override
    public void onBackPressed() {
        if (firstPress + 2000 > System.currentTimeMillis()) {
            //2秒钟内连续按下"back"键
            super.onBackPressed();
            finish();
        } else {
            ShowToast("再按一次退出");
            firstPress = System.currentTimeMillis();
        }
    }

    public void ShowDialog(final String result) {
        if (builder == null) return;
        runOnUiThread(new TimerTask() {
            @Override
            public void run() {
                builder.setMessage("提示");
                builder.setMessage(result);
                builder.setPositiveButton("确定", null);
                builder.show();
            }
        });
    }

    public void changeFragment(int index) {

    }

    PowerManager.WakeLock wakeLock = null;

    @SuppressWarnings("deprecation")
    public void acquireWakeLock(Activity activity) {
        if (wakeLock == null) {
            PowerManager pm = (PowerManager) activity
                    .getSystemService(Context.POWER_SERVICE);
            wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, this
                    .getClass().getCanonicalName());
            wakeLock.acquire();
            // View.setKeepScreenOn(true);
        }
    }

    public void SetScreenPorait(Activity activity) {
        if (activity.getRequestedOrientation() != android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {

    }
}
