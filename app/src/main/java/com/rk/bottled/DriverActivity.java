package com.rk.bottled;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import com.rk.bottled.R;

// git test

public class DriverActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private VerticalViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (VerticalViewPager) findViewById(R.id.container);
        Log.d("RFM", this + "'");
        ((GlobalSingleton) this.getApplication()).setViewPager(mViewPager);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), this);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setScreenDimensions(getSreenSize());
        mViewPager.init();


        mViewPager.setCurrentItem(0);
    }

    public android.graphics.Point getSreenSize() {
        Log.i("RFM", "context " + this.getBaseContext());

        WindowManager wm = (WindowManager) this.getBaseContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        android.graphics.Point size = new android.graphics.Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        Log.i("RFM", "width= "  + width + "height= " + height);
        return new android.graphics.Point(width, height);
    }


}