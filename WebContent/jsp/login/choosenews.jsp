<%@ page language="java" import="java.util.*,  java.sql.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="/struts-tags" prefix="s" %> 
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
  		<script src="<%=basePath%>form-1/assets/js/jquery.query.js"></script>
  		<script src="<%=basePath%>form-1/assets/js/getnews.js"></script>
  		<script src="<%=basePath%>form-1/assets/bootstrap/js/bootstrap.min.js"></script>
		
        
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- Favicon and touch icons -->
        <link rel="shortcut icon" href="<%=basePath%>form-1/assets/ico/favicon.png">
        <style>
        .uncommit{
			color: #a6a6d2;
			font-weight: bold;
		}
		.commited{
			color:#9F4D95;
			font-weight: bold;
		}
		.irrelevant{
			color:#F88158;
			font-weight: bold;
		}
		.glyphicon-trash:hover{
			color:blue;
			cursor:pointer;
		}
		</style>
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
            			<li><a href="javascript:void(0);" id="getunconfirmednews">已提交</a></li>
            			<li><a href="javascript:void(0);" id="getconfirmednews">不相关</a></li>
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
          			<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" id="username"><%out.print(username); %> <span class="caret"></span></a>
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
		<div>
			<ul id="whichnews" class="pagination pagination-lg">
    			<s:if test='%{dbname=="fenghuang"}'><li class="active" id = "fenghuang"></s:if><s:else><li id = "fenghuang"></s:else><a href="javascript:void(0);" onclick="Go(this);">凤凰</a></li>
    			<s:if test='%{dbname=="huanqiu_china"}'><li class="active" id = "huanqiu_china"></s:if><s:else><li id = "huanqiu_china"></s:else><a href="javascript:void(0);" onclick="Go(this);">环球中国</a></li>
    			<s:if test='%{dbname=="xinhua"}'><li class="active" id = "xinhua"></s:if><s:else><li id = "xinhua"></s:else><a href="javascript:void(0);" onclick="Go(this);">新华</a></li>
   				<s:if test='%{dbname=="xinhua_world"}'><li class="active" id = "xinhua_world"></s:if><s:else><li id = "xinhua_world"></s:else><a href="javascript:void(0);" onclick="Go(this);">新华国际</a></li>
   				<s:if test='%{dbname=="xinlang"}'><li class="active" id = "xinlang"></s:if><s:else><li id = "xinlang"></s:else><a href="javascript:void(0);" onclick="Go(this);">新浪</a></li>
   				<s:if test='%{dbname=="huanqiu_world"}'><li class="active" id = "huanqiu_world"></s:if><s:else><li id = "huanqiu_world"></s:else><a href="javascript:void(0);" onclick="Go(this);">环球国际</a></li>
  			</ul>
  		</div>
  		<div id="newslist">
  		<!--  <iframe src="http://www.ifeng.com"  name="iframepage" id = "iframepage" width="100%"  height="500" marginheight="0" marginwidth="0"></iframe>-->
  			<!-- news list -->
  			<div>
  				<ul class="list-group">
  					<s:iterator id="newsList" value="list">
  						<li class="list-group-item">
  							<div class="row"><s:if test="%{#newsList.state==0}"><span class="col-xs-1"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span>&nbsp;&nbsp;<span class="uncommit">未修改</span></span></s:if><s:elseif test="%{#newsList.state==1}"><span class="col-xs-1"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span>&nbsp;&nbsp;<span class="commited">已提交</span></span></s:elseif><s:elseif test="%{#newsList.state==2}"><span class="col-xs-1"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span>&nbsp;&nbsp;<span class="irrelevant">不相关</span></span></s:elseif><label style="display:none"><s:property value="#newsList.id" /></label><a class="col-xs-6" href="javascript:void(0);" style="color:green;" onclick="findnews(this);"><s:property value="#newsList.title"/></a><label id="time0" class="col-xs-2"><s:date name="#newsList.updateTime"  format="yyyy-MM-dd HH:mm:ss"/></label><label id="time0" class="col-xs-2"><s:date name="#newsList.saveTime"  format="yyyy-MM-dd HH:mm:ss"/></label></div></li>
  					</s:iterator>
				</ul>
  			</div>
  			<!-- 
  			<ul class="list-group" style="display:none">
  				<s:iterator id="newsList" value="list">
  					<li class="list-group-item" id="id0"><s:property value="#newsList.id" /></li>
  				</s:iterator>
			</ul>
			-->
  			<ul class="pagination" name="pages">
    			<li id="previous">
      				<a href="javascript:void(0);" aria-label="Previous">
        				<span aria-hidden="false">上一页</span>
      				</a>
    			</li>
    			<s:if test="%{beginid>=5}"><li class="page"><a href="javascript:void(0);"><s:property value="%{beginid-4}"/></a></li></s:if>
    			<s:if test="%{beginid>=4}"><li class="page"><a href="javascript:void(0);"><s:property value="%{beginid-3}"/></a></li></s:if>
    			<s:if test="%{beginid>=3}"><li class="page"><a href="javascript:void(0);"><s:property value="%{beginid-2}"/></a></li></s:if>
    			<s:if test="%{beginid>=2}"><li class="page"><a href="javascript:void(0);"><s:property value="%{beginid-1}"/></a></li></s:if>
    			<s:if test="%{0<pageCountActual}"><li class="active"><a href="javascript:void(0);"><s:property value="beginid"/></a></li></s:if>
    			<s:if test="%{1<pageCountActual}"><li class="page"><a href="javascript:void(0);"><s:property value="%{beginid+1}"/></a></li></s:if>
    			<s:if test="%{2<pageCountActual}"><li class="page"><a href="javascript:void(0);"><s:property value="%{beginid+2}"/></a></li></s:if>
    			<s:if test="%{3<pageCountActual}"><li class="page"><a href="javascript:void(0);"><s:property value="%{beginid+3}"/></a></li></s:if>
    			<s:if test="%{4<pageCountActual}"><li class="page"><a href="javascript:void(0);"><s:property value="%{beginid+4}"/></a></li></s:if>
    			<s:if test="%{5<pageCountActual}"><li class="page"><a href="javascript:void(0);"><s:property value="%{beginid+5}"/></a></li></s:if>
    			<s:if test="%{6<pageCountActual}"><s:if test="%{beginid<5}"><li class="page"><a href="javascript:void(0);"><s:property value="%{beginid+6}"/></a></li></s:if></s:if>
    			<s:if test="%{7<pageCountActual}"><s:if test="%{beginid<4}"><li class="page"><a href="javascript:void(0);"><s:property value="%{beginid+7}"/></a></li></s:if></s:if>
    			<s:if test="%{8<pageCountActual}"><s:if test="%{beginid<3}"><li class="page"><a href="javascript:void(0);"><s:property value="%{beginid+8}"/></a></li></s:if></s:if>
    			<s:if test="%{9<pageCountActual}"><s:if test="%{beginid<2}"><li class="page"><a href="javascript:void(0);"><s:property value="%{beginid+9}"/></a></li></s:if></s:if>
    			<li id="next">
      				<a href="javascript:void(0);" aria-label="Next">
        				<span aria-hidden="true">下一页</span>
      				</a>
    			</li>
  			</ul>
  			<label style="display:none"></label>
		</div>
	</div>
	<script type="text/javascript">
	function findnews(obj){
		var newsid = obj.previousSibling.innerText;
		if($('#fenghuang').hasClass("active"))
			dbname = "fenghuang";
		else if($('#huanqiu_china').hasClass("active"))
			dbname = "huanqiu_china";
		else if($('#xinhua').hasClass("active"))
			dbname = "xinhua";
		else if($('#xinhua_world').hasClass("active"))
			dbname = "xinhua_world";
		else if($('#xinlang').hasClass("active"))
			dbname = "xinlang";
		else if($('#huanqiu_world').hasClass("active"))
			dbname = "huanqiu_world";
		document.write('<form name="myForm"><input type="hidden" name="dbname"><input type="hidden" name="newsid"><input type="hidden" name="state"></form>');  
  	    var myForm=document.forms['myForm'];  
  	    myForm.action="incident";  
  	    myForm.method='GET';  
  	    myForm.dbname.value=dbname;
  	    myForm.newsid.value=newsid;
  	    myForm.state.value=$.query.get('state');
  	    myForm.submit();
	}
	function Go(obj){
		var dbname = obj.parentNode.id;
		if(!($('#'+dbname).hasClass("active")))
		{
			var whichnews = $('#whichnews');
	  	  	get('choosenews',dbname,1);
		}
	}
	function get(action,dbname,beginid){
		document.write('<form name="myForm"><input type="hidden" name="dbname"><input type="hidden" name="beginid"><input type="hidden" name="state"></form>');  
		    var myForm=document.forms['myForm'];  
		    myForm.action=action;  
		    myForm.method='GET';  
		    myForm.dbname.value=dbname;
		    myForm.beginid.value=beginid;
		    myForm.state.value=$.query.get('state');
		    myForm.submit();
	}
	$('.page').on('click',function(){
		if($('#fenghuang').hasClass("active"))
			dbname = "fenghuang";
		else if($('#huanqiu_china').hasClass("active"))
			dbname = "huanqiu_china";
		else if($('#xinhua').hasClass("active"))
			dbname = "xinhua";
		else if($('#xinhua_world').hasClass("active"))
			dbname = "xinhua_world";
		else if($('#xinlang').hasClass("active"))
			dbname = "xinlang";
		else if($('#huanqiu_world').hasClass("active"))
			dbname = "huanqiu_world";
		var beginid = $(this).get(0).innerText;
		get('choosenews',dbname,beginid);
	});
	$('#previous').on('click',function(){
		if($('#fenghuang').hasClass("active"))
			dbname = "fenghuang";
		else if($('#huanqiu_china').hasClass("active"))
			dbname = "huanqiu_china";
		else if($('#xinhua').hasClass("active"))
			dbname = "xinhua";
		else if($('#xinhua_world').hasClass("active"))
			dbname = "xinhua_world";
		else if($('#xinlang').hasClass("active"))
			dbname = "xinlang";
		else if($('#huanqiu_world').hasClass("active"))
			dbname = "huanqiu_world";
		var pagesul = document.getElementsByName('pages');
		var pages = pagesul[0].children;
		var beginid;
		for(var i = 0;i<pages.length;i++)
		{
			if($(pages[i]).hasClass("active")){
				beginid=parseInt($(pages[i].firstChild).get(0).innerText)-1;
				break;
			}
		}
		if(beginid>=1)
			get('choosenews',dbname,beginid.toString());
	});
	$('#next').on('click',function(){
		if($('#fenghuang').hasClass("active"))
			dbname = "fenghuang";
		else if($('#huanqiu_china').hasClass("active"))
			dbname = "huanqiu_china";
		else if($('#xinhua').hasClass("active"))
			dbname = "xinhua";
		else if($('#xinhua_world').hasClass("active"))
			dbname = "xinhua_world";
		else if($('#xinlang').hasClass("active"))
			dbname = "xinlang";
		else if($('#huanqiu_world').hasClass("active"))
			dbname = "huanqiu_world";
		var pagesul = document.getElementsByName('pages');
		var pages = pagesul[0].children;
		var beginid;
		var i = 0
		for(;i<pages.length;i++)
		{
			if($(pages[i]).hasClass("active")){
				beginid=parseInt($(pages[i].firstChild).get(0).innerText)+1;
				break;
			}
		}
		if(pages[i+1].innerText!='下一页')
			get('choosenews',dbname,beginid.toString());
	});
	
	$('.glyphicon-trash').on('click',function(){
		var newsid = this.parentNode.nextSibling.innerText;
		var trash = this;
		var l = trash.nextSibling.nextSibling;
		if(!$(l).hasClass('irrelevant')){
			$.ajax({
				url:'irrelevantLabel',
				type:'post',
				data:{username:$('#username').get(0).innerText, "dbname":$.query.get('dbname'), "newsid":newsid},
				success:function(data){
					//alert(data);
					$(l).removeClass('uncommit');
					$(l).addClass('irrelevant');
					l.innerText = "不相关";
				}
			});
		}
	});
	
	</script>
	</body>
</html>