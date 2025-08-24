package com.sd.demo.config;

import java.io.FileInputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class FirebaseConfig {

    public static void initialize() throws Exception {
        FileInputStream serviceAccount = new FileInputStream("./bank-db-4e66b-firebase-adminsdk-fbsvc-10a6145a2e.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://bank-db-4e66b.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
    }
}
