$(document).ready(function(){
	var dbname;
	if($.query.get('dbname').length==0)
		dbname="fenghuang";
	else dbname= $.query.get('dbname');
	$('#getallnews').on('click',function(){
		choosenewsGET('choosenews',dbname, 1, 0);
	});
	$('#getunchangednews').on('click',function(){
		choosenewsGET('choosenews',dbname, 1, 1);
	});
	$('#getunconfirmednews').on('click',function(){
		choosenewsGET('choosenews',dbname, 1, 2);
	});
	$('#getconfirmednews').on('click',function(){
		choosenewsGET('choosenews',dbname, 1, 3);
	});
	$('#gettempentity').on('click',function(){
		chooseentityGET('chooseentity','deviceinfor', 1, 1);
	});
	$('#getformalentity').on('click',function(){
		chooseentityGET('chooseentity', 'deviceinfor', 1, 0);
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
function chooseentityGET(action,entitytype,beginid, state){
	document.write('<form name="myForm"><input type="hidden" name="entitytype"/><input type="hidden" name="beginid"/><input type="hidden" name="state"/></form>');  
	    var myForm=document.forms['myForm'];  
	    myForm.action=action;  
	    myForm.method='GET';  
	    myForm.entitytype.value=entitytype;
	    myForm.beginid.value=beginid;
	    myForm.state.value=state;
	    myForm.submit();
}