package com.navertool.db.booking;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.navertool.web.ValueUtil;


@Repository("naverBookingDao")
@Transactional(propagation = Propagation.REQUIRED)
public class NaverBookingDao {

    private static final String SELECT_NAVER_ORDER = "select f from NaverOrder f";
    private static final String SELECT_NAVER_ORDER_NOTE = "select f from NaverOrderNote f";
    private static final String SELECT_FILE_HISTORY = "select f from FileProcessHistory f";
    private static final String SELECT_KEYHISTORY_QUERY = "select f from KeywordHistory f";
    private static final String SELECT_INVENTORY_QUERY = "select f from NaverOrderInventory f";

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public synchronized void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    
    public synchronized void insert(NaverModel person) {
        entityManager.persist(person);
    }

    public synchronized  void update(NaverModel entity) {
        entityManager.merge(entity);
    }
    
    public synchronized  void remove(NaverModel entity, Integer id) {
//        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
        
        NaverModel actorToBeRemoved = entityManager.getReference(entity.getClass(), id);
        entityManager.remove(actorToBeRemoved);
    }
    
    
    
    public synchronized  NaverOrder getNaverOrderByOrderNum(String orderNum) {
        Query query = entityManager.createQuery(SELECT_NAVER_ORDER + " where orderNum='" + orderNum +"'" );
        List<NaverOrder> persons = (List<NaverOrder>) query.getResultList();
        
        if ( persons != null && persons.size() > 0 ) {
            return persons.get(persons.size()-1);
        }
        return null;
    }

    public synchronized  NaverOrder getNaverOrderByOrderProductNum(String orderProductNum) {
        Query query = entityManager.createQuery(SELECT_NAVER_ORDER + " where orderProductNum='" + orderProductNum +"'");
        List<NaverOrder> persons = (List<NaverOrder>) query.getResultList();
        if ( persons != null && persons.size() > 0 ) {
            return persons.get(persons.size()-1);
        }
        return null;
    }
    
    public synchronized  List<NaverOrder> getNaverOrderByNames(String name) {
        Query query = entityManager.createQuery(SELECT_NAVER_ORDER + " where customerName='" + name +"' OR  recipientName='" + name+ "'");
        List<NaverOrder> persons = (List<NaverOrder>) query.getResultList();

        return persons;
    }

    // search by numbers that combined code by joshua
    public synchronized  List<NaverOrder> getNaverOrderByPlacementNumber(String key) {
        Query query = entityManager.createQuery(SELECT_NAVER_ORDER + " where orderPlacementNumber='" + key +"'");
        List<NaverOrder> persons = (List<NaverOrder>) query.getResultList();

        return persons;
    }

    public synchronized  List<NaverOrder> getNaverOrderByProductNum(String key) {
        Query query = entityManager.createQuery(SELECT_NAVER_ORDER + " where productNum='" + key +"'");
        List<NaverOrder> persons = (List<NaverOrder>) query.getResultList();

        return persons;
    }
    
    public synchronized  List<NaverOrder> getNaverOrderByStringFieldSearch(String field, String key) {
        Query query = entityManager.createQuery(SELECT_NAVER_ORDER + " where " + field + "='" + key +"'");
        List<NaverOrder> persons = (List<NaverOrder>) query.getResultList();
        return persons;
    }
    
    public synchronized  List<NaverOrder> getNaverOrderByStatus(String status) {
        Query query = entityManager.createQuery(SELECT_NAVER_ORDER + " where orderStatus='" + status +"'");
        List<NaverOrder> persons = (List<NaverOrder>) query.getResultList();
        return persons;
    }

    public synchronized  List<NaverOrder> getNaverOrderByStatus(String status, String subStatus) {
        
        if ( StringUtils.isBlank(subStatus)){
            return getNaverOrderByStatus(status);
        }
        
        Query query = entityManager.createQuery(SELECT_NAVER_ORDER + " where orderStatus='" + status +"' and orderDetailStatus='" +subStatus +"'");
        List<NaverOrder> persons = (List<NaverOrder>) query.getResultList();
        return persons;
    }

    public synchronized  List<NaverOrder> getNaverOrderByExcludingCancelReturnStatus() {
        
        Query query = entityManager.createQuery(SELECT_NAVER_ORDER + " where orderStatus NOT IN ('취소','반품')");
        List<NaverOrder> persons = (List<NaverOrder>) query.getResultList();
        return persons;
    }

    
    
    public synchronized List<NaverOrder> getAllNaverOrder() {
        Query query = entityManager.createQuery(SELECT_NAVER_ORDER);
        List<NaverOrder> persons = (List<NaverOrder>) query.getResultList();
        return persons;
    }
    
    
    /*
     * NOTES
     */
    
    public synchronized  List<NaverOrderNote> getNaverOrderNoteByOrderNum(String orderProductNum) {
        Query query = entityManager.createQuery(SELECT_NAVER_ORDER_NOTE + " where orderProductNum='" + orderProductNum +"'");
        List<NaverOrderNote> persons = (List<NaverOrderNote>) query.getResultList();

        return persons;
    }    
    
    
    public synchronized  List<NaverOrderNote> getNaverOrderNoteByDatetime(String datetime) {
        
        long timestamp = ValueUtil.getTimestamp(datetime);
        
        Query query = entityManager.createQuery(SELECT_NAVER_ORDER_NOTE + " where timestamp > " + timestamp );
        List<NaverOrderNote> persons = (List<NaverOrderNote>) query.getResultList();

        return persons;
    }       
    
    
    /*
     * File History 
     */
    
    public synchronized  FileProcessHistory getHistory(String titleType, String titleDate) {
        Query query = entityManager.createQuery(SELECT_FILE_HISTORY + " where titleType='" + titleType +"' AND titleDate='" + titleDate+ "'");
        List<FileProcessHistory> persons = (List<FileProcessHistory>) query.getResultList();
        if ( persons != null && persons.size() > 0 ) {
            return persons.get(0);
        }
        return null;
    }
    
    
    public synchronized  List<FileProcessHistory> getAllFileHistory() {
        Query query = entityManager.createQuery(SELECT_FILE_HISTORY);
        List<FileProcessHistory> persons = (List<FileProcessHistory>) query.getResultList();
        return persons;
    } 

    
    
    /*
     * Inventory
     */
    
    public synchronized List<NaverOrderInventory> getAllNaverInventory() {
        Query query = entityManager.createQuery(SELECT_INVENTORY_QUERY);
        List<NaverOrderInventory> ret = (List<NaverOrderInventory>) query.getResultList();
        return ret;
    }
 
    public synchronized List<NaverOrderInventory> getInventoryByStoreOrderNum(String num) {
        Query query = entityManager.createQuery(SELECT_INVENTORY_QUERY + " where orderNum='" + num +"'");
        List<NaverOrderInventory> ret = (List<NaverOrderInventory>) query.getResultList();
        return ret;
    }
    

    public synchronized List<NaverOrderInventory> getInventoryByOrderPlancemenNum(String num) {
        Query query = entityManager.createQuery(SELECT_INVENTORY_QUERY + " where orderPlacementNum='" + num +"'");
        List<NaverOrderInventory> ret = (List<NaverOrderInventory>) query.getResultList();
        return ret;
    }
    
    
    public synchronized NaverOrderInventory getInventoryById(String id) {
        Query query = entityManager.createQuery(SELECT_INVENTORY_QUERY + " where id=" + id);
        List<NaverOrderInventory> ret = (List<NaverOrderInventory>) query.getResultList();
        
        if ( ret != null || ret.size() > 0 )
            return ret.get(0);
        return null;
    }
    
}
