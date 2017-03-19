package com.example.joe_pc.joeweather.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;

/**
 * Created by Joe_PC on 2017/3/17.
 */

public class TouchEventLayout extends LinearLayout {


    public TouchEventLayout(Context context) {
        this(context, null);
    }

    public TouchEventLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TouchEventLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float startX = 0.0f;
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            startX = event.getX();
        } else if (action == MotionEvent.ACTION_UP) {
            performClick();
            changeAlpha(0.0f, 1.0f);
        } else if (action == MotionEvent.ACTION_SCROLL) {
            float endX = event.getX();
            if (startX - endX > 0) {//向上滑
                changeAlpha(0.0f, 1.0f);
            } else {//向下滑
                changeAlpha(1.0f, 0.0f);
            }
        } else if (action == MotionEvent.ACTION_CANCEL) {
            changeAlpha(0.0f, 1.0f);
        }
        return true;
    }

    private void changeAlpha(float start, float end) {
        ObjectAnimator.ofFloat(this, "alpha", start, end).setDuration(500).start();
    }
}
