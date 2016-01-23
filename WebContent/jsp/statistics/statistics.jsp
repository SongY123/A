<%@ page language="java" import="java.util.*,  java.sql.*" pageEncoding="UTF-8"%>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
String username = (String)session.getAttribute("username");
%>  
<!DOCTYPE html>
<html lang="zh-CN">

    <head>
	
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>选择新闻</title>

        <!-- CSS -->
       	
       	<link href="<%=basePath%>form-1/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
       	<link href="<%=basePath%>form-1/assets/css/context.standalone.css" rel="stylesheet" type="text/css" />
       			
       	<!-- SCRIPT -->
		<script src="<%=basePath%>form-1/assets/js/jquery-2.1.4.min.js"></script>
  		<script src="<%=basePath%>form-1/assets/bootstrap/js/bootstrap.min.js"></script>
		<script src="<%=basePath%>form-1/assets/Chart/Chart.js"></script>
        
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- Favicon and touch icons -->
        <link rel="shortcut icon" href="<%=basePath%>form-1/assets/ico/favicon.png">
	</head>

    <body>
	<nav class="navbar navbar-default">
  		<div class="container-fluid">
    	<!-- Brand and toggle get grouped for better mobile display -->
    		<div class="navbar-header">
      			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        			<span class="sr-only">Toggle navigation</span>
        			<span class="icon-bar"></span>
       			 	<span class="icon-bar"></span>
        			<span class="icon-bar"></span>
      			</button>
      		<a class="navbar-brand" href="#"><img src="<%=basePath%>form-1/assets/ico/favicon.png"></img></a>
    		</div>

    	<!-- Collect the nav links, forms, and other content for toggling -->
    	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      		<ul class="nav navbar-nav">
      			<li class="dropdown">
          			<a href="choosenews" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">新闻列表 <span class="caret"></span></a>
          			<ul class="dropdown-menu">
          				<li><a href="javascript:void(0);" id="getallnews">所有</a></li>
            			<li><a href="javascript:void(0);" id="getunchangednews">未修改</a></li>
            			<li><a href="javascript:void(0);" id="getunconfirmednews">未确认</a></li>
            			<li><a href="javascript:void(0);" id="getconfirmednews">已确认</a></li>
          			</ul>
        		</li>
        		<li><a href="statistics">统计</a></li>
     		</ul>
     		
      		<form class="navbar-form navbar-left" role="search">
        		<div class="form-group">
          			<input type="text" class="form-control" placeholder="Search">
        		</div>
        		<button type="submit" class="btn btn-primary">Submit</button>
      		</form>
      		
     		<ul class="nav navbar-nav navbar-right">
        		<li class="dropdown">
          			<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><%out.print(username); %> <span class="caret"></span></a>
          			<ul class="dropdown-menu">
            			<li><a href="#">任务</a></li>
           				<li><a href="#">权限</a></li>
            			<li role="separator" class="divider"></li>
            			<li><a href="logout">登出</a></li>
          			</ul>
        		</li>
      		</ul>
    	</div><!-- /.navbar-collapse -->
  		</div><!-- /.container-fluid -->
	</nav>


	<div class="container">
		<canvas id="myChart" width="600" height="450"></canvas>
		
	</div>
	<script type="text/javascript">
	$(document).ready(function(){
		$('#getallnews').on('click',function(){
			choosenewsGET('choosenews','fenghuang', 1, 0);
		});
		$('#getunchangednews').on('click',function(){
			choosenewsGET('choosenews','fenghuang', 1, 1);
		});
		$('#getunconfirmednews').on('click',function(){
			choosenewsGET('choosenews','fenghuang', 1, 2);
		});
		$('#getconfirmednews').on('click',function(){
			choosenewsGET('choosenews','fenghuang', 1, 3);
		});
	});
	function choosenewsGET(action, dbname, beginid, state){
		document.write('<form name="myForm"><input type="hidden" name="dbname"/><input type="hidden" name="beginid"/><input type="hidden" name="state"/></form>');  
		    var myForm=document.forms['myForm'];  
		    myForm.action=action;  
		    myForm.method='GET';  
		    myForm.dbname.value=dbname;
		    myForm.beginid.value=beginid;
		    myForm.state.value=state;
		    myForm.submit();
	}
	</script>
	
	<script type="text/javascript">
		var ctx = document.getElementById("myChart").getContext("2d");
		<%String data = (String)session.getAttribute("data");%>
		var data = {
				labels : ["凤凰","环球中国","新华","新华国际","新浪","环球国际"],
				datasets : [
					{
						fillColor : "rgba(220,220,220,0.5)",
						strokeColor : "rgba(220,220,220,1)",
						pointColor : "rgba(220,220,220,1)",
						pointStrokeColor : "#fff",
						data : [<%out.print(data);%>]
					}
				]
			};
		var myNewChart = new Chart(ctx).Bar(data);
		
	</script>
	</body>
</html>