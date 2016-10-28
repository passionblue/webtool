package com.webtool.model;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PriceCheckRequest {

    private static Logger m_logger = LoggerFactory.getLogger(PriceCheckRequest.class);

    private String name;
    private String title;

    private BigDecimal price = new BigDecimal(100.0); ; 
    private BigDecimal discountPrice; 
    private BigDecimal targetPriceWon = new BigDecimal(100000.0);
    
    private BigDecimal fxRate = new BigDecimal(1150.0); ; 
    private BigDecimal shippingIn = new BigDecimal(0.0); 
    private BigDecimal shippingOut = new BigDecimal(10.0); 
    
    private BigDecimal rebatePercent = new BigDecimal(5.0);

    private BigDecimal targetShippingWon = new BigDecimal(29500.0);
    private BigDecimal discountRate = new BigDecimal(0.0); ; 
    
    public PriceCheckRequest(String name) {
        this.name = name;
    }
    public PriceCheckRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }
    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    
    
    public BigDecimal getTargetPriceWon() {
        return targetPriceWon;
    }
    public void setTargetPriceWon(BigDecimal targetPriceWon) {
        this.targetPriceWon = targetPriceWon;
    }
    public BigDecimal getFxRate() {
        return fxRate;
    }
    public void setFxRate(BigDecimal fxRate) {
        this.fxRate = fxRate;
    }
    
    public BigDecimal getShippingIn() {
        return shippingIn;
    }
    public void setShippingIn(BigDecimal shippingIn) {
        this.shippingIn = shippingIn;
    }
    public BigDecimal getShippingOut() {
        return shippingOut;
    }
    public void setShippingOut(BigDecimal shippingOut) {
        this.shippingOut = shippingOut;
    }
    
    public BigDecimal getRebatePercent() {
        return rebatePercent;
    }
    public void setRebatePercent(BigDecimal rebatePercent) {
        this.rebatePercent = rebatePercent;
    }
    
    public BigDecimal getDiscountRate() {
        return discountRate;
    }
    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    
    public BigDecimal getTargetShippingWon() {
        return targetShippingWon;
    }
    public void setTargetShippingWon(BigDecimal targetShippingWon) {
        this.targetShippingWon = targetShippingWon;
    }
    @Override
    public String toString() {
        return "PriceCheckRequest [name=" + name + ", title=" + title + ", price=" + price + ", discountPrice=" + discountPrice + "]";
    }
}
