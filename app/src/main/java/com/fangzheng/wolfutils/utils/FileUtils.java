package com.fangzheng.wolfutils.utils;

/**
 * @author fangzheng
 * @date 2016/10/5
 * @email fangzheng428@163.com
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * 文件操作工具类
 */
public class FileUtils {


    /**
     * 文件复制
     * @param oldFile
     * @param newFilePath
     * @return
     */
    public static boolean copy(File oldFile, String newFilePath) {
        if(!oldFile.exists()){
            System.err.println("原文件不存在");
            return false;
        }

        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        byte[] buffer = new byte[1024];
        try{
            inputStream = new FileInputStream(oldFile);
            outputStream = new FileOutputStream(newFilePath);
            int len = -1;
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            try {
                if(inputStream!=null){
                    inputStream.close();
                }
                if (outputStream!=null) {
                    outputStream.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     *  文件复制
     * @param oldFilePath
     * @param newFilePath
     * @return
     */
    public static boolean copy(String oldFilePath, String newFilePath){
        File oldFile = new File(oldFilePath);
        return copy(oldFile,newFilePath);
    }
}