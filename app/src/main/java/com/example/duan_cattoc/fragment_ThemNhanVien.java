package com.example.duan_cattoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.duan_cattoc.Dao.NhanVienDAO;
import com.example.duan_cattoc.adapter.NhanVienAdapter;
import com.example.duan_cattoc.model.Nhanvien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class fragment_ThemNhanVien extends Fragment {
    ListView lvNhanVien;
    ArrayList<Nhanvien> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edUser,edHoTen,edPass,edRePass,edDiaChi,edsDT;
    Button btnSaveTT,btnCancleTT;
    static NhanVienDAO dao;
    NhanVienAdapter adapter;
    Nhanvien item;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_fragment_them_nhan_vien,container,false);
        lvNhanVien = v.findViewById(R.id.lvNhanVien);
        fab = v.findViewById(R.id.fab);
        dao = new NhanVienDAO(getActivity());
        capNhatLv();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0);
            }
        });

        lvNhanVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(),1);// update
                return false;
            }
        });
        return v;
    }

    void capNhatLv() {
        list = (ArrayList<Nhanvien>) dao.getAll();
        adapter = new NhanVienAdapter(getActivity(), this, list);
        lvNhanVien.setAdapter(adapter);
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

    protected void openDialog(final Context context, final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_add_user);
        edUser = dialog.findViewById(R.id.edUser);
        edHoTen = dialog.findViewById(R.id.edHoTen);
        edPass = dialog.findViewById(R.id.edPass);
        edDiaChi=dialog.findViewById(R.id.edDiaChi);
        edsDT=dialog.findViewById(R.id.edsDT);
        edRePass = dialog.findViewById(R.id.edRePass);
        btnCancleTT = dialog.findViewById(R.id.btnCancelTT);
        btnSaveTT = dialog.findViewById(R.id.btnSaveTT);

        if (type != 0){
            edUser.setText(item.getMaNV());
            edPass.setText(item.getMatKhau());
            edHoTen.setText(item.getHoTen());
            edDiaChi.setText(item.getDiaChi());
            edsDT.setText(item.getsDT());
            edRePass.setText(item.getMatKhau());
        }

        btnCancleTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnSaveTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new Nhanvien();

                item.setMaNV(edUser.getText().toString());
                item.setMatKhau(edPass.getText().toString());
                item.setHoTen(edHoTen.getText().toString());
                item.setDiaChi(edDiaChi.getText().toString());
                item.setsDT(edsDT.getText().toString());
                if(validate() > 0){
                    if (type == 0){
                        if (dao.insert(item) > 0){
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            edUser.setText("");
                            edPass.setText("");
                            edRePass.setText("");
                            edHoTen.setText("");
                            edDiaChi.setText("");
                            edsDT.setText("");
                        }else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        item.setMaNV(edUser.getText().toString());
                        if (dao.updatePass(item) > 0){
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        }else {
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
        if (edUser.getText().length() == 0 || edHoTen.getText().length() == 0 || edPass.getText().length() == 0 || edRePass.getText().length() == 0 ||edDiaChi.getText().length()==0||edsDT.getText().length()==0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }else {
            String pass = edPass.getText().toString();
            String repass = edRePass.getText().toString();
            if (!pass.equals(repass)){
                Toast.makeText(getActivity(), "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}
