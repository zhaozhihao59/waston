$(document).ready(function(){
	//TAB 切换
	$('#free').click(function(){
		$('.orer-tab-on').removeClass('orer-tab-on');
		$(this).addClass('orer-tab-on');
		$('#t_free').show();
		$('#t_camp').hide();
		$('#t_liner').hide();
		$('#t_hotel').hide();
		$('#t_ticket').hide();
		$('#t_rental').hide();
	});
	
	$('#camp').click(function(){
		$('.orer-tab-on').removeClass('orer-tab-on');
		$(this).addClass('orer-tab-on');
		$('#t_free').hide();
		$('#t_camp').show();
		$('#t_liner').hide();
		$('#t_hotel').hide();
		$('#t_ticket').hide();
		$('#t_rental').hide();
	});
	
	$('#liner').click(function(){
		$('.orer-tab-on').removeClass('orer-tab-on');
		$(this).addClass('orer-tab-on');
		$('#t_free').hide();
		$('#t_camp').hide();
		$('#t_liner').show();
		$('#t_hotel').hide();
		$('#t_ticket').hide();
		$('#t_rental').hide();
	});
	
	$('#hotel').click(function(){
		$('.orer-tab-on').removeClass('orer-tab-on');
		$(this).addClass('orer-tab-on');
		$('#t_free').hide();
		$('#t_camp').hide();
		$('#t_liner').hide();
		$('#t_hotel').show();
		$('#t_ticket').hide();
		$('#t_rental').hide();
	});
	
	$('#ticket').click(function(){
		$('.orer-tab-on').removeClass('orer-tab-on');
		$(this).addClass('orer-tab-on');
		$('#t_free').hide();
		$('#t_camp').hide();
		$('#t_liner').hide();
		$('#t_hotel').hide();
		$('#t_ticket').show();
		$('#t_rental').hide();
	});
	
	$('#rental').click(function(){
		$('.orer-tab-on').removeClass('orer-tab-on');
		$(this).addClass('orer-tab-on');
		$('#t_free').hide();
		$('#t_camp').hide();
		$('#t_liner').hide();
		$('#t_hotel').hide();
		$('#t_ticket').hide();
		$('#t_rental').show();
	});
	
	
});