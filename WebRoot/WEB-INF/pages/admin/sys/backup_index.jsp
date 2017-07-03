<%@ page language="java" pageEncoding="UTF-8"%>
<!doctype html >
<html>
	<head>
		<base href="${BASE_PATH }">
		<title>${BASE_CONFIG.siteName}-后台管理-数据备份管理首页</title>
		<jsp:include page="/WEB-INF/pages/admin/base/inc.jsp" />
		<script type="text/javascript" src="static/admin/sys/backup.js"></script>
	</head>
	<body class="iframe-body">
		<a href="javascript:;" class="ui_button mb10" id="data_backup_btn">数据备份</a>
		<a href="javascript:;" class="ui_button mb10" id="batch_remove_btn">批量删除</a>
		
		<!-- table -->
	    <table id="backUpTable"></table>
	    <div id="pagerBar"></div>
	    <!--/table -->
	    
	    <div id="new_backup_canves" style="display: none">
	    	备份备注:<textarea id="backup_remark" class="ui_textarea" cols="80" rows="8"></textarea>
	    </div>
	    
	</body>
</html>