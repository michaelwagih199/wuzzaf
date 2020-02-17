package com.example.wuzzaf.firestoreStructure;

public class UsersFirestoreDbContract {


    // Root collection name
    public static final String COLLECTION_NAME = "users";
   // Document ID
    public static final String DOCUMENT_ID = "document_id";

    // Document field names
    public static final String FIELD_FIRST_NAME = "first_name";
    public static final String FIELD_LAST_NAME = "last_name";
    public static final String FIELD_PASSWORD = "password";
    public static final String FIELD_USER_NAME = "user_name";
    public static final String FIELD_USER_TYPE = "user_type";


    // To prevent someone from accidentally instantiating the usrers 		class, make the constructor private
    private UsersFirestoreDbContract() {}

}
