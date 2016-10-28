package com.navertool.search;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SearchRequest {

    private static Logger m_logger = LoggerFactory.getLogger(SearchRequest.class);
    
    private String keyword;
    private String searchField;

    private String option1;
    private String option2;
    
    
    private String searchStatus    = "발송대기";
    private String searchSubStatus;  // Not being used. 
    
    private String searchDateField;
    private String searchDateKey;
        
    
    //Default Constructor
    public SearchRequest() {
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public String getSearchStatus() {
        return searchStatus;
    }

    public void setSearchStatus(String searchStatus) {
        this.searchStatus = searchStatus;
    }

    public String getSearchDateField() {
        return searchDateField;
    }

    public void setSearchDateField(String searchDateField) {
        this.searchDateField = searchDateField;
    }

    public String getSearchDateKey() {
        return searchDateKey;
    }

    public void setSearchDateKey(String searchDateKey) {
        this.searchDateKey = searchDateKey;
    }

    public String getSearchSubStatus() {
        return searchSubStatus;
    }

    public void setSearchSubStatus(String searchSubStatus) {
        this.searchSubStatus = searchSubStatus;
    }

    @Override
    public String toString() {
        return "SearchRequest [keyword=" + keyword + ", searchField=" + searchField + ", searchStatus=" + searchStatus + ", searchSubStatus=" + searchSubStatus + ", searchDateField=" + searchDateField
                + ", searchDateKey=" + searchDateKey + "]";
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

    
    
}
