<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>聊天室</title>
<link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.4.1/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.4.1/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="/css/style.css" />
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script type="text/javascript" src="/js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<!-- 发送内容 -->
<script type="text/javascript">
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
</script>
<!-- 检查用户登陆 -->
<script type="text/javascript">
	//检查时是否在线
	function check(){
		var userId = ${user.userId};
		$.post("/chat/user/check?userId="+userId,function(data){
			if(data == 200){
				// 提示用户下线了
				alert("用户已经被踢下线了!");
				// 回到登录页面!
				window.location.href="/chat/user/login";
			}
		});
	}
	//退出
	function exit(){
		var userId = ${user.userId};
		$.post("/chat/user/loginout?userId="+userId,function(data){
			if(data.status  == 200)
			{
				alert("欢迎您下次光临！");
				window.location.href="/chat/user/login";
			}
		});
	}
	window.onbeforeunload=onclose;
	function onclose()
	{
		exit();
	}
</script>
<!--消息显示区 -->
<script type="text/javascript">
var userIdc;
var sysBBS = "<span style='font-size:14px; line-height:30px;'>欢迎光临心之语聊天室，请遵守聊天室规则，不要使用不文明用语。</span><br><span style='line-height:22px;'>";var sysBBS = "<span style='font-size:14px; line-height:30px;'>欢迎光临心之语聊天室，请遵守聊天室规则，不要使用不文明用语。</span><br><span style='line-height:22px;'>";
	//更新信息
	//window.setInterval("showAllContent();",1000);
	//window.setInterval("showPersonContent();",1000);
	//window.setInterval("showOnLine();",10000);
	//window.setInterval("check();",1000);

	$(function(){
		showAllContent();
		showPersonContent();
		check();
	});
	
	// 显示群聊聊天的内容
	function showAllContent(){
		$.post("/chat/content/showAllMessage?"+new Date().getTime(),function(data){
			if(data.status == 200)
			{
				//name+*@*$+msg
				var message = data.data;
				var userId = ${user.userId};
				var name = message.split('*@*&')[0];
				var msg = message.split('*@*&')[1];			
				var tabs = $("#tabs");
				var tab = tabs.tabs("getTab",name);
				if(tab){
					tabs.tabs("select",'群聊');
				}else{
					tabs.tabs('add',{
					    title:name,
					    id:userId,
					    content:"<table><tr><td width=600px height=290px><div id="+userId+"content width=600px height=290px overflow-y:auto></div></td></tr></table>",
					    closable:true,
					    bodyCls:"content"
					});
				}
				var history = $("#"+userId+"content").text();
				$("#"+userId+"content").html(history+msg);
			}
		});
	}
	//显示私聊
	function showPersonContent(){
		$.post("/chat/content/showPersonMessage?"+new Date().getTime(),function(data){
			if(data.status == 200)
			{
				//userId+*@*$+name+*@*$+msg
				var message = data.data;
				var userId = message.split('*@*&')[0];
				var name = message.split('*@*&')[1];
				var msg = message.split('*@*&')[2];
				
				var tabs = $("#tabs");
				var tab = tabs.tabs("getTab",name);
				if(tab){
					tabs.tabs("select",name);
				}else{
					tabs.tabs('add',{
					    title:name,
					    id:userId,
					    content:"<table><tr><td width=600px height=290px><div id="+userId+"content width=600px height=290px overflow-y:auto></div></td></tr></table>",
					    closable:true,
					    bodyCls:"content"
					});
				}
				var history = $("#"+userId+"content").text();
				$("#"+userId+"content").html(history+msg);
			}
		});
	}
	//点击发送消息按钮
	function send(){
		itemAddEditor.sync();
		//拼接message  未完成
		if($('#desc').val()==""){
			return false;
		}
		var value = $('#desc').val();
		itemAddEditor.html('');
		var tab = $('#tabs').tabs('getSelected'); 
		var userIdc = tab.panel('options').id;
		var userIdb = '${user.userId}';
		//群聊
		if(userIdc == '${user.userId}')
		{
			//'0'+useId(发送方id)+message 发送到后端
			message = '0'+"*@*&"+userIdc+"*@*&"+value;
		}
		//私聊
		else
		{
			//1+userId(发送方Id)+userId(接收方Id)+message 发送到后端
			message = '1'+"*@*&"+userIdb+"*@*"+${user.name}+"*@*&"+userIdc+"*@*&"+value;
		}
		alert(message);
		// $("#form1").serialize():让表单中所有的元素都提交.
		// jquery提交数据.{id:1,name:aa,age:25}
		$.post("/chat/content/sendMessage?"+new Date().getTime(),{message:message},function(data){
			//$("#content").html(sysBBS+data+"</span>");
		});
	}
	
	
	function checkScrollScreen(){
		if(!$("#scrollScreen").attr("checked")){
	    	$("#content").css("overflow","scroll");
	    }else{
	    	$("#content").css("overflow","hidden");
	        //当聊天信息超过一屏时，设置最先发送的聊天信息不显示
	        //alert($("#content").height());
	        $("#content").scrollTop($("#content").height()*2);
	    }
	    setTimeout('checkScrollScreen()',500);
	}
</script>
<!-- 人员列表 -->
<script type="text/javascript">
// 显示在线人员列表
function showOnLine(){
	// 异步发送请求 获取在线人员列表
	// Jquery发送异步请求
		$.post("/chat/online/showOnline?"+new Date().getTime(),function(data){
		$("#online").html(data);
	});
	$("#onlineUserList").tree('reload');
}
$(function(){
	showOnLine();
	check();
	$("#onlineUserList").tree({
		url : '/chat/online/activerlist',
		animate: true,
		method : "GET",
		onContextMenu: function(e,node){
            e.preventDefault();
            $(this).tree('select',node.target);
            $('#onlineUserMenu').menu('show',{
                left: e.pageX,
                top: e.pageY
            });
        },
        onClick: function(node){
			if($('#onlineUserMenu').tree("isLeaf",node.target)){
				var tabs = $("#tabs");
				var tab = tabs.tabs("getTab",node.text);
				if(tab){
					tabs.tabs("select",node.text);
				}else if(node.id != '${user.userId}'){
					tabs.tabs('add',{
					    title:node.text,
					    id:node.id,
					    content:"<table><tr><td width=600px height=290px><div id="+node.id+"content width=600px height=290px overflow-y:auto></div></td></tr></table>",
					    closable:true,
					    bodyCls:"content"
					});
				}
			}
		}
	});
});
//添加好友 踢人 查看有户信息
function menuHandler(item){
	var tree = $("#onlineUserList");
	var node = tree.tree("getSelected");
	if(item.name === "add"){
		//添加好友
		var userIdb = ${user.userId};
		$.post("/chat/user/addFriend",{userIdb:userIdb,userIdc:node.id},function(data){
			alert(data);
		});	
	}else if(item.name === "lookInfo"){
		//弹出该用户的信息框
		$.post("/chat/user/userInfo",{userId:node.id},function(data){
			alert(data);
		});	
	}else if(item.name === "delete"){
		var userIdb = ${user.userId};
		$.messager.confirm('确认','确定下线名为 '+node.text+'吗？',function(r){
			if(r){
				$.post("/chat/user/delete",{userIdb:userIdb,userIdc:node.id},function(){
					tree.tree("remove",node.target);
				});	
			}
		});
	}
}
</script>
<script type="text/javascript">
	//下载
	function download(link)
	{
		$.post(link);
	}
	//搜寻文件
	function query()
	{
		var querystring = $('query').val();
		$.post('/search',{querystring:querystring});
	}
</script>
</head>
<body>

	<table width="778" height="150" border="0" align="center"
		cellpadding="0" cellspacing="0" background="../images/top.jpg">
		<tr>
			<td>&nbsp;</td>
		</tr>
	</table>
	<table width="778" height="276" border="0" align="center"
		cellpadding="0" cellspacing="0">
		<tr>
			<td width="165" valign="top" bgcolor="#f6fded" style="padding:5px">
				在线人员列表
				<div id="online" width="165" valign="top" bgcolor="#f6fded">
				</div>
				<div>
					 <ul id="onlineUserList" class="easyui-tree">
				    </ul>
				</div>
				<div id="onlineUserMenu" class="easyui-menu" style="width:120px;" data-options="onClick:menuHandler">
				    <div data-options="iconCls:'icon-add',name:'add'">添加好友</div>
				    <div data-options="iconCls:'icon-remove',name:'lookInfo'">查看用户信息</div>
				    <c:if test="${user.typeId == 0||user.typeId == 1}">
				   		<div data-options="iconCls:'icon-remove',name:'delete'">踢人</div>
				   	</c:if>
				</div>
			</td>
			<td width="613" height="200px" valign="top"
				background="../images/main_bj.jpg" bgcolor="#FFFFFF"
				style="padding:5px; ">
				
				 <div data-options="region:'center',title:''" style="width:613px">
    				<div id="tabs" class="easyui-tabs">
		   				 <div title="群聊" style="padding:20px;" id="${user.userId}">
		   				 	<table>
		   				 		<tr>
		   				 			<td width=600px height=250px>
		   				 				<div width=600px height=250px id="${user.userId}content" overflow-y:auto>
		   				 					
		   				 				</div>
		   				 			</td>
		   				 		</tr>
		   				 	</table>
		        		 </div>
					</div>
    			</div>
				</td>

		</tr>
	</table>
	<form id="form1" class="form1" method="post">
	    <table cellpadding="5" align='center'>
	    	<tr>
				<td><input name="from" type="hidden" value="${user.userId}"></td>
			</tr>
	        <tr>
	            <td>
	                <textarea style="width:800px;height:300px;visibility:hidden;" name="desc" id='desc'></textarea>
	                <input name="Submit2" type="button" class="btn_grey" value="发送" onClick="send()()">
				    <input name="button_exit" type="button" class="btn_grey" value="退出聊天室" onClick="exit()()">
	            	 <a href="javascript:void(0)" class="easyui-linkbutton fileUpload">上传文件</a>
	                 <input type="hidden" name="image"/>
	            </td>
	        </tr>
		</table>
	</form>
	<div>
		<input type='text' id="query"/>
		<input type='button' onclick="search()"/>
	</div>
	<table align="center" border='1px'>
		<tr>
			<th width="200px">文件名</th>
			<th width="300px">文件下载地址</th>
			<th>下载</th>
			<th>文件下载次数</th>
		</tr>
		<c:forEach items="${fileList}" var='file'>
			<tr>
				<td align="center">${file.name}</td>
				<td align="center">${file.link}</td>
				<td><a href="javascript:download('/chat/download?link=${file.link}&id=${file.id}')">下载</a></td>
				<td>${file.count}</td>
			</tr>
		</c:forEach>
	</table>
	<div  align="center" class="word_dark">
			CopyRights reserved 2018
	</div>
</body>	
</html>
