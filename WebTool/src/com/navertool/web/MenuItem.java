package com.navertool.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MenuItem {

    private static Logger m_logger = LoggerFactory.getLogger(MenuItem.class);

    private String value;
    private String display;
    
    

    public MenuItem(String value, String display) {
        super();
        this.value = value;
        this.display = display;
    }

    // Default Constructor
    public MenuItem() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    @Override
    public String toString() {
        return "MenuItem [value=" + value + ", display=" + display + "]";
    }

}
