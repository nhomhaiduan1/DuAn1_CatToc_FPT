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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.duan_cattoc.Dao.LoaiDichVuDAO;
import com.example.duan_cattoc.Dao.NhanVienDAO;
import com.example.duan_cattoc.adapter.LoaiDichVuAdapter;
import com.example.duan_cattoc.adapter.NhanVienAdapter;
import com.example.duan_cattoc.model.LoaiDichVu;
import com.example.duan_cattoc.model.Nhanvien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class fragment_LoaiDichVu extends Fragment {
    ListView lvLoaiDichVu;
    ArrayList<LoaiDichVu> list;
    static LoaiDichVuDAO dao;
    LoaiDichVuAdapter adapter;
    LoaiDichVu item;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaLoaiDichVu, edTenLoaiDichVu;
    Button btnSave, btnCancel;

    public fragment_LoaiDichVu() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_fragment_loai_dich_vu, container, false);
        lvLoaiDichVu = v.findViewById(R.id.lvLoaiDichVu);
        fab = v.findViewById(R.id.fab);
        dao = new LoaiDichVuDAO(getActivity());
       capNhatLv();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });

        lvLoaiDichVu.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(), 1);
                return false;
            }
        });

        return v;
    }

    void capNhatLv() {
        list = (ArrayList<LoaiDichVu>) dao.getAll();
        adapter = new LoaiDichVuAdapter(getActivity(), this,list);
        lvLoaiDichVu.setAdapter(adapter);
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
        dialog.setContentView(R.layout.dialog_loai_dich_vu);
       edMaLoaiDichVu = dialog.findViewById(R.id.edMaLoaiDichVu);
        edTenLoaiDichVu = dialog.findViewById(R.id.edTenLoaiDichVu);
        btnCancel = dialog.findViewById(R.id.btnCancelLS);
        btnSave = dialog.findViewById(R.id.btnSaveLS);

        edMaLoaiDichVu.setEnabled(false);
        if (type != 0) {
            edMaLoaiDichVu.setText(String.valueOf(item.getMaLoai()));
            edTenLoaiDichVu.setText(item.getTenLoai());
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
                item = new LoaiDichVu();
                item.setTenLoai(edTenLoaiDichVu.getText().toString());
                if (validate() > 0) {
                    if (type == 0) {
                        if (dao.insert(item) > 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        item.setMaLoai(Integer.parseInt(edMaLoaiDichVu.getText().toString()));
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
        if (edTenLoaiDichVu.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;

        }
        return check;

    }
}