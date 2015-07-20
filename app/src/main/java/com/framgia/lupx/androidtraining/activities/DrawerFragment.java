package com.framgia.lupx.androidtraining.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.lupx.androidtraining.R;
import com.framgia.lupx.androidtraining.adapter.NavDrawerAdapter;
import com.framgia.lupx.androidtraining.models.NavDrawerItem;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by FRAMGIA\pham.xuan.lu on 20/07/2015.
 */
public class DrawerFragment extends Fragment {

    public interface GetDrawerDataCallback {
        List<NavDrawerItem> getDrawerItems();
    }

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private View containerView;
    private RecyclerView recyclerView;
    private NavDrawerAdapter adapter;
    private List<NavDrawerItem> drawerItems;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = getActivity();
        if (activity instanceof GetDrawerDataCallback) {
            drawerItems = ((GetDrawerDataCallback) activity).getDrawerItems();
            if (drawerItems == null) {
                drawerItems = Collections.emptyList();
            }

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        mDrawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        recyclerView = (RecyclerView) view.findViewById(R.id.drawerList);
        adapter = new NavDrawerAdapter(getActivity(), drawerItems);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }
}
