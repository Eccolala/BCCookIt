package com.example.woops.cookit.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {


    //创建食材管理table语句
    //category 是食材种类
    //surplus 是食材剩余量具体数值
    //measure 是食材剩余量的单位，比如斤 公斤 个 捆
    public static String CREATE_STORE_TABLE = "create table store ("
            + "id integer primary key autoincrement, "
            + "category text, "
            + "surplus integer, "
            + "measure text)";

    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STORE_TABLE);
        Toast.makeText(mContext,"创建库存表成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
