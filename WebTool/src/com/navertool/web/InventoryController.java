package com.navertool.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.navertool.db.booking.BookingDbInstance;
import com.navertool.db.booking.NaverOrder;
import com.navertool.db.booking.NaverOrderInventory;
import com.navertool.search.NewInventory;
import com.navertool.search.SearchManager;
import com.navertool.search.SearchRequest;

@Controller
public class InventoryController extends BaseController{

    private static Logger m_logger = LoggerFactory.getLogger(InventoryController.class);

    private InventoryOrderMap inventoryMap;
    private SearchManager searchManager = new SearchManager();
    
    // Default Constructor
    public InventoryController() {
        
        List<NaverOrderInventory> inventoryList = BookingDbInstance.getInstance().getNaverBookingDao().getAllNaverInventory();
        inventoryMap = InventoryOrderMap.getInstance();
        inventoryMap.processInventory(inventoryList);
        
        m_logger.info("InventoryController initialized");
    }

    @ModelAttribute("productNumbers")
    public Map getSearchFields()
    {
        
        Map< String, String > phones = new HashMap<>();  
        phones.put("496053506", "나이키 마노아 레더 부츠 헤이스택 황색 남성");  
        phones.put("460109209", "나이키 에어 프레스토 올검 검정");  
          
        return phones;
    }    
    
    @ModelAttribute("sizeOptions2")
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

    @ModelAttribute("dateOptions")
    public List getSearchDates()
    {
        List<String> dates = new ArrayList();
        
        DateTime dt = new DateTime(new Date() );

        dates.add(dt.toString("MM/dd"));
        dt = dt.minusDays(15);
        for (int i = 0; i < 30; i++) {
            dates.add(dt.toString("MM/dd"));
            dt = dt.plusDays(1);
        }
        
        return dates;
    }    
    
    
    @RequestMapping(value = "/naver/inventoryListPage", method = RequestMethod.GET)
    public String getStartMenu( HttpServletRequest req,
            Model model) {

        try {
            AuthUtil.checkAndUpdateAuthentication(req);
        }
        catch (Exception e) {
            return "/naver/start";
        }
        HttpSession session = req.getSession();

        SearchRequest r = new SearchRequest();
        r.setSearchStatus("발송대기");
        List<NaverOrder> results  = searchManager.searchByRequest(r);
        model.addAttribute("ordersList", results);        
        
        List<NaverOrderInventory> inventoryList = BookingDbInstance.getInstance().getNaverBookingDao().getAllNaverInventory();
        
        session.setAttribute("inventoryList", inventoryList);
        session.setAttribute("inventoryMap",  inventoryMap);
        return "/naver/inventoryList";
    }    
    
    
    @RequestMapping(value = "/naver/inventoryOrderPackageListPage", method = RequestMethod.GET)
    public String getInventoryOrderPackageListPage( HttpServletRequest req,
            Model model) {

        try {
            AuthUtil.checkAndUpdateAuthentication(req);
        }
        catch (Exception e) {
            return "/naver/start";
        }
        HttpSession session = req.getSession();
        
        List<NaverOrderInventory> inventoryList = BookingDbInstance.getInstance().getNaverBookingDao().getAllNaverInventory();
        
        model.addAttribute("inventoryList", inventoryList);
        return "/naver/inventoryOrderPackageList";
    }    
    
    
    @RequestMapping(value = "/naver/inventoryAdd", method = RequestMethod.POST)
    public String addNewInventory(
            @ModelAttribute("NaverOrderInventory") NewInventory inventory,  
            HttpServletRequest req,
            Model model,
            RedirectAttributes redirectAttributes ) {

        try { 
            AuthUtil.checkAndUpdateAuthentication(req);
        }
        catch (Exception e) {
            return "/naver/start";
        }
        HttpSession session = req.getSession();
        
        WebUtil.trimAllStringFields(inventory);;
        
        List<NaverOrder> l = BookingDbInstance.getInstance().getNaverBookingDao().getNaverOrderByProductNum(inventory.getProductNum());
        
        String productName = inventory.getOrderProductNum();
        if (l.size() >  0 ) {
            productName = l.get(l.size()-1).getProductName();
        }
        
        int qty = inventory.getQty();

        for (int i = 0; i < qty; i++) {

            NaverOrderInventory naverInventory = new NaverOrderInventory();
            
            try {
                BeanUtils.copyProperties(naverInventory, inventory);
            }
            catch (Exception  e) {
                m_logger.error(e.getMessage() ,e);
                continue;
            }
            
            naverInventory.setProductDescription(productName);

            // Persist
            BookingDbInstance.getInstance().getNaverBookingDao().insert(naverInventory);
            inventoryMap.processInventory(naverInventory);
            m_logger.info("New Inventory Added {} ", naverInventory);
        }
        
        
        SearchRequest r = new SearchRequest();
        r.setSearchStatus("발송대기");
        List<NaverOrder> results  = searchManager.searchByRequest(r);
        model.addAttribute("ordersList", results);
        redirectAttributes.addFlashAttribute("ordersList", results);

        List<NaverOrderInventory> inventoryList = BookingDbInstance.getInstance().getNaverBookingDao().getAllNaverInventory();
        
        session.setAttribute("inventoryList", inventoryList);
        session.setAttribute("inventoryMap",  inventoryMap);
        
        return "redirect:/naver/inventoryOrderPackageListPage";
    }
    
    @RequestMapping(value = "/naver/inventoryUpdate", method = RequestMethod.POST)
    public String updateInventory(
            @ModelAttribute("NaverOrderInventory") NewInventory inventory,  
            HttpServletRequest req,
            Model model,
            RedirectAttributes redirectAttributes ) {

        try { 
            AuthUtil.checkAndUpdateAuthentication(req);
        }
        catch (Exception e) {
            return "/naver/start";
        }
        HttpSession session = req.getSession();

        
        if ( inventory.isRemoved() ) {
            String id = inventory.getOption2();

            NaverOrderInventory found = BookingDbInstance.getInstance().getNaverBookingDao().getInventoryById(id.trim());
        
            if (found != null) { 
                BookingDbInstance.getInstance().getNaverBookingDao().remove(found, found.getId());
                m_logger.info("Inventory removed [{}], {}", id, found);
            }
            
        } else {
            // Code to update
            
            
        }
        
        SearchRequest r = new SearchRequest();
        r.setSearchStatus("발송대기");
        List<NaverOrder> results  = searchManager.searchByRequest(r);
        model.addAttribute("ordersList", results);
        redirectAttributes.addFlashAttribute("ordersList", results);

        List<NaverOrderInventory> inventoryList = BookingDbInstance.getInstance().getNaverBookingDao().getAllNaverInventory();
        
        session.setAttribute("inventoryList", inventoryList);
        session.setAttribute("inventoryMap",  inventoryMap);
        
        return "redirect:/naver/inventoryOrderPackageListPage";
    }    
    
    private synchronized OrderMap getInventoryMap(){
        
        if ( inventoryMap == null ) {
            List<NaverOrderInventory> inventoryList = BookingDbInstance.getInstance().getNaverBookingDao().getAllNaverInventory();
//            orderMap = new OrderMap(inventoryList, "", "");
        }
        
        return inventoryMap;
    }
    
    
}
