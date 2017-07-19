package com.lide.app.ui.takeStock;

import android.content.Context;

import com.lide.app.MApplication;
import com.lide.app.persistence.util.DBOperator;
import com.lubin.bean.TakeStockEpcCollect;
import com.lubin.bean.TakeStockOrder;
import com.lubin.bean.TakeStockSkuCollect;
import com.lubin.bean.TakeStockTask;
import com.lubin.dao.DaoSession;
import com.lubin.dao.TakeStockEpcCollectDao;
import com.lubin.dao.TakeStockOrderDao;
import com.lubin.dao.TakeStockSkuCollectDao;
import com.lubin.dao.TakeStockTaskDao;


/**
 * Created by lubin on 2017/3/1.
 */

public class TakeStockTransaction {
    private static DBOperator<TakeStockOrderDao, TakeStockOrder> orderDBOperator;
    private static DBOperator<TakeStockTaskDao, TakeStockTask> taskDBOperator;
    private static DBOperator<TakeStockSkuCollectDao, TakeStockSkuCollect> skuDBOperator;
    private static DBOperator<TakeStockEpcCollectDao, TakeStockEpcCollect> epcDBOperator;
    private static DaoSession daoSession;

    public static void init(Context context) {
        if (daoSession == null) {
            daoSession = MApplication.getDaoSession(context);
        }
    }

    public static DBOperator<TakeStockOrderDao, TakeStockOrder> getOrderDBOperator() {
        if (orderDBOperator == null) {
            orderDBOperator = DBOperator.getOperator(daoSession.getTakeStockOrderDao(), TakeStockOrder.class);
        }
        return orderDBOperator;
    }

    public static DBOperator<TakeStockTaskDao, TakeStockTask> getTaskDBOperator() {
        if (taskDBOperator == null) {
            taskDBOperator = DBOperator.getOperator(daoSession.getTakeStockTaskDao(), TakeStockTask.class);
        }
        return taskDBOperator;
    }

    public static DBOperator<TakeStockSkuCollectDao, TakeStockSkuCollect> getSkuDBOperator() {
        if (skuDBOperator == null) {
            skuDBOperator = DBOperator.getOperator(daoSession.getTakeStockSkuCollectDao(), TakeStockSkuCollect.class);
        }
        return skuDBOperator;
    }

    public static DBOperator<TakeStockEpcCollectDao, TakeStockEpcCollect> getEpcDBOperator() {
        if (epcDBOperator == null) {
            epcDBOperator =  DBOperator.getOperator(daoSession.getTakeStockEpcCollectDao(), TakeStockEpcCollect.class);
        }
        return epcDBOperator;
    }
}
