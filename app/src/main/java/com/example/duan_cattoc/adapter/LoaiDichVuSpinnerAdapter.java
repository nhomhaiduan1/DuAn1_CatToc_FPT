package com.example.duan_cattoc.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.duan_cattoc.R;
import com.example.duan_cattoc.model.LoaiDichVu;

import java.util.ArrayList;

public class LoaiDichVuSpinnerAdapter extends ArrayAdapter<LoaiDichVu> {

    private Context context;
    ArrayList<LoaiDichVu> list;
    TextView tvMaLoaiDichVu, tvTenLoaiDichVu;

    public LoaiDichVuSpinnerAdapter(@NonNull Context context, ArrayList<LoaiDichVu> list) {
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
            v = inflater.inflate(R.layout.item_loai_dich_vu_spinner_adapter, null);

        }
        final LoaiDichVu item = list.get(position);
        if (item != null) {
            tvMaLoaiDichVu = v.findViewById(R.id.tvMaLoaiDichVuSp);
            tvMaLoaiDichVu.setText(item.getMaLoai() + ". ");

            tvTenLoaiDichVu = v.findViewById(R.id.tvTenLoaiDichVuSp);
            tvTenLoaiDichVu.setText(item.getTenLoai());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_loai_dich_vu_spinner_adapter, null);

        }
        final LoaiDichVu item = list.get(position);
        if (item != null) {
            tvMaLoaiDichVu = v.findViewById(R.id.tvMaLoaiDichVuSp);
            tvMaLoaiDichVu.setText(item.getMaLoai() + ". ");

            tvTenLoaiDichVu = v.findViewById(R.id.tvTenLoaiDichVuSp);
            tvTenLoaiDichVu.setText(item.getTenLoai());
        }
        return v;
    }
}