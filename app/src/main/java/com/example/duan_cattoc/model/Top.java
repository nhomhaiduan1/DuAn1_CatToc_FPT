package com.example.duan_cattoc.model;

public class Top {
    private String tenDichVu;
    private int soLuong;

    public Top(String tenDichVu, int soLuong) {
        this.tenDichVu = tenDichVu;
        this.soLuong = soLuong;
    }

    public Top() {
    }

    public String getTenDichVu() {
        return tenDichVu;
    }

    public void setTenDichVu(String tenDichVu) {
        this.tenDichVu = tenDichVu;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
