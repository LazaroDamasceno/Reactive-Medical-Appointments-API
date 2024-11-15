package com.api.v1.config.firestore_db;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirestoreDbConfig {

    @PostConstruct
    public void configuration() throws IOException {
        String path = "src/main/resources/private_key/serviceAccountKey.json";
        FileInputStream serviceAccount = new FileInputStream(path);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://spring-api-db-1a696-default-rtdb.firebaseio.com")
                .build();
        FirebaseApp.initializeApp(options);
    }

}
