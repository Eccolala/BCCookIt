package com.example.woops.cookit.util;

import android.content.Context;
import android.view.animation.AccelerateInterpolator;
import android.widget.Scroller;

/**
 * Created by woops on 16-5-25.
 */
public class FixedSpeedScroller extends Scroller{
    private int mDuration = 1000;

    public FixedSpeedScroller(Context context, AccelerateInterpolator accelerateInterpolator) {
        super(context,accelerateInterpolator);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy,mDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, mDuration);
    }
    public void setmDuration(int time) {
        mDuration = time;
    }

    public int getmDuration() {
        return mDuration;
    }
}
