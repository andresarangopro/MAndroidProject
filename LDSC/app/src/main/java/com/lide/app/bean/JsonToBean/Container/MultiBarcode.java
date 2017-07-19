package com.lide.app.bean.JsonToBean.Container;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lubin on 2016/11/26.
 */

public class MultiBarcode {

    /**
     * created : 2016-05-19 14:22:03.160
     * id : 6cdd7ab9-b23e-4b72-b70e-7dc2b1a06a5d
     * modified : 2016-05-19 14:22:03.160
     * multibarcode : TEST20160519001
     * multibarcodeType : UID
     * skuId : 00012BFC-8CB0-4F99-82C8-B777ADBF6AA6
     * version : 1
     */

    private String created;
    private String id;
    private String modified;
    private String multibarcode;
    private String multibarcodeType;
    private String skuId;
    private int version;

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getMultibarcode() {
        return multibarcode;
    }

    public void setMultibarcode(String multibarcode) {
        this.multibarcode = multibarcode;
    }

    public String getMultibarcodeType() {
        return multibarcodeType;
    }

    public void setMultibarcodeType(String multibarcodeType) {
        this.multibarcodeType = multibarcodeType;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
