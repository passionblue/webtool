package com.navertool.db.booking;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "file_process_history")
public class FileProcessHistory extends NaverModel{

    private static Logger m_logger = LoggerFactory.getLogger(FileProcessHistory.class);

    private Integer id;
    private String fileName;

    private String dateProcessed;
    private Integer rowCounts;
    private Integer failCounts;
    
    @Id
    @GeneratedValue
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

        
    
    // Default Constructor
    public FileProcessHistory() {
    }



    public String getFileName() {
        return fileName;
    }



    public void setFileName(String fileName) {
        this.fileName = fileName;
    }



    public String getDateProcessed() {
        return dateProcessed;
    }



    public void setDateProcessed(String dateProcessed) {
        this.dateProcessed = dateProcessed;
    }



    public Integer getRowCounts() {
        return rowCounts;
    }



    public void setRowCounts(Integer rowCounts) {
        this.rowCounts = rowCounts;
    }



    public void setId(Integer id) {
        this.id = id;
    }



    public Integer getFailCounts() {
        return failCounts;
    }



    public void setFailCounts(Integer failCounts) {
        this.failCounts = failCounts;
    }



    @Override
    public String toString() {
        return "FileProcessHistory [id=" + id + ", fileName=" + fileName + ", dateProcessed=" + dateProcessed + ", rowCounts=" + rowCounts + ", failCounts=" + failCounts + ", titleType=" + titleType
                + ", titleDate=" + titleDate + ", timeCreated=" + timeCreated + ", timeUpdated=" + timeUpdated + ", dataVersion=" + dataVersion + "]";
    }





}
