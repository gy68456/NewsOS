<%@page import="com.sun.xml.internal.bind.v2.schemagen.xmlschema.Import"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
 <%@ page import="javax.servlet.http.HttpSession" %>
 <%@ page import="Servlet.Login" %>
 <%@ page import="zjxTool.nowtime" %>
<%
	
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/newsWeb/";
Login objLogin = new Login();
int op =0;
String str = (String)session.getAttribute("sionLogin");
if(str != null){
	op =1;
}else{
	op =0;
}
nowtime objnowtime = new nowtime();
String time = objnowtime.nowtime();

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>"> 	 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新闻发布系统前台页面</title>
	<link rel="stylesheet" href="css/style.css" type="text/css" />
	<script type="text/javascript" src="../Admin/js/jquery.min.js"></script>

	<script type="text/javascript" src="../Admin/js/jquery.min.js"></script>
	<script type="text/javascript" src="../Admin/js/jquery.easyui.min.js"></script>
	
	<script type="text/javascript" src="../Admin/js/validator.js"></script>
	<script type="text/javascript" src="../Admin/js/drag.js"></script>
	<script type="text/javascript" >
	
function tijiaoForm(){
		
		$('#fm').form('submit', { 
		    url:'../IT007/Login?index=1', 
		    
		    onSubmit: function(){ 
		        //进行表单验证 
		        //如果返回false阻止提交 
		        
		        if($('#inputlogin').val() == "" ){
		        	
		        	$.messager.alert('消息', '用户名不能为空', 'info'); 
		        	return false;
		        }
		        if($('#inputlogin').val().length >= 30){
	        		$.messager.alert('消息', '用户名过长', 'info'); 
	        		return false;
	        	}
		        if($('#inputpwd').val() == ""){
		        	
		        	$.messager.alert('消息', '密码不能为空', 'info'); 
		        	return false;
		        }
		        if($('#inputpwd').val().length >= 15){
	        		$.messager.alert('消息', '密码过长', 'info'); 
	        		return false;
	        	}
		        
		    }, 
		    success:function(reuslt){ 
		    	
		    	var _json = JSON.parse(reuslt);
		    	
		    	if(_json.Secces == 1){
		    		alert( '登录成功')
		    		
		            
		    		
		    	}else if(_json.Error == 0){
		    		alert( '用户不存在') 
		    		
		             
		    		
		    	}
		       
		    } 
		}); 
	}

function aa(){
	if(<%= op%> == 1){
		$('#ad').hide();
		$('#ap').append("");
		$('#ap').show();
		
	}else{
		$('#ap').hide();
		$('#ad').show();
	}
}

$(function() {//页面加载时，进行绑定
	
	bind(0);
});

function bind(pageIndex) {
	var temp = "";
	var total = 0;
	$.ajax({
		type : "post",
		url : "../IT007/SCjsonNews?id=0",
		async : false, ////作用是防止在ajax成功调用之前就调用$("#Pagination").pagination,这个时候数据个数还没有初始化
		dataType : "json",
		success : function(data) {
			var json = data.rows;//json数据
			total = data.total;//记录总数
			$.each(json, function(i, field) {

				temp += "<li><a href='cn/jk/team/Newscontent.html?NewsId="+field["NewsID"]+"'>" + field["NewsTitle"]
						+ "</a><span>";
			});
			$("#datas").html(temp); //将创建的新行附加在DIV中
			
			
			
		}

	});

	$(function() {
		$.getJSON("../IT007/SCjsonNews?id=1", function(data) {
			$.each(data.rows, function(i, field) {
				$("#rr ul").append(
						"<li><a href='cn/jk/team/Newscontent.html?NewsId="+field["NewsID"]+"'>" + field["NewsTitle"] + "</a><span>");
			});
		});

	});
	
	

}
</script>
</head>
<body onload="aa()">
<div id="content">
		<div id="top">
			<p><a href="#">Home</a><a href="#">About</a><a href="#">Archive</a><a href="#">Sitemap</a></p>
			<form id="search_engine" method="post" action="." accept-charset="UTF-8">
				<p><input class="searchfield" name="search_query" type="text" id="keywords" value="输关键字检索" onfocus="document.forms['search_engine'].keywords.value='';" onblur="if (document.forms['search_engine'].keywords.value == '') document.forms['search_engine'].keywords.value='输关键字检索';" />
				<input class="searchbutton" name="submit" type="submit" value="搜索" /></p>
			</form>
		</div>
	
		<div id="logo">
			<h1><a href="#">小新闻   大世界</a></h1>
			<p>Little  news  big  world</p>
		</div>

		<ul id="menu">
					
			<li><a href="cn/jk/team/index.html?id=0">主页</a></li>
			<li><a href="cn/jk/team/newspage.html?id=0">头条</a></li>
			<li><a href="cn/jk/team/newspage.html?id=1">要闻</a></li>
			<li><a href="cn/jk/team/newspage.html?id=2">深谈</a></li>
			<li><a href="cn/jk/team/newspage.html?id=3">新议</a></li>
			<li><a href="cn/jk/team/newspage.html?id=5">下载</a></li>
			<li><a href="cn/jk/team/newspage.html?id=6">关于</a></li>
			<li><a href="cn/jk/team/newspage.html?id=7">帮助</a></li>
		</ul>
		
		<div id="main">
			<h3><a href="#">看你所看，想你所想</a></h3><h2><a href="#">Aliquam metus turpis, luctus ac, sagittis eget</a></h2>
			<p>
			<iframe src="content/lunbo.jsp" frameborder=0 height=281px width=500px scrolling=no></iframe>
		
			</p>
			</div>
		
		<div id="ad" >
			<form id="fm" method="post"  accept-charset="UTF-8">
				<p><input class="searchbutton" type="submit" value="登录代码" />
				<input class="searchfield" pattern="^[_A-z0-9]{1,}$" maxlength="20" class="form-control" id="inputlogin" name="inputlogin"  type="text" id="keywords"/> </p>
				
				<p><input class="searchbutton" name="submit" type="submit" value="登录密码" />
				<input class="searchfield" pattern="^[_A-z0-9]{1,}$" maxlength="15" class="form-control" id="inputpwd" name="inputpwd" type="password" id="keywords" /></p>
				<p>
				<input name="submit" type="submit" onclick="tijiaoForm()" value="登录" />
				<input name="submit" type="submit" value="注册" />
				</p>
			</form>
		</div>
		
		<div id="ap" >
			<p><%= str%> :您好 </p>
			
			<p> 当前积分为:102</p>
			<p>现在时间为:<%= time %> </p>
				
			
		</div>
		
		<div id="shortnews">
			<div class="news">
				<h3><a href="#">好奇！让你发现新大陆</a></h3>
				<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Ut pretium mauris nec arcu.</p>
			</div>
			<h3><a href="#">让新闻带你认识崭新的世界</a></h3>
			<p>Aliquam lacus massa, pellentesque sit amet, feugiat et, cursus volutpat, massa. Integer nibh. Maecenas mattis ipsum a felis.</p>
		</div>

		<div id="line"></div>
		
		<div id="left">
			
				<ul>
			<li><a href="cn/jk/team/newspage.html?id=0">头条</a></li>
			<li><a href="cn/jk/team/newspage.html?id=1">要闻</a></li>
			<li><a href="cn/jk/team/newspage.html?id=2">深谈</a></li>
			<li><a href="cn/jk/team/newspage.html?id=3">新议</a></li>
			<li><a href="cn/jk/team/newspage.html?id=5">下载</a></li>
			<li><a href="#">综合</a></li>
			<li><a href="#">更多</a></li>
					
				</ul>
		</div>
					
		<div id="right">	
			<div id="rl">
			<div id="rltitle">头条<span><a href="http://localhost:8080/NewsOS/newsWeb/cn/jk/team/newspage.html?id=0">MORE>></a></span></div>
				<ul>
					<div id="datas"></div>
				</ul>
			</div>
			
			<div id="rr">
			<div id="rltitle">要闻<span><a href="http://localhost:8080/NewsOS/newsWeb/cn/jk/team/newspage.html?id=1">MORE>></a></span></div>
				<ul>
				</ul>
			</div>
		
			<p class="border">新闻是包含海量资讯的新闻服务平台,真实反映每时每刻的新闻热点。您可以搜索新闻事件、热点话题、人物动态、产品资讯等,快速了解它们的最新进展。新闻中心是一个包含有头条，要闻，深谈，新议的专业时事报道门户网站。消息即狭义的新闻，它是对新近发生的有社会意义并引起公众兴趣的事实的简短报道。因此，真实性、时效性及文字少、篇幅小成为消息的基本特征。</p>
		</div>
			
		<div id="footer">
			<ul id="fr" class="links">
				<li><a title="RSS Articles">真实</a></li>
				<li><a title="RSS Pages">新鲜</a></li>
				<li><a title="RSS Comments">时效</a></li>
				<li><a title="RSS Comments">重要</a></li>
				<li><a title="RSS Comments">有趣</a></li>
				<li><a title="RSS Comments">简洁</a></li>
			</ul>
			<div id="fl">
				<p class="links"><a href="#">首页</a><a href="#">关于网页</a><a href="#">意见</a><a href="#">网站地图</a></p>
				<p>新闻报道具有真实、新鲜、重要、趣味可读，时效性极强的特点。新闻的本源是讲究用事实说话，新闻是对客观事实进行报道和传播而形成的信息，反映在新闻信息中的内容必须对事实具有真实传达。足不出门，让新闻带你认识崭新的世界！</p>
				<p>&copy; Copyright <strong>云工商 &middot;信息工程学院&middot; 2013级计算机科学与技术1班 <a title="Awsome Web Templates" href="http://www.cssmoban.com/">Computer science and technology 1.com</strong></a></p>
			</div>
		</div>	
	</div>
</body>
</html>