package com.example.duan_cattoc.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan_cattoc.Dao.NhanVienDAO;
import com.example.duan_cattoc.R;
import com.example.duan_cattoc.fragment_ThemNhanVien;
import com.example.duan_cattoc.model.Nhanvien;

import java.util.ArrayList;
public class NhanVienAdapter extends ArrayAdapter<Nhanvien> {
    private Context context;
    fragment_ThemNhanVien fragment;
    private ArrayList<Nhanvien> list;
    TextView tvMaNV,tvTenNV,tvMatKhau;
    ImageView imgDel;
    NhanVienDAO dao;

    public NhanVienAdapter(@NonNull Context context, fragment_ThemNhanVien fragment, ArrayList<Nhanvien> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
        this.fragment = fragment;
    }

    public NhanVienAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        dao = new NhanVienDAO(getContext());
        if (v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_add_user,null);

        }

        final Nhanvien item = list.get(position);
        if (item != null){
            tvMaNV = v.findViewById(R.id.tvMaNV);
            tvMaNV.setText("Mã NV: "+item.getMaNV());
            tvTenNV= v.findViewById(R.id.tvTenNV);
            tvTenNV.setText("Họ tên: "+item.getHoTen());
            tvMatKhau = v.findViewById(R.id.tvMatKhau);
            tvMatKhau.setText("Mật khẩu: "+item.getMatKhau());


            imgDel = v.findViewById(R.id.imgDeleteLS);
        }

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pt xoa
                fragment.xoa(item.getMaNV());
            }
        });
        return v;
    }
}
