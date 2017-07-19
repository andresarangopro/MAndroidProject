package android.extend.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.extend.util.LogUtil;

public abstract class BaseDBHelper
{
	public final String TAG = getClass().getSimpleName();

	private MyDBOpenHelper mOpenHelper = null;

	private String oldTible = null;

	public SQLiteOpenHelper getSQLiteOpenHelper(Context context)
	{
		if(oldTible == null){
			if (mOpenHelper == null)
			{
				mOpenHelper = new MyDBOpenHelper(context);
			}
		}else{
			if(!getTableName().equals(oldTible)){
				mOpenHelper = new MyDBOpenHelper(context);
			}
		}
		oldTible = getTableName();
		return mOpenHelper;
	}

	public SQLiteDatabase getWritableDatabase(Context context)
	{
		return getSQLiteOpenHelper(context).getWritableDatabase();
	}

	public SQLiteDatabase getReadableDatabase(Context context)
	{
		return getSQLiteOpenHelper(context).getReadableDatabase();
	}

	protected long insert(Context context, ContentValues values)
	{
		return getWritableDatabase(context).insert(getTableName(), null, values);
	}

	protected int update(Context context, ContentValues values, String whereClause, String[] whereArgs)
	{
		return getWritableDatabase(context).update(getTableName(), values, whereClause, whereArgs);
	}

	protected int delete(Context context, String whereClause, String[] whereArgs)
	{
		return getWritableDatabase(context).delete(getTableName(), whereClause, whereArgs);
	}

	protected Cursor query(Context context, String selection, String[] selectionArgs, String orderBy,boolean flag)
	{
		return getReadableDatabase(context).query(flag,getTableName(), null, selection, selectionArgs, null, null, orderBy,null);
	}

	protected abstract String getTableName();

	protected abstract int getTableVersion();

	protected abstract String getSQLCreateContent();

	protected abstract String getDataBName();

	private String getDBName(String dbName)
	{
		if (!dbName.endsWith(".db"))
		{
			return dbName + ".db";
		}
		return dbName;
	}

	private class MyDBOpenHelper extends SQLiteOpenHelper
	{
		public MyDBOpenHelper(Context context)
		{
			super(context, getDBName(getDataBName()), null, getTableVersion());
		}

		@Override
		public void onCreate(SQLiteDatabase db)
		{
			String tableName = getTableName();
			String content = getSQLCreateContent();
			LogUtil.d(TAG, "onCreate: " + tableName + ";\n" + content);
			db.execSQL("CREATE TABLE IF NOT EXISTS " + tableName + " " + content + ";");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
		{
			LogUtil.d(TAG, "onUpgrade: " + oldVersion + "; " + newVersion);
			if (oldVersion != newVersion)
			{
				db.execSQL("DROP TABLE IF EXISTS " + getTableName() + ";");
				onCreate(db);
			}
		}
	}

	public synchronized boolean hasRecord(Context context)
	{
		Cursor cursor = null;
		try
		{
			cursor = query(context, null, null, null,true);
			if (cursor.getCount() > 0)
			{
				return true;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (cursor != null)
			{
				cursor.close();
			}
		}
		return false;
	}
}
