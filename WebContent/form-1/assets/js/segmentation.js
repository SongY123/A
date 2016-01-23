$(document).ready(function(){
	
	context.init({preventDoubleContext: false});
	
	context.attach('.inline-menu', [
		{header: 'Options'},
		{text: 'Open', href: '#'},
		{text: 'Open in new Window', href: '#'},
		{divider: true},
		{text: 'Copy', href: '#'},
		{text: 'Dafuq!?', href: '#'}
	]);
	
	context.attach('#contenttext', [
		
		{header: '修改'},
		{text: '重新分词',href:"#",action: function(e){
			if(window.getSelection) { 
				var textObj = document.getElementById("contenttext"); 
				var selectedText = window.getSelection().toString(); 
				//alert(selectedText);
				var start = window.getSelection().anchorOffset;
				var end = window.getSelection().focusOffset;
				//alert(start);
				//alert(end);
				if(start<end){
					var anchornode = window.getSelection().anchorNode.parentNode;
					var focusnode = window.getSelection().focusNode.parentNode;
					var color1;
					if(anchornode.previousSibling==null)
						color1 = "blue";
					else color1=anchornode.previousSibling.style.color;
					var color2;
					if(focusnode.nextSibling.nodeName=="#text")
						color2 = "blue";
					else 
						color2 = focusnode.nextSibling.style.color;
					//alert(color1);
					//alert(color2);
					var color3;
					if((color1=="blue"&&color2=="red")||(color2=="blue"&&color1=="red"))
						color3="green";
					else if((color1=="blue"&&color2=="green")||(color2=="blue"&&color1=="green"))
						color3="red";
					else if((color1=="red"&&color2=="green")||(color2=="red"&&color1=="green"))
						color3="blue";
					else if(color1==color2&&color1=="red")
						color3 = "blue";
					else if(color1==color2&&color1=="blue")
						color3 = "red";
					else if(color1==color2&&color1=="green")
						color3 = "red";
					for(var node = anchornode;node!=focusnode.nextSibling&&node!=null;node = node.nextSibling){
						node.style.color = color3;
					}
				}
				else{
					var anchornode = window.getSelection().anchorNode.parentNode;
					var focusnode = window.getSelection().focusNode.parentNode;
					var color1;
					if(focusnode.previousSibling==null)
						color1 = "blue";
					else color1=focusnode.previousSibling.style.color;
					var color2;
					if(anchornode.nextSibling.nodeName=="#text")
						color2 = "blue";
					else 
						color2 = anchornode.nextSibling.style.color;
					//alert(color1);
					//alert(color2);
					var color3;
					if((color1=="blue"&&color2=="red")||(color2=="blue"&&color1=="red"))
						color3="green";
					else if((color1=="blue"&&color2=="green")||(color2=="blue"&&color1=="green"))
						color3="red";
					else if((color1=="red"&&color2=="green")||(color2=="red"&&color1=="green"))
						color3="blue";
					else if(color1==color2&&color1=="red")
						color3 = "blue";
					else if(color1==color2&&color1=="blue")
						color3 = "red";
					else if(color1==color2&&color1=="green")
						color3 = "red";
					for(var node = anchornode;node!=focusnode.previousSibling&&node!=null;node = node.previousSibling){
						node.style.color = color3;
					}
				}
			}}
		}
	]);
	
	context.settings({compress: true});
	
	/*
	context.attach('html', [
		{header: 'Compressed Menu'},
		{text: 'Back', href: '#'},
		{text: 'Reload', href: '#'},
		{divider: true},
		{text: 'Save As', href: '#'},
		{text: 'Print', href: '#'},
		{text: 'View Page Source', href: '#'},
		{text: 'View Page Info', href: '#'},
		{divider: true},
		{text: 'Inspect Element', href: '#'},
		{divider: true},
		{text: 'Disable This Menu', action: function(e){
			e.preventDefault();
			context.destroy('html');
			alert('html contextual menu destroyed!');
		}},
		{text: 'Donate?', action: function(e){
			e.preventDefault();
			$('#donate').submit();
		}}
	]);
	*/
	
	
	$(document).on('mouseover', '.me-codesta', function(){
		$('.finale h1:first').css({opacity:0});
		$('.finale h1:last').css({opacity:1});
	});
	
	$(document).on('mouseout', '.me-codesta', function(){
		$('.finale h1:last').css({opacity:0});
		$('.finale h1:first').css({opacity:1});
	});
	
	$(".contenttext").mouseup(function (e) {
		var txt;
		var parentOffset = $(this).offset();
		var x = e.pageX - parentOffset.left;
		var y = e.pageY - parentOffset.top;
		txt = window.getSelection();
		if (txt.toString().length > 1) {
			alert(txt);
		}
	});
	
});