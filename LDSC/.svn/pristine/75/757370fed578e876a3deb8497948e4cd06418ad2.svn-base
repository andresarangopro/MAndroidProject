package com.lide.app.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.lide.app.MApplication;
import com.lide.app.R;
import com.lide.app.persistence.util.ResourcesUtils;

public abstract class FragmentBase extends Fragment {

    public MApplication mApplication;

    public LayoutInflater mInflater;

    private Handler handler;

    public Context mContext;

    ProgressDialog mypDialog;

    public android.support.v7.app.AlertDialog.Builder builder;

    DialogInterface.OnClickListener listener;
    public Drawable redBackground;
    public Drawable greyBackground;
    public Drawable commonBackground;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mContext = getActivity();
        mypDialog = new ProgressDialog(mContext);
        mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mypDialog.setCanceledOnTouchOutside(false);
        handler = new Handler(mContext.getMainLooper());
        mApplication = MApplication.getInstance();
        mInflater = LayoutInflater.from(getActivity());
        builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
        builder.setTitle("提示");
        redBackground = ResourcesUtils.getDrawable(getActivity(), R.drawable.btn_click_red_havebackground);
        greyBackground = ResourcesUtils.getDrawable(getActivity(), R.drawable.btn_click_grey_havebackground);
        commonBackground = ResourcesUtils.getDrawable(getActivity(), R.drawable.button_common);
    }

    @Override
    public void onPause() {
        super.onPause();
        mypDialog.dismiss();
    }

    public void setListener(DialogInterface.OnClickListener listener) {
        this.listener = listener;
    }

    public void runOnWorkThread(Runnable action) {
        new Thread(action).start();
    }

    public void runOnUiThread(Runnable action) {
        if (handler != null)
            handler.post(action);
    }

    public void runOnUiThread(Runnable action, long time) {
        handler.postAtTime(action, time);
    }

    public FragmentBase() {

    }

    Toast mToast;

    public void ShowToast(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mToast == null) {
                    mToast = Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT);
                } else {
                    mToast.setText(text);
                }
                mToast.show();
            }
        });
    }

    public void ShowToast(int text) {
        if (mToast == null) {
            mToast = Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
        }
        mToast.show();
    }

    public void showDialog(String result) {
        if (result != null) {
            builder.setMessage(result);
            builder.setPositiveButton("确定", listener);
            builder.show();
        }
    }


    public void stopProgressDialog(final String result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (!mypDialog.isShowing()) return;
                    mypDialog.dismiss();
                    if (result == null) return;
                    Toast.makeText(mContext, result,
                            Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void startProgressDialog(final String text) {
        if (mypDialog.isShowing()) return;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    mypDialog.setMessage(text);
                    mypDialog.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public View findViewById(int paramInt) {
        return getView().findViewById(paramInt);
    }

    public void startAnimActivity(Intent intent) {
        this.startActivity(intent);
    }

    public void startAnimActivity(Class<?> cla) {
        mContext.startActivity(new Intent(getActivity(), cla));
    }

    public void startAnimActivity(Intent intent, int flag) {
        this.startActivity(intent);
    }


    public void setBarcode(String barcode) {

    }

    public void setData(Object o) {

    }

    public void showDataByType(String Type) {

    }

    public void startScan() {
    }

    public void readOrClose() {
    }

}
