package com.ljb.common.file;

import com.ljb.common.exception.FileOperateException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

/**
 * @author linjinbo
 * @create 2018-10-24
 */
public class FileScanner {

    private FileFilter fileFileter;

    private static FileScanner FILE_SCANNER;

    public FileScanner() {

    }
    public FileScanner(FileFilter fileFileter) {
        this();
        this.fileFileter = fileFileter;
    }

    public static FileScanner getFileScanner() {
        if (FILE_SCANNER == null) {
            synchronized (FileScanner.class) {
                if (FILE_SCANNER == null) {
                    FILE_SCANNER = new FileScanner(new ScannerDefaultFilter());
                }
            }
        }
        return FILE_SCANNER;
    }

    public void copyDirectory(String originPath, String targetPath) {
        try {
            FileUtils.copyDirectory(new File(originPath), new File(targetPath), fileFileter);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileOperateException(e.getMessage());
        }
    }

    public static class ScannerDefaultFilter implements FileFilter {

        public boolean accept(File pathname) {
            return true;
        }
    }

}
