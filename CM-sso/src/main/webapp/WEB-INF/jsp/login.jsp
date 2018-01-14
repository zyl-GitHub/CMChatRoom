<!doctype html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>聊天室登陆</title>

<link rel="stylesheet" type="text/css" href="/css/default.css">

<!--必要样式-->
<link rel="stylesheet" type="text/css" href="/css/styles.css">
<script type='text/javascript'>
	var url = '${redirect}';//'www.baidu.com';//
	function submit() {
	//登陆框消失
	$('.login').addClass('test');
	//
	setTimeout(function () {
		$('.login').addClass('testtwo');
	}, 300);
	//认证窗口显示
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
	//认证完成
	var value = $('#loginItem').serialize();
	$.post('/sso/user/login',value,function(data){
		if(data.status == 500)
		{
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
			//返回认证结果
			setTimeout(function () {
				$('.authent').fadeOut();
				$('.login').removeClass('test');
				$('#info').attr('color','red');
				$('#info').text('认证失败');
			}, 1000);
		}
		else if(data.status == 200)
		{
			window.location.href = url+'?json='+data;
		}
	});
}
function register(){
	window.location.href = '/sso/page/register?redirect='+url;
}
function forget(){

}
</script>
</head>
<body>

<div class='login'>
  <div class='login_title'>
	<span>账号登录</span>
  </div>
  <div class='login_fields'>
	<form id='loginItem'>
		<div class='login_fields__user'>
		  <div class='icon'>
			<img src='/img/user_icon_copy.png'>
		  </div>
		  <input placeholder='账号' type='text' name='userId'>
			<div class='validation'>
			  <img src='/img/tick.png'>
			</div>
		  </input>
		</div>
		<div class='login_fields__password'>
		  <div class='icon'>
			<img src='/img/lock_icon_copy.png'>
		  </div>
		  <input placeholder='密码' type='password' name='password'>
		  <div class='validation'>
			<img src='/img/tick.png'>
		  </div>
		</div>
	</form>
	<div class='login_fields__submit'>
	  <input type='button' onClick='submit()' value='登录' >
	  <div class='forgot'>
	  	<a href='javascript:register()' style='font-size:15px'>注册</a>
		<a href='javascript:forget()' style='font-size:15px'>忘记密码</a>
	  </div>
	    <p id='info' align='center' style='font-size:15px'>
			请认证账号
		</p>
	</div>
  </div>
</div>
<div class='authent'>
  <img src='/img/puff.svg'>
  <p>认证中...</p>
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
