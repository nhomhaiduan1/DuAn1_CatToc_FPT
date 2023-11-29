package com.example.duan_cattoc.model;

public class DichVu {
    private int maDichVu;
    private String TenDichVu;
    private int giaDichVu;
    private int maLoai;

    public DichVu() {
    }

    public int getMaDichVu() {
        return maDichVu;
    }

    public void setMaDichVu(int maDichVu) {
        this.maDichVu = maDichVu;
    }

    public String getTenDichVu() {
        return TenDichVu;
    }

    public void setTenDichVu(String tenDichVu) {
        TenDichVu = tenDichVu;
    }

    public int getGiaDichVu() {
        return giaDichVu;
    }

    public void setGiaDichVu(int giaDichVu) {
        this.giaDichVu = giaDichVu;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public DichVu(int maDichVu, String tenDichVu, int giaDichVu, int maLoai) {
        this.maDichVu = maDichVu;
        TenDichVu = tenDichVu;
        this.giaDichVu = giaDichVu;
        this.maLoai = maLoai;
    }
}
