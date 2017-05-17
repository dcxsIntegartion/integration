package team.union.business.article.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
* 标题: BisArticle.java
* 类描述:文章类
* @author zh
* @date 2017年5月17日 
* @version 1.0
 */
public class BisArticle implements Serializable{

	/**  
	* 字段:      字段名称
	* @Fields serialVersionUID : TODO 
	*/
	private static final long serialVersionUID = 1L;

	private Long artId;
	private String artTitle;			//标题
	private String artContent;			//内容
	private Date artCreateTime;			//创建时间
	private String artPlace;			//地点
	private Integer artTop;				//是否置顶  0：否   1是
	private Integer artSort;			//排序，越大越靠前
	private Integer artAdmire;			//点赞数量
	
	
	public Long getArtId() {
		return artId;
	}
	public void setArtId(Long artId) {
		this.artId = artId;
	}
	public String getArtTitle() {
		return artTitle;
	}
	public void setArtTitle(String artTitle) {
		this.artTitle = artTitle;
	}
	public String getArtContent() {
		return artContent;
	}
	public void setArtContent(String artContent) {
		this.artContent = artContent;
	}
	public Date getArtCreateTime() {
		return artCreateTime;
	}
	public void setArtCreateTime(Date artCreateTime) {
		this.artCreateTime = artCreateTime;
	}
	public String getArtPlace() {
		return artPlace;
	}
	public void setArtPlace(String artPlace) {
		this.artPlace = artPlace;
	}
	public Integer getArtTop() {
		return artTop;
	}
	public void setArtTop(Integer artTop) {
		this.artTop = artTop;
	}
	public Integer getArtSort() {
		return artSort;
	}
	public void setArtSort(Integer artSort) {
		this.artSort = artSort;
	}
	public Integer getArtAdmire() {
		return artAdmire;
	}
	public void setArtAdmire(Integer artAdmire) {
		this.artAdmire = artAdmire;
	}
}
