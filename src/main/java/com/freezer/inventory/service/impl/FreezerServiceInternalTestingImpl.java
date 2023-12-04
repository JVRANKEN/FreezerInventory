package com.freezer.inventory.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freezer.inventory.objects.FreezerItem;
import com.freezer.inventory.service.FreezerServiceInternalTesting;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class FreezerServiceInternalTestingImpl implements FreezerServiceInternalTesting {
    private static final String DATABASE_FREEZER = "freezer_items";
    private final Firestore dbFireStore = FirestoreClient.getFirestore();

    @Override
    public String getAllFreezerItemsToJSON() throws ExecutionException, InterruptedException, JsonProcessingException {
        URL path = this.getClass().getClassLoader().getResource("/data");

        ApiFuture<QuerySnapshot> future = dbFireStore.collection(DATABASE_FREEZER).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<FreezerItem> freezerItemList = new ArrayList<>();

        for (DocumentSnapshot document : documents) {
            FreezerItem freezerItem = document.toObject(FreezerItem.class);
            freezerItemList.add(freezerItem);
        }

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(freezerItemList);


        try (PrintWriter out = new PrintWriter(new FileWriter(String.valueOf(path)))) {
            out.write(jsonString.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Json exported on: " + new Date();
    }

}
