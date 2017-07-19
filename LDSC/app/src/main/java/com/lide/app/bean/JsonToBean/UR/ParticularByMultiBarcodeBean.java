package com.lide.app.bean.JsonToBean.UR;

import com.google.gson.Gson;

/**
 * Created by HKR on 2017/3/13.
 */

public class ParticularByMultiBarcodeBean {

    public SkuParticularBiz[] data;

    public static ParticularByMultiBarcodeBean objectFromData(String str) {
        return new Gson().fromJson(str, ParticularByMultiBarcodeBean.class);
    }

    public class SkuParticularBiz {
        /**
         * barcode : 00203S
         * brandCode : 001
         * brandName : TEST
         * created : 2016-09-13 10:17:31.620
         * currentPrice : 0
         * disable : false
         * enableUniqueCode : true
         * id : 2ff1980a-c2ff-4d4f-a74a-73ea3b178771
         * imagePath : null
         * imageThumbnailPath : null
         * imageThumbnailUrl : null
         * imageUrl : null
         * modified : 2016-09-13 10:17:31.620
         * multiBarcodeId : 0be6c6fb-52a1-4494-84c0-49db9a99fa64
         * multiBarcodeType : MULTI_BARCODE
         * name : 定货号测试款二&绿fg色&小码
         * prodcutCalculCode : null
         * prodcutOnlineSalesUrl01 : null
         * productCode : 002
         * productColorCode : 03
         * productColorId : C724BD10-87E4-4155-A12F-DC4CEE282408
         * productColorName : 绿fg色
         * productColorRgb : #000000
         * productCurrentPrice : 97.77
         * productDescription : null
         * productDisable : false
         * productId : BDCC8F5B-E298-49BE-97ED-22D49C77A1BE
         * productImageThumbnailUrl : null
         * productImageUrl : null
         * productIsHot : false
         * productIsPromote : false
         * productIsShowing : false
         * productLikeCount : 0
         * productName : 定货号测试款二
         * productOnlineSalesUrl02 : null
         * productRetailPrice : 100
         * productSizeCode : S
         * productSizeGroupCode : 01
         * productSizeGroupId : 4cc60385-8ace-4390-99fb-e70280c46991
         * productSizeGroupName : 01
         * productSizeId : 1bd925de-7ce3-48e3-97dc-ea97319e2183
         * productSizeName : 小码
         * productSource : null
         * productUseColor : true
         * productUseSize : true
         * retailPrice : 0
         * sourceCode : null
         * version : 1
         */

        private String barcode;
        private String brandCode;
        private String brandName;
        private String created;
        private int currentPrice;
        private boolean disable;
        private boolean enableUniqueCode;
        private String id;
        private Object imagePath;
        private Object imageThumbnailPath;
        private Object imageThumbnailUrl;
        private Object imageUrl;
        private String modified;
        private String multiBarcodeId;
        private String multiBarcodeType;
        private String name;
        private Object prodcutCalculCode;
        private Object prodcutOnlineSalesUrl01;
        private String productCode;
        private String productColorCode;
        private String productColorId;
        private String productColorName;
        private String productColorRgb;
        private double productCurrentPrice;
        private Object productDescription;
        private boolean productDisable;
        private String productId;
        private Object productImageThumbnailUrl;
        private Object productImageUrl;
        private boolean productIsHot;
        private boolean productIsPromote;
        private boolean productIsShowing;
        private int productLikeCount;
        private String productName;
        private Object productOnlineSalesUrl02;
        private int productRetailPrice;
        private String productSizeCode;
        private String productSizeGroupCode;
        private String productSizeGroupId;
        private String productSizeGroupName;
        private String productSizeId;
        private String productSizeName;
        private Object productSource;
        private boolean productUseColor;
        private boolean productUseSize;
        private int retailPrice;
        private Object sourceCode;
        private int version;

        public String getBarcode() {
            return barcode;
        }

        public void setBarcode(String barcode) {
            this.barcode = barcode;
        }

        public String getBrandCode() {
            return brandCode;
        }

        public void setBrandCode(String brandCode) {
            this.brandCode = brandCode;
        }

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public int getCurrentPrice() {
            return currentPrice;
        }

        public void setCurrentPrice(int currentPrice) {
            this.currentPrice = currentPrice;
        }

        public boolean isDisable() {
            return disable;
        }

        public void setDisable(boolean disable) {
            this.disable = disable;
        }

        public boolean isEnableUniqueCode() {
            return enableUniqueCode;
        }

        public void setEnableUniqueCode(boolean enableUniqueCode) {
            this.enableUniqueCode = enableUniqueCode;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getImagePath() {
            return imagePath;
        }

        public void setImagePath(Object imagePath) {
            this.imagePath = imagePath;
        }

        public Object getImageThumbnailPath() {
            return imageThumbnailPath;
        }

        public void setImageThumbnailPath(Object imageThumbnailPath) {
            this.imageThumbnailPath = imageThumbnailPath;
        }

        public Object getImageThumbnailUrl() {
            return imageThumbnailUrl;
        }

        public void setImageThumbnailUrl(Object imageThumbnailUrl) {
            this.imageThumbnailUrl = imageThumbnailUrl;
        }

        public Object getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(Object imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getModified() {
            return modified;
        }

        public void setModified(String modified) {
            this.modified = modified;
        }

        public String getMultiBarcodeId() {
            return multiBarcodeId;
        }

        public void setMultiBarcodeId(String multiBarcodeId) {
            this.multiBarcodeId = multiBarcodeId;
        }

        public String getMultiBarcodeType() {
            return multiBarcodeType;
        }

        public void setMultiBarcodeType(String multiBarcodeType) {
            this.multiBarcodeType = multiBarcodeType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getProdcutCalculCode() {
            return prodcutCalculCode;
        }

        public void setProdcutCalculCode(Object prodcutCalculCode) {
            this.prodcutCalculCode = prodcutCalculCode;
        }

        public Object getProdcutOnlineSalesUrl01() {
            return prodcutOnlineSalesUrl01;
        }

        public void setProdcutOnlineSalesUrl01(Object prodcutOnlineSalesUrl01) {
            this.prodcutOnlineSalesUrl01 = prodcutOnlineSalesUrl01;
        }

        public String getProductCode() {
            return productCode;
        }

        public void setProductCode(String productCode) {
            this.productCode = productCode;
        }

        public String getProductColorCode() {
            return productColorCode;
        }

        public void setProductColorCode(String productColorCode) {
            this.productColorCode = productColorCode;
        }

        public String getProductColorId() {
            return productColorId;
        }

        public void setProductColorId(String productColorId) {
            this.productColorId = productColorId;
        }

        public String getProductColorName() {
            return productColorName;
        }

        public void setProductColorName(String productColorName) {
            this.productColorName = productColorName;
        }

        public String getProductColorRgb() {
            return productColorRgb;
        }

        public void setProductColorRgb(String productColorRgb) {
            this.productColorRgb = productColorRgb;
        }

        public double getProductCurrentPrice() {
            return productCurrentPrice;
        }

        public void setProductCurrentPrice(double productCurrentPrice) {
            this.productCurrentPrice = productCurrentPrice;
        }

        public Object getProductDescription() {
            return productDescription;
        }

        public void setProductDescription(Object productDescription) {
            this.productDescription = productDescription;
        }

        public boolean isProductDisable() {
            return productDisable;
        }

        public void setProductDisable(boolean productDisable) {
            this.productDisable = productDisable;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public Object getProductImageThumbnailUrl() {
            return productImageThumbnailUrl;
        }

        public void setProductImageThumbnailUrl(Object productImageThumbnailUrl) {
            this.productImageThumbnailUrl = productImageThumbnailUrl;
        }

        public Object getProductImageUrl() {
            return productImageUrl;
        }

        public void setProductImageUrl(Object productImageUrl) {
            this.productImageUrl = productImageUrl;
        }

        public boolean isProductIsHot() {
            return productIsHot;
        }

        public void setProductIsHot(boolean productIsHot) {
            this.productIsHot = productIsHot;
        }

        public boolean isProductIsPromote() {
            return productIsPromote;
        }

        public void setProductIsPromote(boolean productIsPromote) {
            this.productIsPromote = productIsPromote;
        }

        public boolean isProductIsShowing() {
            return productIsShowing;
        }

        public void setProductIsShowing(boolean productIsShowing) {
            this.productIsShowing = productIsShowing;
        }

        public int getProductLikeCount() {
            return productLikeCount;
        }

        public void setProductLikeCount(int productLikeCount) {
            this.productLikeCount = productLikeCount;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public Object getProductOnlineSalesUrl02() {
            return productOnlineSalesUrl02;
        }

        public void setProductOnlineSalesUrl02(Object productOnlineSalesUrl02) {
            this.productOnlineSalesUrl02 = productOnlineSalesUrl02;
        }

        public int getProductRetailPrice() {
            return productRetailPrice;
        }

        public void setProductRetailPrice(int productRetailPrice) {
            this.productRetailPrice = productRetailPrice;
        }

        public String getProductSizeCode() {
            return productSizeCode;
        }

        public void setProductSizeCode(String productSizeCode) {
            this.productSizeCode = productSizeCode;
        }

        public String getProductSizeGroupCode() {
            return productSizeGroupCode;
        }

        public void setProductSizeGroupCode(String productSizeGroupCode) {
            this.productSizeGroupCode = productSizeGroupCode;
        }

        public String getProductSizeGroupId() {
            return productSizeGroupId;
        }

        public void setProductSizeGroupId(String productSizeGroupId) {
            this.productSizeGroupId = productSizeGroupId;
        }

        public String getProductSizeGroupName() {
            return productSizeGroupName;
        }

        public void setProductSizeGroupName(String productSizeGroupName) {
            this.productSizeGroupName = productSizeGroupName;
        }

        public String getProductSizeId() {
            return productSizeId;
        }

        public void setProductSizeId(String productSizeId) {
            this.productSizeId = productSizeId;
        }

        public String getProductSizeName() {
            return productSizeName;
        }

        public void setProductSizeName(String productSizeName) {
            this.productSizeName = productSizeName;
        }

        public Object getProductSource() {
            return productSource;
        }

        public void setProductSource(Object productSource) {
            this.productSource = productSource;
        }

        public boolean isProductUseColor() {
            return productUseColor;
        }

        public void setProductUseColor(boolean productUseColor) {
            this.productUseColor = productUseColor;
        }

        public boolean isProductUseSize() {
            return productUseSize;
        }

        public void setProductUseSize(boolean productUseSize) {
            this.productUseSize = productUseSize;
        }

        public int getRetailPrice() {
            return retailPrice;
        }

        public void setRetailPrice(int retailPrice) {
            this.retailPrice = retailPrice;
        }

        public Object getSourceCode() {
            return sourceCode;
        }

        public void setSourceCode(Object sourceCode) {
            this.sourceCode = sourceCode;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }
    }
}
