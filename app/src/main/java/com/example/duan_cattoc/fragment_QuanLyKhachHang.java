package com.example.duan_cattoc;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.duan_cattoc.Dao.KhachHangDAO;
import com.example.duan_cattoc.adapter.KhachHangAdapter;
import com.example.duan_cattoc.model.KhachHang;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class fragment_QuanLyKhachHang extends Fragment {
    ListView lvKhachHang;
    ArrayList<KhachHang> list;
    static KhachHangDAO dao;
    KhachHangAdapter adapter;
    KhachHang item;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaTV, edTenTV, edNamSinh, edCccd ,edSoCuoi;
    Button btnSave, btnCancel,btnTimKiem;
    public fragment_QuanLyKhachHang() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_khach_hang, container, false);
        lvKhachHang = v.findViewById(R.id.lvKhachHang);
        edSoCuoi = v.findViewById(R.id.edSoCuoi);
        btnTimKiem = v.findViewById(R.id.btnTimKiem);
        fab = v.findViewById(R.id.fab);
        dao = new KhachHangDAO(getActivity());
        capNhatLv();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);

            }
        });
        lvKhachHang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(), 1);
                return false;
            }
        });
        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String soCuoi = edSoCuoi.getText().toString();
                if (!soCuoi.isEmpty()) {
                    timKiemKhachHangTheoSoCuoi(soCuoi);
                } else {
                    // Hiển thị thông báo khi ô tìm kiếm trống
                    Toast.makeText(getContext(), "Vui lòng nhập 3 số cuối", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Thêm sự kiện cho việc xóa nội dung ô tìm kiếm
        edSoCuoi.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    // Nếu người dùng ấn nút DELETE, thực hiện cập nhật ListView
                    capNhatLv();
                }
                return false;
            }
        });
        return v;

    }
    // Phương thức tìm kiếm khách hàng theo 3 số cuối của số điện thoại
    private void timKiemKhachHangTheoSoCuoi(String soCuoi) {
        ArrayList<KhachHang> ketQuaTimKiem = dao.searchByLastThreeDigits(soCuoi);
        if (ketQuaTimKiem != null && !ketQuaTimKiem.isEmpty()) {
            // Cập nhật danh sách khách hàng trên ListView
            adapter = new KhachHangAdapter(getActivity(), this, ketQuaTimKiem);
            lvKhachHang.setAdapter(adapter);
        } else {
            // Hiển thị thông báo khi không tìm thấy kết quả
            Toast.makeText(getContext(), "Không tìm thấy khách hàng", Toast.LENGTH_SHORT).show();
        }
    }
    void capNhatLv() {
        list = (ArrayList<KhachHang>) dao.getAll();
        adapter = new KhachHangAdapter(getActivity(), this, list);
        lvKhachHang.setAdapter(adapter);
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
                capNhatLv();
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

    protected void openDialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_khach_hang);
        edMaTV = dialog.findViewById(R.id.edMaKH);
        edTenTV = dialog.findViewById(R.id.edTenKH);
        edNamSinh = dialog.findViewById(R.id.edNamSinh);
        edCccd = dialog.findViewById(R.id.edSDT);

        btnCancel = dialog.findViewById(R.id.btnCancelTV);
        btnSave = dialog.findViewById(R.id.btnSaveTV);

        edMaTV.setEnabled(false);
        if (type != 0) {
            edMaTV.setText(String.valueOf(item.getMaKH()));
            edTenTV.setText(item.getHoTen());
            edNamSinh.setText(item.getNamSinh());
            edCccd.setText(item.getSDT());
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
                item = new KhachHang();
                item.setHoTen(edTenTV.getText().toString());
                item.setNamSinh(edNamSinh.getText().toString());
                item.setSDT(edCccd.getText().toString());
                if (validate() > 0) {
                    if (type == 0) {
                        if (dao.insert(item) > 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        item.setMaKH(Integer.parseInt(edMaTV.getText().toString()));
                        if (dao.update(item) > 0) {
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public int validate() {
        int check = 1;
        if (edTenTV.getText().length() == 0 || edNamSinh.getText().length() == 0 || edCccd.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;

        }
        return check;

    }


}