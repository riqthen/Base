package com.riq.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by 锐 on 2017/3/28.
 */

public class MaleDao {

    private MyDbHelper dbHelper;

    public MaleDao(Context context) {
        dbHelper = new MyDbHelper(context);
    }

    //添加
    public void add(Male male) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Person.NAME, male.getName());
        values.put(Person.AGE, male.getAge());
        //合法：insert into male(name, age) values('zhang', 10);
        //不合法：insert into male(name, age) values(null, null);
        db.insert(Person.TABLE_NAME, /*Person.NAME*/null, values);   //写Person.NAME是因为，保证当values为空的时候语句合法
        db.close();
    }


    //删除
    public void delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Person.TABLE_NAME
                , Person._ID
                , new String[]{String.valueOf(id)});
        db.close();
    }


    //更新
    public void update(Male male) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Person.NAME, male.getName());
        values.put(Person.AGE, male.getAge());
        db.update(Person.TABLE_NAME
                , values
                , Person._ID
                , new String[]{String.valueOf(male.getId())});
    }


    //根据id查询
    public Male findById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(true   //是否去重复记录
                , Person.TABLE_NAME
                , new String[]{Person.NAME, Person.AGE}   //null 表示查询所有列
                , Person._ID + "=?"      //查询条件
                , new String[]{String.valueOf(id)}      //查询条件的值
                , null      //分组条件
                , null      //分组条件的值
                , null      //排序
                , null);    //分页条件
        Male male = null;
        while (cursor.moveToNext()) {
            male = new Male();
            male.setId(cursor.getColumnIndexOrThrow(Person._ID));
            male.setName(cursor.getString(cursor.getColumnIndexOrThrow(Person.NAME)));
            male.setAge(cursor.getInt(cursor.getColumnIndexOrThrow(Person.AGE)));
        }
        cursor.close();
        db.close();
        return male;
    }

    //查询所有
    public ArrayList<Male> findAll() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(true   //是否去重复记录
                , Person.TABLE_NAME
                , new String[]{Person.NAME, Person.AGE}   //null 表示查询所有列
                , null      //查询条件
                , null      //查询条件的值
                , null      //分组条件
                , null      //分组条件的值
                , null      //排序
                , null);    //分页条件
        ArrayList<Male> males = new ArrayList<>();
        Male male;
        while (cursor.moveToNext()) {
            male = new Male();
            male.setId(cursor.getColumnIndexOrThrow(Person._ID));
            male.setName(cursor.getString(cursor.getColumnIndexOrThrow(Person.NAME)));
            male.setAge(cursor.getInt(cursor.getColumnIndexOrThrow(Person.AGE)));
            males.add(male);
        }
        cursor.close();
        db.close();
        return males;
    }

//=================使用原生语句===============

    //SQLite语句添加
    public void rawAdd(Male male) {
        String sql = "insert into male(name, age) values(?, ?)";
        Object[] args = {male.getName(), male.getAge()};
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(sql, args);
        db.close();
    }

    //SQLite语句删除
    public void rawDelete(int id) {
        String sql = "delete from male where _id = ?";
        Object[] args = {id};
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(sql, args);
        db.close();
    }

    //SQLite语句更新
    public void rawUpdate(Male male) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "update from male where _id = ?";
        Object[] args = {male.getName(), male.getAge(), male.getId()};
        db.execSQL(sql, args);
        db.close();
    }

    //SQLite语句查询
    public Male rawFindById(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "select _id, name, age from male where _id = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});
        Male male = null;
        if (cursor.moveToNext()) {
            male = new Male();
            male.setId(cursor.getColumnIndexOrThrow(Person._ID));
            male.setName(cursor.getString(cursor.getColumnIndexOrThrow(Person.NAME)));
            male.setAge(cursor.getInt(cursor.getColumnIndexOrThrow(Person.AGE)));
        }
        cursor.close();
        db.close();
        return male;
    }

    //SQLite语句查询所有
    public ArrayList<Male> rawFindAll() {
        String sql = "select _id, name, age from male";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<Male> males = new ArrayList<>();
        Male male;
        while (cursor.moveToNext()) {
            male = new Male();
            male.setId(cursor.getColumnIndexOrThrow(Person._ID));
            male.setName(cursor.getString(cursor.getColumnIndexOrThrow(Person.NAME)));
            male.setAge(cursor.getInt(cursor.getColumnIndexOrThrow(Person.AGE)));
            males.add(male);
        }
        cursor.close();
        db.close();
        return males;
    }

    //事务transaction
    public void transaction() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.beginTransaction();
            db.execSQL("insert into male(name, age) values('li', 3)");
            db.execSQL("update male set age = ? where male name = ?", new Object[]{"zhao", 8});
            db.setTransactionSuccessful();  //设置事务成功的标志（true）
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();        //结束事务，判断事务的标记是否为true，如果为true则提交，否则则回滚事务
        }
        db.close();
    }

}
