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
<link rel="stylesheet" type="text/css" href="static/front_jp/comm/jp-wa-index.css" />
</head>
<body>
	<jsp:include page="/WEB-INF/pages/front_jp/comm/front_header.jsp"><jsp:param name="menu" value="2" /></jsp:include>
	<jsp:include page="/WEB-INF/pages/front_jp/comm/news_move.jsp"></jsp:include>
	<div class="act-index-middlebox">
		<div class="fl act-left-box">
			<div class="hehua-img"></div>
			<div class="act-tt-box">採用情報</div>
			<ul class="act-left-ul">
				<li class="cur">
					<a href="javascript:void(0);" class="yew01-ico">
						弁護士と法律アシスト　
					</a>
				</li>
				<li>
					<a href="javascript:void(0);" class="yew02-ico">
						特許弁理士
					</a>
				</li>
				<li>
					<a href="javascript:void(0);" class="yew03-ico">
						一般事務スタッフ
					</a>
				</li>
			</ul>
			<div class="mt10">
				<a class="pw-wa" href="cms_jp/to_common_problem_jp.htm">
					よくある問題
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
			<div class="">
				<div class="scl-gzjh-ttbg mt5"></div>
			</div>
			<div class="knowledge-ccbox mt20">
				華誠でのキャリアとして、法律専攻の学生や理工系専門及び一般事務スタッフは、中国で最も優秀な法律スペシャリストと一緒に法律業務を経験することができます。私達と一緒にチームワークを高めていくことのできる、法律分野やその他関連する専門分野における優れた人材を求めています。
			</div>
			
			<div class="job-opp-cbox mt20" style="display:inline-block;">
				<div class="jobopp-nav-box">
					<ul class="jobopp-ul clb" style="margin-top:-5px;">
						<li class="cur">弁護士と法律アシスト</li>
						<li>特許弁理士</li>
						<li>一般事務スタッフ</li>
					</ul>
				</div>
				<div class="scl-knowledge-showbox" >
				<c:forEach var="ca1" items="${cmsArticle1}">
		<div>
		 <div class="fr " style="width:883px;margin:20px auto;">${ca1.jpContent}</div>  
		</div>
		</c:forEach>
		</div>
		<div class="scl-knowledge-showbox none">
		<c:forEach var="ca2" items="${cmsArticle2}">
		<div>
 			<div class="fr " style="width:883px;margin:20px auto;">${ca2.jpContent}</div>
		</div>
		</c:forEach>
				</div>
		<div class="scl-knowledge-showbox none">
		
		<s:iterator var="ca3" value="cmsArticle3">
			<s:property value="jpContent" escape="false" />
		</s:iterator>
		<%-- <c:forEach var="ca3" items="${cmsArticle3}">
		<div class="fr " style="width:900px;margin:0 auto;">
			<s:property value="ca3.content"/>
		</div>
		</c:forEach> --%>
				</div>
			
			</div>
			
			
			<script>
			var s='${i}';
			$(function(){
			if(s==null||s==""){
				s=0;
			}
				$(function(){
					$(".act-left-ul li").each(function(i){
						$(".jobopp-ul li").eq(s).addClass("cur").siblings(".jobopp-ul li").removeClass("cur");
						$(".scl-knowledge-showbox").eq(s).show().siblings(".scl-knowledge-showbox").hide();
						$(this).click(function(){
							$(this).addClass("cur").siblings().removeClass("cur");
							
							$(".jobopp-ul li").eq(i).addClass("cur").siblings(".jobopp-ul li").removeClass("cur");
							$(".scl-knowledge-showbox").eq(i).show().siblings(".scl-knowledge-showbox").hide();
						});
					});
					$(".jobopp-ul li").each(function(i){
						$(".act-left-ul li").eq(s).addClass("cur").siblings(".act-left-ul li").removeClass("cur");
						$(".scl-knowledge-showbox").eq(s).show().siblings(".scl-knowledge-showbox").hide();
						$(this).click(function(){
							$(this).addClass("cur").siblings().removeClass("cur");
							
							$(".act-left-ul li").eq(i).addClass("cur").siblings(".act-left-ul li").removeClass("cur");
							$(".scl-knowledge-showbox").eq(i).show().siblings(".scl-knowledge-showbox").hide();
						});
					});
				});
			
			});	
			</script>
		</div>
		<div class="cb"></div>
	</div>
	<jsp:include page="/WEB-INF/pages/front_jp/comm/front_foot_v2.jsp"></jsp:include>
</body>
</html>
