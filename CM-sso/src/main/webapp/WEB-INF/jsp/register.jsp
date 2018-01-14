<!doctype html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>聊天室注册</title>

<link rel="stylesheet" type="text/css" href="/css/default.css">

<!--必要样式-->
<link rel="stylesheet" type="text/css" href="/css/stylesclone.css">
<script type='text/javascript'>
	var redirect = ${redirect};
	function register() {
	//登陆框消失
	$('.login').addClass('test');
	//
	setTimeout(function () {
		$('.login').addClass('testtwo');
	}, 300);
	//注册窗口显示
	setTimeout(function () {
		$('.authent').show().animate({ right: -320 }, {
			easing: 'easeOutQuint',
			duration: 600,
			queue: false
		});
		$('.authent').animate({ opacity: 1 }, {
			duration: 200,
			queue: false
		}).addClass('visible');
	}, 500);
	//注册完成
	var value = $('#registerItem').serialize();
	$.post('/sso/user/register',value,function(data){
		if(data.status == 500)
		{
			alert(data.msg);
			setTimeout(function () {
				$('.authent').show().animate({ right: 90 }, {
					easing: 'easeOutQuint',
					duration: 600,
					queue: false
				});
				$('.authent').animate({ opacity: 0 }, {
					duration: 200,
					queue: false
				}).addClass('visible');
				$('.login').removeClass('testtwo');
			}, 1000);
			//返回注册结果
			setTimeout(function () {
				$('.authent').fadeOut();
				$('.login').removeClass('test');
			}, 1000);
		}
		else if(data.status == 200)
		{
			alert(data.msg);
			window.location.href = '/sso/page/login?redirect='+redirect;
		}
	});
}

</script>
</head>
<body>

<div class='login'>
  <div class='login_title'>
	<span>账号注册</span>
  </div>
  <div class='login_fields'>
	<form id='registerItem'>
		<div class='login_fields__user'>
		  <div class='icon'>
			<img src='/img/user_icon_copy.png'>
		  </div>
		  <input placeholder='账号' type='text' name='userId' value='${user.userId}'>
			<div class='validation'>
			  <img src='/img/tick.png'>
			</div>
		  </input>
		</div>
		<div class='login_fields__password'>
		  <div class='icon'>
			<img src='/img/lock_icon_copy.png'>
		  </div>
		  <input placeholder='密码' type='password' name='password'  value='${user.password}'>
		  <div class='validation'>
			<img src='/img/tick.png'>
		  </div>
		</div>
		<div class='login_fields__password'>
		  <div class='icon'>
			<img src='/img/lock_icon_copy.png'>
		  </div>
		  <input placeholder='姓名' type='text' name='name'  value='${user.name}'>
		  <div class='validation'>
			<img src='/img/tick.png'>
		  </div>
		</div>
		<div class='login_fields__password'>
		  <div class='icon'>
			<img src='/img/lock_icon_copy.png'>
		  </div>
		  <input placeholder='昵称' type='text' name='nickname' value='${user.nickname}'>
		  <div class='validation'>
			<img src='/img/tick.png'>
		  </div>
		</div>
		<div class='login_fields__password'>
		  <div class='icon'>
			<img src='/img/lock_icon_copy.png'>
		  </div>
		  <input placeholder='性别' type='text' name='sex' value='${user.sex}'>
		  <div class='validation'>
			<img src='/img/tick.png'>
		  </div>
		</div>
		<div class='login_fields__password'>
		  <div class='icon'>
			<img src='/img/lock_icon_copy.png'>
		  </div>
		  <input placeholder='年龄' type='text' name='age'  value='${user.age}'>
		  <div class='validation'>
			<img src='/img/tick.png'>
		  </div>
		</div>
		<div class='login_fields__password'>
		  <div class='icon'>
			<img src='/img/lock_icon_copy.png'>
		  </div>
		  <input placeholder='手机号' type='text' name='phone'  value='${user.phone}'>
		  <div class='validation'>
			<img src='/img/tick.png'>
		  </div>
		</div>
		<div class='login_fields__password'>
		  <div class='icon'>
			<img src='/img/lock_icon_copy.png'>
		  </div>
		  <input placeholder='邮箱' type='text' name='email'  value='${user.email}'>
		  <div class='validation'>
			<img src='/img/tick.png'>
		  </div>
		</div>
		<div class='login_fields__password'>
		  <div class='icon'>
			<img src='/img/lock_icon_copy.png'>
		  </div>
		  <input placeholder='个性签名' type='text' name='sign'  value='${user.sign}'>
		  <div class='validation'>
			<img src='/img/tick.png'>
		  </div>
		</div>
	</form>
	<div class='login_fields__submit' align='center'>
	  <input type='button' onClick='register()' value='注册' >
	</div>
  </div>
</div>
<div class='authent'>
  <img src='/img/puff.svg'>
   <p>注册中...</p>
</div>

<script type="text/javascript" src='/js/stopExecutionOnTimeout.js?t=1'></script>
<script type="text/javascript" src="/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="/js/jquery-ui.min.js"></script>
<script type="text/javascript">
$('input[type="text"],input[type="password"]').focus(function () {
	$(this).prev().animate({ 'opacity': '1' }, 200);
});
$('input[type="text"],input[type="password"]').blur(function () {
	$(this).prev().animate({ 'opacity': '.5' }, 200);
});
$('input[type="text"],input[type="password"]').keyup(function () {
	if (!$(this).val() == '') {
		$(this).next().animate({
			'opacity': '1',
			'right': '30'
		}, 200);
	} else {
		$(this).next().animate({
			'opacity': '0',
			'right': '20'
		}, 200);
	}
});
var open = 0;
$('.tab').click(function () {
	$(this).fadeOut(200, function () {
		$(this).parent().animate({ 'left': '0' });
	});
});
</script>
</body>
</html>
