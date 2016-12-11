package com.example.jayeshvaghela.project_vaghela;

/**
 * Created by jayeshvaghela on 2016-12-08.
 */
public class Student {

    String studentID;
    String studentScore;
    String studentComment;

    Student(String id, String score, String comment)
    {
        this.studentID = id;
        this.studentScore = score;
        this.studentComment = comment;
    }

    public String getStudentComment() {
        return studentComment;
    }

    public void setStudentComment(String studentComment) {
        this.studentComment = studentComment;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentScore() {
        return studentScore;
    }

    public void setStudentScore(String studentScore) {
        this.studentScore = studentScore;
    }
}
