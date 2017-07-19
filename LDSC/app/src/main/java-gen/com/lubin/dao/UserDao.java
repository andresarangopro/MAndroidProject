package com.lubin.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.lubin.bean.User;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER".
*/
public class UserDao extends AbstractDao<User, Long> {

    public static final String TABLENAME = "USER";

    /**
     * Properties of entity User.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property UserName = new Property(1, String.class, "userName", false, "USER_NAME");
        public final static Property Password = new Property(2, String.class, "password", false, "PASSWORD");
        public final static Property WarehouseCode = new Property(3, String.class, "warehouseCode", false, "WAREHOUSE_CODE");
        public final static Property ApiKey = new Property(4, String.class, "apiKey", false, "API_KEY");
        public final static Property WarehouseId = new Property(5, String.class, "warehouseId", false, "WAREHOUSE_ID");
        public final static Property Date = new Property(6, java.util.Date.class, "date", false, "DATE");
    }

    private DaoSession daoSession;


    public UserDao(DaoConfig config) {
        super(config);
    }
    
    public UserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"USER_NAME\" TEXT," + // 1: userName
                "\"PASSWORD\" TEXT," + // 2: password
                "\"WAREHOUSE_CODE\" TEXT," + // 3: warehouseCode
                "\"API_KEY\" TEXT," + // 4: apiKey
                "\"WAREHOUSE_ID\" TEXT," + // 5: warehouseId
                "\"DATE\" INTEGER);"); // 6: date
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, User entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(2, userName);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(3, password);
        }
 
        String warehouseCode = entity.getWarehouseCode();
        if (warehouseCode != null) {
            stmt.bindString(4, warehouseCode);
        }
 
        String apiKey = entity.getApiKey();
        if (apiKey != null) {
            stmt.bindString(5, apiKey);
        }
 
        String warehouseId = entity.getWarehouseId();
        if (warehouseId != null) {
            stmt.bindString(6, warehouseId);
        }
 
        java.util.Date date = entity.getDate();
        if (date != null) {
            stmt.bindLong(7, date.getTime());
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, User entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(2, userName);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(3, password);
        }
 
        String warehouseCode = entity.getWarehouseCode();
        if (warehouseCode != null) {
            stmt.bindString(4, warehouseCode);
        }
 
        String apiKey = entity.getApiKey();
        if (apiKey != null) {
            stmt.bindString(5, apiKey);
        }
 
        String warehouseId = entity.getWarehouseId();
        if (warehouseId != null) {
            stmt.bindString(6, warehouseId);
        }
 
        java.util.Date date = entity.getDate();
        if (date != null) {
            stmt.bindLong(7, date.getTime());
        }
    }

    @Override
    protected final void attachEntity(User entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public User readEntity(Cursor cursor, int offset) {
        User entity = new User( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // userName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // password
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // warehouseCode
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // apiKey
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // warehouseId
            cursor.isNull(offset + 6) ? null : new java.util.Date(cursor.getLong(offset + 6)) // date
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, User entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUserName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPassword(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setWarehouseCode(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setApiKey(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setWarehouseId(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setDate(cursor.isNull(offset + 6) ? null : new java.util.Date(cursor.getLong(offset + 6)));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(User entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(User entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(User entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}