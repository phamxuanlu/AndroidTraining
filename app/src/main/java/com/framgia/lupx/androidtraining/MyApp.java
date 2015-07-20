package com.framgia.lupx.androidtraining;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by FRAMGIA\pham.xuan.lu on 20/07/2015.
 */
public class MyApp {
    public int cacheSize;
    private static MyApp _instance;
    private static Context context;
    private RequestQueue mRequestQueue;


    private MyApp(Context context) {
        this.context = context;

        mRequestQueue = getRequestQueue();

    }

    public static synchronized MyApp getInstance(Context context) {
        if (_instance == null)
            _instance = new MyApp(context);
        return _instance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addRequest(Request<T> req) {
        getRequestQueue().add(req);
    }

}
