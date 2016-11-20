<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="folders/css/reset.css">
<link rel="stylesheet" type="text/css"
	href="folders/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="folders/css/default.css">
<link rel="stylesheet" type="text/css"
	href="folders/css/jquery.filer.css">
<link rel="stylesheet" type="text/css"
	href="folders/css/jquery.filer-dragdropbox-theme.css">
<link rel="stylesheet" type="text/css" href="folders/css/tomorrow.css">
<link rel="stylesheet" type="text/css" href="folders/css/custom.css">
<script type="text/javascript" src="../ckeditor4.1/ckeditor.js"></script>

<script type="text/javascript">
$('#filer_input').filer({
    showThumbs: true,
   
    templates: {
        box: '<ul class="jFiler-items-list jFiler-items-grid"></ul>',
        item: '<li class="jFiler-item">\
                    <div class="jFiler-item-container">\
                        <div class="jFiler-item-inner">\
                            <div class="jFiler-item-thumb">\
                                <div class="jFiler-item-status"></div>\
                                <div class="jFiler-item-info">\
                                    <span class="jFiler-item-title"><b title="{{fi-name}}">{{fi-name | limitTo: 25}}</b></span>\
                                    <span class="jFiler-item-others">{{fi-size2}}</span>\
                                </div>\
                                {{fi-image}}\
                            </div>\
                            <div class="jFiler-item-assets jFiler-row">\
                                <ul class="list-inline pull-left">\
                                    <li>{{fi-progressBar}}</li>\
                                </ul>\
                                <ul class="list-inline pull-right">\
                                    <li><a class="icon-jfi-trash jFiler-item-trash-action"></a></li>\
                                </ul>\
                            </div>\
                        </div>\
                    </div>\
                </li>',
        itemAppend: '<li class="jFiler-item">\
                        <div class="jFiler-item-container">\
                            <div class="jFiler-item-inner">\
                                <div class="jFiler-item-thumb">\
                                    <div class="jFiler-item-status"></div>\
                                    <div class="jFiler-item-info">\
                                        <span class="jFiler-item-title"><b title="{{fi-name}}">{{fi-name | limitTo: 25}}</b></span>\
                                        <span class="jFiler-item-others">{{fi-size2}}</span>\
                                    </div>\
                                    {{fi-image}}\
                                </div>\
                                <div class="jFiler-item-assets jFiler-row">\
                                    <ul class="list-inline pull-left">\
                                        <li><span class="jFiler-item-others">{{fi-icon}}</span></li>\
                                    </ul>\
                                    <ul class="list-inline pull-right">\
                                        <li><a class="icon-jfi-trash jFiler-item-trash-action"></a></li>\
                                    </ul>\
                                </div>\
                            </div>\
                        </div>\
                    </li>',
        progressBar: '<div class="bar"></div>',
        itemAppendToEnd: false,
        removeConfirmation: true,
        _selectors: {
            list: '.jFiler-items-list',
            item: '.jFiler-item',
            progressBar: '.bar',
            remove: '.jFiler-item-trash-action'
        }
    },
    uploadFile: {
        url: "../IT007/UploadHandle",
        data: null,
        type: 'POST',
        enctype: 'multipart/form-data',
        beforeSend: function(){},
        success: function(data, el){
            var parent = el.find(".jFiler-jProgressBar").parent();
            el.find(".jFiler-jProgressBar").fadeOut("slow", function(){
                $("<div class=\"jFiler-item-others text-success\"><i class=\"icon-jfi-check-circle\"></i> 成功</div>").hide().appendTo(parent).fadeIn("slow");    
            });
            
        },
        error: function(el){
            var parent = el.find(".jFiler-jProgressBar").parent();
            el.find(".jFiler-jProgressBar").fadeOut("slow", function(){
                $("<div class=\"jFiler-item-others text-error\"><i class=\"icon-jfi-minus-circle\"></i> 失败</div>").hide().appendTo(parent).fadeIn("slow");    
            });
        },
        statusCode: null,
        onProgress: null,
        onComplete: null,
        
    },
    onRemove: function(itemEl, file){
        var file = file.name;
        $.post('./php/remove_file.php', {file: file});
    }
});
	
	
</script>

</head>
<body>
	<!-- <div>
		<form action="../IT007/UploadHandle" enctype="multipart/form-data"
			method="post">
			上传用户：<input type="text" name="username"><br /> 上传文件1：<input
				type="file" name="file1"><br /> 上传文件2：<input type="file"
				name="file2"><br /> <input type="submit" value="提交">
		</form>
	</div> -->

	<div class="col-md-6">
	<b>上传图片</b>
			<br>

			<div class="jFiler jFiler-theme-default">
				<input
					style="position: absolute; left: -9999px; top: -9999px; z-index: -9999;"
					name="files[]" id="demo-fileInput-7" multiple="multiple"
					type="file">

			</div>
			
		
	</div>
</body>
<!-- <script src="http://libs.useso.com/js/jquery/1.11.0/jquery.min.js"
	type="text/javascript"></script>
<script>
	window.jQuery
			|| document
					.write('<script src="js/jquery-2.1.1.min.js"><\/script>')
</script> -->
<script type="text/javascript" src="js/jquery.min.js"></script>
<script src="folders/js/bootstrap.min.js" type="text/javascript"></script>
<script src="folders/js/jquery.filer.min.js" type="text/javascript"></script>
<script src="folders/js/prettify.js" type="text/javascript"></script>
<script src="folders/js/scripts.js" type="text/javascript"></script>
<script src="folders/js/custom2.js" type="text/javascript"></script>
</html>