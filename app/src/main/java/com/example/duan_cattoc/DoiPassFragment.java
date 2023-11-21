package com.example.duan_cattoc;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.duan_cattoc.Dao.NhanVienDAO;
import com.google.android.material.textfield.TextInputEditText;


public class DoiPassFragment extends Fragment {

    TextInputEditText edPassOld, edPassChange, edRePassChange;
    Button btnSaveUserChange, btnCancleUserChange;
    NhanVienDAO nvdao;

    public DoiPassFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doi_pass, container, false);
    }
}