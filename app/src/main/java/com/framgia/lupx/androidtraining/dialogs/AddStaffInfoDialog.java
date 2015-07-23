package com.framgia.lupx.androidtraining.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.framgia.lupx.androidtraining.R;
import com.framgia.lupx.androidtraining.models.Employee;

/**
 * Created by FRAMGIA\pham.xuan.lu on 22/07/2015.
 */
public class AddStaffInfoDialog extends Dialog {

    public interface InputDoneListener {
        void onInputDone(Employee employee);
    }

    private EditText edtFullName;
    private EditText edtPhone;
    private EditText edtEmail;
    private Button btnCancel;
    private Button btnAdd;
    private InputDoneListener listener;

    public AddStaffInfoDialog(Context context, InputDoneListener listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_staff);
        edtFullName = (EditText) findViewById(R.id.edtFullName);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(onClickListener);
        btnCancel.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnCancel:
                    dismiss();
                    break;
                case R.id.btnAdd:
                    Employee em = new Employee();
                    em.fullName = edtFullName.getText().toString();
                    em.phone = edtPhone.getText().toString();
                    em.email = edtEmail.getText().toString();
                    if (listener != null) {
                        listener.onInputDone(em);

                    }
                    dismiss();
                    break;
            }
        }
    };
}
