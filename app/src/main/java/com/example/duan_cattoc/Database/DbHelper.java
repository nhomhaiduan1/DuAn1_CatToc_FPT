package com.example.duan_cattoc.Database;

import android.content.Context;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
public static final String DB_NAME ="CATTOC_FPT";
public static final int DB_VERSION = 4;


//
//    //Dịch vụ
//    private static final String TABLE_CATTOC = "CATTOC"; //Bảng
//    private static final String KEY_ID = "Id"; // ID
//    private static final String KEY_CATTOC_NAME = "CATTOCName"; //Tên Dịch vụ Cắt Tóc
//    private static final String KEY_CATTOC_AMOUNT = "DrinkAmount"; // Số lượng
//    private static final String KEY_CATTOC_PRICE = "DrinkPrice"; //Giá
//
//
//    //Chỗ Cắt Tóc
//    private static final String TABLE_BOOK = "BOOK"; //book phòng
//    private static final String KEY_CATTOC_LIST = "CATTOCList"; //Danh Sách Dịch Vụ
//
//    //thanh toán
//    private static final String TABLE_BILL = "BILL"; //Bill thanh Toán
//    private static final String KEY_TOTAL_MONEY = "TotalMoney"; //tiền
//    private static final String KEY_DATE_BILL = "DateBill"; //Ngày hóa Đơn

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
//
//        String createTableStudent = String.format("CREATE TABLE %s" +
//                        "(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s INTEGER, %s DOUBLE)",
//                TABLE_CATTOC, KEY_ID, KEY_CATTOC_NAME, KEY_CATTOC_AMOUNT,KEY_CATTOC_PRICE);
//        db.execSQL(createTableStudent);
//
//        String createTableDepartment = String.format("CREATE TABLE %s" +
//                        "(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT)",
//                TABLE_BOOK, KEY_ID, KEY_CATTOC_LIST);
//        db.execSQL(createTableDepartment);
//
//        String createTableCategoryRoom = String.format("CREATE TABLE %s" +
//                        "(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s DOUBLE,%s TEXT)",
//                TABLE_BILL, KEY_ID, KEY_CATTOC_LIST, KEY_TOTAL_MONEY, KEY_DATE_BILL);
//        db.execSQL(createTableCategoryRoom);





//Data Mẫu
        db.execSQL("INSERT INTO NhanVien VALUES('admin','Admin','admin')," +
                "('NguyenVanM','Bùi Công Minh','1')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if (i != i1) {
            db.execSQL("drop table if exists NhanVien");

            onCreate(db);
        }
    }
}
