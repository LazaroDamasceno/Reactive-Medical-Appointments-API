package com.api.v1.firestore_db;

import com.google.cloud.firestore.CollectionReference;
import com.google.firebase.cloud.FirestoreClient;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DbSets {

    public CollectionReference peopleCollection() {
        return FirestoreClient.getFirestore().collection("people");
    }

    public CollectionReference customerCollection() {
        return FirestoreClient.getFirestore().collection("customers");
    }

    public CollectionReference doctorCollection() {
        return FirestoreClient.getFirestore().collection("doctors");
    }


}
