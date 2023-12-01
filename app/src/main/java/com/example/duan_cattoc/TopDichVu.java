package com.example.duan_cattoc;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.duan_cattoc.Dao.HoaDonDAO;
import com.example.duan_cattoc.Dao.ThongKeDAO;
import com.example.duan_cattoc.adapter.TopAdapter;
import com.example.duan_cattoc.model.Top;

import java.util.ArrayList;

public class TopDichVu extends Fragment {

    ListView lvTop;
    ArrayList<Top> list;
    TopAdapter adapter;
    ThongKeDAO dao;

    public TopDichVu() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_top_dich_vu, container, false);
        lvTop = v.findViewById(R.id.lvTop);
        HoaDonDAO hoaDonDAO = new HoaDonDAO(getActivity());
        list = (ArrayList<Top>) HoaDonDAO.getTop();
        adapter = new TopAdapter(getActivity(),this,list);
        lvTop.setAdapter(adapter);
        return v;
    }
}