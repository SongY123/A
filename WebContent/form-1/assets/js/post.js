function post(url,title,body){
	//alert(url);
	//alert(title);
	//alert(body);
	document.write('<form name="myForm"><input type="hidden" name="title"><input type="hidden" name="body"></form>');  
	var myForm=document.forms['myForm'];  
	myForm.action=url;  
	myForm.method='POST';  
	myForm.title.value=title;
	myForm.body.value=body;
	myForm.submit(); 
}