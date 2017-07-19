package com.lide.app.service.YBX;

/**
 * Created by tanqimin on 2016/11/14.
 */

public interface ReadedListener {
    /**
     * 读取的事件
     *
     * @param context
     */
    void onReaded(RfidContext context);
}
