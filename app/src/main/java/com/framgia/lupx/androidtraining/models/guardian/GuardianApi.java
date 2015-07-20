package com.framgia.lupx.androidtraining.models.guardian;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FRAMGIA\pham.xuan.lu on 20/07/2015.
 */
public class GuardianApi {
    public static final String URL = "http://content.guardianapis.com/search?q=android&api-key=reparykpp38aw29a28jbr4t9&show-fields=thumbnail";

    public static List<Article> getListArticles(String json) {
        List<Article> lst = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject response = jsonObject.getJSONObject("response");
            JSONArray articles = response.getJSONArray("results");

            Gson gson = new Gson();
            lst = gson.fromJson(articles.toString(), new TypeToken<List<Article>>() {
            }.getType());

        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return lst;
    }
}
