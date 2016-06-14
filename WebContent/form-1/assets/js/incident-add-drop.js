	function dropincident(obj){
		obj.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.removeChild(obj.parentNode.parentNode.parentNode.parentNode.parentNode.nextElementSibling);
		obj.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.removeChild(obj.parentNode.parentNode.parentNode.parentNode.parentNode.nextElementSibling);
		obj.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.removeChild(obj.parentNode.parentNode.parentNode.parentNode.parentNode.nextElementSibling);
		obj.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.removeChild(obj.parentNode.parentNode.parentNode.parentNode.parentNode);
	}
	
	function addincident(obj){
		var tmp1 = $(obj.parentNode.parentNode.parentNode.parentNode.parentNode).clone();
		var tmp2 = $(obj.parentNode.parentNode.parentNode.parentNode.parentNode.nextElementSibling).clone();
		var tmp3 =$(obj.parentNode.parentNode.parentNode.parentNode.parentNode.nextElementSibling.nextElementSibling).clone();
		var tmp4 = $(obj.parentNode.parentNode.parentNode.parentNode.parentNode.nextElementSibling.nextElementSibling.nextElementSibling).clone();
		//tmp1.children("td").children("div").children("input:first").val("");
		tmp1.insertAfter(obj.parentNode.parentNode.parentNode.parentNode.parentNode.nextElementSibling.nextElementSibling.nextElementSibling);
		tmp2.insertAfter(obj.parentNode.parentNode.parentNode.parentNode.parentNode.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling);
		tmp3.insertAfter(obj.parentNode.parentNode.parentNode.parentNode.parentNode.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling);
		tmp4.insertAfter(obj.parentNode.parentNode.parentNode.parentNode.parentNode.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling);
		rebind();
	}
	function dropline(obj){
		obj.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.removeChild(obj.parentNode.parentNode.parentNode.parentNode.parentNode);
	}
	function addline(obj){
		var tmp = $(obj.parentNode.parentNode.parentNode.parentNode.parentNode).clone();
		tmp.children("td").children("div").children("input:first").val("");
		tmp.children("td").children("div").children().eq(3).val("");
		tmp.insertAfter(obj.parentNode.parentNode.parentNode.parentNode.parentNode);
		rebind();
	}
	function rebind(){
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
	       incidenttype=keyword.id;
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
	       incidenttype=keyword.id;
	   }).on('onUnsetSelectValue', function (e) {
	       console.log("onUnsetSelectValue");
	   });
	}
	