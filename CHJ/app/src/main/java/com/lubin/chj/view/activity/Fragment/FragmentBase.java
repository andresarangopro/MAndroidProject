package com.lubin.chj.view.activity.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;


public abstract class FragmentBase extends Fragment {


    public LayoutInflater mInflater;

    private Handler handler;

    public Context mContext;

    ProgressDialog mypDialog;
    public AlertDialog.Builder builder;


    public void runOnWorkThread(Runnable action) {
        new Thread(action).start();
    }

    public void runOnUiThread(Runnable action) {
        handler.post(action);
    }

    public void runOnUiThread(Runnable action, long time) {
        handler.postAtTime(action, time);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mContext = getActivity();
        handler = new Handler(mContext.getMainLooper());
        mInflater = LayoutInflater.from(getActivity());
        builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("提示");
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

    public void onScanStop() {

    }

    public void setBarcode(String barcode) {
    }

    public void ShowToast(int text) {
        if (mToast == null) {
            mToast = Toast.makeText(getActivity(), text, Toast.LENGTH_LONG);
        } else {
            mToast.setText(text);
        }
        mToast.show();
    }


    public void stopProgressDialog(String result) {
        mypDialog.cancel();
        Toast.makeText(mContext, result,
                Toast.LENGTH_SHORT).show();

    }

    public void startProgressDialog(String text) {
        mypDialog = new ProgressDialog(mContext);
        mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mypDialog.setMessage(text);
        mypDialog.setCanceledOnTouchOutside(false);
        mypDialog.show();
    }

    public void ShowLog(String msg) {

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

    public void finishByBackBtn() {
    }

    public void finishByBackIcon() {
    }
}
