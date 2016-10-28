package com.webtool.model;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PriceCheckResult {

    private static Logger m_logger = LoggerFactory.getLogger(PriceCheckResult.class);

    private BigDecimal regularPrice = new BigDecimal(-1); // %
    private BigDecimal specialDisountRate = new BigDecimal(-1); // %
    private BigDecimal purchasePrice = new BigDecimal(-1);
    private BigDecimal shippingInCharge = new BigDecimal(0); //$
    private BigDecimal shippingOutCharge = new BigDecimal(10); //$

    private BigDecimal misc = new BigDecimal(0); //$

    private BigDecimal priceBeforeTax = new BigDecimal(0);  // $
    private BigDecimal tax = new BigDecimal(0); // % tax to pay
    private BigDecimal finalPrice = new BigDecimal(0);  // Final purchase price in $
    
    private BigDecimal totalExpense = new BigDecimal(0); // finalPrice + shippingInCharge + shippingOutCharge + misc
    private BigDecimal fx = new BigDecimal(1200); // per dollar  like 1450.23
    private BigDecimal totalExpenseWon = new BigDecimal(0); // totalExpense * fx
    private BigDecimal chargePriceWon = new BigDecimal(-1); // charge price to customer in WON
    private BigDecimal chargeShiipingWon = new BigDecimal(-1);
    private BigDecimal chargeCustomerWon = new BigDecimal(-1); // chargePriceWon + chargeShiipingWon;
    
    private BigDecimal fee = new BigDecimal(5.8); // chargePriceWon + chargeShiipingWon;
    private BigDecimal feeAmountWon = new BigDecimal(0); // chargePriceWon + chargeShiipingWon;
    private BigDecimal totalWon = new BigDecimal(-1); // chargeCustomerWon - feeAmountWon
    private BigDecimal profitInWon = new BigDecimal(-1); // totalWon - totalExpenseWon
    private BigDecimal profits = new BigDecimal(-1); // converted to Dollar
    private BigDecimal rebatePercent = new BigDecimal(-1); // 
    private BigDecimal rebateAmount = new BigDecimal(-1); // 
    private BigDecimal assumedProfits = new BigDecimal(-1); // 
    
    public PriceCheckResult() {
    }






    public BigDecimal getRegularPrice() {
        return regularPrice;
    }






    public void setRegularPrice(BigDecimal regularPrice) {
        this.regularPrice = regularPrice;
    }






    public BigDecimal getSpecialDisountRate() {
        return specialDisountRate;
    }






    public void setSpecialDisountRate(BigDecimal specialDisountRate) {
        this.specialDisountRate = specialDisountRate;
    }






    public BigDecimal getShippingInCharge() {
        return shippingInCharge;
    }

    public void setShippingInCharge(BigDecimal shippingInCharge) {
        this.shippingInCharge = shippingInCharge;
    }

    public BigDecimal getShippingOutCharge() {
        return shippingOutCharge;
    }

    public void setShippingOutCharge(BigDecimal shippingOutCharge) {
        this.shippingOutCharge = shippingOutCharge;
    }

    public BigDecimal getMisc() {
        return misc;
    }

    public void setMisc(BigDecimal misc) {
        this.misc = misc;
    }

    public BigDecimal getPriceBeforeTax() {
        return priceBeforeTax;
    }

    public void setPriceBeforeTax(BigDecimal priceBeforeTax) {
        this.priceBeforeTax = priceBeforeTax;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public BigDecimal getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(BigDecimal totalExpense) {
        this.totalExpense = totalExpense;
    }

    public BigDecimal getFx() {
        return fx;
    }

    public void setFx(BigDecimal fx) {
        this.fx = fx;
    }

    public BigDecimal getTotalExpenseWon() {
        return totalExpenseWon;
    }

    public void setTotalExpenseWon(BigDecimal totalExpenseWon) {
        this.totalExpenseWon = totalExpenseWon;
    }

    public BigDecimal getChargePriceWon() {
        return chargePriceWon;
    }

    public void setChargePriceWon(BigDecimal chargePriceWon) {
        this.chargePriceWon = chargePriceWon;
    }

    public BigDecimal getChargeShiipingWon() {
        return chargeShiipingWon;
    }

    public void setChargeShiipingWon(BigDecimal chargeShiipingWon) {
        this.chargeShiipingWon = chargeShiipingWon;
    }

    public BigDecimal getChargeCustomerWon() {
        return chargeCustomerWon;
    }

    public void setChargeCustomerWon(BigDecimal chargeCustomerWon) {
        this.chargeCustomerWon = chargeCustomerWon;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getFeeAmountWon() {
        return feeAmountWon;
    }

    public void setFeeAmountWon(BigDecimal feeAmountWon) {
        this.feeAmountWon = feeAmountWon;
    }

    public BigDecimal getTotalWon() {
        return totalWon;
    }

    public void setTotalWon(BigDecimal totalWon) {
        this.totalWon = totalWon;
    }

    public BigDecimal getProfitInWon() {
        return profitInWon;
    }

    public void setProfitInWon(BigDecimal profitInWon) {
        this.profitInWon = profitInWon;
    }

    public BigDecimal getProfits() {
        return profits;
    }

    public void setProfits(BigDecimal profits) {
        this.profits = profits;
    }

    public BigDecimal getRebatePercent() {
        return rebatePercent;
    }

    public void setRebatePercent(BigDecimal rebatePercent) {
        this.rebatePercent = rebatePercent;
    }

    public BigDecimal getRebateAmount() {
        return rebateAmount;
    }

    public void setRebateAmount(BigDecimal rebateAmount) {
        this.rebateAmount = rebateAmount;
    }

    public BigDecimal getAssumedProfits() {
        return assumedProfits;
    }

    public void setAssumedProfits(BigDecimal assumedProfits) {
        this.assumedProfits = assumedProfits;
    }






    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }






    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
    
    
    
    

}
