package com.navertool.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.navertool.db.booking.BookingDbInstance;
import com.navertool.db.booking.NaverBookingDataPersistService;
import com.navertool.db.booking.NaverOrder;
import com.navertool.db.booking.NaverOrderNote;
import com.navertool.search.AuthRequest;
import com.navertool.search.OrderUpdateReuqest;
import com.navertool.search.SearchContext;
import com.navertool.search.SearchManager;
import com.navertool.search.SearchRequest;
import com.navertool.search.SortByRequest;

@Controller
public class OrderController  extends BaseController {

    private static Logger m_logger = LoggerFactory.getLogger(OrderController.class);

    private SearchManager searchManager = new SearchManager();
    
    @ModelAttribute
    public SortByRequest createMyTask2(Model model) {
        model.addAttribute("SortByRequest", new SortByRequest());
        return new SortByRequest();
    }    
    
    @ModelAttribute
    public SearchRequest createMyTask(Model model) {
        model.addAttribute("SearchRequest", new SearchRequest());
        return new SearchRequest();
    }        
    
    @ModelAttribute
    public OrderUpdateReuqest getOrderUpdateReuqestModel(Model model) {
        model.addAttribute("OrderUpdateReuqest", new OrderUpdateReuqest());
        return new OrderUpdateReuqest();
    }        
    
    @ModelAttribute("searchFields")
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
    
    @ModelAttribute("noteTypes")
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

    @ModelAttribute("searchDates")
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
    @ModelAttribute("searchStatus")
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
    
    @ModelAttribute("sizeOptions")
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
    
    @ModelAttribute("searchByDateFields")
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
    
    @ModelAttribute("sortByDateFields")
    public Map getSortByDateFields()
    {

        Map< String, String > phones = new HashMap<>();  
        
        phones.put("datePaid",  "결제일");  
        phones.put("dateSendBy", "발송기한일");  
        phones.put("sizeAndDatePaid",  "사이즈/결제일");  
        phones.put("sizeAndDateSendBy",  "사이즈/발송기한일");  
        return phones;
    }    
    
    @ModelAttribute("sortTypes")
    public Map getSortTypes()
    {

        Map< String, String > phones = new HashMap<>();  
        
        phones.put("ASC",  "오래된거먼저/작은거먼저");  
        phones.put("DESC", "최근거먼저/큰거먼저");  
        return phones;
    } 
    
    
    @ModelAttribute("storeNames")
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
    
    
    @RequestMapping(value = "/naver/startMenu", method = RequestMethod.GET)
    public String getStartMenu( HttpServletRequest req,
            Model model) {

        try {
            AuthUtil.checkAndUpdateAuthentication(req);
        }
        catch (Exception e) {
            return "/naver/start";
        }

        return "/naver/dashboard";
    }    
    
    
    @RequestMapping(value = "/naver/searchPage", method = RequestMethod.GET)
    public String getForm( HttpServletRequest req,
            Model model) {

        HttpSession session = req.getSession();
        SearchContext ctx = WebUtil.getSearchContext(session);

        
        model.addAttribute("request", ctx.getSearchRequest());
        model.addAttribute("sort", ctx.getSortRequest());
        
//      return "/naver/search";
      return ctx.getResultPage();
    }
    
    
    
    @RequestMapping(value = "/naver/gotoDetail", method = RequestMethod.GET)
    public String getToDetail(
            HttpServletRequest req,
            Model model) {        

        try {
            AuthUtil.checkAndUpdateAuthentication(req);
        }
        catch (Exception e) {
            return "/naver/start";
        }
        
        return "/naver/detail";
    }
    
    @RequestMapping(value = "/naver/detailPage", method = RequestMethod.GET)
    public String getDetailForm(
            HttpServletRequest req,
            @RequestParam("orderNum") String orderNum,
            Model model) {

        try { 
            AuthUtil.checkAndUpdateAuthentication(req);
        }
        catch (Exception e) {
            return "/naver/start";
        }
        
        
        List<NaverOrderNote> notes = BookingDbInstance.getInstance().getNaverBookingDao().getNaverOrderNoteByOrderNum(orderNum);
        
        if (notes != null) 
            model.addAttribute("notes", notes);
        
        NaverOrder order  = searchManager.searchByOrderNum(orderNum);
        model.addAttribute("order", order);
        return "/naver/detail";
    }
    
    @RequestMapping(value = "/naver/orderUpdate", method = RequestMethod.POST)
    public String updateOrderData(
            @ModelAttribute("OrderUpdateReuqest") @Validated OrderUpdateReuqest orderUpdateReuqest,  
            HttpServletRequest req,
            Model model,
            RedirectAttributes redirectAttributes ) {

        try {
            AuthUtil.checkAndUpdateAuthentication(req);
        }
        catch (Exception e) {
            return "/naver/start";
        }
        
        NaverOrder order  = searchManager.searchByOrderNum(orderUpdateReuqest.getOrderProductNum());
        
        if (order == null) {
            return "/naver/searchAllPending"; //??
        } 
        
        if ( "trackNumEntered".equalsIgnoreCase(orderUpdateReuqest.getFieldName())) {
            
            if ( !StringUtils.isBlank(orderUpdateReuqest.getValue1())) {
                order.setTrackNumEntered(orderUpdateReuqest.getValue1());
                order.setEnteredWithoutSending(orderUpdateReuqest.getValue2());
                order.setDateTrackNumEntered(ValueUtil.getCurrentTimestamp());
                BookingDbInstance.getInstance().getNaverBookingDao().update(order);
            }
            
        } else if ( "confirmShipping".equalsIgnoreCase(orderUpdateReuqest.getFieldName())) {
            order.setEnteredWithoutSending(null);
            BookingDbInstance.getInstance().getNaverBookingDao().update(order);
            m_logger.info("Unmark the field setEnteredWithoutSending");

        } else if ( "forcedStatus".equalsIgnoreCase(orderUpdateReuqest.getFieldName())) {
            
            if ( !StringUtils.isBlank(orderUpdateReuqest.getValue1())) {
                order.setOrderStatus(orderUpdateReuqest.getValue1().trim());
                order.setForcedStatus(orderUpdateReuqest.getValue1().trim());
                order.setDateForcedStatus(ValueUtil.getCurrentTimestamp());
                if ( !StringUtils.isBlank(orderUpdateReuqest.getValue2())) {
                    order.setOrderDetailStatus(orderUpdateReuqest.getValue2().trim());
                    order.setForcedDetailStatus(orderUpdateReuqest.getValue2().trim());
                }
                
                BookingDbInstance.getInstance().getNaverBookingDao().update(order);
            }
            
        } else if ( "orderPlacement".equalsIgnoreCase(orderUpdateReuqest.getFieldName())) {
            
                
                order.setOrderPlacementNumber(orderUpdateReuqest.getValue1());
                order.setStoreName(orderUpdateReuqest.getValue2());
                order.setStoreOrderNumber(orderUpdateReuqest.getValue3());
                order.setOrderAccount(orderUpdateReuqest.getValue4());
                order.setDatePlaced(ValueUtil.getCurrentTimestamp());
                
                if ( orderUpdateReuqest.getValue5() != null ) {
                    order.setPriceOriginalProduct( ValueUtil.convertFromString(Double.class, orderUpdateReuqest.getValue5()));
                }
                
                BookingDbInstance.getInstance().getNaverBookingDao().update(order);
            
        } else if ( "note".equalsIgnoreCase(orderUpdateReuqest.getFieldName())) {
            
            if ( !StringUtils.isBlank(orderUpdateReuqest.getValue1())) {
                NaverOrderNote note = new NaverOrderNote();
                note.setDateEntered(ValueUtil.getCurrentTimestamp());
                note.setNote(orderUpdateReuqest.getValue1());
                note.setOrderProductNum(orderUpdateReuqest.getOrderProductNum());
                note.setTimeCreated(System.currentTimeMillis());
                note.setTimeUpdated(System.currentTimeMillis());
                note.setType(orderUpdateReuqest.getValue2());
                
                BookingDbInstance.getInstance().getNaverBookingDao().insert(note);
            
                if ( "ALERT".equalsIgnoreCase(note.getType()) || "REMINDER".equalsIgnoreCase(note.getType()) ) {
                    order.setFlagged("1");
                    order.setDateFlagged(ValueUtil.getCurrentTimestamp());
                    BookingDbInstance.getInstance().getNaverBookingDao().update(order);
                }
                
                m_logger.info("New Note added to [{}] - {}", orderUpdateReuqest.getOrderProductNum(), note);
            }
        }
        
        List<NaverOrderNote> notes = BookingDbInstance.getInstance().getNaverBookingDao().getNaverOrderNoteByOrderNum(orderUpdateReuqest.getOrderProductNum());
        
        if (notes != null) 
            model.addAttribute("notes", notes);
        model.addAttribute("order", order);
        
        redirectAttributes.addFlashAttribute("order", order);
        redirectAttributes.addFlashAttribute("notes", notes);
        return "redirect:/naver/detailPage?orderNum=" + order.getOrderProductNum();
    }
    

    @RequestMapping(value = "/naver/searchAllPending", method = RequestMethod.GET)
    public String getBasic(
            HttpServletRequest req,
            Model model) {

        try {
            AuthUtil.checkAndUpdateAuthentication(req);
        }
        catch (Exception e) {
            return "/naver/start";
        }
        
        
        HttpSession session = req.getSession();
        SearchContext ctx = WebUtil.getSearchContext(session);
        
//        List<NaverOrder> results = searchManager.searchAllPendingShipping();
        
        SearchRequest r = new SearchRequest();
        r.setSearchStatus("발송대기");
        
        List<NaverOrder> results  = searchManager.searchByRequest(r);
        
        ctx.setSearchRequest(r);
        ctx.setSearchResults(results);
        ctx.refreshSorting();
        ctx.setResultPage("/naver/search");
        model.addAttribute("request", ctx.getSearchRequest());
        model.addAttribute("sort", ctx.getSortRequest());
        
//        return "/naver/search";
        return ctx.getResultPage();
    }    
    
    
    @RequestMapping(value = "/naver/searchToday", method = RequestMethod.GET)
    public String getOrderToday(
            HttpServletRequest req,
            Model model) {

        try {
            AuthUtil.checkAndUpdateAuthentication(req);
        }
        catch (Exception e) {
            return "/naver/start";
        }
        
        
        HttpSession session = req.getSession();
        SearchContext ctx = WebUtil.getSearchContext(session);
        
//        List<NaverOrder> results = searchManager.searchAllPendingShipping();
        
        SearchRequest r = new SearchRequest();
        r.setSearchStatus("모든상태");
        
        r.setSearchDateField("datePaid");
        r.setSearchDateKey(DateUtil.getTodayDate());
        
        List<NaverOrder> results  = searchManager.searchByRequest(r);
        
        ctx.setSearchRequest(r);
        ctx.setSearchResults(results);
        ctx.refreshSorting();
        ctx.setResultPage("/naver/search");
        
        model.addAttribute("request", ctx.getSearchRequest());
        model.addAttribute("sort", ctx.getSortRequest());
        
//        return "/naver/search";
        return ctx.getResultPage();
    }     
  
    @RequestMapping(value = "/naver/searchShippingProcessedToday", method = RequestMethod.GET)
    public String getShippingProcessedToday(
            HttpServletRequest req,
            Model model) {

        try {
            AuthUtil.checkAndUpdateAuthentication(req);
        }
        catch (Exception e) {
            return "/naver/start";
        }
        
        
        HttpSession session = req.getSession();
        SearchContext ctx = WebUtil.getSearchContext(session);
        
//        List<NaverOrder> results = searchManager.searchAllPendingShipping();
        
        SearchRequest r = new SearchRequest();
        r.setSearchStatus("발송대기");
        
        r.setSearchDateField("dateTrackNumEntered");
        r.setSearchDateKey(DateTime.now().toString("MM/dd"));
        
        List<NaverOrder> results  = searchManager.searchByRequest(r);
        
        ctx.setSearchRequest(r);
        ctx.setSearchResults(results);
        ctx.refreshSorting();
        ctx.setResultPage("/naver/searchSlim");
        
        model.addAttribute("request", ctx.getSearchRequest());
        model.addAttribute("sort", ctx.getSortRequest());
        
//        return "/naver/search";
        return ctx.getResultPage();
    }        
    
    @RequestMapping(value = "/naver/searchByFilter", method = RequestMethod.POST)
    public String searchByFilter(@ModelAttribute("SearchRequest") @Validated SearchRequest searchRequest,  
            BindingResult result,  
            HttpServletRequest req,
            Model model, final 
            RedirectAttributes redirectAttributes ) {

        try {
            AuthUtil.checkAndUpdateAuthentication(req);
        }
        catch (Exception e) {
            return "/naver/start";
        }
        
        
        m_logger.info("searchByFilter [{}]", searchRequest);
        HttpSession session = req.getSession();
        
//        NaverBookingDataPersistService persistService = BookingDbInstance.getInstance();
        
        /*
         * clean
         */

        SearchContext ctx = WebUtil.getSearchContext(session);
        
        String formatted = searchRequest.getKeyword() == null? null: searchRequest.getKeyword().trim();
        searchRequest.setKeyword(formatted);
        
        List<NaverOrder> results = searchManager.searchByRequest(searchRequest);
        
        ctx.setSearchRequest(searchRequest);
        ctx.setSearchResults(results);
        ctx.refreshSorting();
        
        model.addAttribute("request", ctx.getSearchRequest());
        model.addAttribute("sort", ctx.getSortRequest());
        
        redirectAttributes.addFlashAttribute("request", ctx.getSearchRequest());
        redirectAttributes.addFlashAttribute("sort", ctx.getSortRequest());
        return "redirect:/naver/searchPage";
    }
    
    

    @RequestMapping(value = "/naver/sortByFilter", method = RequestMethod.POST)
    public String sortByFilter(@ModelAttribute("SortyByRequest") @Validated SortByRequest searchForm,  
            BindingResult result,  
            HttpServletRequest req,
            Model model, final 
            RedirectAttributes redirectAttributes ) {
        
        try {
            AuthUtil.checkAndUpdateAuthentication(req);
        }
        catch (Exception e) {
            
            return "redirect:/naver/start";
        }
        
        
        m_logger.info("sortByFilter [{}]", searchForm);
        HttpSession session = req.getSession();
        
        SearchContext ctx = WebUtil.getSearchContext(session);
        ctx.setSortRequest(searchForm);
        ctx.refreshSorting();
        
        model.addAttribute("request", ctx.getSearchRequest());
        model.addAttribute("sort", ctx.getSortRequest());
        
        redirectAttributes.addFlashAttribute("request", ctx.getSearchRequest());
        redirectAttributes.addFlashAttribute("sort", ctx.getSortRequest());
        
        return "redirect:/naver/searchPage";
    }      
    
    @ModelAttribute
    public AuthRequest getAuthRequestModel(Model model) {
        model.addAttribute("AuthRequest", new AuthRequest());
        return new AuthRequest();
    }    

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest req, Model model) {
        
        try {
            AuthUtil.checkAndUpdateAuthentication(req);
        }
        catch (Exception e) {
            return "/naver/start";
        }        
        
        return "/naver/dashboard";
    }    
    
    @RequestMapping(value = "/naver/checkmeout", method = RequestMethod.POST)
    public String checkmeout(@ModelAttribute("AuthRequest") @Validated AuthRequest authRequest,  
            BindingResult result,  
            HttpServletRequest req,
            Model model, final 
            RedirectAttributes redirectAttributes ) {

        
        HttpSession session = req.getSession();

        try {
            AuthUtil.authenticate(req, authRequest);
        }
        catch (Exception e) {
            m_logger.error(e.getMessage() ,e);
            return "/naver/start";
        }
        
        return "/naver/dashboard";
    }
    
    
}
