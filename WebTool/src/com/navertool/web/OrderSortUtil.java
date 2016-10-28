package com.navertool.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.navertool.db.booking.NaverOrder;

public class OrderSortUtil {

    private static Logger m_logger = LoggerFactory.getLogger(OrderSortUtil.class);

    // Default Constructor
    public OrderSortUtil() {
    }

    public static void sortBySendByTime(List<NaverOrder> list, boolean descending) {

        Collections.sort(list, new Comparator<NaverOrder>() {

            @Override
            public int compare(NaverOrder o1, NaverOrder o2) {

                long time = (o1 == null ? -1 : ValueUtil.getTimestamp(o1.getDateSendBy())/60000);
                long time2 = (o2 == null ? -1 : ValueUtil.getTimestamp(o2.getDateSendBy())/60000);

                if (descending)
                    return (int) ((time2 - time) % 10512000);
                else
                    return (int) ((time - time2) % 10512000);
            }
        });
    }

    public static void sortByOrderTime(List<NaverOrder> list, boolean descending) {

        Collections.sort(list, new Comparator<NaverOrder>() {

            @Override 
            public int compare(NaverOrder o1, NaverOrder o2) {

                long time = (o1 == null ? -1 : ValueUtil.getTimestamp(o1.getDatePaid())/60000);
                long time2 = (o2 == null ? -1 : ValueUtil.getTimestamp(o2.getDatePaid())/60000);
                if (descending)
                    return (int) ((time2 - time) % 10512000);
                else
                    return (int) ((time - time2) % 10512000);
            }
        });
    }
    
    
    public static void sortBySizeAndSendByTime(List<NaverOrder> list, boolean descending) {

        Collections.sort(list, new Comparator<NaverOrder>() {

            @Override 
            public int compare(NaverOrder o1, NaverOrder o2) {

                
                String size = o1.getProductInfo1();
                String size2 = o2.getProductInfo1();
                
                if ( size == null && size2 != null ) {
                    return -1;
                } else if ( size != null && size2 == null ) {
                    return 1;
                } else if ( (size == null && size2 == null ) || ( size.equals(size2))) {
                
                    long time = (o1 == null ? -1 : ValueUtil.getTimestamp(o1.getDateSendBy())/60000);
                    long time2 = (o2 == null ? -1 : ValueUtil.getTimestamp(o2.getDateSendBy())/60000);

                    if (descending)
                        return (int) ((time2 - time) % 10512000);
                    else
                        return (int) ((time - time2) % 10512000);
                } else {
                    
                    
                    double sizeInt = ValueUtil.convertToGuaranteedDouble(size, -1.0);
                    double sizeInt2 = ValueUtil.convertToGuaranteedDouble(size2, -1.0);
                    
                    if (descending)
                        return (sizeInt2 - sizeInt) > 0.0?1:-1;
                    else
                        return (sizeInt - sizeInt2) > 0.0?1:-1;
                }
            }
        });
    }

    
    public static void sortBySizeAndOrderTime(List<NaverOrder> list, boolean descending) {

        Collections.sort(list, new Comparator<NaverOrder>() {

            @Override 
            public int compare(NaverOrder o1, NaverOrder o2) {
                
                String size = o1.getProductInfo1();
                String size2 = o2.getProductInfo1();
                
                if ( size == null && size2 != null ) {
                    return -1;
                } else if ( size != null && size2 == null ) {
                    return 1;
                } else if ( (size == null && size2 == null ) || ( size.equals(size2))) {
                
                    long time = (o1 == null ? -1 : ValueUtil.getTimestamp(o1.getDatePaid())/60000);
                    long time2 = (o2 == null ? -1 : ValueUtil.getTimestamp(o2.getDatePaid())/60000);
                    if (descending)
                        return (int) ((time2 - time) % 10512000);
                    else
                        return (int) ((time - time2) % 10512000);
                } else {
                    
                    
                    double sizeInt = ValueUtil.convertToGuaranteedDouble(size, -1.0);
                    double sizeInt2 = ValueUtil.convertToGuaranteedDouble(size2, -1.0);
                    
                    if (descending)
                        return (sizeInt2 - sizeInt) > 0.0?1:-1;
                    else
                        return (sizeInt - sizeInt2) > 0.0?1:-1;
                }
            }
        });
    }
    
    
    public static Map<String, Map<String, List<NaverOrder>>> summarizeOrderList(List<NaverOrder> list){
        
        
        Map<String, Map<String, List<NaverOrder>>> map = new TreeMap<>(); 
        
        
        for (NaverOrder naverOrder : list) {
            
            if (!map.containsKey(naverOrder.getProductName())){
                
                Map<String, List<NaverOrder>> m = new TreeMap<>(); 
                
                map.put(naverOrder.getProductName(), m );
            }
            
            Map<String, List<NaverOrder>> mapBySize = map.get(naverOrder.getProductName());
            
            List<NaverOrder> listOrder = null;
            if (StringUtils.isBlank(naverOrder.getProductInfo1())) {
                if (!mapBySize.containsKey("NoSizeSet")){
                    listOrder = new ArrayList<NaverOrder>(); 
                    mapBySize.put("NoSizeSet", listOrder );
                } else {
                    listOrder = mapBySize.get("NoSizeSet");
                }
                
            } else {
                if (!mapBySize.containsKey(naverOrder.getProductInfo1())){
                    listOrder = new ArrayList<NaverOrder>(); 
                    mapBySize.put(naverOrder.getProductInfo1(), listOrder );
                } else {
                    listOrder = mapBySize.get(naverOrder.getProductInfo1());
                }
                
            }
            
            listOrder.add(naverOrder);
            
            
        }

        
        
        return map;
        
        
        
    }
    
    /*
     * keyed by productNum and getProductInfo1, which is size
     */
    public static Map<String, Map<String, List<NaverOrder>>> summarizeOrderList2(List<NaverOrder> list){
        
        
        Map<String, Map<String, List<NaverOrder>>> map = new TreeMap<>(); 
        
        
        for (NaverOrder naverOrder : list) {
            
            if (!map.containsKey(naverOrder.getProductNum())){
                
                Map<String, List<NaverOrder>> m = new TreeMap<>(); 
                
                map.put(naverOrder.getProductNum(), m );
            }
            
            Map<String, List<NaverOrder>> mapBySize = map.get(naverOrder.getProductNum());
            
            List<NaverOrder> listOrder = null;
            if (StringUtils.isBlank(naverOrder.getProductInfo1())) {
                if (!mapBySize.containsKey("NoSizeSet")){
                    listOrder = new ArrayList<NaverOrder>(); 
                    mapBySize.put("NoSizeSet", listOrder );
                } else {
                    listOrder = mapBySize.get("NoSizeSet");
                }
                
            } else {
                if (!mapBySize.containsKey(naverOrder.getProductInfo1())){
                    listOrder = new ArrayList<NaverOrder>(); 
                    mapBySize.put(naverOrder.getProductInfo1(), listOrder );
                } else {
                    listOrder = mapBySize.get(naverOrder.getProductInfo1());
                }
                
            }
            
            listOrder.add(naverOrder);
            
            
        }

        
        
        return map;
        
        
        
    }
    
}
