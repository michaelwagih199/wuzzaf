package com.example.wuzzaf.dao;

import com.example.wuzzaf.entities.EmplyCv;
import com.example.wuzzaf.entities.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import static com.example.wuzzaf.firestoreStructure.EmployFirestoreDbContract.COLLECTION_NAME;

public class EmployFirestoreManager {

    /* ContactsFirestoreManager object **/
    private static EmployFirestoreManager employFirestoreManager;

    private FirebaseFirestore firebaseFirestore;
    private CollectionReference contactsCollectionReference;

    public static EmployFirestoreManager newInstance() {
        if (employFirestoreManager == null) {
            employFirestoreManager = new EmployFirestoreManager();
        }
        return employFirestoreManager;
    }


    private EmployFirestoreManager() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        contactsCollectionReference = firebaseFirestore.collection(COLLECTION_NAME);
    }

    public void createDocument(EmplyCv contact) {
        contactsCollectionReference.add(contact);
    }

    public void getAllContacts(OnCompleteListener<QuerySnapshot> onCompleteListener) {
        contactsCollectionReference.get().addOnCompleteListener(onCompleteListener);
    }

    public void updateContact(EmplyCv contact) {
        String documentId = contact.getDocumentId();
        DocumentReference documentReference = contactsCollectionReference.document(documentId);
        documentReference.set(contact);
    }

    public void deleteContact(String documentId) {
        DocumentReference documentReference = contactsCollectionReference.document(documentId);
        documentReference.delete();
    }

    public void sendContactsBulk(String name, String age, String phone, String collage, String degee, String grade) {
        createDocument(new EmplyCv(name,age,phone,collage,degee,grade));

    }


}
