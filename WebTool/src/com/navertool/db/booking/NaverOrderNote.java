package com.navertool.db.booking;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Entity
@Table(name = "naver_order_note")
public class NaverOrderNote extends NaverModel{

    private static Logger m_logger = LoggerFactory.getLogger(NaverOrderNote.class);

    private Integer id;
    private String orderProductNum;
    private String dateEntered;
    private String note;
    private String type; // info, alert, reminder
    
    private Long   timestamp;
    
    //Default Constructor
    public NaverOrderNote() {
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

    public String getDateEntered() {
        return dateEntered;
    }

    public void setDateEntered(String dateEntered) {
        this.dateEntered = dateEntered;
    }

    @Column(length=4096)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "NaverOrderNote [id=" + id + ", orderProductNum=" + orderProductNum + ", dateEntered=" + dateEntered + ", note=" + note + ", type=" + type + "]";
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }


}
