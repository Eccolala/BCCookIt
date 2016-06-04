package com.example.woops.cookit.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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
import com.example.woops.cookit.activity.EquDetailAty.FirstEquAty;
import com.example.woops.cookit.fragment.RecyclerViewFragment;
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
        //设备页面
        MaterialRippleLayout eleEquipment = (MaterialRippleLayout) findViewById(R.id.ele_equ_layout);
        eleEquipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, FirstEquAty.class));
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
                    //case 0:
                    //    return RecyclerViewFragment.newInstance();
                    //case 1:
                    //    return RecyclerViewFragment.newInstance();
                    //case 2:
                    //    return WebViewFragment.newInstance();
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
                        return "营养早餐";
                    case 1:
                        return "老人餐";
                    case 2:
                        return "儿童餐";
                    case 3:
                        return "病人餐";
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
                                "http://image.codes51.com/Article/image/20160417/20160417101809_0472.jpg");
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.green,
                                "http://i3.sinaimg.cn/lx/2012/1114/U6626P622DT20121114162756_1.jpg");
                    case 2:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.red,
                                "http://image1.nphoto.net/news/image/201203/c8524a2fb7b2ba6f.jpg");
                    case 3:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.default_fill_color,
                                "http://img4.imgtn.bdimg.com/it/u=1516802001,136721947&fm=21&gp=0.jpg");
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
                "Mark",
                "Refresh",
                "Copy",
                "Heart",
                "Info",
                "Like",
                "Record",
                "Search",
                "Settings"
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
                        if (buttonIndex == 0) {
                           startActivity(new Intent(mContext,ItaActivity.class));
                        } else if (buttonIndex == 1) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(
                                    "https://github.com/Nightonke/BoomMenu")));
                        } else if (buttonIndex == 2) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(
                                    "https://github.com/Nightonke")));
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
