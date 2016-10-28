package com.navertool.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.navertool.db.booking.NaverOrder;

public class DataUtil {

    private static Logger m_logger = LoggerFactory.getLogger(DataUtil.class);

    // Default Constructor
    public DataUtil() {
    }
    
    public static boolean isTerminalState(NaverOrder order){
        
        if ( order == null) return false;
        
        String subStatus = order.getOrderDetailStatus();
        
        if(  subStatus != null && (
                subStatus.equals("정산완료")||
                subStatus.equals("취소완료")||
                subStatus.equals("반품완료")
                )){
            return true;
        }
        return false;
    }
    
    public static boolean isTerminalNoPurchaseState(NaverOrder order){
        
        if ( order == null) return false;
        
        String subStatus = order.getOrderDetailStatus();
        
        if(  subStatus != null && (
                subStatus.equals("취소완료")||
                subStatus.equals("반품완료")
                )){
            return true;
        }
        return false;
    }
    
    public static boolean isProcessingDone(NaverOrder order){
        
        if ( order == null) return false;

        
        String status = order.getOrderStatus();

        if(  status != null && (
                status.equals("배송완료") ||
                status.equals("배송중") ||
                status.equals("구매확정") 
                )){
            return true;
        }
        
        
        String subStatus = order.getOrderDetailStatus();
        
        if(  subStatus != null && (
                subStatus.equals("정산완료")||
                subStatus.equals("취소완료")||
                subStatus.equals("반품완료")
                )){
            return true;
        }
        return false;
    }
    
    
    public static String getShoeSizeForOrder(NaverOrder order) {

        String optionCode = order.getOptionCode();
        
        if ( StringUtils.isBlank(optionCode)) return null;

        char[] ca = new char[10];
        
        int p = 0;
        for (int i = optionCode.length()-1; i >=0; i--) {
            
            Character c = optionCode.charAt(i);
            
            if ( Character.isDigit(c) || c.equals('.') )
                ca[p++]=c;
            else
                break;
        }
        
        if (p == 0 ) return null;
        
        if ( p == 1 ) 
            return String.valueOf(ca[0]);

        
        char[] car = new char[p];
        
        for (int i = 0; i < p; i++) {
            car[i] = ca[p-1-i];
        }
        
        return String.valueOf(car);
    }
    
    
}
