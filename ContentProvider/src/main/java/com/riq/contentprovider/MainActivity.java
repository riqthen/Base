package com.riq.contentprovider;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.baidu.com/s?ie=UTF-8&wd=%E4%BD%BF%E7%94%A8contentprovider%E5%BE%97%E5%88%B0%E8%81%94%E7%B3%BB%E4%BA%BA%E4%BF%A1%E6%81%AF
 * 系统可用的
 * 1. Browser:读取或修改书签,浏览历史或网络搜索;
 * 2. CallLog:查看或更新通话历史;
 * 3. Contacts:获取,修改或保存联系人; (最常用)
 * 4. LiveFolders:由Content Provider提供内容的特定文件夹;
 * 5. MediaStore:访问声音,视频和图片;
 * 6. Setting:查看和获取蓝牙设置,铃声和其它设备偏好;
 * 7. SearchRecentSuggestions:该类能为应用程序创建简单的查询建议提供者,它基于近期查询提供建议;
 * 8. SyncStateContract:用于使用数据数组帐号关联数据的Content Provider约束,希望使用标准方式保存数据的provider可以使用它;
 * 9. UserDictionary:在可以预测文本输入时,提供用户定义的单词给输入法使用.应用程序和输入法能增加到该字典,单词能关联频率信息和本地化信息。
 */

/**
 * 一个程序通过ContentProvider暴露自己的数据，另一个程序通过ContentResolver来访问数据
 */
//联系人数据  需要联系人读写权限  联系人的数据放在不同的表里面，例如头像，电话等分别在不同的表
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        User user = new User();
        List<User> users = new ArrayList<>();
        //TODO 通过ContentResolver来访问数据
        ContentResolver resolver = getContentResolver();
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        Cursor cursor = resolver.query(uri, null, null, null, null);
        while (cursor.moveToNext()) {
            //联系人列表中所有用户的id号
            long id = cursor.getLong(cursor.getColumnIndex(ContactsContract.Contacts.NAME_RAW_CONTACT_ID));
            user.id = id;
            Log.e("========id========", id + "");
            Cursor cursor2 = resolver.query(ContactsContract.Data.CONTENT_URI, null, ContactsContract.Contacts.Data.RAW_CONTACT_ID + "=?", new String[]{id + ""}, null);
            while (cursor2.moveToNext()) {
                user.name = cursor2.getString(cursor2.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME /*"display_name"*/));
                user.phone = cursor2.getString(cursor2.getColumnIndex(ContactsContract.Data.DATA1));
            }
        }
        users.add(user);
        Log.e("--------user-------", users.toString());
    }

    private class User {
        Long id;
        String name;
        String phone;

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    '}';
        }
    }
}
