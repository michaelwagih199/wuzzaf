package com.example.wuzzaf.dao;

import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.wuzzaf.activities.EmployResult;
import com.example.wuzzaf.entities.CompanyAcceptOrder;
import com.example.wuzzaf.entities.EmployApplyOrder;
import com.example.wuzzaf.helpers.Constant;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    public void pupUi(final TextView txtVResult, String userName) {

        contactsCollectionReference
                .whereEqualTo("employName", userName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                if (document.getData().isEmpty()) {
                                    txtVResult.setText("waiting for any company see your cv ");
                                } else {
                                    String company = document.getData().get("companyName").toString();
                                    txtVResult.setText("Congratulation \n you are accepted for jop in " + "\n \t" + company + "\n company");

                                }

                            }

                        }
                    }
                });

    }
}

