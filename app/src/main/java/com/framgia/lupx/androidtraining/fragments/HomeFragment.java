package com.framgia.lupx.androidtraining.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.framgia.lupx.androidtraining.R;
import com.framgia.lupx.androidtraining.adapter.HomePagerAdapter;

/**
 * Created by FRAMGIA\pham.xuan.lu on 20/07/2015.
 */
public class HomeFragment extends Fragment {

    public interface GetTabsCallback {
        String[] getTabTitles();
    }

    private PagerSlidingTabStrip tabStrip;
    private String[] titles;
    private ViewPager viewPager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = getActivity();
        if (activity instanceof GetTabsCallback) {
            titles = ((GetTabsCallback) activity).getTabTitles();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        tabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        tabStrip.setUnderlineHeight(0);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        HomePagerAdapter adapter = new HomePagerAdapter(getActivity().getSupportFragmentManager(), titles);
        viewPager.setAdapter(adapter);
        tabStrip.setViewPager(viewPager);
        return view;
    }
}
