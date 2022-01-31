package com.example.alertdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn;
    AlertDialog.Builder alertBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertBuilder=new AlertDialog.Builder(MainActivity.this);
                alertBuilder.setTitle("Alert Title");
                alertBuilder.setMessage("This is a Message Body");
                alertBuilder.setCancelable(false);   //if this sets to true then, if we click outside the alertbox also, the alert box gets closed
                alertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Positive Response",Toast.LENGTH_LONG).show();
                    }
                });
                alertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.cancel();
                        Toast.makeText(MainActivity.this,"Negative Response",Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog alert = alertBuilder.create();
                alert.show();
            }
        });
    }
}