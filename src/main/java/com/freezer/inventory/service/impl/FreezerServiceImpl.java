package com.freezer.inventory.service.impl;

import com.freezer.inventory.objects.FreezerItem;
import com.freezer.inventory.service.FreezerService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class FreezerServiceImpl implements FreezerService {
    private static final String DATABASE_PERSON = "crud_person";
    private Firestore dbFireStore = FirestoreClient.getFirestore();

    @Override
    public FreezerItem getFreezerItemByItemName(String objectId) throws ExecutionException, InterruptedException {
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
    public String createFreezerItem(FreezerItem person) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> collectionApiFuture = dbFireStore.collection(DATABASE_PERSON)
                .document(String.valueOf(person.getId()))
                .set(person);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

    @Override
    public String updateFreezerItem(FreezerItem person) throws ExecutionException, InterruptedException {
        // In our cases -> we are going to update not on firstName but on objectId
        ApiFuture<WriteResult> collectionApiFuture = dbFireStore.collection(DATABASE_PERSON)
                .document(String.valueOf(person.getId()))
                .set(person);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

    @Override
    public String deleteFreezerItem(String documentId) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> writeResultApiFuture =  dbFireStore.collection(DATABASE_PERSON).document(documentId).delete();

        return "deleted on: " + writeResultApiFuture.get().getUpdateTime().toString();
    }
}
