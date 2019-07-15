package com.mlsdev.mlsdevstore.test.utils;

import android.app.Instrumentation;
import android.os.SystemClock;
import android.view.MotionEvent;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;


public class VerticalSwipe {
    static void swiper(int start, int end, int delay) {
        long downTime = SystemClock.uptimeMillis();
        long eventTime = SystemClock.uptimeMillis();
        Instrumentation inst = getInstrumentation();

        MotionEvent event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_DOWN, 500, start, 0);
        inst.sendPointerSync(event);
        eventTime = SystemClock.uptimeMillis() + delay;
        event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_MOVE, 500, end, 0);
        inst.sendPointerSync(event);
        event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP, 500, end, 0);
        inst.sendPointerSync(event);
        SystemClock.sleep(2000); //The wait is important to scroll
    }

    // This swipes all the way to the bottom of the screen
    public static void swipeToBottom() {
        swiper(1000, 100, 0);
    }

    // This scrolls down one page at a time
    public static void scrollSlowlyDown() {
        swiper(775, 100, 100);
    }

    // This swipes to the top
    public static void swipeToTop() {
        swiper(1000, 1500, 0);
    }

    // This scrolls up one page at a time
    public static void scrollSlowlyUp() {
        swiper(1000, 1500, 100);
    }
}
