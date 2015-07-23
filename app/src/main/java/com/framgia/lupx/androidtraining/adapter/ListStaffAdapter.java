package com.framgia.lupx.androidtraining.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.framgia.lupx.androidtraining.R;
import com.framgia.lupx.androidtraining.models.Employee;

import java.util.List;

/**
 * Created by FRAMGIA\pham.xuan.lu on 22/07/2015.
 */
public class ListStaffAdapter extends RecyclerView.Adapter<ListStaffAdapter.StaffViewHolder> {

    private List<Employee> data;
    private Context context;
    private LayoutInflater inflater;

    public ListStaffAdapter(Context context, List<Employee> data) {
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public void onBindViewHolder(StaffViewHolder holder, int position) {
        Employee em = this.data.get(position);
        holder.txtFullName.setText(em.fullName);
        holder.txtPhone.setText("Phone : " + em.phone);
        holder.txtEmail.setText("Email : " + em.email);
    }

    @Override
    public int getItemCount() {
        return this.data == null ? 0 : this.data.size();
    }

    @Override
    public StaffViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_staff_row, parent, false);
        StaffViewHolder holder = new StaffViewHolder(view);
        return holder;
    }

    public static class StaffViewHolder extends RecyclerView.ViewHolder {
        public TextView txtFullName;
        public TextView txtPhone;
        public TextView txtEmail;


        public StaffViewHolder(View view) {
            super(view);

            txtFullName = (TextView) view.findViewById(R.id.txtFullName);
            txtEmail = (TextView) view.findViewById(R.id.txtEmail);
            txtPhone = (TextView) view.findViewById(R.id.txtPhone);
        }
    }
}
