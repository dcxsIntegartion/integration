package team.union.business.article.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import team.union.basic_data.com.rs.BsgridVo;
import team.union.basic_data.com.rs.Result;
import team.union.business.article.dao.BisArticleDao;
import team.union.business.article.model.BisArticle;
import team.union.business.article.service.IBisArticleService;
import team.union.sys_sp.com.cfg.PromptMsgConfig.PROMPT;

@Repository
@Transactional(rollbackFor = Exception.class)
public class BisArticleServiceImpl implements IBisArticleService {

	@Autowired
	private BisArticleDao articleDao;
	
	@Override
	public BsgridVo<HashMap<String, Object>> selMap(Map<String, Object> parm, int curPage, int pageSize) {
		PageHelper.startPage(curPage, pageSize);
		List<HashMap<String, Object>> data =  articleDao.selMap(parm);
		Page<HashMap<String, Object>> pageData = (Page<HashMap<String, Object>>) data;
		BsgridVo<HashMap<String, Object>> bsgridVo = new BsgridVo<HashMap<String, Object>>();
		bsgridVo.setCurPage(curPage);
		bsgridVo.setData(pageData);
		bsgridVo.setSuccess(true);
		bsgridVo.setTotalRows(pageData.getTotal());
		return bsgridVo;
	}

	@Override
	public Result findById(Long id) {
		Result result = new Result();
		try {
			if (id == null) {
				throw new Exception();
			}
			BisArticle article = articleDao.findById(id);
			result.setState(PROMPT.SUCCESS.getNo());
			result.setMsg(PROMPT.SUCCESS.getMsg());
			result.setData(article);
		} catch (Exception e) {
			result.setState(PROMPT.FAIL.getNo());
			result.setMsg("查找文章失败");
		}
		return result;
	}

	@Override
	public Result update(BisArticle article) {
		Result result = new Result();
		try {
			if (article.getArtId() == null) {
				throw new Exception();
			}
			articleDao.update(article);
			result.setState(PROMPT.SUCCESS.getNo());
			result.setMsg(PROMPT.SUCCESS.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
			result.setState(PROMPT.FAIL.getNo());
			result.setMsg("编辑文章失败");
		}
		return result;
	}

	@Override
	public Result insert(BisArticle article) {
		Result result = new Result();
		try {
			articleDao.insert(article);
			result.setState(PROMPT.SUCCESS.getNo());
			result.setMsg(PROMPT.SUCCESS.getMsg());
		} catch (Exception e) {
			result.setState(PROMPT.FAIL.getNo());
			result.setMsg("新增文章失败");
		}
		return result;
	}

	@Override
	public Result deleteById(Long id) {
		Result result = new Result();
		try {
			if (id == null) {
				throw new Exception();
			}
			articleDao.deleteById(id);
			result.setState(PROMPT.SUCCESS.getNo());
			result.setMsg(PROMPT.SUCCESS.getMsg());
		} catch (Exception e) {
			result.setState(PROMPT.FAIL.getNo());
			result.setMsg("删除文章失败");
		}
		return result;
	}

}
