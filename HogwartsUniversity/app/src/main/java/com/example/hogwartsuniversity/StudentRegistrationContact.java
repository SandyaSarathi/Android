package com.example.hogwartsuniversity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentRegistrationContact extends AppCompatActivity implements View.OnClickListener {

    Button btn_prev,btn_cancel,btn_register;
    int check=1;
    EditText txt_mobile,txt_address,txt_alternate_mobile,txt_mail;
    DBHandler db;
    Date date= Calendar.getInstance().getTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration_contact);

        txt_mobile=(EditText) findViewById(R.id.txt_mobile);
        txt_mail=(EditText) findViewById(R.id.txt_mail);
        txt_address=(EditText) findViewById(R.id.txt_address);
        txt_mobile=(EditText) findViewById(R.id.txt_mobile);

        btn_prev=(Button) findViewById(R.id.btn_prev);
        btn_prev.setOnClickListener(this);

        btn_cancel=(Button) findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(this);

        btn_register=(Button) findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btn_cancel:
                Intent cancel = new Intent(this,StudentRegistration.class);
                startActivity(cancel);
                break;
            case R.id.btn_prev:
                Intent prev = new Intent(this,StudentRegistrationParent.class);
                startActivity(prev);
                break;
            case R.id.btn_register:
                check=validation();
                if(check==1)
                {
                    DataClass data = (DataClass) getApplicationContext();
                    data.setMail(txt_mail.getText().toString());
                    data.setPh(txt_mobile.getText().toString());
                    data.setAddress(txt_address.getText().toString());
                   // System.out.println("insertion "+data.getUser());
                    db = new DBHandler(StudentRegistrationContact.this);
                    db.addStudent(data.getFname(),data.getLname(),data.getGender(),data.getDob(),data.getCourse(),data.getDepartment(),data.getAdmission(),data.getStatus()
                    ,data.getPercent_ssc(),data.getSsc_school(),data.getSsc_completed(),data.getPercent_hsc(),data.getHsc_school(),data.getHsc_completed(),data.getParent_fname(),data.getParent_lname()
                    ,data.getParent_mail(),data.getParent_ph(),data.getParent_address(),data.getMail(),data.getPh(),data.getAddress(),data.getUserMail(),String.valueOf(date));
                    Toast.makeText(this,"Inserted Successfully",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(this,StudentRegistration.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                }
                break;
        }
    }

    private int validation() {
        if(txt_mail.getText().toString().isEmpty()  )
        {
            Toast.makeText(this,"Enter email address",Toast.LENGTH_LONG).show();
        }

        else if(!isValidEmail(txt_mail.getText().toString()))
        {
            Toast.makeText(this,"Enter valid email id",Toast.LENGTH_LONG).show();

        }
        else if(txt_mobile.getText().toString().isEmpty() || txt_mobile.getText().toString().length()<10)
        {
            Toast.makeText(this,"Enter valid mobile number",Toast.LENGTH_LONG).show();
        }
        else if(txt_address.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Enter address",Toast.LENGTH_LONG).show();
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