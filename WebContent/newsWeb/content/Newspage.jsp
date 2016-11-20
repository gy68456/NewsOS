<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/newsWeb/";	
	int op =0;
	String str = (String)session.getAttribute("sionLogin");
	if(str != null){
		op =1;
	}else{
		op =0;
	}
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新闻显示页面</title>
<link rel="stylesheet" href="css/style2.css" type="text/css" />
<link rel="stylesheet" href="js/pagination.css" type="text/css"></link>
<link rel="stylesheet" href="css/skin.css" type="text/css"></link>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.pagination.js"></script>


<script type="text/javascript">
	/*$(function() {
		$.getJSON("../IT007/SCjsonNews", function(data) {
			$.each(data.rows, function(i, field) {
				$("#rl ul").append(
						"<li><a href='#'>" + field["NewsTitle"] + "</a><span>"
								+ field["ReleaseTime"] + "</span></li>");
			});
		});

	});*/

	
		$(function() {//页面加载时，进行绑定

			bind(0);
		});

		//点击分页时调用的函数，page_id为当前页的索引
		function pageselectCallback(page_id, jq) {
			bind(page_id);
			//bind(0);
			
		}

		function bind(pageIndex) {
			var temp = "";
			var addrs = "";
			var total = 0;
			
			$.ajax({
				type : "post",
				url : "../IT007/SCjsonNews?id="+GetQueryString("id")+"&pageindex=" + (pageIndex + 1),
				async : false, ////作用是防止在ajax成功调用之前就调用$("#Pagination").pagination,这个时候数据个数还没有初始化
				dataType : "json",
				data : {pageInde: pageIndex + 1},//"pageIndex=" + (pageIndex + 1),//传递页面索引
				//发送请求前，显示加载动画
				/* beforeSend : function() {
					$("#divload").show();
					$("#datas #Pagination").hide()
				},*/
				//请求完毕后，隐藏加载动画
				complete : function() {
					$("#divload").hide();
					$("#datas #Pagination").show()
				}, 
				success : function(data) {
					
					var json = data.rows;//json数据 href='../Download?name="+field["name"]+"&url="+field["FilePath"]+"'
					total = data.total;//记录总数
					if(GetQueryString("id") == 5){
						$.each(json, function(i, field) {
							$('#addrs').hide();
							temp += "<li><a onclick=\"XiaZ('"+field["FilePath"]+"','"+field["name"]+"')\">" + field["name"]
									+ "</a><span>" + field["upfiletime"]
									+ "</span></li>";
						});
					}else if(GetQueryString("id") ==6 || GetQueryString("id") ==7){$('#addrs').hide();}
					else{
					$.each(json, function(i, field) {

						temp += "<li><a href='cn/jk/team/Newscontent.html?NewsId="+field["NewsID"]+"'>" + field["NewsTitle"]
								+ "</a><span>" + field["ReleaseTime"]
								+ "</span></li>";
						addrs = field["Newstype"];
					});
					}
					$("#datas").html(temp); //将创建的新行附加在DIV中
					$("#addrs").html("<a>当前位置：主页>>>"+addrs+"</a>");
					$(document).attr("title",addrs);
					
					if (total != 0) {
						//调用分页函数，将分页插件绑定到id为Pagination的div上
						$("#Pagination").pagination(total, { //recordCount在后台定义的一个公有变量，通过从数据库查询记录进行赋值，返回记录的总数
							
							callback : pageselectCallback, //点击分页时，调用的回调函数
							prev_text : '« 上一页', //显示上一页按钮的文本
							next_text : '下一页 »', //显示下一页按钮的文本
							items_per_page : 10, //每页显示的项数
							num_display_entries : 6, //分页插件中间显示的按钮数目
							current_page : pageIndex, //当前页索引
							num_edge_entries : 2, //分页插件左右两边显示的按钮数目
							link_to: "cn/jk/team/newspage.html?id="+GetQueryString("id")+"#"
						});
					} 	
					
				}

			});

			

		}
		
		
		function GetQueryString(name) {
			   var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");
			   var r = window.location.search.substr(1).match(reg);
			   if (r!=null) return (r[2]); return null;
			}
		function XiaZ(Url,Name)
		{
			
			if(<%=op%> !=1)
				{
				
				alert("请先登录");
				}
			else
				{
				
				window.location.href="../Download?name="+Name+"&url="+Url+"";
					
				}
		}
</script>
</head>
<body>
	<div id="content">
		<div id="top">
			<p>
				<a href="#">Home</a><a href="#">About</a><a href="#">Archive</a><a
					href="#">Sitemap</a>
			</p>
			<form id="search_engine" method="post" action="."
				accept-charset="UTF-8">
				<p>
					<input class="searchfield" name="search_query" type="text" id="keywords" value="输关键字检索" onfocus="document.forms['search_engine'].keywords.value='';" onblur="if (document.forms['search_engine'].keywords.value == '') document.forms['search_engine'].keywords.value='输关键字检索';" />
				<input class="searchbutton" name="submit" type="submit" value="搜索" />
				</p>
			</form>
		</div>

		<div id="logo">
			<h1>
				<a href="#">小新闻   大世界</a>
			</h1>
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
			<h3 id="addrs">
				
			</h3>

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
				<div id="divload" style="text-align: center">
					<img src="images/wait.gif" />
				</div>
				 	
				<div id="datas"></div>
				 <div id="Pagination" class="meneame"></div> 
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