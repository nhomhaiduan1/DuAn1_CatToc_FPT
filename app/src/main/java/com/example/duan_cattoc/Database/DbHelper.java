package com.example.duan_cattoc.Database;

import android.content.Context;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
public static final String DB_NAME ="CATTOC_FPT";
public static final int DB_VERSION = 18;


    public DbHelper (@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//Tạo bảng nhân viên
        String createTableNhanVien = "create table NhanVien(" +
                "maNV TEXT PRIMARY KEY, " +
                "matKhau TEXT NOT NULL, " +
                "hoTen TEXT NOT NULL, " +
                "diaChi TEXT NOT NULL, " +
                "sDT TEXT NOT NULL)";
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
        String createTableHoaDon = "create table HoaDon(" +
                "maHD INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "maNV TEXT REFERENCES NhanVien(maNV), " +
                "maKH INTEGER REFERENCES KhachHang(maKH), " +
                "maDichVu INTEGER REFERENCES DichVu(maDichVu), " +
                "gia INTEGER NOT NULL, " +
                "ngay DATE NOT NULL, " +
                "thanhToan INTEGER NOT NULL)";
        db.execSQL(createTableHoaDon);
//Data Mẫu
        db.execSQL("INSERT INTO NhanVien VALUES('admin','admin','Admin','Hà Nội','0355888999')," +
                "('NguyenVanM','1','Tran Văn Sơn','Hải Phòng','02364728123')");
        db.execSQL("INSERT INTO KhachHang VALUES(1,'Phùng Thanh Độ','2004', '0335888092')," +
                "(2,'Nguyễn Thị Trân','2001', '03283234111')," +
                "(3,'Bùi Đăng Khoa','1995', '03283231232')," +
                "(4,'Lê Minh Khôi','1996', '03283234222')," +
                "(5,'Mạc Văn Khoa','2000', '03283234333')," +
                "(6,'Nguyễn Bảo Long','2005', '03283234444')," +
                "(7,'Lê Thị Khánh Huyền','2006', '03283234888')");
        db.execSQL("INSERT INTO LoaiDichVu VALUES(1,'Cắt Tóc'),(2,'Nhuộm Tóc'),(3,'Uốn Tóc'),(4,'Gội Đầu'),(5,'Chăm Sóc Da')");
        db.execSQL("INSERT INTO DichVu VALUES(1,'Cắt Layer',40,'2'),(2,'Cắt 3 phân',30,'1'),(3,'Cắt Sport',40,'3')," +
                "(4,'Nhuộm Trà Sữa',200,'4'),(5,'Nhuộm Đỏ',150,'5'),(6,'Uốn Sóng',150,'6')," +
                "(7,'Uốn Sâu',40,'7'),(8,'Gội Chuyên Sâu',100,'8')");
        db.execSQL("INSERT INTO HoaDon VALUES(1,'admin','1','1','30','2023/12/2','1')," +
                "(2,'NguyenVanM','2','2','20','2023/12/2','1')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if (i != i1) {
            db.execSQL("drop table if exists NhanVien");
            db.execSQL("drop table if exists LoaiDichVu");
            db.execSQL("drop table if exists DichVu");
            db.execSQL("drop table if exists KhachHang");
            db.execSQL("drop table if exists HoaDon");
            onCreate(db);
        }
    }
}
