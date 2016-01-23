$(document).ready(function(){
	$('#getallnews').on('click',function(){
		choosenewsGET('choosenews',$.query.get('dbname'), 1, 0);
	});
	$('#getunchangednews').on('click',function(){
		choosenewsGET('choosenews',$.query.get('dbname'), 1, 1);
	});
	$('#getunconfirmednews').on('click',function(){
		choosenewsGET('choosenews',$.query.get('dbname'), 1, 2);
	});
	$('#getconfirmednews').on('click',function(){
		choosenewsGET('choosenews',$.query.get('dbname'), 1, 3);
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