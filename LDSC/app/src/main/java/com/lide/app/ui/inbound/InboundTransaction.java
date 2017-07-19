package com.lide.app.ui.inbound;

import android.content.Context;

import com.lide.app.MApplication;
import com.lide.app.persistence.util.DBOperator;
import com.lubin.bean.InBoundCase;
import com.lubin.bean.InBoundDetail;
import com.lubin.bean.InBoundOperate;
import com.lubin.bean.InBoundOrder;
import com.lubin.dao.DaoSession;
import com.lubin.dao.InBoundCaseDao;
import com.lubin.dao.InBoundDetailDao;
import com.lubin.dao.InBoundOperateDao;
import com.lubin.dao.InBoundOrderDao;

/**
 * Created by lubin on 2016/12/6.
 */

public class InboundTransaction {

    private static DBOperator<InBoundOrderDao, InBoundOrder> OrderDBOperator;
    private static DBOperator<InBoundCaseDao, InBoundCase> CaseDBOperator;
    private static DBOperator<InBoundDetailDao, InBoundDetail> DetailDBOperator;
    private static DBOperator<InBoundOperateDao, InBoundOperate> OperateDBOperator;
    private static DaoSession daoSession;

    public static void init(Context context) {
        if (daoSession == null) daoSession = MApplication.getDaoSession(context);
    }

    public static DBOperator<InBoundOrderDao, InBoundOrder> getOrderDBOperator() {
        if (OrderDBOperator == null) {
            OrderDBOperator = DBOperator.getOperator(daoSession.getInBoundOrderDao(), InBoundOrder.class);
        }
        return OrderDBOperator;
    }

    public static DBOperator<InBoundCaseDao, InBoundCase> getCaseDBOperator() {
        if (DetailDBOperator == null) {
            CaseDBOperator = DBOperator.getOperator(daoSession.getInBoundCaseDao(), InBoundCase.class);
        }
        return CaseDBOperator;
    }

    public static DBOperator<InBoundDetailDao, InBoundDetail> getDetailDBOperator() {
        if (DetailDBOperator == null) {
            DetailDBOperator = DBOperator.getOperator(daoSession.getInBoundDetailDao(), InBoundDetail.class);
        }
        return DetailDBOperator;
    }

    public static DBOperator<InBoundOperateDao, InBoundOperate> getOperateDBOperator() {
        if (OperateDBOperator == null) {
            OperateDBOperator = DBOperator.getOperator(daoSession.getInBoundOperateDao(), InBoundOperate.class);
        }
        return OperateDBOperator;
    }
}
