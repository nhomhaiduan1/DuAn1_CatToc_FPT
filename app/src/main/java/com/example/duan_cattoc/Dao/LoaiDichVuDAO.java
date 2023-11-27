package com.example.duan_cattoc.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan_cattoc.Database.DbHelper;
import com.example.duan_cattoc.model.LoaiDichVu;

import java.util.ArrayList;
import java.util.List;

public class LoaiDichVuDAO {
    private SQLiteDatabase db;

    public LoaiDichVuDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(LoaiDichVu obj) {
        ContentValues values = new ContentValues();
        values.put("tenLoai", obj.getTenLoai());
        return db.insert("LoaiDichVu", null, values);
    }

    public long update(LoaiDichVu obj) {
        ContentValues values = new ContentValues();
        values.put("tenLoai", obj.getTenLoai());
        return db.update("LoaiDichVu", values, "maLoai = ?", new String[]{String.valueOf(obj.getMaLoai())});
    }

    public long delete(String id) {
        return db.delete("LoaiDichVu", "maLoai = ?", new String[]{String.valueOf(id)});
    }

    public List<LoaiDichVu> getAll() {
        String sql = "SELECT * FROM LoaiDichVu";
        return getData(sql);
    }

    public LoaiDichVu getID(String id) {
        String sql = "SELECT * FROM LoaiDichVu WHERE maLoai=?";
        List<LoaiDichVu> list = getData(sql, id);
        return list.get(0);
    }

    @SuppressLint("Range")
    private List<LoaiDichVu> getData(String sql, String... selectionArgs) {
        List<LoaiDichVu> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            LoaiDichVu obj = new LoaiDichVu();
            obj.setMaLoai(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maLoai"))));
            obj.setTenLoai(cursor.getString(cursor.getColumnIndex("tenLoai")));
            list.add(obj);
        }
        return list;
    }

}
