<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="../Admin/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../Admin/themes/icon.css">
<script type="text/javascript" src="../Admin/js/jquery.min.js"></script>
<script type="text/javascript" src="../Admin/js/jquery.easyui.min.js"></script>

<script type="text/javascript">
	$(function() {
		
		$("#datetree").tree({
			datatype : "json",
			url : "Menu.json",
			animate : true,
			lines : true,
			onClick : function(node) {
				//判断标签是否已经存在
				if ($('#tt').tabs('exists', node.text)) {
					$('#tt').tabs('select', node.text);
				} else {
					addPanel(node.url, node.text);

				}
			}

		});

		function addPanel(link, tab) {

			$('#tt').tabs('add', {
				title : tab,
				//content: '<div style="padding:10px"><a herf='+link+'>sdfd</a></div>',
				href : link,
				closable : true

			});
		}

	});
</script>
</head>
<body>
	<div id="datetree" class="easyui-panel" style="padding: 5px"></div>


	<div id="tt" class="easyui-tabs" data-options="tools:'#tab-tools'"
		style="width: 50%; height: 400px; margin-top: 20px;"></div>
</body>
</html>