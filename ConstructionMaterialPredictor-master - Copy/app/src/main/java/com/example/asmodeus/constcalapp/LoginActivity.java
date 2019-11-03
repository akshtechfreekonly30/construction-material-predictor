package com.example.asmodeus.constcalapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private TextView link;
    private EditText usrid;
    private EditText passwrd;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login=(Button)findViewById(R.id.lgn_btn_Login);
        link=(TextView)findViewById(R.id.lgn_tv_newusr);
        usrid=(EditText)findViewById(R.id.lgn_et_usrid);
        passwrd=(EditText)findViewById(R.id.lgn_et_pass);
        db=new DatabaseHelper(this);
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LoginActivity.this,SignupActivity.class);

                startActivity(i);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usr = usrid.getText().toString();
                String pas = passwrd.getText().toString();

                Boolean chk=db.idPass(usr,pas);
                Boolean chkusr=db.chkUser(usr);
                if(chkusr==false)
                {
                    if(chk==true){
                        toastMessage("Login SuccessFull");
                        Intent i=new Intent(LoginActivity.this,InputActivity.class);
                        i.putExtra("title",usrid.getText().toString());
                        startActivity(i);
                    }
                    else{
                        toastMessage("! invalid Credentials !");
                    }
                }
                else{
                        toastMessage("! User NOT found plz Register !");
                }
            }
        });

    }
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

}
