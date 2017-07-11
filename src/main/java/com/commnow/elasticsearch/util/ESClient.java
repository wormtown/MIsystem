package com.commnow.elasticsearch.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.admin.cluster.stats.ClusterStatsResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.commnow.elasticsearch.bussiness.entity.CompanyNews;
import com.commnow.elasticsearch.vo.ElasticsearchVo;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;



public class ESClient {
	private TransportClient client;
	
    @SuppressWarnings({ "resource" })
	public ESClient(String ipAddress) {
		try {
			client = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ipAddress),9300));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
    
    /**
     * 查看链接状态
     * @return
     */
    public ClusterStatsResponse getElasticsearchClusterInfo(){
    	return client.admin().cluster().prepareClusterStats().execute().actionGet();
    }
    
    /**
     * 得到指定索引的source
     * @param queryBuilder
     * @param indexName
     * @param type
     * @return
     */
    public Map<String, Object> getElasticsearchSource(String indexName, String indexType, String indexId){
    	return client.prepareGet(indexName, indexType, indexId).execute().actionGet().getSource();
    }
    /**
     * 测试
     */
    public void testGet() {

      GetResponse response = client.prepareGet("poms", "fetches", "AVuxHsTbi2sBgbH5JINd")
              .setOperationThreaded(false)    // 线程安全
              .get();
      System.out.println(response.getSourceAsString());
  }
    
    //聚合查询
    public Map<String,Long> sumSearch(){
    	Map<String, Long> resultMap = new HashMap<String,Long>(); 
    	SearchRequestBuilder searchRequestBuilder = client.prepareSearch("poms").setTypes("fetches");
    	searchRequestBuilder.setSize(5000);
    	TermsAggregationBuilder field = AggregationBuilders.terms("newsCount").field("title.keyword").size(200);
        searchRequestBuilder.addAggregation(field);
        SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();
        Map<String, Aggregation> aggMap = searchResponse.getAggregations().asMap();
        
        StringTerms gradeTerms = (StringTerms) aggMap.get("newsCount");
        Iterator<Bucket> gradeBucketIt = gradeTerms.getBuckets().iterator();
       
        while(gradeBucketIt.hasNext()){
	        Bucket gradeBucket = gradeBucketIt.next();
	        System.out.println(gradeBucket.getKeyAsString() + "这条新闻有" + gradeBucket.getDocCount() +"个媒体转载。");
	        resultMap.put(gradeBucket.getKeyAsString(),gradeBucket.getDocCount());
        }
    	return resultMap;
    }
    
    //重载的查询方法，加入时间过滤    
    public List<CompanyNews> searchNewsByCompany(QueryBuilder queryBuilder, String indexName, String type, String from, String to){
    	
    	List<CompanyNews> list = new ArrayList<CompanyNews>();
    	try {	
    			SearchRequestBuilder requestBuilder = client.prepareSearch(indexName);
    			requestBuilder.setTypes(type);
    			requestBuilder.setQuery(queryBuilder);
    			//设置查询结果数量
    			requestBuilder.setSize(5000);
    			//设置按时间过滤
    			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    			Date fromDate = sdf.parse(from);
    			Date toDate = sdf.parse(to);
    			requestBuilder.setPostFilter(QueryBuilders.rangeQuery("publish_date").from(fromDate.getTime()).to(toDate.getTime()));
    			//设置精确查询
    			requestBuilder.setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
    			//设置按匹配度排序
    			requestBuilder.setExplain(true);
    			//设置高亮显示(写网页的时候用，写表格用不着)
//    			HighlightBuilder highlightBuilder = new HighlightBuilder().field("*").requireFieldMatch(false);
//    			highlightBuilder.preTags("<span style=\"color:red\">");
//    			highlightBuilder.postTags("</span>");
//    			requestBuilder.highlighter(highlightBuilder);
    	        //查询
		    	SearchResponse searchResponse = requestBuilder.execute().actionGet();
		    	SearchHits hits = searchResponse.getHits();
		    	System.out.println(hits.totalHits());
		    	SearchHit[] searchHits = hits.hits();
		    	System.out.println(searchHits.length);
		    	
		    	if(searchHits.length>0){
		    		for(SearchHit hit : searchHits){
		    			String title = (String)hit.getSource().get("title");
		    			CompanyNews news = new CompanyNews();
		    			news.setSourceName((String)hit.getSource().get("source_name"));
		    			news.setTitle(title);
		    			news.setAuthor((String)hit.getSource().get("author"));
		    			news.setContent((String)hit.getSource().get("content"));
		    			news.setKeywords((String)hit.getSource().get("keywords"));
						news.setPublishDate(sdf.parse((String)hit.getSource().get("publish_date")));
			    		news.setUrl((String)hit.getSource().get("url"));
			    		news.setCrawlDate(sdf.parse((String)hit.getSource().get("crawltime")));
		    			list.add(news);
		    		}
		    	}
	    	} catch (Exception e) {
				e.printStackTrace();
			}
    	client.close();
    	return list;
    }
    
    //无时间过滤的查询方法
    public List<CompanyNews> searchNewsByCompany(QueryBuilder queryBuilder, String indexName, String type){
    	
    	List<CompanyNews> list = new ArrayList<CompanyNews>();
    	try {	
    			SearchRequestBuilder requestBuilder = client.prepareSearch(indexName);
    			requestBuilder.setTypes(type);
    			requestBuilder.setQuery(queryBuilder);
    			//设置查询结果数量
    			requestBuilder.setSize(5000);
    			//设置精确查询
    			requestBuilder.setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
    			//设置按匹配度排序
    			requestBuilder.setExplain(true);
    			//设置高亮显示(写网页的时候用，写表格用不着)
//    			HighlightBuilder highlightBuilder = new HighlightBuilder().field("*").requireFieldMatch(false);
//    			highlightBuilder.preTags("<span style=\"color:red\">");
//    			highlightBuilder.postTags("</span>");
//    			requestBuilder.highlighter(highlightBuilder);
    	        //查询
		    	SearchResponse searchResponse = requestBuilder.execute().actionGet();
		    	SearchHits hits = searchResponse.getHits();
		    	System.out.println(hits.totalHits());
		    	SearchHit[] searchHits = hits.hits();
		    	System.out.println(searchHits.length);
		    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    	if(searchHits.length>0){
		    		for(SearchHit hit : searchHits){
		    			//设置高亮域
//		    			Map<String, HighlightField> highlightFields = hit.getHighlightFields();
//		    			HighlightField titleField = highlightFields.get("title");
//		    			//取得定义的高亮标签
//		    			Text[] titleText = titleField.fragments();
		    			String title = (String)hit.getSource().get("title");
//		    			//给title加高亮标签
//		    			 for(Text text : titleText){    
//		                     title += text;  
//		    			 }
		    			CompanyNews news = new CompanyNews();
		    			news.setSourceName((String)hit.getSource().get("source_name"));
		    			news.setTitle(title);
		    			news.setAuthor((String)hit.getSource().get("author"));
		    			news.setContent((String)hit.getSource().get("content"));
		    			news.setKeywords((String)hit.getSource().get("keywords"));
						news.setPublishDate(sdf.parse((String)hit.getSource().get("publish_date")));
			    		news.setUrl((String)hit.getSource().get("url"));
			    		news.setCrawlDate(sdf.parse((String)hit.getSource().get("crawltime")));
		    			list.add(news);
		    		}
		    	}
	    	} catch (Exception e) {
				e.printStackTrace();
			}
    	client.close();
    	return list;
    }
    
    /**
     * 
     * @param search
     * @param scope 查找范围("content"或者"title")
     * @return
     */
    
    public static List<CompanyNews> searchNewsList(ElasticsearchVo search, String scope){
    	List<CompanyNews> list = new ArrayList<CompanyNews>();
		ESClient esClient = new ESClient("103.36.136.225");
		//"content"字段中关键字查询
		QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(search.getCompany(),scope);
		if(search.getFromDate() == "" || search.getToDate() == ""){
			 list = esClient.searchNewsByCompany(queryBuilder, "poms", "fetches");
		}else{
			 list = esClient.searchNewsByCompany(queryBuilder, "poms", "fetches", search.getFromDate(), search.getToDate());
		}
		return list;
    }
    
    
    
    /**
     * 从新闻内容中查找相关公司新闻并直接输出表格
     * @param search
     */
    public static void searchFromContentAndWriteExcel(ElasticsearchVo search){
    	try {
    		List<CompanyNews> list = new ArrayList<CompanyNews>();
			ESClient esClient = new ESClient("103.36.136.225");
			//"content"字段中关键字查询
			QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(search.getCompany(),"content");
			if(search.getFromDate() == null || search.getToDate() == null){
				 list = esClient.searchNewsByCompany(queryBuilder, "poms", "fetches");
			}else{
				 list = esClient.searchNewsByCompany(queryBuilder, "poms", "fetches", search.getFromDate(), search.getToDate());
			}
			String diretory = "d:/news/new";
			File dir = new File(diretory);
    		File fileWrite = null;
    		if (dir.exists()) {// 判断目录是否存在
    			fileWrite = new File("d:/news/"+search.getCompany()+"news.xls");
    			
    		}
    		if (!diretory.endsWith(File.separator)) {// 结尾是否以"/"结束
    			diretory = diretory + File.separator;
    			fileWrite = new File(diretory+search.getCompany()+"news.xls");
    		}
    		if (dir.mkdirs()) {// 创建目标目录
    			fileWrite = new File(diretory+search.getCompany()+"news.xls");
    		} else {
    			System.out.println("创建目录失败！");
    			
    		}
			//创建excel
			fileWrite.createNewFile();
			OutputStream os = new FileOutputStream(fileWrite);
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet(search.getCompany(),0);
			int index = 1;
			ws.addCell(new Label(0,0,"新闻引自"));
			ws.addCell(new Label(1,0,"新闻标题"));
			ws.addCell(new Label(2,0,"作者/转载自"));
			ws.addCell(new Label(3,0,"内容"));
			ws.addCell(new Label(4,0,"关键字"));
			ws.addCell(new Label(5,0,"刊载日期"));
			ws.addCell(new Label(6,0,"原文链接"));
			ws.addCell(new Label(7,0,"创建时间"));
		for(CompanyNews news : list){
			String sourceName = news.getSourceName();
			String title = news.getTitle();
			//title.replaceAll("巴斯夫","巴斯夫");
			String author = news.getAuthor();
			String content = news.getContent();
			String keywords = news.getKeywords();
			
			
			String url = news.getUrl();
			
			SimpleDateFormat formatTo = new SimpleDateFormat("yyyy-MM-dd");
			String publishDate = formatTo.format(news.getPublishDate());
			String crawlDate = formatTo.format(news.getCrawlDate());
			System.out.println(crawlDate);
			if(-1 != content.indexOf(search.getCompany())){
				ws.addCell(new Label(0,index,ExcelUtil.trimFormat(sourceName)));
				ws.addCell(new Label(1,index,ExcelUtil.trimFormat(title)));
				ws.addCell(new Label(2,index,ExcelUtil.trimFormat(author)));
				ws.addCell(new Label(3,index,ExcelUtil.trimFormat(content)));
				ws.addCell(new Label(4,index,ExcelUtil.trimFormat(keywords)));
				ws.addCell(new Label(5,index,ExcelUtil.trimFormat(publishDate)));
				ws.addCell(new Label(6,index,ExcelUtil.trimFormat(url)));
				ws.addCell(new Label(7,index,ExcelUtil.trimFormat(crawlDate.toString())));
				index++;
			}
		}
		wwb.write();
		wwb.close();
	} catch (IOException e1 ) {
		e1.printStackTrace();
	} catch(WriteException e2){
		e2.printStackTrace();
	}
    }
    
    public static void createNewsExcel(List<CompanyNews> list, String directory, String company){
    	WritableWorkbook wwb = null;
    	WritableSheet ws = null;
    	File fileWrite = null;
    	OutputStream os = null;
    	try{	
	    		File dir = new File(directory);
	    		if (dir.exists()) {// 判断目录是否存在
	    			fileWrite = new File(directory+company+"news.xls");
	    			
	    		}
	    		if (!directory.endsWith(File.separator)) {// 结尾是否以"/"结束
	    			directory = directory + File.separator;
	    			fileWrite = new File(directory+company+"news.xls");
	    		}
	    		if (dir.mkdirs()) {// 创建目标目录
	    			fileWrite = new File(directory+company+"news.xls");
	    		} else {
	    			System.out.println("创建目录失败！");
	    			
	    		}
    		
	    		
	    		fileWrite.createNewFile();
	    		os = new FileOutputStream(fileWrite);
	    		wwb = Workbook.createWorkbook(os);
	    		ws = wwb.createSheet(company,0);
	    		int index = 1;
	    		ws.addCell(new Label(0,0,"新闻引自"));
	    		ws.addCell(new Label(1,0,"新闻标题"));
	    		ws.addCell(new Label(2,0,"作者/转载自"));
	    		ws.addCell(new Label(3,0,"内容"));
	    		ws.addCell(new Label(4,0,"关键字"));
	    		ws.addCell(new Label(5,0,"刊载日期"));
	    		ws.addCell(new Label(6,0,"原文链接"));
	    		ws.addCell(new Label(7,0,"创建时间"));
	    	for(CompanyNews news : list){
	    		String sourceName = news.getSourceName();
	    		String title = news.getTitle();
	    		//title.replaceAll("巴斯夫","巴斯夫");
	    		String author = news.getAuthor();
	    		String content = news.getContent();
	    		String keywords = news.getKeywords();
	    		
	    		
	    		String url = news.getUrl();
	    		
	    		SimpleDateFormat formatTo = new SimpleDateFormat("yyyy-MM-dd");
	    		String publishDate = formatTo.format(news.getPublishDate());
	    		String crawlDate = formatTo.format(news.getCrawlDate());
	    		System.out.println(crawlDate);
	    		if(-1 != content.indexOf(company) || -1 != news.getContent().indexOf(company)){
	    			ws.addCell(new Label(0,index,ExcelUtil.trimFormat(sourceName)));
	    			ws.addCell(new Label(1,index,ExcelUtil.trimFormat(title)));
	    			ws.addCell(new Label(2,index,ExcelUtil.trimFormat(author)));
	    			ws.addCell(new Label(3,index,ExcelUtil.trimFormat(content)));
	    			ws.addCell(new Label(4,index,ExcelUtil.trimFormat(keywords)));
	    			ws.addCell(new Label(5,index,ExcelUtil.trimFormat(publishDate)));
	    			ws.addCell(new Label(6,index,ExcelUtil.trimFormat(url)));
	    			ws.addCell(new Label(7,index,ExcelUtil.trimFormat(crawlDate.toString())));
	    			index++;
	    		}
	    	}
	    	wwb.write();
    	}catch(Exception e1){
    		e1.printStackTrace();
    	}finally{
				try {
					wwb.close();
					os.close();
				} catch (WriteException e2) {
					e2.printStackTrace();
				} catch (IOException e3) {
					e3.printStackTrace();
				}
    	}
    }
   
	public static void createNewsPage(List<CompanyNews> list, String directory, String company) {
    	File fileWrite = null;
    	OutputStream os = null;
    	PrintStream ps = null;
    	Date date = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String fileName = sdf.format(date);
    	try {
			fileName = DateUtil.dateToStamp(fileName);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	try{	
	    		File dir = new File(directory);
	    		if (dir.exists()) {// 判断目录是否存在
	    			fileWrite = new File(directory+fileName+"news.html");
	    			
	    		}
	    		if (!directory.endsWith(File.separator)) {// 结尾是否以"/"结束
	    			directory = directory + File.separator;
	    			fileWrite = new File(directory+fileName+"news.html");
	    		}
	    		if (dir.mkdirs()) {// 创建目标目录
	    			fileWrite = new File(directory+fileName+"news.html");
	    		}else {
	    			System.out.println("创建目录失败！");
	    			
	    		}
	    		fileWrite.createNewFile();
	    		os = new FileOutputStream(fileWrite);
	    		ps = new PrintStream(os);
	    		StringBuilder sb = new StringBuilder();
	    		sb.append("<!DOCTYPE HTML>");
	    		sb.append("<html>");
	    		sb.append("<head>");
	    		sb.append("<title>Tables</title>");
	    		sb.append("</head> ");
	    		sb.append("<body>");
	    		sb.append("<div>");
	    		sb.append("<h4>"+company+":</h4>");
	    		sb.append("<table style='border-bottom:1px solid #333333'>");
	    		sb.append("<thead>");
	    		sb.append("<tr>");
	    		sb.append("<th>#</th>");
	    		sb.append("<th>援引</th>");
	    		sb.append("<th>标题</th>");
	    		sb.append("<th>原文链接</th>");
	    		sb.append("</tr>");
	    		sb.append("</thead>");
	    		sb.append("<tbody>");
	    		int count = 1;
	    		for(CompanyNews news : list){
	    			if(-1 != news.getContent().indexOf(company) || -1 != news.getTitle().indexOf(company)){
	    				sb.append("<tr>");
	    				sb.append("<td>"+ count++ +"</td>");
		    			sb.append("<td>"+news.getSourceName()+"</td>");
		    			sb.append("<td>"+news.getTitle()+"</td>");
		    			sb.append("<td><a href='"+news.getUrl()+"'target='_blank'>点击查看原文</a></td>");
		    			sb.append("</tr>");
	    			}
	    		}
	    		sb.append("</tbody>");
	    		sb.append("</table>");
	    		sb.append("</div>");
	    		sb.append("</body>");
	    		sb.append("</html>");
	    		ps.println(sb.toString());
    	}catch(Exception e1){
    			e1.printStackTrace();
    	}finally{
    		try {
				os.close();
				ps.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
    	}
		
	}
    
    
	public static void main(String[] args){
//		ElasticsearchVo search = new ElasticsearchVo();
//		search.setCompany("陶氏");
//		search.setFromDate("2017-04-01");
//		search.setToDate("2017-07-11");
//		search.setCompany("霍尼韦尔");
//		//search.setFromDate("2017-04-01");
//		//search.setToDate("2017-05-01");
//		ESClient.searchFromContentAndWriteExcel(search);
		ESClient esClient = new ESClient("103.36.136.225");
		esClient.testGet();
		esClient.sumSearch();
//		
//		List<CompanyNews> list = esClient.searchNewsList(search, "title");
//		System.out.println(list);
//		
	}

	

	
}
