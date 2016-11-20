<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="themes/icon.css">
	
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="js/validator.js"></script>
	<script type="text/javascript" >
	

	
	var _url;
	function add(){
		$('#inputloginid').val("");
		$('#inputnames').val("");
		$('#inputauth').val("");
		$('#inputpwd').val("");
		$('#xx').hide();
		
		 $('#inputloginid').attr("readonly",false);
		_url = "../IT007/AdminUser?option=1";
        $('#dlg').dialog('open').dialog('setTitle','新增用户信息'); 
        
	}
	function edit(){
		var row = $('#dg').datagrid('getSelected');   
		 $('#inputnames').val(row.Name);
		 if (row){ 
             $('#dlg').dialog('open').dialog('setTitle','编辑用户信息'); 
             $('#inputloginid').val(row.LoginID);
             $('#inputloginid').attr("readonly",true);
             $('#inputnames').val(row.Name.replace(/(\s*$)/g, ""));
             $('#aa').show();
             $('#inputauth').val(row.Authorizations);
             _url = "../IT007/AdminUser?option=2&CKid="+row.LoginID+"";
             $('#fm').form('load',row); 
            	
         }else{
         $.messager.alert('消息', '请选择一条记录', 'info'); 
         }  
		
	}
	
function del(){
	var row = $('#dg').datagrid('getSelected'); 
	if (row){
	
		$.post("../IT007/AdminUser",{option:3,del:row.LoginID},

			     function(data){//data为反回值，function进行反回值处理
			var _json = JSON.parse(data);
					
					if(_json.Secces == 12){
						$.messager.alert('消息', '成功删除', 'info'); 
						$('#dg').datagrid("reload");
					}else if(_json.Error == 23){
						$.messager.alert('消息', '权限不足', 'info'); 
					}else{
						$.messager.alert('消息', '错误！', 'Error'); 
					}
			           

			      });
				
	}else{
		 $.messager.alert('消息', '请选择一条记录', 'info'); 
	}	
		
	
	};
function tijiaoForm(){
		
		$('#fm').form('submit', { 
		    url:_url, 
		    onSubmit: function(){ 
		        //进行表单验证 
		        //如果返回false阻止提交 
		        
		        if($('#inputloginid').val() == "" ){
		        	
		        	$.messager.alert('消息', '用户名不能为空', 'info'); 
		        	return false;
		        }
		        if($('#inputloginid').val().length >= 30){
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
		        if($('#inputnames').val() == "" ){
		        	
		        	$.messager.alert('消息', '姓名不能为空', 'info'); 
		        	return false;
		        }
		        if($('#inputnames').val().length >= 20){
	        		$.messager.alert('消息', '姓名过长', 'info'); 
	        		return false;
	        	}
		        if($('#inputauth').val() == ""  ){
		        	
		        	$.messager.alert('消息', '权限不能为空', 'info'); 
		        	return false;
		        }
		        if($('#inputauth').val().length >= 2 ){
	        		$.messager.alert('消息', '字符过长', 'info'); 
	        		return false;
	        	}
		        
		    }, 
		    success:function(reuslt){ 
		    	
		    	var _json = JSON.parse(reuslt);
		    	
		    	if(_json.Secces == 11){
		    		$.messager.alert('消息', '保存成功', 'ok'); 
		    		$('#dlg').dialog('close');
		    		$('#dg').datagrid("reload");
		    	}else if(_json.Error == 21){
		    		$.messager.alert('消息', '保存失败', 'error'); 
		    	}else if(_json.Error == 22){
		    		$.messager.alert('消息', '用户已存在', 'error'); 
		    	}
		    	
		    	
		    	
		        
		    } 
		}); 
	}
	function finddata(){
		$("#dg").datagrid({
			url:'../IT007/SCjson',
			queryParams:{
				zhaxun:$("#chaxun").val()
			}
		});
		//$('#dg').datagrid("reload");
	}


	</script>
	
	
</head>
<body >
	 <div class="easyui-panel"  icon="icon-save" fit="true" collapsible="true" minimizable="true" maximizable=true closable="true">
  <div class="easyui-layout" fit="true" >
    <div region="north" border="false" style="border-bottom:1px solid #ddd;height:32px;padding:2px 5px;background:#fafafa;">
      <div style="float:left;">
        <a href="#" class="easyui-linkbutton" plain="true" onclick="add()" icon="icon-add">新增</a>
      </div>
      <div class="datagrid-btn-separator"></div>
       <div style="float:left;">
        <a href="#" class="easyui-linkbutton" plain="true" onclick="edit()" icon="icon-save">编辑</a>
      </div>
      <div class="datagrid-btn-separator"></div>
       <div style="float:left;">
        <a href="#" class="easyui-linkbutton" id="delSC" plain="true" onclick="del()" icon="icon-remove">删除</a>
      </div>
      <div style="float:right;">
         <input class="easyui-searchbox" id="chaxun" data-options="prompt:'请输入查询条件',searcher:''" style="width:130px;vertical-align:middle;"></input>
         <a href="#" class="easyui-linkbutton" plain="true" onclick="finddata()">检索</a>
      </div>
    </div>
     <div region="center" border="false">   
	<table id="dg" title="Client Side Pagination" style="padding-top: 10px"  text-align: center;">
	
	</table>
	</div>
	</div>
    </div>
	<div id="dlg" class="easyui-dialog" title="Basic Dialog" data-options="iconCls:'icon-save',buttons: '#dlg-buttons',iconCls: 'icon-save' "style="width:340px;height:450px;padding:10px">
		
		<form   id="fm" name="fm" method="post"   data-toggle="validator" >
			        <div class="form-group">
			        
			          <label for="inputName" class="control-label">账号</label>
			          <div class="input-group">
			          <span class="input-group-addon">☺</span>
			          <input type="text" pattern="^[_A-z0-9]{1,}$"   maxlength="20" class="form-control" id="inputloginid"    name="loginid" placeholder="请输入账号"  >
			        </div>
			        </div>
			         <div class="form-group" id="aa">
			          <label id="" for="inputTwitter" class="control-label">密码</label>
			          <div class="input-group">
			            <span class="input-group-addon">🔑</span> 
			            <input type="password" pattern="^[_A-z0-9]{1,}$" maxlength="15" name="pwd" class="form-control" id="inputpwd" placeholder="请输入旧密码" >
			          </div>
			          </div>
			        <div class="form-group" id="xx">
			          <label for="inputTwitter" class="control-label">新密码</label>
			          <div class="input-group">
			            <span class="input-group-addon">🔑</span> 
			            <input type="password" pattern="^[_A-z0-9]{1,}$" maxlength="15" name="pwd" class="form-control" id="inputpwd" placeholder="请输入新密码" >
			          </div>
			          </div>
			          <div class="form-group">
			          <label for="inputTwitter" class="control-label">姓名</label>
			          <div class="input-group">
			            <span class="input-group-addon">☞</span>
			            <input type="text"  maxlength="20" name="names" class="form-control" id="inputnames" placeholder="请输入姓名" >
			          </div>
			          </div>
			          <div class="form-group">
			          <label for="inputTwitter" class="control-label">权限</label>
			          <div class="input-group">
			           <span class="input-group-addon">☆</span>
			           	 <select id="inputauth" name="authorizations" class="form-control" style="width: 250px;">
								
								<option value="0">超级管理员</option>
								<option value="1">管理员</option>
								<option value="2">部门经理</option>
								<option value="3">组长</option>
								
						</select>  
			           
			            
			          </div>
			          
			        </div>			      
			       <div id="dlg-buttons">
		
		<a href="#" class="easyui-linkbutton" onclick="tijiaoForm()" data-options="iconCls:'icon-save'">保存</a>
		<a href="#" class="easyui-linkbutton" onclick="$('#dlg').dialog('close')" data-options="iconCls:'icon-cancel' ">关闭</a>
		
	</div>
			      </form>
		
		
	</div>
	<script type="text/javascript">
	$(function(){
		
		$('#dg').datagrid({
			title:"",
			fit:true,
			fitColumns:true,
			url:'../IT007/SCjson',			
			rownumbers:true,			
			singleSelect:true,			
			columns:[[
			    {field:'ck',title:"" ,checkbox:true},
				{field:"LoginID",title:"登陆名" ,width:200 ,align:"center",resizeable:true}, 
				{field:"Name",title:"姓名" ,width:200 ,align:"center",resizeable:true},     
				{field:"Authorizations",title:"权限" ,width:200 ,align:"center",resizeable:true}  
			   ]],			
			pagination:true,
			pageList:[5,10,20,30,50,100],
			pageSize:20
		});
		
		//设置分页信息
		$("#dg").datagrid("getPager").pagination({
			
			displayMsg:"当前显示 {from}至{to} 条记录，共有{total} 条记录"
		});
		$('#dlg').dialog('close');
	});
	
	
	
	</script>
</body>
</html>