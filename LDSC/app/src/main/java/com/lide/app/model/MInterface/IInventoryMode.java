package com.lide.app.model.MInterface;

import com.lide.app.listener.OnLoadFinishListener;

/**
 * Created by huangjianxionh on 2017/1/9.
 */

public interface IInventoryMode{
    void queryInventoryList(String barcode, final OnLoadFinishListener listener);
}
