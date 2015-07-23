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
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.Toast;

import com.framgia.lupx.androidtraining.R;
import com.framgia.lupx.androidtraining.adapter.ListStaffAdapter;
import com.framgia.lupx.androidtraining.database.DatabaseHelper;
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
    private DatabaseHelper<Employee> db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_database, container, false);
        listStaff = (RecyclerView) view.findViewById(R.id.listStaff);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        listStaff.setLayoutManager(layoutManager);
        try {
            db = new DatabaseHelper<>(getActivity(), "EMPLOYEE.db", Employee.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        lst = db.getAllRows();
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

        //FAB Animation
        listStaff.addOnScrollListener(new MyRecyclerViewScroll() {
            @Override
            public void show() {
                floatAddButton.animate().translationY(0)
                        .setInterpolator(new DecelerateInterpolator(2))
                        .start();
            }

            @Override
            public void hide() {
                floatAddButton.animate().translationY(floatAddButton.getHeight() + 24)
                        .setInterpolator(new AccelerateInterpolator(2))
                        .start();
            }
        });

        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.simple_grow);
        floatAddButton.startAnimation(animation);

        return view;
    }

    @Override
    public void onPause() {
        int s = lst.size();
        for (int i = 0; i < s; i++) {
            if (lst.get(i).id == -1) {
                long id = db.insert(lst.get(i));
                lst.get(i).id = (int) id;
            }
        }
        super.onPause();
    }

    public abstract class MyRecyclerViewScroll extends RecyclerView.OnScrollListener {
        private static final float HIDE_THRESHOLD = 100;
        private static final float SHOW_THRESHOLD = 50;

        int scrollDist = 0;
        private boolean isVisible = true;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            if (isVisible && scrollDist > HIDE_THRESHOLD) {
                hide();
                scrollDist = 0;
                isVisible = false;
            } else if (!isVisible && scrollDist < -SHOW_THRESHOLD) {
                show();
                scrollDist = 0;
                isVisible = true;
            }
            if ((isVisible && dy > 0) || (!isVisible && dy < 0)) {
                scrollDist += dy;
            }
        }

        public abstract void show();

        public abstract void hide();
    }
}
