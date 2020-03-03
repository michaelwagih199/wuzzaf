package com.example.wuzzaf.entities;

import com.google.firebase.firestore.DocumentId;

public class JopRequirements {
    @DocumentId
    private String documentId;
   private String collage,degree,grade,experienceYear,age,jopTitle,jopDescription,id;

    public JopRequirements() {
    }

    public JopRequirements(String collage, String degree, String grade, String experienceYear, String age, String jopTitle, String jopDescription, String id) {
        this.collage = collage;
        this.degree = degree;
        this.grade = grade;
        this.experienceYear = experienceYear;
        this.age = age;
        this.jopDescription = jopDescription;
        this.id = id;
        this.jopTitle = jopTitle;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getCollage() {
        return collage;
    }

    public void setCollage(String collage) {
        this.collage = collage;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getExperienceYear() {
        return experienceYear;
    }

    public void setExperienceYear(String experienceYear) {
        this.experienceYear = experienceYear;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getJopDescription() {
        return jopDescription;
    }

    public void setJopDescription(String jopDescription) {
        this.jopDescription = jopDescription;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJopTitle() {
        return jopTitle;
    }

    public void setJopTitle(String jopTitle) {
        this.jopTitle = jopTitle;
    }
}
