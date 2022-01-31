package com.example.dummyproject;

import android.app.Application;
import android.content.Intent;

public class DBData extends Application {
    String STUDENT_ID,FIRSTNAME,LASTNAME,DOB,PASSWORD,MAIL_ID,ADDRESS,MOTHER_NAME,FATHER_NAME,GUARDIAN_NAME,HSC_SCHOOL_NAME,SSLC_SCHOOL_NAME,GENDER,COURSE,
            DEPARTMENT,ADMISSION_TYPE,NAME,PWD;
    String MOBILE_NO;
    String MOTHER_MOB;
    String FATHER_MOB;
    String GUARDIAN_MOB;
    String HSC_PASSING_YEAR;
    String HSC_MARKS;
    String SSLC_PASSING_YEAR;
    String SSLC_MARKS;
    String name;
    /* public DBData(String firstname, String lastname,String dob,String gender,String password,String mailid,String address,String mothername,String fathername,String guardianname,
                   String hscschoolname,String sslcschoolname,String course, String department,String admissiontype, String studenttype,Integer mobileno,Integer mothermob,
                   Integer fathermob,Integer guardianmob,Integer hscpassingyear,Integer hscmarks,Integer sslcpassingyear,Integer sslcmarks)
     {
         this.FIRSTNAME = firstname;this.LASTNAME = lastname;this.GENDER = gender;this.PASSWORD = password;this.MAIL_ID = mailid;this.ADDRESS = address;
         this.MOTHER_NAME = mothername;this.FATHER_NAME = fathername;this.GUARDIAN_NAME =guardianname;this.HSC_SCHOOL_NAME =hscschoolname;this.SSLC_SCHOOL_NAME=sslcschoolname;
         this.COURSE = course;this.DEPARTMENT = department;this.ADMISSION_TYPE = admissiontype;this.STUDENT_TYPE= studenttype;this.DOB = dob;this.MOBILE_NO =mobileno;
         this.MOTHER_MOB = mothermob;this.FATHER_MOB =fathermob;this.GUARDIAN_MOB= guardianmob;this.HSC_PASSING_YEAR =hscpassingyear;this.HSC_MARKS = hscmarks;
         this.SSLC_PASSING_YEAR =sslcpassingyear;this.SSLC_MARKS = sslcmarks;
     }*/
    public String getFirstName() { return FIRSTNAME; }
    public String getLastName() { return LASTNAME; }
    public String getName() { return NAME; }
    public String getPwd() { return PWD; }
    public String getDob() {
        return DOB;
    }
    public String getPassword() {
        return PASSWORD;
    }
    public String getMailid() {
        return MAIL_ID;
    }
    public String getAddress() {
        return ADDRESS;
    }
    public String getMotherName() {
        return MOTHER_NAME;
    }
    public String getFatherName() {
        return FATHER_NAME;
    }
    public String getGuardianName() {
        return GUARDIAN_NAME;
    }
    public String getHscSchoolName() {
        return HSC_SCHOOL_NAME;
    }
    public String getSslcSchoolName() {
        return SSLC_SCHOOL_NAME;
    }
    public String getGender() {
        return GENDER;
    }
    public String getCourse() {
        return COURSE;
    }
    public String getDepartment() { return DEPARTMENT; }
    public String getAdmissionType() {
        return ADMISSION_TYPE;
    }
    public String getMobileNo() { return String.valueOf(MOBILE_NO); }
    public String getMotherMob() { return String.valueOf(MOTHER_MOB); }
    public String getFatherMob() { return String.valueOf(FATHER_MOB); }
    public String getGuardianMob() { return String.valueOf(GUARDIAN_MOB); }
    public String getHscPassingYear() { return String.valueOf(HSC_PASSING_YEAR); }
    public String getHscMarks() { return String.valueOf(HSC_MARKS); }
    public String getSslcPassingYear() { return String.valueOf(SSLC_PASSING_YEAR); }
    public String getSslcMarks() { return String.valueOf(SSLC_MARKS); }
    public String getUser() { return name; }

    public void setFirstName(String firstName) { this.FIRSTNAME=firstName; }
    public void setLastName( String lastName) { this.LASTNAME = lastName; }
    public void setName(String name) { this.NAME=name; }
    public void setPwd( String pwd) { this.PWD = pwd; }
    public void setDob(String dob) { this.DOB = dob; }
    public void setPassword(String password) {
        this.PASSWORD = password;
    }
    public void setMailid(String mailid) {
        this.MAIL_ID = mailid;
    }
    public void setAddress(String address) {
        this.ADDRESS = address;
    }
    public void setMotherName(String mothername) {
        this.MOTHER_NAME = mothername;
    }
    public void setFatherName(String fathername) {
        this.FATHER_NAME = fathername;
    }
    public void setGuardianName(String guardianname) {
        this.GUARDIAN_NAME =guardianname;
    }
    public void setHscSchoolName(String hscschoolname) {
        this.HSC_SCHOOL_NAME =hscschoolname;
    }
    public void setSslcSchoolName(String sslcschoolname) {
        this.SSLC_SCHOOL_NAME=sslcschoolname;
    }
    public void setGender(String gender) { this.GENDER = gender; }
    public void setCourse(String course) { this.COURSE = course; }
    public void setDepartment(String department) { this.DEPARTMENT = department; }
    public void setAdmissionType(String admissiontype) {
        this.ADMISSION_TYPE = admissiontype;
    }
    public void setMobileNo(String mobileno) { this.MOBILE_NO =mobileno; }
    public void setMotherMob(String mothermob) { this.MOTHER_MOB = mothermob; }
    public void setFatherMob(String fathermob) { this.FATHER_MOB =fathermob; }
    public void setGuardianMob(String guardianmob) {this.GUARDIAN_MOB= guardianmob; }
    public void setHscPassingYear(String hscpassingyear) { this.HSC_PASSING_YEAR =hscpassingyear; }
    public void setHscMarks(String hscmarks) { this.HSC_MARKS = hscmarks; }
    public void setSslcPassingYear(String sslcpassingyear) { this.SSLC_PASSING_YEAR =sslcpassingyear; }
    public void setSslcMarks(String sslcmarks) { this.SSLC_MARKS = sslcmarks; }

    public void setUser(String name) {
        this.name = name;
    }
}

