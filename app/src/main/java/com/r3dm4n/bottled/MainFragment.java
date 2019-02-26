package com.r3dm4n.bottled;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainFragment extends Fragment {

    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         view = inflater.inflate(R.layout.fragment_main, container, false);
        ViewPager mViewPager = (ViewPager) view.findViewById(R.id.container_main);
    //    SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
    //    mViewPager.setAdapter(mSectionsPagerAdapter);

        return view;
    }

    public View getView() {
        return view;
    }

}