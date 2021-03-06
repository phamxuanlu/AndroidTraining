package com.framgia.lupx.androidtraining.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.lupx.androidtraining.R;
import com.framgia.lupx.androidtraining.models.NavDrawerItem;

import java.util.List;

/**
 * Created by FRAMGIA\pham.xuan.lu on 20/07/2015.
 */
public class NavDrawerAdapter extends RecyclerView.Adapter<NavDrawerAdapter.NavViewHolder> {

    private RecyclerViewItemClickListener itemClickListener;

    private List<NavDrawerItem> data;
    private LayoutInflater inflater;
    private Context context;

    public NavDrawerAdapter(Context context, List<NavDrawerItem> data, RecyclerViewItemClickListener itemClickListener) {
        this.data = data;
        this.itemClickListener = itemClickListener;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public NavViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.navigation_drawer_row, viewGroup, false);
        NavViewHolder holder = new NavViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(NavViewHolder viewHolder, final int position) {
        NavDrawerItem item = data.get(position);
        viewHolder.imgIcon.setImageResource(item.iconResource);
        viewHolder.txtName.setText(item.name);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (data != null)
            return data.size();
        return 0;
    }

    static class NavViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgIcon;
        public TextView txtName;

        public NavViewHolder(View view) {
            super(view);
            imgIcon = (ImageView) view.findViewById(R.id.imgIcon);
            txtName = (TextView) view.findViewById(R.id.txtName);
        }
    }
}
