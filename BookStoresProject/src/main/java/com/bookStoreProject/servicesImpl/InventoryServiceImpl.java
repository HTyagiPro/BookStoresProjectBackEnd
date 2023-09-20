package com.bookStoreProject.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookStoreProject.entity.Inventory;
import com.bookStoreProject.repository.InventoryRepository;
import com.bookStoreProject.services.InventoryService;

import java.util.List;
import java.util.Map;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    // Get a list of all inventory items
    @Override
    public List<Inventory> getAllInventoryItems() {
        return inventoryRepository.findAll();
    }

    // Get an inventory item by its ID
    @Override
    public Inventory getInventoryItemById(Long inventoryItemId) {
        return inventoryRepository.findById(inventoryItemId).orElse(null);
    }

    // Create a new inventory item
    @Override
    public Inventory createInventoryItem(Inventory inventoryItem) {
        return inventoryRepository.save(inventoryItem);
    }

    // Update an existing inventory item
    @Override
    public Inventory updateInventoryItem(Long inventoryItemId, Inventory inventoryItem) {
        if (inventoryRepository.existsById(inventoryItemId)) {
            inventoryItem.setInventoryID(inventoryItemId);
            return inventoryRepository.save(inventoryItem);
        }
        return null;
    }

    // Delete an inventory item by its ID
    @Override
    public void deleteInventoryItem(Long inventoryItemId) {
        inventoryRepository.deleteById(inventoryItemId);
    }

    // Update inventory levels based on the provided data
    @Override
    public ResponseEntity<String> updateInv(Map<String, String> map) {
        try {
            Inventory inventory = inventoryRepository.getInventoryByBookID(Long.parseLong(map.get("book")));
            inventory.setStockLevelNew(Integer.parseInt(map.get("new")));
            inventory.setStockLevelUsed(Integer.parseInt(map.get("used")));
            inventoryRepository.save(inventory);
            return new ResponseEntity<String>("Inventory Updated Successfully!!!", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>("Something Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}






















//package com.bookStoreProject.servicesImpl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import com.bookStoreProject.entity.Inventory;
//import com.bookStoreProject.repository.InventoryRepository;
//import com.bookStoreProject.services.InventoryService;
//
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class InventoryServiceImpl implements InventoryService {
//    private final InventoryRepository inventoryRepository;
//
//    @Autowired
//    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
//        this.inventoryRepository = inventoryRepository;
//    }
//
//    @Override
//    public List<Inventory> getAllInventoryItems() {
//        return inventoryRepository.findAll();
//    }
//
//    @Override
//    public Inventory getInventoryItemById(Long inventoryItemId) {
//        return inventoryRepository.findById(inventoryItemId).orElse(null);
//    }
//
//    @Override
//    public Inventory createInventoryItem(Inventory inventoryItem) {
//        return inventoryRepository.save(inventoryItem);
//    }
//
//    @Override
//    public Inventory updateInventoryItem(Long inventoryItemId, Inventory inventoryItem) {
//        if (inventoryRepository.existsById(inventoryItemId)) {
//            inventoryItem.setInventoryID(inventoryItemId);
//            return inventoryRepository.save(inventoryItem);
//        }
//        return null;
//    }
//
//    @Override
//    public void deleteInventoryItem(Long inventoryItemId) {
//        inventoryRepository.deleteById(inventoryItemId);
//    }
//
//	@Override
//	public ResponseEntity<String> updateInv(Map<String, String> map) {
//		// TODO Auto-generated method stub
//		try {
//			Inventory inventory = inventoryRepository.getInventoryByBookID(Long.parseLong(map.get("book")));
//			inventory.setStockLevelNew(Integer.parseInt(map.get("new")));
//			inventory.setStockLevelUsed(Integer.parseInt(map.get("used")));
//			inventoryRepository.save(inventory);
//			return new ResponseEntity<String>("Inventory Updated Successfully!!!", HttpStatus.OK);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return new ResponseEntity<String>("Something Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//}
