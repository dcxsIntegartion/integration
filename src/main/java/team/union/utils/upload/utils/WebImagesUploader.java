package team.union.utils.upload.utils;
import java.io.File;
import javax.servlet.http.HttpServletRequest;


/**
 * UEditor文件上传辅助类
 * 
 */
public class WebImagesUploader extends Uploader{

	
	public WebImagesUploader(HttpServletRequest request) {
		super(request);
	}

	@Override
	public void upload() throws Exception {
		super.upload();
		this.url = (this.getVituralPath()+this.relativePath+File.separator+this.fileName).replace(File.separator, "/");
	}
	
	

}
