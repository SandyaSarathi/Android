package com.example.hogwartsuniversity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginPage extends AppCompatActivity implements View.OnClickListener {
    TextView lbl_forgot_pwd;
    EditText txt_user_mail,txt_password,txt_mail,txt_new_password,txt_confirm_password;
    Button btn_login,btn_submit,btn_change_pwd;
    RelativeLayout rlayout_password,rlayout_user_id,rlayout_login,rlayout_forgot_pwd;
    DBHandler db;
    Cursor cursor;
    String user,password;
    DataClass data;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        lbl_forgot_pwd = (TextView) findViewById(R.id.lbl_reset_pwd);
        lbl_forgot_pwd.setOnClickListener(this);

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);

        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);

        btn_change_pwd = (Button) findViewById(R.id.btn_change_pwd);
        btn_change_pwd.setOnClickListener(this);

        rlayout_login=(RelativeLayout)findViewById(R.id.rlayout_login);
        rlayout_user_id=(RelativeLayout)findViewById(R.id.rlayout_user_mail);
        rlayout_password=(RelativeLayout)findViewById(R.id.rlayout_password);
        rlayout_forgot_pwd=(RelativeLayout)findViewById(R.id.rlayout_reset_pwd);

        txt_user_mail=(EditText) findViewById(R.id.txt_user_mail);
        txt_password=(EditText) findViewById(R.id.txt_password);
        txt_new_password=(EditText) findViewById(R.id.txt_new_password);
        txt_confirm_password=(EditText) findViewById(R.id.txt_confirm_password);
        txt_mail=(EditText) findViewById(R.id.txt_mail);

        db=new DBHandler(this);
        data = (DataClass) getApplicationContext();


    }
    @SuppressLint("Range")
    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.lbl_reset_pwd:
                rlayout_forgot_pwd.setVisibility(View.VISIBLE);
                rlayout_login.setVisibility(View.GONE);
                break;

            case R.id.btn_login:
                try {
                    cursor=db.login(txt_user_mail.getText().toString());
                    user = cursor.getString(cursor.getColumnIndex("mail"));
                    password = cursor.getString(cursor.getColumnIndex("password"));
                    if(!user.equals(txt_user_mail.getText().toString()) && !password.equals(txt_password.getText().toString()))
                    {
                        Toast.makeText(this,"Invalid User id or Password",Toast.LENGTH_LONG).show();

                    }
                    else
                    {
                        data.setUserMail(cursor.getString(cursor.getColumnIndex("mail")));
                        if(cursor.getString(cursor.getColumnIndex("Type")).equals("Student"))
                        {
                            Intent i2= new Intent(this,HomePage.class);
                            i2.putExtra("Name",cursor.getString(cursor.getColumnIndex("name")));
                            startActivity(i2);
                        }
                        else
                        {
                            Intent i = new Intent(this,DashBoard.class);
                            i.putExtra("Name",cursor.getString(cursor.getColumnIndex("name")));
                            startActivity(i);
                        }

                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(this,"No User id or Password found",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn_submit:
                try {
                    cursor = db.login(txt_mail.getText().toString());
                    user = cursor.getString(cursor.getColumnIndex("mail"));
                    rlayout_forgot_pwd.setVisibility(View.VISIBLE);
                    rlayout_user_id.setVisibility(View.GONE);
                    rlayout_password.setVisibility(View.VISIBLE);
                }
                catch(Exception e)
                {
                    Toast.makeText(this,"Invalid Mail Id",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn_change_pwd:
                boolean validator=validation();
                if(validator==true) {
                    boolean check = db.updatePassword(txt_mail.getText().toString(), txt_new_password.getText().toString());
                    if (check == true) {
                        Toast.makeText(this, "Password updated successfully", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(this, " Password update failed", Toast.LENGTH_LONG).show();

                    }
                    rlayout_forgot_pwd.setVisibility(View.GONE);
                    rlayout_login.setVisibility(View.VISIBLE);
                }
                break;

        }
    }

    private boolean validation() {
        if(!txt_new_password.getText().toString().equals(txt_confirm_password.getText().toString()))
        {
            Toast.makeText(this,"Confirm password doesn't match!!",Toast.LENGTH_LONG).show();

        }
        else if(txt_new_password.getText().toString().length()<8)
        {
            Toast.makeText(this,"Password should be atleast 8 characters",Toast.LENGTH_LONG).show();


        }
        else if(!isValidPassword(txt_new_password.getText().toString()))
        {
            Toast.makeText(this,"Password should contain atleast 1 upper case, 1 lower case, 1 number",Toast.LENGTH_LONG).show();

        }
        else
        {
            return true;
        }
        return false;
    }

    public static boolean isValidPassword(String password) {
        Matcher matcher = Pattern.compile("((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{4,20})").matcher(password);
        return matcher.matches();
    }
}