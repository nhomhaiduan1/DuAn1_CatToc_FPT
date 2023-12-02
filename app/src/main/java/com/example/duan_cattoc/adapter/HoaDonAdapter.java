package com.example.duan_cattoc.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan_cattoc.Dao.DichVuDAO;
import com.example.duan_cattoc.Dao.HoaDonDAO;
import com.example.duan_cattoc.Dao.KhachHangDAO;
import com.example.duan_cattoc.Dao.LoaiDichVuDAO;
import com.example.duan_cattoc.Dao.NhanVienDAO;
import com.example.duan_cattoc.R;
import com.example.duan_cattoc.fragment_QuanLyHoaDon;
import com.example.duan_cattoc.model.DichVu;
import com.example.duan_cattoc.model.HoaDon;
import com.example.duan_cattoc.model.KhachHang;
import com.example.duan_cattoc.model.Nhanvien;


import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HoaDonAdapter extends ArrayAdapter<HoaDon> {
    private Context context;
    fragment_QuanLyHoaDon fragment;
    private ArrayList<HoaDon> list;
    TextView tvMaHD, tvTenKH, tvTenDichVu, tvGia, tvNgay, tvThanhToan;
    ImageView imgDel;
    DichVuDAO DichVuDAO;
    KhachHangDAO KhachHangDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public HoaDonAdapter(@NonNull Context context, fragment_QuanLyHoaDon fragment, ArrayList<HoaDon> list) {
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
            v = inflater.inflate(R.layout.item_don_hang, null);
        }
        final HoaDon item = list.get(position);
        if (item != null) {
            tvMaHD = v.findViewById(R.id.tvMaHD);
            tvMaHD.setText("Mã Hóa Đơn: " + item.getMaHD());

            DichVuDAO = new DichVuDAO(context);
            DichVu dichVu = DichVuDAO.getID(String.valueOf(item.getMaDichVu()));
            tvTenDichVu = v.findViewById(R.id.tvTenDichVu);
            tvTenDichVu.setText("Tên Dịch Vụ: " + dichVu.getTenDichVu());
            KhachHangDAO = new KhachHangDAO(context);
            KhachHang KhachHang = KhachHangDAO.getID(String.valueOf(item.getMaKH()));
            tvTenKH = v.findViewById(R.id.tvTenTV);
            tvTenKH.setText("Khách Hàng: " + KhachHang.getHoTen());
//            Nhanvien nhanvien = NhanVienDAO.getID(String.valueOf(item.getMaNV()));
            tvGia = v.findViewById(R.id.tvGia);
            tvGia.setText("Tiền Dịch Vụ: " + item.getTienDichVu());
            tvNgay = v.findViewById(R.id.tvNgayHD);
            tvNgay.setText("Ngày : " + sdf.format(item.getNgay()));

            tvThanhToan = v.findViewById(R.id.tvThanhToan);
            if (item.getThanhToan() == 1) {
                tvThanhToan.setTextColor(Color.BLUE);
                tvThanhToan.setText("Đã Thanh Toán");
            } else {
                tvThanhToan.setTextColor(Color.RED);
                tvThanhToan.setText("Chưa Thanh Toán");
            }
            imgDel = v.findViewById(R.id.imgDeleteLS);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // goi phuong thuc xoa
                fragment.xoa(String.valueOf(item.getMaHD()));
            }
        });
        return v;

    }
}
