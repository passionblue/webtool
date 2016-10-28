package com.navertool.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthRequest {

    private static Logger m_logger = LoggerFactory.getLogger(AuthRequest.class);

    private String phrase;
    private long   timestamp;
    private long   expireTimestamp;
    
    // Default Constructor
    public AuthRequest() {
        timestamp = System.currentTimeMillis();
        expireTimestamp = System.currentTimeMillis() + 3600*1000;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void extend(){
        expireTimestamp = System.currentTimeMillis() + 3600*1000;
    }
    
    public boolean expired() {
        if ( System.currentTimeMillis() > expireTimestamp) 
            return true;
        return false;
    }
    
}
