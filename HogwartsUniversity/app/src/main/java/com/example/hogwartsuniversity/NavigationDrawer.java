package com.example.hogwartsuniversity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class NavigationDrawer extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        dl = (DrawerLayout)findViewById(R.id.drawer_layout);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);
        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.student:
                        Intent i = new Intent(NavigationDrawer.this,StudentRegistration.class);
                        startActivity(i);
                        break;
                    case R.id.faculty:
                        Intent i1 = new Intent(NavigationDrawer.this,FacultyRegistrationPersonal.class);
                        startActivity(i1);
                        break;
                    case R.id.profile:
                        Intent i2 = new Intent(NavigationDrawer.this,ProfilePage.class);
                        startActivity(i2);
                        break;
                    case R.id.homePage:
                        Intent i3 = new Intent(NavigationDrawer.this,DashBoard.class);
                        startActivity(i3);
                        break;

                    default:
                        return true;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_logout:
                Intent i = new Intent(this,LoginPage.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                break;
            case R.id.my_profile:
                Intent i1 = new Intent(this,FacultyProfile.class);
                DataClass dataclass = (DataClass) getApplicationContext();
                i1.putExtra("Mail",dataclass.getUserMail());
                startActivity(i1);
                break;
        }

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout, menu);
        return true;
    }
}