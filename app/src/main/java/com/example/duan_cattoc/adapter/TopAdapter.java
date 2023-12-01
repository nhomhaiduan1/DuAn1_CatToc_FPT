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


import com.example.duan_cattoc.R;
import com.example.duan_cattoc.TopDichVu;
import com.example.duan_cattoc.model.Top;

import java.util.ArrayList;

public class TopAdapter extends ArrayAdapter<Top> {
    private Context context;
    TopDichVu fragment;
    ArrayList<Top> list;
    TextView tvSach,tvSoLuong;
    ImageView imgDel;
    public TopAdapter(@NonNull Context context, TopDichVu fragment, ArrayList<Top> list) {
        super(context, 0,list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_top,null);
        }
        final Top item = list.get(position);
        if (item != null){
            tvSach = v.findViewById(R.id.tvDichVu);
            tvSach.setText("Dịch Vụ: "+item.getTenDichVu());

            tvSoLuong = v.findViewById(R.id.tvSL);
            tvSoLuong.setText("Số lượng: "+item.getSoLuong());
        }
        return v;
    }


}
