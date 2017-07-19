package com.lide.app.ui;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.lide.app.MApplication;
import com.lide.app.R;
import com.lide.app.listener.OnFinishListener;
import com.lide.app.persistence.util.ResourcesUtils;
import com.lide.app.service.IScanService;
import com.lide.app.service.ScanServiceControl;

/**
 * 基类
 *
 * @author smile
 * @ClassName: BaseActivity
 */
public class BaseActivity extends AppCompatActivity {

    public MApplication mApplication;
    public ProgressDialog mypDialog;
    public String TAG = "test";
    public IScanService scanService = ScanServiceControl.getScanService();
    public Drawable redBackground;
    public Drawable commonBackground;
    public Drawable grayBackground;
    public android.support.v7.app.AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = MApplication.getInstance();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mypDialog = new ProgressDialog(BaseActivity.this);
        mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mypDialog.setCanceledOnTouchOutside(false);
        acquireWakeLock(this);
        SetScreenPorait(this);
        redBackground = ResourcesUtils.getDrawable(this, R.drawable.btn_click_red_havebackground);
        grayBackground = ResourcesUtils.getDrawable(this, R.drawable.btn_click_grey_havebackground);
        commonBackground = ResourcesUtils.getDrawable(this, R.drawable.button_common);
        builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle("提示!");
        scanService.setListener(new OnFinishListener() {
            @Override
            public void OnFinish(String data) {
                setData(data);
            }
        });

        //无title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public Toast mToast;

    public void ShowToast(final String text) {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
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

    public long firstPress;

    public void stopProgressDialog(final String result) {
        if (!mypDialog.isShowing()) return;
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mypDialog.cancel();
                    if (result != null) {
                        Toast.makeText(BaseActivity.this, result,
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startProgressDialog(final String text) {
        if (mypDialog.isShowing()) return;
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mypDialog.setMessage(text);
                    mypDialog.show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showDialog(final String result) {
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    builder.setMessage(result);
                    builder.setPositiveButton("确定", null);
                    builder.show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mypDialog.dismiss();
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

    public void setData(String data) {

    }

}
