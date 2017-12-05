package com.jjyh.it.sqldemo.order;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chenmingying on 2017/12/5.
 */

public abstract class DataBaseHelper {
    /**
     * 用来创建和获取数据库的SQLiteOpenHelper
     */
    protected DBHelper mDbHelper;
    /**
     * 数据库对象
     */
    protected SQLiteDatabase mDb;

    /**
     * 数据库信息
     */
    private int mDbVersion;//版本
    private String mDbName;//数据库名
    /**
     * 创建表语句
     */
    private String[] mDbCreateSql;
    /**
     * 更新表语句
     */
    private String[] mDbUpdateSql;

    protected abstract int getMDbVersion(Context context);

    protected abstract String getDbName(Context context);

    protected abstract String[] getDbCreateSql(Context context);

    protected abstract String[] getDbUpdateSql(Context context);

    public DataBaseHelper(Context context) {
        this.mDbVersion = this.getMDbVersion(context);
        this.mDbName = this.getDbName(context);
        this.mDbCreateSql = this.getDbCreateSql(context);
        this.mDbUpdateSql = this.getDbUpdateSql(context);
        this.mDbHelper = new DBHelper(context,this.mDbName,null,this.mDbVersion);
    }
    protected SQLiteDatabase open() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (mDb == null)
                    mDb = mDbHelper.getWritableDatabase();
            }
        }).start();
        return mDb;
    }

    protected SQLiteDatabase getDB() {
        if (mDb == null) {
            mDb = mDbHelper.getWritableDatabase();
        }
        return this.mDb;
    }

    public void close(){
        this.mDb.close();
        this.mDbHelper.close();
    }

    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String[] arr = DataBaseHelper.this.mDbCreateSql;
            //执行创建表语句
            for (int i=0;i<arr.length;i++){
                String sql = arr[i];
                db.execSQL(sql);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String[] arr = DataBaseHelper.this.mDbUpdateSql;
            //执行更新语句
            for (int i=0;i<arr.length;i++){
                String sql = arr[i];
                db.execSQL(sql);
            }
        }
    }
}
