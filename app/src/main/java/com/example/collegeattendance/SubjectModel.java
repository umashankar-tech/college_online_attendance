package com.example.collegeattendance;

public class SubjectModel {
    String batch ,subjectname,stream,year;
    public  SubjectModel(){

    }
    public SubjectModel(String batch, String subjectname, String stream, String year) {
        this.batch = batch;
        this.subjectname = subjectname;
        this.stream = stream;
        this.year = year;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }



}
