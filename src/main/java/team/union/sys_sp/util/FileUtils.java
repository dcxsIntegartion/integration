package team.union.sys_sp.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Title: 文件处理工具
 * Description:
 * @author chens
 * @date 2017年4月26日
 * @version 1.0
 */
public class FileUtils {
	/**
	 * 读取文件
	 * @param path
	 * @return
	 */
	 public static String ReadFile(String path) {  
	        File file = new File(path);  
	        BufferedReader reader = null;  
	        String laststr = "";  
	        try {  
	            reader = new BufferedReader(new FileReader(file));  
	            String tempString = null;  
	            while ((tempString = reader.readLine()) != null) {  
	                laststr = laststr + tempString;  
	            }  
	            reader.close();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } finally {  
	            if (reader != null) {  
	                try {  
	                    reader.close();  
	                } catch (IOException e1) {  
	                }  
	            }  
	        }  
	        return laststr;  
	    }  
	
	/**
	 * 格式化文件大小
	 * @param fileSize
	 * @return
	 * @author 岳雷
	 */
	public static String FormetFileSize(long fileSize) {
		String formetFileSize = "";
		DecimalFormat df = new DecimalFormat("#.00");
		if (fileSize < 1024) {
			formetFileSize = df.format((double) fileSize) + "B";
		} else if (fileSize < 1048576) {
			formetFileSize = df.format((double) fileSize / 1024) + "K";
		} else if (fileSize < 1073741824) {
			formetFileSize = df.format((double) fileSize / 1048576) + "M";
		} else {
			formetFileSize = df.format((double) fileSize / 1073741824) + "G";
		}
		if (fileSize == 0) {
			formetFileSize = fileSize + formetFileSize;
		}
		return formetFileSize;
	}
	
	/**
	 * 删除文件,只支持删除文件,不支持删除目录
	 * @param file
	 * @throws Exception
	 */
	public static void deleteFile(String filePath) throws Exception {
		File deleteFile = new File(filePath);
		if (!deleteFile.exists()) {
			throw new Exception("文件" + deleteFile.getName() + "不存在,请确认!");
		}
		if (deleteFile.isFile()) {
			if (deleteFile.canWrite()) {
				deleteFile.delete();
			} else {
				throw new Exception("文件" + deleteFile.getName() + "只读,无法删除,请手动删除!");
			}
		} else {
			throw new Exception("文件" + deleteFile.getName() + "不是一个标准的文件,有可能为目录,请确认!");
		}
	}

	/**
	 * //1.从旧文件拷贝内容到新文件 
	 * //2.删除旧文件
	 * @param oldPath
	 * @param newPath
	 * @throws Exception
	 */
	public static void transferFile(String oldPath, String newPath) throws Exception {
		int byteread = 0;
		File oldFile = new File(oldPath);
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			if (oldFile.exists()) {
				fileInputStream = new FileInputStream(oldFile);
				fileOutputStream = new FileOutputStream(newPath);
				byte[] buffer = new byte[2048];
				while ((byteread = fileInputStream.read(buffer)) != -1) {
					fileOutputStream.write(buffer, 0, byteread);
				}
			} else {
				throw new Exception("需要转移的文件不存在!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			// 如果流不关闭,则删除不了旧文件
			if (fileInputStream != null) {
				fileInputStream.close();
				FileUtils.deleteFile(oldPath);
			}
			if (fileOutputStream != null) {
				fileOutputStream.close();
			}
		}
	}
	
}
