package com.example.duan_cattoc.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan_cattoc.Database.DbHelper;
import com.example.duan_cattoc.model.KhachHang;

import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO {
    private SQLiteDatabase db;

    public KhachHangDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(KhachHang kh) {
        ContentValues values = new ContentValues();
        values.put("tendangnhap", kh.getTenDangNhap());
        values.put("matkhau", kh.getMatKhau());
        values.put("hoten", kh.getHoTen());

        return db.insert("khachhang", null, values);
    }

    public int checkLogin(String tendangnhap, String matkhau) {
        String sql = "select * from khachhang where tendangnhap=? and matkhau=?";
        List<KhachHang> list = getData(sql, tendangnhap, matkhau);
        if (list.size() == 0) {
            return -1;
        } else {
            return 1;
        }
    }

    @SuppressLint("Range")
    public List<KhachHang> getData(String sql, String... selectinArgs) {
        List<KhachHang> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectinArgs);
        while (cursor.moveToNext()) {
            KhachHang khachHang = new KhachHang();
            khachHang.setTenDangNhap(cursor.getString(cursor.getColumnIndex("tendangnhap")));
            khachHang.setMatKhau(cursor.getString(cursor.getColumnIndex("matkhau")));
            khachHang.setHoTen(cursor.getString(cursor.getColumnIndex("hoten")));

            list.add(khachHang);
        }
        return list;
    }

    public List<KhachHang> getAll() {
        String sql = "select * from khachhang";
        return getData(sql);
    }
}
