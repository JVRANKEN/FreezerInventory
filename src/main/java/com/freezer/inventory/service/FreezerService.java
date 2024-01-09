package com.freezer.inventory.service;


import com.freezer.inventory.objects.FreezerItem;

import java.text.ParseException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface FreezerService {

    List<FreezerItem> getAllFreezerItems() throws ExecutionException, InterruptedException;

    List<FreezerItem> getFreezerItemByItemName(final String item) throws ExecutionException, InterruptedException;

    List<FreezerItem> getFreezerItemByType(String type) throws ExecutionException, InterruptedException;

    List<FreezerItem> getFreezerItemByCategory(String category) throws ExecutionException, InterruptedException;

    List<FreezerItem> getFreezerItemByDateExpire(String dateExpiry) throws ExecutionException, InterruptedException, ParseException;

    List<FreezerItem> getFreezerItemByFrozenDateAndMaxMonths(String date, int maxMonths) throws ExecutionException, InterruptedException, ParseException;

    String createFreezerItem(FreezerItem person) throws ExecutionException, InterruptedException;

    String updateFreezerItem(FreezerItem freezerItem) throws ExecutionException, InterruptedException;

    String deleteFreezerItem(String documentId) throws ExecutionException, InterruptedException;
    String deleteFreezerItemByName(String itemName) throws ExecutionException, InterruptedException;
}
