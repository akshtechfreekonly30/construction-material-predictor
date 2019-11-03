package com.example.asmodeus.constcalapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class BaseFragment extends Fragment{
    View MyView;
    TextView help;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MyView=inflater.inflate(R.layout.base_layout,container,false);
        help=(TextView)MyView.findViewById(R.id.hlp);

        help.setText("1) Click on the menu button on top left corner.\n2) Select from the given choices .\n3) quit or logout\n ");

        return MyView;
    }
}
