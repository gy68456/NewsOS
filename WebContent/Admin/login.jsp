<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
session.invalidate();


%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登陆</title>
	<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="themes/icon.css">
	
	<link rel="stylesheet" type="text/css" href="css/login.css">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/drag.css">
	


</head>

<body class="denglu02">
	<div class="dl">
		<div class="biaoti"></div>
		<div class="log">
			<ul class="xuzhi02">
				  <li class="xz">新闻发布后台管理系统</li>
				 <li>读书百遍，其义自见</li>
				 <li>书犹药也，善读之可以医愚</li>	
			</ul>
			  <ul class="deng02">
				<div class="container">
			<div class="row">
				<form data-toggle="validator" role="form"  id="fm" method="post" >
			        <div class="form-group">
			        
			          <label for="inputName" class="control-label">用户名</label>
			          <div class="input-group">
			          <span class="input-group-addon">☺</span>
			          <input type="text" pattern="^[_A-z0-9]{1,}$" maxlength="20" class="form-control" id="inputlogin" name="inputlogin" placeholder="请输入用户名"  required>
			        </div>
			        </div>
			        <div class="form-group has-feedback">
			          <label for="inputTwitter" class="control-label">密码</label>
			          <div class="input-group">
			            <span class="input-group-addon">🔑</span>
			            <input type="password" pattern="^[_A-z0-9]{1,}$" maxlength="15" class="form-control" id="inputpwd" name="inputpwd" placeholder="请输入用密码" required>
			          </div>
			          
			        </div>
			        <div class="form-group">
			         <div id="drag"></div>
			         
			        

			        </div>
			        
			        <div class="form-group">
			          <a type="submit" class="btn btn-primary"  onclick="tijiaoForm()">登陆</a> 
			          
			        </div>
			      </form>
			</div>
		</div>
			</ul>
			
		</div>
	</div>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	
	<script type="text/javascript" src="js/validator.js"></script>
	<script type="text/javascript" src="js/drag.js"></script>
		<script type="text/javascript" >
		
function tijiaoForm(){
		
		$('#fm').form('submit', { 
		    url:'../IT007/Login', 
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
		        if($('.drag_text').html() != "验证通过"){
		        	
		        	$.messager.alert('消息', '验证不通过', 'info'); 
	        		return false;
		        }
		       
		        
		    }, 
		    success:function(reuslt){ 
		    	
		    	var _json = JSON.parse(reuslt);
		    	
		    	if(_json.Secces == 1){
		    		$.messager.alert('消息', '登陆成功', 'ok',function () {
		    			window.location.href='../Admin/default.jsp';
		            }); 
		    		
		    	}else if(_json.Error == 0){
		    		$.messager.alert('消息', '用户不存在', 'error',function () {
		    			window.location.href='../Admin/login.jsp';
		            }); 
		    		
		    	}
		    	
		    	
		    	
		        
		    } 
		}); 
	}
	
	
	</script>
<script type="text/javascript">
$('#drag').drag();
</script>
</body>
</html>