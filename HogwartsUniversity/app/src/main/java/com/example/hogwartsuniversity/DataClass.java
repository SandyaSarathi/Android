package com.example.hogwartsuniversity;

import android.app.Application;

import java.util.ArrayList;

public class DataClass extends Application {
    Integer id,emp_id,expirience,img_name;
    long count;
    String user,fname,lname,gender,dob,mail,address,course,department,admission,
            status,percent_ssc,ssc_school,hsc_school,percent_hsc,ssc_completed,
            hsc_completed,parent_fname,parent_lname,parent_mail,parent_address,job_type,designation,joining_date
            ,university,degree,passed_year,percentage_obtained,ph,parent_ph,alt_ph,description;


    //Constructor for list of individual's profile
    public DataClass(int Id,String Name,String Department,String Mail)
    {
        fname=Name;
        id=Id;
        department=Department;
        mail=Mail;
    }
    public DataClass()
    {

    }

    //Constructor for Count of students, Faculties
    public DataClass(int Count,int ImgName,String Description)
    {
        this.count=Count;
        this.img_name=ImgName;
        this.description=Description;
    }

    public String getUserMail() {
        return user;
    }

    public void setUserMail(String user) {
        this.user = user;
    }

    public void setId(int id)
    {
        this.id=id;
    }
    public int getId()
    {
        return this.id;
    }
    public void setFname(String name)
    {
        fname=name;
    }
    public String getFname() {
        return fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
    public String getLname()
    {
        return lname;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setAdmission(String admission) {
        this.admission = admission;
    }

    public String getAdmission() {
        return admission;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setPercent_ssc(String percent_ssc) {
        this.percent_ssc = percent_ssc;
    }

    public String getPercent_ssc() {
        return percent_ssc;
    }

    public void setSsc_school(String ssc_school) {
        this.ssc_school = ssc_school;
    }

    public String getSsc_school() {
        return ssc_school;
    }

    public void setSsc_completed(String ssc_completed) {
        this.ssc_completed = ssc_completed;
    }

    public String getSsc_completed() {
        return ssc_completed;
    }

    public void setPercent_hsc(String percent_hsc) {
        this.percent_hsc = percent_hsc;
    }

    public String getPercent_hsc() {
        return percent_hsc;
    }

    public void setHsc_school(String hsc_school) {
        this.hsc_school = hsc_school;
    }

    public String getHsc_school() {
        return hsc_school;
    }

    public String getHsc_completed() {
        return hsc_completed;
    }

    public void setHsc_completed(String hsc_completed) {
        this.hsc_completed = hsc_completed;
    }

    public void setParent_fname(String parent_fname) {
        this.parent_fname = parent_fname;
    }

    public String getParent_fname() {
        return parent_fname;
    }

    public void setParent_lname(String parent_lname) {
        this.parent_lname = parent_lname;
    }

    public String getParent_lname() {
        return parent_lname;
    }

    public void setParent_mail(String parent_mail) {
        this.parent_mail = parent_mail;
    }

    public String getParent_mail() {
        return parent_mail;
    }

    public void setParent_ph(String parent_ph) {
        this.parent_ph = parent_ph;
    }

    public String getParent_ph() {
        return parent_ph;
    }

    public void setParent_address(String parent_address) {
        this.parent_address = parent_address;
    }

    public String getParent_address() {
        return parent_address;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getPh() {
        return ph;
    }

    public void setAlt_ph(String alt_ph) {
        this.alt_ph = alt_ph;
    }

    public String getAlt_ph() {
        return alt_ph;
    }

    public void setEmp_id(Integer emp_id) {
        this.emp_id = emp_id;
    }

    public Integer getEmp_id() {
        return emp_id;
    }

    public void setJob_type(String job_type) {
        this.job_type = job_type;
    }

    public String getJob_type() {
        return job_type;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }

    public void setExpirience(Integer expirience) {
        this.expirience = expirience;
    }

    public Integer getExpirience() {
        return expirience;
    }

    public void setJoining_date(String joining_date) {
        this.joining_date = joining_date;
    }

    public String getJoining_date() {
        return joining_date;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDegree() {
        return degree;
    }

    public String getCompleted_year() {
        return passed_year;
    }

    public String getPercentage_obtained() {
        return percentage_obtained;
    }

    public String getUniversity() {
        return university;
    }

    public void setCompleted_year(String passed_year) {
        this.passed_year = passed_year;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public void setPercentage_obtained(String percentage_obtained) {
        this.percentage_obtained = percentage_obtained;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getImg_name() {
        return img_name;
    }

    public void setImg_name(int img_name) {
        this.img_name = img_name;
    }
}
