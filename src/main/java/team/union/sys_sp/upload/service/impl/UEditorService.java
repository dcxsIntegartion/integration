package team.union.sys_sp.upload.service.impl;

import team.union.sys_sp.upload.service.IUEditorService;
import team.union.sys_sp.util.FileUtils;
import java.io.File;  
import java.net.URISyntaxException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(rollbackFor = Exception.class)
public class UEditorService implements IUEditorService{

    /**
     * @param packagePath 文件所在包路径
     * @return 
     */
    public String readUEditorJson(String packagePath){  
        String result=null;  
        String urlpath;
		try {
			urlpath = UEditorService.class.getClassLoader().getResource("").toURI().getPath()+
					  packagePath;
			urlpath.replace("/", File.separator);  
			result = FileUtils.ReadFile(urlpath);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
        return result;  
    }  
}
