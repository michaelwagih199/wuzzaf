package com.example.wuzzaf.entities;

import com.google.firebase.firestore.DocumentId;

public class EmplyCv {

    @DocumentId
    private String documentId;
    private String name,age,phone,collage,degee,grade;

    public EmplyCv() {

    }

    public EmplyCv(String name, String age, String phone, String collage, String degee, String grade) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.collage = collage;
        this.degee = degee;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCollage() {
        return collage;
    }

    public void setCollage(String collage) {
        this.collage = collage;
    }

    public String getDegee() {
        return degee;
    }

    public void setDegee(String degee) {
        this.degee = degee;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}