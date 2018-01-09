<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<!-- html的静态布局 -->
  <form id="userqueryForm">
	<!-- 查询条件 -->
	<TABLE class="table_search">
		<TBODY>
			<TR>
				<TD class="left">用户账号：</td>
				<td><INPUT type="text" name="userId" /></TD>
				<TD class="left">用户名称：</TD>
				<td><INPUT type="text" name="name" /></TD>

				<TD class="left">昵称：</TD>
				<td><INPUT type="text" name="nickname" /></TD>
				<TD class="left">用户类型：</TD>
				<td><select name="typeId">
						<option value="">请选择</option>
						<c:forEach items="${groupList}" var="dictInfo">
							<option value="${dictInfo.typeId}">${dictInfo.description}</option>
						</c:forEach>
				</select></TD>
				<td><a id="btn" href="#" onclick="queryuser()"
					class="easyui-linkbutton" iconCls='icon-search'>查询</a></td>
			</TR>


		</TBODY>
	</TABLE>

	<!-- 查询列表 -->
	<TABLE border=0 cellSpacing=0 cellPadding=0 width="99%" align=center>
		<TBODY>
			<TR>
				<TD>
					<table id="userList"></table>
				</TD>
			</TR>
		</TBODY>
	</TABLE>
</form>
<div id="userEditWindow" class="easyui-window" title="编辑人员信息" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/user/page/user-edit'" style="width:80%;height:80%;padding:10px;">
</div>
<script>

    function getSelectionsIds(){
    	var userList = $("#userList");
    	var sels = userList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(",");
    	return ids;
    }
   
  //datagrid列定义
	var columns_v = [ [ {
		field : 'userId',//对应json中的key
		title : '账号',
		width : 120
	}, {
		field : 'name',//对应json中的key
		title : '姓名 ',
		width : 180
	}, {
		field : 'nickname',//对应json中的key
		title : '昵称',
		width : 120,
	}, {
		field : 'sex',//对应json中的key
		title : '性别',
		width : 120
	},{
		field : 'typeId',//对应json中的key
		title : '身份',
		width : 120,
		formatter:function(value, row, index){
			if(value == '0')
				return 'boss';
			else if(value == '1')
				return '管理员';
			else if(value == '2')
				return '贫民';
		}
	}, {
		field : 'vip',//对应json中的key
		title : 'vip',
		width : 120,
		formatter:function(value, row, index){
			if(value == '0')
				return 'vip';
			else if(value == '1')
				return '非vip';
		}
	}, {
		field : 'age',//对应json中的key
		title : '年龄',
		width : 120
	},{
		field:'phone',
		title:'手机号',
		width:120,
	},{
		field:'email',
		title:'邮箱',
		width:120,
	},{
		field:'created',
		title:'建号时间',
		width:120,
	}] ];

	//定义 datagird工具
    var toolbar_v = [{
        text:'新增',
        iconCls:'icon-add',
        handler:function(){
        	$(".tree-title:contains('新增人员')").parent().click();
        }
    },{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一个人员才能编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一个人员!');
        		return ;
        	}
        	
        	$("#userEditWindow").window({
        		onLoad :function(){
        			//回显数据
        			var data = $("#userList").datagrid("getSelections")[0];
        			data.priceView = TAOTAO.formatPrice(data.price);
        			$("#userEditForm").form("load",data);
        		}
        	}).window("open");
        }
    },{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中商品!');
        		return ;
        	}
        	$.messager.confirm('确认','确定删除ID为 '+ids+' 的商品吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("/user/delete",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','删除商品成功!',undefined,function(){
            					$("#userList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    }];
	//加载datagrid

	$(function() {
		$('#"userList"').datagrid({
			title : '用户查询',//数据列表标题
			nowrap : true,//单元格中的数据不换行，如果为true表示不换行，不换行情况下数据加载性能高，如果为false就是换行，换行数据加载性能不高
			striped : true,//条纹显示效果
			url : '/user/queryuser',//加载数据的连接，引连接请求过来是json数据
			idField : 'userId',//此字段很重要，数据结果集的唯一约束(重要)，如果写错影响 获取当前选中行的方法执行
			loadMsg : '',
			columns : columns_v,
			pagination : true,//是否显示分页
			rownumbers : true,//是否显示行号
			pageList:[15,30,50],
			toolbar : toolbar_v
		});
	});
	
	//查询方法
	function queryuser(){
		//datagrid的方法load方法要求传入json数据，最终将 json转成key/value数据传入action
		//将form表单数据提取出来，组成一个json
		var formdata = $("#userqueryForm").serializeJson();
		$('#userList').datagrid('load',formdata);
	}
</script>