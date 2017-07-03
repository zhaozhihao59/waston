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
	href="static/front_jp/comm/jp-wa-index.css" />
</head>
<body>
	<jsp:include page="/WEB-INF/pages/front_jp/comm/front_header.jsp"><jsp:param
			name="menu" value="4" /></jsp:include>
	<jsp:include page="/WEB-INF/pages/front_jp/comm/news_move.jsp"></jsp:include>
	<div class="act-index-middlebox">
		<input id="item.sort" value="${item.sort}" type="hidden" />
		<div class="fl act-left-box">
			<input id="item.sort" value="${item.sort}" type="hidden" />
			<div class="hehua-img"></div>
			<div class="act-tt-box">专业人员</div>
			<ul class="act-left-ul">

				<li name="maxtype" class="cur" data1="0"><a
					href="javascript:void(0);" class="yew01-ico"> パートナー </a></li>
				<li name="maxtype" data1="1"><a href="javascript:void(0);"
					class="yew02-ico"> シニアコンサルタント </a></li>
				<li name="maxtype" data1="2"><a href="javascript:void(0);"
					class="yew04-ico"> 弁理士 </a></li>
				<li name="maxtype" data1="3"><a href="javascript:void(0);"
					class="yew03-ico"> 弁護士 </a></li>
			</ul>
			<div class="mt10">
				<a class="pw-wa" href="cms/to_common_problem.htm"> 言語 <i></i>
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
			<div class="knowledge-ccbox mt20">
				華誠は経験豊富な弁護士により編成されています。その多くの弁護士は国内外でのさまざまな経験があり、語学も英語、日本語、イタリア語、またロシア語に精通するなど高い教育を受けています。優れた弁護士のチームワークと協力関係による高品位のサービスを提供することで、クライアントからご評価をいただいております。各メンバーは、それぞれ独自のバックグラウンドやクライアントとの業務を通じて、さまざまな経験があります。
			</div>

			<div class="professional-ccbox">
				<div class="professional-imgbox fl">
					<img src="${item.photo }">
				</div>
				<div class="professional-sccbox fr">
					<div class="psc-name mt10">${item.nameJp }</div>
					<div class="psc-mail-box mt10">${item.email }</div>
					<!-- 					<div class="psc-zz-box mt10">${item.typeName }</div> -->
					<div class="psc-zz-box mt10">
						業務：${item.business }
						<c:if test="${empty item.business}">
					无
					</c:if>
					</div>
					<div class="psc-zz-box mt10">
						肩書き：${item.qualification }
						<c:if test="${empty item.qualification}">
					无
					</c:if>
					</div>
					<div class="psc-lan-box mt10">
						言語：${item.languageName }
						<c:if test="${empty item.languageName}">
					无
					</c:if>
					</div>
				</div>
				<div class="cb"></div>
			</div>
			<div class="professional-scccontentbox">
				<div>${item.descJp }</div>
			</div>
			<div class="pde-cz-btn">
				<a
					href="front/professional_jp/pre_page.htm?selIds=${item.id }&item.sort=${item.sort}">上一頁</a>
				<a
					href="front/professional_jp/next_page.htm?selIds=${item.id }&item.sort=${item.sort}">次のページ</a>
				<a href="jp/toProfessional_jp.htm">戻りリスト</a>
			</div>

		</div>
		<div class="cb"></div>
	</div>
	<script>
		//选中样式
		//合伙人和高级顾问

		if ("${typeSessionJp}" != null && "${typeSessionJp}" != "") {
			$("li[name=maxtype]").removeClass("cur");
			$("li[data1=${typeSessionJp}]").addClass("cur");
		}
		$("li[name=maxtype]")
				.click(
						function() {
							//传入大类参数
							var type = $(this).attr("data1");
							//如果tpye等于1或者等于0为合伙人或者高级顾问，如果不等，则是律师
							window.location.href = "${BASE_PATH}front/professional_jp/index.htm?condition.type="
									+ type;
						});
	</script>
	<jsp:include page="/WEB-INF/pages/front_jp/comm/front_foot_v2.jsp"></jsp:include>
</body>
</html>
