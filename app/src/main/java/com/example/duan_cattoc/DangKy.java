package com.example.duan_cattoc;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duan_cattoc.Dao.KhachHangDAO;
import com.example.duan_cattoc.model.KhachHang;
import com.google.android.material.textfield.TextInputEditText;

public class DangKy extends AppCompatActivity {

    TextInputEditText edHoten, edDangkypassword, edDangkyusername, edNhaplaipassword;
    Button btnDangky;
    KhachHangDAO khachHangDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        edHoten = findViewById(R.id.edHoten);
        edDangkypassword = findViewById(R.id.edDangkypassword);
        edDangkyusername = findViewById(R.id.edDangkyusername);
        edNhaplaipassword = findViewById(R.id.edNhaplaipassword);
        btnDangky = findViewById(R.id.btnDangky);
        khachHangDAO = new KhachHangDAO(DangKy.this);

        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate() > 0) {
                    KhachHang khachHang = new KhachHang();

                    khachHang.setTenDangNhap(edDangkyusername.getText().toString().trim());
                    khachHang.setMatKhau(edDangkypassword.getText().toString().trim());
                    khachHang.setHoTen(edHoten.getText().toString().trim());
                    if (khachHangDAO.insert(khachHang) > 0) {
                        Toast.makeText(DangKy.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), DangNhap.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(DangKy.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

    public int validate() {
        int check = 1;
        if (edHoten.getText().length() == 0 || edDangkyusername.getText().length() == 0 || edDangkypassword.getText().length() == 0 || edNhaplaipassword.getText().length() == 0) {
            Toast.makeText(this, "Không bỏ trống", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        if (!edDangkypassword.getText().toString().trim().matches(edNhaplaipassword.getText().toString().trim())) {
            Toast.makeText(this, "Mật khẩu không trùng hợp", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}