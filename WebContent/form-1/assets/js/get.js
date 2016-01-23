$(document).ready(function(){
	$('#segmentation').on('click',function(){
		get('segmentation',$.query.get('dbname'),$.query.get('newsid'));
	});
	$('#nominal').on('click',function(){
		get('nominal',$.query.get('dbname'),$.query.get('newsid'));
	});
	$('#entity').on('click',function(){
		get('entity',$.query.get('dbname'),$.query.get('newsid'));
	});
	$('#incident').on('click',function(){
		get('incident',$.query.get('dbname'),$.query.get('newsid'));
	});
	
});
function get(action,dbname,newsid){
	document.write('<form name="myForm"><input type="hidden" name="dbname"><input type="hidden" name="newsid"></form>');  
	    var myForm=document.forms['myForm'];  
	    myForm.action=action;  
	    myForm.method='GET';  
	    myForm.dbname.value=dbname;
	    myForm.newsid.value=newsid;
	    myForm.submit();
}