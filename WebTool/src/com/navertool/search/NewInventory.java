package com.navertool.search;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * request the change sorting on the result
 */

public class NewInventory {

    private static Logger m_logger = LoggerFactory.getLogger(NewInventory.class);

    private String  datePurchased;
    private String  productNum;
    private String  productDescription;
    private String  option1; // size
    private String  option2; // set id when cancels
    private String  option3;
    
    private double  price;
    private int     qty;

    private String orderStore; 
    private String orderNum;                // store order number
    private String orderPlacementNum;       // store order number, or original OrderPlacementNum
    private String dateArrived;
    private String orderProductNum;         // If this is returned/cancelled item
    private boolean removed;
    
    private boolean needToReturnToStore;  // Cancelled or returned item
    private String note;

    
    public String getDatePurchased() {
        return datePurchased;
    }
    public void setDatePurchased(String datePurchased) {
        this.datePurchased = datePurchased;
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
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getQty() {
        return qty;
    }
    public void setQty(int qty) {
        this.qty = qty;
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
    public String getDateArrived() {
        return dateArrived;
    }
    public void setDateArrived(String dateArrived) {
        this.dateArrived = dateArrived;
    }
    public boolean isNeedToReturnToStore() {
        return needToReturnToStore;
    }
    public void setNeedToReturnToStore(boolean needToReturnToStore) {
        this.needToReturnToStore = needToReturnToStore;
    }
    @Override
    public String toString() {
        return "NewInventory [datePurchased=" + datePurchased + ", productNum=" + productNum + ", productDescription=" + productDescription + ", option1=" + option1 + ", option2=" + option2
                + ", option3=" + option3 + ", price=" + price + ", qty=" + qty + ", orderStore=" + orderStore + ", orderNum=" + orderNum + ", orderPlacementNum=" + orderPlacementNum + ", dateArrived="
                + dateArrived + ", needToReturnToStore=" + needToReturnToStore + "]";
    }
    public String getOrderProductNum() {
        return orderProductNum;
    }
    public void setOrderProductNum(String orderProductNum) {
        this.orderProductNum = orderProductNum;
    }
    public boolean isRemoved() {
        return removed;
    }
    public void setRemoved(boolean removed) {
        this.removed = removed;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    

    
    
}
