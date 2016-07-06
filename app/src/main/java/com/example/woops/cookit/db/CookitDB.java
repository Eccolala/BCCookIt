package com.example.woops.cookit.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.woops.cookit.bean.Store;

import java.util.ArrayList;
import java.util.List;

public class CookitDB {
    //数据库名
    public static final String DB_NAME = "cookit";
    //数据库版本
    public static final int VERSION = 1;

    private static CookitDB cookitDB;

    private SQLiteDatabase db;


    /**
     * 构造方法私有化
     */

    private CookitDB(Context context) {
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(context, DB_NAME
                , null, VERSION);
        db = dbHelper.getWritableDatabase();
    }


    /**
     * 获取CookitDB的实例
     */
    public synchronized static CookitDB getInstance(Context context) {
        if (cookitDB == null) {
            cookitDB = new CookitDB(context);
        }
        return cookitDB;
    }

    /**
     * 将Store实体存储到数据库
     */
    public void savaStore(Store store) {
        if (store != null) {
            ContentValues values = new ContentValues();
            values.put("category", store.getCategory());
            values.put("surplus", store.getSurplus());
            values.put("measure", store.getMeasure());
            db.insert("store", null, values); // 插入第一条数据
            Log.d("MY", "插入数据成功");
        }
    }

    /**
     * 从数据库中读取库存数据
     */
    public List<Store> loadStore() {
        List<Store> list = new ArrayList<Store>();
        Cursor cursor = db
                .query("store", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Store store = new Store();

                store.setId(cursor
                        .getInt(cursor.getColumnIndex("id")));
                store.setCategory(cursor.getString(cursor
                        .getColumnIndex("category")));
                store.setSurplus(cursor.getString(cursor
                        .getColumnIndex("surplus")));
                store.setMeasure(cursor.getString(cursor
                        .getColumnIndex("measure")));
                list.add(store);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    /**
     * 数据库读取测试
     */
    public void loadDataBaseTest() {
        Cursor cursor = db
                .query("store", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
// 遍历Cursor对象,取出数据并打印
                String name = cursor.getString(cursor.
                        getColumnIndex("category"));
                String surplus = cursor.getString(cursor.
                        getColumnIndex("surplus"));
                String measure = cursor.getString(cursor.getColumnIndex
                        ("measure"));
                Log.d("MY", "store category is " + name);
                Log.d("MY", "store surplus is " + surplus);
                Log.d("MY", "store  measure is " + measure);
            } while (cursor.moveToNext());
        }
        cursor.close();

    }

    /**
     * 数据库删除测试
     */
    public void deleteDataBaseTest() {
        db.delete("store", null, null);
        Log.d("MY", "删除数据成功");
    }

}


/**
 * 从数据库读取信息填充listView,请参考第一行代码的酷欧天气
 */

