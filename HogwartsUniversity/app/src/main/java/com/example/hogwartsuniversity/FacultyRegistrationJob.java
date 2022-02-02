package com.example.hogwartsuniversity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FacultyRegistrationJob extends AppCompatActivity implements View.OnClickListener {

    DatePickerDialog datePickerDialog;

    Button btn_next,btn_cancel,btn_prev;
    Spinner spinner_type,spinner_designation,spinner_department;
    String[] category={"Academic","Non Academic"};
    String[] academic = {"HOD","Professor","Assistant.Professor","Principal"};
    String[] non_academic={"Admin","Clerk","Lab Assistant","Librarian","Office Assistant","Cashier"};
    String[] department={" ","AERO","CSE","ECE","EEE","IT","CIVIL","MECH"};
    ArrayAdapter arrayAdapter,arrayAdapter_department;
    RelativeLayout relativeLayout;
    TextView lbl_department;
    EditText txt_joined_date,txt_expirience;
    RadioGroup radio_type_status;
    RadioButton radioButton;
    int check=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_registration_job);

        lbl_department=(TextView)findViewById(R.id.lbl_department);

        radio_type_status = (RadioGroup) findViewById(R.id.radio_type_status);

        txt_joined_date=(EditText)findViewById(R.id.txt_joined_date);
        txt_joined_date.setOnClickListener(this);
        txt_expirience=(EditText)findViewById(R.id.txt_expirience);

        spinner_type=(Spinner) findViewById(R.id.spinner_job_type);
        arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,category);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_type.setAdapter(arrayAdapter);

        spinner_designation=(Spinner) findViewById(R.id.spinner_designation);

        spinner_department=(Spinner) findViewById(R.id.spinner_department);

        relativeLayout=(RelativeLayout)findViewById(R.id.relative_layout);
        RelativeLayout.LayoutParams param = (RelativeLayout.LayoutParams) relativeLayout.getLayoutParams();

        btn_next = (Button) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);

        btn_prev = (Button) findViewById(R.id.btn_prev);
        btn_prev.setOnClickListener(this);

        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(this);

        spinner_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = parent.getItemAtPosition(position).toString();
                switch (selected) {

                    case "Academic":
                        arrayAdapter = new ArrayAdapter(FacultyRegistrationJob.this,android.R.layout.simple_spinner_item, academic);
                        arrayAdapter_department=new ArrayAdapter(FacultyRegistrationJob.this,android.R.layout.simple_spinner_item, department);
                        lbl_department.setVisibility(View.VISIBLE);
                       spinner_department.setVisibility(View.VISIBLE);
                        param.setMargins(0,1350,0,0);
                        relativeLayout.setLayoutParams(param);
                        break;
                    case "Non Academic":
                        arrayAdapter = new ArrayAdapter(FacultyRegistrationJob.this, android.R.layout.simple_spinner_item, non_academic);
                        lbl_department.setVisibility(View.GONE);
                        spinner_department.setVisibility(View.GONE);
                        param.setMargins(0,1250,0,0);
                        relativeLayout.setLayoutParams(param);
                        break;
                }
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_designation.setAdapter(arrayAdapter);
                arrayAdapter_department.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_department.setAdapter(arrayAdapter_department);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_next:
                check=validation();
                int selectedId=radio_type_status.getCheckedRadioButtonId();
                radioButton=(RadioButton) findViewById(selectedId);
                if(check==1)
                {
                    Intent next = new Intent(this,FacultyRegistrationEducation.class);
                    DataClass data=(DataClass) getApplicationContext();
                    data.setStatus(radioButton.getText().toString());
                    data.setJob_type(spinner_type.getSelectedItem().toString());
                    data.setDesignation(spinner_designation.getSelectedItem().toString());
                    data.setDepartment(spinner_department.getSelectedItem().toString());
                    data.setJoining_date(txt_joined_date.getText().toString());
                    data.setExpirience(Integer.parseInt(txt_expirience.getText().toString()));
                    startActivity(next);
                }
                break;
            case R.id.btn_prev:
                super.onBackPressed();
              /*  Intent prev = new Intent(this,FacultyRegistrationPersonal.class);
                startActivity(prev);
                break;*/
            case R.id.btn_cancel:
                Intent cancel = new Intent(this,FacultyRegistrationPersonal.class);
                startActivity(cancel);
                break;
            case R.id.txt_joined_date:
                datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txt_joined_date.setText(String.valueOf(month+1)+"/"+String.valueOf(dayOfMonth)+"/"+String.valueOf(year));
                    }
                },2020, 0, 1);
                datePickerDialog.show();
                break;
        }
    }

    private int validation() {
        if(txt_joined_date.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Enter date joined",Toast.LENGTH_LONG).show();
        }
        else if(txt_expirience.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Enter expirience in years",Toast.LENGTH_LONG).show();
        }
        else
        {
            return 1;
        }
        return 0;
    }
}