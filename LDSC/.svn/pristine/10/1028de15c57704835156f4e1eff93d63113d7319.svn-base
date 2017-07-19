package com.lide.app.model;

import com.google.gson.Gson;
import com.lide.app.bean.BeanToJson.ForTaskOrderlist;
import com.lide.app.bean.BeanToJson.ProductToJsonForDiff;
import com.lide.app.bean.BeanToJson.QueryEpcDiff;
import com.lide.app.bean.BeanToJson.SKUToJsonForDiff;
import com.lide.app.bean.JsonToBean.EPCDiff;
import com.lide.app.bean.JsonToBean.ProductDiff;
import com.lide.app.bean.JsonToBean.SkuDiff;
import com.lide.app.listener.OnLoadFinishListener;
import com.lide.app.persistence.util.NetWorkUtil;
import com.lide.app.persistence.util.Utils;

import java.util.HashMap;
import java.util.Map;

public class QueryDiffModel {
    //api地址
    private String apiUrl;

    public QueryDiffModel() {
        apiUrl = Utils.apiUrl;
    }

    /**
     * 根据款号查询差异
     *
     * @param productCode        款号
     * @param currentProductPage 当前页数
     */
    public void LoadProductDiff(String productCode, int currentProductPage, final OnLoadFinishListener listener) {

        String apiMethod = "api/takeStockOrder/findDiff/product";
        String postUrl = apiUrl + apiMethod;

        ProductToJsonForDiff productToJsonForDiff = new ProductToJsonForDiff();
        productToJsonForDiff.diffType = "ALL";
        productToJsonForDiff.id = Utils.getCurrentTask().getTakeStockOrder().getTakeStockId();
        if (productCode != null)
            productToJsonForDiff.productCode = productCode;
        else {
            productToJsonForDiff.orderByField = "productCode";
            productToJsonForDiff.currentPage = currentProductPage;
            productToJsonForDiff.isPageable = true;
            productToJsonForDiff.recordsPerPage = 10;
        }
        String requestJsonData = new Gson().toJson(productToJsonForDiff);

        NetWorkUtil.startNetWord(requestJsonData, postUrl, listener);
    }

    /**
     * 根据SKU查询差异
     *
     * @param key         条码
     * @param productCode 款号
     */
    public void LoadSkuDiff(String key, String productCode, final OnLoadFinishListener listener) {

        String apiMethod = "api/takeStockOrder/findDiff/sku";
        String postUrl = apiUrl + apiMethod;

        SKUToJsonForDiff skuToJsonForDiff = new SKUToJsonForDiff();
        skuToJsonForDiff.diffType = "ALL";
        skuToJsonForDiff.id = Utils.getCurrentTask().getTakeStockOrder().getTakeStockId();
        if (key != null) {
            skuToJsonForDiff.barcode = key;
        } else {
            skuToJsonForDiff.productCode = productCode;
        }
        String requestJsonData = new Gson().toJson(skuToJsonForDiff);

        NetWorkUtil.startNetWord(requestJsonData, postUrl, listener);
    }

    /**
     * 根据条码查询已盘点的EPC
     *
     * @param epc         EPC
     * @param currentPage 当前页码
     * @param barcode     条码
     */
    public void LoadEPC(String epc, int currentPage, String barcode, final OnLoadFinishListener listener) {

        String apiMethod = "api/takeStockOrder/findEpc/barcode";
        String postUrl = apiUrl + apiMethod;

        QueryEpcDiff queryEpcDiff = new QueryEpcDiff();
        queryEpcDiff.id = Utils.getCurrentTask().getTakeStockOrder().getTakeStockId();
        if (epc != null) {
            queryEpcDiff.epc = epc;
        } else {
            queryEpcDiff.isPageable = true;
            queryEpcDiff.currentPage = currentPage;
            queryEpcDiff.orderByField = "epc";
            queryEpcDiff.recordsPerPage = 10;
            queryEpcDiff.barcode = barcode;
        }
        String requestJsonData = new Gson().toJson(queryEpcDiff);

        NetWorkUtil.startNetWord(requestJsonData, postUrl, listener);
    }

    public ProductDiff toProductDiff(String result) {
        return ProductDiff.objectFromData(result);
    }

    public SkuDiff toSkuDiff(String result) {
        return SkuDiff.objectFromData(result);
    }

    public EPCDiff toEPC(String result) {
        return EPCDiff.objectFromData(result);
    }

}
