package com.example.duan_cattoc.model;

public class KhachHang {
    private int maKH;
    private String hoTen;
    private String namSinh;

    private String SDT;

    public KhachHang(int maKH, String hoTen, String namSinh, String SDT) {
        this.maKH = maKH;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
        this.SDT = SDT;
    }

    public KhachHang() {
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }
}
