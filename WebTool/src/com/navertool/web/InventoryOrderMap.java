package com.navertool.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.navertool.db.booking.NaverOrderInventory;

public class InventoryOrderMap extends OrderMap<NaverOrderInventory, String, String> {

    private static Logger m_logger = LoggerFactory.getLogger(InventoryOrderMap.class);

    
    private List<NaverOrderInventory> orders;
    private String fieldForTop = "productNum";
    private String fieldForBottom = "option1";
    
    // Default Constructor
    public InventoryOrderMap(List<NaverOrderInventory> orders) {
        super();
        this.orders = orders;
        
        processOrderList(orders, String.class, String.class, fieldForTop, fieldForBottom);
    }
    
    public InventoryOrderMap(List<NaverOrderInventory> orders, String field1, String field2) {
        super();
        this.orders = orders;
        this.fieldForTop = field1;
        this.fieldForBottom = field2;
        
        processOrderList(orders, String.class, String.class, fieldForTop, fieldForBottom);
    }
    
    private static InventoryOrderMap m_instance = new InventoryOrderMap();

    public static InventoryOrderMap getInstance() {
        return m_instance; 
    }
    
    public void processInventory(NaverOrderInventory inventory) {
        processOrder(inventory, String.class, String.class, fieldForTop, fieldForBottom);
    }
    
    public void processInventory(List<NaverOrderInventory> inventory) {
        processOrderList(inventory, String.class, String.class, fieldForTop, fieldForBottom);
    }

    private InventoryOrderMap() {

    }
    
}
