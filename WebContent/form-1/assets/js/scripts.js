
jQuery(document).ready(function() {
	
    /*
        Fullscreen background
    */
    
    /*
        Form validation
    */
    $('.login-form input[type="text"], .login-form input[type="password"], .login-form textarea').on('focus', function() {
    	$(this).removeClass('input-error');
    });
    
    $('.login-form').on('submit', function(e) {
    	
    	$(this).find('input[type="text"], input[type="password"], textarea').each(function(){
    		if( $(this).val() == "" ) {
    			e.preventDefault();
    			$(this).addClass('input-error');
    		}
    		else {
    			$(this).removeClass('input-error');
    		}
    	});
    	if($('#form-remember').get(0).checked)
		{
			setCookie('username',$('#form-username').val(),30)
			setCookie('password',$('#form-password').val(),30)
			setCookie('remember','true',30)
		}
		else{
			clearCookie('username');
			clearCookie('password');
			clearCookie('remember');
		}
    	
    });
    
    
});
