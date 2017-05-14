package com.riq.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 继承于SQLiteOpenHelper，重写两个方法，构造方法(少参)
 */

public class SQLiteDbHelper extends SQLiteOpenHelper {
    public static final String CREATE_BOOK = "create table book("
            + "id integer primary key autoincrement,"
            + "author text"
            + "price real,"
            + "pages integer,"
            + "name text)";
    private static final String CREATE_CATEGORY = "create table Category ("
            + "id integer primary key autoincrement,"
            + "category_name text,"
            + "category_code integer)";

    public SQLiteDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //创建数据库
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
    }

    //升级数据库（废除旧表，创建新表）
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists Category");
        onCreate(db);
    }
}
