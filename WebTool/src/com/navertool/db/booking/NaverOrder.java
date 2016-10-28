package com.navertool.db.booking;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//스토어팜_전체주문조회

@Entity
@Table(name = "naver_order")
public class NaverOrder extends NaverModel{

    private Integer id;
    private String orderProductNum;
    private String orderNum;
    private String dateSent;
    private String customerName;
    private String customerId;
    private String recipientName;
    private String orderStatus;
    private String orderDetailStatus;
    private String paySource;
    private String datePaid;
    private String productNum;
    private String productName;
    private String optionInfo;
    private String optionCode;
    private Integer orderCount;
    
    private Integer priceOption; 
    private Integer priceOriginal;
    private Integer priceDiscount;
    private Integer priceProduct;
    private String dateStoreOrder;
    private String dateSendBy;          //발송기한
    private String dateSent2;           //발송처리일
    private Integer chargeShipping;
    private Integer chargeExtraShipping;
    private Integer shippingDiscount;
    private String phoneRecipient;
    private String phoneRecipient2;
    private String shippingAddress;
    private String phoneCustomer;
    private String zip;
    private String deliveryText;
    private String payMethod;
    private Integer fee;
    private Integer feeChannel;
    private Integer feeAffiliates;
    private Integer settleAmountExpected;
    private String dateOrder;

    private String datePurchaseFinal;
    private String inpath;
    private String dateDelivered;

    /*
     * Product Extra
     */
    
    private String productInfo1; //size
    private String productInfo2; //size

    /*
     * Forced status
     */
    
    private String forcedStatus;
    private String forcedDetailStatus;
    private String dateForcedStatus; 
    
    /*
     * 
     */
    
    private String flagged; // "1" if flagged
    private String dateFlagged; 
    
    /*
     * Shipping Process
     * 
     */
    private String trackNum;         //송장번호 read from Naver records. Eventually, both wll be the same
    private String trackNumEntered;  //임시송장번호entered by shipper
    private String dateTrackNumEntered;
    private String enteredWithoutSending; // In case, when track num netered "1" if entered without sending
    
    /*
     * processing 
     */
    
    private String sourceFileCreatedBy;
    private String dateEntered;
    
    /*
     * Order Placement & Delivery
     * 
     */
    
    private String  orderPlacementNumber; // 주문번호 if one or more orders were combined or different order code was used other than orderNum; 
    private Boolean isInventoryFilled; // true if should be filled by inventory 
    private String  storeName; //Nike or something
    private String  storeOrderNumber;  // 주문번호
    private String  orderAccount; /// account can be different for a stroe
    private String  datePlaced;     //주문시간
    private String  dateShipment;    // 쉬핑 시간
    private String  dateExpected;  // 예상도착
    private String trackingNumber; // shipper tracking code
    private String emailContent;
    private String dateEmailReceived; 
    
    /*
     * 재고 Inventory related
     */
    
    private String inventoryAssigned; // 1 or null
    private String inventoryNum;
    private String dateInventoryAssigned;
    
    /*
     * US cost. 
     */
    private Double priceOriginalProduct;
    private Double chageUSShipping;
    
    /*
     * Settlement
     */
    
    private String dateSettled;
    private String dateSettleCompleted;
    
    private Integer fee1;           
    private String  fee1Detail; // 상품 결제
    
    private Integer fee2;
    private String  fee2Detail; // 상품 연동수수료

    private Integer fee3;
    private String  fee3Detail; // 기타비용 수수료
    
    private Integer fee4;
    private String  fee4Detail; // 구매평

    private Integer fee5;
    private String  fee5Detail; // 프리미엄 구매평

    private Integer fee6;
    private String  fee6Detail; // 스토어찜
    
    private Integer fee7;
    private String  fee7Detail; // 배송비 수수료

    private Integer fee8;
    private String  fee8Detail;
    
    
    // Default Constructor
    public NaverOrder(String titleType, String titleDate) {
        this.titleDate = titleDate;
        this.titleType = titleType;
    }

    public NaverOrder(){
        
    }
    
    @Id
    @GeneratedValue
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    

    public String getOrderProductNum() {
        return orderProductNum;
    }


    public void setOrderProductNum(String orderProductNum) {
        this.orderProductNum = orderProductNum;
    }


    public String getOrderNum() {
        return orderNum;
    }


    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }


    public String getTrackNum() {
        return trackNum;
    }


    public void setTrackNum(String trackNum) {
        this.trackNum = trackNum;
    }


    public String getDateSent() {
        return dateSent;
    }


    public void setDateSent(String dateSent) {
        this.dateSent = dateSent;
    }


    public String getCustomerName() {
        return customerName;
    }


    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    public String getCustomerId() {
        return customerId;
    }


    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }


    public String getRecipientName() {
        return recipientName;
    }


    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }


    public String getOrderStatus() {
        return orderStatus;
    }


    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }


    public String getOrderDetailStatus() {
        return orderDetailStatus;
    }


    public void setOrderDetailStatus(String orderDetailStatus) {
        this.orderDetailStatus = orderDetailStatus;
    }


    public String getPaySource() {
        return paySource;
    }


    public void setPaySource(String paySource) {
        this.paySource = paySource;
    }


    public String getDatePaid() {
        return datePaid;
    }


    public void setDatePaid(String datePaid) {
        this.datePaid = datePaid;
    }


    public String getProductNum() {
        return productNum;
    }


    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }


    public String getProductName() {
        return productName;
    }


    public void setProductName(String productName) {
        this.productName = productName;
    }


    public String getOptionInfo() {
        return optionInfo;
    }


    public void setOptionInfo(String optionInfo) {
        this.optionInfo = optionInfo;
    }


    public String getOptionCode() {
        return optionCode;
    }


    public void setOptionCode(String optionCode) {
        this.optionCode = optionCode;
    }


    public Integer getOrderCount() {
        return orderCount;
    }


    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }


    public Integer getPriceOption() {
        return priceOption;
    }


    public void setPriceOption(Integer priceOption) {
        this.priceOption = priceOption;
    }


    public Integer getPriceOriginal() {
        return priceOriginal;
    }


    public void setPriceOriginal(Integer priceOriginal) {
        this.priceOriginal = priceOriginal;
    }


    public Integer getPriceDiscount() {
        return priceDiscount;
    }


    public void setPriceDiscount(Integer priceDiscount) {
        this.priceDiscount = priceDiscount;
    }


    public Integer getPriceProduct() {
        return priceProduct;
    }


    public void setPriceProduct(Integer priceProduct) {
        this.priceProduct = priceProduct;
    }


    public String getDateStoreOrder() {
        return dateStoreOrder;
    }


    public void setDateStoreOrder(String dateStoreOrder) {
        this.dateStoreOrder = dateStoreOrder;
    }


    public String getDateSendBy() {
        return dateSendBy;
    }


    public void setDateSendBy(String dateSendBy) {
        this.dateSendBy = dateSendBy;
    }


    public String getDateSent2() {
        return dateSent2;
    }


    public void setDateSent2(String dateSent2) {
        this.dateSent2 = dateSent2;
    }


    public Integer getChargeShipping() {
        return chargeShipping;
    }


    public void setChargeShipping(Integer chargeShipping) {
        this.chargeShipping = chargeShipping;
    }


    public Integer getChargeExtraShipping() {
        return chargeExtraShipping;
    }


    public void setChargeExtraShipping(Integer chargeExtraShipping) {
        this.chargeExtraShipping = chargeExtraShipping;
    }


    public Integer getShippingDiscount() {
        return shippingDiscount;
    }


    public void setShippingDiscount(Integer shippingDiscount) {
        this.shippingDiscount = shippingDiscount;
    }


    public String getPhoneRecipient() {
        return phoneRecipient;
    }


    public void setPhoneRecipient(String phoneRecipient) {
        this.phoneRecipient = phoneRecipient;
    }


    public String getPhoneRecipient2() {
        return phoneRecipient2;
    }


    public void setPhoneRecipient2(String phoneRecipient2) {
        this.phoneRecipient2 = phoneRecipient2;
    }


    public String getShippingAddress() {
        return shippingAddress;
    }


    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }


    public String getPhoneCustomer() {
        return phoneCustomer;
    }


    public void setPhoneCustomer(String phoneCustomer) {
        this.phoneCustomer = phoneCustomer;
    }


    public String getZip() {
        return zip;
    }


    public void setZip(String zip) {
        this.zip = zip;
    }


    public String getDeliveryText() {
        return deliveryText;
    }


    public void setDeliveryText(String deliveryText) {
        this.deliveryText = deliveryText;
    }


    public String getPayMethod() {
        return payMethod;
    }


    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }


    public Integer getFee() {
        return fee;
    }


    public void setFee(Integer fee) {
        this.fee = fee;
    }


    public Integer getFeeChannel() {
        return feeChannel;
    }


    public void setFeeChannel(Integer feeChannel) {
        this.feeChannel = feeChannel;
    }


    public Integer getFeeAffiliates() {
        return feeAffiliates;
    }


    public void setFeeAffiliates(Integer feeAffiliates) {
        this.feeAffiliates = feeAffiliates;
    }


    public Integer getSettleAmountExpected() {
        return settleAmountExpected;
    }


    public void setSettleAmountExpected(Integer settleAmountExpected) {
        this.settleAmountExpected = settleAmountExpected;
    }


    public String getDateOrder() {
        return dateOrder;
    }


    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public String getDatePurchaseFinal() {
        return datePurchaseFinal;
    }


    public void setDatePurchaseFinal(String datePurchaseFinal) {
        this.datePurchaseFinal = datePurchaseFinal;
    }


    public String getInpath() {
        return inpath;
    }


    public void setInpath(String inpath) {
        this.inpath = inpath;
    }


    public String getDateDelivered() {
        return dateDelivered;
    }


    public void setDateDelivered(String dateDelivered) {
        this.dateDelivered = dateDelivered;
    }

    
    

    public Double getPriceOriginalProduct() {
        return priceOriginalProduct;
    }

    public void setPriceOriginalProduct(Double priceOriginalProduct) {
        this.priceOriginalProduct = priceOriginalProduct;
    }

    public Double getChageUSShipping() {
        return chageUSShipping;
    }

    public void setChageUSShipping(Double chageUSShipping) {
        this.chageUSShipping = chageUSShipping;
    }

    public String getDateSettled() {
        return dateSettled;
    }

    public void setDateSettled(String dateSettled) {
        this.dateSettled = dateSettled;
    }

    public String getDateSettleCompleted() {
        return dateSettleCompleted;
    }

    public void setDateSettleCompleted(String dateSettleCompleted) {
        this.dateSettleCompleted = dateSettleCompleted;
    }

    public Integer getFee1() {
        return fee1;
    }

    public void setFee1(Integer fee1) {
        this.fee1 = fee1;
    }

    public String getFee1Detail() {
        return fee1Detail;
    }

    public void setFee1Detail(String fee1Detail) {
        this.fee1Detail = fee1Detail;
    }

    public Integer getFee2() {
        return fee2;
    }

    public void setFee2(Integer fee2) {
        this.fee2 = fee2;
    }

    public String getFee2Detail() {
        return fee2Detail;
    }

    public void setFee2Detail(String fee2Detail) {
        this.fee2Detail = fee2Detail;
    }

    public Integer getFee3() {
        return fee3;
    }

    public void setFee3(Integer fee3) {
        this.fee3 = fee3;
    }

    public String getFee3Detail() {
        return fee3Detail;
    }

    public void setFee3Detail(String fee3Detail) {
        this.fee3Detail = fee3Detail;
    }

    public Integer getFee4() {
        return fee4;
    }

    public void setFee4(Integer fee4) {
        this.fee4 = fee4;
    }

    public String getFee4Detail() {
        return fee4Detail;
    }

    public void setFee4Detail(String fee4Detail) {
        this.fee4Detail = fee4Detail;
    }

    public Integer getFee5() {
        return fee5;
    }

    public void setFee5(Integer fee5) {
        this.fee5 = fee5;
    }

    public String getFee5Detail() {
        return fee5Detail;
    }

    public void setFee5Detail(String fee5Detail) {
        this.fee5Detail = fee5Detail;
    }

    public Integer getFee6() {
        return fee6;
    }

    public void setFee6(Integer fee6) {
        this.fee6 = fee6;
    }

    public String getFee6Detail() {
        return fee6Detail;
    }

    public void setFee6Detail(String fee6Detail) {
        this.fee6Detail = fee6Detail;
    }

    public Integer getFee7() {
        return fee7;
    }

    public void setFee7(Integer fee7) {
        this.fee7 = fee7;
    }

    public String getFee7Detail() {
        return fee7Detail;
    }

    public void setFee7Detail(String fee7Detail) {
        this.fee7Detail = fee7Detail;
    }

    public Integer getFee8() {
        return fee8;
    }

    public void setFee8(Integer fee8) {
        this.fee8 = fee8;
    }

    public String getFee8Detail() {
        return fee8Detail;
    }

    public void setFee8Detail(String fee8Detail) {
        this.fee8Detail = fee8Detail;
    }
    
    
    public String getSourceFileCreatedBy() {
        return sourceFileCreatedBy;
    }

    public void setSourceFileCreatedBy(String sourceFileCreatedBy) {
        this.sourceFileCreatedBy = sourceFileCreatedBy;
    }

    public String getDateEntered() {
        return dateEntered;
    }

    public void setDateEntered(String dateEntered) {
        this.dateEntered = dateEntered;
    }



    public String getTrackNumEntered() {
        return trackNumEntered;
    }

    public void setTrackNumEntered(String trackNumEntered) {
        this.trackNumEntered = trackNumEntered;
    }

    public String getDateTrackNumEntered() {
        return dateTrackNumEntered;
    }

    public void setDateTrackNumEntered(String dateTrackNumEntered) {
        this.dateTrackNumEntered = dateTrackNumEntered;
    }


    
    public String getEnteredWithoutSending() {
        return enteredWithoutSending;
    }

    public void setEnteredWithoutSending(String enteredWithoutSending) {
        this.enteredWithoutSending = enteredWithoutSending;
    }

    public String getOrderPlacementNumber() {
        return orderPlacementNumber;
    }

    public void setOrderPlacementNumber(String orderPlacementNumber) {
        this.orderPlacementNumber = orderPlacementNumber;
    }

    public Boolean getIsInventoryFilled() {
        return isInventoryFilled;
    }

    public void setIsInventoryFilled(Boolean isInventoryFilled) {
        this.isInventoryFilled = isInventoryFilled;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getEmailContent() {
        return emailContent;
    }

    public void setEmailContent(String emailContent) {
        this.emailContent = emailContent;
    }

    public String getDateEmailReceived() {
        return dateEmailReceived;
    }

    public void setDateEmailReceived(String dateEmailReceived) {
        this.dateEmailReceived = dateEmailReceived;
    }
    
    public String getFlagged() {
        return flagged;
    }

    public void setFlagged(String flagged) {
        this.flagged = flagged;
    }

    public String getInventoryAssigned() {
        return inventoryAssigned;
    }

    public void setInventoryAssigned(String inventoryAssigned) {
        this.inventoryAssigned = inventoryAssigned;
    }

    public String getInventoryNum() {
        return inventoryNum;
    }

    public void setInventoryNum(String inventoryNum) {
        this.inventoryNum = inventoryNum;
    }

    public String getDateInventoryAssigned() {
        return dateInventoryAssigned;
    }

    public void setDateInventoryAssigned(String dateInventoryAssigned) {
        this.dateInventoryAssigned = dateInventoryAssigned;
    }

    @Override
    public String toString() {
        return "NaverOrder [id=" + id + ", orderProductNum=" + orderProductNum + ", orderNum=" + orderNum + ", trackNum=" + trackNum + ", dateSent=" + dateSent + ", customerName=" + customerName
                + ", customerId=" + customerId + ", recipientName=" + recipientName + ", orderStatus=" + orderStatus + ", orderDetailStatus=" + orderDetailStatus + ", paySource=" + paySource
                + ", datePaid=" + datePaid + ", productNum=" + productNum + ", productName=" + productName + ", optionInfo=" + optionInfo + ", optionCode=" + optionCode + ", orderCount=" + orderCount
                + ", priceOption=" + priceOption + ", priceOriginal=" + priceOriginal + ", priceDiscount=" + priceDiscount + ", priceProduct=" + priceProduct + ", dateStoreOrder=" + dateStoreOrder
                + ", dateSendBy=" + dateSendBy + ", dateSent2=" + dateSent2 + ", chargeShipping=" + chargeShipping + ", chargeExtraShipping=" + chargeExtraShipping + ", shippingDiscount="
                + shippingDiscount + ", phoneRecipient=" + phoneRecipient + ", phoneRecipient2=" + phoneRecipient2 + ", shippingAddress=" + shippingAddress + ", phoneCustomer=" + phoneCustomer
                + ", zip=" + zip + ", deliveryText=" + deliveryText + ", payMethod=" + payMethod + ", fee=" + fee + ", feeChannel=" + feeChannel + ", feeAffiliates=" + feeAffiliates
                + ", settleAmountExpected=" + settleAmountExpected + ", dateOrder=" + dateOrder + ", datePurchaseFinal=" + datePurchaseFinal + ", inpath=" + inpath + ", dateDelivered=" + dateDelivered
                + ", priceOriginalProduct=" + priceOriginalProduct + ", chageUSShipping=" + chageUSShipping + ", dateSettled=" + dateSettled + ", dateSettleCompleted=" + dateSettleCompleted
                + ", fee1=" + fee1 + ", fee1Detail=" + fee1Detail + ", fee2=" + fee2 + ", fee2Detail=" + fee2Detail + ", fee3=" + fee3 + ", fee3Detail=" + fee3Detail + ", fee4=" + fee4
                + ", fee4Detail=" + fee4Detail + ", fee5=" + fee5 + ", fee5Detail=" + fee5Detail + ", fee6=" + fee6 + ", fee6Detail=" + fee6Detail + ", fee7=" + fee7 + ", fee7Detail=" + fee7Detail
                + ", fee8=" + fee8 + ", fee8Detail=" + fee8Detail + ", titleType=" + titleType + ", titleDate=" + titleDate + ", timeCreated=" + timeCreated + ", timeUpdated=" + timeUpdated
                + ", dataVersion=" + dataVersion + "]";
    }

    public String getDateFlagged() {
        return dateFlagged;
    }

    public void setDateFlagged(String dateFlagged) {
        this.dateFlagged = dateFlagged;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreOrderNumber() {
        return storeOrderNumber;
    }

    public void setStoreOrderNumber(String storeOrderNumber) {
        this.storeOrderNumber = storeOrderNumber;
    }

    public String getDatePlaced() {
        return datePlaced;
    }

    public void setDatePlaced(String datePlaced) {
        this.datePlaced = datePlaced;
    }

    public String getDateShipment() {
        return dateShipment;
    }

    public void setDateShipment(String dateShipment) {
        this.dateShipment = dateShipment;
    }

    public String getDateExpected() {
        return dateExpected;
    }

    public void setDateExpected(String dateExpected) {
        this.dateExpected = dateExpected;
    }

    public String getOrderAccount() {
        return orderAccount;
    }

    public void setOrderAccount(String orderAccount) {
        this.orderAccount = orderAccount;
    }

    public String getProductInfo1() {
        return productInfo1;
    }

    public void setProductInfo1(String productInfo1) {
        this.productInfo1 = productInfo1;
    }

    public String getProductInfo2() {
        return productInfo2;
    }

    public void setProductInfo2(String productInfo2) {
        this.productInfo2 = productInfo2;
    }

    public String getForcedStatus() {
        return forcedStatus;
    }

    public void setForcedStatus(String forcedStatus) {
        this.forcedStatus = forcedStatus;
    }

    public String getForcedDetailStatus() {
        return forcedDetailStatus;
    }

    public void setForcedDetailStatus(String forcedDetailStatus) {
        this.forcedDetailStatus = forcedDetailStatus;
    }

    public String getDateForcedStatus() {
        return dateForcedStatus;
    }

    public void setDateForcedStatus(String dateForcedStatus) {
        this.dateForcedStatus = dateForcedStatus;
    }

    
}
