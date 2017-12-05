package com.jjyh.it.sqldemo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.jjyh.it.sqldemo.bean.ChatsRecord;

/**
 * Created by zhangjunjie on 2017/8/22.
 */

public abstract class DataBaseHelper {
    private final static String tag = "modb";

    static final String G_DATABASE_NAME = "moassistant.db";
    static final int G_DATABASE_VERSION = 1;
    /**
     * 用来创建和获取数据库的SQLiteOpenHelper
     */
    protected DBHelper mDbHelper;
    /**
     * 数据库对象
     */
    protected SQLiteDatabase mDb;

    public DataBaseHelper(Context context) {
        this.mDbHelper = new DBHelper(context,this.G_DATABASE_NAME,null,this.G_DATABASE_VERSION);
    }

    protected void open() {
        /*new AsyncTask<Void,Void,Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                mDb = mDbHelper.getWritableDatabase();
                Log.i(tag,"dbhelper open");
                return null;
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);*/
        mDb = mDbHelper.getWritableDatabase();
    }

    protected SQLiteDatabase getDB(){
        return this.mDb;
    }


    public void close(){
        if (this.mDb != null)
            this.mDb.close();
        if (this.mDbHelper != null)
            this.mDbHelper.close();
        Log.i(tag,"dbhelper close");
    }

    private class DBHelper extends SQLiteOpenHelper{

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String sql = getCreateChatRecordSql();
            db.execSQL(sql);
            Log.i(tag,"dbhelper onCreate");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //TODO:do database upgrade
            Log.i(tag,"onUpgrade db");
        }

        private String getCreateChatRecordSql() {
            String sql = "CREATE TABLE IF NOT EXISTS "+ ChatsRecord.TABLE_CHATSRECORD+" ( "
                    + ChatsRecord._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + ChatsRecord.CONTENTS + " TEXT, "
                    + ChatsRecord.TYPE +" INTEGER"
                    + " )";
            return sql;
        }

    }
}
