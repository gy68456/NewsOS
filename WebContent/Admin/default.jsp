<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新闻发布系统后台显示页</title>
<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<link rel="stylesheet" type="text/css" href="css/default.css">
<link rel="stylesheet" type="text/css" href="css/normalize.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/tabtool.js"></script>
<script type="text/javascript" src="js/script.js"></script>
<script type="text/javascript">
function load(){
addPanel('home.jsp','首页');
}
</script>
</head>
<body class="easyui-layout" onload="load()">
	<div data-options="region:'north',border:false"
		style="height:80px; background: #A9FACD; padding: 10px; background-image: url('img/1.jpg');">
		
		</div>
	<div data-options="region:'west',split:true,title:' '"
		style="width: 250px;">
		<div class="content">


			<div id="menu" class="menu white">
				<div class="menu-header">控制面板</div>
				<ul>
					<li class="active"><a href="javascript:void(0)"
						data-options="plain:true,iconCls:'icon-add'"
						onclick="addPanel('home.jsp','首页')"><i class="fa fa-home"></i>首页</a></li>
					<li><a href="#"><i class="fa fa-file-image-o"> </i>用户管理</a>
						<ul class="submenu">
							<li><a href="#" onclick="addPanel('Userlist.jsp','管理员用户')">管理员用户</a></li>

						</ul></li>
					<li><a href="#"><i class="fa fa-cog"> </i>新闻管理</a>
						<ul class="submenu">
							<li><a href="#"
								onclick="addPanel('ReleaseList.jsp?option=1','已发布新闻列表')">
									已发布 </a></li>
							<li><a href="#"
								onclick="addPanel('ReleaseList.jsp?option=2','未发布新闻列表')">
									未发布 </a></li>
							<li><a href="#"
								onclick="addPanel('AuditingList.jsp?option=3','已审核新闻列表')">
									已审核</a></li>
							<li><a href="#"
								onclick="addPanel('AuditingList.jsp?option=4','未审核新闻列表')">
									未审核 </a></li>

							<li><a href="#" onclick="addPanel('AddNewsInfo.jsp','新建新闻')">
									新建新闻 </a></li>
						</ul></li>
					<li><a href="#"><i class="fa fa-cog"> </i>设置</a>
						<ul class="submenu">
							<li><a href="#" onclick="addPanel('uploadfile.jsp','上传文件')">
									上传文件 </a></li>
						<li><a href="#" onclick="addPanel('uploadimgs.jsp','更改首页图片')">
							更改首页图片</a></li>
				</ul>
					</li>
					
						
					<li><a href="login.jsp"><i class="fa fa-envelope"> </i> 退出<span
						class="menu-label"> out </span></a></li>
				</ul>

			</div>

		</div>
	</div>
	
	<div data-options="region:'center'">

		<div id="tabs" class="easyui-tabs" data-options="tools:'#tab-tools'"
			style="width: 100%; height: 100%"></div>

	</div>
</body>

</html>