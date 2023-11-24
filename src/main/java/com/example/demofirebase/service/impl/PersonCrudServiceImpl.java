package com.example.demofirebase.service.impl;

import com.example.demofirebase.objects.Person;
import com.example.demofirebase.service.PersonCrudService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class PersonCrudServiceImpl implements PersonCrudService {
    private static final String DATABASE_PERSON = "crud_person";
    private Firestore dbFireStore = FirestoreClient.getFirestore();

    @Override
    public Person getCRUDPerson(String objectId) throws ExecutionException, InterruptedException {
        DocumentReference documentReference = dbFireStore.collection(DATABASE_PERSON)
                .document(objectId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        Person person;
        if (document.exists()) {
            person = document.toObject(Person.class);

            return person;
        }
        return null;
    }

    @Override
    public String createCRUDPerson(Person person) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> collectionApiFuture = dbFireStore.collection(DATABASE_PERSON)
                .document(String.valueOf(person.getId()))
                .set(person);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

    @Override
    public String updateCRUDPerson(Person person) throws ExecutionException, InterruptedException {
        // In our cases -> we are going to update not on firstName but on objectId
        ApiFuture<WriteResult> collectionApiFuture = dbFireStore.collection(DATABASE_PERSON)
                .document(String.valueOf(person.getId()))
                .set(person);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

    @Override
    public String deleteCRUDPerson(String documentId) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> writeResultApiFuture =  dbFireStore.collection(DATABASE_PERSON).document(documentId).delete();

        return "deleted on: " + writeResultApiFuture.get().getUpdateTime().toString();
    }
}
