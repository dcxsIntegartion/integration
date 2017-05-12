package team.union.business.commodity.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.union.business.com.interf.IDao;
import team.union.business.commodity.model.BisCommodity;

/**
 * 
* <p>标题: BisCommodityDao.java</p>
* <p>业务描述:商品dao</p>
* <p>类描述:</p>
* @author zh
* @date 2017年5月7日 
* @version V1.0
 */
public interface BisCommodityDao extends IDao<BisCommodity>{
    
	/**
	 * 
	* 方法名:          selMap
	* 方法功能描述:    分页查询
	* @param parm
	* @return  
	* @return:        List<HashMap<String,Object>>
	* @Author:        zh
	* @Create Date:   2017年5月7日
	 */
	public List<HashMap<String, Object>> selMap(Map<String, Object> parm);
	
	/**
	 * 
	* 方法名:          batchOpearate
	* 方法功能描述:    批量操作
	* 				1、批量删除，map需包含key del  
	* 				2、批量上架，map需包含key onSale
	* 				3、批量下架，map需包含key offSale
	* 				1、2、3情况中一次只能出现一个
	* 				所有操作 需包含key data：商品id集合
	* @param map  
	* @return:        void
	* @Author:        zh
	* @Create Date:   2017年5月7日
	 */
	void batchOpearate(Map<String, Object> map);
	
	/**
	 * 
	* 方法名:          updateOne
	* 方法功能描述:    编辑单个商品信息
	* @param commodity  
	* @return:        void
	* @Author:        zh
	* @Create Date:   2017年5月7日
	 */
	void updateOne(BisCommodity commodity);
	
	/**
	 * 新增商品
	 */
	int insert(BisCommodity commodity);
	
	/**
	 * 
	* 方法名:          onSaleAuto
	* 方法功能描述:    自动上线
	* @return:        void
	* @Author:        zh
	* @Create Date:   2017年5月7日
	 */
	void onSaleAuto();
	
	/**
	 * 
	* 方法名:          offSaleAuto
	* 方法功能描述:    自动下线
	* @return:        void
	* @Author:        zh
	* @Create Date:   2017年5月7日
	 */
	void offSaleAuto();

	/**
	 * 活动接口：
	 * 查询未选择的商品
	 * @param param
	 * @return
	 */
	public List<HashMap<String, Object>> getavtivityCommodity(HashMap<String, Object> param);
}