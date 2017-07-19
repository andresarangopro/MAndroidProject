package com.lide.app.service.YBX;

import java.util.ArrayList;
import java.util.List;

/**
 * RFID读写器返回数据结果封装
 * Created by tanqimin on 2016/11/14.
 */
public class RfidContext {
    /**
     * RFID集合
     */
    private List<Rfid> rfids = new ArrayList<>();

    public List<Rfid> getRfids() {
        return rfids;
    }

    public void setRfids(List<Rfid> rfids) {
        this.rfids = rfids;
    }
}
