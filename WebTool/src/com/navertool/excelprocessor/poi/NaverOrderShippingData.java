package com.navertool.excelprocessor.poi;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.navertool.db.booking.NaverModel;
import com.navertool.db.booking.NaverOrder;

public class NaverOrderShippingData extends BaseNaverExcelDataHandler {

    private static Logger m_logger = LoggerFactory.getLogger(NaverOrderShippingData.class);

    // Default Constructor
    public NaverOrderShippingData(String file) throws Exception {
        super(file, true);
    }

    public static void main(String[] args) throws Exception {

        
        String extentions[] = new String[]{"xls", "xlsx" };
        
        
        
        List<File> files = (List) FileUtils.listFiles(new File("D:\\NaverDev\\data"), extentions, false);        
        
        for (File file : files) {
            
            if ( file.getName().startsWith("스토어팜_전체주문배송현황") ){

//                BaseNaverExcelDataHandler h = new NaverOrderShippingData(file.getAbsolutePath());
//                h.processSheet();
                
                m_logger.info("Processing file {}", file.getName());

            }
            
        }               
        
////        BaseNaverExcelDataHandler h = new NaverOrderHandler("D:\\NaverDev\\data\\스토어팜_구매확정내역_20160831_0000.xls");
//        BaseNaverExcelDataHandler h = new NaverOrderHandler("D:\\NaverDev\\data\\스토어팜_구매확정내역_20160925_0742.xls");
//        BaseNaverExcelDataHandler h = new NaverOrderHandler("D:\\NaverDev\\data\\스토어팜_전체주문조회_20160925_0741.xls");
//        h.processSheet();
    }

    @Override
    public NaverModel generateModel() {
        NaverModel model = new NaverOrder(this.titleType, this.titleDateType);
        return model;
    }

    
    
}
