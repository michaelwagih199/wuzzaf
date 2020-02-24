package com.example.wuzzaf.entities;

import com.google.firebase.firestore.DocumentId;

public class JopRequirements {
    @DocumentId
    private String documentId;
   private String collage,degree,ExperienceYear,Age,JopDescription,id;

    public JopRequirements() {
    }

    public JopRequirements(String documentId, String collage, String degree, String experienceYear, String age, String jopDescription, String id) {
        this.documentId = documentId;
        this.collage = collage;
        this.degree = degree;
        ExperienceYear = experienceYear;
        Age = age;
        JopDescription = jopDescription;
        this.id = id;
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

    public String getExperienceYear() {
        return ExperienceYear;
    }

    public void setExperienceYear(String experienceYear) {
        ExperienceYear = experienceYear;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getJopDescription() {
        return JopDescription;
    }

    public void setJopDescription(String jopDescription) {
        JopDescription = jopDescription;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
