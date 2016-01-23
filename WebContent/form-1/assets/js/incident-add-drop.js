	function dropincident(obj){
		obj.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.removeChild(obj.parentNode.parentNode.parentNode.parentNode.parentNode.previousElementSibling);
		obj.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.removeChild(obj.parentNode.parentNode.parentNode.parentNode.parentNode.previousElementSibling);
		obj.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.removeChild(obj.parentNode.parentNode.parentNode.parentNode.parentNode.previousElementSibling);
		obj.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.removeChild(obj.parentNode.parentNode.parentNode.parentNode.parentNode);
	}
	
	function addincident(obj){
		var tmp1 = $(obj.parentNode.parentNode.parentNode.parentNode.parentNode.previousElementSibling.previousElementSibling.previousElementSibling).clone();
		var tmp2 = $(obj.parentNode.parentNode.parentNode.parentNode.parentNode.previousElementSibling.previousElementSibling).clone();
		var tmp3 =$(obj.parentNode.parentNode.parentNode.parentNode.parentNode.previousElementSibling).clone();
		var tmp4 = $(obj.parentNode.parentNode.parentNode.parentNode.parentNode).clone();
		tmp4.children("td").children("div").children("input:first").val("");
		tmp1.insertAfter(obj.parentNode.parentNode.parentNode.parentNode.parentNode);
		tmp2.insertAfter(obj.parentNode.parentNode.parentNode.parentNode.parentNode.nextElementSibling);
		tmp3.insertAfter(obj.parentNode.parentNode.parentNode.parentNode.parentNode.nextElementSibling.nextElementSibling);
		tmp4.insertAfter(obj.parentNode.parentNode.parentNode.parentNode.parentNode.nextElementSibling.nextElementSibling.nextElementSibling);
	}
	function dropline(obj){
		obj.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.removeChild(obj.parentNode.parentNode.parentNode.parentNode.parentNode);
	}
	function addline(obj){
		var tmp = $(obj.parentNode.parentNode.parentNode.parentNode.parentNode).clone();
		tmp.children("td").children("div").children("input:first").val("");
		tmp.insertAfter(obj.parentNode.parentNode.parentNode.parentNode.parentNode);
	}
	
	