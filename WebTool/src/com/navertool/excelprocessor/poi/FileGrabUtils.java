package com.navertool.excelprocessor.poi;
import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileGrabUtils {

    private static Logger m_logger = LoggerFactory.getLogger(FileGrabUtils.class);


    
    public static List<File> getFilesByMatching(String dir, String prefix){
        // IOFileFilter filte = FileFilterUtils.prefixFileFilter("스토어팜_전체주문조회");
        IOFileFilter filte = FileFilterUtils.prefixFileFilter(prefix);
        List<File> list = (List<File>) FileUtils.listFiles(new File(dir), filte, null);
        Collections.sort(list, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return (int) (o1.lastModified() - o2.lastModified());
            }
        });

        for (File file : list) {
            m_logger.info("{}", file);
        }
        
        return list;

    }
    
}
