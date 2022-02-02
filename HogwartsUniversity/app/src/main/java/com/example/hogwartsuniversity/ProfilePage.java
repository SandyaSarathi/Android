package com.example.hogwartsuniversity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import java.util.ArrayList;

public class ProfilePage extends NavigationDrawer implements View.OnClickListener {

    Button btn_search;
    CardView fixed,expand;
    ImageView img_down_arrow,img_up_arrow;
    RelativeLayout relativeLayout_search;
    Spinner spinner_department,spinner_course;
    String[] department={"","AERO","CSE","ECE","EEE","IT","CIVIL","MECH"};
    String[] courses = {"","B.E","B.Tech","B.Arch","M.E","M.Tech"};
    String[] type = {"Student","Faculty"};
    String[] jobType={" ","Academic","Non Academic"};
    ArrayAdapter arrayAdapter;
    int s1;
    String s2,s3,s4;
    DBHandler db;
    Cursor cursor;
    Spinner spinner_type,spinner_jobtype;
    RelativeLayout tableLayout,relativeLayout_recycler;
    EditText txt_name,txt_search;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_profile_page);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_profile_page, null, false);
        drawerLayout.addView(contentView, 0);

        btn_search = (Button) findViewById(R.id.btn_search);
        btn_search.setOnClickListener(this);

        img_down_arrow = (ImageView) findViewById(R.id.img_down_arrow);
        img_down_arrow.setOnClickListener(this);

        img_up_arrow = (ImageView) findViewById(R.id.img_up_arrow);
        img_up_arrow.setOnClickListener(this);

        fixed = (CardView) findViewById(R.id.card_view_fixed);
        expand = (CardView) findViewById(R.id.card_view_expand);

        txt_name=(EditText)findViewById(R.id.txt_name);
        txt_search=(EditText)findViewById(R.id.txt_search);

        spinner_department = (Spinner) findViewById(R.id.spinner_department);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,department);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_department.setAdapter(arrayAdapter);

        spinner_course = (Spinner) findViewById(R.id.spinner_course);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,courses);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_course.setAdapter(arrayAdapter);

        spinner_type=(Spinner) findViewById(R.id.spinner_type);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,type);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_type.setAdapter(arrayAdapter);

        spinner_jobtype=(Spinner) findViewById(R.id.spinner_job_type);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,jobType);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_jobtype.setAdapter(arrayAdapter);

        tableLayout=(RelativeLayout)findViewById(R.id.table_layout);

        relativeLayout_search = (RelativeLayout) findViewById(R.id.rlayout_btn);
        relativeLayout_recycler=(RelativeLayout) findViewById(R.id.relativeLayout_recycler);
    }

    @SuppressLint("Range")
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.img_down_arrow:
                RelativeLayout.LayoutParams search_dwn = (RelativeLayout.LayoutParams) relativeLayout_search.getLayoutParams();
                expand.setVisibility(View.VISIBLE);
                img_down_arrow.setVisibility(View.GONE);
                search_dwn.setMargins(0,1400,0,0);
                RelativeLayout.LayoutParams table_layout_dwn=(RelativeLayout.LayoutParams) tableLayout.getLayoutParams();
                img_up_arrow.setVisibility(View.VISIBLE);
                table_layout_dwn.setMargins(0, 1600, 0, 0);
                RelativeLayout.LayoutParams recycler_dwn = (RelativeLayout.LayoutParams) relativeLayout_recycler.getLayoutParams();
                recycler_dwn.setMargins(0, 1800, 0, 0);
                relativeLayout_recycler.setLayoutParams(recycler_dwn);
                relativeLayout_search.setLayoutParams(search_dwn);
                tableLayout.setLayoutParams(table_layout_dwn);
                break;
            case R.id.img_up_arrow:
                RelativeLayout.LayoutParams search_up = (RelativeLayout.LayoutParams) relativeLayout_search.getLayoutParams();
                RelativeLayout.LayoutParams table_layout_up=(RelativeLayout.LayoutParams) tableLayout.getLayoutParams();
                RelativeLayout.LayoutParams recycler_up = (RelativeLayout.LayoutParams) relativeLayout_recycler.getLayoutParams();
                expand.setVisibility(View.GONE);
                img_down_arrow.setVisibility(View.VISIBLE);
                img_up_arrow.setVisibility(View.GONE);
                search_up.setMargins(0, 700, 0, 0);
                table_layout_up.setMargins(0,900,0,0);
                recycler_up.setMargins(0, 1100, 0, 0);
                relativeLayout_search.setLayoutParams(search_up);
                tableLayout.setLayoutParams(table_layout_up);
                relativeLayout_recycler.setLayoutParams(recycler_up);
                break;
            case R.id.btn_search:
                RelativeLayout.LayoutParams search = (RelativeLayout.LayoutParams) relativeLayout_search.getLayoutParams();
                RelativeLayout.LayoutParams heading =(RelativeLayout.LayoutParams) tableLayout.getLayoutParams();
                RelativeLayout.LayoutParams recycler = (RelativeLayout.LayoutParams) relativeLayout_recycler.getLayoutParams();
                expand.setVisibility(View.GONE);
                img_down_arrow.setVisibility(View.VISIBLE);
                img_up_arrow.setVisibility(View.GONE);
                heading.setMargins(0,900,0,0);
                search.setMargins(0, 700, 0, 0);
                recycler.setMargins(0, 1100, 0, 0);
                relativeLayout_search.setLayoutParams(search);
                tableLayout.setLayoutParams(heading);
                String str_department = spinner_department.getSelectedItem().toString();
                String str_course = spinner_course.getSelectedItem().toString();
                String str_name = txt_name.getText().toString();
                String str_id = txt_search.getText().toString();
                String str_type=spinner_type.getSelectedItem().toString();
                String str_job_type=spinner_jobtype.getSelectedItem().toString();
                tableLayout.setVisibility(View.VISIBLE);
                db = new DBHandler(this);
                ArrayList<DataClass> list = new ArrayList<>();
                if(spinner_type.getSelectedItem().toString().equals("Student"))
                {
                    cursor = db.fetchStudent(str_name,str_department,str_course,str_id);
                    if (cursor.moveToFirst()) {
                        do {
                            s1 = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                            s2 = cursor.getString(cursor.getColumnIndex("firstName"));
                            s3 = cursor.getString(cursor.getColumnIndex("department"));
                            s4 = cursor.getString(cursor.getColumnIndex("mail"));
                            list.add(new DataClass(s1,s2,s3,s4));
                        } while (cursor.moveToNext());
                    }
                }
                else
                {
                    cursor = db.fetchFaculty(str_name,str_department,str_id,str_job_type);
                    if (cursor.moveToFirst()) {
                        do {
                            s1 = Integer.parseInt(cursor.getString(cursor.getColumnIndex("empId")));
                            s2 = cursor.getString(cursor.getColumnIndex("firstName"));
                            s3 = cursor.getString(cursor.getColumnIndex("department"));
                            s4 = cursor.getString(cursor.getColumnIndex("mail"));
                            list.add(new DataClass(s1,s2,s3,s4));

                        } while (cursor.moveToNext());
                    }
                }
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                recyclerView.setHasFixedSize(true);
                DataAdapter adapter = new DataAdapter(list,str_type);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(adapter);
        }
    }
}
