package com.freezer.inventory.service;


import com.freezer.inventory.objects.FreezerItem;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface FreezerService {

    List<FreezerItem> getFreezerItemByItemName(final String item) throws ExecutionException, InterruptedException;

    List<FreezerItem> getFreezerItemByType(String type) throws ExecutionException, InterruptedException;

    List<FreezerItem> getFreezerItemByCategory(String category) throws ExecutionException, InterruptedException;

    List<FreezerItem> getFreezerItemByDateExpire(Date dateExpiry) throws ExecutionException, InterruptedException;

    List<FreezerItem> getFreezerItemByFrozenDateAndMaxMonths(Date date) throws ExecutionException, InterruptedException;

    // getFreezeritembeforeDate
    // getFreezeritemByCategory
    String createFreezerItem(FreezerItem person) throws ExecutionException, InterruptedException;

    String updateFreezerItem(FreezerItem person) throws ExecutionException, InterruptedException;

    String deleteFreezerItem(String documentId) throws ExecutionException, InterruptedException;
    // delete freezer item quantity , or lower it
}
