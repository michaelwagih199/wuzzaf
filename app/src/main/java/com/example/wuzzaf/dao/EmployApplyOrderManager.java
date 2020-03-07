package com.example.wuzzaf.dao;

import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wuzzaf.Adapters.EmploySugesstionAdapter;
import com.example.wuzzaf.Adapters.JobSugesstionAdapter;
import com.example.wuzzaf.entities.EmployApplyOrder;
import com.example.wuzzaf.entities.EmplyCv;
import com.example.wuzzaf.entities.JopRequirements;
import com.example.wuzzaf.entities.Users;
import com.example.wuzzaf.helpers.SharedPrefrenceHelper;
import com.example.wuzzaf.helpers.ToastMessage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

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

    public void sendContactsBulk(String companyName, String userName) {
        createDocument(new EmployApplyOrder(companyName, userName));
    }


    //get user of company
    public void getData2(@NotNull final Context mContext, @NotNull final RecyclerView recycleCustomer, String userName) {
        contactsCollectionReference
                .whereEqualTo("companyName", userName)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<EmployApplyOrder> list = new ArrayList<>();
                    for (DocumentSnapshot document : task.getResult()) {
                        EmployApplyOrder taskItem = document.toObject(EmployApplyOrder.class);
                        list.add(taskItem);
                        EmploySugesstionAdapter jobSugesstionAdapter = new EmploySugesstionAdapter(mContext, (ArrayList<EmployApplyOrder>) list);
                        recycleCustomer.setAdapter(jobSugesstionAdapter);
                    }
                    Log.d("Tag", list.toString());
                }
            }
        });

    }


}
