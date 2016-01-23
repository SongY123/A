<%@ page language="java" import="java.util.*,  java.sql.*" pageEncoding="UTF-8"%>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
String title = (String)session.getAttribute("title");
String context = (String)session.getAttribute("context");
String titles = (String)session.getAttribute("titles");
String contexts = (String)session.getAttribute("contexts");
%>  
<!DOCTYPE html>
<html lang="zh-CN">

    <head>
	
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>分词</title>

        <!-- CSS -->
       	<link href="<%=basePath%>form-1/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
       	<link href="<%=basePath%>form-1/assets/css/context.standalone.css" rel="stylesheet" type="text/css" />
       			
       	<!-- SCRIPT -->
		<script src="<%=basePath%>form-1/assets/js/jquery-2.1.4.min.js"></script>
  		<script src="<%=basePath%>form-1/assets/js/get.js"></script>
  		<script src="<%=basePath%>form-1/assets/js/jquery.query.js"></script>
  		<script src="<%=basePath%>form-1/assets/bootstrap/js/bootstrap.js"></script>
        <script src="<%=basePath%>form-1/assets/js/bootstrap-suggest.js"></script>
		<script type="text/javascript" src="<%=basePath%>form-1/assets/js/segmentation.js"></script>
		<script type="text/javascript" src="<%=basePath%>form-1/assets/js/context.js"></script>
		<script type="text/javascript" src="<%=basePath%>form-1/assets/js/post.js"></script>
        
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- Favicon and touch icons -->
        <link rel="shortcut icon" href="<%=basePath%>form-1/assets/ico/favicon.png">

		<script type="text/javascript" language="javascript">   
		function iFrameHeight() {   
			var ifm= document.getElementById("iframepage");   
			var subWeb = document.frames ? document.frames["iframepage"].document : ifm.contentDocument;   
			if(ifm != null && subWeb != null) {
   				ifm.height = subWeb.body.scrollHeight;
   				ifm.width = subWeb.body.scrollWidth;
			}   
		}   
		</script>
		<style type="text/css">
			a:link{text-decoration:none ;}
			a:visited {text-decoration:none;}
			a:hover {text-decoration:none;}
			a:active {text-decoration:none;} 
			h4
			{
				margin-bottom:20px;
			}
		</style>
	</head>

    <body>
    <%
		String username = (String)session.getAttribute("username");
    %>
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
        <li><a href="choosenews">新闻列表<span class="sr-only">(current)</span></a></li>
        <li><a href="statistics">统计</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Separated link</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">One more separated link</a></li>
          </ul>
        </li>
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

	<div>
		<!-- Title on the left -->
		<div class="col-xs-4">
			<%out.print("<h4>"+title+"</h4>");%>
		</div>
		<%
			//out.print("<iframe class = \"col-xs-3\" src=\""+basePath+"jsp/mark/news.jsp?title="+title+"&body="+context +'\"'+"  id=\"iframepage\" scrolling=\"no\" marginheight=\"0\" marginwidth=\"0\" onLoad=\"iFrameHeight()\"></iframe>");
    	%>
		
		<!-- Editiable page on the right -->
		<div class="col-xs-8">
		<div>
 	 	<ul class="pagination pagination-lg">
    		<li class="active"><a href = "javascript:void(0)">分词</a></li>
    		<li><a href="javascript:void(0);" id ="nominal">词性标注</a></li>
    		<li><a href="javascript:void(0);" id ="entity">命名实体识别</a></li>
   			<li><a href="javascript:void(0);" id ="incident">事件抽取</a></li>
  		</ul>
  		<ul class="pagination pagination-lg">
  		<li><a href="javascript:void(0);" id ="nextnews">下一条</a></li>
  		</ul>
  		</div>
  		<div id="contenttext">
  		<h4><%
	  		String[]result = titles.split("\\s+");
  			boolean tag = true;
  			for(String str:result)
  			{
  				if(tag)
  					for(int i = 0;i<str.length();i++){
  						out.print("<a style=\"color:blue;\">"+str.charAt(i)+"</a>");
  						tag = false;	
  					}
  				else
  					for(int i = 0;i<str.length();i++){
	  					out.print("<a style=\"color:red;\">"+str.charAt(i)+"</a>");
	  					tag = true;		
  					}
  			}
  			%>
  		</h4>
		<%
		if(context!=null)
		{
			String[]resultcontext = contexts.split("\\s+");
  			boolean tagcontext = true;
  			for(String str:result)
  			{
  				if(tag)
  					for(int i = 0;i<str.length();i++){
  						out.print("<a style=\"color:blue;\">"+str.charAt(i)+"</a>");
  						tagcontext = false;	
  					}
  				else
  					for(int i = 0;i<str.length();i++){
	  					out.print("<a style=\"color:red;\">"+str.charAt(i)+"</a>");
	  					tagcontext = true;		
  					}
  			}
		}
		%>
		<button type="button" class="btn btn-primary">提交修改</button>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<button type="button" class="btn btn-primary">确认修改</button>
		</div>
        </div>
	</div>
	</body>
</html>