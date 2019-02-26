package com.r3dm4n.bottled;

import android.app.Application;

public class GlobalSingleton extends Application {

    public VerticalViewPager viewPager;

    public void setViewPager(VerticalViewPager v) {
        viewPager = v;
    }

    public VerticalViewPager getViewPager() {
        return viewPager;
    }
}
