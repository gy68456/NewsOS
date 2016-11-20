<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新闻发布情况显示列表</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min2.css">
<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="themes/icon.css">

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">
function edit(){
	
	if(<%=request.getParameter("option")%> == 2){
		var row = $('#dg').datagrid('getSelected');   
		 if (row){ 
	         $('#dlg').dialog('open').dialog('setTitle','发布新闻'); 	 
	         
	         
	                  
	         
	         
	         $('#title').val(row.NewsTitle);
	         $('#type').val(row.Newstype);        
	         $('#content').val(row.NewsContent);
	         $('#title').attr({readonly:'true'});
	         $('#type').attr({readonly:'true'});     
	         $('#content').attr({readonly:'true'});
	         
	         $('#fm').form('load',row); 
	        	
	     }else{
	     $.messager.alert('消息', '请选择一条记录', 'info'); 
	     }  
	}
	
	
	
}
function quxiaofabu(){
	var row = $('#dg').datagrid('getSelected');  
	 if (row){ 
	$.post("../IT007/Newsliuc",{op:4,NewsID:row.NewsID},

		     function(data){//data为反回值，function进行反回值处理
		var _json = JSON.parse(data);
				
				if(_json.Secces == 11){
					$.messager.alert('消息', '成功', 'info'); 
					$('#dg').datagrid("reload");
				}else if(_json.Error == 21){
					$.messager.alert('消息', '失败', 'info'); 
				}else{
					$.messager.alert('消息', '错误！', 'Error'); 
				}
		           

		      });
	  }else{
	         $.messager.alert('消息', '请选择一条记录', 'info'); 
	         }  	
	}
function chongxinshenhe(){
	var row = $('#dg').datagrid('getSelected');  
	 if (row){ 

	$.post("../IT007/Newsliuc",{op:5,NewsID:row.NewsID},
			
		     function(data){//data为反回值，function进行反回值处理
		var _json = JSON.parse(data);
				
				if(_json.Secces == 11){
					$.messager.alert('消息', '成功', 'info'); 
					$('#dg').datagrid("reload");
				}else if(_json.Error == 21){
					$.messager.alert('消息', '失败', 'info'); 
				}else{
					$.messager.alert('消息', '错误！', 'Error'); 
				}
		           

		      });
	  }else{
	         $.messager.alert('消息', '请选择一条记录', 'info'); 
	         }  	
}



function fabu2(){
	var row = $('#dg').datagrid('getSelected');  
	 if (row){ 
	$.post("../IT007/Newsliuc",{op:6,NewsID:row.NewsID},

		     function(data){//data为反回值，function进行反回值处理
		var _json = JSON.parse(data);
				
				if(_json.Secces == 11){
					$.messager.alert('消息', '成功', 'info'); 
					$('#dg').datagrid("reload");
				}else if(_json.Error == 21){
					$.messager.alert('消息', '失败', 'info'); 
				}else{
					$.messager.alert('消息', '错误！', 'Error'); 
				}
		           

		      });		
}else{
    $.messager.alert('消息', '请选择一条记录', 'info'); 
} 
  } 

function del(){
	var row = $('#dg').datagrid('getSelected'); 
	if (row){
	
		$.post("../IT007/Newsliuc",{op:7,NewsID:row.NewsID},

			     function(data){//data为反回值，function进行反回值处理
			var _json = JSON.parse(data);

			if(_json.Secces == 11){
				$.messager.alert('消息', '删除成功', 'info'); 
				$('#dg').datagrid("reload");
			}else if(_json.Error == 21){
				$.messager.alert('消息', '失败', 'info'); 
			}else{
				$.messager.alert('消息', '错误！', 'Error'); 
			}
			           
			      });
				
	}else{
		 $.messager.alert('消息', '请选择一条记录', 'info'); 
	}	

}


	</script>
</head>
<body>
	<div class="easyui-panel" icon="icon-save" fit="true"
		collapsible="true" minimizable="true" maximizable=true closable="true">
		<div class="easyui-layout" fit="true">
			<div region="north" border="false"
				style="border-bottom: 1px solid #ddd; height: 32px; padding: 2px 5px; background: #fafafa;">


				<div style="float: left;">
					<a href="#" id="aa" class="easyui-linkbutton" plain="true"
						onclick="edit()" icon="icon-save">查看</a>
				</div>



				<div style="float: left;">
					<a href="#" id="cc" class="easyui-linkbutton" id="delSC"
						plain="true" onclick="quxiaofabu()" icon="icon-remove">取消发布</a>
				</div>

				<div style="float: left;">
					<a href="#" class="easyui-linkbutton" id="delSC" plain="true"
						onclick="del()" icon="icon-cancel">删除</a>
				</div>
				<div style="float: right;">
					<input class="easyui-searchbox" id="chaxun"
						data-options="prompt:'请输入查询条件',searcher:''"
						style="width: 130px; vertical-align: middle;"></input> <a href="#"
						class="easyui-linkbutton" plain="true" onclick="finddata()">检索</a>
				</div>
			</div>
			<div region="center" border="false">
				<table id="dg" title="Client Side Pagination"
					style="padding-top: 10px" text-align:center;">

				</table>
			</div>
		</div>
	</div>


	<div id="dlg" class="easyui-dialog" title="Basic Dialog"
		data-options="iconCls:'icon-save',buttons: '#dlg-buttons',iconCls: 'icon-save' "
		style="width: 700px; height: 500px; padding: 10px">

		<form id="fm" name="fm" method="post" data-toggle="validator">
			<div class="form-group">

				<label for="inputName" class="control-label">标题</label>
				<div class="input-group">
					<span class="input-group-addon">☺</span> <input type="text"
						class="form-control" id="title" name="title">

				</div>

			</div>
			<div class="form-group">

				<label for="inputName" class="control-label">类型</label>
				<div class="input-group">
					<span class="input-group-addon">☺</span> <input type="text"
						class="form-control" id="type" name="type">

				</div>

			</div>
			<div class="form-group">
				<label for="inputTwitter" class="control-label">内容</label>
				<div class="input-group">

					<textarea style="height: 500px" name="pwd" class="form-control"
						id="content"></textarea>
				</div>
			</div>


			<div id="dlg-buttons">


				<!-- 未发布状态 -->
				<a id="fabu2" href="#" class="easyui-linkbutton" onclick="fabu2()"
					data-options="iconCls:'icon-save'">发布</a> <a id="tuihuifabu"
					href="#" class="easyui-linkbutton" onclick="chongxinshenhe()"
					data-options="iconCls:'icon-save'">重新审核</a> <a id="guanbi"
					href="#" class="easyui-linkbutton"
					onclick="$('#dlg').dialog('close')"
					data-options="iconCls:'icon-cancel' ">关闭</a>

			</div>
		</form>


	</div>

	<script type="text/javascript">
	
	$(function(){
		
		$('#dg').datagrid({
			title:"",
			fit:true,
			fitColumns:true,
			url:'../IT007/SCjRelease?option=<%=request.getParameter("option")%>',
								rownumbers : true,
								singleSelect : true,
								columns : [ [ {
									field : 'ck',
									title : "",
									checkbox : true
								}, {
									field : "NewsID",
									title : "ID",
									width : 200,
									align : "center",
									resizeable : true
								}, {
									field : "NewsTitle",
									title : "标题",
									width : 200,
									align : "center",
									resizeable : true
								}, {
									field : "Release",
									title : "是否发布",
									width : 200,
									align : "center",
									resizeable : true
								}, {
									field : "Newstype",
									title : "新闻类型",
									width : 200,
									align : "center",
									resizeable : true
								}, {
									field : "ReleaseTime",
									title : "发布时间",
									width : 200,
									align : "center",
									resizeable : true
								}, {
									field : "Depict",
									title : "备注",
									width : 200,
									align : "center",
									resizeable : true
								} ] ],
								pagination : true,
								pageList : [ 5, 10, 20, 30, 50, 100 ],
								pageSize : 20
							});

			//设置分页信息
			$("#dg").datagrid("getPager").pagination({

				displayMsg : "当前显示 {from}至{to} 条记录，共有{total} 条记录"
			});
			$('#dlg').dialog('close');

			if (
	<%=request.getParameter("option")%>
		== 2) {
				$('#cc').hide();

			} else if (
	<%=request.getParameter("option")%>
		== 1) {
				$('#aa').hide();

			}

		});
	
	
	
	</script>
</body>
</html>