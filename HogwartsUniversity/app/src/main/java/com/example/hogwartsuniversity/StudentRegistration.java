package com.example.hogwartsuniversity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class StudentRegistration extends NavigationDrawer implements View.OnClickListener{

    DatePickerDialog datePickerDialog;
    EditText txt_dob,txt_fname,txt_lname;
    Button btn_next,btn_cancel;
    RadioGroup radio_gender;
    RadioButton radioButton;
    int check=1,yr;
    DrawerLayout drawerLayout;
    int current_yr = Calendar.getInstance().get(Calendar.YEAR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_student_registration);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_student_registration, null, false);
        drawerLayout.addView(contentView, 0);

        txt_dob=(EditText) findViewById(R.id.txt_dob);
        txt_dob.setOnClickListener(this);

        txt_fname=(EditText) findViewById(R.id.txt_fname);

        txt_lname=(EditText) findViewById(R.id.txt_lname);

        btn_next =(Button) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);

        btn_cancel =(Button) findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_next:
                radio_gender = (RadioGroup) findViewById(R.id.radio_gender);
                int selectedId=radio_gender.getCheckedRadioButtonId();
                radioButton=(RadioButton) findViewById(selectedId);
                check = validation();
                if(check==1)
                {
                    Intent next = new Intent(this,StudentRegistrationCourse.class);
                    DataClass data=(DataClass) getApplicationContext();
                    data.setFname(txt_fname.getText().toString());
                    data.setLname(txt_lname.getText().toString());
                    data.setDob(txt_dob.getText().toString());
                    data.setGender(radioButton.getText().toString());
                    startActivity(next);
                }
                break;
            case R.id.btn_cancel:
                Intent cancel = new Intent(this,LoginPage.class);
                startActivity(cancel);
                break;
            case R.id.txt_dob:
                datePickerDialog = new DatePickerDialog(StudentRegistration.this,new DatePickerDialog.OnDateSetListener(){

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        yr=year;
                        txt_dob.setText(String.valueOf(month + 1) + "/" + String.valueOf(dayOfMonth) + "/" + String.valueOf(year));
                    }
                },2020, 0, 1);
                datePickerDialog.show();
                break;

        }

    }

    public int validation() {

        if (txt_fname.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter first name", Toast.LENGTH_LONG).show();
        } else if (txt_lname.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter last name", Toast.LENGTH_LONG).show();
        } else if (txt_dob.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter birth date", Toast.LENGTH_LONG).show();
        }
        else if (!txt_dob.getText().toString().isEmpty() && current_yr-yr<18 ) {
            Toast.makeText(this, "Below 18 years Not eligible", Toast.LENGTH_LONG).show();
        }else {
            return 1;
        }
        return 0;
    }
}