package com.framgia.lupx.androidtraining.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.framgia.lupx.androidtraining.MyApp;
import com.framgia.lupx.androidtraining.R;
import com.framgia.lupx.androidtraining.activities.NewsDetailActivity;
import com.framgia.lupx.androidtraining.adapter.HomeNewsAdapter;
import com.framgia.lupx.androidtraining.models.guardian.Article;
import com.framgia.lupx.androidtraining.models.guardian.GuardianApi;

import java.util.List;

/**
 * Created by FRAMGIA\pham.xuan.lu on 20/07/2015.
 */
public class HomeArticlesFragment extends Fragment {

    private RecyclerView recyclerView;
    private HomeNewsAdapter adapter;
    private List<Article> articles;
    private View mView;
    private ProgressBar loading;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_home_articles, container, false);
        recyclerView = (RecyclerView) mView.findViewById(R.id.listArticles);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        loading = (ProgressBar) mView.findViewById(R.id.loading);
        getData();
        loading.setVisibility(View.VISIBLE);
        return mView;
    }

    private void getData() {
        StringRequest request = new StringRequest(Request.Method.GET, GuardianApi.URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                articles = GuardianApi.getListArticles(response);
                adapter = new HomeNewsAdapter(getActivity(), articles, new HomeNewsAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Toast.makeText(getActivity(), "Click on item " + position, Toast.LENGTH_SHORT).show();
                    }
                });
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                loading.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MyApp.getInstance(getActivity().getApplicationContext()).addRequest(request);
    }
}
