package com.lide.app.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.lubin.dao.DaoMaster;
import com.lubin.dao.InBoundOperateDao;
import com.lubin.dao.OutBoundOperateDao;
import com.lubin.dao.OutBoundOrderDao;
import com.lubin.dao.TakeStockEpcCollectDao;
import com.lubin.dao.TakeStockSkuCollectDao;

import org.greenrobot.greendao.database.Database;


/**
 * Created by lubin on 2016/7/19.
 */
public class THDevOpenHelper extends DaoMaster.OpenHelper {


    public THDevOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);

    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
            case 2:
                db.execSQL("ALTER TABLE OUT_BOUND_OPERATE ADD COLUMN IS_UPLOAD INTEGER");
            case 3:
                db.execSQL("DROP TABLE OUT_BOUND_OPERATE");
                OutBoundOperateDao.createTable(db, true);
            case 4:
                //删除多余表
                db.execSQL("DROP TABLE EPC");
                db.execSQL("DROP TABLE SKU");
                db.execSQL("DROP TABLE PRODUCT");
                db.execSQL("ALTER TABLE OUT_BOUND_DETAIL ADD COLUMN SKU_NAME TEXT");
            case 5:
                //改列名
                db.execSQL("ALTER TABLE IN_BOUND_OPERATE RENAME TO _IN_BOUND_OPERATE_old_20161229");
                InBoundOperateDao.createTable(db, true);
                db.execSQL("INSERT INTO " +
                        "IN_BOUND_OPERATE(BARCODE,EPC,DEVICE_ID,MULTI_BARCODE_ID,QTY,OPERATE_QTY,IS_UPLOAD,IN_BOUND_DETAIL_ID,IN_BOUND_CASE_ID,IN_BOUND_ORDER_ID) " +
                        "SELECT BARCODE,EPC,DEVICE_ID,MULTI_BARCODE_ID,QTY,OPERATE_QTY,IS_UPLOADING,IN_BOUND_DETAIL_ID,IN_BOUND_CASE_ID,IN_BOUND_ORDER_ID FROM _IN_BOUND_OPERATE_old_20161229");
                db.execSQL("DROP TABLE _IN_BOUND_OPERATE_old_20161229");
            case 6:
                //入库单增加一列，控制是否允许混合收货
                db.execSQL("ALTER TABLE IN_BOUND_ORDER ADD COLUMN INBOUND_MODE TEXT");
            case 7:
                db.execSQL("ALTER TABLE CHECK_TASK RENAME TO TAKE_STOCK_TASK");
                db.execSQL("ALTER TABLE COLLECT RENAME TO TAKE_STOCK_EPC_COLLECT");
                db.execSQL("ALTER TABLE SKU_COLLECT RENAME TO TAKE_STOCK_SKU_COLLECT");
                db.execSQL("ALTER TABLE 'ORDER' RENAME TO TAKE_STOCK_ORDER");
            case 8:
                db.execSQL("ALTER TABLE TAKE_STOCK_TASK ADD COLUMN REAL_POINT INTEGER");
            case 9:
                updateTakeStockDetail(db);
            case 10:
                updateOutBoundOrder(db);
        }
    }

    private void updateTakeStockDetail(Database db) {
        db.execSQL("UPDATE TAKE_STOCK_EPC_COLLECT SET TAKE_STOCK_TASK = (SELECT _ID FROM TAKE_STOCK_TASK AS T WHERE TAKE_STOCK_EPC_COLLECT.TAKE_STOCK_TASK = T.NAME)");
        db.execSQL("UPDATE TAKE_STOCK_SKU_COLLECT SET TASK_CODE = (SELECT _ID FROM TAKE_STOCK_TASK AS T WHERE TAKE_STOCK_SKU_COLLECT.TASK_CODE = T.NAME)");

        db.execSQL("ALTER TABLE TAKE_STOCK_EPC_COLLECT RENAME TO _TAKE_STOCK_EPC_COLLECT_OLD_20170303");
        TakeStockEpcCollectDao.createTable(db, true);
        db.execSQL("INSERT INTO " +
                "TAKE_STOCK_EPC_COLLECT(TASK_ID,EPC,IS_UPLOAD) " +
                "SELECT TAKE_STOCK_TASK,EPC,IS_UPLOADING FROM _TAKE_STOCK_EPC_COLLECT_OLD_20170303");
        db.execSQL("DROP TABLE _TAKE_STOCK_EPC_COLLECT_OLD_20170303");

        db.execSQL("ALTER TABLE TAKE_STOCK_SKU_COLLECT RENAME TO _TAKE_STOCK_SKU_COLLECT_OLD_20170303");
        TakeStockSkuCollectDao.createTable(db, true);
        db.execSQL("INSERT INTO " +
                "TAKE_STOCK_SKU_COLLECT(TASK_ID,BARCODE,DATE,NUM,IS_UPLOAD)" +
                "SELECT TASK_CODE,BARCODE,DATE,NUM,IS_UPLOADING FROM _TAKE_STOCK_SKU_COLLECT_OLD_20170303");
        db.execSQL("DROP TABLE _TAKE_STOCK_SKU_COLLECT_OLD_20170303");
    }

    private void updateOutBoundOrder(Database db) {
        db.execSQL("ALTER TABLE OUT_BOUND_ORDER RENAME TO _OUT_BOUND_ORDER_OLD_20170313");
        OutBoundOrderDao.createTable(db, true);
        db.execSQL("INSERT INTO " +
                "OUT_BOUND_ORDER(ORDER_ID,CODE,STATUS,TO_WAREHOUSE_NAME,OPERATE_QTY,QTY,CREATE_TIME)" +
                "SELECT ORDER_ID,CODE,STATUS,TO_WAREHOUSE_NAME,OPERATE_QTY,QTY,CREATE_TIME FROM _OUT_BOUND_ORDER_OLD_20170313");
        db.execSQL("DROP TABLE _OUT_BOUND_ORDER_OLD_20170313");
        db.execSQL("UPDATE OUT_BOUND_ORDER SET IS_CREATE = 1");
    }

}
