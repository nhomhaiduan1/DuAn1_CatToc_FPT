package com.example.duan_cattoc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan_cattoc.R;
import com.example.duan_cattoc.model.KhachHang;


import java.util.ArrayList;

public class KhachHangSpinnerAdapter extends ArrayAdapter<KhachHang> {
    private Context context;
    private ArrayList<KhachHang> list;

    TextView tvMaKH, tvTenKH;

    public KhachHangSpinnerAdapter(@NonNull Context context, ArrayList<KhachHang> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_khach_hang_spinner, null);
        }
        final KhachHang item = list.get(position);
        if (item != null) {
            tvMaKH = v.findViewById(R.id.tvMaKHsp);
            tvMaKH.setText(item.getMaKH() + ". ");
            tvTenKH = v.findViewById(R.id.tvTenKHSp);
            tvTenKH.setText(item.getHoTen());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_khach_hang_spinner, null);
        }
        final KhachHang item = list.get(position);
        if (item != null) {
            tvMaKH = v.findViewById(R.id.tvMaKHsp);
            tvMaKH.setText(item.getMaKH() + ". ");
            tvTenKH = v.findViewById(R.id.tvTenKHSp);
            tvTenKH.setText(item.getHoTen());
        }
        return v;
    }
}
