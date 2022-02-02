package com.example.hogwartsuniversity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class DashBoard extends NavigationDrawer implements  AdapterView.OnItemClickListener {
    GridView gridView;
    DBHandler db;
    String strUserName;
    TextView lbl_user;
    DrawerLayout drawerLayout;
    DataClass dataclass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_dash_board, null, false);
        drawerLayout.addView(contentView, 0);


        gridView = (GridView) findViewById(R.id.grid_view);
        gridView.setOnItemClickListener(this);

        lbl_user = (TextView) findViewById(R.id.lbl_user);

        dataclass = (DataClass) getApplicationContext();
        Intent i= getIntent();
       strUserName= i.getStringExtra("Name");
        strUserName= lbl_user.getText().toString()+" "+strUserName;
        lbl_user.setText(strUserName);

        ArrayList<DataClass> data = new ArrayList<>();
        db=new DBHandler(this);

        data.add(new DataClass(Integer.parseInt(String.valueOf(db.countStudent())) ,R.drawable.ic_student_svgrepo_com,"No.Of Students"));
        data.add(new DataClass(db.countTeachers(),R.drawable.teacher,"No.Of Teaching Staff"));
        data.add(new DataClass(db.countNonTeachers(),R.drawable.staff,"No.Of Non Teaching Staff"));
        data.add(new DataClass(db.countRecords(dataclass.getUserMail()),R.drawable.ic_coordinating_people_svgrepo_com,"Registered Records"));

        GridAdapter adapterClass = new GridAdapter(this,data);
        gridView.setAdapter(adapterClass);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(position==3)
        {
            Intent i = new Intent(this,RegsiteredData.class);
            startActivity(i);
        }
    }
}