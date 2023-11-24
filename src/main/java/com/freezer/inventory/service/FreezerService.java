package com.freezer.inventory.service;


import com.freezer.inventory.objects.FreezerItem;

import java.util.concurrent.ExecutionException;

public interface FreezerService {

    FreezerItem getFreezerItemByItemName(final String item) throws ExecutionException, InterruptedException;

    // getFreezeritembeforeDate
    // getFreezeritemByCategory
    String createFreezerItem(FreezerItem person) throws ExecutionException, InterruptedException;

    String updateFreezerItem(FreezerItem person) throws ExecutionException, InterruptedException;

    String deleteFreezerItem(String documentId) throws ExecutionException, InterruptedException;
    // delete freezer item quantity , or lower it
}
