
$(document).ready(function() {	
	
	$('.theme').click(function() {		
		var value = $(this).attr("id");	
		var file = "css/"+value+".css";
		
		// clear the selected theme and mark one as selected
		 $('.theme').each(function () {
			 $(this).removeClass('selected');
		 });
		$(this).addClass('selected');
		
		// apply the theme
		$("#theme").attr("href", file);
		 
		return false;
	});
	
	
	$(".prefs-link").pageslide({ modal: true });
	
});


