$('.carousel').carousel('pause');

//make section height of window
	$(function(){
		$('#intro').css({'height':($(window).height())+'px'});
		$(window).resize(function(){
		$('#intro').css({'height':($(window).height())+'px'});
		});
	});

//custom scrollbar
	$(document).ready(
	function() {  
		$("html").niceScroll({cursorcolor:"#707070",horizrailenabled:false, zindex:9999, cursoropacitymin:.3, background:"#e8e8e8",cursorwidth:"10px",cursorborder:"none"});
		}
	);

//contact form
	$(document).ready(function() {
	var options = {
	target: '.alert',
	beforeSubmit: showRequest,
	success: showResponse
	};
	$('#contactForm').ajaxForm(options);
	});
	function showRequest(formData, jqForm, options) {
	var queryString = $.param(formData);
	return true;
	}
	function showResponse(responseText, statusText)  {
	}
	$.fn.clearForm = function() {
		return this.each(function() {
		var type = this.type, tag = this.tagName.toLowerCase();
		if (tag == 'form')
			return $(':input',this).clearForm();
		if (type == 'text' || type == 'password' || tag == 'textarea')
			this.value = '';
		else if (type == 'checkbox' || type == 'radio')
			this.checked = false;
		else if (tag == 'select')
			this.selectedIndex = -1;
		});
	};

//smooth scroll on page
	$(function() {
		$('#more a, .nav a, .nav li a, .brand, #footer li a').bind('click',function(event){
		var $anchor = $(this);

		$('[data-spy="scroll"]').each(function () {
		var $spy = $(this).scrollspy('refresh')
		});

		$('html, body').stop().animate({
		scrollTop: $($anchor.attr('href')).offset().top -61
		}, 1500,'easeInOutExpo');

		event.preventDefault();
		});
	});

//gallery image hover tooltip trigger
	$("[data-thumb=tooltip]").tooltip();

//collapse menu on click on mobile and tablet devices
	$('.nav a').click(function () { $(".nav-collapse").collapse("hide") });





