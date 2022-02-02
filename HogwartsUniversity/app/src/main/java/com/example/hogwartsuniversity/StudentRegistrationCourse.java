package com.example.hogwartsuniversity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class StudentRegistrationCourse extends AppCompatActivity implements View.OnClickListener {

    Button btn_next,btn_prev,btn_cancel;
    RadioGroup radio_type_status;
    RadioButton radio_btn;
    String[] courses = {"B.E","B.Tech","B.Arch","M.E","M.Tech"};
    Spinner spinner_course,spinner_department,spinner_admission;
    ArrayAdapter arrayAdapter_course;
    String[] admission ={"Management","Counselling"};
    String[] department={"AERO","CSE","ECE","EEE","IT","CIVIL","MECH"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration_course);

        btn_next = (Button) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);

        btn_prev = (Button) findViewById(R.id.btn_prev);
        btn_prev.setOnClickListener(this);

        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(this);

        spinner_course=(Spinner) findViewById(R.id.spinner_course);
        //spinner_course.setOnItemSelectedListener(this);
        arrayAdapter_course = new ArrayAdapter(this, android.R.layout.simple_spinner_item,courses);
        arrayAdapter_course.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_course.setAdapter(arrayAdapter_course);

        spinner_department=(Spinner) findViewById(R.id.spinner_department);
        //spinner_branch.setOnItemSelectedListener(this);
        arrayAdapter_course = new ArrayAdapter(this, android.R.layout.simple_spinner_item,department);
        arrayAdapter_course.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_department.setAdapter(arrayAdapter_course);

        spinner_admission=(Spinner) findViewById(R.id.spinner_admission);
        arrayAdapter_course = new ArrayAdapter(this, android.R.layout.simple_spinner_item,admission);
        arrayAdapter_course.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_admission.setAdapter(arrayAdapter_course);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_next:
                radio_type_status =(RadioGroup) findViewById(R.id.radio_type_status);
                int selectedId = radio_type_status.getCheckedRadioButtonId();
                radio_btn = (RadioButton) findViewById(selectedId);
                DataClass data = (DataClass) getApplicationContext();
                data.setCourse(spinner_course.getSelectedItem().toString());
                data.setDepartment(spinner_department.getSelectedItem().toString());
                data.setStatus(radio_btn.getText().toString());
                data.setAdmission(spinner_admission.getSelectedItem().toString());
                data.setDepartment(spinner_department.getSelectedItem().toString());

                Intent next = new Intent(this,StudentRegistrationEducation.class);
                startActivity(next);
                break;
            case R.id.btn_prev:
                Intent prev = new Intent(this,StudentRegistration.class);
                startActivity(prev);
                break;
            case R.id.btn_cancel:
                Intent cancel = new Intent(this,StudentRegistration.class);
                startActivity(cancel);
                break;
        }
    }
}