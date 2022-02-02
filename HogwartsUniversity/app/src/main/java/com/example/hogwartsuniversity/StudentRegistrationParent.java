package com.example.hogwartsuniversity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentRegistrationParent extends AppCompatActivity implements View.OnClickListener{
    Button btn_next,btn_prev,btn_cancel;
    EditText txt_fname,txt_lname,txt_mail,txt_mobile,txt_address;
    int check=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration_parent);

        txt_fname =(EditText) findViewById(R.id.txt_fname);
        txt_lname =(EditText) findViewById(R.id.txt_lname);
        txt_mail =(EditText) findViewById(R.id.txt_mail);
        txt_mobile =(EditText) findViewById(R.id.txt_mobile);
        txt_address =(EditText) findViewById(R.id.txt_address);


        btn_next =(Button) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);

        btn_prev =(Button) findViewById(R.id.btn_prev);
        btn_prev.setOnClickListener(this);

        btn_cancel =(Button) findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_next:
                check=validation();
                if(check==1)
                {
                    DataClass data = (DataClass) getApplicationContext();
                    data.setParent_fname(txt_fname.getText().toString());
                    data.setParent_lname(txt_lname.getText().toString());
                    data.setParent_ph(txt_mobile.getText().toString());
                    data.setParent_mail(txt_mail.getText().toString());
                    data.setParent_address(txt_address.getText().toString());
                    Intent next = new Intent(this,StudentRegistrationContact.class);
                    startActivity(next);
                }
                break;
            case R.id.btn_prev:
                Intent prev = new Intent(this,StudentRegistrationEducation.class);
                startActivity(prev);
                break;
            case R.id.btn_cancel:
                Intent cancel = new Intent(this,StudentRegistration.class);
                startActivity(cancel);
                break;
        }
    }

    private int validation() {
        if(txt_fname.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Enter first name",Toast.LENGTH_LONG).show();
        }
        else if(txt_lname.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Enter last name",Toast.LENGTH_LONG).show();
        }
        else if(txt_mail.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Enter mail id",Toast.LENGTH_LONG).show();
        }
        else if(!isValidEmail(txt_mail.getText().toString()))
        {
            Toast.makeText(this,"Enter valid email id",Toast.LENGTH_LONG).show();

        }
        else if(txt_mobile.getText().toString().length()<10)
        {
            Toast.makeText(this,"Enter valid mobile",Toast.LENGTH_LONG).show();
        }
        else if(txt_mobile.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Enter mobile",Toast.LENGTH_LONG).show();
        }
        else if(txt_address.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Enter Address",Toast.LENGTH_LONG).show();
        }
        else
        {
            return 1;
        }
        return 0;
    }

    private boolean isValidEmail(String email) {
        String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}