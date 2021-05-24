package com.example.app.Database;

public class Userhelper {
    String hname,phoneno,password,oxygenavai,ambulanceavai;
    Integer noofcovidpatients,noofdoctors,noofbed;

    public Userhelper() {}

    public Userhelper(String hname, String phoneno, String password) {
        this.hname = hname;
        this.phoneno = phoneno;
        this.password = password;
        this.ambulanceavai=null;
        this.oxygenavai=null;
        this.noofbed=null;
        this.noofcovidpatients=null;
        this.noofdoctors=null;
    }


    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
