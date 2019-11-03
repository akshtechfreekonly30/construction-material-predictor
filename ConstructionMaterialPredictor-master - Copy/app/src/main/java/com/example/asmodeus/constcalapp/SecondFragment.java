package com.example.asmodeus.constcalapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SecondFragment extends Fragment {
    View MyView;
    String r,l,w,f,h,bud;
    TextView res_brick;
    public int BUDGET;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MyView=inflater.inflate(R.layout.second_layout,container,false);
        int rms,brk;
        int bricks[]=new int[2];
        int insqft;
        double hinmtr;
        double insqmtr;
        double area;

        res_brick=(TextView)MyView.findViewById(R.id.res_brick);
        Bundle bundle=getArguments();

        l=bundle.getString("ipLength");
        w=bundle.getString("ipWidth");
        h=bundle.getString("ipHeight");
        f=bundle.getString("ipFloors");
        r=bundle.getString("ipRooms");
        bud=bundle.getString("ipBudget");
        BUDGET=Integer.parseInt(bud);

        rms=Integer.parseInt(f)*Integer.parseInt(r)*4;
        insqft = Integer.parseInt(w) * Integer.parseInt(l);
        insqmtr=0.0929*insqft;
        hinmtr=Integer.parseInt(h)*0.3048;
        area=insqmtr*hinmtr;

        for(int i=0;i<2;i++)
        {

            switch(i)
            {
                case 0://single brick wall
                    bricks[i]= (int) (Integer.parseInt(r)*(8*insqft));
                    break;
                case 1://full brick wall
                    bricks[i]= (int) (Integer.parseInt(f)*(64*insqft));
                    break;
            }

        }
        brk=bricks[0]+bricks[1];
        int mrp = (int) (brk * 3);
        res_brick.setText("NO OF BRICKS:-\n\n 1)Half Brick Wall:-"+bricks[0]+"\n 2)One Brick Wall:-"+bricks[1]+"\n 3) Total Bricks required :-"+brk+"\n\nCOST :-"+mrp+" INR");

        return MyView;
    }

}
