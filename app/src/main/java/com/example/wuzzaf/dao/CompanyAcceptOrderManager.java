package com.example.wuzzaf.dao;

import com.example.wuzzaf.entities.CompanyAcceptOrder;
import com.example.wuzzaf.entities.EmployApplyOrder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import static com.example.wuzzaf.firestoreStructure.CompanyAcceptOrderDB.companyAcceptOrder;

public class CompanyAcceptOrderManager {
    /* ContactsFirestoreManager object **/
    private static CompanyAcceptOrderManager companyAcceptOrderManager;
    private FirebaseFirestore firebaseFirestore;
    private CollectionReference contactsCollectionReference;

    public static CompanyAcceptOrderManager newInstance() {
        if (companyAcceptOrderManager == null) {
            companyAcceptOrderManager = new CompanyAcceptOrderManager();
        }
        return companyAcceptOrderManager;
    }

    private CompanyAcceptOrderManager() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        contactsCollectionReference = firebaseFirestore.collection(companyAcceptOrder);
    }
    public void createDocument(CompanyAcceptOrder contact) {
        contactsCollectionReference.add(contact);
    }

    public void getAllContacts(OnCompleteListener<QuerySnapshot> onCompleteListener) {
        contactsCollectionReference.get().addOnCompleteListener(onCompleteListener);
    }

    public void updateContact(CompanyAcceptOrder contact) {
        String documentId = contact.getDocumentId();
        DocumentReference documentReference = contactsCollectionReference.document(documentId);
        documentReference.set(contact);
    }

    public void deleteContact(String documentId) {
        DocumentReference documentReference = contactsCollectionReference.document(documentId);
        documentReference.delete();
    }

    public void sendContactsBulk(String companyName, String userName) {
        createDocument(new CompanyAcceptOrder(companyName, userName));
    }

}
