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

import com.example.duan_cattoc.Dao.LoaiDichVuDAO;
import com.example.duan_cattoc.R;
import com.example.duan_cattoc.model.DichVu;
import com.example.duan_cattoc.model.LoaiDichVu;
import com.example.duan_cattoc.fragment_QuanLyDichVu;
import java.util.List;

public class DichVuAdapter extends ArrayAdapter<DichVu> {
    private Context context;
    fragment_QuanLyDichVu fragment;
    List<DichVu> list;

    TextView tvMaDichVu, tvTenDichVu, tvGiaDichVu, tvLoaiDichVu;
    ImageView imgDel;

    public DichVuAdapter(@NonNull Context context, fragment_QuanLyDichVu fragment, List<DichVu> list) {
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
            v = inflater.inflate(R.layout.item_dichvu, null);
        }
        final DichVu item = list.get(position);
        if (item != null) {
            LoaiDichVuDAO loaiSachDAO = new LoaiDichVuDAO(context);
            LoaiDichVu loaiSach = loaiSachDAO.getID(String.valueOf(item.getMaLoai()));
            tvMaDichVu = v.findViewById(R.id.tvMaDichVu);
            tvMaDichVu.setText("Mã Dịch Vụ: " + item.getMaDichVu() );

            tvTenDichVu = v.findViewById(R.id.tvTenDichVu);
            tvTenDichVu.setText("Tên Dịch Vụ: " + item.getTenDichVu());
            tvGiaDichVu = v.findViewById(R.id.tvGiaThue);
            tvGiaDichVu.setText("Giá Dịch Vụ: " + item.getGiaDichVu()+"K");
            tvLoaiDichVu = v.findViewById(R.id.tvLoaiDichVu);
            tvLoaiDichVu.setText("Loại Dịch Vụ: " + loaiSach.getTenLoai());

            imgDel = v.findViewById(R.id.imgDeleteLS);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pt xóa
                fragment.xoa(String.valueOf(item.getMaDichVu()));

            }
        });
        return v;
    }
}
