<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<title>聊天室文件下载</title>
<link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.4.1/themes/metro/easyui.css">
<link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.4.1/themes/icon.css">
<link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.4.1/themes/color.css">
<link rel="stylesheet" type="text/css" href="/css/style.css" />
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script type="text/javascript" src="/js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script>
var itemAddEditor ;
//页面初始化完毕后执行此方法
$(function(){
	//创建富文本编辑器
	itemAddEditor = TAOTAO.createEditor("#form1 [name=desc]");
	//初始化类目选择和图片上传器
	TAOTAO.init({fun:function(node){
		//根据商品的分类id取商品 的规格模板，生成规格信息。
		TAOTAO.changeItemParam(node, "form1");
	}});
});
function getSelectionsIds(){
	var itemList = $("#itemList");
	var sels = itemList.datagrid("getSelections");
	var ids = [];
	for(var i in sels){
		ids.push(sels[i].id);
	}
	ids = ids.join(",");
	return ids;
}
</script>
</head>
<body>
<!-- html的静态布局 -->
  <form id="userqueryForm">
	<!-- 查询条件 -->
	<TABLE class="table_search">
		<TBODY>
			<TR>
				<TD class="left">文件名称：</td>
				<td><INPUT type="text" name="name" /></TD>
				<td><a id="btn" href="#" onclick="queryfile()"
					class="easyui-linkbutton" iconCls='icon-search'>查询</a></td>
			</TR>


		</TBODY>
	</TABLE>
 <a href="javascript:void(0)" class="easyui-linkbutton fileUpload">上传文件</a>
	<!-- 查询列表 -->
	<table class="easyui-datagrid" id="itemList" title="商品列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/file/queryfile',method:'get',pageSize:30">
	    <thead>
	        <tr>
	            <th data-options="field:'name',width:200">文件名称</th>
	            <th data-options="field:'link',width:600">文件下载地址</th>
	            <th data-options="
	            field:'id',width:100,formatter: function (value, row, index) {
     
      			 return '<a href=/file/download?link='+row.link+'&filename='+row.name+'&id='+row.id+'>下载</a>';
   				 }"></th>
	            <th data-options="field:'count',width:70,align:'right'">下载次数</th>
	        </tr>
	    </thead>
	</table>
</form>
</body>
	<script type="text/javascript">
	//查询方法
	function queryfile(){
		//datagrid的方法load方法要求传入json数据，最终将 json转成key/value数据传入action
		//将form表单数据提取出来，组成一个json
		var formdata = $("#userqueryForm").serializeJson();
		$('#itemList').datagrid('load',formdata);
	}
	</script>
</html>