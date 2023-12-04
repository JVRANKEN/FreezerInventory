package com.freezer.inventory.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freezer.inventory.objects.FreezerItem;
import com.freezer.inventory.service.FreezerService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class FreezerServiceImpl implements FreezerService {
    private static final String DATABASE_FREEZER = "freezer_items";
    private final Firestore dbFireStore = FirestoreClient.getFirestore();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public List<FreezerItem> getAllFreezerItems() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = dbFireStore.collection(DATABASE_FREEZER).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<FreezerItem> freezerItemList = new ArrayList<>();

        for (DocumentSnapshot document : documents) {
            FreezerItem freezerItem = document.toObject(FreezerItem.class);
            freezerItemList.add(freezerItem);
        }

        return freezerItemList;
    }

    @Override
    public List<FreezerItem> getFreezerItemByItemName(String item) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = dbFireStore.collection(DATABASE_FREEZER).whereEqualTo("item", item).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<FreezerItem> freezerItemList = new ArrayList<>();

        for (DocumentSnapshot document : documents) {
            FreezerItem freezerItem = document.toObject(FreezerItem.class);
            freezerItemList.add(freezerItem);
        }

        return freezerItemList;
    }

    @Override
    public List<FreezerItem> getFreezerItemByType(String type) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = dbFireStore.collection(DATABASE_FREEZER).whereEqualTo("type", type).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<FreezerItem> freezerItemList = new ArrayList<>();

        for (DocumentSnapshot document : documents) {
            FreezerItem freezerItem = document.toObject(FreezerItem.class);
            freezerItemList.add(freezerItem);
        }

        return freezerItemList;
    }

    @Override
    public List<FreezerItem> getFreezerItemByCategory(String category) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = dbFireStore.collection(DATABASE_FREEZER).whereEqualTo("category", category).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<FreezerItem> freezerItemList = new ArrayList<>();

        for (DocumentSnapshot document : documents) {
            FreezerItem freezerItem = document.toObject(FreezerItem.class);
            freezerItemList.add(freezerItem);
        }

        return freezerItemList;
    }

    /**
     * Add one day because of the Timestamp database save in Firebase*
     *
     * @param dateExpiry
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws ParseException
     */
    @Override
    public List<FreezerItem> getFreezerItemByDateExpire(String dateExpiry) throws ExecutionException, InterruptedException, ParseException {
        Date dateUpdated = DateUtils.addDays(dateFormat.parse(dateExpiry), 1);

        ApiFuture<QuerySnapshot> future = dbFireStore.collection(DATABASE_FREEZER).whereLessThanOrEqualTo("expiryDate", dateUpdated).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<FreezerItem> freezerItemList = new ArrayList<>();

        for (DocumentSnapshot document : documents) {
            FreezerItem freezerItem = document.toObject(FreezerItem.class);
            freezerItemList.add(freezerItem);
        }

        return freezerItemList;
    }

    /**
     * Add one day because of the Timestamp database save in Firebase
     *
     * @param date
     * @param maxMonths
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws ParseException
     */
    @Override
    public List<FreezerItem> getFreezerItemByFrozenDateAndMaxMonths(String date, int maxMonths) throws ExecutionException, InterruptedException, ParseException {
        Date dateUpdated = DateUtils.addMonths(dateFormat.parse(date), maxMonths);
        dateUpdated = DateUtils.addDays(dateUpdated, 1);
        ApiFuture<QuerySnapshot> future = dbFireStore.collection(DATABASE_FREEZER).whereLessThanOrEqualTo("frozenDate", dateUpdated).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<FreezerItem> freezerItemList = new ArrayList<>();

        for (DocumentSnapshot document : documents) {
            FreezerItem freezerItem = document.toObject(FreezerItem.class);
            freezerItemList.add(freezerItem);
        }

        return freezerItemList;
    }

    @Override
    public String createFreezerItem(FreezerItem freezerItem) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentReference> collectionApiFuture = dbFireStore.collection(DATABASE_FREEZER)
                .add(freezerItem);

        return collectionApiFuture.get().toString();
    }

    @Override
    public String updateFreezerItem(FreezerItem freezerItem, String documentId) throws ExecutionException, InterruptedException {
        // In our cases -> we are going to update not on firstName but on objectId
        ApiFuture<WriteResult> collectionApiFuture = dbFireStore.collection(DATABASE_FREEZER)
                .document(documentId)
                .set(freezerItem);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

    @Override
    public String deleteFreezerItem(String documentId) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> writeResultApiFuture = dbFireStore.collection(DATABASE_FREEZER).document(documentId).delete();

        return "deleted on: " + writeResultApiFuture.get().getUpdateTime().toString();
    }

    @Override
    public String deleteFreezerItemByName(String itemName) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = dbFireStore.collection(DATABASE_FREEZER).whereEqualTo("item", itemName).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<String> logMessageList = new ArrayList<>();
        for (DocumentSnapshot document : documents) {
            String documentId = document.getId();
            ApiFuture<WriteResult> writeResultApiFuture = dbFireStore.collection(DATABASE_FREEZER).document(documentId).delete();
            logMessageList.add(itemName + " with DocID: <" + documentId + "> deleted on: " + writeResultApiFuture.get().getUpdateTime());
        }

        return String.join("/n", logMessageList);
    }
}
