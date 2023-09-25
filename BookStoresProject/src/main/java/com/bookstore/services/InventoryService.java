package com.bookstore.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.bookstore.entity.Inventory;

public interface InventoryService {
    List<Inventory> getAllInventoryItems();
    Inventory getInventoryItemById(Long inventoryItemId);
    Inventory createInventoryItem(Inventory inventoryItem);
    Inventory updateInventoryItem(Long inventoryItemId, Inventory inventoryItem);
    void deleteInventoryItem(Long inventoryItemId);
    public ResponseEntity<String> updateInv(Map<String, String>map);
}
