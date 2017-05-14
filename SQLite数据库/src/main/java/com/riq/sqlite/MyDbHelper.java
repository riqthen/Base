package com.riq.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 锐 on 2017/3/28.
 */

//TODO 建表
public class MyDbHelper extends SQLiteOpenHelper {
    //数据库名
    private static final String DB_NAME = "person.db";
    //数据库版本号
    private static final int VERSION = 1;

    //创建数据库表
    private static final String CREATE_TABLE_MALE = "CREATE TABLE male(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "name TEXT, age INTEGER)";        //可以不大写
    //删除数据库
    private static final String DROP_TABLE_MALE = "DROP TABLE IF EXIST male";

    public MyDbHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    //如果数据库表不存在，则调用该方法
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MALE);
    }

    //更新数据库，根据版本号
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //先删除表
        db.execSQL(DROP_TABLE_MALE);
        //再创建表
        db.execSQL(CREATE_TABLE_MALE);
    }
}
