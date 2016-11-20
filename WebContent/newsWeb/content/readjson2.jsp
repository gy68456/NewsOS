<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
	<style rel="stylesheet" type="text/css" >
	#content{margin: auto;}
	#content div {float: left; margin-left: 250px; }
	.tit{width: 600px; height: 20px; margin-top: 10px;}
	.tit a:HOVER {
	color: red;
}
	</style>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	$.getJSON("News.json",function(data){
		$.each(data,function(index,row){
			$("#content").append("<div class='tit'><a href='#'>"+row["Title"]+"</a></div>"+"<div>"+row["AddTime"]+"</div>"+"<br/>");
		});
		
	});
		
});

</script>
</head>
<body>
<div id="content">

</div>
</body>
</html>