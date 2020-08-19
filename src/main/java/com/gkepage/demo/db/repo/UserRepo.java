package com.gkepage.demo.db.repo;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gkepage.demo.db.model.User;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.common.collect.Lists;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepo  {

    private Firestore db;

    public UserRepo() throws Exception{

        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("src/main/docker/[JSONFILE].json"))
        .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
        FirestoreOptions firestoreOptions = 
                FirestoreOptions.newBuilder()
                        .setProjectId("[PROJECT_ID]")
                        .setCredentials(credentials)
                        .build();

        Firestore db = firestoreOptions.getService();
        this.db = db;
    }

    // Specify ProjectId
    public UserRepo(String projectId) throws Exception{

        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("src/main/docker/[JSONFILE].json"))
        .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
        FirestoreOptions firestoreOptions = 
                FirestoreOptions.newBuilder()
                        .setProjectId("[PROJECT_ID]")
                        .setCredentials(credentials)
                        .build();

        Firestore db = firestoreOptions.getService();
        this.db = db;
        }

        Firestore getDb(){
            return db;
        }

        public void addUser(String collection, String fromMbti, String toMbti, String stars) throws Exception{
            System.out.println("addDocument starts with [collection] : " + collection);
            switch (collection){
                case "users":{
                    DocumentReference docRef = db.collection("users").document();
                    Map<String, Object> data = new HashMap<>();
                    data.put("from", fromMbti);
                    data.put("stars",Long.parseLong(stars));
                    data.put("to", toMbti);

                    ApiFuture<WriteResult> result = docRef.set(data);

                    System.out.println("Update time : " + result.get().getUpdateTime());

                    break;
                }
                default:
            }
        }

        public Map<String, Object> getAllDocuments(Map<String, Object> mbtiArray, String to) throws Exception{
            System.out.println("getAllDocuments starts with [mbtiArray] : " + mbtiArray);
            System.out.println("getAllDocuments starts with [toMbti] : " + to);

            ApiFuture<QuerySnapshot> query = db.collection("users").get();

            Map<String, Object> mbtiSum = new HashMap<>(); // 총합을 나눌 개수

            QuerySnapshot querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            for (QueryDocumentSnapshot document : documents){
                String tmpFrom = document.getString("from");
                String tmpTo = document.getString("to");
                int tmpStars = ((Long) document.get("stars")).intValue();
                
                System.out.println("From: " + tmpFrom + ", To: " + tmpTo + ", Stars: " + tmpStars);
                if(tmpTo.equals(to)){
                    if(mbtiSum.get(tmpFrom) == null){
                        mbtiSum.put(tmpFrom, 1);
                    }else{
                        mbtiSum.put(tmpFrom, (int)mbtiSum.get(tmpFrom)+1);
                    }
                        mbtiArray.put(tmpFrom, (int)mbtiArray.get(tmpFrom) + tmpStars);
                }
            }   
            System.out.println("before calculating mbtiArray: "+mbtiArray.toString());
            System.out.println("before calculating mbtiSum: "+mbtiSum.toString());
            

            for(String key: mbtiArray.keySet()){
                if(mbtiSum.get(key) != null)
                mbtiArray.put(key, (Double.parseDouble(mbtiArray.get(key).toString())/(int)mbtiSum.get(key)));
                else  mbtiArray.put(key, 0.0);
            }
            /*
            mbtiArray.forEach((key, value)
            -> mbtiArray.put(key, (Double.parseDouble(value.toString())/(int)mbtiSum.get(key))));
            */

            System.out.println("after calculating mbtiArray: "+mbtiArray.toString());
            System.out.println("after calculating mbtiSum: "+mbtiSum.toString());
            return mbtiArray;
        }

}