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
import com.example.duan_cattoc.model.DichVu;


import java.util.ArrayList;

public class DichVuSpinnerAdapter extends ArrayAdapter<DichVu> {
    private Context context;
    private ArrayList<DichVu> list;

    TextView tvMaDichVu, tvTenDichVu;

    public DichVuSpinnerAdapter(@NonNull Context context, ArrayList<DichVu> list) {
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
            v = inflater.inflate(R.layout.item_dich_vu_spinner, null);
        }
        final DichVu item = list.get(position);
        if (item != null) {
            tvMaDichVu = v.findViewById(R.id.tvMaDichVuSp);
            tvMaDichVu.setText(item.getMaDichVu() + ". ");
            tvTenDichVu = v.findViewById(R.id.tvTenDichVuSp);
            tvTenDichVu.setText(item.getTenDichVu());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_dich_vu_spinner, null);
        }
        final DichVu item = list.get(position);
        if (item != null) {
            tvMaDichVu = v.findViewById(R.id.tvMaDichVuSp);
            tvMaDichVu.setText(item.getMaDichVu() + ". ");
            tvTenDichVu = v.findViewById(R.id.tvTenDichVuSp);
            tvTenDichVu.setText(item.getTenDichVu());
        }
        return v;
    }
}
