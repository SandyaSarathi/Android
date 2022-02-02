package com.example.hogwartsuniversity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {

    public DBHandler(Context context) {

        super(context, "hogwarts", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table faculty (empId INTEGER PRIMARY KEY AUTOINCREMENT,password TEXT,firstName TEXT,lastName TEXT,gender TEXT,dob TEXT,mail TEXT,ph TEXT," +
                "address TEXT,job_type TEXT,designation TEXT,department TEXT,status TEXT,date_joined TEXT,expirience INTEGER," +
                "university TEXT,degree TEXT,completed_year TEXT,percentage TEXT,inserted_by TEXT,inserted_on TEXT)");

        db.execSQL("create table student(id Integer PRIMARY KEY AUTOINCREMENT,password TEXT,firstName TEXT,lastName TEXT,gender TEXT,dob TEXT,mail TEXT,ph TEXT,address TEXT,course TEXT" +
                ",department TEXT,admission TEXT,status TEXT,percent_ssc INTEGER,ssc_school TEXT,hsc_school TEXT,percent_hsc INTEGER,ssc_completed INTEGER,hsc_completed INTEGER" +
                ",parent_fname TEXT,parent_lname TEXT,parent_mail TEXT,parent_ph TEXT,parent_address TEXT,inserted_by TEXT,inserted_on TEXT )");

        db.execSQL("create table login(mail TEXT PRIMARY KEY,name TEXT,password TEXT,Type TEXT)");

        db.execSQL("create trigger trigger_faculty after insert on faculty begin insert into login values(new.mail,new.firstName,new.password,'Faculty');end  ");
        db.execSQL("create trigger trigger_student after insert on student begin insert into login values(new.mail,new.firstName,new.password,'Student');end");

    }

    public void addStudent(String name, String Lname, String Gender, String DOB, String Course, String Department, String Admission, String Status, String ssc_percent, String school_ssc, String ssc_passed, String hsc_percent, String school_hsc, String hsc_passed, String parent_name, String parent_lname, String parnt_mail, String parnt_ph, String Parnt_address, String email, String mobile, String Adrress, String Inserted_by,String Inserted_on)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("firstName",name);
        values.put("lastName",Lname);
        values.put("gender",Gender);
        values.put("dob",DOB);
        values.put("password",DOB);
        values.put("mail",email);
        values.put("ph",mobile);
        values.put("address",Adrress);
        values.put("course",Course);
        values.put("department",Department);
        values.put("admission",Admission);
        values.put("status",Status);
        values.put("percent_ssc",ssc_percent);
        values.put("ssc_school",school_ssc);
        values.put("hsc_school",school_hsc);
        values.put("percent_hsc",hsc_percent);
        values.put("ssc_completed",ssc_passed);
        values.put("hsc_completed",hsc_passed);
        values.put("parent_fname",parent_name);
        values.put("parent_lname",parent_lname);
        values.put("parent_mail",parnt_mail);
        values.put("parent_ph",parnt_ph);
        values.put("parent_address",Parnt_address);
        values.put("inserted_by",Inserted_by);
        values.put("inserted_on",Inserted_on);

        db.insert("student", null, values);
        // at last we are closing our
        // database after adding database.
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + "student");
        db.execSQL("DROP TABLE IF EXISTS " + "faculty");
        onCreate(db);
    }

    public void addFaculty(String fname, String lname, String Gender, String DOB, String Status, String jobType, String Designation, String Department, String joining_date, Integer Expirience, String University, String Degree, String passed_year, String percentage_obtained, String mail, String ph, String address,String Inserted_by,String Inserted_on) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("firstName",fname);
        values.put("lastName",lname);
        values.put("gender",Gender);
        values.put("dob",DOB);
        values.put("mail",mail);
        values.put("ph",ph);
        values.put("password",DOB);
        values.put("address",address);
        values.put("job_type",jobType);
        values.put("department",Department);
        values.put("designation",Designation);
        values.put("status",Status);
        values.put("university",University);
        values.put("degree",Degree);
        values.put("completed_year",passed_year);
        values.put("percentage",percentage_obtained);
        values.put("date_joined",joining_date);
        values.put("expirience",Expirience);
        values.put("inserted_by",Inserted_by);
        values.put("inserted_on",Inserted_on);

        db.insert("faculty", null, values);
        // at last we are closing our
        // database after adding database.
        db.close();
    }

    public Cursor viewStudent(String Mail) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from student where mail=?", new String[]{Mail});
        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }
    public Cursor viewFaculty(String Mail)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from faculty where mail=?", new String[]{Mail});
        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }

    public Cursor login(String Mail)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from login where mail=?", new String[]{Mail});
        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }
    public boolean updatePassword(String userMail,String Password)
    {
        SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("Select l.password from login l inner join student s on l.mail=s.mail  where l.mail=?", new String[]{userMail});
            ContentValues values = new ContentValues();
            values.put("password",Password);
            if(cursor.getCount()>0)
            {
                db.update("student",values, "mail=?",new String[] {userMail});
                db.update("login",values, "mail=?",new String[] {userMail});
                return true;

            }
            else
            {
                db.update("faculty",values, "mail=?",new String[] {userMail});
                db.update("login",values, "mail=?",new String[] {userMail});
                return true;
            }
    }

    public boolean deleteFaculty(String userMail)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from faculty where mail=?", new String[]{userMail});
        if(cursor.getCount()>0)
        {
            int result = db.delete("faculty","mail=?",new String[] {userMail});
            if(result==0)
            {
                return false;
            }
            else
            {
                return true;
            }
        }

            return false;
    }

    public boolean deleteStudent(String userMail)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from student where mail=?", new String[]{userMail});
        if(cursor.getCount()>0)
        {
            int result = db.delete("student","mail=?",new String[] {userMail});
            if(result==0)
            {
                return false;
            }
            else
            {
                return true;
            }
        }

        return false;
    }

    public boolean updateFaculty(String userMail,String Mail,String mobile,String Address,int Expirience,String Status,String type,String Designation,String Department)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from faculty where mail=?", new String[]{userMail});
        ContentValues values = new ContentValues();
        values.put("mail",Mail);
        values.put("ph",mobile);
        values.put("address",Address);
        values.put("designation",Designation);
        values.put("department",Department);
        values.put("job_type",type);
        values.put("expirience",Expirience);
        values.put("status",Status);
        if(cursor.getCount()>0)
        {
            int result = db.update("faculty",values,"mail=?",new String[] {userMail});

            if(result==0)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        return false;
    }

    public Cursor fetchStudent(String name, String Department, String course, String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from student where department=? or firstName =? or course=? or id=?", new String[]{Department,name,course,id});
        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }
    public Cursor fetchFaculty(String name, String Department, String id,String type) {
        SQLiteDatabase db = this.getReadableDatabase();
       Cursor cursor = db.rawQuery("Select * from faculty where department=? or firstName =?  or empId=? or job_type=?", new String[]{Department,name,id,type});
       // Cursor cursor = db.rawQuery("Select * from Faculty where emp_id=?" , new String[]{id});

        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }

    public boolean updateStudent(String userMail, String Status, String Course, String Department, String Mail, String Mobile, String Address,  String ParentPhn) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from student where mail=?", new String[]{userMail});
        ContentValues values = new ContentValues();
        values.put("mail",Mail);
        values.put("ph",Mobile);
        values.put("address",Address);
        values.put("status",Status);
        values.put("parent_ph",ParentPhn);
        values.put("course",Course);
        values.put("department",Department);

        if(cursor.getCount()>0)
        {
            int result = db.update("student",values, "mail=?",new String[] {userMail});
            if(result==0)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        db.close();
        return false;
    }

    public long countStudent()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, "student");

        return count;
    }
    public int countTeachers()
    {
        SQLiteDatabase db = this.getWritableDatabase();
      Cursor cursor = db.rawQuery("Select empId from faculty where job_type=?", new String[]{"Academic"});

        return cursor.getCount();
    }
    public int countNonTeachers()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select empId from faculty where job_type=?", new String[]{"Non Academic"});

        return cursor.getCount();
    }
    @SuppressLint("Range")
    public int countRecords(String user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from student  where inserted_by=?", new String[]{user});
        Cursor cursor1 = db.rawQuery("Select *  from faculty where inserted_by=?", new String[]{user});
        return cursor.getCount()+cursor1.getCount();
    }
    public Cursor registeredData(String insertedBy)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select id ,firstName,department,mail from student where inserted_by=? UNION select empId as id,firstName,department,mail from faculty where inserted_by=?", new String[]{insertedBy,insertedBy});
        return cursor;
    }
}

