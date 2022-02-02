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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class FacultyProfile extends NavigationDrawer implements View.OnClickListener {

    Button btn_next_personal,btn_cancel_personal,btn_next,btn_prev,btn_contact_cancel,btn_contact_prev,btn_cancel;
    Button btn_update,btn_edit_cancel;
    RelativeLayout rPersonal,rJob,rContact,rEducation,rButton,rDepartemnt,rProfile,rHideDepartment;
    TextView txt_id,txt_fname,txt_lname,txt_dob,txt_gender;
    TextView txt_joined_date,txt_expirience,txt_status;
    TextView txt_university,txt_degree,txt_passed_year,txt_percentage;
    TextView txt_email,txt_ph,txt_address;
    TextView lbl_edit,lbl_delete;
    TextView lbl_edit_department;
    DrawerLayout drawerLayout;
    ScrollView rEdit;
    RadioGroup radioGroupStatus;
    RadioButton radioBtnActive,radioBtnInActive,radioButtonStatus;
    EditText txt_edit_expirience,txt_edit_address,txt_edit_mobile,txt_edit_mail;
    ArrayAdapter arrayAdapter,arrayAdapter_department;
    String[] category={"Academic","Non Academic"};
    String[] dept={" ","AERO","CSE","ECE","EEE","IT","CIVIL","MECH"};
    String[] academic = {"HOD","Professor","Assistant.Professor","Principal"};
    String[] non_academic={"Admin","Clerk","Lab Assistant","Librarian","Office Assistant","Cashier"};
    Spinner spinner_designation,spinner_department,spinner_type;
    String status;
    String userMail;

    TextView txt_designation,txt_department,txt_job_type;

    DBHandler db;
    Cursor cursor;


    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_faculty_profile);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_faculty_profile, null, false);
        drawerLayout.addView(contentView, 0);


        txt_designation = (TextView) findViewById(R.id.txt_designation);
        txt_job_type = (TextView) findViewById(R.id.txt_job_type);
        txt_department = (TextView) findViewById(R.id.txt_department);

        txt_id = (TextView) findViewById(R.id.txt_id);
        txt_fname = (TextView) findViewById(R.id.txt_fname);
        txt_lname = (TextView) findViewById(R.id.txt_lname);
        txt_dob = (TextView) findViewById(R.id.txt_dob);
        txt_gender = (TextView) findViewById(R.id.txt_gender);


        lbl_edit = (TextView) findViewById(R.id.lbl_edit);
        lbl_edit.setOnClickListener(this);

        lbl_delete = (TextView) findViewById(R.id.lbl_delete);
        lbl_delete.setOnClickListener(this);

        lbl_edit_department = (TextView) findViewById(R.id.lbl_edit_department);

        txt_university = (TextView) findViewById(R.id.txt_university);
        txt_degree = (TextView) findViewById(R.id.txt_degree);
        txt_passed_year = (TextView) findViewById(R.id.txt_passed_year);
        txt_percentage = (TextView) findViewById(R.id.txt_percentage);

        txt_email = (TextView) findViewById(R.id.txt_mail);
        txt_ph = (TextView) findViewById(R.id.txt_mobile);
        txt_address = (TextView) findViewById(R.id.txt_address);
        txt_status = (TextView) findViewById(R.id.txt_status);


        txt_joined_date = (TextView) findViewById(R.id.txt_joined_date);
        txt_expirience = (TextView) findViewById(R.id.txt_expirience);

        btn_next_personal = (Button) findViewById(R.id.btn_next_personal);
        btn_next_personal.setOnClickListener(this);

        btn_cancel_personal = (Button) findViewById(R.id.btn_cancel_personal);
        btn_cancel_personal.setOnClickListener(this);

        btn_contact_prev = (Button) findViewById(R.id.btn_contact_prev);
        btn_contact_prev.setOnClickListener(this);

        btn_contact_cancel = (Button) findViewById(R.id.btn_contact_cancel);
        btn_contact_cancel.setOnClickListener(this);

        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(this);

        btn_edit_cancel = (Button) findViewById(R.id.btn_edit_cancel);
        btn_edit_cancel.setOnClickListener(this);

        btn_update = (Button) findViewById(R.id.btn_update);
        btn_update.setOnClickListener(this);

        btn_next = (Button) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);

        btn_prev = (Button) findViewById(R.id.btn_prev);
        btn_prev.setOnClickListener(this);

        txt_edit_mail=(EditText)findViewById(R.id.txt_edit_mail);
        txt_edit_mobile=(EditText)findViewById(R.id.txt_edit_mobile);
        txt_edit_address=(EditText)findViewById(R.id.txt_edit_address);
        txt_edit_expirience=(EditText)findViewById(R.id.txt_edit_expirience);

        radioBtnActive=(RadioButton)findViewById(R.id.radio_btn_active);
        radioBtnInActive=(RadioButton)findViewById(R.id.radio_btn_inactive);

        rContact = (RelativeLayout) findViewById(R.id.rlayout_contact);
        rPersonal = (RelativeLayout) findViewById(R.id.rlayout_personal);
        rEducation = (RelativeLayout) findViewById(R.id.rlayout_educational);
        rJob = (RelativeLayout) findViewById(R.id.rlayout_job);
        rProfile = (RelativeLayout) findViewById(R.id.rlayout_profile);
        rHideDepartment = (RelativeLayout) findViewById(R.id.rlayout_hide_department);
        RelativeLayout.LayoutParams hideDept = (RelativeLayout.LayoutParams) rHideDepartment.getLayoutParams();
        rButton = (RelativeLayout) findViewById(R.id.rlayout_buttons);

        rDepartemnt = (RelativeLayout) findViewById(R.id.rlayout_department);
        RelativeLayout.LayoutParams showDept = (RelativeLayout.LayoutParams) rDepartemnt.getLayoutParams();

        rEdit = (ScrollView) findViewById(R.id.scrollView_edit);
        db = new DBHandler(FacultyProfile.this);
        //DataClass dataclass = (DataClass) getApplicationContext();
        Intent i = getIntent();
        userMail=i.getStringExtra("Mail");
        cursor = db.viewFaculty(userMail);
        personlaProfile(cursor);

        spinner_type = (Spinner) findViewById(R.id.spinner_job_type);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, category);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_type.setAdapter(arrayAdapter);
        spinner_type.setSelection(arrayAdapter.getPosition(cursor.getString(cursor.getColumnIndex("job_type"))));
        txt_edit_expirience.setText(cursor.getString(cursor.getColumnIndex("expirience")));
        txt_edit_mail.setText(cursor.getString(cursor.getColumnIndex("mail")));
        txt_edit_mobile.setText(cursor.getString(cursor.getColumnIndex("ph")));
        txt_edit_address.setText(cursor.getString(cursor.getColumnIndex("address")));
        status = cursor.getString(cursor.getColumnIndex("status"));
        if(status.equals("Active"))
        {
            radioBtnActive.setChecked(true);
        }
        else
        {
            radioBtnInActive.setChecked(true);
        }

        spinner_designation = (Spinner) findViewById(R.id.spinner_designation);

        spinner_department = (Spinner) findViewById(R.id.spinner_department);

        spinner_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = parent.getItemAtPosition(position).toString();
                switch (selected) {

                    case "Academic":
                        arrayAdapter = new ArrayAdapter(FacultyProfile.this, android.R.layout.simple_spinner_item, academic);
                        arrayAdapter_department = new ArrayAdapter(FacultyProfile.this, android.R.layout.simple_spinner_item, dept);
                        lbl_edit_department.setVisibility(View.VISIBLE);
                        spinner_department.setVisibility(View.VISIBLE);
                        hideDept.setMargins(0, 50, 0, 0);
                        rDepartemnt.setLayoutParams(showDept);
                        arrayAdapter_department.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_department.setAdapter(arrayAdapter_department);
                        spinner_department.setSelection(arrayAdapter_department.getPosition(cursor.getString(cursor.getColumnIndex("department"))));
                        break;
                    case "Non Academic":
                        arrayAdapter = new ArrayAdapter(FacultyProfile.this, android.R.layout.simple_spinner_item, non_academic);
                        arrayAdapter_department = new ArrayAdapter(FacultyProfile.this, android.R.layout.simple_spinner_item, dept);
                        lbl_edit_department.setVisibility(View.GONE);
                        arrayAdapter_department.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_department.setAdapter(arrayAdapter_department);
                        spinner_department.setVisibility(View.GONE);
                        hideDept.setMargins(0, -100, 0, 0);
                       rHideDepartment.setLayoutParams(hideDept);
                        break;
                }
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_designation.setAdapter(arrayAdapter);
                spinner_designation.setSelection(arrayAdapter.getPosition(cursor.getString(cursor.getColumnIndex("designation"))));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

            @SuppressLint("Range")
            private void personlaProfile(Cursor cursor) {
                txt_id.setText(cursor.getString(cursor.getColumnIndex("empId")));
                txt_fname.setText(cursor.getString(cursor.getColumnIndex("firstName")));
                txt_lname.setText(cursor.getString(cursor.getColumnIndex("lastName")));
                txt_dob.setText(cursor.getString(cursor.getColumnIndex("dob")));
                txt_gender.setText(cursor.getString(cursor.getColumnIndex("gender")));

            }

            @SuppressLint("Range")
            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.btn_next:
                        if (rJob.getVisibility() == View.VISIBLE) {
                            rJob.setVisibility(View.GONE);
                            rEducation.setVisibility(View.VISIBLE);
                            educationProfile(cursor);
                        } else if (rEducation.getVisibility() == View.VISIBLE) {
                            rEducation.setVisibility(View.GONE);
                            rContact.setVisibility(View.VISIBLE);
                            rButton.setVisibility(View.GONE);
                            contactProfile(cursor);
                        }
                        break;
                    case R.id.btn_prev:
                        if (rJob.getVisibility() == View.VISIBLE) {
                            rPersonal.setVisibility(View.VISIBLE);
                            rJob.setVisibility(View.GONE);
                            rButton.setVisibility(View.GONE);
                        } else if (rEducation.getVisibility() == View.VISIBLE) {
                            rJob.setVisibility(View.VISIBLE);
                            rEducation.setVisibility(View.GONE);
                        }
                        break;
                    case R.id.btn_cancel:
                        if (rJob.getVisibility() == View.VISIBLE) {
                            rPersonal.setVisibility(View.VISIBLE);
                            rJob.setVisibility(View.GONE);
                            rButton.setVisibility(View.GONE);
                        } else if (rEducation.getVisibility() == View.VISIBLE) {
                            rPersonal.setVisibility(View.VISIBLE);
                            rEducation.setVisibility(View.GONE);
                            rButton.setVisibility(View.GONE);
                        }
                        break;
                    case R.id.btn_next_personal:
                        rPersonal.setVisibility(View.GONE);
                        rJob.setVisibility(View.VISIBLE);
                        rButton.setVisibility(View.VISIBLE);
                        jobProfile(cursor);
                        break;
                    case R.id.btn_cancel_personal:

                  /*    Intent i = new Intent(getApplicationContext(), ProfilePage.class);
                        startActivity(i);
                        break;*/
                        super.onBackPressed();
                        break;

                    case R.id.btn_update:
                        radioGroupStatus=(RadioGroup)findViewById(R.id.radio_type_status);
                        int SelectedId=radioGroupStatus.getCheckedRadioButtonId();
                        radioButtonStatus=(RadioButton)findViewById(SelectedId);
                        boolean res=db.updateFaculty(userMail,txt_edit_mail.getText().toString(),txt_edit_mobile.getText().toString(),txt_edit_address.getText().toString(),
                        Integer.parseInt(txt_edit_expirience.getText().toString()),radioButtonStatus.getText().toString(),spinner_type.getSelectedItem().toString()
                        ,spinner_designation.getSelectedItem().toString(),spinner_department.getSelectedItem().toString());
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
               rEducation.setVisibility(View.VISIBLE);
               rButton.setVisibility(View.VISIBLE);
               break;
           case R.id.lbl_edit:
               rEdit.setVisibility(View.VISIBLE);
               rProfile.setVisibility(View.GONE);
               break;
           case R.id.btn_edit_cancel:
               rEdit.setVisibility(View.GONE);
               rProfile.setVisibility(View.VISIBLE);
               break;
                    case R.id.lbl_delete:
                        boolean result=db.deleteFaculty(userMail);
                        if(result==true)
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
            private void contactProfile(Cursor cursor) {
                txt_email.setText(cursor.getString(cursor.getColumnIndex("mail")));
                txt_ph.setText(cursor.getString(cursor.getColumnIndex("ph")));
                txt_address.setText(cursor.getString(cursor.getColumnIndex("address")));
            }

            @SuppressLint("Range")
            private void educationProfile(Cursor cursor) {
                txt_university.setText(cursor.getString(cursor.getColumnIndex("university")));
                txt_degree.setText(cursor.getString(cursor.getColumnIndex("degree")));
                txt_percentage.setText(cursor.getString(cursor.getColumnIndex("completed_year")));
                txt_passed_year.setText(cursor.getString(cursor.getColumnIndex("percentage")));
            }

            @SuppressLint("Range")
            private void jobProfile(Cursor cursor) {
                txt_joined_date.setText(cursor.getString(cursor.getColumnIndex("date_joined")));
                txt_expirience.setText(cursor.getString(cursor.getColumnIndex("expirience")));
                txt_designation.setText(cursor.getString(cursor.getColumnIndex("designation")));
                txt_department.setText(cursor.getString(cursor.getColumnIndex("department")));
                txt_job_type.setText(cursor.getString(cursor.getColumnIndex("job_type")));
                txt_status.setText(cursor.getString(cursor.getColumnIndex("status")));

            }
        }