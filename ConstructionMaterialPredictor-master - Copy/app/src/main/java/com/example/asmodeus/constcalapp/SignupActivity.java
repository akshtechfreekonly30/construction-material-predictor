package com.example.asmodeus.constcalapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    DatabaseHelper dtbHelper;
    private EditText uid;
    private EditText pas;
    private EditText confpas;
    private Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        uid=(EditText)findViewById(R.id.sgn_et_usrid);
        pas=(EditText)findViewById(R.id.sgn_et_pass);
        confpas=(EditText)findViewById(R.id.sgn_et_pass_conf);
        signup=(Button)findViewById(R.id.sgn_btn_sign);
        dtbHelper=new DatabaseHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userid=uid.getText().toString();
                String passwrd=pas.getText().toString();
                String confPasswrd=confpas.getText().toString();

                if(userid.equals("")|| passwrd.equals("")||confPasswrd.equals("")){
                    toastMessage("! Please Enter Data in ALL fields !");
                }
                else{
                    if(passwrd.equals(confPasswrd))
                    {
                        Boolean chkuid=dtbHelper.chkUser(userid);
                        if(chkuid==true){
                            Boolean insert=dtbHelper.insert(userid,passwrd);
                            if(insert==true){
                                toastMessage("User Registered Successfully");
                                Intent i =new Intent(SignupActivity.this,LoginActivity.class);
                                startActivity(i);
                            }
                            else{
                                toastMessage("! Registration Failed !");
                            }
                        }
                        else{
                                toastMessage("User already Registered");
                        }
                    }
                    else{
                        toastMessage("! Passwords don't match !");
                    }
                }

            }
        });

    }




    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

}
