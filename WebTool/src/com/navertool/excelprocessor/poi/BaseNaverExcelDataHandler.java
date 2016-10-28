package com.navertool.excelprocessor.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.navertool.db.booking.FileProcessHistory;
import com.navertool.db.booking.NaverBookingDataPersistService;
import com.navertool.db.booking.NaverModel;
import com.navertool.db.booking.NaverOrder;
import com.navertool.db.booking.NaverOrderHistory;
import com.navertool.util.DataUtil;
import com.navertool.util.FileUtil;

abstract public class BaseNaverExcelDataHandler {

    private static Logger m_logger = LoggerFactory.getLogger(BaseNaverExcelDataHandler.class);

    
    protected HSSFWorkbook workbook;
    protected Sheet worksheet;
    protected List<String> colums;
    protected List<String> columsForUpdateOnly;
    
    protected String fileName;
    protected String titleType;
    protected String titleDateType;
    protected boolean X2007Plus;
    protected boolean skipNotFoundOrder;
    
    protected Map<String,String> fieldMap;
    
    NaverBookingDataPersistService persistService;
    
    // Default Constructor
    public BaseNaverExcelDataHandler(String file) throws Exception  {
        this(file, false);
    }
    
    public BaseNaverExcelDataHandler(String file, boolean skipNotFoundOrder) throws Exception  {
        
        this.fileName           = file;
        this.skipNotFoundOrder  = skipNotFoundOrder;
        
        String baseName = FilenameUtils.getBaseName(file);
        
        if ( baseName.startsWith("스토어팜") ){
            
            int pos = baseName.indexOf("_", 5);
            
            titleType = baseName.substring(0,  pos);
            
            
            int pos2 = baseName.indexOf("\\.", pos+1);
            
            if ( pos2 == -1 ) 
                titleDateType = baseName.substring(pos+1);
            else
                titleDateType = baseName.substring(pos+1, pos2);

            FileInputStream fileInputStream = new FileInputStream(file);
            workbook = new HSSFWorkbook(fileInputStream);
            worksheet = workbook.getSheetAt(0);
            
            
        }  else {

            int pos = baseName.indexOf("_");
            
            titleType = baseName.substring(0,  pos);
            titleDateType = baseName.substring(pos+1);
            X2007Plus = true;
            
            
            OPCPackage pkg = OPCPackage.open(new File(file));   
            XSSFWorkbook wb = new XSSFWorkbook(pkg);
            worksheet = wb.getSheetAt(0);
        }


        colums = getColumnNames();
        
        
        m_logger.debug("File attached {} {} {}", baseName, titleType, titleDateType);
//        m_logger.debug("File attached {} ", maps);
        
        
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config-hsql-booking.xml");
        
        //searchRankPersistService was not defined in the config file but the class has Component annontation
        persistService = (NaverBookingDataPersistService) context.getBean("naverBookingDataPersistService"); 
        
        fieldMap = loadFieldMap(titleType);
        

    }


    public void processSheet() throws Exception {
        
        
        FileProcessHistory history = persistService.getNaverBookingDao().getHistory(titleType, titleDateType);
        
        if ( history != null ) {
            
            m_logger.error("ALREADY process {}", history );
            return;
        }
        
        int failCount =0;
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {

            Row row = worksheet.getRow(i);
            
            
            String orderNum = (String) getValueFromRow("orderNum", colums, row);
            
            NaverOrder naverOrder = persistService.getNaverBookingDao().getNaverOrderByOrderNum(orderNum);
            
            try {
                NaverOrder newData = (NaverOrder) generateModel();
                
//                if (naverOrder!= null && naverOrder.getOrderDetailStatus() != null && naverOrder.getOrderDetailStatus().equals("정산완료")) {
//                    m_logger.info("Skipped #{} because it was settled completed", orderNum);
//                    continue;
//                }
//                
//                if (naverOrder!= null && naverOrder.getOrderDetailStatus() != null && naverOrder.getOrderDetailStatus().equals("취소완료")) {
//                    m_logger.info("Skipped #{} because it was settled completed", orderNum);
//                    continue;
//                }                
                
                NaverModel data = processRow(newData, colums, row);
                
                if (naverOrder!= null) {
                    
                    if ( DataUtil.isTerminalState(naverOrder)) {
                            m_logger.info("Skipped #{} because it was settled completed", orderNum);
                            continue;
                    }                         
                    
                    Integer id = naverOrder.getId();
//                    BeanUtils.copyProperties(naverOrder, data);
                    copyProperties(naverOrder, data, columsForUpdateOnly != null? columsForUpdateOnly: colums);
                    naverOrder.incrementVersion();
                    naverOrder.setId(id);
                    
                    m_logger.info("Updated existing order {} {}", naverOrder.getId(), naverOrder.getOrderPlacementNumber());

                    persistService.getNaverBookingDao().update(naverOrder);

                } else {
                    if ( skipNotFoundOrder ) 
                        continue;

                    m_logger.info("Inserting New order {}", data);
                    
                    String size = DataUtil.getShoeSizeForOrder((NaverOrder)data);
                    ((NaverOrder)data).setProductInfo1(size);
                    
                    persistService.getNaverBookingDao().insert(data);
                }

                /*
                 * 
                 */
                NaverOrderHistory naverOrderHistory = new NaverOrderHistory();
                BeanUtils.copyProperties(naverOrderHistory, data);
                naverOrderHistory.setId(null);
                persistService.getNaverBookingDao().insert(naverOrderHistory);
                
                m_logger.info("Order AFTER processed from row #{} - {}", i, data);
                
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
    
    private void copyProperties(Object dest, Object src, List<String> fields) {
        
        
        for (String colName : fields) {

            String field = fieldMap.get(colName);
            
            if (field == null ) 
                continue;
            
            try {
                Object val = BeanUtils.getProperty(src, field);
                if ( val != null ) 
                    BeanUtils.setProperty(dest, field, val);
            }
            catch (Exception  e) {
                m_logger.error(e.getMessage() ,e);
            }
        }
        
    }
    
    
    abstract public NaverModel generateModel();
    
    
    protected List<String> getColumnNames() {
        
        Row row1 = worksheet.getRow(0);
        
        
        int startCell = row1.getFirstCellNum();
        int lastCell = row1.getLastCellNum();
        
        List<String> colums = new ArrayList();
        
        for (int i = startCell; i < lastCell; i++) {
            
            Cell cell = row1.getCell(i);
            
            
            if ( cell == null ) {
                m_logger.warn("Cell not returned for idx {}", i);
                continue;
            }
            int cellType = cell.getCellType();
            
//            m_logger.debug("Cell Type {}", cellType);
            
            String name = cellValueAsString(cell);
            
            colums.add(name == null?"":name);
        }

        
        return colums;
    }
    
    
    protected NaverModel processRow( NaverModel obj, List<String> columnNames, Row row) throws Exception  {
        
        
        
        
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
    

    protected Object getValueFromRow(String fieldName,  List<String> columnNames, Row row) throws Exception {

        return getValueFromRow(fieldName, fieldMap, columnNames, row);
    }    
    
    protected Object getValueFromRow(String fieldName,  Map<String,String> fieldMapping, List<String> columnNames, Row row) throws Exception {
        
        for (int i = 0; i < columnNames.size(); i++) {
            
            String colName = columnNames.get(i);
            
 
            
            Cell   cell = row.getCell(i);
            Object value =  cellValue(cell);
            
            if ( colName.equals("Order#") && value instanceof Double ){ // Only for "구입"
                BigDecimal bigDecimal = new BigDecimal((Double)value);
                String plaingDoubleString = bigDecimal.unscaledValue().toString();
                value = plaingDoubleString;
            }
            
            String name = fieldMapping.get(colName);
            
            if (name == null) 
                continue;

            if (fieldName.equalsIgnoreCase(name))  {
            
                if ( value instanceof Date) {
                    
                    DateTime dt = new DateTime((Date) value );
                    value = dt.toString("yyyyMMdd-HH:mm:ss");
                }

                return value;
            }
            
        }
        
        return null;
        
    }
    

    protected String cellValueAsString(Cell cell) {
        
        Object val = cellValue(cell);
        
        if ( val == null) return null;
        
        return val.toString();
    }
    
    
    protected Object cellValue(Cell cell) {
        
        switch(cell.getCellType()) {
        
        case Cell.CELL_TYPE_BOOLEAN: return Boolean.valueOf(cell.getBooleanCellValue());
        case Cell.CELL_TYPE_NUMERIC: 
            return HSSFDateUtil.isCellDateFormatted(cell)? cell.getDateCellValue(): Double.valueOf(cell.getNumericCellValue());
        case Cell.CELL_TYPE_STRING: return cell.getStringCellValue();
        
        default: return null;
        }
        
    }
    
    protected Map<String,String> loadFieldMap(String title) throws Exception {
        
        
        List<String> entries = FileUtil.loadKeywordsFromFile("excel/map/"+ title + ".map");
        Map<String, String> ret = new HashMap();
        
        for (String string : entries) {

            
            String pair[] = string.split("=");
            
            if ( pair == null || pair.length != 2 || pair[1] == null || pair[1].trim().length() == 0 ) continue;
            

            ret.put(pair[0], pair[1]);
            
        }
        
        return ret;
    }
    
    

    
    public static void main2(String[] args) {
        try {
            FileInputStream fileInputStream = new FileInputStream("D:\\NaverDev\\data\\스토어팜_전체주문조회_20160923_2047.xls");
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
            HSSFSheet worksheet = workbook.getSheetAt(0);
            HSSFRow row1 = worksheet.getRow(0);
            
           
            
            System.out.println("Number of cell getPhysicalNumberOfRows : " +  worksheet.getPhysicalNumberOfRows());
            System.out.println("Number of cell getFirstCellNum         : " + row1.getFirstCellNum());
            System.out.println("Number of cell: " + row1.getLastCellNum());
            System.out.println("Number of cell: " + row1.getPhysicalNumberOfCells());

            
//            
//            HSSFCell cellA1 = row1.getCell((short) 0);
//            String a1Val = cellA1.getStringCellValue();
//            HSSFCell cellB1 = row1.getCell((short) 1);
//            String b1Val = cellB1.getStringCellValue();
//            HSSFCell cellC1 = row1.getCell((short) 2);
//            boolean c1Val = cellC1.getBooleanCellValue();
//            HSSFCell cellD1 = row1.getCell((short) 3);
//            Date d1Val = cellD1.getDateCellValue();
//
//            System.out.println("A1: " + a1Val);
//            System.out.println("B1: " + b1Val);
//            System.out.println("C1: " + c1Val);
//            System.out.println("D1: " + d1Val);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
