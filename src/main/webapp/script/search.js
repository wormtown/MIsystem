/**
 * 生成html
 */
function createPage(){
	var companyName = $("#company").val();
	var scope = $("#scope").val();
	var start = $("#start").val();
	var end = $("#end").val();
	var dir = $("#direction").val();
	$.ajax({
		url:"http://localhost:8080/CommnowMi/esclient/createPage.do",
		type:"post",
		async: false,
		data:{"companyName":companyName,"scope":scope,"start":start,"end":end,"dir":dir},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				alert("成功生成页面，请在我的文档中查看！");
			}
		},
		error:function(){
			
		}
	});
}
/**
 * 生成excel
 */
function createExcel(){
	var companyName = $("#company").val();
	var scope = $("#scope").val();
	var start = $("#start").val();
	var end = $("#end").val();
	var dir = $("#direction").val();

	$.ajax({
		url:"http://localhost:8080/CommnowMi/esclient/createExcel.do",
		type:"post",
		async: false,
		data:{"companyName":companyName,"scope":scope,"start":start,"end":end,"dir":dir},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				alert("成功生成表格，请在我的文档中查看！");
			}
		},
		error:function(){
			
		}
	});
}

