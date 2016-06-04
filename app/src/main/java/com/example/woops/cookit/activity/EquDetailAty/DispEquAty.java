//package com.example.woops.cookit.activity.EquDetailAty;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.support.v4.view.PagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.util.SparseArray;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.animation.AccelerateInterpolator;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.woops.cookit.R;
//import com.example.woops.cookit.bean.EquInfo;
//import com.example.woops.cookit.ui.CustomViewPager;
//import com.example.woops.cookit.ui.ZoomOutPageTransformer;
//import com.example.woops.cookit.util.FixedSpeedScroller;
//
//import java.lang.reflect.Field;
//
//
//public class DispEquAty extends Activity implements ViewPager.OnPageChangeListener {
//
//    private CustomViewPager viewPager;
//    private RelativeLayout ryContainer;
//    private int[] mImgs = {R.drawable.len, R.drawable.leo, R.drawable.lep,
//            R.drawable.leq, R.drawable.ler, R.drawable.les, R.drawable.mln, R.drawable.mmz, R.drawable.mna,
//            R.drawable.mnj, R.drawable.leo, R.drawable.leq, R.drawable.les, R.drawable.lep};
//    private String[] mNames = {"电饭锅", "电水壶", "微波炉","抽油烟机","电磁炉","电饭锅", "电水壶", "微波炉","抽油烟机","电磁炉",
//            "电饭锅", "电水壶", "微波炉","抽油烟机","电磁炉","抽油烟机","电磁炉"};
//    private int mPosition;
//    private FixedSpeedScroller scroller;
//    private SparseArray<EquInfo> mDatas = new SparseArray<>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_disp_equ_aty);
//        initView();
//        initData();
//
//        /**
//         * 将Viewpager所在容器的事件分发交给ViewPager
//         */
//        ryContainer.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return viewPager.dispatchTouchEvent(event);
//            }
//        });
//        ViewpagerAdapter mAdapter = new ViewpagerAdapter();
//        viewPager.setAdapter(mAdapter);
//        //设置缓存数为展示的数目
//        viewPager.setOffscreenPageLimit(mImgs.length);
//        viewPager.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.viewpager_margin));
//        //设置切换动画
//        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
//        viewPager.addOnPageChangeListener(this);
//        setViewPagerSpeed(250);
//
//
//    }
//
//    private void initData() {
//        for (int i = 0; i < mImgs.length; i++) {
//            EquInfo info = new EquInfo();
//            info.setPic(mImgs[i]);
//            info.setName(mNames[(int) (Math.random() * mNames.length)]);
//            info.setSex(i % 3 == 0 ? false : true);
//            mDatas.put(i, info);
//        }
//    }
//
//    private void initView() {
//        viewPager = (CustomViewPager) findViewById(R.id.vp);
//    }
//
//    /**
//     * 设置ViewPager切换速度
//     *
//     * @param duration
//     */
//    private void setViewPagerSpeed(int duration) {
//        try {
//            Field field = ViewPager.class.getDeclaredField("mScroller");
//            field.setAccessible(true);
//            scroller = new FixedSpeedScroller(DispEquAty.this, new AccelerateInterpolator());
//            field.set(viewPager, scroller);
//            scroller.setmDuration(duration);
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        mPosition = position;
//    }
//
//    @Override
//    public void onPageSelected(int position) {
//        //当手指左滑速度大于2000时viewpager右滑（注意是item+2）
//        if (viewPager.getSpeed() < -1800) {
//
//            viewPager.setCurrentItem(mPosition + 2);
//            viewPager.setSpeed(0);
//        } else if (viewPager.getSpeed() > 1800 && mPosition > 0) {
//            //当手指右滑速度大于2000时viewpager左滑（注意item-1即可）
//            viewPager.setCurrentItem(mPosition - 1);
//            viewPager.setSpeed(0);
//        }
//    }
//
//    @Override
//    public void onPageScrollStateChanged(int state) {
//
//    }
//
//
//
//    class ViewpagerAdapter extends PagerAdapter {
//        @Override
//        public Object instantiateItem(ViewGroup container, final int position) {
//            final EquInfo info = mDatas.get(position);
//            //设置一大堆演示用的数据，麻里麻烦~~
//            View view = LayoutInflater.from(DispEquAty.this).inflate(R.layout.viewpager_layout, null);
//            ImageView ivPortrait = (ImageView) view.findViewById(R.id.iv);
//            ImageView ivSex = (ImageView) view.findViewById(R.id.iv_sex);
//            TextView tvName = (TextView) view.findViewById(R.id.tv_name);
//            TextView tvDistance = (TextView) view.findViewById(R.id.tv_distance);
//            tvName.setText(info.getTitle());
//            tvDistance.setText(info.getDistance() + "km");
//            ivPortrait.setImageResource(info.getPortraitId());
//            if (info.getSex()) {
//                ivSex.setImageResource(R.drawable.girl);
//            } else {
//                ivSex.setImageResource(R.drawable.boy);
//            }
//            ivPortrait.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(DispEquAty.this, "这是 " + info.getTitle() + " >.<", Toast.LENGTH_SHORT).show();
//                }
//            });
//            container.addView(view);
//            return view;
//        }
//
//        @Override
//        public int getCount() {
//            return mImgs.length;
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view == object;
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            View view = (View) object;
//            container.removeView(view);
//        }
//
//    }
//}
//
