package com.lide.app.bean.BeanToJson;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by lubin on 2016/11/26.
 */

public class Binding {

    /**
     * tags : [""]
     * mutilBarcodeId :
     * beforeBindingOperationsEnum : {}
     */

    private String mutilBarcodeId;
    private String beforeBindingOperationsEnum;

    public String getBeforeBindingOperationsEnum() {
        return beforeBindingOperationsEnum;
    }

    public void setBeforeBindingOperationsEnum(String beforeBindingOperationsEnum) {
        this.beforeBindingOperationsEnum = beforeBindingOperationsEnum;
    }

    private List<String> tags;

    public static Binding objectFromData(String str) {

        return new Gson().fromJson(str, Binding.class);
    }

    public String getMutilBarcodeId() {
        return mutilBarcodeId;
    }

    public void setMutilBarcodeId(String mutilBarcodeId) {
        this.mutilBarcodeId = mutilBarcodeId;
    }


    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

}
