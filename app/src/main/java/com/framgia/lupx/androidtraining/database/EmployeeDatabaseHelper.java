package com.framgia.lupx.androidtraining.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.framgia.lupx.androidtraining.models.Employee;

import java.util.ArrayList;

/**
 * Created by FRAMGIA\pham.xuan.lu on 22/07/2015.
 */
public class EmployeeDatabaseHelper extends SQLiteOpenHelper {
    public final static String TABLE_NAME = "employee";


    public EmployeeDatabaseHelper(Context context, String name) {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                "id integer primary key, " +
                "fullname text, " +
                "phone text, " +
                "email text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long insert(Employee em) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("fullname", em.fullName);
        cv.put("phone", em.phone);
        cv.put("email", em.email);
        return db.insert(TABLE_NAME, null, cv);
    }

    public Employee getData(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE id=" + id, null);
        if (cursor != null) {
            Employee em = new Employee();
            em.id = cursor.getInt(cursor.getColumnIndex("id"));
            em.fullName = cursor.getString(cursor.getColumnIndex("fullname"));
            em.phone = cursor.getString(cursor.getColumnIndex("phone"));
            em.email = cursor.getString(cursor.getColumnIndex("email"));
            return em;
        } else {
            return null;
        }
    }

    public Integer delete(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, " id = ? ", new String[]{Integer.toString(id)});
    }


    public ArrayList<Employee> getAllEmployee() {
        ArrayList<Employee> lst = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            Employee em = new Employee();
            em.id = cursor.getInt(cursor.getColumnIndex("id"));
            em.fullName = cursor.getString(cursor.getColumnIndex("fullname"));
            em.phone = cursor.getString(cursor.getColumnIndex("phone"));
            em.email = cursor.getString(cursor.getColumnIndex("email"));
            lst.add(em);
            cursor.moveToNext();
        }
        return lst;
    }
}
