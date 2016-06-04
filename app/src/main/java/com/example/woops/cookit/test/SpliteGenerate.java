package com.example.woops.cookit.test;


import android.util.Log;

import com.example.woops.cookit.bean.Store;
import com.example.woops.cookit.db.CookitDB;
import com.example.woops.cookit.db.MyDatabaseHelper;

public class SpliteGenerate {
    //库存对象
    private Store mStore = new Store();

    private String mResult;
    private String mOperate;
    private String mSurplus;
    private String mMeasure;
    private String mCategory;
    //数据库辅助类
    private MyDatabaseHelper dbHelper;

    public SpliteGenerate(String result) {
        this.mResult = result;
    }

    /**
     * 把语音识别做成命令模式，这是以后要做的事情
     */

    /**
     * 实现分类并存储数据
     */
    public Store classification() {
        mOperate = mResult.substring(0, 2);
        mSurplus = mResult.substring(2, 3);
        mMeasure = mResult.substring(3, 4);
        mCategory = mResult.substring(4, 6);

        mStore.setCategory(mCategory);
        mStore.setMeasure(mMeasure);
        mStore.setSurplus(mSurplus);
        mStore.setOperate(mOperate);
        return mStore;
    }


    public void displayStore() {
        Log.d("MY", mOperate);
        Log.d("MY", mSurplus);
        Log.d("MY", mMeasure);
        Log.d("MY", mCategory);
    }

    /**
     * 将切割好的数据存入数据库中
     */
    public void handleSpliteData(CookitDB cookitDB) {

    }

}
