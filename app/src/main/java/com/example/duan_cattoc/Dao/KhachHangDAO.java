package com.example.duan_cattoc.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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

    public long insert(KhachHang obj) {
        ContentValues values = new ContentValues();
        values.put("hoTen", obj.getHoTen());
        values.put("namSinh", obj.getNamSinh());
        values.put("SDT", obj.getSDT());
        return db.insert("KhachHang", null, values);
    }

    public long update(KhachHang obj) {
        ContentValues values = new ContentValues();
        values.put("hoTen", obj.getHoTen());
        values.put("namSinh", obj.getNamSinh());
        values.put("SDT", obj.getSDT());
        return db.update("KhachHang", values, "maKH = ?", new String[]{String.valueOf(obj.getMaKH())});
    }

    public long delete(String id) {
        return db.delete("KhachHang", "maKH = ?", new String[]{String.valueOf(id)});
    }

    public List<KhachHang> getAll() {
        String sql = "SELECT * FROM KhachHang";
        return getData(sql);
    }

    public KhachHang getID(String id) {
        String sql = "SELECT * FROM KhachHang WHERE maKH=?";
        List<KhachHang> list = getData(sql, id);
        return list.get(0);
    }

    @SuppressLint("Range")
    private List<KhachHang> getData(String sql, String... selectionArgs) {
        List<KhachHang> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            KhachHang obj = new KhachHang();
            obj.setMaKH(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maKH"))));
            obj.setHoTen(cursor.getString(cursor.getColumnIndex("hoTen")));
            obj.setNamSinh(cursor.getString(cursor.getColumnIndex("namSinh")));
            obj.setSDT(cursor.getString(cursor.getColumnIndex("SDT")));
            Log.i("//==", obj.toString());
            list.add(obj);
        }
        return list;
    }


}
