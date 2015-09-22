function sendEmail() {
	var subject = $("#sendmail #email").value();
	var content = $("#sendmail #content").value();
	$.ajax({
		url: '/send-email.php',
		data: 'subject=' + $subject + '&content=' + $content,
		error: function() {
			$('#info').html('<p>An error has occurred</p>');
		},
		success: function(data) {
			alert(data);
		},
		type: 'POST'
	});
};

$(function () {
	$('a').smoothScroll();
})