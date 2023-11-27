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
import com.example.duan_cattoc.fragment_LoaiDichVu;
import com.example.duan_cattoc.model.LoaiDichVu;

import java.util.ArrayList;

public class LoaiDichVuAdapter extends ArrayAdapter<LoaiDichVu> {
    private Context context;
    fragment_LoaiDichVu fragment;
    private ArrayList<LoaiDichVu> list;
    TextView tvMaLoai, tvTenLoai;
    ImageView imgDel;
    public LoaiDichVuAdapter(@NonNull Context context, fragment_LoaiDichVu fragment, ArrayList<LoaiDichVu> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
        this.fragment = fragment;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_loai_dich_vu, null);
        }
        final LoaiDichVu item = list.get(position);
        if (item != null) {
            tvMaLoai = v.findViewById(R.id.tvMaLoaiDichVu);
            tvMaLoai.setText("Mã Loại: " + item.getMaLoai());
            tvTenLoai = v.findViewById(R.id.tvTenLoaiDichVu);
            tvTenLoai.setText("Tên Loại: " + item.getTenLoai());

            imgDel = v.findViewById(R.id.imgDeleteLS);
        }

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // gọi phương thức xóa
                fragment.xoa(String.valueOf(item.getMaLoai()));
            }
        });
        return v;
    }
}
