package com.example.hogwartsuniversity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FacultyRegistrationEducation extends AppCompatActivity implements View.OnClickListener {

    Button btn_next,btn_prev,btn_cancel;
    EditText txt_university,txt_degree,txt_passed_year,txt_percentage;
    int check=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_registration_education);

        txt_degree=(EditText)findViewById(R.id.txt_degree);
        txt_university=(EditText)findViewById(R.id.txt_university);
        txt_passed_year=(EditText)findViewById(R.id.txt_passed_year);
        txt_percentage=(EditText)findViewById(R.id.txt_percentage);

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
                    Intent next = new Intent(this,FacultyRegistrationContact.class);
                    DataClass data = (DataClass) getApplicationContext();
                    data.setUniversity(txt_university.getText().toString());
                    data.setDegree(txt_degree.getText().toString());
                    data.setCompleted_year(txt_passed_year.getText().toString());
                    data.setPercentage_obtained(txt_percentage.getText().toString());
                    startActivity(next);
                }
                break;
            case R.id.btn_prev:
                Intent prev = new Intent(this,FacultyRegistrationJob.class);
                startActivity(prev);
                break;
            case R.id.btn_cancel:
                Intent cancel = new Intent(this,FacultyRegistrationPersonal.class);
                startActivity(cancel);
                break;
        }
    }

    private int validation() {
        if(txt_passed_year.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Enter graduated year",Toast.LENGTH_LONG).show();
        }
        else if(txt_university.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Enter university name",Toast.LENGTH_LONG).show();
        }
        else if(txt_degree.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Enter degree",Toast.LENGTH_LONG).show();
        }
        else if(txt_percentage.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Enter passed percentage",Toast.LENGTH_LONG).show();
        }
        else if(Integer.parseInt(txt_percentage.getText().toString())>100)
        {
            Toast.makeText(this,"Enter valid percentage",Toast.LENGTH_LONG).show();
        }

        else
        {
            return 1;
        }
        return 0;
    }
}