package com.r3dm4n.bottled;


import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;
    private Activity activity;
    public VerticalViewPager v;

    public SectionsPagerAdapter(FragmentManager fm, Activity activity) {
        super(fm);
        this.activity = activity;
        init();
    }

    private void init() {
        fragments = new ArrayList<Fragment>();
        InboxFragment inbox_fragement = new InboxFragment();
        v =  ((GlobalSingleton) activity.getApplication()).getViewPager();
        inbox_fragement.addViewPager(v);

        fragments.add(new MainFragment()); // 0 - main
        fragments.add(new SettingsFragment()); // 1 - settings
        fragments.add(inbox_fragement); // 2 - inbox
        fragments.add(new UsersFragment()); // 3 - users
        fragments.add(new ChatFragment()); // 4 - chat

        v.setCurrentItem(0);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Main";
            case 1:
                return "Settings";
            case 2:
                return "Inbox";
        }
        return null;
    }
}