package com.example.wuzzaf.entities;

import com.google.firebase.firestore.DocumentId;

public class EmployApplyOrder {
    @DocumentId
    private String documentId;
    private String companyName,UserName;

    public EmployApplyOrder() {
    }

    public EmployApplyOrder(String companyName, String userName) {
        this.companyName = companyName;
        UserName = userName;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
}
