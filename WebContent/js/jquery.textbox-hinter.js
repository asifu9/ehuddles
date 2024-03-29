/* 
 * jQuery - Textbox Hinter plugin v1.1
 * http://www.aakashweb.com/
 * Copyright 2012, Aakash Chakravarthy
 * Released under the MIT License.
 */

(function($){
	$.fn.tbHinter = function(options) {

	var defaults = {
		text: 'Enter a text ...',
   		styleClass: ''
	};
	
	var options = $.extend(defaults, options);

	return this.each(function(){
		alert(" hi ss");
		$(this).focus(function(){
			if($(this).val() == options.text){
				$(this).val('');
				$(this).removeClass(options.styleClass);
			}
		});
		
		$(this).blur(function(){
			if($(this).val() == ''){
				$(this).val(options.text);
				$(this).addClass(options.styleClass);
			}
		});
		
		$(this).blur();
		
	});
};
})(jQuery);