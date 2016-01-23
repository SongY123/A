<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>登录</title>

        <!-- CSS -->
        <link rel="stylesheet" href="form-1/assets/css/Roboto.css">
        <link rel="stylesheet" href="form-1/assets/bootstrap/css/bootstrap.css">
        <link rel="stylesheet" href="form-1/assets/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="form-1/assets/css/form-elements.css">
        <link rel="stylesheet" href="form-1/assets/css/style.css">
		<link rel="stylesheet" href="form-1/assets/css/home-background.css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- Favicon and touch icons -->
        <link rel="shortcut icon" href="form-1/assets/ico/favicon.png">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="form-1/assets/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="form-1/assets/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="form-1/assets/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="form-1/assets/ico/apple-touch-icon-57-precomposed.png">
		<!-- Javascript -->
        <script src="form-1/assets/js/jquery-1.11.1.min.js"></script>
        <script src="form-1/assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="form-1/assets/js/jquery.backstretch.min.js"></script>
        <script src="form-1/assets/js/scripts.js"></script>

        <!--[if lt IE 10]>
            <script src="form-1/assets/js/placeholder.js"></script>
        <![endif]-->

		<script type="text/javascript">
		function getCookie(c_name)
		{
			if (document.cookie.length>0)
			{ 
				c_start=document.cookie.indexOf(c_name + "=")
				if (c_start!=-1)
				{ 
					c_start=c_start + c_name.length+1 
					c_end=document.cookie.indexOf(";",c_start)
					if (c_end==-1) c_end=document.cookie.length
					return unescape(document.cookie.substring(c_start,c_end))
				} 
			}
			return ""
		}
		
		function clearCookie(name) {  
		    setCookie(name, "", -1);  
		}  
		
		function setCookie(c_name,value,expiredays)
		{
			var exdate=new Date()
			exdate.setDate(exdate.getDate()+expiredays)
			document.cookie=c_name+ "=" +escape(value)+
			((expiredays==null) ? "" : "; expires="+exdate.toGMTString())
		}
		
		function checkCookie()
		{
			username=getCookie('username')
			password = getCookie('password')
			remember = getCookie('remember')
			if (username!=null && username!=""&&password!=null&&password!="")
		  	{
				$('#form-username').attr('value',username)
				$('#form-password').attr('value',password)
			}
			if(remember=='true')
				$('#form-remember').prop("checked",true)
		}
		function login(){
			if($('#form-remember').get(0).checked)
			{
				setCookie('username',$('#form-username').val(),30)
				setCookie('password',$('#form-password').val(),30)
				setCookie('remember','true',365)
			}
			else{
				clearCookie('username');
				clearCookie('password');
				clearCookie('remember');
			}
			//document.getElementById("form").submit()
		}
		</script>
    </head>

    <body class="home" onLoad="checkCookie()">
        <!-- Top content -->
        <div class="top-content">
        	
            <div class="inner-bg">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2 text">
                            <h1><strong>登录</strong></h1>
                            <div class="description">
                            	<p>
                            		
                            	</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 form-box">
                        	<div class="form-top">
                        		<div class="form-top-left">
                        			<h3>请先登录</h3>
                        		</div>
                        		<div class="form-top-right">
                        			<i class="fa fa-key"></i>
                        		</div>
                            </div>
                            <div class="form-bottom">
			                    <form role="form" action="login" method="post" class="login-form" id="form">
			                    	<div class="form-group">
			                    		<label class="sr-only" for="form-username">用户名</label>
			                        	<input type="text" name="username" placeholder="用户名" class="form-username form-control" id="form-username">
			                        </div>
			                        <div class="form-group">
			                        	<label class="sr-only" for="form-password">密码</label>
			                        	<input type="password" name="password" placeholder="密码" class="form-password form-control" id="form-password">
			                        </div>
			                        <div class='checkbox'>
										<label>
										<input type='checkbox' value='remember-me' id="form-remember">记住密码
										</label>
									</div>
			                        <button type="submit" class="btn" id="form-login">登录</button>
			                    </form>
		                    </div>
                        </div>
                    </div>
                </div>
            </div>
            
        </div>

    </body>

</html>