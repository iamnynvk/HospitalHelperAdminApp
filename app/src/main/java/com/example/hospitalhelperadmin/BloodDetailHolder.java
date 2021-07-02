package com.example.hospitalhelperadmin;

public class BloodDetailHolder {
    String age, bloodgroup, email, fullname, genderbtn, mobilno, user;

    public BloodDetailHolder() {
    }

    public BloodDetailHolder(String age, String bloodgroup, String email, String fullname, String genderbtn, String mobilno, String user) {
        this.age = age;
        this.bloodgroup = bloodgroup;
        this.email = email;
        this.fullname = fullname;
        this.genderbtn = genderbtn;
        this.mobilno = mobilno;
        this.user = user;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getGenderbtn() {
        return genderbtn;
    }

    public void setGenderbtn(String genderbtn) {
        this.genderbtn = genderbtn;
    }

    public String getMobilno() {
        return mobilno;
    }

    public void setMobilno(String mobilno) {
        this.mobilno = mobilno;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}