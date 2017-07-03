<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dv" uri="/WEB-INF/tld/default_value.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html >
<html>
<head>
<base href="${BASE_PATH}" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<jsp:include page="/WEB-INF/pages/common/robots.jsp" />
<meta name="baidu-site-verification" content="k66pxbcp2Z" />
<meta property="qc:admins" content="422176645761667301356375636" />
<meta property="wb:webmaster" content="9fe48f4d2c9e0fa7" />
<title>∷WATSON & BAND∷</title>
<meta name="Description" content="WATSON & BAND, 华诚律师事务所" />
<meta name="Keywords" content="WATSON & BAND, 华诚律师事务所" />
<meta name="author" content="华诚律师事务所" />
<meta name="copyright" content="WATSON & BAND" />
<meta name="company" content="WATSON & BAND, 华诚律师事务所" />
<jsp:include page="/WEB-INF/pages/common/front_inc.jsp" />
<link rel="stylesheet" type="text/css" href="static/front/css/wa-index.css" />
</head>
<body>
	<jsp:include page="/WEB-INF/pages/front/comm/front_header.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/pages/front/comm/news_move.jsp"></jsp:include>
	<div class="act-index-middlebox">
		<div class="fl act-left-box">
			<div class="news-left-img"></div>
			<div class="act-tt-box">新闻与出版物</div>
			<ul class="act-left-ul">
				<li >
					<a href="cms_ListPage.htm?item.id=3d5ffe8b-e890-11e4-ae9b-00266c0e7760" " class="xw-ico">
						华诚动态
					</a>
				</li>
				<li>
					<a href="cms_ListPage.htm?item.id=439c9076-e890-11e4-ae9b-00266c0e7760" class="cbw-ico">
						业界动态
					</a>
				</li>
				<li <c:if test="${item.id=='5da5b951-e890-11e4-ae9b-00266c0e7760' }">class="cur"</c:if>>
					<a href="pub_ListPage.htm?item.id=5da5b951-e890-11e4-ae9b-00266c0e7760" class="hcjb-ico">
						出版物
					</a>
				</li>
				<li>
					<a href="cms/video_list.htm" class="yew02-ico">
						精彩视频
					</a>
				</li>
			</ul>
			<div class="mt10">
				<a class="pw-wa" href="cms/to_common_problem.htm">
					常见问题
					<i></i>
				</a>
				<!-- 
				<a class="dy-wa" href="#">
					华诚通讯订阅 -->
					<i></i>
				</a>
			</div>
		</div>
		<div class="fr actright-box">
		
		<input id="itemId" type="hidden" value="${item.id }">
		<div id="hcActive">
			<jsp:include page="/WEB-INF/pages/front/cms/pub_index_inc.jsp"></jsp:include>
		</div>

			 
			
		    
		    
		    
		    <div class="sctr-right-box" style="margin-top:30px;">
				<div class="srb-nav-box2">华诚期刊下载</div>
			</div>
			
		    <div class="mt15">
			    <div class="select-div fl">
			    	 <div class="tc-select-div w200 fl">
								<s:select
									  list="cmsChannels" 
									  listKey="id"
									  listValue="name"
									  headerKey=""
									  headerValue="--请选择--"
									  cssClass="w" 
									  name="" 
									  id="cmsChannels"
							/>
					</div>
			    </div>
			    <div class="select-div fl ml5">
			    	<%-- <select class="w200 ">
				    	<option>请选择</option>
				    	<option>华诚通讯2015年3月刊.pdf</option>
			    	</select> --%>
			    	 <div class="tc-select-div w200 fl">
							<s:select
								  list="cmsChannels" 
								  listKey="id"
								  listValue="name"
								  headerKey=""
								  headerValue="--请选择--"
								  cssClass="w" 
								  name="" 
								  id="cmsArticle"
						/>
					</div>
		       </div>
			  <a onclick="dowloadFile()" href="javascript:void(0);" class="scl-xz-btn fl ml10">下载</a>
			    <div class="cb"></div>
		    </div>
		    
		    
		     <div class="sctr-right-box" style="margin-top:10px;">
				<div class="srb-nav-box2">华诚案例</div>
			</div>
			<div id="hcActive1">
				<jsp:include page="/WEB-INF/pages/front/cms/pub_index_inc2.jsp"></jsp:include>
			</div>
		
		</div>
		<div class="cb"></div>
	</div>
	<jsp:include page="/WEB-INF/pages/front/comm/front_foot_v2.jsp"></jsp:include>
</body>
<script type="text/javascript">
		// 下拉列表  选择一部请求
		var cmsChannels = $("#cmsChannels");
		 cmsChannels.change(function(){
			$.post(base+"pub_ListPage_inc.htm",{"item.channelId":cmsChannels.val()},function(data){
			$("#cmsArticle").children().remove();
			$("#cmsArticle").html("<option value='-1' >-- 请选择 --</option>");
			var dataJson = JSON.parse(data.message);
			$.each(dataJson.cmsArticleList,function(i,cur){
				var opt = $("<option value='"+cur.id+"' >"+cur.name+"</option>");
				$("#cmsArticle").append(opt);
			});
			},"json");
		 });
		
	//下载模板
	function dowloadFile(){
		var id = $("#cmsArticle").val();
		var cmsChannels = $("#cmsChannels").val();
		if(id == -1){
			$.dialog.tips("请选择上传的文件要下载的文件");
			return false;
		}
		if(cmsChannels == ''){
			$.dialog.tips("请选择期刊类型");
			return false;
		}
		
		window.location.href = basePath+"dowloadFile.htm?selIds="+id;
	}
</script>
</html>
