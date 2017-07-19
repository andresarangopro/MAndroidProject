package com.lide.app.ui.outbound.createOrder;

import android.content.Context;

import com.lide.app.MApplication;
import com.lide.app.persistence.util.DBOperator;
import com.lubin.bean.OutBoundDetail;
import com.lubin.bean.OutBoundOperate;
import com.lubin.bean.OutBoundOrder;
import com.lubin.dao.DaoSession;
import com.lubin.dao.OutBoundDetailDao;
import com.lubin.dao.OutBoundOperateDao;
import com.lubin.dao.OutBoundOrderDao;

/**
 * Created by lubin on 2016/12/13.
 */

public class OutboundTransaction {

    private static DBOperator<OutBoundOrderDao, OutBoundOrder> OrderDBOperator;
    private static DBOperator<OutBoundOperateDao, OutBoundOperate> OperateDBOperator;
    private static DBOperator<OutBoundDetailDao, OutBoundDetail> DetailDBOperator;
    private static DaoSession daoSession;

    public static void init(Context context) {
        if (daoSession == null) daoSession = MApplication.getDaoSession(context);
    }

    public static DBOperator<OutBoundOrderDao, OutBoundOrder> getOrderDBOperator() {
        if (OrderDBOperator == null) {
            OrderDBOperator = DBOperator.getOperator(daoSession.getOutBoundOrderDao(), OutBoundOrder.class);
        }
        return OrderDBOperator;
    }

    public static DBOperator<OutBoundOperateDao, OutBoundOperate> getOperateDBOperator() {
        if (OperateDBOperator == null) {
            OperateDBOperator = DBOperator.getOperator(daoSession.getOutBoundOperateDao(), OutBoundOperate.class);
        }
        return OperateDBOperator;
    }

    public static DBOperator<OutBoundDetailDao, OutBoundDetail> getDetailDBOperator() {
        if (DetailDBOperator == null) {
            DetailDBOperator = DBOperator.getOperator(daoSession.getOutBoundDetailDao(), OutBoundDetail.class);
        }
        return DetailDBOperator;
    }
}
