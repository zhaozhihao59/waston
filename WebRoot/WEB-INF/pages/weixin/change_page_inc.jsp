<%@ page language="java" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="hiddenPage" style="height: 100px ; text-align: center ;line-height:60px;font-size: 10px">
	<div class="no-sj"><c:if test="${param.lastPage == 0}">暂无数据</c:if></div>
<%-- 	<c:if test="${param.lastPage > 0}"><img class="mt30" width="50" height="53" src="static/weixin/images/Preloader_4.GIF"></c:if>
 --%></div>
<script>
	var range = 50; //距下边界长度/单位px
	var totalheight = 0;
	
	var sendForm = '${param.sendForm}';
	var resultDiv = '${param.resultDiv}';
	var currentPage = '${param.currentPage}';
	var lastPage = '${param.lastPage}';
	
	var isLoad = false;// 是否正在载入下一页
	
	if(sendForm == null ){
		sendForm = 'sendForm';
	}
	if(resultDiv == null){
		resultDiv = 'resultDiv';
	}
    $(window).scroll(function(){
		var srollPos = $(window).scrollTop(); //滚动条距顶部距离(页面超出窗口的高度)
		totalheight = parseFloat($(window).height()) + parseFloat(srollPos);
		
		if(($(document).height()-range) <= totalheight && !isLoad) {
			loadNextPage();
		}
	});
		
	function loadNextPage(){
		isLoad = true;
		$('#hiddenPage').html('<img style="width:50px;height:53px;" src="static/weixin/images/Preloader_4.GIF">');
		// 页数+1
		currentPage++;
		$('#'+sendForm).find('.J_currPage').val(currentPage);
		$('#'+sendForm).ajaxSubmit(function(data){
			$('#'+resultDiv).append(data);
			if(currentPage < lastPage && lastPage != 0){	// 后面还有内容
				$('#hiddenPage').text('下拉载入');
				isLoad = false;
			}else if(currentPage >= lastPage){
				$('#hiddenPage').text('已经是最后一页了');
			}
		})
	}
</script>
