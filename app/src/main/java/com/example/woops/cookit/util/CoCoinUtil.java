package com.example.woops.cookit.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;

import com.example.woops.cookit.Application.CoCoinApplication;
import com.example.woops.cookit.R;

import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

/**
 * Created by woops on 16-4-30.
 */
public class CoCoinUtil {
    public static Typeface typefaceLatoRegular = null;
    public static Typeface typefaceLatoHairline = null;
    public static Typeface typefaceLatoLight = null;

    public static RelativeSizeSpan relativeSizeSpan;
    public static ForegroundColorSpan redForegroundSpan;
    public static ForegroundColorSpan greenForegroundSpan;
    public static ForegroundColorSpan whiteForegroundSpan;
    private static String lastColor0, lastColor1, lastColor2;
    private static Random random;
    public static int MY_BLUE;


    public static void init(Context context) {

        typefaceLatoRegular = Typeface.createFromAsset(
                context.getAssets(), "fonts/Lato-Regular.ttf");
        typefaceLatoHairline = Typeface.createFromAsset(
                context.getAssets(), "fonts/Lato-Hairline.ttf");
        typefaceLatoLight = Typeface.createFromAsset(
                context.getAssets(), "fonts/LatoLatin-Light.ttf");
        relativeSizeSpan = new RelativeSizeSpan(2f);
        redForegroundSpan = new ForegroundColorSpan(Color.parseColor("#ff5252"));
        greenForegroundSpan = new ForegroundColorSpan(Color.parseColor("#4ca550"));
        whiteForegroundSpan = new ForegroundColorSpan(Color.parseColor("#ffffff"));

        lastColor0 = "";
        lastColor1 = "";
        lastColor2 = "";


        random = new Random();

//        MY_BLUE = ContextCompat.getColor(CoCoinApplication.getAppContext(), R.color.my_blue);
    }

    public static int[] DRAWER_TOP_URL = {
            R.drawable.material_design_0,
            R.drawable.material_design_1,
            R.drawable.material_design_2,
            R.drawable.material_design_3,
            R.drawable.material_design_4
    };

    public static HashMap<String, Integer> GetDrawerTopUrl() {
        HashMap<String, Integer> drawerTopUrls = new HashMap<>();
        drawerTopUrls.put("0", DRAWER_TOP_URL[0]);
        drawerTopUrls.put("1", DRAWER_TOP_URL[1]);
        drawerTopUrls.put("2", DRAWER_TOP_URL[2]);
        drawerTopUrls.put("3", DRAWER_TOP_URL[3]);
        drawerTopUrls.put("4", DRAWER_TOP_URL[4]);
        return drawerTopUrls;
    }
    public static Typeface GetTypeface() {
        if (typefaceLatoLight == null) init(CoCoinApplication.getAppContext());
        if ("en".equals(Locale.getDefault().getLanguage()))
            return typefaceLatoLight;
        if ("zh".equals(Locale.getDefault().getLanguage()))
            return Typeface.DEFAULT;
        return typefaceLatoLight;
    }

    private static CoCoinUtil ourInstance = new CoCoinUtil();
    public static CoCoinUtil getInstance() {
        if (ourInstance == null || typefaceLatoLight == null || typefaceLatoHairline == null) {
            ourInstance = new CoCoinUtil();
            init(CoCoinApplication.getAppContext());
        }
        return ourInstance;
    }
}
