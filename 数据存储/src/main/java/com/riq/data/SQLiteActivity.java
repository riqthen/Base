package com.riq.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * 存放在/data/data/包名/databases
 * xUtils数据库操作
 */
public class SQLiteActivity extends AppCompatActivity {
    private SQLiteDbHelper dbHelper;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        tv = (TextView) findViewById(R.id.tv);
        dbHelper = new SQLiteDbHelper(this, "Book.db", null, 2);    //同一个数据库做操作需要修改版本号
    }

    public void createDb(View view) {
        dbHelper.getWritableDatabase(); //创建数据库
    }

    public void addData(View view) {
        SQLiteDatabase db = dbHelper.getWritableDatabase(); //创建数据库
        ContentValues values = new ContentValues(); //创建数据内容对象
        values.put("name", "红楼梦");
        values.put("author", "曹雪芹");
        values.put("pages", 800);
        values.put("price", 59.99);
        db.insert("Book", null, values);    //insert()方法向数据库添加数据
        values.clear();
        values.put("name", "三国演义");
        values.put("author", "罗贯中");
        values.put("pages", 600);
        values.put("price", 49.99);
        db.insert("Book", null, values);
    }

    public void updateData(View view) {
        SQLiteDatabase db2 = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("price", 40.00);
        db2.update("Book", values, "name = ?", new String[]{"三国演义"}); //修改name列，第四个参数表示name的值

    }

    public void deleteData(View view) {
        SQLiteDatabase db3 = dbHelper.getWritableDatabase();
        db3.delete("Book", "pages > ?", new String[]{"750"});    //删除pages>750的数据
    }

    public void queryData(View view) {
        SQLiteDatabase db4 = dbHelper.getWritableDatabase();
        Cursor cursor = db4.query("Book", null, null, null, null, null, null);
        if (cursor.moveToNext()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                double price = cursor.getDouble(cursor.getColumnIndex("price"));
                tv.setText(name + "   " + author + "   " + pages + "   " + price);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
}
