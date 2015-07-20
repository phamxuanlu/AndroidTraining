package com.framgia.lupx.androidtraining.asynctasks;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.framgia.lupx.androidtraining.BitmapLruCache;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;

/**
 * Created by FRAMGIA\pham.xuan.lu on 20/07/2015.
 */
public class LoadRemoteImageAsyncTask extends AsyncTask<String, Void, Void> {

    private WeakReference<ImageView> imgViewRef;
    private Context context;
    private String url;
    private Bitmap bitmap;

    public LoadRemoteImageAsyncTask(Context context, ImageView img) {
        this.context = context;
        this.imgViewRef = new WeakReference<ImageView>(img);
    }

    @Override
    protected Void doInBackground(String... params) {
        url = params[0];
        if (BitmapLruCache.getInstance().get(url) != null) {
            bitmap = BitmapLruCache.getInstance().get(url);
            return null;
        }
        if (isCancelled()) {
            return null;
        }
        try {
            InputStream in = new URL(url).openStream();
            bitmap = BitmapFactory.decodeStream(in);
            BitmapLruCache.getInstance().put(url, bitmap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (isCancelled())
            return;
        if (imgViewRef.get() != null) {
            if (bitmap != null) {
                imgViewRef.get().setImageBitmap(bitmap);
            }
        }
    }
}
