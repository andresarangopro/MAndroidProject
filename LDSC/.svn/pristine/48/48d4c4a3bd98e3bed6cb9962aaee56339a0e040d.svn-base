package android.extend.util.business;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.extend.app.BaseFragment;

/**
 * 业务基类
 * Created by tanqimin on 2016/11/19.
 */

public class BusinessBase {
    private SQLiteDatabase sqLiteDatabase;

    public BusinessBase(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    public <T> T runInTx(TxAction<T> txAction) {
        T t;
        try {
            sqLiteDatabase.beginTransaction();
            t = txAction.run();
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception ex) {
            throw ex;
        } finally {
            sqLiteDatabase.endTransaction();
        }

        return t;
    }

    public void execute(Runnable runnable) {
        try {
            sqLiteDatabase.beginTransaction();
            runnable.run();
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception ex) {
            throw ex;
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }


}
