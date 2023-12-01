package com.example.duan_cattoc.Database;

import android.content.Context;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
public static final String DB_NAME ="CATTOC_FPT";
public static final int DB_VERSION = 9;


    public DbHelper (@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//Tạo bảng nhân viên
        String createTableNhanVien = "create table NhanVien(" +
                "maNV TEXT PRIMARY KEY, " +
                "hoTen TEXT NOT NULL, " +
                "matKhau TEXT NOT NULL)";
        db.execSQL(createTableNhanVien);
//Tạo bảng loại dịch vụ
        String createTableLoaiDichVu = "create table LoaiDichVu(" +
                "maLoai INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tenLoai TEXT NOT NULL)";
        db.execSQL(createTableLoaiDichVu);
        // bảng khác
        // tạo bảng Sách
        String createTableDichVu = "create table DichVu(" +
                "maDichVu INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tenDichVu TEXT NOT NULL, " +
                "giaDichVu INTEGER NOT NULL, " +
                "maLoai INTEGER REFERENCES LoaiDichVu(maLoai))";
        db.execSQL(createTableDichVu);
        // tạo bảng Khách hàng
        String createTableKhachHang = "create table KhachHang(" +
                "maKH INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "hoTen TEXT NOT NULL, " +
                "namSinh TEXT NOT NULL, " +
                "SDT TEXT NOT NULL) ";
        db.execSQL(createTableKhachHang);
        // tạo bảng Đơn Hàng
        String createTableDonHang = "create table DonHang(" +
                "maDH INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "maTT TEXT REFERENCES ThuThu(maTT), " +
                "maTV INTEGER REFERENCES ThanhVien(maTV), " +
                "maSach INTEGER REFERENCES Sach(maSach), " +
                "tienThue INTEGER NOT NULL, " +
                "ngay DATE NOT NULL, " +
                "traSach INTEGER NOT NULL)";
        db.execSQL(createTableDonHang);
//Data Mẫu
        db.execSQL("INSERT INTO NhanVien VALUES('admin','Admin','admin')," +
                "('NguyenVanM','Bùi Công Minh','1')");
        db.execSQL("INSERT INTO KhachHang VALUES(1,'Phùng Thanh Độ','2004', '0335888092')," +
                "(2,'Nguyễn Thị Trân','2001', '03283234231')");
        db.execSQL("INSERT INTO LoaiDichVu VALUES(1,'Cắt Tóc'),(2,'Nhuộm Tóc'),(3,'Uốn Tóc')");
        db.execSQL("INSERT INTO DichVu VALUES(1,'Cắt Layer',40000,'2'),(2,'Nhuộm Vàng',15000,'1'),(3,'Uốn Con Sâu',200000,'3')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if (i != i1) {
            db.execSQL("drop table if exists NhanVien");
            db.execSQL("drop table if exists LoaiDichVu");
            db.execSQL("drop table if exists DichVu");
            db.execSQL("drop table if exists KhachHang");
            onCreate(db);
        }
    }
}
