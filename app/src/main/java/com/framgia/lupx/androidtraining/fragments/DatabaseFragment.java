package com.framgia.lupx.androidtraining.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.Toast;

import com.framgia.lupx.androidtraining.R;
import com.framgia.lupx.androidtraining.adapter.ListStaffAdapter;
import com.framgia.lupx.androidtraining.database.EmployeeDatabaseHelper;
import com.framgia.lupx.androidtraining.dialogs.AddStaffInfoDialog;
import com.framgia.lupx.androidtraining.models.Employee;

import java.util.List;

/**
 * Created by FRAMGIA\pham.xuan.lu on 22/07/2015.
 */
public class DatabaseFragment extends Fragment {

    private List<Employee> lst;
    private RecyclerView listStaff;
    private ListStaffAdapter adapter;
    private FloatingActionButton floatAddButton;
    private EmployeeDatabaseHelper db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_database, container, false);
        listStaff = (RecyclerView) view.findViewById(R.id.listStaff);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        listStaff.setLayoutManager(layoutManager);
        db = new EmployeeDatabaseHelper(getActivity(), "EMPLOYEE.db");
        lst = db.getAllEmployee();
        adapter = new ListStaffAdapter(getActivity(), lst);
        listStaff.setAdapter(adapter);
        floatAddButton = (FloatingActionButton) view.findViewById(R.id.fab);
        floatAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddStaffInfoDialog dialog = new AddStaffInfoDialog(getActivity(), new AddStaffInfoDialog.InputDoneListener() {
                    @Override
                    public void onInputDone(Employee employee) {
                        lst.add(employee);
                        adapter.notifyDataSetChanged();
                    }
                });
                dialog.show();
            }
        });
        return view;
    }

    @Override
    public void onPause() {
        int s = lst.size();
        for (int i = 0; i < s; i++) {
            if (lst.get(i).id == -1) {
                db.insert(lst.get(i));
            }
        }
        super.onPause();
    }
}
