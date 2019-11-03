package com.example.asmodeus.constcalapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.BatchUpdateException;


public class FirstFragment extends Fragment{
    View MyView;
    String r,l,w,f,h,bud;
    int bag[]= new int[4];
    float agre[]= new float[4];
    float sand[]= new float[4];

    int insqft,bd;
    double hinmtr;
    double insqmtr;
    double area;
    public TextView cm,sug_cme;
    int rS[]=new int[4];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MyView=inflater.inflate(R.layout.first_layout,container,false);
                 Bundle bundle=getArguments();
                sug_cme=(TextView)MyView.findViewById(R.id.sug_cme);
                l=bundle.getString("ipLength");
                w=bundle.getString("ipWidth");
                h=bundle.getString("ipHeight");
                f=bundle.getString("ipFloors");
                r=bundle.getString("ipRooms");
                bud=bundle.getString("ipBudget");
    int bd=Integer.parseInt(bud);
        cm=(TextView)MyView.findViewById(R.id.res_cme);

        insqft=Integer.parseInt(w)*Integer.parseInt(l);
        insqmtr=0.0929*insqft;
        hinmtr=Integer.parseInt(h)*0.3048;
        area=insqmtr*hinmtr;

        for(int i=0;i<4;i++) {
            switch (i) {
                case 0://m5
                    bag[0] = (int) Math.round((0.076 * 1.52 *200*area) /50);

                    agre[0]= (float) ((0.61*1.52*35.28*area)/100);
                    sand[0]=(float)(0.30*35.28*1.52*area)/100;
                    rS[0]= (int) (bag[0]*350+sand[0]*3000+agre[0]*2600);
                    break;
                case 1://m10
                    bag[1] = (int) Math.round((0.1 * 1.52*200*area) / 50);
                    agre[1]=(float)(0.6*1.52*35.28*area)/100;
                    sand[1]=(float)(0.3*35.28*1.52*area)/100;
                    rS[1]= (int) (bag[1]*350+sand[1]*3000+agre[1]*2600);
                    break;
                case 2://m20
                    bag[2] = (int) Math.round((0.181 * 1.52 *200*area) /50);
                    agre[2]=(float)(0.545*1.52*35.28*area)/100;
                    sand[2]=(float)(0.272*35.28*1.52*area)/100;
                    rS[2]= (int) (bag[2]*350+sand[2]*3000+agre[2]*2600);
                    break;
                case 3://m25
                    bag[3] = (int) Math.round((0.25 * 1.52*200*area) /50);
                    agre[3]=(float)(0.5*1.52*35.28*area)/100;
                    sand[3]=(float)(0.25*35.28*1.52*area)/100;
                    rS[3]= (int) (bag[3]*350+sand[3]*3000+agre[3]*2600);
                    break;
            }
        }

        cm.setText("\n"+"Bags of Cement Required:-\n 1) m5:- \t"+bag[0]+" bags \n 2) m10:-\t"+bag[1]+" bags \n 3) m20:-\t"+bag[2]+" bags\n 4) m25:-\t"+bag[3]+" bags\n\nAggregate Requiredin brass:-\n 1) m5:- \t"+agre[0]+" brass\n 2) m10:-\t"+agre[1]+ " brass\n 3) m20:-\t"+agre[2]+" brass\n 4) m25:-\t"+agre[3]+" brass\n\nSand Required in brass:-\n 1) m5:- \t"+sand[0]+" brass\n 2) m10:-\t"+sand[1]+" brass\n 3) m20:-\t"+sand[2]+" brass\n 4) m25:-\t"+sand[3]+" brass \n\n Cost :-\n 1)m5:- \t"+rS[0]+"\n 2)m10:-\t"+rS[1]+"\n 3)m20:-\t"+rS[2]+"\n 4)m25:-\t"+rS[3]);
        suggest();


        return MyView;
    }
    void suggest()
    {
        for(int i=0;i<4;i++)
        {
            if(rS[i]<(bd/2))
            {
                continue;
            }
            else
            { String ty = null;
                switch(i)
                {
                    case 0:ty="m5";
                        break;
                    case 1:ty="m10";
                        break;
                    case 2:ty="m20";
                        break;
                    case 3:ty="m25";
                        break;
                }

                sug_cme.setText("\n\n Suggestions :-\n 1) type:-\t"+ty+" \n 2) bags of cement required:-\t"+bag[i]+" bags\n 3)Aggregate(Scrap) required:-\t"+agre[i]+" brass\n 4)Sand required:-\t"+sand[i]+" brass\n 5)Cost:-\t"+rS[i]);
                break;
            }
        }

    }
}
