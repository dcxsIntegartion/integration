package team.union.nonbusiness.upload.service.impl;

import team.union.nonbusiness.com.cfg.BaseConfig;
import team.union.nonbusiness.upload.service.IUEditorService;
import java.io.BufferedReader;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.IOException;  
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
public class UEditorService implements IUEditorService{

    /* @param name   文件名  不包含路径 
     */  
    public static  String getSrcPath(String name)  
    {  
        String result=null;  
        URL urlpath = UEditorService.class.getClassLoader().getResource(name);   
        String path=urlpath.toString();  
        //remove the head "file:",if it exists  
        if(path.startsWith("file"))  
        {  
            path=path.substring(5);  
        }  
        path.replace("/", File.separator);  
        result=path;  
        return result;  
    }  
	public static void main(String[] args) {
//		UEditorService  ue= new UEditorService();
		try {
			System.out.println( UEditorService.class.getClassLoader().getResource("").toURI().getPath());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
