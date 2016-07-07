package com.example.woops.cookit.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.balysv.materialripple.MaterialRippleLayout;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.example.woops.cookit.R;
import com.example.woops.cookit.fragment.RecyclerViewFragment;
import com.example.woops.cookit.fragment.RecyclerViewFragment1;
import com.example.woops.cookit.fragment.RecyclerViewFragment3;
import com.example.woops.cookit.fragment.RecyclerViewFragment4;
import com.example.woops.cookit.util.CoCoinUtil;
import com.example.woops.cookit.util.CustomSliderView;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.github.johnpersano.supertoasts.SuperToast;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Eases.EaseType;
import com.nightonke.boommenu.Types.BoomType;
import com.nightonke.boommenu.Types.ButtonType;
import com.nightonke.boommenu.Types.ClickEffectType;
import com.nightonke.boommenu.Types.DimType;
import com.nightonke.boommenu.Types.OrderType;
import com.nightonke.boommenu.Types.PlaceType;
import com.nightonke.boommenu.Util;

import java.util.HashMap;
import java.util.Random;

public class FirstActivity extends AppCompatActivity {
    private MaterialViewPager mViewPager;
    private Context mContext;
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    //小箭头
    private ActionBarDrawerToggle mDrawerToggle;
    private SliderLayout mDemoSlider;

    //设置BoomMenu
    private boolean init = false;
    private BoomMenuButton boomMenuButton;

    //打开第三方app的包名
    public static final String EQUEMENT_PACKAGE_NAME = "mr_immortalz.com.modelqq";
    public static final String OPENCV_PACKAGE_NAME = "com.example.woops.opencvtest";
    public static final String AR_PACKAGE_NAME = "cn.easyar.samples.helloarvideo";
    public static final String STORE_PACKAGE_NAME = "com.just.firstapp";
    public static final String NEU_PACKAGE_NAME = "lecho.lib.hellocharts.samples";
    public static final String CAP_PACKAGE_NAME = "com.just.learnintentservice";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        /**
         * 设置超级Toast
         */
        SuperToast.cancelAllSuperToasts();

        //设置侧滑菜单监听器
        setListener();


        //获取Context
        mContext = this;
        CoCoinUtil.init(mContext);
        /**
         * 设置MaterialViewPager和ToolBar
         */
        setMaterialViewPager();

        /**
         * 设置Toggle
         */
        setToggle();


        /**
         * 设置slider
         */
        setSlider();

        boomMenuButton = (BoomMenuButton) findViewById(R.id.boom);


    }

    private void setListener() {
        MaterialRippleLayout community = (MaterialRippleLayout) findViewById(R.id.comunity_layout);
        community.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, ComtyActivity.class));
            }
        });
        //厨房设备管理设备页面
        MaterialRippleLayout eleEquipment = (MaterialRippleLayout) findViewById(R.id.ele_equ_layout);
        eleEquipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(mContext.getPackageManager().getLaunchIntentForPackage(EQUEMENT_PACKAGE_NAME));
            }
        });
        //easyAR采购助手
        MaterialRippleLayout arHelper = (MaterialRippleLayout) findViewById(R.id.arbuy_layout);
        arHelper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(mContext.getPackageManager().getLaunchIntentForPackage(AR_PACKAGE_NAME));
            }
        });
        //库存管理
        MaterialRippleLayout storeHelper = (MaterialRippleLayout) findViewById(R.id.store_layout);
        storeHelper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(mContext.getPackageManager().getLaunchIntentForPackage(STORE_PACKAGE_NAME));
            }
        });
        //饮食营养分享
        MaterialRippleLayout neuHelper = (MaterialRippleLayout) findViewById(R.id.report_layout);
        neuHelper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(mContext.getPackageManager().getLaunchIntentForPackage(NEU_PACKAGE_NAME));
            }
        });

        //opencv
        MaterialRippleLayout cvHelper = (MaterialRippleLayout) findViewById(R.id.open_layout);
        cvHelper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(mContext.getPackageManager().getLaunchIntentForPackage(OPENCV_PACKAGE_NAME));
            }
        });
        //厨房监控
        MaterialRippleLayout keepHelper = (MaterialRippleLayout) findViewById(R.id.sync_layout);
        keepHelper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(mContext.getPackageManager().getLaunchIntentForPackage(CAP_PACKAGE_NAME));
            }
        });

    }

    private void setSlider() {
        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        //设置图片资源
        HashMap<String, Integer> urls = CoCoinUtil.GetDrawerTopUrl();

        //设置一个装载slider的容器CustomSliderView
        for (String name : urls.keySet()) {
            CustomSliderView customSliderView = new CustomSliderView(this);
            // initialize a SliderLayout
            customSliderView
                    .image(urls.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            mDemoSlider.addSlider(customSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.ZoomOut);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.setCustomIndicator((PagerIndicator) findViewById(R.id.custom_indicator));
    }

    private void setToggle() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, 0, 0) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        mDrawer.setDrawerListener(mDrawerToggle);
    }

    private void setMaterialViewPager() {

        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);
//        mViewPager.getPagerTitleStrip().setTypeface(CoCoinUtil.getInstance().typefaceLatoLight, Typeface.NORMAL);
        View logo = findViewById(R.id.logo_white);
        if (logo != null) {
            logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.notifyHeaderChanged();
                }
            });
        }

        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                switch (position % 4) {
                    case 0:
                        return RecyclerViewFragment1.newInstance();
                    case 1:
                        return RecyclerViewFragment.newInstance();
                    case 2:
                        return RecyclerViewFragment3.newInstance();
                    case 3:
                        return RecyclerViewFragment4.newInstance();
                    default:
                        return RecyclerViewFragment.newInstance();
                }
            }

            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % 7) {
                    case 0:
                        return "母婴专区";
                    case 1:
                        return "库存推荐";
                    case 2:
                        return "美容瘦身";
                    case 3:
                        return "食疗养生";
                }
                return "";
            }
        });

        /**
         * 设置Header的渐变色及背景图片
         */
        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.red,
                                "http://www.yys100.com/f_manage/edit/UploadFile/2015810232831432.JPG");
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.green,
                                "http://tgi1.jia.com/112/664/12664172.jpg");
                    case 2:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.default_fill_color,
                                "http://img01.baimao.com/M01/2D/90/wKgAFFOjiyKAGRV0AAHdKAwVdSo287.jpg");
                    case 3:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.default_fill_color,
                                "http://image.enmuo.com/CMS/2012/03/30/1/CMS_120330165310136_1E_600x400.jpg");
                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });

        /**
         * 设置一些奇怪的东西，并不明白
         */
        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        /**
         * 设置ToolBar
         */
        toolbar = mViewPager.getToolbar();
        setTitle("");

        if (toolbar != null) {
            setSupportActionBar(toolbar);

            final ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowHomeEnabled(true);
                actionBar.setDisplayShowTitleEnabled(true);
                actionBar.setDisplayUseLogoEnabled(false);
                actionBar.setHomeButtonEnabled(true);
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item) ||
                super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawers();
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

//        if (init) return;
//        init = true;


        /**
         * 这里强行设置了Drawable数组的大小为9，其实并不科学
         */
        int number = 9;
        Drawable[] drawables = new Drawable[number];
        int[] drawablesResource = new int[]{
                R.drawable.mark,
                R.drawable.refresh,
                R.drawable.copy,
                R.drawable.heart,
                R.drawable.info,
                R.drawable.like,
                R.drawable.record,
                R.drawable.search,
                R.drawable.settings
        };
        for (int i = 0; i < number; i++)
            drawables[i] = ContextCompat.getDrawable(mContext, drawablesResource[i]);

        String[] STRINGS = new String[]{
                "收藏",
                "刷新",
                "保存",
                "点赞",
                "信息",
                "分享",
                "视频",
                "搜索",
                "设置"
        };
        String[] strings = new String[number];
        for (int i = 0; i < number; i++)
            strings[i] = STRINGS[i];

        int[][] colors = new int[number][2];
        for (int i = 0; i < number; i++) {
            colors[i][1] = GetRandomColor();
            colors[i][0] = Util.getInstance().getPressedColor(colors[i][1]);
        }

        // this is an example to show how to use the builder
        new BoomMenuButton.Builder()
                // set all sub buttons with subButtons method
                //.subButtons(subButtonDrawables, subButtonColors, subButtonTexts)
                // or add each sub button with addSubButton method
                .subButtons(drawables, colors, strings)
                .frames(80)
                .duration(1000)
                .delay(100)
                .showOrder(OrderType.RANDOM)
                .hideOrder(OrderType.RANDOM)
                .button(ButtonType.CIRCLE)
                .boom(BoomType.PARABOLA)
                .place(PlaceType.CIRCLE_9_2)
                .showMoveEase(EaseType.EaseOutBack)
                .hideMoveEase(EaseType.EaseOutCirc)
                .showScaleEase(EaseType.EaseOutBack)
                .hideScaleType(EaseType.EaseOutCirc)
                .rotateDegree(720)
                .showRotateEase(EaseType.EaseOutBack)
                .hideRotateType(EaseType.Linear)
                .autoDismiss(true)
                .cancelable(true)
                .dim(DimType.DIM_6)
                .clickEffect(ClickEffectType.RIPPLE)
                .boomButtonShadow(Util.getInstance().dp2px(2), Util.getInstance().dp2px(2))
                .subButtonsShadow(Util.getInstance().dp2px(2), Util.getInstance().dp2px(2))
                .subButtonTextColor(Color.BLACK)
                .onBoomButtonBlick(null)
                .animator(null)
                .onSubButtonClick(new BoomMenuButton.OnSubButtonClickListener() {
                    @Override
                    public void onClick(int buttonIndex) {
                        if (buttonIndex == 7) {
                            startActivity(new Intent(mContext, ItaActivity.class));
                        }
                    }
                })
                // this only work when the place type is SHARE_X_X
                .shareStyle(0, 0, 0)
                .init(boomMenuButton);

    }

    private String[] Colors = {
            "#F44336",
            "#E91E63",
            "#9C27B0",
            "#2196F3",
            "#03A9F4",
            "#00BCD4",
            "#009688",
            "#4CAF50",
            "#8BC34A",
            "#CDDC39",
            "#FFEB3B",
            "#FFC107",
            "#FF9800",
            "#FF5722",
            "#795548",
            "#9E9E9E",
            "#607D8B"};

    private int GetRandomColor() {
        Random random = new Random();
        int p = random.nextInt(Colors.length);
        return Color.parseColor(Colors[p]);
    }


}
