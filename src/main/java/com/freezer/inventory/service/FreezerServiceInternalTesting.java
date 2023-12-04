package com.freezer.inventory.service;


import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface FreezerServiceInternalTesting {
    String exportDatabaseItemsToJSON() throws ExecutionException, InterruptedException, JsonProcessingException;

    String importDataFromFileToFirebase() throws IOException, ExecutionException, InterruptedException;
}
