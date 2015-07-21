package com.framgia.lupx.androidtraining.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.framgia.lupx.androidtraining.R;

/**
 * Created by FRAMGIA\pham.xuan.lu on 21/07/2015.
 */
public class NotificationReceiverActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView txtName;
    private TextView txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        txtName = (TextView) findViewById(R.id.txtFrom);
        txtMessage = (TextView) findViewById(R.id.txtMessage);
        onNewIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        String name = intent.getStringExtra("NAME");
        String message = intent.getStringExtra("MESSAGE");

        txtName.setText("From : " + name);
        txtMessage.setText("Message : \n" + message);
    }
}
