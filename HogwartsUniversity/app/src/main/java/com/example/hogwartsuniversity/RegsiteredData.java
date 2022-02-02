package com.example.hogwartsuniversity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

public class RegsiteredData extends NavigationDrawer {
    DrawerLayout drawerLayout;
    DBHandler db;
    Cursor cursor;
    int s1,s5;
    String s2,s3,s4;
    boolean ReadOnly = true;


    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_regsitered_data);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_regsitered_data, null, false);
        drawerLayout.addView(contentView, 0);
        DataClass data = (DataClass) getApplicationContext();
        db = new DBHandler(this);
        ArrayList<DataClass> list = new ArrayList<>();
        cursor = db.registeredData(data.getUserMail());
        if (cursor.moveToFirst()) {
            do {
                s1 = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                s2 = cursor.getString(cursor.getColumnIndex("firstName"));
                s3 = cursor.getString(cursor.getColumnIndex("department"));
                s4 = cursor.getString(cursor.getColumnIndex("mail"));
                list.add(new DataClass(s1,s2,s3,s4));
            } while (cursor.moveToNext());
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        DataAdapter adapter = new DataAdapter(list,ReadOnly);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}