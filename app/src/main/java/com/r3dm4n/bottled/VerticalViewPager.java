package com.r3dm4n.bottled;

import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.content.Context;
import android.view.View;

/**
 * Uses a combination of a PageTransformer and swapping X & Y coordinates
 * of touch events to create the illusion of a vertically scrolling ViewPager.
 *
 * Requires API 11+
 *
 */
public class VerticalViewPager extends ViewPager {
    private VerticalPageTransformer vpt;
    private TouchHandler th;
    private android.graphics.Point screenDimensions;

    public VerticalViewPager(Context context) {
        super(context);
        init();
    }

    public VerticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void init() {
        // The majority of the magic happens here
        vpt = new VerticalPageTransformer();
        th = new TouchHandler();
        th.setScreenSize(screenDimensions);
        setPageTransformer(true, vpt );
        // The easiest way to get rid of the overscroll drawing that happens on the left and right
        setOverScrollMode(OVER_SCROLL_IF_CONTENT_SCROLLS);
    }

    private class VerticalPageTransformer implements ViewPager.PageTransformer {
        public boolean verticalScroll = false;
        @Override
        public void transformPage(View view, float position) {

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                view.setAlpha(1);

                if (verticalScroll) {
                    // Counteract the default slide transition
                    view.setTranslationX(view.getWidth() * -position);

                    //set Y position to swipe in from top
                    float yPosition = position * view.getHeight();
                    view.setTranslationY(yPosition);
                }

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev){
        boolean intercepted = super.onInterceptTouchEvent(ev);
        return intercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
/*
        if(ev.getActionMasked() == 0) {

            switch (getCurrentItem()) {
                case 1:
                    setCurrentItem(2);
                    vpt.verticalScroll = true;
                    break;
                case 2:
                    setCurrentItem(0);
                    vpt.verticalScroll = false;
                    break;
                default:
                    setCurrentItem(1);
                    vpt.verticalScroll = true;
                    break;
            }
        }
*/
       int swipe = th.process(ev.getActionMasked(), ev.getX(), ev.getY());

       //swipe 1 = up, 2 right , 3 down, 4 left

        getCurrentItem(); //returns the fragment number
        //you also need to map all the fragments out...
        //you also need to not create new fragment instances all the time.
        //

        int current = getCurrentItem();
        switch(swipe) {
            // swipe up
            case 1:
          //      setCurrentItem(0);
                Log.d("T","UP");
                vpt.verticalScroll = true;
                break;

            // swipe right
            case 2:
                switch(current) {
                    case 0: // main -->
                        setCurrentItem(1); // settings
                        break;
                    case 2: // Inbox -->
                        setCurrentItem(0); // main
                        break;
                    case 3: // Users -->
                        setCurrentItem(2); // Inbox
                    case 4: // chat -->
                        setCurrentItem(2); // Inbox
                    default:
                        break;
                }
                Log.d("T", "RIGHT");
                vpt.verticalScroll = false;
                break;

            // swipe down
            case 3:
                Log.d("T", "DOWN");
              //  setCurrentItem(1);
                vpt.verticalScroll = true;
                break;

            // left swipe
            case 4:
                switch(current) {
                    case 0: // main -->
                        setCurrentItem(2); // inbox
                        break;
                    case 1: // Settings -->
                        setCurrentItem(0); // main
                        break;
                    case 2: // inbox -->
                        setCurrentItem(3); // users
                    default:
                        break;
                }
                Log.d("T","LEFT");
                vpt.verticalScroll = false;
            default:
                break;
        }
        return true;
    }

    public void setScreenDimensions(android.graphics.Point p) {
        this.screenDimensions = p;
    }



}