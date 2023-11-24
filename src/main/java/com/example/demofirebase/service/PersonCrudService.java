package com.example.demofirebase.service;


import com.example.demofirebase.objects.Person;

import java.util.concurrent.ExecutionException;

public interface PersonCrudService {

    Person getCRUDPerson(final String objectId) throws ExecutionException, InterruptedException;

    String createCRUDPerson(Person person) throws ExecutionException, InterruptedException;

    String updateCRUDPerson(Person person) throws ExecutionException, InterruptedException;

    String deleteCRUDPerson(String documentId) throws ExecutionException, InterruptedException;
}
