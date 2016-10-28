package com.navertool.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderMap<V, T, K> {

    private static Logger m_logger = LoggerFactory.getLogger(OrderMap.class);

    protected Map<T, Map<K, List<V>>> mapTop;
    
    // Default Constructor
    public OrderMap() {

        mapTop = new ConcurrentSkipListMap<>();
    }

    /*
     * Create layer map structure 
     */
    
    
    public void processOrder(V naverOrder , Class<T> clazzTop, Class<K> classBottom, String fieldTop, String fieldBottom ) {

        K noneKeyStr = (K) getInvalidKeyValueForType(classBottom);

        T valTop = (T) getBeanValueByFieldName(naverOrder, fieldTop);
        
        if (!mapTop.containsKey(valTop)) {

            Map<K, List<V>> m = new ConcurrentSkipListMap<K, List<V>>();
            mapTop.put(valTop, m);
        }

        Map<K, List<V>> mapBottom = mapTop.get(valTop);

        List<V> listOrder = null;
        K valBottom = (K) getBeanValueByFieldName(naverOrder, fieldBottom);
        
        if (valBottom == null) {
            if (!mapBottom.containsKey(noneKeyStr)) {
                listOrder = new CopyOnWriteArrayList<V>();
                mapBottom.put(noneKeyStr, listOrder);
            }
            else {
                listOrder = mapBottom.get(noneKeyStr);
            }
        }
        else {
            if (!mapBottom.containsKey(valBottom)) {
                listOrder = new CopyOnWriteArrayList<V>();
                mapBottom.put(valBottom, listOrder);
            }
            else {
                listOrder = mapBottom.get(valBottom);
            }
        }
        listOrder.add(naverOrder);
        
        
        
    }
    
    public void processOrderList(List<V> list , Class<T> clazzTop, Class<K> classBottom, String fieldTop, String fieldBottom ) {

        K noneKeyStr = (K) getInvalidKeyValueForType(classBottom);

        for (V naverOrder : list) {

            processOrder(naverOrder, clazzTop, classBottom, fieldTop, fieldBottom);
            
/*            
            T valTop = (T) getBeanValueByFieldName(naverOrder, fieldTop);
            
            if (!mapTop.containsKey(valTop)) {

                Map<K, List<NaverOrder>> m = new TreeMap<K, List<NaverOrder>>();
                mapTop.put(valTop, m);
            }

            Map<K, List<NaverOrder>> mapBottom = mapTop.get(valTop);

            List<NaverOrder> listOrder = null;
            K valBottom = (K) getBeanValueByFieldName(naverOrder, fieldBottom);
            
            if (valBottom == null) {
                if (!mapBottom.containsKey(noneKeyStr)) {
                    listOrder = new ArrayList<NaverOrder>();
                    mapBottom.put(noneKeyStr, listOrder);
                }
                else {
                    listOrder = mapBottom.get(noneKeyStr);
                }
            }
            else {
                if (!mapBottom.containsKey(valBottom)) {
                    listOrder = new ArrayList<NaverOrder>();
                    mapBottom.put(valBottom, listOrder);
                }
                else {
                    listOrder = mapBottom.get(valBottom);
                }
            }
            listOrder.add(naverOrder);
            */
        }

    }
    
    private Object getInvalidKeyValueForType( Class clazz){
        
        if ( clazz == String.class ) {
            return "NA";
        } else if ( clazz == Double.class ) {
            return Double.MIN_VALUE;
        }
        
        return "NA";
        
    }
    private Object getBeanValueByFieldName(Object bean, String field) {
        
        if ( bean == null || field == null ) 
            return null;
        try {
            return BeanUtils.getProperty(bean, field);
        }
        catch (Exception  e) {
//            m_logger.error(e.getMessage() ,e);
            return null;
        }
    }

    public Map<T, Map<K, List<V>>> getMap() {
        return mapTop;
    }

}
