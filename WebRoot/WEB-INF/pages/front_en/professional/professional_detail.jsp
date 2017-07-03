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
<link rel="stylesheet" type="text/css"
	href="static/front_en/comm/en-wa-index.css" />
</head>
<body>
	<jsp:include page="/WEB-INF/pages/front_en/comm/front_header.jsp"><jsp:param
			name="menu" value="4" /></jsp:include>
	<jsp:include page="/WEB-INF/pages/front_en/comm/news_move.jsp"></jsp:include>
	<div class="act-index-middlebox">
		<input id="item.sort" value="${item.sort}" type="hidden" />
		<div class="fl act-left-box">
			<div class="hehua-img"></div>
			<div class="act-tt-box">PROFESSIONAL</div>
			<ul class="act-left-ul">
				<li name="maxtype" class="cur" data1="0"><a
					href="javascript:void(0);" class="yew01-ico"> Partner </a></li>
				<li name="maxtype" data1="1"><a href="javascript:void(0);"
					class="yew02-ico"> Senior Consultant </a></li>
				<li name="maxtype" data1="2"><a href="javascript:void(0);"
					class="yew04-ico"> Patent Agency </a></li>
				<li name="maxtype" data1="3"><a href="javascript:void(0);"
					class="yew03-ico"> Lawyer </a></li>
			</ul>
			<div class="mt10">
				<a class="pw-wa" href="cms/to_common_problem.htm">
					Common_problem <i></i>
				</a>
				<!-- 
				<a class="dy-wa" href="#">
					华诚通讯订阅 -->
				<i></i> </a>
			</div>
		</div>
		<div class="fr actright-box">
			<div class="">
				<div class="scl-pro-ttbg mt5"></div>
			</div>
			<div class="knowledge-ccbox mt20">Watson & Band has assembled
				an impressive team of capable, well-educated lawyers who possess a
				variety of local and international experience and proficiency in
				languages such as English, Japanese, Italian and Russian. By working
				together and pooling these talents, we are able to deliver the
				high-quality services that have won us praise. Each member has a
				unique background and set of experiences that he or she can draw
				from to better assist our clients. To learn more about a lawyer,
				please select a practice area or a name from the list below.</div>

			<div class="professional-ccbox">
				<div class="professional-imgbox fl">
					<img src="${item.photo }">
				</div>
				<div class="professional-sccbox fr">
					<div class="psc-name mt10">${item.nameEn }</div>
					<div class="psc-mail-box mt10">${item.email }</div>
					<!-- 					<div class="psc-zz-box mt10">${item.typeName }</div> -->
					<div class="psc-zz-box mt10">
						Business：${item.businessEn }
						<c:if test="${empty item.businessEn}">
					NULL
					</c:if>
					</div>
					<div class="psc-zz-box mt10">
						Qualification：${item.qualificationEn }
						<c:if test="${empty item.qualificationEn}">
					NULL
					</c:if>
					</div>
					<div class="psc-lan-box mt10">
						Languages ：${item.languageName }
						<c:if test="${empty item.languageName}">
					NULL
					</c:if>
					</div>
				</div>
				<div class="cb"></div>
			</div>

			<div class="professional-scccontentbox">
				<div>${item.descEn }</div>
			</div>
			<div class="pde-cz-btn">
				<a
					href="front/professional_en/pre_page_en.htm?selIds=${item.id }&item.sort=${item.sort}">Previous
					page</a> <a
					href="front/professional_en/next_page_en.htm?selIds=${item.id }&item.sort=${item.sort}">Next
					page</a> <a href="en/toProfessional_en.htm">Return list</a>
			</div>

		</div>
		<div class="cb"></div>
	</div>
	<script>
		//选中样式
		//合伙人和高级顾问
		if ("${typeSessionEn}" != null && "${typeSessionEn}" != "") {
			$("li[name=maxtype]").removeClass("cur");
			$("li[data1=${typeSessionEn}]").addClass("cur");
		}
		$("li[name=maxtype]")
				.click(
						function() {
							//传入大类参数
							var type = $(this).attr("data1");
							//如果tpye等于1或者等于0为合伙人或者高级顾问，如果不等，则是律师
							window.location.href = "${BASE_PATH}front/professional_en/index.htm?condition.type="
									+ type
						});
	</script>
	<jsp:include page="/WEB-INF/pages/front_en/comm/front_foot_v2.jsp"></jsp:include>
</body>
</html>
