package com.example.dummyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.InvalidObjectException;
import java.io.ObjectInputValidation;

public class LoginPage extends AppCompatActivity  {
    TextView lbl_forgot_pwd;
    EditText txt_user_id,txt_password;
    Button btn_login;
    DBHandler dbHandler;
    Cursor cursor;
    String name,password;
    DBData dbData;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        txt_user_id = (EditText) findViewById(R.id.txt_user_id);
        txt_password = (EditText) findViewById(R.id.txt_password);
        btn_login = (Button) findViewById(R.id.btn_login);

        dbHandler=new DBHandler(this);
      //  dbHandler.addLogin();
        dbData=(DBData) getApplicationContext();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View v) {
                /*Intent i = new Intent(v.getContext(),HomePage.class);
                startActivity(i);
                try {*/
                cursor=dbHandler.login(txt_user_id.getText().toString(),txt_password.getText().toString());
                name = cursor.getString(cursor.getColumnIndex("mail_id"));
                password = cursor.getString(cursor.getColumnIndex("password"));
                if(!name.equals(txt_user_id.getText().toString()) && !password.equals(txt_password.getText().toString()))
                {
                    Toast.makeText(LoginPage.this,"Invalid User id or Password",Toast.LENGTH_LONG).show();
                }
                else
                {
                    dbData.setUser(name);
                    Toast.makeText(LoginPage.this,"Login Working "+name,Toast.LENGTH_LONG).show();
                    Intent i = new Intent(v.getContext(),LoginPage.class);
                    startActivity(i);
                }
                Toast.makeText(LoginPage.this,"No User id or Password found",Toast.LENGTH_LONG).show();
            }
        });
        lbl_forgot_pwd = (TextView) findViewById(R.id.lbl_forgot_pwd);
        lbl_forgot_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(v.getContext(), LoginPage.class);
                startActivity(a);
            }
        });
    }
}
