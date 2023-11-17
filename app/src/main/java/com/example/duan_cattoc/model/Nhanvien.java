package com.example.duan_cattoc.model;

public class Nhanvien {
    private String maNV;
    private String hoTen;
    private String matKhau;

    public Nhanvien(String maNV, String hoTen, String matKhau) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.matKhau = matKhau;
    }

    public Nhanvien() {
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
