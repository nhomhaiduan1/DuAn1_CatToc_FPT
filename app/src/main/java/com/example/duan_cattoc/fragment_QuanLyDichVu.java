package com.example.duan_cattoc;

import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duan_cattoc.Dao.LoaiDichVuDAO;
import com.example.duan_cattoc.adapter.DichVuAdapter;
import com.example.duan_cattoc.adapter.LoaiDichVuSpinnerAdapter;
import com.example.duan_cattoc.model.DichVu;
import com.example.duan_cattoc.model.LoaiDichVu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class fragment_QuanLyDichVu extends Fragment {
    ListView lvDichVu;
    LoaiDichVuDAO.DichVuDAO DichVuDAO;
    DichVuAdapter adapter;
    DichVu item;
    List<DichVu> list;

    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaDichVu, edTenDichVu, edGiaDichVu;
    Spinner spinner;
    Button btnSave, btnCancel;

    LoaiDichVuSpinnerAdapter spinnerAdapter;
    ArrayList<LoaiDichVu> listLoaiDichVu;
    LoaiDichVuDAO loaiDichVuDAO;
    LoaiDichVu loaiDichVu;
    int maLoaiDichVu, position;


    public fragment_QuanLyDichVu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_fragment_quan_ly_dich_vu, container, false);
        lvDichVu = v.findViewById(R.id.lvDichVu);
        DichVuDAO = new LoaiDichVuDAO.DichVuDAO(getActivity());
        capNhatLv();
        fab = v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });
        lvDichVu.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
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
        list = (ArrayList<DichVu>) DichVuDAO.getAll();
        adapter = new DichVuAdapter(getActivity(), this, list);
        lvDichVu.setAdapter(adapter);
    }

    public void xoa(final String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DichVuDAO.delete(Id);
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
        dialog.setContentView(R.layout.dialog_dich_vu);
        edMaDichVu = dialog.findViewById(R.id.edMaDichVu);
        edTenDichVu = dialog.findViewById(R.id.edTenDichVu);
        edGiaDichVu = dialog.findViewById(R.id.edGiaDichVu);
        spinner = dialog.findViewById(R.id.spLoaiDichVu);
        btnCancel = dialog.findViewById(R.id.btnCancelDichVu);
        btnSave = dialog.findViewById(R.id.btnSaveDichVu);

        listLoaiDichVu = new ArrayList<LoaiDichVu>();
        loaiDichVuDAO = new LoaiDichVuDAO(context);
        listLoaiDichVu = (ArrayList<LoaiDichVu>) loaiDichVuDAO.getAll();

        spinnerAdapter = new LoaiDichVuSpinnerAdapter(context, listLoaiDichVu);
        spinner.setAdapter(spinnerAdapter);
        // lay maLoaiSach
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoaiDichVu = listLoaiDichVu.get(position).getMaLoai();
//                Toast.makeText(context, "Chọn "+listLoaiSach.get(position).getTenLoai(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // kiem tra tupe insert hay update
        edMaDichVu.setEnabled(false);
        if (type != 0) {
            edMaDichVu.setText(String.valueOf(item.getMaDichVu()));
            edTenDichVu.setText(item.getTenDichVu());
            edGiaDichVu.setText(String.valueOf(item.getGiaDichVu()));
            for (int i = 0; i < listLoaiDichVu.size(); i++)
                if (item.getMaLoai() == (listLoaiDichVu.get(i).getMaLoai())) {
                    position = i;
                }
            Log.i("demo", "posDichVu " + position);
            spinner.setSelection(position);
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
                item = new DichVu();
                item.setTenDichVu(edTenDichVu.getText().toString());
                item.setGiaDichVu(parseInt(edGiaDichVu.getText().toString(), 0));
                item.setMaLoai(maLoaiDichVu);
                if (validate() > 0) {
                    if (type == 0) {
                        if (DichVuDAO.insert(item) > 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        item.setMaDichVu(Integer.parseInt(edMaDichVu.getText().toString()));
                        if (DichVuDAO.update(item) > 0) {
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Sứa thất bại", Toast.LENGTH_SHORT).show();
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
        if (edTenDichVu.getText().length() == 0 || edGiaDichVu.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

    public static int parseInt(String string, int defaultValue) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}