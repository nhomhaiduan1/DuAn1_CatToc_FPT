package com.example.duan_cattoc.model;

import java.util.Date;

public class HoaDon {
    private int maHD;
    private String maNV;
    private String maTV;
    private int maKH;
    private int maDichVu;
    private Date ngay;
    private int tienDichVu;
    private int ThanhToan;

    public HoaDon(int maHD, String maNV, int maKH, int maDichVu, Date ngay, int tienDichVu, int thanhToan) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.maKH = maKH;
        this.maDichVu = maDichVu;
        this.ngay = ngay;
        this.tienDichVu = tienDichVu;
        ThanhToan = thanhToan;
    }

    public HoaDon() {
    }

    public HoaDon(String maTV) {
        this.maTV = maTV;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public int getMaDichVu() {
        return maDichVu;
    }

    public void setMaDichVu(int maDichVu) {
        this.maDichVu = maDichVu;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public int getTienDichVu() {
        return tienDichVu;
    }

    public void setTienDichVu(int tienDichVu) {
        this.tienDichVu = tienDichVu;
    }

    public int getThanhToan() {
        return ThanhToan;
    }

    public void setThanhToan(int thanhToan) {
        ThanhToan = thanhToan;
    }
}
