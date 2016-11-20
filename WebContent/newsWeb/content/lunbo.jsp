<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>"> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel='stylesheet prefetch' href='NewsOS/newsWeb/css/bootstrap.min.css'>

<link rel="stylesheet" type="text/css" href="NewsOS/newsWeb/css/lunbo.css">
	<script src='NewsOS/newsWeb/js/jquery-2.2.3.min.js'></script>
	<script src='NewsOS/newsWeb/js/bootstrap.min.js'></script>
	
<title>Insert title here</title>

</head>
<body>
<div class="htmleaf-container">
		
		<section class="awSlider">
		  <div  class="carousel slide" data-ride="carousel">
		    <!-- Indicators -->
		    <ol class="carousel-indicators">
		      <li data-target=".carousel" data-slide-to="0" class="active"></li>
		      <li data-target=".carousel" data-slide-to="1"></li>
		      <li data-target=".carousel" data-slide-to="2"></li>
		      <li data-target=".carousel" data-slide-to="3"></li>
		    </ol>

		    <!-- Wrapper for slides -->
		    <div class="carousel-inner" role="listbox" id="imgbox">
		      <!-- <div class="item active">
		        <img src="NewsOS/newsWeb/img/wallpaper-thumb-941.jpg">
		        <div class="carousel-caption">Görsel #1</div>
		      </div>
		      <div class="item">
		        <img src="NewsOS/newsWeb/img/wallpaper-thumb-101.jpg">
		        <div class="carousel-caption">Görsel #2</div>
		      </div>
		      <div class="item">
		        <img src="NewsOS/newsWeb/img/wallpaper-thumb-1051.jpg">
		        <div class="carousel-caption">Görsel #3</div>
		      </div>
		      <div class="item">
		        <img src="NewsOS/newsWeb/img/wallpaper-thumb-74.jpg">
		        <div class="carousel-caption">Görsel #4</div>
		      </div> -->
		    </div>

		    <!-- Controls -->
		    <a class="left carousel-control" href=".carousel" role="button" data-slide="prev">
		      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
		      <span class="sr-only">Geri</span>
		    </a>
		    <a class="right carousel-control" href=".carousel" role="button" data-slide="next">
		      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
		      <span class="sr-only">İleri</span>
		    </a>
		  </div>
		</section>
		
	</div>
<script>
	$('section.awSlider .carousel').carousel({
	    pause: 'hover',
	    interval: 2000
	});
	var startImage = $('section.awSlider .item.active > img').attr('src');
	$('section.awSlider').append('<img src="' + startImage + '">');
	$('section.awSlider .carousel').on('slid.bs.carousel', function () {
	    var bscn = $(this).find('.item.active > img').attr('src');
	    $('section.awSlider > img').attr('src', bscn);
	});
	
	
	
	$(function() {
		$.getJSON("/NewsOS/IT007/SCimg", function(data) {
			$.each(data.rows, function(i, field) {
				if(i==0){
					$("#imgbox").append(
							" <div class='item active'><img src='"+"http://localhost:8080/"+field["imgurl"]+"'><div class='carousel-caption'>"+field["upimgdepict"]+"</div></div>");
				}else{
				$("#imgbox").append(
						
						" <div class='item'><img src='"+"http://localhost:8080/"+field["imgurl"]+"'><div class='carousel-caption'>"+field["upimgdepict"]+"</div></div>");
				}
				});
		});

	});
	</script>

	
</body>
</html>