package com.example.duan_cattoc.Database;

import android.content.Context;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
public static final String DB_NAME ="CATTOC_FPT";
public static final int DB_VERSION = 3;

    public DbHelper (@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableNhanVien = "create table NhanVien(" +
                "maNV TEXT PRIMARY KEY, " +
                "hoTen TEXT NOT NULL, " +
                "matKhau TEXT NOT NULL)";
        db.execSQL(createTableNhanVien);
        // bảng khác







//Data Mẫu
        db.execSQL("INSERT INTO NhanVien VALUES('admin','Admin','admin')," +
                "('minhbc','Bùi Công Minh','1')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if (i != i1) {
            db.execSQL("drop table if exists NhanVien");

            onCreate(db);
        }
    }
}
