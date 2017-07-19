package com.lide.app.service.YBX;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.lide.app.listener.OnFinishListener;

/**
 * Created by lubin on 2016/11/23.
 */


public class BarcodeReceiver extends BroadcastReceiver {

    public static OnFinishListener listener = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        byte[] barcode = intent.getByteArrayExtra("barcode");
        int barocodelen = intent.getIntExtra("length", 0);
        byte temp = intent.getByteExtra("barcodeType", (byte) 0);
        android.util.Log.i("debug", "----codetype--" + temp);
        String barcodeStr = new String(barcode, 0, barocodelen);//直接获取字符串

        if (listener != null)
            listener.OnFinish(barcodeStr);
    }

    public static void setListener(OnFinishListener listener) {
        BarcodeReceiver.listener = listener;
    }
}