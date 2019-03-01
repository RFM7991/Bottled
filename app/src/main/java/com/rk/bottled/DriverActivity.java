package com.rk.bottled;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

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

    // hide keyboard on pressing message space
    public void pressMessageSpace(View view) {

        hideKeyboard(this);
    }

    // hide keyboard
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}