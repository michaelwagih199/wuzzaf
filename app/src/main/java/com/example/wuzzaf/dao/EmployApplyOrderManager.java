package com.example.wuzzaf.dao;

import com.example.wuzzaf.entities.EmployApplyOrder;
import com.example.wuzzaf.entities.Users;
import com.example.wuzzaf.helpers.SharedPrefrenceHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import static com.example.wuzzaf.firestoreStructure.EmployApplyOrderDB.employApplyOrder;

public class EmployApplyOrderManager {

    /* ContactsFirestoreManager object **/
    private static EmployApplyOrderManager employApplyOrderManager;

    private FirebaseFirestore firebaseFirestore;
    private CollectionReference contactsCollectionReference;
    SharedPrefrenceHelper sharedPrefrenceHelper = new SharedPrefrenceHelper();


    public static EmployApplyOrderManager newInstance() {
        if (employApplyOrderManager == null) {
            employApplyOrderManager = new EmployApplyOrderManager();
        }
        return employApplyOrderManager;
    }


    private EmployApplyOrderManager() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        contactsCollectionReference = firebaseFirestore.collection(employApplyOrder);
    }

    public void createDocument(EmployApplyOrder contact) {
        contactsCollectionReference.add(contact);
    }

    public void getAllContacts(OnCompleteListener<QuerySnapshot> onCompleteListener) {
        contactsCollectionReference.get().addOnCompleteListener(onCompleteListener);
    }

    public void updateContact(EmployApplyOrder contact) {
        String documentId = contact.getDocumentId();
        DocumentReference documentReference = contactsCollectionReference.document(documentId);
        documentReference.set(contact);
    }

    public void deleteContact(String documentId) {
        DocumentReference documentReference = contactsCollectionReference.document(documentId);
        documentReference.delete();
    }

    public void sendContactsBulk( String companyName , String userName ) {
        createDocument(new EmployApplyOrder(companyName, userName ));
    }

}
