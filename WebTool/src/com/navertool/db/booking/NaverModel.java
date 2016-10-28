package com.navertool.db.booking;
import javax.persistence.MappedSuperclass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MappedSuperclass
public class NaverModel {

    private static Logger m_logger = LoggerFactory.getLogger(NaverModel.class);

    protected String titleType;
    protected String titleDate;
    protected long   timeCreated;
    protected long   timeUpdated;
    protected int    dataVersion = 0;
    
    //Default Constructor
    public NaverModel() {
        timeCreated = System.currentTimeMillis();
        timeUpdated = System.currentTimeMillis();
    }

    public void incrementVersion() {
        dataVersion++;
    }

    public String getTitleType() {
        return titleType;
    }

    public void setTitleType(String titleType) {
        this.titleType = titleType;
    }

    public String getTitleDate() {
        return titleDate;
    }

    public void setTitleDate(String titleDate) {
        this.titleDate = titleDate;
    }

    public long getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(long timeCreated) {
        this.timeCreated = timeCreated;
    }

    public long getTimeUpdated() {
        return timeUpdated;
    }

    public void setTimeUpdated(long timeUpdated) {
        this.timeUpdated = timeUpdated;
    }

    public int getDataVersion() {
        return dataVersion;
    }

    public void setDataVersion(int dataVersion) {
        this.dataVersion = dataVersion;
    }

    
}
