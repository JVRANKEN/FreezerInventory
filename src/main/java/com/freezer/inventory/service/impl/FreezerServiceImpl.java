package com.freezer.inventory.service.impl;

import com.freezer.inventory.objects.FreezerItem;
import com.freezer.inventory.service.FreezerService;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class FreezerServiceImpl implements FreezerService {
    private static final String DATABASE_PERSON = "freezer_items";
    private Firestore dbFireStore = FirestoreClient.getFirestore();

    private FreezerItem getFreezerItemByItemId(String objectId) throws ExecutionException, InterruptedException {
        DocumentReference documentReference = dbFireStore.collection(DATABASE_PERSON)
                .document(objectId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        FreezerItem person;
        if (document.exists()) {
            person = document.toObject(FreezerItem.class);

            return person;
        }
        return null;
    }

    @Override
    public FreezerItem getFreezerItemByItemName(String item) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = dbFireStore.collection(DATABASE_PERSON).whereEqualTo("item", item).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<FreezerItem> freezerItemList = new ArrayList<>();

        for (DocumentSnapshot document : documents) {
            FreezerItem freezerItem =  document.toObject(FreezerItem.class);
            freezerItemList.add(freezerItem);
        }

        freezerItemList.size();
        return null;
    }

    @Override
    public String createFreezerItem(FreezerItem freezerItem) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> collectionApiFuture = dbFireStore.collection(DATABASE_PERSON)
                .document(String.valueOf(freezerItem.getId()))
                .set(freezerItem);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

    @Override
    public String updateFreezerItem(FreezerItem freezerItem) throws ExecutionException, InterruptedException {
        // In our cases -> we are going to update not on firstName but on objectId
        ApiFuture<WriteResult> collectionApiFuture = dbFireStore.collection(DATABASE_PERSON)
                .document(String.valueOf(freezerItem.getId()))
                .set(freezerItem);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

    @Override
    public String deleteFreezerItem(String documentId) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> writeResultApiFuture = dbFireStore.collection(DATABASE_PERSON).document(documentId).delete();

        return "deleted on: " + writeResultApiFuture.get().getUpdateTime().toString();
    }
}
