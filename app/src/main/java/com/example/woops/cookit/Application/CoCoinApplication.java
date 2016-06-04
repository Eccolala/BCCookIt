package com.example.woops.cookit.Application;

import android.app.Application;
import android.content.Context;
import android.provider.Settings;

/**
 * Created by woops on 16-4-30.
 */
public class CoCoinApplication extends Application {
    public static final int VERSION = 120;

    private static Context mContext;



    @Override
    public void onCreate() {
        super.onCreate();

        CoCoinApplication.mContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return CoCoinApplication.mContext;
    }

    public static String getAndroidId() {
        return Settings.Secure.getString(
                getAppContext().getContentResolver(), Settings.Secure.ANDROID_ID);
    }
}
