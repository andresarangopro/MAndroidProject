package com.lide.app.ui;


import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.lide.app.MApplication;

/**
 * 基类
 *
 * @author smile
 * @ClassName: BaseActivity
 */
public class BaseActivity extends FragmentActivity {

    MApplication mApplication;
    protected int mScreenWidth;
    protected int mScreenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = MApplication.getInstance();
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = getWindowManager().getDefaultDisplay().getWidth();
        int height = getWindowManager().getDefaultDisplay().getHeight();

        Log.d("px",mScreenWidth+"");
        Log.d("px",mScreenHeight+"");
        Log.d("px",width+"");
        Log.d("px",height+"");
    }

    Toast mToast;

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
            //杀死当前app所使用的进程
            Process.killProcess(Process.myPid());
        } else {
            ShowToast("再按一次退出");
            firstPress = System.currentTimeMillis();
        }
    }
}