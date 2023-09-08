package com.example.bookStoreProject.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.bookStoreProject.entity.Inventory;
import com.example.bookStoreProject.repository.InventoryRepository;
import com.example.bookStoreProject.services.InventoryService;

import java.util.List;
import java.util.Map;

@Service
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public List<Inventory> getAllInventoryItems() {
        return inventoryRepository.findAll();
    }

    @Override
    public Inventory getInventoryItemById(Long inventoryItemId) {
        return inventoryRepository.findById(inventoryItemId).orElse(null);
    }

    @Override
    public Inventory createInventoryItem(Inventory inventoryItem) {
        return inventoryRepository.save(inventoryItem);
    }

    @Override
    public Inventory updateInventoryItem(Long inventoryItemId, Inventory inventoryItem) {
        if (inventoryRepository.existsById(inventoryItemId)) {
            inventoryItem.setInventoryID(inventoryItemId);
            return inventoryRepository.save(inventoryItem);
        }
        return null;
    }

    @Override
    public void deleteInventoryItem(Long inventoryItemId) {
        inventoryRepository.deleteById(inventoryItemId);
    }

	@Override
	public ResponseEntity<String> updateInv(Map<String, String> map) {
		// TODO Auto-generated method stub
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
