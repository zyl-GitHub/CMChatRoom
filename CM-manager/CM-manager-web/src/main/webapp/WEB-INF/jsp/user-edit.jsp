<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="userEditForm" class="userForm" method="post">
		<input type="hidden" name="id"/>
	    <table cellpadding="5">
	         <tr>
	            <td>账号:</td>
	            <td><input class="easyui-textbox" type="text" name="userId" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>密码:</td>
	            <td><input class="easyui-textbox" type="password" name="password" data-options="multiline:true,validType:'length[4,12]'" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>姓名:</td>
	            <td><input class="easyui-numberbox" type="text" name="name" data-options="min:1,max:99999999,precision:2,required:true" />
	            </td>
	        </tr>
	        <tr>
	            <td>昵称:</td>
	            <td><input class="easyui-numberbox" type="text" name="nickname" data-options="min:1,max:99999999,precision:0,required:true" /></td>
	        </tr>
	        <tr>
	            <td>性别:</td>
	            <td><input class="easyui-numberbox" type="text" name="sex" data-options="min:1,max:99999999,precision:0,required:true" /></td>
	        </tr>
	        <tr>
	            <td>年龄:</td>
	            <td><input class="easyui-numberbox" type="text" name="age" data-options="min:1,max:99999999,precision:0,required:true" /></td>
	        </tr>
	        <tr>
	            <td>手机号:</td>
	            <td><input class="easyui-numberbox" type="text" name="phone" data-options="min:1,max:99999999,precision:0,required:true" /></td>
	        </tr>
	        <tr>
	            <td>邮箱:</td>
	            <td><input class="easyui-numberbox" type="text" name="email" data-options="min:1,max:99999999,precision:0,required:true" /></td>
	        </tr>
	        <tr>
	            <td>身份:</td>
	            <td>
	            <select name="typeId">
						<option value="">请选择</option>
						<c:forEach items="${groupList}" var="dictInfo">
							<option value="${dictInfo.typeId}">${dictInfo.description}</option>
						</c:forEach>
				</select>
	            </td>
	        </tr>
	        <tr>
	            <td>个性签名:</td>
	            <td><input class="easyui-numberbox" type="text" name="sign" data-options="min:1,max:99999999,precision:0,required:true" /></td>
	        </tr>
	    </table>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	</div>
</div>
<script type="text/javascript">
	function submitForm(){
		if(!$('#userEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		$.post("/user/update",$("#userEditForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','修改商品成功!','info',function(){
					$("#userEditWindow").window('close');
					$("userList").datagrid("reload");
				});
			}
		});
	}
</script>
