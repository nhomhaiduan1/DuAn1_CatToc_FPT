package com.example.duan_cattoc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan_cattoc.Dao.NhanVienDAO;
import com.example.duan_cattoc.model.Nhanvien;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    Toolbar toolbar;
    View mHeaderView;
    TextView tvUser;
    NavigationView nv;

    NhanVienDAO nhanVienDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // anh xa
        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar1);
        nv = findViewById(R.id.nvView);
        // set toolbar thay actionbar
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.menu);
        ab.setDisplayHomeAsUpEnabled(true);
        // set mau icon ve ban goc
        nv.setItemIconTintList(null);

        // show user trên header
        mHeaderView = nv.getHeaderView(0);
        tvUser = mHeaderView.findViewById(R.id.tvUser);
        Intent i = getIntent();
        String user = i.getStringExtra("user");
        nhanVienDAO = new NhanVienDAO(this);
        Nhanvien nhanVien = nhanVienDAO.getID(user);
        String username = nhanVien.getHoTen();
        tvUser.setText("Welcome " + username + "!");

        // admin co quyen add user
        if (user.equalsIgnoreCase("admin")) {
            nv.getMenu().findItem(R.id.sub_AddUser).setVisible(true);
        }
        fragment_QuanLyDonHang frquanlydonhang = new fragment_QuanLyDonHang();
        replaceFrg(frquanlydonhang);


        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                if (id == R.id.sub_quanlydonhang) {
                    setTitle("Quản Lý Đơn Hàng");
                    replaceFrg(frquanlydonhang);

                } else if (id == R.id.sub_QLDV) {
                    setTitle("Quản lý Dịch vụ");
                    fragment_QuanLyDichVu frdichvu = new fragment_QuanLyDichVu();
                    replaceFrg(frdichvu);

                } else if (id == R.id.sub_QLKH) {
                    setTitle("Quản lý Khách Hàng");
                    fragment_QuanLyKhachHang frkhachhang = new fragment_QuanLyKhachHang();
                    replaceFrg(frkhachhang);

                } else if (id == R.id.sub_DoanhThu) {
                    setTitle("Quản lý Doanh Thu");
                    fragment_DoanhThu frdoanhthu = new fragment_DoanhThu();
                    replaceFrg(frdoanhthu);

                } else if (id == R.id.sub_LoaiDichVu) {
                    setTitle("Loại Dịch Vụ");
                    fragment_LoaiDichVu frloaidichvu = new fragment_LoaiDichVu();
                    replaceFrg(frloaidichvu);

                } else if (id == R.id.sub_LienHe) {
                    setTitle("LienHe");
                    fragment_LienHe frlienhe = new fragment_LienHe();
                    replaceFrg(frlienhe);

                } else if (id == R.id.sub_AddUser) {
                    setTitle("Thêm người dùng");
                    fragment_ThemNhanVien fradduser = new fragment_ThemNhanVien();
                    replaceFrg(fradduser);

           }

               else if (id == R.id.sub_Pass) {
                    setTitle("Thay đổi mật khẩu");
                    fragment_DoiMatKhau frchangepass = new fragment_DoiMatKhau();
                    replaceFrg(frchangepass);

                } else if (id == R.id.sub_Logout) {
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Đăng xuất");
                    builder.setMessage("Bạn có muốn đăng xuất không?");
                    builder.setCancelable(true);

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MainActivity.this, DangNhap.class);
                            Toast.makeText(MainActivity.this, "Đã đăng xuất", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            Toast.makeText(MainActivity.this, "Không đăng xuất", Toast.LENGTH_SHORT).show();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();

                }
                drawer.closeDrawers();
                return true;
            }
        });
    }

    public void replaceFrg(Fragment frg) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.flContent, frg).commit();
    }
}