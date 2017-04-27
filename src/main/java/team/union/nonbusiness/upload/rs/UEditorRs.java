package team.union.nonbusiness.upload.rs;

import java.util.List;

/**
 * Description: 富文本编辑器返回对象
 * @author chens
 * @date 2017年4月26日
 * @version 1.0
 */
public class UEditorRs {

	private String state;
	
	private String url;
	
	private List<String> list;
	
	private String title;
	
	private String original;
	
	public static  UEditorRs isSuccess(String url,String name,List<String> list){
		UEditorRs ue = new UEditorRs();
		ue.setOriginal(name);
		ue.setTitle(name);
		ue.setList(list);
		ue.setState("SUCCESS");
		ue.setUrl(url);
		return ue;
	}
	public static UEditorRs isSuccess(String url,String name){
		UEditorRs ue = new UEditorRs();
		ue.setOriginal(name);
		ue.setTitle(name);
		ue.setState("SUCCESS");
		ue.setUrl(url);
		return ue;
	}
	public UEditorRs isError(String url,String name){
		UEditorRs ue = new UEditorRs();
		ue.setState("ERROR");
		return ue;
	}
	
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}
	
	
}
