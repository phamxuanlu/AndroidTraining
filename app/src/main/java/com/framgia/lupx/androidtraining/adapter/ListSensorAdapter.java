package com.framgia.lupx.androidtraining.adapter;

import android.content.Context;
import android.hardware.Sensor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.framgia.lupx.androidtraining.R;

import java.util.List;

/**
 * Created by FRAMGIA\pham.xuan.lu on 22/07/2015.
 */
public class ListSensorAdapter extends RecyclerView.Adapter<ListSensorAdapter.SensorViewHolder> {

    private List<Sensor> data;
    private Context context;
    private LayoutInflater inflater;

    public ListSensorAdapter(Context context, List<Sensor> data) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.data = data;
    }

    @Override
    public SensorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_sensor_row, parent, false);
        SensorViewHolder holder = new SensorViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SensorViewHolder holder, int position) {
        Sensor sensor = data.get(position);
        holder.txtSensorName.setText(sensor.getName());
        holder.txtVendor.setText("Vendor : " + String.valueOf(sensor.getVendor()));
        holder.txtVersion.setText("Version : " + String.valueOf(sensor.getVersion()));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class SensorViewHolder extends RecyclerView.ViewHolder {

        public TextView txtSensorName;
        public TextView txtVendor;
        public TextView txtVersion;

        public SensorViewHolder(View view) {
            super(view);
            txtSensorName = (TextView) view.findViewById(R.id.txtSensorName);
            txtVendor = (TextView) view.findViewById(R.id.txtVendor);
            txtVersion = (TextView) view.findViewById(R.id.txtVersion);
        }
    }
}
