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

import com.example.wuzzaf.entities.EmplyCv;
import com.example.wuzzaf.entities.JopRequirements;

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



import static com.example.wuzzaf.firestoreStructure.EmployFirestoreDbContract.COLLECTION_NAME;

public class EmployFirestoreManager {

    /* ContactsFirestoreManager object **/
    private static EmployFirestoreManager employFirestoreManager;

    private FirebaseFirestore firebaseFirestore;
    private CollectionReference contactsCollectionReference;
    SharedPrefrenceHelper sharedPrefrenceHelper = new SharedPrefrenceHelper();

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

    public void updateContact(EmplyCv contact,String documentId) {
        //String documentId = contact.getDocumentId();
        DocumentReference documentReference = contactsCollectionReference.document(documentId);
        documentReference.set(contact);
    }

    public void deleteContact(String documentId) {
        DocumentReference documentReference = contactsCollectionReference.document(documentId);
        documentReference.delete();
    }

    public void sendContactsBulk(String name, String age, String phone, String collage, String degee, String grade, String userName,String experienceYears) {
        createDocument(new EmplyCv(name, age, phone, collage, degee, grade, userName,experienceYears));

    }

    public void popUI (String userName,
                       final EditText name,
                       final EditText age,
                       final EditText phone,
                       final EditText experienceYear,
                       final Spinner collage,
                       final EditText degree,
                       final Spinner grade,
                       final Button save,
                       final TextView id,
                       final Context context) {

        contactsCollectionReference
                .whereEqualTo("userName", userName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.getData().isEmpty()) {
                                    save.setText("Save");
                                } else {

                                    try {
                                        save.setText("Update");
                                        name.setText(document.getData().get("name").toString());
                                        age.setText(document.getData().get("age").toString());
                                        experienceYear.setText(document.getData().get("experienceYears").toString());
                                        phone.setText(document.getData().get("phone").toString());
                                        degree.setText(document.getData().get("degee").toString());
                                        id.setText(document.getId());

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                }
                                String  isAttendance =  document.getData().get("grade").toString();

                                Log.d("tag", document.getId() + " => " + isAttendance);
                            }

                        } else {

                            ToastMessage.addMessage("false", context);
                            // Log.d("tag", "Error getting documents: ", task.getException());
                        }
                    }
                });

    }


    public void popUID (String userName,
                       final TextView name,
                       final TextView age,
                       final TextView phone,
                       final TextView experienceYear,
                       final TextView collage,
                       final TextView degree,
                       final TextView grade,
                       final TextView id,
                       final Context context) {

        contactsCollectionReference
                .whereEqualTo("userName", userName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.getData().isEmpty()) {

                                } else {

                                    try {
                                        name.setText(document.getData().get("name").toString());
                                        age.setText(document.getData().get("age").toString());
                                        experienceYear.setText(document.getData().get("experienceYears").toString());
                                        phone.setText(document.getData().get("phone").toString());
                                        degree.setText(document.getData().get("degee").toString());
                                        collage.setText(document.getData().get("collage").toString());
                                        grade.setText(document.getData().get("grade").toString());
                                        id.setText(document.getId());

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                }

                            }

                        }
                    }
                });

    }

    public void popUISuggest (String userName,
                        final TextView experienceYear,
                        final TextView collage,
                        final TextView grade,
                        final Context context) {

        contactsCollectionReference
                .whereEqualTo("userName", userName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.getData().isEmpty()) {

                                } else {

                                    try {

                                        experienceYear.setText(document.getData().get("experienceYears").toString());
                                        collage.setText(document.getData().get("collage").toString());
                                        grade.setText(document.getData().get("grade").toString());


                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                }

                            }

                        }
                    }
                });

    }


}
