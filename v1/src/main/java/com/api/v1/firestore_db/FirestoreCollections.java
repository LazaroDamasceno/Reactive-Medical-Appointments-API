package com.api.v1.firestore_db;

import com.google.cloud.firestore.CollectionReference;
import com.google.firebase.cloud.FirestoreClient;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FirestoreCollections {

    public CollectionReference peopleCollection() {
        return FirestoreClient.getFirestore().collection("people");
    }

    public CollectionReference customersCollection() {
        return FirestoreClient.getFirestore().collection("customers");
    }

    public CollectionReference doctorsCollection() {
        return FirestoreClient.getFirestore().collection("doctors");
    }

}
