package android.extend.data.sqlite.common;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tanqimin on 2016/11/10.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {

    //数据库名称
    private static final String name = "lead_db.db";
    //数据库版本
    private static final int version = 3;

    private SQLiteDatabase readableDatabase = null;

    public DatabaseOpenHelper(Context context) {
        //第三个参数CursorFactory指定在执行查询时获得一个游标实例的工厂类,设置为null,代表使用系统默认的工厂类
        super(context, name, null, version);
    }

    public SQLiteDatabase getDatabase() {
        if (readableDatabase == null) {
            readableDatabase = this.getReadableDatabase();
        }
        return readableDatabase;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS person (id varchar(36) primary key, name varchar(20), age INTEGER, created datetime)");
        //创建用户表
        db.execSQL("CREATE TABLE IF NOT EXISTS USER (\n" +
                "  [id] VARCHAR(36) PRIMARY KEY, \n" +
                "  [created] DATETIME NOT NULL, \n" +
                "  [modified] DATETIME NOT NULL, \n" +
                "  [code] VARCHAR(32) NOT NULL, \n" +
                "  [shop_code] VARCHAR(32) NOT NULL, \n" +
                "  [password] VARCHAR(32) \n" +
                "  )");
        //创建收货单表
        db.execSQL("CREATE TABLE IF NOT EXISTS IN_ORDER (\n" +
                "  [id] VARCHAR(36) PRIMARY KEY, \n" +
                "  [created] DATETIME NOT NULL, \n" +
                "  [modified] DATETIME NOT NULL, \n" +
                "  [code] VARCHAR(32) NOT NULL, \n" +
                "  [box_code] VARCHAR(25), \n" +
                "  [create_date] DATETIME NOT NULL, \n" +
                "  [type] VARCHAR(4) NOT NULL, \n" +
                "  [rev_wh_code] VARCHAR(15) NOT NULL, \n" +
                "  [rev_wh_name] VARCHAR(100) NOT NULL, \n" +
                "  [deliv_wh_code] VARCHAR(15) NOT NULL, \n" +
                "  [deliv_wh_name] VARCHAR(100) NOT NULL, \n" +
                "  [is_sync]  VARCHAR(15) NOT NULL, \n" +
                "  [contain_rfid] VARCHAR(15) NOT NULL, \n" +
                "  [state]  VARCHAR(15), \n" +
                "  [shop_code] VARCHAR(15) NOT NULL, \n" +
                "  [remark] VARCHAR(255) \n" +
                "  )");
        //CONSTRAINT [] PRIMARY KEY (code, box_code, rev_wh_code, deliv_wh_code)
        //创建收货单明细表
        db.execSQL("CREATE TABLE IF NOT EXISTS IN_ORDER_LINE (\n" +
                "  [id] VARCHAR(36) PRIMARY KEY, \n" +
                "  [created] DATETIME NOT NULL, \n" +
                "  [modified] DATETIME NOT NULL, \n" +
                "  [in_order_id] VARCHAR(36) NOT NULL REFERENCES IN_ORDER(id) ON DELETE NO ACTION ON UPDATE NO ACTION, \n" +
                "  [box_code] VARCHAR(25), \n" +
                "  [prod_code] VARCHAR(25) NOT NULL, \n" +
                "  [prod_name] VARCHAR(25) NOT NULL, \n" +
                "  [uid_enabled]  VARCHAR(15) NOT NULL, \n" +
                "  [qty] NUMERIC NOT NULL, \n" +
                "  [oper_qty] NUMERIC NOT NULL \n" +
                "  )");
        //CONSTRAINT [] PRIMARY KEY ([box_code], [in_order_id], [prod_code])
        //创建收货单唯一码
        db.execSQL("CREATE TABLE IF NOT EXISTS  IN_ORDER_UID (\n" +
                "  [id] VARCHAR(36) PRIMARY KEY, \n" +
                "  [created] DATETIME NOT NULL, \n" +
                "  [modified] DATETIME NOT NULL, \n" +
                "  [in_order_id] VARCHAR(36) NOT NULL REFERENCES IN_ORDER(id) ON DELETE NO ACTION ON UPDATE NO ACTION, \n" +
                "  [box_code] VARCHAR(25), \n" +
                "  [prod_code] VARCHAR(25) NOT NULL, \n" +
                "  [prod_uid] VARCHAR(30) NOT NULL, \n" +
                "  [qty] NUMERIC NOT NULL, \n" +
                "  [oper_qty] NUMERIC NOT NULL \n" +
                " )\n");
        //CONSTRAINT [] PRIMARY KEY (in_order_id, box_code, prod_code, prod_uid)
        //创建发货单
        db.execSQL("CREATE TABLE IF NOT EXISTS  [OUT_ORDER] (\n" +
                "  [id] VARCHAR(36) PRIMARY KEY, \n" +
                "  [created] DATETIME NOT NULL, \n" +
                "  [modified] DATETIME NOT NULL, \n" +
                "  [code] VARCHAR(32) NOT NULL, \n" +
                "  [shop_code] VARCHAR(100) NOT NULL, \n" +
                "  [create_date] DATETIME NOT NULL, \n" +
                "  [type] VARCHAR(4) NOT NULL, \n" +
                "  [rev_wh_code] VARCHAR(15) NOT NULL, \n" +
                "  [rev_wh_name] VARCHAR(100) NOT NULL, \n" +
                "  [deliv_wh_code] VARCHAR(15) NOT NULL, \n" +
                "  [deliv_wh_name] VARCHAR(100) NOT NULL, \n" +
                "  [is_sync]  VARCHAR(15) NOT NULL, \n" +
                "  [is_empty]  VARCHAR(15) NOT NULL, \n" +
                "  [remark] VARCHAR(255) \n" +
                ")");
        //CONSTRAINT [] PRIMARY KEY ([code], [rev_wh_code], [deliv_wh_code])
        //创建发货单明细
        db.execSQL("CREATE TABLE IF NOT EXISTS  [OUT_ORDER_LINE] (\n" +
                "  [id] VARCHAR(36) PRIMARY KEY, \n" +
                "  [created] DATETIME NOT NULL, \n" +
                "  [modified] DATETIME NOT NULL, \n" +
                "  [out_order_id] VARCHAR(36) NOT NULL REFERENCES [OUT_ORDER]([id]) ON DELETE NO ACTION ON UPDATE NO ACTION, \n" +
                "  [prod_code] VARCHAR(25) NOT NULL, \n" +
                "  [prod_name] VARCHAR(25) NOT NULL, \n" +
                "  [uid_enabled]  VARCHAR(15) NOT NULL, \n" +
                "  [qty] NUMERIC NOT NULL, \n" +
                "  [oper_qty] NUMERIC NOT NULL \n" +
                " )\n");
        // CONSTRAINT [] PRIMARY KEY ([out_order_id], [prod_code])
        //创建发货单唯一码
        db.execSQL("CREATE TABLE IF NOT EXISTS  OUT_ORDER_UID (\n" +
                "  [id] VARCHAR(36) PRIMARY KEY, \n" +
                "  [created] DATETIME NOT NULL, \n" +
                "  [modified] DATETIME NOT NULL, \n" +
                "  [out_order_id] VARCHAR(36) NOT NULL REFERENCES OUT_ORDER(id) ON DELETE NO ACTION ON UPDATE NO ACTION, \n" +
                "  [box_code] VARCHAR(25), \n" +
                "  [prod_code] VARCHAR(25) NOT NULL, \n" +
                "  [prod_uid] VARCHAR(30) NOT NULL, \n" +
                "  [qty] NUMERIC NOT NULL, \n" +
                "  [oper_qty] NUMERIC NOT NULL \n" +
                " )\n");
        //创建盘点单
        db.execSQL("CREATE TABLE IF NOT EXISTS  [TS_ORDER] (\n" +
                "  [id] VARCHAR(36) PRIMARY KEY, \n" +
                "  [created] DATETIME NOT NULL, \n" +
                "  [modified] DATETIME NOT NULL, \n" +
                "  [code] VARCHAR(32) NOT NULL, \n" +
                "  [create_date] DATETIME NOT NULL, \n" +
                "  [type] VARCHAR(4) NOT NULL, \n" +
                "  [shop_code] VARCHAR(15) NOT NULL, \n" +
                "  [shop_name] VARCHAR(100) NOT NULL, \n" +
                "  [is_sync]  VARCHAR(15) NOT NULL, \n" +
                "  [is_uid_sync]  VARCHAR(15) NOT NULL, \n" +
                "  [state]  VARCHAR(15), \n" +
                "  [remark] VARCHAR(255) \n" +
                "  )");
        //CONSTRAINT [] PRIMARY KEY ([code], [shop_code])
        //创建盘点单明细
        db.execSQL("CREATE TABLE IF NOT EXISTS  [TS_ORDER_LINE] (\n" +
                "  [id] VARCHAR(36) PRIMARY KEY, \n" +
                "  [created] DATETIME NOT NULL, \n" +
                "  [modified] DATETIME NOT NULL, \n" +
                "  [ts_order_id] VARCHAR(36) NOT NULL REFERENCES [TS_ORDER]([id]) ON DELETE NO ACTION ON UPDATE NO ACTION, \n" +
                "  [prod_code] VARCHAR(25) NOT NULL, \n" +
                "  [brandId] VARCHAR(10) NOT NULL, \n" +
                "  [year] VARCHAR(4) NOT NULL, \n" +
                "  [uid_enabled]  VARCHAR(15) NOT NULL, \n" +
                "  [is_add]  VARCHAR(15) NOT NULL, \n" +
                "  [qty] NUMERIC NOT NULL, \n" +
                "  [oper_qty] NUMERIC NOT NULL \n" +
                " )\n");
        // CONSTRAINT [] PRIMARY KEY ([ts_order_id], [prod_code])
        //创建盘点单唯一码
        db.execSQL("CREATE TABLE IF NOT EXISTS  [TS_ORDER_UID] (\n" +
                "  [id] VARCHAR(36) PRIMARY KEY, \n" +
                "  [created] DATETIME NOT NULL, \n" +
                "  [modified] DATETIME NOT NULL, \n" +
                "  [ts_order_id] VARCHAR(36) NOT NULL REFERENCES [TS_ORDER]([id]) ON DELETE NO ACTION ON UPDATE NO ACTION, \n" +
                "  [prod_code] VARCHAR(25) NOT NULL, \n" +
                "  [prod_uid] VARCHAR(30) NOT NULL, \n" +
                "  [is_sync]  VARCHAR(15) NOT NULL \n" +
                " )");
        // CONSTRAINT [] PRIMARY KEY ([prod_uid], [prod_code], [ts_order_id])
        //创建盘点单分机明细
        db.execSQL("CREATE TABLE  IF NOT EXISTS [TS_ORDER_AID_LINE] (\n" +
                "  [id] VARCHAR(36) PRIMARY KEY, \n" +
                "  [created] DATETIME NOT NULL, \n" +
                "  [modified] DATETIME NOT NULL, \n" +
                "  [prod_code] VARCHAR(25) NOT NULL, \n" +
                "  [prod_uid] VARCHAR(30) NOT NULL \n" +
                "  )\n");
        //CONSTRAINT [] PRIMARY KEY ([prod_code], [prod_uid])

        db.execSQL("CREATE INDEX IDX_TS_ORDER_CODE ON TS_ORDER(CODE);");
        db.execSQL("CREATE INDEX IDX_TS_ORDER_UID_TS_ORDER_ID ON TS_ORDER_UID(ts_order_id);");
        db.execSQL("CREATE INDEX IDX_TS_ORDER_UID_PROD_CODE ON TS_ORDER_UID(prod_code);");
        db.execSQL("CREATE INDEX IDX_TS_ORDER_LINE_TS_ORDER_ID ON TS_ORDER_LINE(ts_order_id);");
        db.execSQL("CREATE INDEX IDX_TS_ORDER_LINE_PROD_CODE ON TS_ORDER_LINE(prod_code);");
        db.execSQL("CREATE INDEX IDX_TS_ORDER_AID_LINE_PROD_CODE ON TS_ORDER_AID_LINE(prod_code);");
        db.execSQL("CREATE INDEX IDX_TS_ORDER_AID_LINE_PROD_UID ON TS_ORDER_AID_LINE(prod_uid);");

        final int FIRST_DATABASE_VERSION = 1;
        onUpgrade(db, FIRST_DATABASE_VERSION, version);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 使用for实现跨版本升级数据库
        for (int i = oldVersion; i < newVersion; i++) {
            switch (i) {
                case 1:
                    upgradeToVersion1001(db);
                    break;
                case 2:
                    upgradeToVersion1002(db);
                    break;
                default:
                    break;
            }
        }
    }

    private void upgradeToVersion1001(SQLiteDatabase db) {
        // TS_ORDER表新增1个字段is_all
        String sql1 = "ALTER TABLE TS_ORDER ADD COLUMN is_all VARCHAR(15) default ('T') not null";
        db.execSQL(sql1);
    }

    private void upgradeToVersion1002(SQLiteDatabase db) {
        //新加一个盘点范围：TS_SCOPE
        db.execSQL("CREATE TABLE  IF NOT EXISTS [TS_SCOPE] (\n" +
                "  [id] VARCHAR(36) PRIMARY KEY, \n" +
                "  [created] DATETIME NOT NULL, \n" +
                "  [modified] DATETIME NOT NULL, \n" +
                "  [ts_order_id] VARCHAR(25) NOT NULL, \n" +
                "  [prod_pd_code] VARCHAR(30) NOT NULL \n" +
                "  )\n");
    }
}
