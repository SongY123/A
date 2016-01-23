<%@ page language="java" import="java.util.*,  java.sql.*" pageEncoding="UTF-8"%>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
String title = (String)session.getAttribute("title");
String context = (String)session.getAttribute("context");
String titles = (String)session.getAttribute("titles");
String contexts = (String)session.getAttribute("contexts");
String titletags = (String)session.getAttribute("titletags");
String contexttags = (String)session.getAttribute("contexttags");
%>
<!DOCTYPE html>
<html lang="zh-CN">

    <head>
	
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>词性标注</title>

        <!-- CSS -->
       	<link href="<%=basePath%>form-1/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
       	<link href="<%=basePath%>form-1/assets/css/context.standalone.css" rel="stylesheet" type="text/css" />
       			
       	<!-- SCRIPT -->
		<script src="<%=basePath%>form-1/assets/js/jquery-2.1.4.min.js"></script>
  		<script src="<%=basePath%>form-1/assets/js/get.js"></script>
  		<script src="<%=basePath%>form-1/assets/js/entity.js"></script>
  		<script src="<%=basePath%>form-1/assets/js/jquery.query.js"></script>
  		<script src="<%=basePath%>form-1/assets/bootstrap/js/bootstrap.js"></script>
        <script src="<%=basePath%>form-1/assets/js/bootstrap-suggest.js"></script>
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
		<style>
		.btn-selected
		{
			border-style:none;
			outline:none;
		}
		.tag{
			color: #fff;
  			background-color: #61a6f5;
 			border-color: #61a6f5;
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
    	
		<!-- Editiable page on t	he right -->
		<div class="col-xs-8">
		<div>
 	 	<ul class="pagination pagination-lg">
    		<li class="active"><a href="javascript:void(0);">词性标注</a></li>
   			<li><a href="javascript:void(0);" id ="incident">事件抽取</a></li>
  		</ul>
  		<ul class="pagination pagination-lg">
  		<li><a href="javascript:void(0);" id ="nextnews">下一条</a></li>
  		</ul>
  		</div>
  		<div id="contenttext" class="row" style="margin-bottom:20px">
		<%
		//System.out.println(titles);
		//System.out.println(titletags);
		String suggest1 = "<div class=\"col-xs-12 input-group\"><input type=\"text\" class=\"form-control\" ";
		String suggest2 = "/><div class=\"input-group-btn\"><button type=\"button\" class=\"btn dropdown-toggle\" data-toggle=\"dropdown\"><span class=\"caret\"></span></button><ul class=\"dropdown-menu dropdown-menu-right\" role=\"menu\"></ul></div></div>";
		String []titlesplits = titles.split(",\\s");
		String []titletagssplits = titletags.split("\\s+");
		System.out.println(titles);
		System.out.println(titletags);
		System.out.println(titlesplits.length);
		System.out.println(titletagssplits.length);
		for(int i = 0;i<titlesplits.length;i++){
			//System.out.println("i"+i);
			out.print("<div class=\"col-xs-2 row\"><input type=\"button\" class=\"col-xs-12 btn btn-default btn-selected\" value=\""+titlesplits[i]+"\">"+suggest1);
			out.print("value=\""+titletagssplits[i]+"\""+suggest2+"</div>");
		}
		%>
		<%
		if(context==null){
			out.print("</table>");
		}
		else{
			out.print("<table class = \"table\" style=\"TABLE-LAYOUT:fixed;WORD-BREAK:break-all\">");
			String[] contextsplits = context.split("\\s+");
			String[] contexttagssplit = contexttags.split("\\s+");
			for(int i = 0;i<contextsplits.length;++i){
				if(i%8==0)out.print("<tr>");
				out.print("<td>"+contextsplits[i]+"<br/>"+suggest1+contexttagssplit[i]+suggest2+"</td>");
				if((i-7)%8==0)out.print("</tr>");
			}
			out.print("</table>");
		}
		%>
</div>
		<button class="btn btn-default"><a href ="#">回到顶部 >></a></button>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<button type="button" class="btn btn-primary" onclick="Change()">提交修改</button>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<button type="button" class="btn btn-primary">确认修改</button>
        </div>
        
        
		<div class="modal fade" id="myModal" aria-hidden="true" data-backdrop="static" keyboard="false">
      		<div class="modal-dialog">
        		<div class="modal-content">
          			<div class="modal-header">
            			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            			<h4 class="modal-title" id="title"></h4>
          			</div>
          			<form role="form" class="login-form" method='post' action='entityAttr' id='entityAttr'>
          				<div class="modal-body">
          					<ul id="myTab" class="nav nav-tabs" style="margin-bottom:10px">
   								<li class="active" id="attributeli">
      								<a href="#attribute" data-toggle="tab">属性</a>
   								</li>
  								<li id="partli"><a href="#part" data-toggle="tab">拆分</a></li>
							</ul>
							<!-- 标签页  -->
							<div id="myTabContent" class="tab-content">
							<!-- 拆分  -->
							<div class="tab-pane fade" id="part">
								<label class="checkbox-inline">
  									俄
								</label>
								<label class="checkbox-inline">
									<input type="checkbox" id="inlineCheckbox2" value="option2">罗
								</label>
								<label class="checkbox-inline">
									<input type="checkbox" id="inlineCheckbox3" value="option3">斯
								</label>
								<label class="checkbox-inline">
									<input type="checkbox" id="inlineCheckbox3" value="option3">斯
								</label>
							</div>
							<!-- 属性  -->
          					<div class="form-group tab-pane fade in active" id="attribute">
          					<table class="table table-condensed" style="TABLE-LAYOUT:fixed;WORD-BREAK:break-all">
          						<tr class="row" id="selecttr">
          							<td class="col-xs-12"  style="border-top:none">
          								<div class="input-group">
          									<div class="input-group-addon tag">类型</div>
      											<select class="form-control" id="selecttype" name="selecttype">
         											<option>人物</option>
         											<option>国家</option>
        											<option>地区</option>
         											<option>组织</option>
      											</select>
      									</div>
      								</td>
      								<td class="col-xs-12" style="border-top:none">
      								</td>
      							</tr>
          						<tr class="row">
          						<td class="col-xs-12">
        							<div class="input-group">
 	       								<div class="input-group-addon tag">姓名</div>
          								<input type="text" id="personname" name="personname" class="form-username form-control col-xs-6">
			            			</div>
			            		</td>
			            		<td class="col-xs-12">
        							<div class="input-group">
	        							<div class="input-group-addon tag">国家</div>
			            				<input type="text" id="countryname" name="countryname" class="form-username form-control col-xs-6">
          							</div>
          						</td>
          						</tr>
          						
          						<tr class="row">
          						<td class="col-xs-12">
        							<div class="input-group">
										<div class="input-group-addon tag">地区</div>
          								<input type="text" id="regionname" name="regionname" class="form-username form-control col-xs-6" >
			            			</div>
			            		</td>
			            		<td class="col-xs-12">
        							<div class="input-group">
										<div class="input-group-addon tag">组织</div>
			            				<input type="text" id="rolename" name="rolename" class="form-username form-control col-xs-6" >
          							</div>
          						</td>
          						</tr>
          						
          						<tr class="row">
          						<td class="col-xs-12">
        							<div class="input-group">
										<div class="input-group-addon tag">职位</div>
          								<input type="text" id="position" name="position" class="form-username form-control col-xs-6" >
			            			</div>
			            		</td>
			            		<td class="col-xs-12">
        							<div class="input-group">
										<div class="input-group-addon tag">时间</div>
			            				<input type="text" id="time" name="time" placeholder="[2015-10-24]" class="form-username form-control col-xs-6" >
          							</div>
          						</td>
          						</tr>
          						
          						<tr class="row">
          						<td class="col-xs-12">
        							<div class="input-group">
										<div class="input-group-addon tag">其他</div>
          								<input type="text" id="other" name="other" class="form-username form-control col-xs-6" >
			            			</div>
			            		</td>
          						</tr>
          					</table>
			            		
			            	</div>
			            	</div>
          				</div>
          				<div class="modal-footer">
            				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            				<button type="button" class="btn btn-primary" id="mymodalconfirm">确定</button>
          				</div>
          			</form>
        		</div>
      		</div>
    	</div>
        
	</div>
	<script type="text/javascript">
	var testBsSuggest = $(".form-control").bsSuggest({
        //url: "/rest/sys/getuserlist?keyword=",
        url: "jsp/mark/datajson/nominaldata.json",
        /*effectiveFields: ["userName", "shortAccount"],
        searchFields: [ "shortAccount"],
        effectiveFieldsAlias:{userName: "姓名"},*/
        showBtn: false,
        indexKey: 1,
        idField: "attribute2",
  		keyField:"attribute2"
    }).on('onDataRequestSuccess', function (e, result) {
        console.log('onDataRequestSuccess: ', result);
    }).on('onSetSelectValue', function (e, keyword) {
        console.log('onSetSelectValue: ', keyword);
    }).on('onUnsetSelectValue', function (e) {
        console.log("onUnsetSelectValue");
    });
	</script>
	
	<script type="text/javascript">
	//鼠标悬停提示
	$('btn-selected').attr("data-placement","top");
	$('.btn-selected').mouseover(function(){
		$(this).attr("data-original-title",$(this).attr("value"));  
		$(this).tooltip('show');
	});
	//禁用自动关闭
	//鼠标焦点
	$('.btn-selected').focus(function(){
		$(this).blur();	
	});
	//右击button
	var btnselected;
	$('.btn-selected').mousedown(function (e){
		if(e.which==3)//右击
		{
			$('#part').empty();
			$('#part').removeClass('active');
			$('#part').removeClass('in');
			$('#partli').removeClass('active');
			$('#attributeli').addClass('active');
			$('#attribute').addClass('active');
			$('#attribute').addClass('in');
			var title = $(this).get(0).value;
			$('#title').html(title);
			//alert(title.length);
			var label11 = "<label class=\"checkbox-inline\">";
			var label12 = "</label>";
		    var label21 = "<label class=\"checkbox-inline\"><input type=\"checkbox\">";
		    var label22 = "</input></label>";
			for(var i = 0;i<title.length;i++)
			{
				if(i==0){
					var dom = ToDOM(label11+title[i]+label12);
					$('#part').append(dom);
				}
				else{
					var dom = ToDOM(label21+title[i]+label22);
					$('#part').append(dom);
				}
			}
			btnselected = $(this).get(0);
			$('#myModal').modal('show');
		}
	});
	function ToDOM(str){
		   var div = document.createElement("div");
		   if(typeof str == "string")
		       div.innerHTML = str;
		   return div.childNodes;
	}
	//modal确定
	$('#mymodalconfirm').on('click',function(){
		if($('#attributeli').hasClass('active')){
			//alert("attributeli");
			var obj = $('#selecttype').get(0);
			var opt = obj.options[obj.selectedIndex];
			if(opt.text=="国家"){
				var countryname = $('#countryname').val();
				var countrycode = $('#countrycode').val();
				//alert(countryname);
				//alert(countrycode);
			}				
	    	else if(opt.text=="人物"){
	    		var personname = $('#personname').val();
	    		var countryname = $('#countryname').val();
	    		var regionname = $('#regionname').val();
	    		var rolename = $('#rolename').val();
	    		var position = $('#position').val();
	    		var time = $('#time').val();
	    		var other = $('#other').val();
	    		//alert(personname);
	    		//alert(countryname);
	    		//alert(regionname);
	    		//alert(rolename);
	    		//alert(position);
	    		//alert(time);
	    		//alert(other);
    		}
	    	else if(opt.text=="地区"){
	    		var regionname = $('#regionname').val();
	    		var regioncode = $('#regioncode').val();
	    		var regioninfor = $('#regioninfor').val();
	    		//alert(regionname);
	    		//alert(regioncode);
	    		//alert(regioninfor);
	    	}
	    	else if(opt.text=="组织"){
	    		var rolename = $('#rolename').val();
	    		var rolecode = $('#rolecode').val();
	    		var regionname = $('#regionname').val();
	    		var countryname = $('#countryname').val();
	    		var other = $('#other').val();
	    		//alert(rolename);
	    		//alert(rolecode);
	    		//alert(regionname);
	    		//alert(countryname);
	    		//alert(other);
	    	}
	    	else
	    		alert("fail!");
			//$('#entityAttr').submit();
			$.ajax({
				type:"POST",
				url:"entityAttr",
				data:$('#entityAttr').serialize(),
				//success:function(msg){
					//alert(msg);
				//}
			});
		}
		else{
		var childs = $('#part').children();
		var context = childs[0].innerHTML;
		var tag = 0;
		for(var i = 1;i<childs.length;i++)
		{
			var input = childs[i].firstChild;
			if(input.checked){
				//alert(context);
				if(tag==0){
					$(btnselected).attr('value',context);
					tag = 1;
				}
				else{
					var t = "<div class=\"col-xs-2 row\"><input type=\"button\" class=\"col-xs-12 btn btn-default btn-selected\" value=\"\"><div class=\"col-xs-12 input-group\"><input type=\"text\" class=\"form-control\"/><div class=\"input-group-btn\"><button type=\"button\" class=\"btn dropdown-toggle\" data-toggle=\"\" style=\"display: none;\"><span class=\"caret\"></span></button><ul class=\"dropdown-menu dropdown-menu-right\" role=\"menu\"></ul></div></div></div>";
					var d = ToDOM(t);
					var th = $(d).first().get(0);
					th.firstChild.value = context;
					$(btnselected).parent().after($(d));
					btnselected = th.firstChild;
				}
				context = childs[i].innerText;
			}
			else{
				context = context+childs[i].innerText;
			}
		}
		if(tag!=0)
		{
			var t = "<div class=\"col-xs-2 row\"><input type=\"button\" class=\"col-xs-12 btn btn-default btn-selected\" value=\"\"><div class=\"col-xs-12 input-group\"><input type=\"text\" class=\"form-control\"/><div class=\"input-group-btn\"><button type=\"button\" class=\"btn dropdown-toggle\" data-toggle=\"\" style=\"display: none;\"><span class=\"caret\"></span></button><ul class=\"dropdown-menu dropdown-menu-right\" role=\"menu\"></ul></div></div></div>";
			var d = ToDOM(t);
			var th = $(d).first().get(0);
			th.firstChild.value = context;
			$(btnselected).parent().after($(d));
			btnselected = th.firstChild;
		}
		//同上面的button右击绑定，重新绑定
		$('.btn-selected').mousedown(function (e){
			if(e.which==3)//右击
			{
				$('#part').empty();
				$('#part').removeClass('active');
				$('#part').removeClass('in');
				$('#partli').removeClass('active');
				$('#attributeli').addClass('active');
				$('#attribute').addClass('active');
				$('#attribute').addClass('in');
				var title = $(this).get(0).value;
				$('#title').html(title);
				//alert(title.length);
				var label11 = "<label class=\"checkbox-inline\">";
				var label12 = "</label>";
			    var label21 = "<label class=\"checkbox-inline\"><input type=\"checkbox\">";
			    var label22 = "</input></label>";
				for(var i = 0;i<title.length;i++)
				{
					if(i==0){
						var dom = ToDOM(label11+title[i]+label12);
						$('#part').append(dom);
					}
					else{
						var dom = ToDOM(label21+title[i]+label22);
						$('#part').append(dom);
					}
				}
				btnselected = $(this).get(0);
				$('#myModal').modal('show');
			}
		});
		$('btn-selected').attr("data-placement","top");
		$('.btn-selected').mouseover(function(){
			$(this).attr("data-original-title",$(this).attr("value"));  
			$(this).tooltip('show');
		});
		}
		$('.btn-selected').focus(function(){
			$(this).blur();	
		});
		//alert(context);
		$('#myModal').modal('hide');
	});
	//使网页右击失效
	$(document).bind('contextmenu',function(){
		return false;
	});
	//点击合并
	$(document).on('click','.btn-selected',function(){
		if($(this).hasClass("btn-primary"))
			$(this).removeClass("btn-primary");
		else
		{
			var thisnode = $(this).parent().get(0);
			var prevnode = $(this).parent().prev().get(0);
			var nextnode = $(this).parent().next().get(0);
			if(prevnode!=null&&$(prevnode.firstChild).hasClass("btn-primary"))
			{
				var context = thisnode.firstChild.value;
				//alert(context);
				var prevcontext = prevnode.firstChild.value;
				//alert(prevcontext);
				var all = prevcontext+context;
				prevnode.firstChild.value = all;
				thisnode.remove();
			}
			else if(nextnode!=null&&$(nextnode.firstChild).hasClass("btn-primary"))
			{
				var context = thisnode.firstChild.value;
				//alert(context);
				var nextcontext = nextnode.firstChild.value;
				//alert(nextcontext);
				var all = context+nextcontext;
				thisnode.firstChild.value = all;
				nextnode.remove();
			}
			else{
				var parentnode = $(this).parent().parent().get(0);
				var context;
				for(var node = parentnode.firstChild;node!=null;node = node.nextSibling){
					if($(node.firstChild).hasClass("btn-primary"))$(node.firstChild).removeClass("btn-primary");
				}
			}
			$(this).addClass("btn-primary");
		}
		//alert($(this).text());
	});
	function Change(){
		var div = $('#contenttext').children();
		var result = "[";
		for(var i = 0;i<div.length;i++){
			var key = div[i].firstChild.value;
			var value = div[i].firstChild.nextSibling.firstChild.value;
			if(value!=""){
				if(result.length==1)
					result+=value+"="+key;
				else 
					result+=", "+value+"="+key;
			}
		}
		result+="]";
		//alert(result);
		$.ajax({
			type:"POST",
			url:"nominalChange",
			data:{"changener":result,"dbname":$.query.get('dbname'),"newsid":$.query.get('newsid')},
			//success:function(msg){
				//alert(msg);
			//}
		});
	}
	</script>
	</body>
</html>