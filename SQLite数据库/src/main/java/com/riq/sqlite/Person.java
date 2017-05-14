package com.riq.sqlite;

import android.provider.BaseColumns;

/**
 * Created by 锐 on 2017/3/28.
 * 元数据定义，即项目名，如，姓名，年龄等
 */

public final class Person implements BaseColumns {

    //男性表
    public static final String _ID = "id";
    public static final String TABLE_NAME = "person";
    public static final String NAME = "name";
    public static final String AGE = "age";
}
