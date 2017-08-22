<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
<title>传声网--舆情监测</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Novus Admin Panel Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
SmartPhone Compatible web template, free WebDesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript">
	var path = "http://mi.smartpr.net.cn/";
	addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<!-- Bootstrap Core CSS -->
<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- Custom CSS -->
<link href="${pageContext.request.contextPath}/css/style.css" rel='stylesheet' type='text/css' />
<!-- font CSS -->
<!-- font-awesome icons -->
<link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet"> 
<!-- //font-awesome icons -->
 <!-- js-->
<script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/modernizr.custom.js"></script>
<!--webfonts-->
<link href='https://fonts.googleapis.com/css?family=Roboto+Condensed:400,300,300italic,400italic,700,700italic' rel='stylesheet' type='text/css'>
<!--//webfonts--> 
<!--animate-->
<link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet" type="text/css" media="all">
<script src="${pageContext.request.contextPath}/js/wow.min.js"></script>
	<script>
		 new WOW().init();
	</script>
<!--//end-animate-->
<!-- Metis Menu -->
<script src="${pageContext.request.contextPath}/js/metisMenu.min.js"></script>
<script src="${pageContext.request.contextPath}/js/custom.js"></script>
<script src="${pageContext.request.contextPath}/script/search.js?version=201707110909"></script>
<link href="${pageContext.request.contextPath}/css/custom.css" rel="stylesheet">
<script type="text/javascript">
	$(function(){
		$("#createPage").click(createPage);
		$("#createExcel").click(createExcel);
	})
</script>
<!--//Metis Menu -->
</head> 
<body class="cbp-spmenu-push">
	<div class="main-content">
		<!--left-fixed -navigation-->
		<div class="sidebar" role="navigation">
            <div class="navbar-collapse">
				<nav class="cbp-spmenu cbp-spmenu-vertical cbp-spmenu-left" id="cbp-spmenu-s1">
					<ul class="nav" id="side-menu">
						<li>
							<a href="dailynews.html"><i class="fa fa-home nav_icon"></i>首页</a>
						</li>
						<li>
							<a href="#"><i class="fa fa-home nav_icon"></i>新闻检索<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level collapse">
								<li>
									<a href="../index.html">客户服务</a>
								</li>
								<li>
									<a href="${pageContext.request.contextPath}/qiandu/newSearch.do">传声千度</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="../hotnews.html"><i class="fa fa-home nav_icon"></i>热点新闻</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/brief/index.do"><i class="fa fa-home nav_icon"></i>简报系统</a>
						</li>
						<li>
							<a href="http://test.smartpr.net.cn"><i class="fa fa-book nav_icon"></i>我的文档</a>
						</li>
						<li>
							<a href="#"><i class="fa fa-cogs nav_icon"></i>系统维护  <span class="fa arrow"></span></a>
							<ul class="nav nav-second-level collapse">
								<li>
									<a href="http://103.36.136.225:3000">数据源维护</a>
								</li>
								<li>
									<a href="http://103.36.136.225:5601">Kibana</a>
								</li>
								<li>
									<a href="http://103.36.136.225:9100">elasticsearch-head</a>
								</li>
							</ul>
							<!-- /nav-second-level -->
						</li>
					</ul>
					<div class="clearfix"> </div>
					<!-- //sidebar-collapse -->
				</nav>
			</div>
		</div>
		<!--left-fixed -navigation-->
		<!-- header-starts -->
		<div class="sticky-header header-section ">
			<div class="header-left">
				<!--toggle button start-->
				<button id="showLeftPush"><i class="fa fa-bars"></i></button>
				<!--toggle button end-->
				<!--logo -->
				<div class="logo">
					<a href="index.html">
						<h1>COMMNOW</h1>
						<span>舆情监测系统</span>
					</a>
				</div>
				<!--//logo-->
				<!--search-box-->
				<div class="search-box">
					<form class="input">
						<input class="sb-search-input input__field--madoka" placeholder="Search..." type="search" id="input-31" />
						<label class="input__label" for="input-31">
							<svg class="graphic" width="100%" height="100%" viewBox="0 0 404 77" preserveAspectRatio="none">
								<path d="m0,0l404,0l0,77l-404,0l0,-77z"/>
							</svg>
						</label>
					</form>
				</div><!--//end-search-box-->
				<div class="clearfix"> </div>
			</div>
			<div class="header-right">
				<div class="profile_details_left"><!--notifications of menu start -->
					<ul class="nofitications-dropdown">
						<li class="dropdown head-dpdn">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-envelope"></i><span class="badge">3</span></a>
							<ul class="dropdown-menu">
								<li>
									<div class="notification_header">
										<h3>You have 3 new messages</h3>
									</div>
								</li>
								<li><a href="#">
								   <div class="user_img"><img src="${pageContext.request.contextPath}/images/1.png" alt=""></div>
								   <div class="notification_desc">
									<p>Lorem ipsum dolor amet</p>
									<p><span>1 hour ago</span></p>
									</div>
								   <div class="clearfix"></div>	
								</a></li>
								<li class="odd"><a href="#">
									<div class="user_img"><img src="${pageContext.request.contextPath}/images/2.png" alt=""></div>
								   <div class="notification_desc">
									<p>Lorem ipsum dolor amet </p>
									<p><span>1 hour ago</span></p>
									</div>
								  <div class="clearfix"></div>	
								</a></li>
								<li><a href="#">
								   <div class="user_img"><img src="${pageContext.request.contextPath}/images/3.png" alt=""></div>
								   <div class="notification_desc">
									<p>Lorem ipsum dolor amet </p>
									<p><span>1 hour ago</span></p>
									</div>
								   <div class="clearfix"></div>	
								</a></li>
								<li>
									<div class="notification_bottom">
										<a href="#">See all messages</a>
									</div> 
								</li>
							</ul>
						</li>
						<li class="dropdown head-dpdn">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-bell"></i><span class="badge blue">3</span></a>
							<ul class="dropdown-menu">
								<li>
									<div class="notification_header">
										<h3>You have 3 new notification</h3>
									</div>
								</li>
								<li><a href="#">
									<div class="user_img"><img src="${pageContext.request.contextPath}/images/2.png" alt=""></div>
								   <div class="notification_desc">
									<p>Lorem ipsum dolor amet</p>
									<p><span>1 hour ago</span></p>
									</div>
								  <div class="clearfix"></div>	
								 </a></li>
								 <li class="odd"><a href="#">
									<div class="user_img"><img src="${pageContext.request.contextPath}/images/1.png" alt=""></div>
								   <div class="notification_desc">
									<p>Lorem ipsum dolor amet </p>
									<p><span>1 hour ago</span></p>
									</div>
								   <div class="clearfix"></div>	
								 </a></li>
								 <li><a href="#">
									<div class="user_img"><img src="${pageContext.request.contextPath}/images/3.png" alt=""></div>
								   <div class="notification_desc">
									<p>Lorem ipsum dolor amet </p>
									<p><span>1 hour ago</span></p>
									</div>
								   <div class="clearfix"></div>	
								 </a></li>
								 <li>
									<div class="notification_bottom">
										<a href="#">See all notifications</a>
									</div> 
								</li>
							</ul>
						</li>	
						<li class="dropdown head-dpdn">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-tasks"></i><span class="badge blue1">15</span></a>
							<ul class="dropdown-menu">
								<li>
									<div class="notification_header">
										<h3>You have 8 pending task</h3>
									</div>
								</li>
								<li><a href="#">
									<div class="task-info">
										<span class="task-desc">Database update</span><span class="percentage">40%</span>
										<div class="clearfix"></div>	
									</div>
									<div class="progress progress-striped active">
										<div class="bar yellow" style="width:40%;"></div>
									</div>
								</a></li>
								<li><a href="#">
									<div class="task-info">
										<span class="task-desc">Dashboard done</span><span class="percentage">90%</span>
									   <div class="clearfix"></div>	
									</div>
									<div class="progress progress-striped active">
										 <div class="bar green" style="width:90%;"></div>
									</div>
								</a></li>
								<li><a href="#">
									<div class="task-info">
										<span class="task-desc">Mobile App</span><span class="percentage">33%</span>
										<div class="clearfix"></div>	
									</div>
								   <div class="progress progress-striped active">
										 <div class="bar red" style="width: 33%;"></div>
									</div>
								</a></li>
								<li><a href="#">
									<div class="task-info">
										<span class="task-desc">Issues fixed</span><span class="percentage">80%</span>
									   <div class="clearfix"></div>	
									</div>
									<div class="progress progress-striped active">
										 <div class="bar  blue" style="width: 80%;"></div>
									</div>
								</a></li>
								<li>
									<div class="notification_bottom">
										<a href="#">See all pending tasks</a>
									</div> 
								</li>
							</ul>
						</li>	
					</ul>
					<div class="clearfix"> </div>
				</div>
				<!--notification menu end -->
				<div class="profile_details">		
					<ul>
						<li class="dropdown profile_details_drop">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
								<div class="profile_img">	
									<span class="prfil-img"><img src="${pageContext.request.contextPath}/images/a.png" alt=""> </span> 
									<div class="user-name">
										<p>Wikolia</p>
										<span>Administrator</span>
									</div>
									<i class="fa fa-angle-down lnr"></i>
									<i class="fa fa-angle-up lnr"></i>
									<div class="clearfix"></div>	
								</div>	
							</a>
							<ul class="dropdown-menu drp-mnu">
								<li> <a href="#"><i class="fa fa-cog"></i> Settings</a> </li> 
								<li> <a href="#"><i class="fa fa-user"></i> Profile</a> </li> 
								<li> <a href="#"><i class="fa fa-sign-out"></i> Logout</a> </li>
							</ul>
						</li>
					</ul>
				</div>
				<div class="clearfix"> </div>	
			</div>
			<div class="clearfix"> </div>	
		</div>
		<!-- //header-ends -->
		<!-- main content start-->
				<div id="page-wrapper">
					<div class="main-page">
						<div class="forms">
							<div class="form-grids row widget-shadow" data-example-id="basic-forms"> 
								<div class="form-title">
									<h4>简报 :</h4>
								</div>
								<div class="form-body" id="form-div">
									<form>
										 <c:forEach items="${blocks}" var="block" varStatus="b_status">
											 <div style="border-bottom:1px dashed #ccc; margin-bottom:20px;">
												 <div class="col-md-8 form-group">
												  	<input type="text" class="form-control" id="block${b_status.index+1}" value="${b_status.index+1}. ${block.title}">
												 </div>
												 <div class="col-md-1 form-group">
												 	<h4>
														<a href="javascript:void(0)" onclick="setReadOnly(this)">
															<input type="text" value="block${b_status.index+1}" style="display:none;">
															<span class="label label-success">选中</span>
														</a>
													</h4>
												 </div> 
												 <div class="col-md-8 form-group">
												 	<textarea name="summary" id="summary${b_status.index+1}" cols="50" rows="6" class="form-control">${block.summary}</textarea>
												 </div>
												 <div class="col-md-1 form-group">
												 	<h4>
														<a href="javascript:void(0)" onclick="setReadOnly(this)">
															<input type="text" value="summary${b_status.index+1}" style="display:none;">
															<span class="label label-success">选中</span>
														</a>
													</h4>
												 </div> 
												 <div class="col-md-1 form-group">
												 	<h4>
														<a href="javascript:void(0)" onclick="showList(this)">
															<input type="text" value="list${b_status.index+1}" style="display:none;">
															<span class="label label-success">列表</span>
														</a>
													</h4>
												 </div> 
												 <div class="clearfix"></div>
												 <div id="list${b_status.index+1}" class="hidden">
													 <c:forEach items="${block.relevantNews}" var="bean" varStatus="status">
														 <div class="col-md-2 form-group">
														  	<input type="text" class="form-control" name="sourceName" value="${bean.sourceName}" readonly="">
														 </div> 
														 <div class="col-md-2 form-group">
														  	<input type="text" class="form-control" name="publishDate" value="<fmt:formatDate value='${bean.publishDate}' type='date' pattern='yyyy-MM-dd'/>" readonly="">
														 </div> 
														 <div class="col-md-6 form-group">
														  	<input type="text" class="form-control" name="title" value="${bean.title}" id="block${b_status.index+1}bean${status.index+1}">
														 </div>
														 <div class="col-md-1 form-group">
														 	<h4>
														 		<a href="${bean.url }" target="blank"><span class="label label-info">查看</span></a>
															</h4>
														 </div> 
														 <div class="col-md-1 form-group">
														 	<h4>
																<a href="javascript:void(0)" onclick="setReadOnly(this)">
																	<input type="text" value="block${b_status.index+1}bean${status.index+1}" style="display:none;">
																	<span class="label label-success">选中</span>
																</a>
															</h4>
														 </div> 
														 <div class="clearfix"></div>
													 </c:forEach>
												 </div>
												 <div class="col-md-1 form-group hidden" id="addlist${b_status.index+1}">
													 	<h4>
															<a href="javascript:void(0)" onclick="add(this)">
																<input type="text" value="list${b_status.index+1}" style="display:none;">
																<span class="label label-primary">添加</span>
															</a>
														</h4>
												 	 </div>
												 <div class="clearfix"></div> 
											 </div>
										 </c:forEach>
										 <div class="clearfix"></div>
										 <div style="text-align:right">
										 	<button type="submit" class="btn btn-default" style="margin-right:1rem" id="report">我要报错</button> 
										 	<button type="submit" class="btn btn-default" style="margin-right:1rem" id="preview">预&nbsp;&nbsp;览</button> 
										 	<button type="submit" class="btn btn-default" id="briefing">生成简报</button> 
										 </div>	
									</form> 
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
					
		<!--footer-->
		<div class="footer">
		   <p>©2007–2016 开声市场营销策划（上海）有限公司 保留所有权利沪ICP备15043505号 <a href="http://www.commnow.cn/" target="_blank" title="传声网"></a> - Collect from <a href="http://www.commnow.cn/" title="传声网" target="_blank">传声网</a></p>
		</div>
        <!--//footer-->
	
	<!-- Classie -->
		<style type="text/css">
			.hidden{
				display:none;
			}
			a:link{
				text-decoration:none;
			}
			a:visited{
				text-decoration:none;
			}
			a:hover{
				text-decoration:none;
			}
			a:active{
				text-decoration:none;
			}
		</style>
		<script src="${pageContext.request.contextPath}/js/classie.js"></script>
		<script>
			var menuLeft = document.getElementById( 'cbp-spmenu-s1' ),
				showLeftPush = document.getElementById( 'showLeftPush' ),
				body = document.body;
				
			showLeftPush.onclick = function() {
				classie.toggle( this, 'active' );
				classie.toggle( body, 'cbp-spmenu-push-toright' );
				classie.toggle( menuLeft, 'cbp-spmenu-open' );
				disableOther( 'showLeftPush' );
			};
			
			function disableOther( button ) {
				if( button !== 'showLeftPush' ) {
					classie.toggle( showLeftPush, 'disabled' );
				}
			}
			function setReadOnly(obj){
				var text = $(obj).children('span').text();
				var id = $(obj).children("input").val();
				if(text == "编辑"){
					$("#"+id).removeAttr("readonly");
					$(obj).children('span').text("选中");
				}else{
					$("#"+id).attr("readonly","readonly");
					$(obj).children('span').text("编辑");
				}
			}
			function showList(obj){
				var text = $(obj).children('span').text();
				var id = $(obj).children("input").val();
				if(text == "收起"){
					$("#"+id).addClass("hidden");
					$("#"+"add"+id).addClass("hidden");
					$(obj).children('span').text("列表");
				}else{
					$("#"+id).removeClass("hidden");
					$("#"+"add"+id).removeClass("hidden");
					$(obj).children('span').text("收起");
				}
			}
			function add(obj){
				var str = '<div class="col-md-2 form-group">'+
				  		  '<input type="text" class="form-control" name="sourceName" value="" palceholder="媒体">'+
				 		  '</div>'+ 
				 		  '<div class="col-md-2 form-group">'+
				  		  '<input type="text" class="form-control" name="publishDate" value="" palceholder="发表时间">'+
				 		  '</div>'+ 
				 		  '<div class="col-md-6 form-group">'+
				  		  '<input type="text" class="form-control" name="title" value="" id="" placeholder="标题">'+
				 		  '</div>'+
				 		  '<div class="col-md-1 form-group">'+
				 		  '<input type="text" class="form-control" name="url" value="" id="" placeholder="链接">'+
				 		  '</div>'+ 
				 		  '<div class="col-md-1 form-group">'+
				 		  '<h4>'+
						  '<a href="javascript:void(0)" onclick="setReadOnly(this)">'+
						  '<input type="text" value="" style="display:none;">'+
						  '<span class="label label-success">选中</span>'+
						  '</a>'+
						  '</h4>'+
				 		  '</div>'+ 
				 		  '<div class="clearfix"></div>'
				var id = $(obj).children("input").val();
				$("#"+id).append(str);
			}
	</script>
	<!--scrolling js-->
	<script src="${pageContext.request.contextPath}/js/jquery.nicescroll.js"></script>
	<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
	<!--//scrolling js-->
	<!-- Bootstrap Core JavaScript -->
   <script src="${pageContext.request.contextPath}/js/bootstrap.js"> </script>
</body>
</html>