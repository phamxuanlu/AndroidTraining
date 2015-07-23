package com.framgia.lupx.androidtraining.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.framgia.lupx.androidtraining.fragments.DatabaseFragment;
import com.framgia.lupx.androidtraining.fragments.HomeArticlesFragment;
import com.framgia.lupx.androidtraining.fragments.MapFragment;
import com.framgia.lupx.androidtraining.fragments.NotificationFragment;
import com.framgia.lupx.androidtraining.fragments.SensorFragment;

/**
 * Created by FRAMGIA\pham.xuan.lu on 20/07/2015.
 */
public class HomePagerAdapter extends FragmentPagerAdapter {

    private String[] titles;

    public HomePagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        this.titles = titles;
    }


    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new HomeArticlesFragment();
                break;
            case 1:
                fragment = new MapFragment();
                break;
            case 2:
                fragment = new NotificationFragment();
                break;
            case 3:
                fragment = new SensorFragment();
                break;
            case 4:
                fragment = new DatabaseFragment();
                break;
        }
        if (fragment == null) {
            fragment = new HomeArticlesFragment();
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return titles.length;
    }
}
