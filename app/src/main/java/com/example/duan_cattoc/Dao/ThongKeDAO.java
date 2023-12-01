package com.example.duan_cattoc.Dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.duan_cattoc.Database.DbHelper;
import com.example.duan_cattoc.model.DichVu;
import com.example.duan_cattoc.model.Top;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDAO {
    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public ThongKeDAO(Context context) {
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    // thống kê top 10
    @SuppressLint("Range")
    public List<Top> getTop() {
        String sqlTop = "SELECT maDichVu,count(maDichVu) as soLuong FROM HoaDon GROUP BY maDichVu ORDER BY soLuong DESC LIMIT 3";
        List<Top> list = new ArrayList<Top>();
        DichVuDAO dichVuDAO = new DichVuDAO(context);
        Cursor cursor = db.rawQuery(sqlTop, null);
        while (cursor.moveToNext()) {
            Top top = new Top();
            @SuppressLint("Range") DichVu dichVu = dichVuDAO.getID(cursor.getString(cursor.getColumnIndex("maDichVu")));
            top.setTenDichVu(dichVu.getTenDichVu());
            top.setSoLuong(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soLuong"))));
            list.add(top);

        }
        return list;
    }

    // thống kê doanh thu
    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay) {
        String sqlDoanhThu = "SELECT SUM(tienThue) as doanhThu FROM PhieuMuon WHERE ngay BETWEEN ? AND ?";
        List<Integer> list = new ArrayList<Integer>();
        Cursor cursor = db.rawQuery(sqlDoanhThu, new String[]{tuNgay, denNgay});
        while (cursor.moveToNext()) {
            try {
                list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("doanhThu"))));

            } catch (Exception e) {
                list.add(0);
            }
        }
        return list.get(0);
    }
}
