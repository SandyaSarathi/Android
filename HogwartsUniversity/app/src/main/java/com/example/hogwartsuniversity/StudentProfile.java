package com.example.hogwartsuniversity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class StudentProfile extends NavigationDrawer implements View.OnClickListener {

    Button btn_next_personal,btn_cancel_personal,btn_next,btn_prev,btn_contact_cancel,btn_contact_prev,btn_cancel;
    Button btn_update,btn_edit_cancel;
    RelativeLayout rPersonal,rCourse,rParent,rContact,rEducation,rButton,rProfile;
    TextView txt_dob;
    TextView txt_percent_ssc,txt_ssc_school,txt_ssc_completed_year,txt_percent_hsc,txt_hsc_school,txt_hsc_completed_year;
    TextView txt_mail,txt_mobile,txt_parent_address;
    TextView txt_email,txt_ph,txt_address,txt_status;
    TextView lbl_gender,lbl_course,lbl_department,lbl_admission;
    TextView lbl_edit,lbl_delete;
    EditText txt_parent_mobile,txt_edit_address,txt_edit_mobile,txt_edit_mail;
    DBHandler db;
    Spinner spinner_course,spinner_department;
    ArrayAdapter arrayAdapter;
    ScrollView rEdit;
    RadioGroup radioGroup;
    RadioButton radioBtnActive,radioBtnInActive,radioButton;
    TextView txt_parent_fname,txt_parent_lname,txt_id,txt_fname,txt_lname;
    Cursor cursor;
    String status;
    String[] dept={" ","AERO","CSE","ECE","EEE","IT","CIVIL","MECH"};
    String[] courses = {"B.E","B.Tech","B.Arch","M.E","M.Tech"};
    DrawerLayout drawerLayout;
    String userMail;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_student_profile);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_student_profile, null, false);
        drawerLayout.addView(contentView, 0);

        btn_next_personal = (Button) findViewById(R.id.btn_next_personal);
        btn_next_personal.setOnClickListener(this);

        btn_cancel_personal = (Button) findViewById(R.id.btn_cancel_personal);
        btn_cancel_personal.setOnClickListener(this);

        btn_contact_prev = (Button) findViewById(R.id.btn_contact_prev);
        btn_contact_prev.setOnClickListener(this);

        btn_prev = (Button) findViewById(R.id.btn_prev);
        btn_prev.setOnClickListener(this);

        btn_next = (Button) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);

        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(this);

        btn_edit_cancel = (Button) findViewById(R.id.btn_edit_cancel);
        btn_edit_cancel.setOnClickListener(this);

        btn_update = (Button) findViewById(R.id.btn_update);
        btn_update.setOnClickListener(this);

        btn_contact_cancel = (Button) findViewById(R.id.btn_contact_cancel);
        btn_contact_cancel.setOnClickListener(this);

        txt_fname = (TextView) findViewById(R.id.txt_fname);
        txt_id = (TextView) findViewById(R.id.txt_id);
        txt_dob = (TextView) findViewById(R.id.txt_dob);
        txt_lname = (TextView) findViewById(R.id.txt_lname);

        txt_percent_ssc = (TextView) findViewById(R.id.txt_percent_ssc);
        txt_ssc_school = (TextView) findViewById(R.id.txt_ssc_school);
        txt_ssc_completed_year = (TextView) findViewById(R.id.txt_ssc_completed_year);
        txt_percent_hsc = (TextView) findViewById(R.id.txt_percent_hsc);
        txt_hsc_school = (TextView) findViewById(R.id.txt_hsc_school);
        txt_hsc_completed_year = (TextView) findViewById(R.id.txt_hsc_completed_year);


        txt_parent_fname = (TextView) findViewById(R.id.txt_parent_fname);
        txt_parent_lname = (TextView) findViewById(R.id.txt_parent_lname);
        txt_mail = (TextView) findViewById(R.id.txt_mail);
        txt_mobile = (TextView) findViewById(R.id.txt_mobile);
        txt_parent_address = (TextView) findViewById(R.id.txt_parent_address);

        txt_email = (TextView) findViewById(R.id.txt_email);
        txt_ph = (TextView) findViewById(R.id.txt_ph);
        txt_status = (TextView)findViewById(R.id.txt_status);
        txt_address = (TextView) findViewById(R.id.txt_address);

        lbl_delete = (TextView) findViewById(R.id.lbl_delete);
        lbl_delete.setOnClickListener(this);

        lbl_gender=(TextView)findViewById(R.id.txt_gender);
        lbl_course=(TextView)findViewById(R.id.txt_course);
        lbl_department=(TextView)findViewById(R.id.txt_department);
        lbl_admission=(TextView)findViewById(R.id.lbl_admission);
        lbl_edit=(TextView)findViewById(R.id.lbl_edit);
        lbl_edit.setOnClickListener(this);

        txt_edit_mail=(EditText)findViewById(R.id.txt_edit_mail);
        txt_edit_mobile=(EditText)findViewById(R.id.txt_edit_mobile);
        txt_edit_address=(EditText)findViewById(R.id.txt_edit_address);
        txt_parent_mobile=(EditText)findViewById(R.id.txt_parent_mobile);

        rCourse =(RelativeLayout) findViewById(R.id.rlayout_course);
        rPersonal =(RelativeLayout) findViewById(R.id.rlayout_personal);
        rEducation =(RelativeLayout) findViewById(R.id.rlayout_educational);
        rParent =(RelativeLayout) findViewById(R.id.rlayout_parent);
        rContact =(RelativeLayout) findViewById(R.id.rlayout_contact);
        rProfile = (RelativeLayout) findViewById(R.id.rlayout_profile);
        rButton =(RelativeLayout) findViewById(R.id.rlayout_buttons);

        rEdit = (ScrollView) findViewById(R.id.scrollView_edit);

        radioBtnActive=(RadioButton)findViewById(R.id.radio_btn_active);
        radioBtnInActive=(RadioButton)findViewById(R.id.radio_btn_inactive);

        db = new DBHandler(StudentProfile.this);
        DataClass dataclass = (DataClass) getApplicationContext();
        Intent i = getIntent();
        userMail=i.getStringExtra("Mail");
        cursor=db.viewStudent(userMail);
        personalProfile(cursor);

        status = cursor.getString(cursor.getColumnIndex("status"));
        if(status.equals("Active"))
        {
            radioBtnActive.setChecked(true);
        }
        else
        {
            radioBtnInActive.setChecked(true);
        }


        spinner_course = (Spinner) findViewById(R.id.spinner_course);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,courses);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_course.setAdapter(arrayAdapter);
        spinner_course.setSelection(arrayAdapter.getPosition(cursor.getString(cursor.getColumnIndex("course"))));

        spinner_department = (Spinner) findViewById(R.id.spinner_department);
        arrayAdapter = new ArrayAdapter(StudentProfile.this, android.R.layout.simple_spinner_item, dept);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_department.setAdapter(arrayAdapter);
        spinner_department.setSelection(arrayAdapter.getPosition(cursor.getString(cursor.getColumnIndex("department"))));

        txt_parent_mobile.setText(cursor.getString(cursor.getColumnIndex("parent_ph")));
        txt_edit_mail.setText(cursor.getString(cursor.getColumnIndex("mail")));
        txt_edit_mobile.setText(cursor.getString(cursor.getColumnIndex("ph")));
        txt_edit_address.setText(cursor.getString(cursor.getColumnIndex("address")));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_next:
                if(rCourse.getVisibility()==View.VISIBLE)
                {
                    rCourse.setVisibility(View.GONE);
                    rEducation.setVisibility(View.VISIBLE);
                    educationalProfile(cursor);
                }
                else if(rEducation.getVisibility()==View.VISIBLE)
                {
                    rEducation.setVisibility(View.GONE);
                    rParent.setVisibility(View.VISIBLE);
                    parentProfile(cursor);
                }
                else if(rParent.getVisibility()==View.VISIBLE)
                {
                    rParent.setVisibility(View.GONE);
                    rContact.setVisibility(View.VISIBLE);
                    contactProfile(cursor);
                    rButton.setVisibility(View.GONE);
                }
                else if(rContact.getVisibility()==View.VISIBLE)
                {
                    rContact.setVisibility(View.GONE);
                    rPersonal.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.btn_prev:
                if(rCourse.getVisibility()==View.VISIBLE)
                {
                    rPersonal.setVisibility(View.VISIBLE);
                    rCourse.setVisibility(View.GONE);
                    rButton.setVisibility(View.GONE);
                }
                else if(rEducation.getVisibility()==View.VISIBLE)
                {
                    rCourse.setVisibility(View.VISIBLE);
                    rEducation.setVisibility(View.GONE);
                }
                else if(rParent.getVisibility()==View.VISIBLE)
                {
                    rEducation.setVisibility(View.VISIBLE);
                    rParent.setVisibility(View.GONE);
                }
                break;
            case R.id.btn_cancel:
                if(rCourse.getVisibility()==View.VISIBLE)
                {
                    rPersonal.setVisibility(View.VISIBLE);
                    rCourse.setVisibility(View.GONE);
                    rButton.setVisibility(View.GONE);
                }
                else if(rEducation.getVisibility()==View.VISIBLE)
                {
                    rPersonal.setVisibility(View.VISIBLE);
                    rEducation.setVisibility(View.GONE);
                    rButton.setVisibility(View.GONE);
                }
                else if(rParent.getVisibility()==View.VISIBLE)
                {
                    rPersonal.setVisibility(View.VISIBLE);
                    rParent.setVisibility(View.GONE);
                    rButton.setVisibility(View.GONE);
                }
                break;
            case R.id.btn_next_personal:
                rPersonal.setVisibility(View.GONE);
                rCourse.setVisibility(View.VISIBLE);
                courseProfile(cursor);
                rButton.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_cancel_personal:
                Intent i = new Intent(this,ProfilePage.class);
                startActivity(i);
                break;
            case R.id.lbl_edit:
                rEdit.setVisibility(View.VISIBLE);
                rProfile.setVisibility(View.GONE);
                break;
            case R.id.btn_edit_cancel:
                rEdit.setVisibility(View.GONE);
                rProfile.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_update:
                radioGroup=(RadioGroup)findViewById(R.id.radio_type_status);
                int SelectedId=radioGroup.getCheckedRadioButtonId();
                radioButton=(RadioButton)findViewById(SelectedId);
                boolean res=db.updateStudent(userMail,radioButton.getText().toString(),spinner_course.getSelectedItem().toString()
                        ,spinner_department.getSelectedItem().toString(),txt_edit_mail.getText().toString(),txt_edit_mobile.getText().toString(),txt_edit_address.getText().toString(),txt_parent_mobile.getText().toString());
                if(res==false)
                {
                    Toast.makeText(this,"Update Failed",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(this,"Updated Successfully",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn_contact_cancel:
                rPersonal.setVisibility(View.VISIBLE);
                rContact.setVisibility(View.GONE);
                break;
            case R.id.btn_contact_prev:
                rContact.setVisibility(View.GONE);
                rParent.setVisibility(View.VISIBLE);
                rButton.setVisibility(View.VISIBLE);
                break;
            case R.id.lbl_delete:
                boolean check=db.deleteStudent(userMail);
                if(check==true)
                {
                    Toast.makeText(this,"Deleted Successfully",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(this,"Delete Failed",Toast.LENGTH_LONG).show();
                }
                Intent delete = new Intent(this,ProfilePage.class);
                startActivity(delete);
                break;
        }
    }

    @SuppressLint("Range")
    private void courseProfile(Cursor cursor) {
        lbl_course.setText(cursor.getString(cursor.getColumnIndex("course")));
        lbl_department.setText(cursor.getString(cursor.getColumnIndex("department")));
        lbl_admission.setText(cursor.getString(cursor.getColumnIndex("admission")));
        txt_status.setText(cursor.getString(cursor.getColumnIndex("status")));

    }

    @SuppressLint("Range")
    private void contactProfile(Cursor cursor) {
        txt_email.setText(cursor.getString(cursor.getColumnIndex("mail")));
        txt_ph.setText(cursor.getString(cursor.getColumnIndex("ph")));
        txt_address.setText(cursor.getString(cursor.getColumnIndex("address")));
    }

    @SuppressLint("Range")
    private void parentProfile(Cursor cursor) {
        txt_parent_fname.setText(cursor.getString(cursor.getColumnIndex("parent_fname")));
        txt_parent_lname.setText(cursor.getString(cursor.getColumnIndex("parent_lname")));
        txt_mail.setText(cursor.getString(cursor.getColumnIndex("parent_mail")));
        txt_mobile.setText(cursor.getString(cursor.getColumnIndex("parent_ph")));
        txt_parent_address.setText(cursor.getString(cursor.getColumnIndex("parent_address")));
    }

    @SuppressLint("Range")
    private void educationalProfile(Cursor cursor) {
        txt_percent_ssc.setText(cursor.getString(cursor.getColumnIndex("percent_ssc")));
        txt_ssc_school.setText(cursor.getString(cursor.getColumnIndex("ssc_school")));
        txt_hsc_school.setText(cursor.getString(cursor.getColumnIndex("hsc_school")));
        txt_percent_hsc.setText(cursor.getString(cursor.getColumnIndex("percent_hsc")));
        txt_ssc_completed_year.setText(cursor.getString(cursor.getColumnIndex("ssc_completed")));
        txt_hsc_completed_year.setText(cursor.getString(cursor.getColumnIndex("hsc_completed")));
    }

    @SuppressLint("Range")
    private void personalProfile(Cursor cursor) {
        txt_id.setText(cursor.getString(cursor.getColumnIndex("id")));
        txt_fname.setText(cursor.getString(cursor.getColumnIndex("firstName")));
        txt_lname.setText(cursor.getString(cursor.getColumnIndex("lastName")));
        txt_dob.setText(cursor.getString(cursor.getColumnIndex("dob")));
        lbl_gender.setText(cursor.getString(cursor.getColumnIndex("gender")));

    }
}


