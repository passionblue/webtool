package com.navertool.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.navertool.db.booking.BookingDbInstance;
import com.navertool.db.booking.NaverBookingDataPersistService;
import com.navertool.db.booking.NaverOrder;

public class SearchManager {

    private static Logger m_logger = LoggerFactory.getLogger(SearchManager.class);

    private NaverBookingDataPersistService persistService;

    // Default Constructor
    public SearchManager() {
        persistService = BookingDbInstance.getInstance();
    }

    public List<NaverOrder> searchAllPendingShipping() {

        List<NaverOrder> r = persistService.getNaverBookingDao().getNaverOrderByStringFieldSearch("orderStatus", "발송대기");

        return r;
    }

    public List<NaverOrder> searchByField(String field, String value) {

        List<NaverOrder> r = findOrdersByStatusKey("발송대기");

        List<NaverOrder> ret = new ArrayList();

        for (NaverOrder naverOrder : r) {

            List<String> fieldsTo = convertSearchFeilds(field);

            for (String f : fieldsTo) {
                Object obj = null;
                try {
                    obj = BeanUtils.getProperty(naverOrder, f);
                }
                catch (Exception e) {
                    continue;
                }
                if (value != null && obj != null && obj.toString().indexOf(value) >= 0) {

                    if (!ret.contains(naverOrder))
                        ret.add(naverOrder);
                }
            }
        }

        return ret;
    }

    public List<NaverOrder> searchByRequest(SearchRequest request) {

        List<NaverOrder> r = findOrdersByStatusKey(request.getSearchStatus());

        if (!StringUtils.isBlank(request.getKeyword())) {
            r = applyFieldBasedFilters(r, request.getSearchField(), request.getKeyword(), false);
        }

        if (!StringUtils.isBlank(request.getSearchDateField()) && !StringUtils.isBlank(request.getSearchDateKey())) {
            r = applyDateFieldBasedFilters(r, request.getSearchDateField(), request.getSearchDateKey(), true);
        }

        /*
         * Size compare
         */
        if (!StringUtils.isBlank(request.getOption1())) {
            r = applyFieldBasedFilters(r, "productInfo1", request.getOption1(), true);
        }

        return r;
    }

    private List<NaverOrder> applyDateFieldBasedFilters(List<NaverOrder> r, String field, String value, boolean exactCompare) {

        if (value == null)
            return r;

        value = value.trim();

        List<NaverOrder> ret = new ArrayList();
        Set<NaverOrder> tempSest = new HashSet();

        for (NaverOrder naverOrder : r) {

            List<String> fieldsTo = convertSearchFeilds(field);

            for (String f : fieldsTo) {
                Object obj = null;
                try {
                    obj = BeanUtils.getProperty(naverOrder, f);

                    if (obj == null)
                        continue;

                    obj = DateTimeFormat.forPattern("yyyyMMdd-HH:mm:ss").parseDateTime(obj.toString()).toString("MM/dd");
                }
                catch (Exception e) {
                    continue;
                }
                boolean matchFound = false;

                if (value != null && obj != null) {
                    String objString = obj.toString().trim();

                    if (exactCompare)
                        matchFound = objString.equalsIgnoreCase(value);
                    else
                        matchFound = objString.indexOf(value) >= 0;
                }

                if (matchFound) {
                    if (!tempSest.contains(naverOrder)) {
                        tempSest.add(naverOrder);
                        ret.add(naverOrder);
                    }
                }
            }
        }

        return ret;
    }

    private List<NaverOrder> applyFieldBasedFilters(List<NaverOrder> r, String field, String value, boolean exactCompare) {

        List<NaverOrder> ret = new ArrayList();
        Set<NaverOrder> tempSest = new HashSet();
        
        for (NaverOrder naverOrder : r) {

            List<String> fieldsTo = convertSearchFeilds(field);

            for (String f : fieldsTo) {
                Object obj = null;
                try {
                    obj = BeanUtils.getProperty(naverOrder, f);
                }
                catch (Exception e) {
                    continue;
                }
                boolean matchFound = false;

                if (value != null && obj != null) {
                    String objString = obj.toString().trim();

                    if (exactCompare)
                        matchFound = objString.equalsIgnoreCase(value);
                    else
                        matchFound = objString.indexOf(value) >= 0;
                }

                if (matchFound) {
                    if (!tempSest.contains(naverOrder)) {
                        tempSest.add(naverOrder);
                        ret.add(naverOrder);
                    }
                }
            }
        }

        return ret;
    }

    private List<NaverOrder> findOrdersByStatusKey(String key) {

        if ("발주확인".equalsIgnoreCase(key)) {
            return persistService.getNaverBookingDao().getNaverOrderByStatus("발송대기", "발주확인");
        }
        else if ("취소완료".equalsIgnoreCase(key)) {
            return persistService.getNaverBookingDao().getNaverOrderByStatus("취소", "취소완료");
        }
        else if ("배송중".equalsIgnoreCase(key)) {
            return persistService.getNaverBookingDao().getNaverOrderByStatus("배송중");
        }
        else if ("배송완료".equalsIgnoreCase(key)) {
            return persistService.getNaverBookingDao().getNaverOrderByStatus("배송완료");
        }
        else if ("반품완료".equalsIgnoreCase(key)) {
            return persistService.getNaverBookingDao().getNaverOrderByStatus("발송대기");
        }
        else if ("발송대기".equalsIgnoreCase(key)) {
            return persistService.getNaverBookingDao().getNaverOrderByStatus("발송대기", "발주확인");
        }
        else if ("모든상태".equalsIgnoreCase(key)) {
            return persistService.getNaverBookingDao().getAllNaverOrder();
        }
        else if ("취소반품제외".equalsIgnoreCase(key)) {
            return persistService.getNaverBookingDao().getNaverOrderByExcludingCancelReturnStatus();
        }
        else {
            return persistService.getNaverBookingDao().getNaverOrderByStatus("발송대기", "발주확인");
        }

    }

    private List<String> convertSearchFeilds(String field) {

        List<String> ret = new ArrayList();

        if (field.equalsIgnoreCase("name")) {
            ret.add("customerName");
            ret.add("recipientName");
        }
        else if (field.equalsIgnoreCase("all")) {
            ret.add("customerName");
            ret.add("orderProductNum");
            ret.add("orderNum");
            ret.add("customerId");
            ret.add("orderPlacementNumber");
            ret.add("trackingNum");
            ret.add("trackNum"); // song jang
            ret.add("productNum");
            ret.add("productName");
        }
        else {
            ret.add(field);
        }

        return ret;
    }

    public NaverOrder searchByOrderNum(String orderNum) {
        return persistService.getNaverBookingDao().getNaverOrderByOrderProductNum(orderNum);
    }

}
