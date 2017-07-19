package com.lubin.chj.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.lubin.chj.Listener.OnBroadCaseFinishListener;

/**
 * Created by lubin on 2016/10/26.
 */

public class BarcodeReceiver extends BroadcastReceiver {

    public static OnBroadCaseFinishListener listener = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        byte[] barocode = intent.getByteArrayExtra("barocode");
        int barocodelen = intent.getIntExtra("length", 0);

        String barcodeStr = new String(barocode, 0, barocodelen);

        try {
            barcodeStr = new String(barocode, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String barcode = null;
        if (barcodeStr.length() > 10) {
            barcode = barcodeStr.substring(0, 10);
        } else {
            barcode = barcodeStr;
        }
        if (listener != null)
            listener.onBroadCaseFinish(barcode);
    }

    public static void setListener(OnBroadCaseFinishListener listener) {
        BarcodeReceiver.listener = listener;
    }
}
