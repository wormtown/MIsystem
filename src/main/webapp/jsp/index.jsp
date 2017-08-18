<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <base href="<%=basePath%>">

  <title>My JSP 'index.jsp' starting page</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
  <script type="text/javascript">
  $(function(){
	  $("#select").change(function(){
	  		console.log('yes');
	  		var search = $("#select option:selected").val();
	  		$("form").addClass("hidden");
	  		$("#"+search).removeClass("hidden");
	  		
	  	})
  })
  </script>
  <style type="text/css">
  	.hidden{
  		display:none;
  	}
  </style> 	
</head>

<body>
<select id="select">
	<option value="commnow">站内搜索</option>
	<option value="baidu">百度搜索</option>
</select>
<!-- 站内搜索 -->
<form id="commnow" action="qiandu/search.do" method="get" >
  <input type="text" name="keyWords" />
  <input type="submit" value="传声搜索">
  <input type="hidden" value="1" name="pageNum">
</form>

<!-- 百度搜索 -->
<form id="baidu" action="http://www.baidu.com/s" method="get" target="_blank" class="hidden">
	<input type="text" id="search-input" name="wd" />
	<input type="submit" value="百度搜索" />
</form>

<c:if test="${! empty page.list }">
<h3>传声为您找到相关结果约${total}个</h3>
<c:forEach items="${page.list}" var="bean">
  <a href=${bean.url} target="_blank">${bean.title}</a>
  <br/>
  <span>${bean.publishDate}</span>
  <br/>
  <span>${bean.content}</span>
  <br/>
  <br/>
</c:forEach>

<div style="text-align:center; margin-top:100px;">
	<a href="qiandu/search.do?pageNum=1&keyWords=${kw}">首页</a>
	<c:if test="${page.hasPrevious }">
	  <a href="qiandu/search.do?pageNum=${page.previousPageNum }&keyWords=${kw}"> 上一页</a>
	</c:if>
	<c:forEach begin="${page.everyPageStart }" end="${page.everyPageEnd }" var="n">
	  <a href="qiandu/search.do?pageNum=${n }&keyWords=${kw}"> ${n }</a> 
	</c:forEach>
	<c:if test="${page.hasNext }">
	  <a href="qiandu/search.do?pageNum=${page.nextPageNum }&keyWords=${kw}"> 下一页</a>
	</c:if>
	</c:if>
	<a href="qiandu/search.do?pageNum=${page.pageCount}&keyWords=${kw}">尾页</a>
</div>
</body>
</html>
