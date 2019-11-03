package com.example.asmodeus.constcalapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ThirdFragment extends Fragment {
    View MyView;
    String r,l,w,f,h,bud;
    public TextView res_exca;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MyView=inflater.inflate(R.layout.third_layout,container,false);
        Bundle bundle=getArguments();
        res_exca=(TextView)MyView.findViewById(R.id.res_exca);

        l=bundle.getString("ipLength");
        w=bundle.getString("ipWidth");
        h=bundle.getString("ipHeight");
        f=bundle.getString("ipFloors");
        r=bundle.getString("ipRooms");
        bud=bundle.getString("ipBudget");
        int holes,poles;

        holes=5;

        poles=Integer.parseInt(f)+8;
        if(poles>60)
        {

            res_exca.setText("\nREQUIRED EXCAVATION :-\n 1)No of poles Required:-"+holes+"\n 2)size of poles to dig:-60 feet");
        }
        else {
            res_exca.setText("\nREQUIRED EXCAVATION :-\n 1)No of poles Required:-" + holes + "\n 2)size of poles to dig:-" + poles + " feet");
        }
        return MyView;
    }
}
