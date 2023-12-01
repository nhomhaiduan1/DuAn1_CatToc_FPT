package com.example.duan_cattoc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan_cattoc.R;
import com.example.duan_cattoc.fragment_QuanLyKhachHang;
import com.example.duan_cattoc.model.KhachHang;


import java.util.ArrayList;

public class KhachHangAdapter extends ArrayAdapter<KhachHang> {
    private Context context;
    fragment_QuanLyKhachHang fragment;
    private ArrayList<KhachHang> list;
    TextView tvMaKH, tvTenKH, tvNamSinh, tvSDT;
    ImageView imgDel;

    public KhachHangAdapter(@NonNull Context context, fragment_QuanLyKhachHang fragment, ArrayList<KhachHang> list) {
        super(context, 0, list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_khach_hang, null);
        }
        final KhachHang item = list.get(position);
        if (item != null) {
            tvMaKH = v.findViewById(R.id.tvMaKH);
            tvMaKH.setText("Mã Khách Hàng: " + item.getMaKH());
            tvTenKH = v.findViewById(R.id.tvTenKH);
            tvTenKH.setText("Tên Khách Hàng: " + item.getHoTen());
            tvNamSinh = v.findViewById(R.id.tvNamSinh);
            tvNamSinh.setText("Năm sinh: " + item.getNamSinh());

            tvSDT = v.findViewById(R.id.tvSDT);
            tvSDT.setText("SDT: " + item.getSDT());

            imgDel = v.findViewById(R.id.imgDeleteLS);
        }

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // gọi phương thức xóa
                fragment.xoa(String.valueOf(item.getMaKH()));

            }
        });
        return v;
    }
}
