package team.union.sys_sp.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.beanutils.BeanUtils;

/**
 * 
 * @author		yinyao
 * @Description	模块描述
 * @version		1.00 
 * @see			
 * @Date		2015-12-28 下午8:54:29
 */
public class CSVUtils {
	/**
	   * 生成为CVS文件 
	   * @param exportData
	   *       源数据List
	   * @param map
	   *       csv文件的列表头map
	   * @param outPutPath
	   *       文件路径
	   * @param fileName
	   *       文件名称
	   * @return
	   */
	  @SuppressWarnings("rawtypes")
	  public static File createCSVFile(List exportData, LinkedHashMap map, String outPutPath,
	                   String fileName) {
	    File csvFile = null;
	    BufferedWriter csvFileOutputStream = null;
	    try {
	      File file = new File(outPutPath);
	      if (!file.exists()) {
	        file.mkdir();
	      }
	      //定义文件名格式并创建
	      csvFile = File.createTempFile(fileName, ".csv", new File(outPutPath));
	      System.out.println("csvFile：" + csvFile);
	      // UTF-8使正确读取分隔符"," 
	      csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
	        csvFile), "GBK"), 1024);
	      System.out.println("csvFileOutputStream：" + csvFileOutputStream);
	      // 写入文件头部 
	      for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator.hasNext();) {
	        java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator.next();
	        csvFileOutputStream
	          .write("" + (String) propertyEntry.getValue() != null ? (String) propertyEntry
	            .getValue() : "" + "");
	        if (propertyIterator.hasNext()) {
	          csvFileOutputStream.write(",");
	        }
	      }
	      csvFileOutputStream.newLine();
	      // 写入文件内容 
	      for (Iterator iterator = exportData.iterator(); iterator.hasNext();) {
	        Object row = (Object) iterator.next();
	        for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator
	          .hasNext();) {
	          java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator
	            .next();
	          csvFileOutputStream.write((String) BeanUtils.getProperty(row,
	            (String) propertyEntry.getKey()));
	          if (propertyIterator.hasNext()) {
	            csvFileOutputStream.write(",");
	          }
	        }
	        if (iterator.hasNext()) {
	          csvFileOutputStream.newLine();
	        }
	      }
	      csvFileOutputStream.flush();
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      try {
	        csvFileOutputStream.close();
	      } catch (IOException e) {
	        e.printStackTrace();
	      }
	    }
	    return csvFile;
	  }
	 
	  /**
	   * 下载文件
	   * @param response
	   * @param csvFilePath
	   *       文件路径
	   * @param fileName
	   *       文件名称
	   * @throws IOException
	   */
	  public static void exportFile(HttpServletResponse response, String csvFilePath, String fileName, HttpServletRequest request)
	                                                  throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/octet-stream");
	    response.setHeader("Content-Disposition",
	      "attachment; filename=" + fileName);
	    File file = new File(csvFilePath + fileName);
	    BufferedInputStream bis=null;
		BufferedOutputStream out=null;
		try {
	        bis = new BufferedInputStream(new FileInputStream(csvFilePath + fileName));
	        out = new BufferedOutputStream(response.getOutputStream());
	        byte[] buff = new byte[2048];
	        while (true) {
	          int bytesRead;
	          if (-1 == (bytesRead = bis.read(buff, 0, buff.length))){
	              break;
	          }
	          out.write(buff, 0, bytesRead);
	        }
	        file.deleteOnExit();
	    }
	    catch (IOException e) {
	        throw e;
	    }
	    finally{
	        try {
	            if(bis != null){
	                bis.close();
	            }
	            if(out != null){
	                out.flush();
	                out.close();
	            }
	        }
	        catch (IOException e) {
	            throw e;
	        }
	    }
	  }
	 
	  /**
	   * 删除该目录filePath下的所有文件
	   * @param filePath
	   *      文件目录路径
	   */
	  public static void deleteFiles(String filePath) {
	    File file = new File(filePath);
	    if (file.exists()) {
	      File[] files = file.listFiles();
	      for (int i = 0; i < files.length; i++) {
	        if (files[i].isFile()) {
	          files[i].delete();
	        }
	      }
	    }
	  }
	 
	  /**
	   * 删除单个文件
	   * @param filePath
	   *     文件目录路径
	   * @param fileName
	   *     文件名称
	   */
	  public static void deleteFile(String filePath, String fileName) {
	    File file = new File(filePath);
	    if (file.exists()) {
	      File[] files = file.listFiles();
	      for (int i = 0; i < files.length; i++) {
	        if (files[i].isFile()) {
	          if (files[i].getName().equals(fileName)) {
	            files[i].delete();
	            return;
	          }
	        }
	      }
	    }
	  }
	  /**
	   * 测试数据
	   * @param args
	   */
	  @SuppressWarnings({ "rawtypes", "unchecked" })
	  public static void main(String[] args) {
	    List exportData = new ArrayList<Map>();
	    Map row1 = new LinkedHashMap<String, String>();
	    row1.put("1", "11");
	    row1.put("2", "12");
	    row1.put("3", "13");
	    row1.put("4", "14");
	    exportData.add(row1);
	    row1 = new LinkedHashMap<String, String>();
	    row1.put("1", "21");
	    row1.put("2", "22");
	    row1.put("3", "23");
	    row1.put("4", "24");
	    exportData.add(row1);
	    LinkedHashMap map = new LinkedHashMap();
	    map.put("1", "第一列");
	    map.put("2", "第二列");
	    map.put("3", "第三列");
	    map.put("4", "第四列");
	 
	    String path = "d:/export/";
	    String fileName = "文件导出";
	    File file = CSVUtils.createCSVFile(exportData, map, path, fileName);
	    String fileName2 = file.getName();
	    System.out.println("文件名称：" + fileName2);
	  }
}
