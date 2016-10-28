package com.navertool.db.booking;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * status of inventory item
 * 
 * 1. Inventory 
 * 
 * 
 * 
 * 
 */



@Entity
@Table(name = "naver_inventory")
public class NaverOrderInventory extends NaverModel{

    private static Logger m_logger = LoggerFactory.getLogger(NaverOrderInventory.class);

    private Integer id;
    private String  productNum;
    private String  productDescription;
    private String  option1; // size
    private String  option2;
    private String  option3;
    
    private double price;
    
    private boolean returnedItem;  // Cancelled or returned item
    private boolean needToReturnToStore;  // Cancelled or returned item
    
    private String orderStore; 
    private String orderNum;                // store order number
    private String orderPlacementNum;       // store order number, or original OrderPlacementNum like 2016102573099771
    private String inventorySequence;
    private String datePurchased;
    private String dateExpected;
    private String dateMarkReceived;        // Mark when received
    private String note;
    private String naverOrderNum;
    private String modelOrderNum; // to get product description
    
    private String assignedToOrderNum; // in case this item was assigned to specific order
    private String dateAssigned; // in case this item was assigned to specific order
    private String placeInKeep; // 보관장소 한국,미국
    
    //Default Constructor
    public NaverOrderInventory() {
    }
    
    @Id
    @GeneratedValue
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOrderStore() {
        return orderStore;
    }

    public void setOrderStore(String orderStore) {
        this.orderStore = orderStore;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getOrderPlacementNum() {
        return orderPlacementNum;
    }

    public void setOrderPlacementNum(String orderPlacementNum) {
        this.orderPlacementNum = orderPlacementNum;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInventorySequence() {
        return inventorySequence;
    }

    public void setInventorySequence(String inventorySequence) {
        this.inventorySequence = inventorySequence;
    }

    public String getNaverOrderNum() {
        return naverOrderNum;
    }

    public void setNaverOrderNum(String naverOrderNum) {
        this.naverOrderNum = naverOrderNum;
    }

    public String getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(String datePurchased) {
        this.datePurchased = datePurchased;
    }

    public String getDateExpected() {
        return dateExpected;
    }

    public void setDateExpected(String dateExpected) {
        this.dateExpected = dateExpected;
    }

    @Column(length=4096)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getModelOrderNum() {
        return modelOrderNum;
    }

    public void setModelOrderNum(String modelOrderNum) {
        this.modelOrderNum = modelOrderNum;
    }

    public boolean isReturnedItem() {
        return returnedItem;
    }

    public void setReturnedItem(boolean returnedItem) {
        this.returnedItem = returnedItem;
    }

    public String getAssignedToOrderNum() {
        return assignedToOrderNum;
    }

    public void setAssignedToOrderNum(String assignedToOrderNum) {
        this.assignedToOrderNum = assignedToOrderNum;
    }

    public String getDateAssigned() {
        return dateAssigned;
    }

    public void setDateAssigned(String dateAssigned) {
        this.dateAssigned = dateAssigned;
    }

    
    
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isNeedToReturnToStore() {
        return needToReturnToStore;
    }

    
    
    public void setNeedToReturnToStore(boolean needToReturnToStore) {
        this.needToReturnToStore = needToReturnToStore;
    }

    @Override
    public String toString() {
        return "NaverOrderInventory [id=" + id + ", productNum=" + productNum + ", productDescription=" + productDescription + ", option1=" + option1 + ", option2=" + option2 + ", option3=" + option3
                + ", price=" + price + ", returnedItem=" + returnedItem + ", orderStore=" + orderStore + ", orderNum=" + orderNum + ", orderPlacementNum=" + orderPlacementNum + ", inventorySequence="
                + inventorySequence + ", datePurchased=" + datePurchased + ", dateExpected=" + dateExpected + ", note=" + note + ", naverOrderNum=" + naverOrderNum + ", modelOrderNum=" + modelOrderNum
                + ", assignedToOrderNum=" + assignedToOrderNum + ", dateAssigned=" + dateAssigned + "]";
    }

    public String getPlaceInKeep() {
        return placeInKeep;
    }

    public void setPlaceInKeep(String placeInKeep) {
        this.placeInKeep = placeInKeep;
    }

    
    
}
