package com.example.asmodeus.constcalapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputActivity extends AppCompatActivity {
    public EditText L,W,H,flr,rm,budget;
    String r,l,w,f,h,bud,uid;
    Button sub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        L=(EditText)findViewById(R.id.ip_et_length);
        W=(EditText)findViewById(R.id.ip_et_width);
        H=(EditText)findViewById(R.id.ip_et_height);
        flr=(EditText)findViewById(R.id.ip_et_floors);
        rm=(EditText)findViewById(R.id.ip_et_rooms);
        budget=(EditText)findViewById(R.id.ip_et_bud);
        sub=(Button)findViewById(R.id.ip_btn);
        uid=getIntent().getStringExtra("title");
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(budget.getText().toString().equals("")||H.getText().toString().equals("")||W.getText().toString().equals("")||L.getText().toString().equals("")||flr.getText().toString().equals("")||rm.getText().toString().equals(""))
                {
                    Toast.makeText(getBaseContext(),"! Please Enter data in all fields !",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent i = new Intent(InputActivity.this, HomeActivity.class);
                    i.putExtra("ticket_bud", budget.getText().toString());
                    i.putExtra("ticket_H", H.getText().toString());
                    i.putExtra("ticket_W", W.getText().toString());
                    i.putExtra("ticket_L", W.getText().toString());
                    i.putExtra("title", uid);
                    i.putExtra("ticket_flr", flr.getText().toString());
                    i.putExtra("ticket_rm", rm.getText().toString());
                    startActivity(i);
                }
            }
        });

    }
}
