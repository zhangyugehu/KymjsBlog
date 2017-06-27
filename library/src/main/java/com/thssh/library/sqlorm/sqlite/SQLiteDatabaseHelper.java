package com.thssh.library.sqlorm.sqlite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.thssh.library.sqlorm.SqlOrmManager;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/27
 */

public class SQLiteDatabaseHelper extends SQLiteOpenHelper{

    public static class Contact{
        public static final int DB_VERSION = 1;
        public static final String DB_NAME = "test.db";
    }

    public SQLiteDatabaseHelper(Context context) {
        super(context, Contact.DB_NAME, null, Contact.DB_VERSION, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}