package com.atitus.tcc.service;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class FirestoreService {

    public static String getToken() throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore(FirebaseApp.getInstance("tccapp"));

        String token = (String) db.collection("user_token").get().get().getDocuments().get(0).get("token");

        System.out.println("TOKEN: "+token);
        return token;
    }

}
