package com.navertool.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.navertool.search.NewInventory;


@Controller
public class BaseController {

    private static Logger m_logger = LoggerFactory.getLogger(BaseController.class);

    // Default Constructor
    public BaseController() {
    }

    
    @ModelAttribute("qtyOptions")
    public List getQtyOptions()
    {

        List<String> status = new ArrayList();
        
        status.add("0");
        status.add("1");
        status.add("2");
        status.add("3");
        status.add("4");
        status.add("5");
        status.add("6");
        status.add("7");
        status.add("8");
        status.add("9");
        status.add("10");

        return status;
    } 
    
    
    @ModelAttribute("searchFields3")
    public Map getSearchFields()
    {
        ModelAndView mav = new ModelAndView("phone-form");  
        
        Map< String, String > phones = new HashMap<>();  
        phones.put("all", "전부다");  
        phones.put("orderProductNum", "네이버주문번호");  
        phones.put("name", "이름");  
        phones.put("customerId", "고객아이디");  
        phones.put("orderPlacementNumber", "현지배달번호");  
        phones.put("trackingNum", "송장번호");  
        phones.put("productNum", "상품번호");  
        phones.put("productName", "상품이름");  
          
        mav.addObject("phonesMap", phones);  
          
        return phones;
    }
    
    @ModelAttribute("noteTypes3")
    public Map getNoteTypes()
    {
        ModelAndView mav = new ModelAndView("phone-form");  
        
        Map< String, String > phones = new HashMap<>();  
        phones.put("NOTE", "NOTE");  
        phones.put("ALERT", "ALERT");  
        phones.put("REMINDER", "REMINDER");  
          
        mav.addObject("phonesMap", phones);  
          
        return phones;
    }

    @ModelAttribute("searchDates3")
    public List getSearchDates()
    {
        List<String> dates = new ArrayList();
        
        DateTime dt = new DateTime(new Date() );

        dt = dt.minusDays(15);
        dates.add("");
        for (int i = 0; i < 30; i++) {
            dates.add(dt.toString("MM/dd"));
            dt = dt.plusDays(1);
        }
        
        return dates;
    }
    
    @ModelAttribute("searchStatus3")
    public List getSearchStatus()
    {

        List<String> status = new ArrayList();
        
        status.add("모든상태");
        status.add("발송대기");
        status.add("취소완료");
        status.add("배송중");
        status.add("배송완료");
        status.add("반품완료");
        status.add("취소반품제외");
          
        return status;
    }
    
    @ModelAttribute("sizeOptions3")
    public List getSizeOptions()
    {

        List<String> status = new ArrayList();
        
        status.add("5");
        status.add("5.5");
        status.add("6");
        status.add("6.5");
        status.add("7");
        status.add("7.5");
        status.add("8");
        status.add("8.5");
        status.add("9");
        status.add("9.5");
        status.add("10");
        status.add("10.5");
        status.add("11");
        status.add("11.5");
          
        return status;
    }    
    
    @ModelAttribute("searchByDateFields3")
    public Map getSearchDateFeilds()
    {

        Map< String, String > phones = new HashMap<>();  
        
        phones.put("",  "");  
        phones.put("dateTrackNumEntered", "내부송장처리일");  
        phones.put("datePaid",  "결제일");  
        phones.put("dateSendBy", "발송기한일");  
        phones.put("dateSent2",  "발송처리일");  
        return phones;
    }
    
    @ModelAttribute("sortByDateFields3")
    public Map getSortByDateFields()
    {

        Map< String, String > phones = new HashMap<>();  
        
        phones.put("datePaid",  "결제일");  
        phones.put("dateSendBy", "발송기한일");  
        phones.put("sizeAndDatePaid",  "사이즈/결제일");  
        phones.put("sizeAndDateSendBy",  "사이즈/발송기한일");  
        return phones;
    }    
    
    @ModelAttribute("sortTypes3")
    public Map getSortTypes()
    {

        Map< String, String > phones = new HashMap<>();  
        
        phones.put("ASC",  "오래된거먼저/작은거먼저");  
        phones.put("DESC", "최근거먼저/큰거먼저");  
        return phones;
    } 
    
    
    @ModelAttribute("storeNames3")
    public Map getStoreNames()
    {
        ModelAndView mav = new ModelAndView("phone-form");  
        
        Map< String, String > phones = new HashMap<>();  
        phones.put("", "스토어선택");  
        phones.put("개풋락커", "개풋락커");  
        phones.put("챔스", "챔스");  
        phones.put("풋액션", "풋액션");  
        phones.put("이스트베이", "이스트베이");  
        phones.put("노드스트롬", "노드스트롬");  
        phones.put("막장나이키", "막장나이키");  
        phones.put("피니쉬막장라인", "피니쉬막장라인");  
        phones.put("6PM", "6PM");  
        
        phones.put("다른데", "다른데");  
          
        mav.addObject("phonesMap", phones);  
          
        return phones;
    }
    
    
    
    @ModelAttribute
    public NewInventory getNaverOrderInventoryModel(Model model) {
        
        NewInventory inventory = new NewInventory();
        model.addAttribute("NewInventory", inventory);
        return inventory;
    }     
    
    
}
