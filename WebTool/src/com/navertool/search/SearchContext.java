package com.navertool.search;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.navertool.db.booking.NaverOrder;
import com.navertool.web.OrderSortUtil;

public class SearchContext {

    private static Logger m_logger = LoggerFactory.getLogger(SearchContext.class);

    //Default Constructor

    private long   timestamp; //last searched
    private String searchKey;
    private String searchField; // orderProductNum,
    private String sortByField;
    private String status;
    
    private SearchRequest searchRequest;
    private SortByRequest sortRequest;
    private String        resultPage = "/naver/search";
    
    private List<NaverOrder> searchResults;
    
    public SearchContext() {
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public List<NaverOrder> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<NaverOrder> searchResults) {
        this.searchResults = searchResults;
    }

    public String getSortByField() {
        return sortByField;
    }

    public void setSortByField(String sortByField) {
        this.sortByField = sortByField;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SearchRequest getSearchRequest() {
        return searchRequest;
    }

    public void setSearchRequest(SearchRequest searchRequest) {
        this.searchRequest = searchRequest;
    }

    public SortByRequest getSortRequest() {
        return sortRequest;
    }

    public void setSortRequest(SortByRequest sortRequest) {
        this.sortRequest = sortRequest;
    }
    

    
    public String getResultPage() {
        return resultPage;
    }

    public void setResultPage(String resultPage) {
        this.resultPage = resultPage;
    }

    public void refreshSorting() {

        if ( sortRequest == null ) {
            sortRequest = new SortByRequest();
        }
        
        if ( "dateOrder".equalsIgnoreCase(sortRequest.getSortByField()) || "datePaid".equalsIgnoreCase(sortRequest.getSortByField()) ){
            OrderSortUtil.sortByOrderTime(searchResults, "DESC".equalsIgnoreCase(sortRequest.getSortType()));
        } else if ( "sizeAndDatePaid".equalsIgnoreCase(sortRequest.getSortByField()) ) {
            OrderSortUtil.sortBySizeAndOrderTime(searchResults, "DESC".equalsIgnoreCase(sortRequest.getSortType()));
        } else if ( "sizeAndDateSendBy".equalsIgnoreCase(sortRequest.getSortByField()) ) {
            OrderSortUtil.sortBySizeAndSendByTime(searchResults, "DESC".equalsIgnoreCase(sortRequest.getSortType()));
        } else {
            OrderSortUtil.sortBySendByTime(searchResults, "DESC".equalsIgnoreCase(sortRequest.getSortType()));
        }
        
    }
}
