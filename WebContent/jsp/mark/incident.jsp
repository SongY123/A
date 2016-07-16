<%@ page language="java" import="java.util.*,  java.sql.*" pageEncoding="UTF-8"%>  
<%@ taglib uri="/struts-tags" prefix="s" %> 
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
String title = (String)session.getAttribute("title");
String context = (String)session.getAttribute("context");
String incident = (String)session.getAttribute("incident");
String sponsors = (String)session.getAttribute("sponsor");
String []sponsor = sponsors==null?null:sponsors.trim().split("_");
String sponsortypes = (String)session.getAttribute("sponsortype");
String []sponsortype = sponsortypes==null?null:sponsortypes.trim().split("_");
String triggerWord = (String)session.getAttribute("triggerWord");
String bearers = (String)session.getAttribute("bearer");
String[]bearer = bearers==null?null:bearers.trim().split("_");
String bearertypes = (String)session.getAttribute("bearertype");
String []bearertype = bearertypes==null?null:bearertypes.trim().split("_");
String time = (String)session.getAttribute("time");
String location = (String)session.getAttribute("location");
String actor_index = (String)session.getAttribute("actor_index");
String actor_len = (String)session.getAttribute("actor_len");
String actor_Pro = (String)session.getAttribute("actor_Pro");
String actor = (String)session.getAttribute("actor");
%>  
<!DOCTYPE html>
<html lang="zh-CN">

    <head>
		
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>事件抽取</title>

        <!-- CSS -->
       	<link href="<%=basePath%>form-1/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
       	<link href="<%=basePath%>form-1/assets/css/context.standalone.css" rel="stylesheet" type="text/css" />
       			
       	<!-- SCRIPT -->
		<script src="<%=basePath%>form-1/assets/js/jquery-2.1.4.min.js"></script>
		<script src="<%=basePath%>form-1/assets/js/get.js"></script>
		<script src="<%=basePath%>form-1/assets/js/getnews.js"></script>
  		<script src="<%=basePath%>form-1/assets/js/jquery.query.js"></script>
		<script type="text/javascript" src="<%=basePath%>form-1/assets/js/context.js"></script>
		<script src="<%=basePath%>form-1/assets/js/incident.js"></script>
  		<script src="<%=basePath%>form-1/assets/bootstrap/js/bootstrap.js"></script>
        <script src="<%=basePath%>form-1/assets/js/bootstrap-suggest.js"></script>
        <script src="<%=basePath%>form-1/assets/js/incident-add-drop.js"></script>
        
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- Favicon and touch icons -->
        <link rel="shortcut icon" href="<%=basePath%>form-1/assets/ico/favicon.png">

		<style>
		.incident {
  			color: #fff;
  			background-color: #5cb85c;
  			border-color: #4cae4c;
		}
		.sponsor{
  			color: #fff;
  			background-color: #c07abb;
 			border-color: #c07abb;
		}
		.triggerWord{
			color: #fff;
  			background-color: #ffad86;
 			border-color: #ffad86;
		}
		.bearer{
			color: #fff;
  			background-color: #a6a6d2;
 			border-color: #a6a6d2;
		}
		.time{
			color:#fff;
			background-color: #F5D735;
			boder-color: #EBCD2B;
		}
		.location{
			color:#fff;
			background-color: #F88158;
			border-color: #F88158;
		}
		
		.countryinfor{
			color:white;
			background-color:#FF4040;
		}
		.deviceinfor{color:white;
			background-color:#FF00FF;
		}
		.otherinfor{color:white;
			background-color:#9B30FF;
		}
		.personinfor{color:white;
			background-color:#76EE00;
		}
		.regioninfor{color:white;
			background-color:#ADADAD;
		}
		.roleinfor{color:white;
			background-color:#EEB422;
		}
		.orgnizationinfor{
			color:white;
			background-color:#4F9D9D;
		}
		.common{
			color:black;
		}
		</style>
		<style type="text/css">
			a:link{text-decoration:none ;}
			a:visited {text-decoration:none;}
			a:hover {text-decoration:none;color：white;}
			a:active {text-decoration:none;} 
			h4
			{
				margin-bottom:20px;
			}
		</style>
		
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
      			<li class="dropdown">
          			<a href="choosenews" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">新闻列表 <span class="caret"></span></a>
          			<ul class="dropdown-menu">
          				<li><a href="javascript:void(0);" id="getallnews">所有</a></li>
            			<li><a href="javascript:void(0);" id="getunchangednews">未修改</a></li>
            			<li><a href="javascript:void(0);" id="getunconfirmednews">已提交</a></li>
            			<li><a href="javascript:void(0);" id="getconfirmednews">不相关</a></li>
          			</ul>
        		</li>
        		<li class="dropdown">
          			<a href="chooseentity" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">实体列表 <span class="caret"></span></a>
          			<ul class="dropdown-menu">
          				<li><a href="javascript:void(0);" id="getformalentity">正式表</a></li>
            			<li><a href="javascript:void(0);" id="gettempentity">临时表</a></li>
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
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" id="username"><%out.print(username);%><span class="caret"></span></a>
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
		<%
			out.print("<h4 id=\"contenttext\">");
			if(actor==null||actor.trim().length()<=0){
				for(int i = 0;i<title.length();i++)				
					out.print("<a class=\"common\">"+title.charAt(i)+"</a>");
			}
			else{
				String[]actor_indexs = actor_index.split("_");
				String[]actor_lens = actor_len.split("_");
				String[]actor_Pros = actor_Pro.split("_");
				for(int i = 0,j = 0,index = Integer.parseInt(actor_indexs[0]),length = Integer.parseInt(actor_lens[0]);i<title.length();i++){
					if(i>=index&&i<index+length){
						if(actor_Pros[j].equals("countryinfor")){
							out.print("<a class=\"countryinfor\">"+title.charAt(i)+"</a>");
						}
						else if(actor_Pros[j].equals("deviceinfor")){
							out.print("<a class=\"deviceinfor\">"+title.charAt(i)+"</a>");
						}
						else if(actor_Pros[j].equals("otherinfor")){
							out.print("<a class=\"otherinfor\">"+title.charAt(i)+"</a>");
						}
						else if(actor_Pros[j].equals("regioninfor")){
							out.print("<a class=\"regioninfor\">"+title.charAt(i)+"</a>");
						}
						else if(actor_Pros[j].equals("personinfor")){
							out.print("<a class=\"personinfor\">"+title.charAt(i)+"</a>");
						}
						else if(actor_Pros[j].equals("roleinfor")){
							out.print("<a class=\"roleinfor\">"+title.charAt(i)+"</a>");
						}
						else if(actor_Pros[j].equals("orgnizationinfor")){
							out.print("<a class=\"orgnizationinfor\">"+title.charAt(i)+"</a>");
						}
						if(i==index+length-1){
							if(actor_Pros[j].equals("countryinfor")){
								out.print("<font size=\"1\">(国家)</font>");
							}
							else if(actor_Pros[j].equals("deviceinfor")){
								out.print("<font size=\"1\">(设备)</font>");
							}
							else if(actor_Pros[j].equals("otherinfor")){
								out.print("<font size=\"1\">(其他)</font>");
							}
							else if(actor_Pros[j].equals("regioninfor")){
								out.print("<font size=\"1\">(地区)</font>");
							}
							else if(actor_Pros[j].equals("personinfor")){
								out.print("<font size=\"1\">(人物)</font>");
							}
							else if(actor_Pros[j].equals("roleinfor")){
								out.print("<font size=\"1\">(职位)</font>");
							}
							else if(actor_Pros[j].equals("orgnizationinfor")){
								out.print("<font size=\"1\">(组织)</font>");
							}
						}
						if(i==index+length-1&&j<actor_indexs.length-1){
							j++;
							index = Integer.parseInt(actor_indexs[j]);
							length = Integer.parseInt(actor_lens[j]);
						}
					}
					else{
						out.print("<a class=\"common\">"+title.charAt(i)+"</a>");
					}
				}
			}
			out.print("</h4>");
			//out.print("<h4>"+title+"</h4>");
			%>
			<!-- Color compare -->
			<table width="100%" cellspacing="1">
				<tr>
					<td bgcolor="#FF00FF" height="25" width="25"></td><td>设备</td>
					<td bgcolor="#76EE00" height="25" width="25"></td><td>人物</td>
					<td bgcolor="#FF4040" height="25" width="25"></td><td>国家</td>
					<td bgcolor="#ADADAD" height="25" width="25"></td><td>地区</td>
					<td bgcolor="#4F9D9D" height="25" width="25"></td><td>组织</td>
					<td bgcolor="#EEB422" height="25" width="25"></td><td>职位</td>
					<td bgcolor="#9B30FF" height="25" width="25"></td><td>其他</td>
				</tr>
			</table>
		</div>
		<%
			//out.print("<iframe class = \"col-xs-3\" src=\""+basePath+"jsp/mark/news.jsp?title="+title+"&body="+context +'\"'+"  id=\"iframepage\" scrolling=\"no\" marginheight=\"0\" marginwidth=\"0\" onLoad=\"iFrameHeight()\"></iframe>");
    	%>
    			
		<!-- Editiable page on the right -->
		<div class="col-xs-8">
		<div>
 	 	<ul class="pagination pagination-lg">
   			<li class="active"><a href="javascript:void(0);">事件抽取</a></li>
  		</ul>
  		<ul class="pagination pagination-lg">
  			<s:if test="%{islast==0}">
  				<li><a href="incident?dbname=<s:property value="dbname"/>&newsid=<s:property value="lastnewsid"/>&state=<s:property value="state"/>" id ="lastnews">上一条</a></li>
  			</s:if>
	  		<s:if test="%{isfirst==0}">
	  			<li><a href="incident?dbname=<s:property value="dbname"/>&newsid=<s:property value="nextnewsid"/>&state=<s:property value="state"/>" id ="nextnews">下一条</a></li>
	  		</s:if>
  		</ul>
  		</div>
  		
  		<div>
  		<table class="table table-condensed" id="incidenttable" style="TABLE-LAYOUT:fixed;WORD-BREAK:break-all">
  			<!-- 修改确认用户  -->
  			<s:if test="%{if_commit==1}">
  			<tr class="row" id="changeconfirmtr">
  				<td class="col-xs-12">
  					<div class="input-group">
					<span class="input-group-addon bearer" id="changeconfirm">已提交</span>
                    	<input type="text" id="markusername" class="form-control" readOnly="true" value="<s:property value="marker_name"/>"/>
                    	</input>
					</div>
  				</td>
  				<td class="col-xs-8">
  					
  				</td>
  			</tr>
  			</s:if>
  			<s:elseif test="%{if_relevant==1}">
  			<tr class="row" id="changeconfirmtr">
  				<td class="col-xs-12">
  					<div class="input-group">
					<span class="input-group-addon bearer" id="changeconfirm">不相关</span>
                    	<input type="text" id="markusername" class="form-control" readOnly="true" value="<s:property value="marker_name"/>"/>
                    	</input>
					</div>
  				</td>
  				<td class="col-xs-8">
  					
  				</td>
  			</tr>
  			</s:elseif>
  			<s:else>
  			<tr class="row" style="display:none" id="changeconfirmtr">
  				<td class="col-xs-12">
  					<div class="input-group">
					<span class="input-group-addon bearer" id="changeconfirm">未修改</span>
                    		<input type="text" id="markusername" class="form-control" readOnly="true" value="<s:property value="marker_name"/>"/>
                    		</input>
					</div>
  				</td>
  				<td class="col-xs-8">
  					
  				</td>
  			</tr>
  			</s:else>
  			<!-- 事件类型  -->
  			<tr class="row">
  				<td class="col-xs-12">
        			<div class="input-group">
                        <div class="input-group-addon incident">事件类型</div>
                        <input type="text" id="eventType" class="form-control suggest" value="<%if(!(incident==null||(incident!=null&&incident.equals("null"))))out.print(incident);%>">
                        <div class="input-group-btn">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu dropdown-menu-right" role="menu">
                            </ul>
						</div>
					</div>
				</td>
				<td class="col-xs-8">
					<div class="input-group">
                 		<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                             	 动作<span class="caret"></span>
                 		</button>
                			<ul class="dropdown-menu">
                    			<li><a href = "javascript:void(0)" onclick="dropincident(this)">删除</a></li>
                        		<li><a href = "javascript:void(0)" onclick="addincident(this)">增加</a></li>
                    		</ul>
                	</div>	
                </td>
			</tr>
			
			<!-- 发起者  -->
			<%
				String s1 = "<tr class=\"row\" >"+
								"<td class=\"col-xs-12\" style=\"border-top:0px\">"+
									"<div class=\"input-group\">"+
										"<span type=\"button\" class=\"input-group-addon sponsor\">发起者</span>"+
                						"<input type=\"text\" class=\"form-control sponsorinput\"  style=\"width:320px\" value=\"";
                String s2 = "\">"+
										"<span class=\"input-group-addon sponsor\">类型</span>"+
										"<input type=\"text\" class=\"form-control sponsor-suggest\" value=\"";
				String s3 = "\"//>"+
                							"<div class=\"input-group-btn\">"+
                								"<button type=\"button\" class=\"btn btn-default dropdown-toggle\" data-toggle=\"dropdown\">"+
                    								"<span class=\"caret\"></span>"+
                								"</button>"+
                								"<ul class=\"dropdown-menu dropdown-menu-right\" role=\"menu\">"+
                								"</ul>"+
											"</div>"+
									"</div>"+
								"</td>"+
	
								"<td class=\"col-xs-8\"  style=\"border-top:0px\">"+
									"<div class=\"input-group\">"+
     									"<button type=\"button\" class=\"btn btn-default dropdown-toggle\" data-toggle=\"dropdown\">"+
                 	 						"动作<span class=\"caret\"></span>"+
     									"</button>"+
    									"<ul class=\"dropdown-menu\">"+
        									"<li><a href = \"javascript:void(0)\" onclick=\"dropline(this)\">删除</a></li>"+
            								"<li><a href = \"javascript:void(0)\" onclick=\"addline(this)\">增加</a></li>"+
        								"</ul>"+
            						"</div>"+
								"</td>"+
							"</tr>";
							System.out.println(sponsors);
							System.out.println(sponsortypes);
							//System.out.println(sponsor.length);
							//System.out.println(sponsortype.length);
				if(sponsor!=null&&sponsors!=null&&sponsors.length()>=1){
					for(int i = 0;i<sponsor.length;i++)
					{
						System.out.println(sponsor[i]);
						System.out.println(sponsortype[i]);
						if(sponsortype[i].equals("orgnizationinfor"))
								out.print(s1+sponsor[i]+s2+"组织"+s3);
						else if(sponsortype[i].equals("roleinfor"))
							out.print(s1+sponsor[i]+s2+"职位"+s3);
						else if(sponsortype[i].equals("deviceinfor"))
							out.print(s1+sponsor[i]+s2+"设备"+s3);
						else if(sponsortype[i].equals("regioninfor"))
							out.print(s1+sponsor[i]+s2+"地区"+s3);
						else if(sponsortype[i].equals("countryinfor"))
							out.print(s1+sponsor[i]+s2+"国家"+s3);
						else if(sponsortype[i].equals("personinfor"))
							out.print(s1+sponsor[i]+s2+"人物"+s3);
						else if(sponsortype[i].equals("otherinfor"))
							out.print(s1+sponsor[i]+s2+"其他"+s3);
					}
				}
				else
					out.print(s1+s2+s3);
			%>
			
			
			<!-- 触发词 -->
			<tr class="row">
				<td class="col-xs-12"  style="border-top:0px">	
					<div class="input-group">
						<div class="input-group-addon triggerWord">触发词</div>
                        <input type="text" id="triggerWord" class="form-control" value="<%if(!(triggerWord==null||(triggerWord!=null&&triggerWord.equals("null"))))out.print(triggerWord);%>">
					
					</div>
            	</td>
            	<td class="col-xs-8" style="border-top:0px"></td>
            </tr>
            
            
			<!-- 承受者  -->
			<%
				String b1 = "<tr class=\"row\" >"+
								"<td class=\"col-xs-12\" style=\"border-top:0px\">"+
									"<div class=\"input-group\">"+
										"<span type=\"button\" class=\"input-group-addon bearer\">承受者</span>"+
                						"<input type=\"text\" class=\"form-control bearerinput\" style=\"width:320px\" value=\"";
                String b2 = "\">"+
										"<span class=\"input-group-addon bearer\">类型</span>"+
										"<input type=\"text\" class=\"form-control bearer-suggest\" value=\"";
				String b3 = "\"//>"+
                							"<div class=\"input-group-btn\">"+
                								"<button type=\"button\" class=\"btn btn-default dropdown-toggle\" data-toggle=\"dropdown\">"+
                    								"<span class=\"caret\"></span>"+
                								"</button>"+
                								"<ul class=\"dropdown-menu dropdown-menu-right\" role=\"menu\">"+
                								"</ul>"+
											"</div>"+
									"</div>"+
								"</td>"+
	
								"<td class=\"col-xs-8\"  style=\"border-top:0px\">"+
									"<div class=\"input-group\">"+
     									"<button type=\"button\" class=\"btn btn-default dropdown-toggle\" data-toggle=\"dropdown\">"+
                 	 						"动作<span class=\"caret\"></span>"+
     									"</button>"+
    									"<ul class=\"dropdown-menu\">"+
        									"<li><a href = \"javascript:void(0)\" onclick=\"dropline(this)\">删除</a></li>"+
            								"<li><a href = \"javascript:void(0)\" onclick=\"addline(this)\">增加</a></li>"+
        								"</ul>"+
            						"</div>"+
								"</td>"+
							"</tr>";
							System.out.println("success");
				if(bearer!=null&&(bearers!=null&&bearers.length()>=1)){
					for(int i = 0;i<bearer.length;i++)
					{
						System.out.println(bearer.length);
						System.out.println(bearertype.length);
						System.out.println(bearers);
						System.out.println(bearertypes);
						if(bearertype[i].equals("orgnizationinfor"))
								out.print(b1+bearer[i]+b2+"组织"+b3);
						else if(bearertype[i].equals("roleinfor"))
							out.print(b1+bearer[i]+b2+"职位"+b3);
						else if(bearertype[i].equals("deviceinfor"))
							out.print(b1+bearer[i]+b2+"设备"+b3);
						else if(bearertype[i].equals("regioninfor"))
							out.print(b1+bearer[i]+b2+"地区"+b3);
						else if(bearertype[i].equals("countryinfor"))
							out.print(b1+bearer[i]+b2+"国家"+b3);
						else if(bearertype[i].equals("personinfor"))
							out.print(b1+bearer[i]+b2+"人物"+b3);
						else if(bearertype[i].equals("otherinfor"))
							out.print(b1+bearer[i]+b2+"其他"+b3);
					}
				}
				else{
					out.print(b1+b2+b3);
				}
			%>
			
			
			<!-- 时间  -->
			<tr class="row">
				<td class="col-xs-12">
					<div class="input-group">
					<div class="input-group-addon time">时间  </div>
                    <input type="text" class="form-control" id="time" value="<%if(!(time==null||(time!=null&&time.equals("null"))))out.print(time);%>">
					</div>
				</td>
				
				<td class="col-xs-8">
					<div class="input-group">
                 		<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                             	 动作<span class="caret"></span>
                 		</button>
                			<ul class="dropdown-menu">
                    			<li><a href = "javascript:void(0)" onclick="dropline(this)">删除时间</a></li>
                        		<li><a href = "javascript:void(0)" onclick="addline(this)">增加时间</a></li>
                    		</ul>
                	</div>
				</td>
			</tr>
			
			<!-- 地点  -->
			<tr class="row">
				<td class="col-xs-12">
					<div class="input-group">
					<div class="input-group-addon location">地点  </div>
                    <input type="text" class="form-control" id="location" value="<%if(!(location==null||(location!=null&&location.equals("null"))))out.print(location);%>">
					</div>
				</td>
				
				<td class="col-xs-8">
					<div class="input-group">
                 		<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                             	 动作<span class="caret"></span>
                 		</button>
                 		<ul class="dropdown-menu">
                    			<li><a href = "javascript:void(0)" onclick="dropline(this)"><i class="icon-trash"></i>删除地点</a></li>
                        		<li><a href = "javascript:void(0)" onclick="addline(this)"><i class="icon-trash"></i>增加地点</a></li>
                    		</ul>
                	</div>
				</td>
			</tr>

			<tr class="row">
				<td class="col-xs-12">
				<button class="btn btn-default"><a href ="#">回到顶部 >></a></button>
				</td>
				
				<td class="col-xs-8">
					<button type="button" id="change" class="btn btn-primary"  data-toggle="modal" data-target="#myModalChange">提交</button>&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" id="irrelevant" class="btn btn-primary" data-toggle="modal" data-target="#myModalIrrelevant">不相关</button>&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" id="restart" class="btn btn-primary">重新抽取</button>
				</td>
			</tr>


        </table>
        
  		</div>
  		
        </div>
	</div>
	
	<div class="modal fade" id="myModalChange">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">是否确定提交</h4>
          </div>
          <div class="modal-body">
            <p>请确认后提交&hellip;</p>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            <button type="button" class="btn btn-primary" onclick="Change()">确定</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
	
	<div class="modal fade" id="myModal">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">是否确定提交</h4>
          </div>
          <div class="modal-body">
            <p>请确认后提交&hellip;</p>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            <button type="button" class="btn btn-primary" onclick="Irrelevant()">确定</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    	
	<input style="display:none" id="incidenttype" value="<s:property value="incidenttype"/>"/>
	<script>
	var incidenttype = document.getElementById("incidenttype").value;
	//alert(incidenttype);
	function Change(){
		if(document.getElementById("eventType").value.length==0)alert("请确认事件类型后再提交");
		else{
			var sourceActorinput = "";
			$("input.sponsorinput").each(function(){
				sourceActorinput = sourceActorinput + $(this).val()+"_";
			});
			sourceActorinput=sourceActorinput.substring(0,sourceActorinput.length-1);
			//alert(sourceActorinput);
			
			var sourceActorsuggest = "";
			$("input.sponsor-suggest").each(function(){
				var entitytype=$(this).val();
				if (entitytype=="组织")
					sourceActorsuggest = sourceActorsuggest + "orgnizationinfor_";
				else if(entitytype=="职位")
					sourceActorsuggest = sourceActorsuggest + "roleinfor_";
				else if(entitytype=="设备")
					sourceActorsuggest = sourceActorsuggest + "deviceinfor_";
				else if(entitytype=="地区")
					sourceActorsuggest = sourceActorsuggest + "regioninfor_";
				else if(entitytype=="国家")
					sourceActorsuggest = sourceActorsuggest + "countryinfor_";
				else if(entitytype=="人物")
					sourceActorsuggest = sourceActorsuggest + "personinfor_";
				else if(entitytype=="其他")
					sourceActorsuggest = sourceActorsuggest + "otherinfor_";
			});
			sourceActorsuggest=sourceActorsuggest.substring(0,sourceActorsuggest.length-1);
			//alert(sourceActorsuggest);
			
			var targetActorinput = "";
			$("input.bearerinput").each(function(){
				targetActorinput = targetActorinput + $(this).val()+"_";
			});
			targetActorinput=targetActorinput.substring(0,targetActorinput.length-1);
			//alert(targetActorinput);
			
			var targetActorsuggest = "";
			$("input.bearer-suggest").each(function(){
				var entitytype=$(this).val();
				if (entitytype=="组织")
					targetActorsuggest = targetActorsuggest + "orgnizationinfor_";
				else if(entitytype=="职位")
					targetActorsuggest = targetActorsuggest + "roleinfor_";
				else if(entitytype=="设备")
					targetActorsuggest = targetActorsuggest + "deviceinfor_";
				else if(entitytype=="地区")
					targetActorsuggest = targetActorsuggest + "regioninfor_";
				else if(entitytype=="国家")
					targetActorsuggest = targetActorsuggest + "countryinfor_";
				else if(entitytype=="人物")
					targetActorsuggest = targetActorsuggest + "personinfor_";
				else if(entitytype=="其他")
					targetActorsuggest = targetActorsuggest + "otherinfor_";
			});
			targetActorsuggest=targetActorsuggest.substring(0,targetActorsuggest.length-1);
			//alert(targetActorsuggest);
			
			var actor_index = "";
			var actor_len = "";
			var actor_Pro = "";
			var actor = "";
			
			var contenttext = $(document.getElementById("contenttext")).children();
			var this_index = 0;
			var this_len = 0;
			var flag = 0;
			var index = 0;
			$(contenttext).each(function(){
				if(flag==0&&!$(this).hasClass("common")){//an entity begin
					flag = 1;
					this_index = index;
					this_len = this_len+1;
					index = index +1;
					if(actor.length<=0)actor = actor+$(this).text();
					else actor = actor + "_" + $(this).text();
				}
				else if(flag==1&&!$(this).hasClass("common")&&this.nodeName!="FONT"){
					this_len= this_len+1;
					index = index+1;
					actor = actor + $(this).text();
				}
				else if(flag==1&&this.nodeName=="FONT"){
					actor_index = actor_index+this_index+"_";
					actor_len = actor_len+this_len+"_";
					this_len = 0;
					flag = 0;
					var className = "";
					if($(this).text()=="(设备)")
						className = "deviceinfor";
					else if($(this).text()=="(人物)")
						className = "personinfor";
					else if($(this).text()=="(国家)")
						className = "countryinfor";
					else if($(this).text()=="(地区)")
						className = "regioninfor";
					else if($(this).text()=="(组织)")
						className = "orgnizationinfor";
					else if($(this).text()=="(职位)")
						className = "roleinfor";
					else if($(this).text()=="(其他)")
						className = "otherinfor";
					if(actor_Pro.length<=0)actor_Pro = actor_Pro + className;
					else actor_Pro = actor_Pro + "_" + className;
				}
				else 
					index = index+1;
			});
			if(actor_index.length>0){
				actor_index = actor_index.substring(0,actor_index.length-1);
			}
			if(actor_len.length>0){
				actor_len = actor_len.substring(0,actor_len.length-1);
			}
			//alert(actor_index);
			//alert(actor_len);
			//alert(actor_Pro);
			//alert(actor);
			$.ajax({
				url:'addLabel',
				type:'post',
				data:{actorIndex:actor_index,actorLen:actor_len,actorPro:actor_Pro,actorName:actor,eventType:incidenttype, sourceActor:sourceActorinput, targetActor:targetActorinput, sourceActorPro:sourceActorsuggest, targetActorPro:targetActorsuggest, triggerWord:$("#triggerWord").val(), eventLocation:$("#location").val(), eventTime:$("#time").val(), username:$('#username').get(0).innerText, "dbname":$.query.get('dbname'), "newsid":$.query.get('newsid')},
				success:function(data){
					//alert(data);
					$('#markusername').val($('#username').get(0).innerText);
					$('#changeconfirm').get(0).innerText='已提交';
					$('#changeconfirmtr').show();
					$('#myModalChange').modal('hide');
				}
			});
			//alert($('#username').get(0).innerText);
			//document.getElementById("change").setAttribute("disabled", "true");
		}
		/*
		myForm.submit();
		alert("成功提交");
		*/
	}
	
	function Irrelevant(){
		if(document.getElementById("eventType").value.length==0)alert("请确认事件类型后再提交");
		else{
			$.ajax({
				url:'irrelevantLabel',
				type:'post',
				data:{username:$('#username').get(0).innerText, "dbname":$.query.get('dbname'), "newsid":$.query.get('newsid')},
				success:function(data){
					//alert(data);
					$('#changeconfirm').get(0).innerText='不相关';
					$('#changeconfirmtr').show();
					$('#myModalIrrelevant').modal('hide');
				}
			});
			//alert($('#username').get(0).innerText);
		}
		/*
		myForm.submit();
		alert("成功提交");
		*/
	}
	</script>
	
	<script type="text/javascript">
	$("#restart").click(function(){
		//alert($.query.get('dbname'));
		//alert($.query.get('newsid'));
		//alert(incidenttype);
		//alert($("#triggerWord").val());
		var eventType = incidenttype;
		var triggerWord = $("#triggerWord").val();
		var dbname = $.query.get('dbname');
		var newsid = $.query.get('newsid');
		var state = $.query.get('state');
		if(document.getElementById("eventType").value.length==0)alert("请确认事件类型后再提交");
		else if($("#triggerWord").val().length==0){
			alert("请确认触发词后提交");
		}
		else{
			restart('restart',dbname,newsid,triggerWord,eventType,state);
			//alert($('#username').get(0).innerText);
			//document.getElementById("change").setAttribute("disabled", "true");
		}
	});
	$(".suggest").bsSuggest({
         //url: "/rest/sys/getuserlist?keyword=",
        url: "jsp/mark/datajson/incidentdata.json",
        //showBtn: false,
        indexKey: 1,
        idField: "attribute1",
  		keyField:"attribute2"
    }).on('onDataRequestSuccess', function (e, result) {
        console.log('onDataRequestSuccess: ', result);
    }).on('onSetSelectValue', function (e, keyword) {
        console.log('onSetSelectValue: ', keyword);
        incidenttype=keyword.id;
    }).on('onUnsetSelectValue', function (e) {
        console.log("onUnsetSelectValue");
    });
	
	$(".bearer-suggest").bsSuggest({
        //url: "/rest/sys/getuserlist?keyword=",
       url: "jsp/mark/datajson/entitytypedata.json",
       //showBtn: false,
       indexKey: 1,
       idField: "attribute1",
 		keyField:"attribute2"
   }).on('onDataRequestSuccess', function (e, result) {
       console.log('onDataRequestSuccess: ', result);
   }).on('onSetSelectValue', function (e, keyword) {
       console.log('onSetSelectValue: ', keyword);
   }).on('onUnsetSelectValue', function (e) {
       console.log("onUnsetSelectValue");
   });
	
	$(".sponsor-suggest").bsSuggest({
        //url: "/rest/sys/getuserlist?keyword=",
       url: "jsp/mark/datajson/entitytypedata.json",
       //showBtn: false,
       indexKey: 1,
       idField: "attribute1",
 		keyField:"attribute2"
   }).on('onDataRequestSuccess', function (e, result) {
       console.log('onDataRequestSuccess: ', result);
   }).on('onSetSelectValue', function (e, keyword) {
       console.log('onSetSelectValue: ', keyword);
   }).on('onUnsetSelectValue', function (e) {
       console.log("onUnsetSelectValue");
   });
	</script>
	</body>
</html>