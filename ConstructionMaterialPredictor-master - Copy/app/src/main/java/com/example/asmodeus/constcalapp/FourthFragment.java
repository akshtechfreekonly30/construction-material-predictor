package com.example.asmodeus.constcalapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FourthFragment extends Fragment {
    View MyView;
    String r,l,w,f,h,bud;
    public TextView re,sug;
    int i,ln_steel,no_bar,bd,rs;
    int inkg[]=new int[6];
    int rS[]=new int[6];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MyView=inflater.inflate(R.layout.fourth_layout,container,false);
        Bundle bundle=getArguments();
        re=(TextView)MyView.findViewById(R.id.et_res);
        sug=(TextView)MyView.findViewById(R.id.sug_steel);
        l=bundle.getString("ipLength");
        w=bundle.getString("ipWidth");
        h=bundle.getString("ipHeight");
        f=bundle.getString("ipFloors");
        r=bundle.getString("ipRooms");
        bud=bundle.getString("ipBudget");
        bd=Integer.parseInt(bud);


        ln_steel=Integer.parseInt(f)*Integer.parseInt(h);
        no_bar=16*Integer.parseInt(f)*Integer.parseInt(r);

        for(i=0;i<6;i++) {

            switch(i) {
                case 0: //mm6
                    inkg[i] = (int) (ln_steel * 0.22 * no_bar);
                    rS[i]=inkg[i]*36;
                    break;
                case 1://mm8
                    inkg[i] = (int) (ln_steel * 0.39 * no_bar);
                    rS[i]=inkg[i]*36;
                    break;
                case 2://mm10
                    inkg [i]= (int) (ln_steel * 0.62 * no_bar);
                    rS[i]=inkg[i]*36;
                    break;
                case 3://mm12
                    inkg[i] = (int) (ln_steel * 0.89 * no_bar);
                    rS[i]=inkg[i]*36;
                    break;
                case 4://mm16
                    inkg[i] = (int) (ln_steel * 1.58 * no_bar);
                    rS[i]=inkg[i]*36;
                    break;
                case 5://mm32
                    inkg[i] = (int) (ln_steel * 6.32 * no_bar);
                    rS[i]=inkg[i]*36;
                    break;
            }
        }


        re.setText(" 1)No of Steel rods :-"+no_bar+" of length :-"+ln_steel+" meters"+"\n\n 2)Steel in kg according to type:-\n"+"mm6:"+inkg[0]+" kg\n"+"mm8:"+inkg[1]+" kg\n"+"mm10:"+inkg[2]+" kg\n"+"mm12:"+inkg[3]+" kg\n"+"mm16:"+inkg[4]+" kg\n"+"mm32:"+inkg[5]+" kg\n\n 3)Rs per type "+"mm6:"+rS[0]+" INR\n"+"mm8:"+rS[1]+" INR\n"+"mm10:"+rS[2]+" INR\n"+"mm12:"+rS[3]+" INR\n"+"mm16:"+rS[4]+" INR\n"+"mm32:"+rS[5]+" INR\n");
        for(i=0;i<6;i++)
        {

            if(rS[i]<(bd/4))
            {
                continue;
            }
            else
            { String ty = null;
                switch(i)
                {
                    case 0:ty="mm6";
                        break;
                    case 1:ty="mm8";
                        break;
                    case 2:ty="mm10";
                        break;
                    case 3:ty="mm12";
                        break;
                    case 4:ty="mm16";
                        break;
                    case 5:ty="mm32";
                        break;
                }

                sug.setText("\n\n Suggestions :-\n 1) type:-\t"+ty+" \n 2) Steel required:-\t"+inkg[i]+"\n 3)Cost:-\t"+rS[i]);
                break;
            }
        }

        return MyView;
    }
}
