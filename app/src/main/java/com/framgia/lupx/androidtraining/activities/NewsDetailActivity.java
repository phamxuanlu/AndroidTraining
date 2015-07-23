package com.framgia.lupx.androidtraining.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.lupx.androidtraining.R;
import com.framgia.lupx.androidtraining.adapter.ListStaffAdapter;
import com.framgia.lupx.androidtraining.database.DatabaseHelper;
import com.framgia.lupx.androidtraining.models.Employee;

import java.util.List;

/**
 * Created by FRAMGIA\pham.xuan.lu on 21/07/2015.
 */
public class NewsDetailActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private ImageView header;
    private RecyclerView recyclerView;
    private ListStaffAdapter adapter;
    int mutedColor = R.attr.colorPrimary;
    private DatabaseHelper<Employee> db;
    private List<Employee> lst;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        mToolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(mToolbar);

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Framgia");

        header = (ImageView) findViewById(R.id.header);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.header);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {

                mutedColor = palette.getMutedColor(R.attr.colorPrimary);
                collapsingToolbar.setContentScrimColor(mutedColor);
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.scrollableview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
//        String title = getIntent().getStringExtra("TITLE");
//        Log.v("TITLE", title);

        try {
            db = new DatabaseHelper<>(this, "EMPLOYEE.db", Employee.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        lst = db.getAllRows();
        adapter = new ListStaffAdapter(this, lst);
        recyclerView.setAdapter(adapter);
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
