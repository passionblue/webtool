package com.navertool.util;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.poi.POIOLE2TextExtractor;
import org.apache.poi.extractor.ExtractorFactory;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {

    private static Logger m_logger = LoggerFactory.getLogger(FileUtil.class);

    public static List<String> loadKeywordsFromFile(String fileName) throws Exception {

        Scanner sc = new Scanner(FileUtil.class.getClassLoader().getResourceAsStream(fileName));

        List<String> ret = new ArrayList();
        while (sc.hasNextLine()) {

            String line = sc.nextLine();
            if (line == null || line.trim().isEmpty() || line.startsWith("#"))
                continue;

            ret.add(line.trim());
        }

        return ret;
    }

    public static List<String> loadKeywordsFromExcepFile(String fileName) throws Exception  {

        FileInputStream fis = new FileInputStream(fileName);
        POIFSFileSystem fileSystem = new POIFSFileSystem(fis);
        ExcelExtractor exttractor = new ExcelExtractor(fileSystem);  
        
        String extractedString = exttractor.getText();        
        
        Scanner sc = new Scanner(extractedString);

        List<String> ret = new ArrayList();
        while (sc.hasNextLine()) {

            String line = sc.nextLine();
            if (line == null || line.trim().isEmpty() || line.startsWith("#"))
                continue;

            String formattedEntry = formatExcepEntry(line);
            
            if ( formattedEntry != null && formattedEntry.length() > 0 ) 
                ret.add(formattedEntry);
            else 
                m_logger.debug("Dropped the line from excel " + line);
        }

        return ret;
    }
    
    public static Map<String, Integer> loadKeywordsCountrsFromExcelFile(String fileName) throws Exception  {

        FileInputStream fis = new FileInputStream(fileName);
        POIFSFileSystem fileSystem = new POIFSFileSystem(fis);
        ExcelExtractor exttractor = new ExcelExtractor(fileSystem);  
        
        String extractedString = exttractor.getText();        
        
        Scanner sc = new Scanner(extractedString);

        Map<String, Integer>  ret = new HashMap();
        while (sc.hasNextLine()) {

            String line = sc.nextLine();
            if (line == null || line.trim().isEmpty() || line.startsWith("#"))
                continue;

            String formattedEntry = formatExcepEntry(line);
            
            if ( line == null || !line.contains("\t")) continue;
            
            String []pair = line.split("\t"); 
            
            if (pair == null || pair.length != 2 || pair[0] == null || pair[1] == null ) return null;;
            
            
            try {
                int count = Integer.parseInt(pair[1].trim());
                ret.put(pair[0].trim(), count);
            }
            catch (NumberFormatException e) {
                continue;
            }
        }

        return ret;
    }
    
    public static String formatExcepEntry(String entry) {

        if ( entry == null || !entry.contains("\t")) return null;
        
        String []pair = entry.split("\t"); 
        
        if (pair == null || pair.length != 2 || pair[0] == null || pair[1] == null ) return null;;
        
        
        try {
            Integer.parseInt(pair[1].trim());
        }
        catch (NumberFormatException e) {
            return null;
        }
        
        return pair[0].trim();
    }
    
    public static String format(String fileName ) throws Exception {
        
        FileInputStream fis = new FileInputStream(fileName);
        POIFSFileSystem fileSystem = new POIFSFileSystem(fis);
        // Firstly, get an extractor for the Workbook
//        POIOLE2TextExtractor oleTextExtractor =  ExtractorFactory.createExtractor(fileSystem);
        
        
        
        
        
        ExcelExtractor ext = new ExcelExtractor(fileSystem);  
        
        
        
        String text = ext.getText();
        
        return text;
    }
    
    
    public static void main(String[] args) throws Exception {
        System.out.println(loadKeywordsFromExcepFile("D:/LivingLux/KeywordsCapture/keywords-week-20160828.xls"));
    }
    

}
