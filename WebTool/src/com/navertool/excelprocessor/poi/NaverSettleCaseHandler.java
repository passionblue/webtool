package com.navertool.excelprocessor.poi;
import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.navertool.db.booking.FileProcessHistory;
import com.navertool.db.booking.NaverModel;
import com.navertool.db.booking.NaverOrder;
public class NaverSettleCaseHandler extends BaseNaverExcelDataHandler {

    private static Logger m_logger = LoggerFactory.getLogger(NaverSettleCaseHandler.class);

    
    // Default Constructor
    public NaverSettleCaseHandler(String file) throws Exception {
        super(file);
    }
    
    

    @Override
    public NaverModel generateModel() {
        NaverModel model = new NaverOrder(this.titleType, this.titleDateType);
        return model;
    }

    
    
    public void processSheet() throws Exception {

      FileProcessHistory history = persistService.getNaverBookingDao().getHistory(titleType, titleDateType);
        
//        if ( history != null ) {
//            m_logger.error("ALREADY process {}", history );
//            return;
//        }
        
        int failCount = 0;
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {

            Row row = worksheet.getRow(i);
            String orderNum = (String) getValueFromRow("orderNum", colums, row);
            
            NaverOrder naverOrder = (NaverOrder) persistService.getNaverBookingDao().getNaverOrderByOrderNum(orderNum);

            if ( naverOrder == null ) {
                
                m_logger.error("########### Naver Order not found for {}", orderNum);
                continue;
            }

            
            String payType = (String) getValueFromRow("payType", colums, row);
            Object feeSettle = getValueFromRow("feeSettle", colums, row);
            Object feeAffiliate = getValueFromRow("feeAffiliate", colums, row);
            
            try {

                if ( "배송비".equals(payType) ) {
                    BeanUtils.setProperty(naverOrder, "fee7", feeSettle);
                    BeanUtils.setProperty(naverOrder, "fee7Detail", "배송비수수료");
                } else if ( "상품주문".equals(payType) ) {
                    BeanUtils.setProperty(naverOrder, "fee1", feeSettle);
                    BeanUtils.setProperty(naverOrder, "fee1Detail", "상품주문수수료");
                    BeanUtils.setProperty(naverOrder, "fee2", feeAffiliate);
                    BeanUtils.setProperty(naverOrder, "fee2Detail", "상품주문연동수수료");
                }
                
                naverOrder.incrementVersion();                
                naverOrder.setTimeUpdated(System.currentTimeMillis());
                m_logger.info("Order AFTER processed from row #{} - {}", i, naverOrder);
                
                persistService.getNaverBookingDao().update(naverOrder);
            }
            catch (Exception e) {
                m_logger.error(e.getMessage() ,e);
                failCount++;
            }
            
        }
        
        
        FileProcessHistory processHistoryEntry = new FileProcessHistory();
        processHistoryEntry.setDateProcessed(new DateTime(System.currentTimeMillis()).toString("yyyyMMdd"));
        processHistoryEntry.setFileName(fileName);
        processHistoryEntry.setRowCounts(worksheet.getPhysicalNumberOfRows()-1);
        processHistoryEntry.setTitleDate(titleDateType);
        processHistoryEntry.setTitleType(titleType);
        processHistoryEntry.setFailCounts(failCount);
        persistService.getNaverBookingDao().insert(processHistoryEntry);
        
    }

    
    protected NaverModel processPaySettleRow( NaverModel obj, List<String> columnNames, Row row) throws Exception  {
        
        for (int i = 0; i < columnNames.size(); i++) {
            
            String colName = columnNames.get(i);
            
            Cell   cell = row.getCell(i);
            Object value =  cellValue(cell);
            String name = fieldMap.get(colName);
            
            if (name == null) 
                continue;

            if ( value instanceof Date) {
                
                DateTime dt = new DateTime((Date) value );
                value = dt.toString("yyyyMMdd-HH:mm:ss");
            }
            
            try {
                BeanUtils.setProperty(obj, name, value);
            }
            catch (Exception e) {
                m_logger.error(e.getMessage() ,e);
            }
            
        }
        
        return obj;
    }    
    
    public static void main(String[] args) throws Exception {

        String extentions[] = new String[]{"xls", "xlsx" };
        
        
        
        List<File> files = (List) FileUtils.listFiles(new File("D:\\NaverDev\\data"), extentions, false);        
        
        for (File file : files) {
            
            if ( file.getName().startsWith("paySettleDailyDetail") ||
                    file.getName().startsWith("paySettleDailyDetail") ){

                NaverSettleCaseHandler h = new NaverSettleCaseHandler(file.getAbsolutePath());
                h.processSheet();
                
                m_logger.info("Processing file {}", file.getName());

            }
        }        
        
        // BaseNaverExcelDataHandler h = new
        // NaverOrderHandler("D:\\NaverDev\\data\\스토어팜_전체주문조회_20160923_2047.xls");
//        BaseNaverExcelDataHandler h = new NaverSettleCaseHandler("D:\\NaverDev\\data\\paySettleDailyDetail_20160831.xlsx");
//        h.processSheet();
    }
}
