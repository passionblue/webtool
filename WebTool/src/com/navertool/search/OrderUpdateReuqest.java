package com.navertool.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderUpdateReuqest {

    private static Logger m_logger = LoggerFactory.getLogger(OrderUpdateReuqest.class);

    private String orderProductNum;
    private String fieldName;
    private String value1;
    private String value2;
    private String value3;
    private String value4;
    private String value5;
    private String datetime; // regular format
    private String type;
    
    // Default Constructor
    public OrderUpdateReuqest() {
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getValue3() {
        return value3;
    }

    public void setValue3(String value3) {
        this.value3 = value3;
    }

    public String getValue4() {
        return value4;
    }

    public void setValue4(String value4) {
        this.value4 = value4;
    }

    public String getValue5() {
        return value5;
    }

    public void setValue5(String value5) {
        this.value5 = value5;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrderProductNum() {
        return orderProductNum;
    }

    public void setOrderProductNum(String orderProductNum) {
        this.orderProductNum = orderProductNum;
    }

    
    
    
}