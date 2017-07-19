package com.lide.app.service;

import android.content.Context;

import com.lide.app.listener.OnFinishListener;

import java.util.Map;

public interface IScanService {
    void init(Context context);

    void startInventory();

    void stopInventory();

    boolean openReader();

    boolean closeReader();

    boolean openBarcodeReader(Context context);

    boolean closeBarcodeReader();

    void scanBarcode();

    void stopScanBarcode();

    void setCurrentSetting(Map<String,Object> map);

    void setReadDataMode(int readDataMode);

    void setListener(OnFinishListener listener);

    void removeListener();

    void setMode(int mode);
}
