package com.example.wuzzaf.dao;
import com.example.wuzzaf.entities.JopRequirements;
import com.example.wuzzaf.helpers.SharedPrefrenceHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import static com.example.wuzzaf.firestoreStructure.JopRequirementsDb.JopRequirementsName;


public class JopRequirementsManager {

    /* ContactsFirestoreManager object **/
    private static JopRequirementsManager jopRequirementsManager;

    private FirebaseFirestore firebaseFirestore;
    private CollectionReference contactsCollectionReference;
    SharedPrefrenceHelper sharedPrefrenceHelper = new SharedPrefrenceHelper();

    public static JopRequirementsManager newInstance() {

        if (jopRequirementsManager == null) {
            jopRequirementsManager = new JopRequirementsManager();
        }
        return jopRequirementsManager;
    }


    private JopRequirementsManager() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        contactsCollectionReference = firebaseFirestore.collection(JopRequirementsName);
    }

    public void createDocument(JopRequirements JopRequirements) {
        contactsCollectionReference.add(JopRequirements);
    }

    public void getAllContacts(OnCompleteListener<QuerySnapshot> onCompleteListener) {
        contactsCollectionReference.get().addOnCompleteListener(onCompleteListener);
    }

    public void updateContact(JopRequirements contact,String documentId) {
        //String documentId = contact.getDocumentId();
        DocumentReference documentReference = contactsCollectionReference.document(documentId);
        documentReference.set(contact);
    }

    public void deleteContact(String documentId) {
        DocumentReference documentReference = contactsCollectionReference.document(documentId);
        documentReference.delete();
    }

    public void sendContactsBulk(String documentId, String collage, String degree, String experienceYear, String age, String jopDescription, String id) {
        createDocument(new JopRequirements(documentId, collage, degree, experienceYear, age,jopDescription,id));
    }

}
