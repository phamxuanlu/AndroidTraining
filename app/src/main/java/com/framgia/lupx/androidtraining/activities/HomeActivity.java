package com.framgia.lupx.androidtraining.activities;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;
import com.framgia.lupx.androidtraining.R;
import com.framgia.lupx.androidtraining.adapter.HomePagerAdapter;
import com.framgia.lupx.androidtraining.adapter.RecyclerViewItemClickListener;
import com.framgia.lupx.androidtraining.fragments.HomeFragment;
import com.framgia.lupx.androidtraining.models.NavDrawerItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class HomeActivity extends AppCompatActivity implements DrawerFragment.GetDrawerDataCallback, HomeFragment.GetTabsCallback {

    private Toolbar mToolbar;
    private DrawerFragment mDrawerFragment;

    private PagerSlidingTabStrip tabStrip;
    private String[] titles;
    private ViewPager viewPager;

    @Override
    public List<NavDrawerItem> getDrawerItems() {
        List<NavDrawerItem> lst = new ArrayList<>();
        lst.add(new NavDrawerItem(R.drawable.globe, "Home"));
        lst.add(new NavDrawerItem(R.drawable.map, "Maps"));
        lst.add(new NavDrawerItem(R.drawable.megaphone, "Notification"));
        lst.add(new NavDrawerItem(R.drawable.music, "Sensor"));
        lst.add(new NavDrawerItem(R.drawable.gear, "Database"));
        lst.add(new NavDrawerItem(R.drawable.gear, "Settings"));
        lst.add(new NavDrawerItem(R.drawable.profle, "About"));


        return lst;
    }

    @Override
    public RecyclerViewItemClickListener getNavItemClickListener() {
        return new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String tab = getDrawerItems().get(position).name.toLowerCase();
                for (int i = 0; i < titles.length; i++) {
                    if (titles[i].toLowerCase().equals(tab)) {
                        viewPager.setCurrentItem(position);
                        getSupportActionBar().setTitle(getDrawerItems().get(position).name);
                    } else {
                        if (tab.equals("settings")) {
                            Intent intent = new Intent(HomeActivity.this, NewsDetailActivity.class);
                            startActivity(intent);
                        }
                        if (tab.equals("about")) {

                        }

                    }
                }
                mDrawerFragment.closeNavDrawer();
            }
        };
    }

    @Override
    public String[] getTabTitles() {
        String[] tabs = new String[]{
                "Home",
                "Maps",
                "Notification",
                "Sensor",
                "Database"
        };
        return tabs;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mDrawerFragment = (DrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        mDrawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);

        titles = getTabTitles();
        tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabStrip.setUnderlineHeight(0);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        HomePagerAdapter adapter = new HomePagerAdapter(getSupportFragmentManager(), titles);
        viewPager.setAdapter(adapter);
        tabStrip.setViewPager(viewPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
