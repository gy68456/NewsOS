<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/newsWeb/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>"> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style3.css" type="text/css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
$(function() {
	var temp = "";
	var content = "";
	var addrs = "";
	var title = "";
	var total = 0;
	$.ajax({
		type : "post",
		url : "../IT007/SCjsonNews?NewsID="+GetQueryString("NewsID")+"",
		async : false, ////作用是防止在ajax成功调用之前就调用$("#Pagination").pagination,这个时候数据个数还没有初始化
		dataType : "json",
		success : function(data) {
			var json = data.rows;//json数据
			total = data.total;//记录总数
			$.each(json, function(i, field) {

				temp += "<h1 align='center'>"+field["NewsTitle"]+"</h1> <a href='#'><span>上一篇</span></a>&nbsp&nbsp&nbsp&nbsp<a href='#'><span>下一篇</span></a> <span style='float: right;'>"+field["ReleaseTime"]+"</span>";
				content = field["NewsContent"];
				addrs = field["Newstype"];
				title = field["NewsTitle"];
			});
			$("#rl").html(temp); //将创建的新行附加在DIV中
			$("#centent").html(content);
			$("#addrs").html("<a>当前位置：主页>>>"+addrs+">>>"+title+"</a></h3>");
			$(document).attr("title",title);
			
		}

	});

});


function GetQueryString(name) {
	   var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");
	   var r = window.location.search.substr(1).match(reg);
	   if (r!=null) return (r[2]); return null;
	}
</script>
</head>
<body>
<div id="content">
		<div id="top">
			<p><a href="#">Home</a><a href="#">About</a><a href="#">Archive</a><a href="#">Sitemap</a></p>
			<form id="search_engine" method="post" action="." accept-charset="UTF-8">
				<p><input class="searchfield" name="search_query" type="text" id="keywords" value="Search Keywords" onfocus="document.forms['search_engine'].keywords.value='';" onblur="if (document.forms['search_engine'].keywords.value == '') document.forms['search_engine'].keywords.value='Search Keywords';" />
				<input class="searchbutton" name="submit" type="submit" value="Search" /></p>
			</form>
		</div>
	
		<div id="logo">
			<h1><a href="#">小新闻   大世界</a></h1>
			<p>Little  news  big  world</p>
		</div>

		<ul id="menu">
			<li><a href="cn/jk/team/index.html">主页</a></li>
			<li><a href="cn/jk/team/newspage.html?id=0">头条</a></li>
			<li><a href="cn/jk/team/newspage.html?id=1">要闻</a></li>
			<li><a href="cn/jk/team/newspage.html?id=2">深谈</a></li>
			<li><a href="cn/jk/team/newspage.html?id=3">新议</a></li>
			<li><a href="cn/jk/team/newspage.html?id=5">下载</a></li>
			<li><a href="cn/jk/team/newspage.html?id=6">关于</a></li>
			<li><a href="cn/jk/team/newspage.html?id=7">帮助</a></li>
		</ul>
		
		<div id="main">
			<h3 id="addrs">
					
			</div>
	
			
		<div id="line"></div>
		
	
					
		<div id="right">	
			<div id="rl">
				
			</div>
			<div id="centent" style="font-size: 14px">
			
			</div>
			
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