package com.navertool.web;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.navertool.search.SearchContext;
import com.navertool.search.SearchRequest;
import com.navertool.search.SortByRequest;

public class WebUtil {

    private static Logger m_logger = LoggerFactory.getLogger(WebUtil.class);

    // Default Constructor
    public WebUtil() {
    }

    public static SearchContext getSearchContext(HttpSession session) {

        SearchContext ctx = (SearchContext) session.getAttribute("SearchContext");

        if (ctx == null) {
            ctx = new SearchContext();
            ctx.setSearchRequest(new SearchRequest());
            ctx.setSortRequest(new SortByRequest());
            session.setAttribute("SearchContext", ctx);
        }
        return ctx;
    }

    public static String isSelectedOption(String option, String value) {

        if (option.equalsIgnoreCase(value))
            return "selected='selected'";

        return "";

    }

    public static void trimAllStringFields(Object bean) {

        for (Field field : bean.getClass().getDeclaredFields()) {
            
            try {
                String fieldName = field.getName();
                
                Object val = BeanUtils.getProperty(bean, fieldName);
                
                if ( val instanceof String ) {
                    BeanUtils.setProperty(bean, fieldName, val.toString().trim());
                }
            }
            catch (Exception  e) {
                m_logger.error(e.getMessage() ,e);
            }
        }
    }

}
