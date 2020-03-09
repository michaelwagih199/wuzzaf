package com.example.wuzzaf.entities;

import com.google.firebase.firestore.DocumentId;

public class CompanyAcceptOrder {
    @DocumentId
    private String documentId;
    private String companyName,EmployName;

    public CompanyAcceptOrder() {

    }

    public CompanyAcceptOrder( String companyName, String employName) {
        this.companyName = companyName;
        EmployName = employName;
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

    public String getEmployName() {
        return EmployName;
    }

    public void setEmployName(String employName) {
        EmployName = employName;
    }

}
