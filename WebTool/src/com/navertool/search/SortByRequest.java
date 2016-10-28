package com.navertool.search;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * request the change sorting on the result
 */

public class SortByRequest {

    private static Logger m_logger = LoggerFactory.getLogger(SortByRequest.class);

    
    private String sortByField = "dateOrder"; 
    private String sortType = "ASC"; //ASC or DESC;
    
    
    //Default Constructor
    public SortByRequest() {
    }


    public String getSortByField() {
        return sortByField;
    }


    public void setSortByField(String sortByField) {
        this.sortByField = sortByField;
    }


    public String getSortType() {
        return sortType;
    }


    public void setSortType(String sortType) {
        this.sortType = sortType;
    }
    
}
