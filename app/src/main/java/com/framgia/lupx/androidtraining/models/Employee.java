package com.framgia.lupx.androidtraining.models;

import com.framgia.lupx.androidtraining.database.DBField;
import com.framgia.lupx.androidtraining.database.DBKey;
import com.framgia.lupx.androidtraining.database.DBTable;

/**
 * Created by FRAMGIA\pham.xuan.lu on 22/07/2015.
 */
@DBTable(tableName = "employee")
public class Employee {

    public Employee() {
        id = -1;
    }

    @DBField(fieldName = "id", type = "integer")
    @DBKey()
    public int id;

    @DBField(fieldName = "fullname", type = "text")
    public String fullName;

    @DBField(fieldName = "phone", type = "text")
    public String phone;

    @DBField(fieldName = "email", type = "text")
    public String email;

    @DBField(fieldName = "time",type = "integer")
    public long time;

    @DBField(fieldName = "floatfield",type = "real")
    public float floatType;

    @DBField(fieldName = "doubleType",type = "real")
    public double doubleType;
}
