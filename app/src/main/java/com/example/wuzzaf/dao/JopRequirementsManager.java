package com.example.wuzzaf.dao;

import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wuzzaf.Adapters.JobSugesstionAdapter;
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

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

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

    public void updateContact(JopRequirements contact, String documentId) {
        //String documentId = contact.getDocumentId();
        DocumentReference documentReference = contactsCollectionReference.document(documentId);
        documentReference.set(contact);
    }

    public void deleteContact(String documentId) {
        DocumentReference documentReference = contactsCollectionReference.document(documentId);
        documentReference.delete();
    }

    public void sendContactsBulk(String collage, String degree, String grade, String experienceYear, String age, String jopTitle, String jopDescription, String id) {
        createDocument(new JopRequirements(collage, degree, grade, experienceYear, age, jopTitle, jopDescription, id));
    }

    public void popUI(String userName,
                      final EditText jopDescription,
                      final EditText jopTitle,
                      final EditText age,
                      final Spinner collage,
                      final EditText degree,
                      final EditText ExperienceYear,
                      final Spinner grade,
                      final Button save,
                      final TextView id,
                      final Context context) {

        contactsCollectionReference
                .whereEqualTo("id", userName)
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
                                        jopDescription.setText(document.getData().get("jopDescription").toString());
                                        jopTitle.setText(document.getData().get("jopTitle").toString());
                                        age.setText(document.getData().get("age").toString());
                                        degree.setText(document.getData().get("degree").toString());
                                        ExperienceYear.setText(document.getData().get("experienceYear").toString());
                                        id.setText(document.getId());

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                }
                                String isAttendance = document.getData().get("grade").toString();

                                Log.d("tag", document.getId() + " => " + isAttendance);
                            }

                        } else {

                            ToastMessage.addMessage("false", context);
                            // Log.d("tag", "Error getting documents: ", task.getException());
                        }
                    }
                });

    }

    public void getData(final Context context, final RecyclerView recyclerView,String college,String experyear,String grade) {

        contactsCollectionReference
                .whereEqualTo("collage", college)
                .whereEqualTo("experienceYears",experyear)
                .whereEqualTo("grade",grade)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<JopRequirements> list = new ArrayList<>();
                    for (DocumentSnapshot document : task.getResult()) {
                        JopRequirements taskItem = document.toObject(JopRequirements.class);
                        list.add(taskItem);
                        JobSugesstionAdapter jobSugesstionAdapter = new JobSugesstionAdapter(context, (ArrayList<JopRequirements>) list);
                        recyclerView.setAdapter(jobSugesstionAdapter);
                    }
                    Log.d("Tag", list.toString());
                }
            }
        });
    }


    public void getData2(@NotNull final Context mContext, @NotNull final RecyclerView recycleCustomer) {
        contactsCollectionReference
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<JopRequirements> list = new ArrayList<>();
                    for (DocumentSnapshot document : task.getResult()) {
                        JopRequirements taskItem = document.toObject(JopRequirements.class);
                        list.add(taskItem);
                        JobSugesstionAdapter jobSugesstionAdapter = new JobSugesstionAdapter(mContext, (ArrayList<JopRequirements>) list);
                        recycleCustomer.setAdapter(jobSugesstionAdapter);
                    }
                    Log.d("Tag", list.toString());
                }
            }
        });

    }
}
