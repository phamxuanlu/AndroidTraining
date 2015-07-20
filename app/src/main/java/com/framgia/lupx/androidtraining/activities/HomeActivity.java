package com.framgia.lupx.androidtraining.activities;

import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.framgia.lupx.androidtraining.R;
import com.framgia.lupx.androidtraining.fragments.HomeFragment;
import com.framgia.lupx.androidtraining.models.NavDrawerItem;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity implements DrawerFragment.GetDrawerDataCallback, HomeFragment.GetTabsCallback {

    private Toolbar mToolbar;
    private DrawerFragment mDrawerFragment;
    private HomeFragment homeFragment;

    @Override
    public List<NavDrawerItem> getDrawerItems() {
        List<NavDrawerItem> lst = new ArrayList<>();
        lst.add(new NavDrawerItem(R.drawable.home_32, "Home"));
        lst.add(new NavDrawerItem(R.drawable.map_32, "Maps"));
        lst.add(new NavDrawerItem(R.drawable.music_32, "Music"));
        lst.add(new NavDrawerItem(R.drawable.setting, "Settings"));
        lst.add(new NavDrawerItem(R.drawable.setting, "About"));

        return lst;
    }

    @Override
    public String[] getTabTitles() {
        String[] tabs = new String[]{
                "Home",
                "Maps",
                "Music",
                "News"
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

        homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, homeFragment).commit();

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
