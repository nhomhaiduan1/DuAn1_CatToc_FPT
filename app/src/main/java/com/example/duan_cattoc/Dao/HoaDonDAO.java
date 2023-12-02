package com.example.duan_cattoc.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.duan_cattoc.Database.DbHelper;
import com.example.duan_cattoc.model.DichVu;
import com.example.duan_cattoc.model.HoaDon;
import com.example.duan_cattoc.model.Top;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {
    private static SQLiteDatabase db;
    private static Context context;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public HoaDonDAO(Context context) {
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(HoaDon obj) {
        ContentValues values = new ContentValues();
        values.put("maNV", obj.getMaNV());
        values.put("maKH", obj.getMaKH());
        values.put("maDichVu", obj.getMaDichVu());
        values.put("ngay", sdf.format(obj.getNgay()));
        values.put("gia", obj.getTienDichVu());
        values.put("thanhToan", obj.getThanhToan());
        return db.insert("HoaDon", null, values);
    }

    public long update(HoaDon obj) {
        ContentValues values = new ContentValues();
        values.put("maNV", obj.getMaNV());
        values.put("maKH", obj.getMaKH());
        values.put("maDichVu", obj.getMaDichVu());
        values.put("ngay", sdf.format(obj.getNgay()));
        values.put("gia", obj.getTienDichVu());
        values.put("thanhToan", obj.getThanhToan());
        return db.update("HoaDon", values, "maHD = ?", new String[]{String.valueOf(obj.getMaHD())});
    }

    public long delete(String id) {
        return db.delete("HoaDon", "maHD = ?", new String[]{String.valueOf(id)});
    }

    public List<HoaDon> getAll() {
        String sql = "SELECT * FROM HoaDon";
        return getData(sql);
    }

    public HoaDon getID(String id) {
        String sql = "SELECT * FROM HoaDon WHERE maHD=?";
        List<HoaDon> list = getData(sql, id);
        return list.get(0);
    }

    @SuppressLint("Range")
    private List<HoaDon> getData(String sql, String... selectionArgs) {
        List<HoaDon> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            HoaDon obj = new HoaDon();
            obj.setMaHD(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maHD"))));
            obj.setMaNV(cursor.getString(cursor.getColumnIndex("maNV")));
            obj.setMaKH(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maKH"))));
            obj.setMaDichVu(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maDichVu"))));
            obj.setTienDichVu(Integer.parseInt(cursor.getString(cursor.getColumnIndex("gia"))));
            try {
                obj.setNgay(sdf.parse(cursor.getString(cursor.getColumnIndex("ngay"))));
//                Log.d("jjfjn", "getData: "+sdf.parse(cursor.getString(cursor.getColumnIndex("ngay"))));
            } catch (ParseException e) {
                e.printStackTrace();
//                Log.i("akhjj","123");
            }
            obj.setThanhToan(Integer.parseInt(cursor.getString(cursor.getColumnIndex("thanhToan"))));
            list.add(obj);
        }
        return list;
    }


//     thống kê top 10
    @SuppressLint("Range")
    public static List<Top> getTop() {
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



//     thống kê doanh thu
    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay) {
        String sqlDoanhThu = "SELECT SUM(gia) as doanhThu FROM HoaDon WHERE ngay BETWEEN ? AND ?";
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
