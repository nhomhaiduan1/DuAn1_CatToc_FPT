package com.example.duan_cattoc;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan_cattoc.Dao.DichVuDAO;
import com.example.duan_cattoc.Dao.HoaDonDAO;
import com.example.duan_cattoc.Dao.KhachHangDAO;
import com.example.duan_cattoc.adapter.DichVuSpinnerAdapter;
import com.example.duan_cattoc.adapter.HoaDonAdapter;
import com.example.duan_cattoc.adapter.KhachHangSpinnerAdapter;
import com.example.duan_cattoc.model.DichVu;
import com.example.duan_cattoc.model.HoaDon;
import com.example.duan_cattoc.model.KhachHang;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class fragment_QuanLyHoaDon extends Fragment {
    ListView lvHoaDon;
    ArrayList<HoaDon> list;
    static HoaDonDAO dao;
    HoaDonAdapter adapter;
    HoaDon item;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaHD;
    Spinner spKH, spDichVu;
    TextView tvNgay, tvGia;
    CheckBox chkThanhToan;
    Button btnSave, btnCancel;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    KhachHangSpinnerAdapter khachHangSpinnerAdapter;
    ArrayList<KhachHang> listKhachHang;
    KhachHangDAO khachHangDAO;

    int maKhachHang;

    DichVuSpinnerAdapter dichVuSpinnerAdapter;
    ArrayList<DichVu> listSach;
    DichVuDAO dichVuDAO;
    DichVu dichVu;
    int maDichVu, gia;
    int positionKH, positionDichVu;
    public fragment_QuanLyHoaDon() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_fragment_quan_ly_don_hang,
                container, false);
        lvHoaDon = v.findViewById(R.id.lvHoaDon);
        fab = v.findViewById(R.id.fab);
        dao = new HoaDonDAO(getActivity());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });
        lvHoaDon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(), 1);// update
                return false;
            }
        });
        capNhatlv();
       return v;
    }

    void capNhatlv() {
        list = (ArrayList<HoaDon>) dao.getAll();
        adapter = new HoaDonAdapter(getActivity(), this, list);
        lvHoaDon.setAdapter(adapter);
    }

    protected void openDialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_don_hang);
        edMaHD = dialog.findViewById(R.id.edMaHD);
        spKH = dialog.findViewById(R.id.spMaKH);
        spDichVu = dialog.findViewById(R.id.spMaDV);
        tvNgay = dialog.findViewById(R.id.tvNgay);
        tvGia = dialog.findViewById(R.id.tvGia);
        chkThanhToan = dialog.findViewById(R.id.chkThanhToan);
        btnCancel = dialog.findViewById(R.id.btnCancel);
        btnSave = dialog.findViewById(R.id.btnSave);
        // set ngày thuê

        tvNgay.setText("Ngày : " + sdf.format(new Date()));

        tvNgay.setText("Ngày: " + sdf.format(new Date()));

        edMaHD.setEnabled(false);

        khachHangDAO = new KhachHangDAO(context);
        listKhachHang = new ArrayList<KhachHang>();
        listKhachHang = (ArrayList<KhachHang>) khachHangDAO.getAll();
        khachHangSpinnerAdapter = new KhachHangSpinnerAdapter(context, listKhachHang);
        spKH.setAdapter(khachHangSpinnerAdapter);
        spKH.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maKhachHang = listKhachHang.get(position).getMaKH();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dichVuDAO = new DichVuDAO(context);
        listSach = new ArrayList<DichVu>();
        listSach = (ArrayList<DichVu>) dichVuDAO.getAll();
        dichVuSpinnerAdapter = new DichVuSpinnerAdapter(context, listSach);
        spDichVu.setAdapter(dichVuSpinnerAdapter);
        // lay mã loại dịch vụ
        spDichVu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maDichVu = listSach.get(position).getMaDichVu();
                gia = listSach.get(position).getGiaDichVu();
                tvGia.setText("Tiền Dịch Vụ: " + gia +"K");
//
            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // edit
        if (type != 0) {
            edMaHD.setText(String.valueOf(item.getMaHD()));
            for (int i = 0; i < listKhachHang.size(); i++)
                if (item.getMaKH() == (listKhachHang.get(i).getMaKH())) {
                    positionKH = i;
                }
            spKH.setSelection(positionKH);

            for (int i = 0; i < listSach.size(); i++)
                if (item.getMaDichVu() == (listSach.get(i).getMaDichVu())) {
                    positionDichVu = i;
                }
            spDichVu.setSelection(positionDichVu);

            tvNgay.setText("Ngày Cắt: " + sdf.format(item.getNgay()));
            tvGia.setText("Giá Tiền: " + item.getTienDichVu() +"K");
            if (item.getThanhToan() == 1) {
                chkThanhToan.setChecked(true);
            } else {
                chkThanhToan.setChecked(false);
            }
        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new HoaDon();
                item.setMaDichVu(maDichVu);
                item.setMaKH(maKhachHang);
                item.setNgay(new Date());
                item.setTienDichVu(gia);
                if (chkThanhToan.isChecked()) {
                    item.setThanhToan(1);
                } else {
                    item.setThanhToan(0);
                }
                if (type == 0) {
                    // insert
                    long insert = dao.insert(item);
                    if (insert > 0) {
                        Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                    capNhatlv();
                    dialog.dismiss();
                } else {
                    // update
                    item.setMaHD(Integer.parseInt(edMaHD.getText().toString()));
                    if (dao.update(item) > 0) {
                        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }
                    capNhatlv();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public void xoa(final String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(Id);
                capNhatlv();
                dialog.cancel();
                Toast.makeText(getContext(), "Đã xóa", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Toast.makeText(getContext(), "Không xóa", Toast.LENGTH_SHORT).show();

            }
        });
        AlertDialog alert = builder.create();
        builder.show();
    }
}