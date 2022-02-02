package com.example.hogwartsuniversity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StudentRegistrationEducation extends AppCompatActivity implements View.OnClickListener {

    Button btn_next,btn_prev,btn_cancel;
    int check=1;
    EditText txt_percent_ssc,txt_ssc_school,txt_ssc_completed_year,txt_percent_hsc,txt_hsc_school,txt_hsc_completed_year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration_education);

        txt_hsc_school = (EditText)findViewById(R.id.txt_hsc_school);
        txt_ssc_school = (EditText)findViewById(R.id.txt_ssc_school);
        txt_percent_ssc = (EditText)findViewById(R.id.txt_percent_ssc);
        txt_percent_hsc = (EditText)findViewById(R.id.txt_percent_hsc);
        txt_ssc_completed_year = (EditText)findViewById(R.id.txt_ssc_completed_year);
        txt_hsc_completed_year = (EditText)findViewById(R.id.txt_hsc_completed_year);



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
                check = validation();
                if(check==1)
                {
                    DataClass data = (DataClass) getApplicationContext();
                    data.setPercent_ssc(txt_percent_ssc.getText().toString());
                    data.setSsc_school(txt_ssc_school.getText().toString());
                    data.setSsc_completed(txt_ssc_completed_year.getText().toString());
                    data.setPercent_hsc(txt_percent_hsc.getText().toString());
                    data.setHsc_school(txt_hsc_school.getText().toString());
                    data.setHsc_completed(txt_hsc_completed_year.getText().toString());
                    Intent next = new Intent(this,StudentRegistrationParent.class);

                    startActivity(next);
                }
                break;
            case R.id.btn_prev:
                Intent prev = new Intent(this,StudentRegistrationCourse.class);
                startActivity(prev);
                break;
            case R.id.btn_cancel:
                Intent cancel = new Intent(this,StudentRegistration.class);
                startActivity(cancel);
                break;
        }
    }
    private int validation() {
        if(txt_ssc_school.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Enter HSC school name",Toast.LENGTH_LONG).show();
        }
        else if(txt_hsc_school.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Enter HSC school name",Toast.LENGTH_LONG).show();
        }
        else if(txt_percent_ssc.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Enter SSC percentage",Toast.LENGTH_LONG).show();
        }
        else if(Integer.parseInt(txt_percent_ssc.getText().toString())>100)
        {
            Toast.makeText(this,"Enter valid percentage",Toast.LENGTH_LONG).show();
        }
        else if(txt_percent_hsc.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Enter HSC percentage",Toast.LENGTH_LONG).show();
        }
        else if(Integer.parseInt(txt_percent_hsc.getText().toString())>100)
        {
            Toast.makeText(this,"Enter valid percentage",Toast.LENGTH_LONG).show();
        }
        else if(txt_ssc_completed_year.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Enter SSC Passed year",Toast.LENGTH_LONG).show();
        }
        else if(txt_hsc_completed_year.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Enter HSC Passed year",Toast.LENGTH_LONG).show();
        }
        else
        {
            return 1;
        }
        return 0;
    }
}