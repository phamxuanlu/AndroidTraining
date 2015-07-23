package com.framgia.lupx.androidtraining.fragments;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.lupx.androidtraining.R;
import com.framgia.lupx.androidtraining.adapter.ListSensorAdapter;

import java.util.List;

/**
 * Created by FRAMGIA\pham.xuan.lu on 22/07/2015.
 */
public class SensorFragment extends Fragment {

    private RecyclerView listSensor;
    private ListSensorAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sensor, container, false);
        listSensor = (RecyclerView) view.findViewById(R.id.listSensors);
        SensorManager sm = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ALL);
        adapter = new ListSensorAdapter(getActivity(), sensors);
        listSensor.setLayoutManager(new LinearLayoutManager(getActivity()));
        listSensor.setAdapter(adapter);
        return view;
    }
}
