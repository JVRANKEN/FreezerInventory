package com.freezer.inventory.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freezer.inventory.objects.FreezerItem;
import com.freezer.inventory.service.FreezerServiceInternalTesting;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.apache.http.client.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class FreezerServiceInternalTestingImpl implements FreezerServiceInternalTesting {
    private static final String DATABASE_FREEZER = "freezer_items";
    private final Firestore dbFireStore = FirestoreClient.getFirestore();

    @Override
    public String exportDatabaseItemsToJSON() throws ExecutionException, InterruptedException, JsonProcessingException {
        String path = "src/main/resources/datasets/firebase_" + DateUtils.formatDate(new Date(), "yyyy_MM_dd_HH_mm_ss") + ".json";
        File filePath = new File(path);
        String absolutePath = filePath.getAbsolutePath();
        ApiFuture<QuerySnapshot> future = dbFireStore.collection(DATABASE_FREEZER).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<FreezerItem> freezerItemList = new ArrayList<>();

        for (DocumentSnapshot document : documents) {
            FreezerItem freezerItem = document.toObject(FreezerItem.class);
            freezerItemList.add(freezerItem);
        }

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(freezerItemList);

        try {
            FileWriter file = new FileWriter(absolutePath);
            file.write(jsonString);
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "Json exported on: " + new Date();
    }

}
