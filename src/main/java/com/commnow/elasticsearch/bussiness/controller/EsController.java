package com.commnow.elasticsearch.bussiness.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.commnow.elasticsearch.util.ESClient;
import com.commnow.elasticsearch.util.PageUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/qiandu")
public class EsController {
	static final Logger logger = LoggerFactory.getLogger(EsController.class);


	public static String path = "F:/index";
	@RequestMapping("/newSearch.do")
	public String createIndex() throws Exception {
		logger.info("进入方法");
		return "jsp/index";
	}
	

	@RequestMapping("/search.do")
	public String serachArticle(Model model,
			@RequestParam(value="keyWords",required = false) String keyWords,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize){
		
		Map<String,Object> map = new HashMap<String, Object>();
		int count = 0;
		try {
			map = ESClient.search(keyWords,"poms","fetches",(pageNum-1)*pageSize, pageSize);
			count = Integer.parseInt(((Long) map.get("count")).toString());
		} catch (Exception e) {
			logger.error("查询索引错误!{}",e);
			e.printStackTrace();
		}
		PageUtil<Map<String, Object>> page = new PageUtil<Map<String, Object>>(String.valueOf(pageNum),String.valueOf(pageSize),count);
		List<Map<String, Object>> articleList = (List<Map<String, Object>>)map.get("dataList");
		page.setList(articleList);
		model.addAttribute("total",count);
		model.addAttribute("pageNum",pageNum);
		model.addAttribute("page",page);
		model.addAttribute("kw",keyWords);
		return "jsp/index";
	}

	
	
	
	

	

	
	/**
	 * 查看文章详细信息
	 * @return
	 */
	@RequestMapping("/detailDocById/{id}.do")
	public String detailArticleById(@PathVariable(value="id") String id, Model modelMap) throws IOException {
		//这里用的查询是直接从hbase中查询一条字符串出来做拆分封装，这里要求同学们用protobuffer
//		Doc Doc = hbaseUtils.get(hbaseUtils.TABLE_NAME, id);
//		modelMap.addAttribute("Doc",Doc);
		return "/detail";
	}
	
	
	
}
