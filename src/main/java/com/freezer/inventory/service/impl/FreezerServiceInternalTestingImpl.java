package com.freezer.inventory.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freezer.inventory.objects.FreezerItem;
import com.freezer.inventory.service.FreezerService;
import com.freezer.inventory.service.FreezerServiceInternalTesting;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.apache.http.client.utils.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class FreezerServiceInternalTestingImpl implements FreezerServiceInternalTesting {
    private final FreezerService freezerService;
    private static final String DATABASE_FREEZER = "freezer_items";
    private final Firestore dbFireStore = FirestoreClient.getFirestore();
    private static Logger logger = LogManager.getLogger(FreezerServiceInternalTestingImpl.class);

    public FreezerServiceInternalTestingImpl(FreezerService freezerService) {
        this.freezerService = freezerService;
    }

    @Override
    public String exportDatabaseItemsToJSON() throws ExecutionException, InterruptedException, JsonProcessingException {
        String path = "src/main/resources/datasets/firebase_" + DateUtils.formatDate(new Date(), "yyyy_MM_dd_HH_mm_ss") + ".json";
        File filePath = new File(path);
//        String absolutePath = filePath.getAbsolutePath();
        ApiFuture<QuerySnapshot> future = dbFireStore.collection(DATABASE_FREEZER).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<FreezerItem> freezerItemList = new ArrayList<>();

        for (DocumentSnapshot document : documents) {
            FreezerItem freezerItem = document.toObject(FreezerItem.class);
            freezerItemList.add(freezerItem);
        }

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(freezerItemList);
        logger.info("Data to json string => ", jsonString);

        try {
            FileWriter file = new FileWriter(filePath);
            file.write(jsonString);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Json exported on: " + new Date();
    }

    @Override
    // TODO add exception for file not found
    public String importDataFromFileToFirebase() throws IOException, ExecutionException, InterruptedException {
        String path = "src/main/resources/datasets/firebase_2023_12_04_23_08_09.json";
        File filePath = new File(path);

        ObjectMapper mapper = new ObjectMapper();
        List<FreezerItem> freezerItemList = Arrays.asList(mapper.readValue(filePath, FreezerItem[].class));

        for (FreezerItem freezerItem : freezerItemList) {
            freezerService.createFreezerItem(freezerItem);
            logger.info("Pushed objects to Firebase=> ", freezerItem.toString());
        }

        return "Json to firebase excecuted on: " + new Date();
    }

}
