package com.framgia.lupx.androidtraining;

import android.app.Application;
import android.content.Intent;
import android.os.Environment;

import services.MapLocationService;

/**
 * Created by FRAMGIA\pham.xuan.lu on 20/07/2015.
 */
public class AndroidTrainingApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MyApp.getInstance(this).cacheSize = (int) (Runtime.getRuntime().maxMemory() / 1024);
        MyApp.getInstance(this).cacheSize /= 8;

        Intent intent = new Intent(this, MapLocationService.class);
        startService(intent);
    }
}
