<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://ckfinder.com" prefix="ckfinder" %>  
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  

%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="UTF-8">
<title>新闻发布页</title>
<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="css/AddNewsInfo.css">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="themes/icon.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="../ckfinder/ckfinder.js"></script>

<script type="text/javascript">

	var editor = null;
	window.onload = function() {
		editor = CKEDITOR.replace('content'); //参数‘content’是textarea元素的name属性值，而非id属性值
	}

    function save(){
      //editor.updateElement(); //非常重要的一句代码
     
        
   		$('#detailForm').form('submit', { 
    		    url:"../IT007/AddNewsinfo", 
    		    onSubmit: function(){ 
    		        //进行表单验证 
    		        //如果返回false阻止提交 
    		        
    		        if($('#txttitle').val().length <= 0 ){
    		        	
    		        	$.messager.alert('消息', '标题不能为空', 'info'); 
    		        	return false;
    		        }
    		        if($('#Newstype').val().length <= 0){
    	        		$.messager.alert('消息', '类型不能为空', 'info'); 
    	        		return false;
    	        	}   		     
    		        if(CKEDITOR.instances.content.getData() == ""){
    		        	
    	        		$.messager.alert('消息', '内容不能为空', 'info'); 
    	        		return false;
    	        	}
 		        
    		    }, 
    		    success:function(reuslt){     		    	
    		    	var _json = JSON.parse(reuslt);  		    	
    		    	if(_json.Secces == 1){
    		    		$.messager.alert('消息', '保存成功', 'ok');    		    		   		    		
    		    	}else if(_json.Error == 0){
    		    		$.messager.alert('消息', '保存失败', 'error'); 
    		    	}    		        
    		    } 
    		}); 
    	
    }
</script>
 
</head>
<body>
			<div class="easyui-panel"  fit="true">
		
			<form id="detailForm" method="post" style="margin-left: 20px" >
			
			  <div class="form-group" style="margin-top: 2px">
			        
			          
			          <div class="input-group" >
			          <span class="input-group-addon" >☺</span>
			          <input type="text"  maxlength="50" class="form-control" style="width: 600px;" id="txttitle"  name="txttitle" placeholder="请输入标题,不超过50个汉字"  >
			        <span class="input-group-addon" >标题</span>
			        </div>
			        </div>
			  
			  
			<div class="form-group" style="margin-top: -5px">
			        
			         
			          <div class="input-group" >
			          <span class="input-group-addon" >☺</span>
			          <input type="text"  maxlength="50" class="form-control" style="width: 600px;" id="txtdepict"  name="txtdepict" placeholder="请描述主题，选填"  >
			        <span class="input-group-addon" >说明</span>
			        </div>
			        </div>
			        
			        <div class="form-group" style="margin-top: -10px">
			    <div class="input-group" >
			   
			        <select id="Newstype" name="Newstype" class="form-control" style="width: 400px;">
								
								<option value="0">头条</option>
								<option value="1">要闻</option>
								<option value="2">深谈</option>
								<option value="3">新议</option>
								
						</select> 
			           <span class="input-group-addon" >新闻类型</span>
			          </div>
			        </div>
				<textarea id="content" name="content" style="margin-top: -10px"></textarea>
				<a href="#" class="easyui-linkbutton" onclick="save()" data-options="iconCls:'icon-save'">保存</a>
					<!-- <input type="submit" value="Submit" onclick="save()" /> -->
			</form>
			
		
	</div>
	<ckfinder:setupCKEditor basePath="../ckfinder/" editor="content" />  
<ckeditor:replace replace="content" basePath="../ckeditor/" />  
</body>

</html>