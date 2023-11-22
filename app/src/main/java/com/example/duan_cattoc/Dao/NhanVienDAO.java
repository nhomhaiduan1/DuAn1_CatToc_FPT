package com.example.duan_cattoc.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan_cattoc.Database.DbHelper;
import com.example.duan_cattoc.model.Nhanvien;

import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {
    private SQLiteDatabase db;

    public NhanVienDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(Nhanvien obj) {
        ContentValues values = new ContentValues();
        values.put("maNV", obj.getMaNV());
        values.put("hoTen", obj.getHoTen());
        values.put("matKhau", obj.getMatKhau());
        return db.insert("NhanVien", null, values);
    }

    public long updatePass(Nhanvien obj) {
        ContentValues values = new ContentValues();
        values.put("hoTen", obj.getHoTen());
        values.put("matKhau", obj.getMatKhau());
        return db.update("NhanVien", values, "maNV = ?", new String[]{String.valueOf(obj.getMaNV())});
    }

    public long delete(String id) {
        return db.delete("NhanVien", "maNV = ?", new String[]{String.valueOf(id)});
    }

    public List<Nhanvien> getAll() {
        String sql = "SELECT * FROM NhanVien";
        return getData(sql);
    }

    public Nhanvien getID(String id) {
        String sql = "SELECT * FROM NhanVien WHERE maNV=?";
        List<Nhanvien> list = getData(sql, id);
        return list.get(0);
    }

    // check login
    public int checkLogin(String id, String password) {
        String sql = "SELECT * FROM NhanVien WHERE maNV=? AND matKhau=?";
        List<Nhanvien> list = getData(sql, id, password);
        if (list.size() == 0) {
            return -1;
        }
        return 1;
    }

    @SuppressLint("Range")
    private List<Nhanvien> getData(String sql, String... selectionArgs) {
        List<Nhanvien> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            Nhanvien obj = new Nhanvien();
            obj.setMaNV(cursor.getString(cursor.getColumnIndex("maNV")));
            obj.setHoTen(cursor.getString(cursor.getColumnIndex("hoTen")));
            obj.setMatKhau(cursor.getString(cursor.getColumnIndex("matKhau")));
            list.add(obj);
        }
        return list;
    }


}
