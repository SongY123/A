//下拉菜单
$(document).ready(function(){
	$('#selecttype').on('change',function(){
	var obj = $('#selecttype').get(0);
	var opt = obj.options[obj.selectedIndex];
	//alert(opt.text);
	var person = "<tr class=\"row\">\
				<td class=\"col-xs-12\">\
				<div class=\"input-group\">\
						<div class=\"input-group-addon tag\">姓名</div>\
						<input type=\"text\" id=\"personname\" name=\"personname\" class=\"form-username form-control col-xs-6\">\
    			</div>\
    		</td>\
    		<td class=\"col-xs-12\">\
				<div class=\"input-group\">\
					<div class=\"input-group-addon tag\">国家</div>\
    				<input type=\"text\" id=\"countryname\" name=\"countryname\" class=\"form-username form-control col-xs-6\">\
					</div>\
				</td>\
				</tr>\
				\
				<tr class=\"row\">\
				<td class=\"col-xs-12\">\
				<div class=\"input-group\">\
					<div class=\"input-group-addon tag\">地区</div>\
						<input type=\"text\" id=\"regionname\" name=\"regionname\" class=\"form-username form-control col-xs-6\">\
    			</div>\
    		</td>\
    		<td class=\"col-xs-12\">\
				<div class=\"input-group\">\
					<div class=\"input-group-addon tag\">组织</div>\
    				<input type=\"text\" id=\"rolename\" name=\"rolename\" class=\"form-username form-control col-xs-6\">\
					</div>\
				</td>\
				</tr>\
				\
				<tr class=\"row\">\
				<td class=\"col-xs-12\">\
				<div class=\"input-group\">\
					<div class=\"input-group-addon tag\">职位</div>\
						<input type=\"text\" id=\"position\" name=\"position\" class=\"form-username form-control col-xs-6\">\
    			</div>\
    		</td>\
    		<td class=\"col-xs-12\">\
				<div class=\"input-group\">\
					<div class=\"input-group-addon tag\">时间</div>\
    				<input type=\"text\" id=\"time\" name=\"time\"  placeholder=\"[2015-10-24]\" class=\"form-username form-control col-xs-6\" >\
					</div>\
				</td>\
				</tr>\
				\
				<tr class=\"row\">\
				<td class=\"col-xs-12\">\
				<div class=\"input-group\">\
					<div class=\"input-group-addon tag\">其他</div>\
						<input type=\"text\" id=\"other\" name=\"other\" class=\"form-username form-control col-xs-6\" >\
    			</div>\
    		</td>\
				</tr>";
	var country = "<tr class=\"row\">\
				<td class=\"col-xs-12\">\
				<div class=\"input-group\">\
						<div class=\"input-group-addon tag\">国家</div>\
						<input type=\"text\" id=\"countryname\" name=\"countryname\" class=\"form-username form-control col-xs-6\" >\
    			</div>\
    		</td>\
    			<td class=\"col-xs-12\">\
    				<div class=\"input-group\">\
                        <div class=\"input-group-addon tag\">代码</div>\
                        <input type=\"text\" class=\"form-control\" id=\"countrycode\" autocomplete=\"off\">\
                        <div class=\"input-group-btn\">\
                            <button type=\"button\" class=\"btn btn-default dropdown-toggle\" data-toggle=\"\">\
                                <span class=\"caret\"></span>\
                            </button>\
                            <ul class=\"dropdown-menu dropdown-menu-right\" role=\"menu\"></ul>\
                        </div>\
                    </div>\
                    </td>\
                <tr>";
    		
		var region = "<tr class=\"row\">\
				<td class=\"col-xs-12\">\
				<div class=\"input-group\">\
						<div class=\"input-group-addon tag\">地名</div>\
						<input type=\"text\" id=\"regionname\" name=\"regionname\" class=\"form-username form-control col-xs-6\" >\
    			</div>\
    		</td>\
    		<td class=\"col-xs-12\">\
    				<div class=\"input-group\">\
                        <div class=\"input-group-addon tag\">代码</div>\
                        <input type=\"text\" class=\"form-control\" id=\"regioncode\" autocomplete=\"off\">\
                        <div class=\"input-group-btn\">\
                            <button type=\"button\" class=\"btn btn-default dropdown-toggle\" data-toggle=\"\">\
                                <span class=\"caret\"></span>\
                            </button>\
                            <ul class=\"dropdown-menu dropdown-menu-right\" role=\"menu\"></ul>\
                        </div>\
                    </div>\
                    </td>\
				</tr>\
				\
				<tr class=\"row\">\
				<td class=\"col-xs-12\">\
				<div class=\"input-group\">\
					<div class=\"input-group-addon tag\">其他</div>\
						<input type=\"text\" id=\"regioninfor\" name=\"regioninfor\" class=\"form-username form-control col-xs-6\" >\
    			</div>\
    		</td>\
    		<td class=\"col-xs-12\">\
				</td>\
				</tr>";
		var role = "<tr class=\"row\">\
				<td class=\"col-xs-12\">\
				<div class=\"input-group\">\
						<div class=\"input-group-addon tag\">组织</div>\
						<input type=\"text\" id=\"rolename\" name=\"rolename\" class=\"form-username form-control col-xs-6\" >\
    			</div>\
    		</td>\
    		<td class=\"col-xs-12\">\
    				<div class=\"input-group\">\
                        <div class=\"input-group-addon tag\">代码</div>\
                        <input type=\"text\" class=\"form-control\" id=\"rolecode\" autocomplete=\"off\">\
                        <div class=\"input-group-btn\">\
                            <button type=\"button\" class=\"btn btn-default dropdown-toggle\" data-toggle=\"\">\
                                <span class=\"caret\"></span>\
                            </button>\
                            <ul class=\"dropdown-menu dropdown-menu-right\" role=\"menu\"></ul>\
                        </div>\
                    </div>\
                    </td>\
				</tr>\
				\
				<tr class=\"row\">\
				<td class=\"col-xs-12\">\
				<div class=\"input-group\">\
					<div class=\"input-group-addon tag\">地区</div>\
						<input type=\"text\" id=\"regionname\" name=\"regionname\" class=\"form-username form-control col-xs-6\" >\
    			</div>\
    		</td>\
    		<td class=\"col-xs-12\">\
				<div class=\"input-group\">\
					<div class=\"input-group-addon tag\">国家</div>\
    				<input type=\"text\" id=\"countryname\" name=\"countryname\" class=\"form-username form-control col-xs-6\" >\
					</div>\
				</td>\
				</tr>\
				\
				<tr class=\"row\">\
				<td class=\"col-xs-12\">\
				<div class=\"input-group\">\
					<div class=\"input-group-addon tag\">其他</div>\
						<input type=\"text\" id=\"other\" name=\"other\" class=\"form-username form-control col-xs-6\" >\
    			</div>\
    		</td>\
    		<td class=\"col-xs-12\">\
				</td>\
				</tr>";
		var o;
    	if(opt.text=="国家")
    		o = parseToDOM(country);
    	else if(opt.text=="人物")
    		o = parseToDOM(person);
    	else if(opt.text=="地区")
    		o = parseToDOM(region);
    	else if(opt.text=="组织")
    		o = parseToDOM(role);
    	$('#selecttr').nextAll().remove();;
		$('#selecttr').parent().append($(o));
		var countrycodeSuggest = $("#countrycode").bsSuggest({
	        //url: "/rest/sys/getuserlist?keyword=",
	        url: "jsp/mark/datajson/countryvalue.json",
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
		
		var countrycodeSuggest = $("#regioncode").bsSuggest({
	        //url: "/rest/sys/getuserlist?keyword=",
	        url: "jsp/mark/datajson/countryvalue.json",
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
		
		var countrycodeSuggest = $("#rolecode").bsSuggest({
	        //url: "/rest/sys/getuserlist?keyword=",
	        url: "jsp/mark/datajson/rolevalue.json",
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
		
	});
})

function parseToDOM(str){
   var table = document.createElement("tbody");
   if(typeof str == "string")
       table.innerHTML = str;
   return table.childNodes;
}