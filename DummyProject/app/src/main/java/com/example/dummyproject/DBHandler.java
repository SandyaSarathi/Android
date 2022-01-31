package com.example.dummyproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    SQLiteDatabase db;

    private static final String DB_NAME = "db_hogwartz_university";private static final int DB_VERSION = 1;

    public static final String STAFF_ID = "staff_id",NAME = "name", PWD="pwd",TABLE_NAME3 = "staff",TABLE_NAME2 = "login",TABLE_NAME1 = "student", STUDENT_ID = "student_id", FIRSTNAME = "firstname", LASTNAME = "lastname", DOB = "dob", GENDER = "gender",
            PASSWORD = "password",MAIL_ID = "mail_id", ADDRESS = "address", MOTHER_NAME = "mother_name", FATHER_NAME = "father_name", GUARDIAN_NAME = "guardian_name",
            HSC_SCHOOL_NAME = "hsc_school_name", SSLC_SCHOOL_NAME = "sslc_school_name",  COURSE = "course", DEPARTMENT = "department",ADMISSION_TYPE = "admission_type";

    public static final String MOBILE_NO = "mobile_no", MOTHER_MOB = "mother_mob",FATHER_MOB = "father_mob",GUARDIAN_MOB = "guardian_mob",
            SSLC_PASSING_YEAR = "sslc_passing_year", SSLC_MARKS = "sslc_marks",HSC_PASSING_YEAR = "hsc_passing_year", HSC_MARKS = "hsc_marks";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + TABLE_NAME1 + " (" +
                STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FIRSTNAME + " TEXT NOT NULL, " +
                LASTNAME + " TEXT NOT NULL, " +
                DOB + " TEXT NOT NULL, " +
                GENDER + " TEXT NOT NULL, " +
                PASSWORD + " TEXT NOT NULL, " +
                MAIL_ID + " TEXT NOT NULL, " +
                MOBILE_NO + " TEXT NOT NULL, " +
                ADDRESS + " TEXT NOT NULL, " +
                MOTHER_NAME + " TEXT NOT NULL, " +
                MOTHER_MOB + " TEXT NOT NULL, " +
                FATHER_NAME + " TEXT NOT NULL, " +
                FATHER_MOB + " TEXT NOT NULL, " +
                GUARDIAN_NAME + " TEXT NOT NULL, " +
                GUARDIAN_MOB + " TEXT NOT NULL, " +
                HSC_SCHOOL_NAME + " TEXT NOT NULL, " +
                HSC_PASSING_YEAR + " TEXT NOT NULL, " +
                HSC_MARKS + " TEXT NOT NULL, " +
                SSLC_SCHOOL_NAME + " TEXT NOT NULL, " +
                SSLC_PASSING_YEAR + " TEXT NOT NULL, " +
                SSLC_MARKS + " TEXT NOT NULL, " +
                COURSE + " TEXT NOT NULL, " +
                DEPARTMENT + " TEXT NOT NULL, " +
                ADMISSION_TYPE + " TEXT NOT NULL);");

        db.execSQL("create table faculty (empId INTEGER PRIMARY KEY AUTOINCREMENT,password TEXT,firstName TEXT,lastName TEXT,gender TEXT,dob TEXT,mail TEXT,ph TEXT," +
                "address TEXT,job_type TEXT,designation TEXT,department TEXT,status TEXT,date_joined TEXT,expirience INTEGER," +
                "university TEXT,degree TEXT,completed_year TEXT,percentage TEXT,inserted_by TEXT,inserted_on TEXT)");



        db.execSQL(" CREATE TABLE " + TABLE_NAME2 + " (" +
                        MAIL_ID + " TEXT NOT NULL PRIMARY KEY, " +
                        PASSWORD + " TEXT NOT NULL);");

   /*     db.execSQL(" CREATE TABLE " + TABLE_NAME3 + " (" +
                STAFF_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " TEXT NOT NULL, " +
                PASSWORD + " TEXT NOT NULL);");*/

        db.execSQL("create trigger trigger_staff after insert on faculty begin insert into login values(new.mail,new.password);end");

        db.execSQL("create trigger trigger_student after insert on student begin insert into login values(new.MAIL_ID,new.PASSWORD);end");
    }

    public void addNewStudent(String Firstname, String Lastname, String Dob, String Gender, String Password, String Mailid, String Mobileno, String Address,
                              String Mothername, String Mothermob, String Fathername, String Fathermob, String Guardianname, String Guardianmob, String Hscschoolname,
                              String Hscpassingyear, String Hscmarks, String Sslcschoolname, String Sslcpassingyear, String Sslcmarks, String Course, String Department,
                              String Admissiontype){
        ContentValues values = new ContentValues();
        values.put(FIRSTNAME,Firstname);values.put(LASTNAME,Lastname);values.put(DOB,Dob);values.put(GENDER,Gender);
        values.put(PASSWORD,Password);values.put(MAIL_ID,Mailid);values.put(MOBILE_NO,Mobileno);values.put(ADDRESS,Address);values.put(MOTHER_NAME,Mothername);
        values.put(MOTHER_MOB,Mothermob);values.put(FATHER_NAME,Fathername);values.put(FATHER_MOB,Fathermob);values.put(GUARDIAN_NAME,Guardianname);
        values.put(GUARDIAN_MOB,Guardianmob);values.put(HSC_SCHOOL_NAME, Hscschoolname);values.put(HSC_PASSING_YEAR, Hscpassingyear);values.put(HSC_MARKS, Hscmarks);
        values.put(SSLC_SCHOOL_NAME, Sslcschoolname);values.put(SSLC_PASSING_YEAR, Sslcpassingyear);values.put(SSLC_MARKS, Sslcmarks);values.put(COURSE,Course);
        values.put(DEPARTMENT,Department);values.put(ADMISSION_TYPE,Admissiontype);
        db.insert(TABLE_NAME1, null, values);
        db.close();
    }

    public void addLogin()
    {
        db.execSQL("Insert into faculty values(1,'Swetha@123','Swetha','S','Female','1/1/2001','swe@gmail.com','9878765643','Chennai','Academic','Professor','CSE','Active','1/1/2019',7,'VIT','BE','2013','90','san@gmail.com','1/1/2019')");

    }

 /*   public void addStaff(String Name, String Password) {
        ContentValues values = new ContentValues();
        values.put(NAME, Name);
        values.put(PASSWORD, Password);
    }
*/



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3);
        onCreate(db);
    }

    public Cursor viewStudent(String user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from student where mail_id=?", new String[]{user});
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public boolean updateStudent(String user, String Mobile, String Address, String Mothermob, String Fathermob,String Department, String Guardianmob){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from student where mail_id=?", new String[]{user});
        ContentValues values = new ContentValues();
        //values.put("mail_id",Mail);
        values.put("mobile_no",Mobile);
        values.put("address",Address);
        values.put("mother_mob",Mothermob);
        values.put("father_mob",Fathermob);
        values.put("guardian_mob",Guardianmob);
        values.put("department",Department);
        if(cursor.getCount()>0)
        {
            int result = db.update("student",values, "mail_id=?",new String[] {user});
            if(result==0) {
                return false;
            }
            else {
                return true;
            }
        }
        db.close();
        return false;
    }
    public Cursor login(String Mail,String Password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from login where mail_id=? and password=?" , new String[]{Mail,Password});
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public boolean updatePassword(String userId,String Password)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select l.password from login l inner join student s on l.mail_id=s.mail_id  where l.mail_id=?", new String[]{userId});
        ContentValues values = new ContentValues();
        values.put("password",Password);
        if(cursor.getCount()>0)
        {
            db.update("student",values, "mail_id=?",new String[] {userId});
            db.update("login",values, "mail_id=?",new String[] {userId});
            return true;
        }
        else
        {
            db.update("staff",values, "staff_id=?",new String[] {userId});
            db.update("login",values, "student_id=?",new String[] {userId});
            return true;
        }
    }
    public long studentTotal()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TABLE_NAME1);
        db.close();
        return count;
    }
}

