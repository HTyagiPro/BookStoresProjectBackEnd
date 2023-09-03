package com.example.bookStoreProject.services;

import java.util.List;

import com.example.bookStoreProject.entity.Inventory;

public interface InventoryService {
    List<Inventory> getAllInventoryItems();
    Inventory getInventoryItemById(Long inventoryItemId);
    Inventory createInventoryItem(Inventory inventoryItem);
    Inventory updateInventoryItem(Long inventoryItemId, Inventory inventoryItem);
    void deleteInventoryItem(Long inventoryItemId);
}
