package team.union.business.commodity.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
* <p>标题: BisCommodityType.java</p>
* <p>业务描述:商品分类</p>
* <p>类描述:</p>
* @author zh
* @date 2017年5月10日 
* @version V1.0
 */
public class BisCommodityType implements Serializable{

	/**  
	* 字段:      字段名称
	* @Fields serialVersionUID : TODO 
	*/
	private static final long serialVersionUID = 1L;

	private BigDecimal typeId;
	private String typeName;			//分类名称
	private Integer typeState;			//分类状态 0：启用 1：禁用
	private String typeDes;				//描述
	private BigDecimal parTypeId;		//父类型 ID
	private Date typeCreateTime;		//创建时间
	private Integer typeLevel;			//等级
	
	private List<BisCommodityType> children;
	
	public BigDecimal getTypeId() {
		return typeId;
	}
	public void setTypeId(BigDecimal typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getTypeState() {
		return typeState;
	}
	public void setTypeState(Integer typeState) {
		this.typeState = typeState;
	}
	public String getTypeDes() {
		return typeDes;
	}
	public void setTypeDes(String typeDes) {
		this.typeDes = typeDes;
	}
	public BigDecimal getParTypeId() {
		return parTypeId;
	}
	public void setParTypeId(BigDecimal parTypeId) {
		this.parTypeId = parTypeId;
	}
	public Date getTypeCreateTime() {
		return typeCreateTime;
	}
	public void setTypeCreateTime(Date typeCreateTime) {
		this.typeCreateTime = typeCreateTime;
	}
	public Integer getTypeLevel() {
		return typeLevel;
	}
	public void setTypeLevel(Integer typeLevel) {
		this.typeLevel = typeLevel;
	}
	public List<BisCommodityType> getChildren() {
		return children;
	}
	public void setChildren(List<BisCommodityType> children) {
		this.children = children;
	}
}
