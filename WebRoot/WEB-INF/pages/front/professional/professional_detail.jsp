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
	<jsp:include page="/WEB-INF/pages/front/comm/front_header.jsp">
		<jsp:param name="menu" value="4" />
	</jsp:include>
	<jsp:include page="/WEB-INF/pages/front/comm/news_move.jsp"></jsp:include>
	<div class="act-index-middlebox">
		<input id="item.sort" value="${item.sort}" type="hidden" />
		<div class="fl act-left-box">
			<div class="hehua-img"></div>
			<div class="act-tt-box">专业人员</div>
			<ul class="act-left-ul">
				<li name="maxtype" class="cur" data1="0"><a
					href="javascript:void(0);" class="yew01-ico"> 合伙人 </a></li>
				<li name="maxtype" data1="1"><a href="javascript:void(0);"
					class="yew02-ico"> 高级顾问 </a></li>
				<li name="maxtype" data1="2"><a href="javascript:void(0);"
					class="yew04-ico"> 专利代理人 </a></li>
				<li name="maxtype" data1="3"><a href="javascript:void(0);"
					class="yew03-ico"> 律师 </a></li>
			</ul>
			<div class="mt10">
				<a class="pw-wa" href="${BASE_PATH}cms/to_common_problem.htm">
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
			<div class="">
				<div class="scl-pro-ttbg mt5"></div>
			</div>
			<div class="knowledge-ccbox mt20">
				华诚出色的法律团队由经验丰富的律师组成，很多都在国内外受过良好的教育，可熟练运用英文、日文、意大利语和俄罗斯语。通过律师的团结协作，我们能够提供高质量的服务。团队中的每个成员都具有独特的背景和经验，可以更好的协助客户。
			</div>
			
			<div class="professional-ccbox">
				<div class="professional-imgbox fl">
					<img src="${item.photo }">
				</div>
				<div class="professional-sccbox fr">
					<div class="psc-name mt10">${item.name }</div>
					<div class="psc-mail-box mt10">${item.email }</div>
<!-- 					<div class="psc-zz-box mt10">${item.typeName }</div> -->
					<div class="psc-zz-box mt10">
					业务：${item.business }
					<c:if test="${empty item.business}">
					无
					</c:if>
					</div>
					<div class="psc-zz-box mt10">
					头衔：${item.qualification }
					<c:if test="${empty item.qualification}">
					无
					</c:if>
					</div>
					<div class="psc-lan-box mt10">
					工作语言：${item.languageName }
					<c:if test="${empty item.languageName}">
					无
					</c:if>
					</div>
				</div>
				<div class="cb"></div>
			</div>
			
			<div class="professional-scccontentbox">
				<div>
					${item.desc }      
				</div>
			</div>
			<div class="pde-cz-btn">
				<a href="${BASE_PATH}front/professional/pre_page.htm?selIds=${item.id }&item.sort=${item.sort}">上一页</a>
				<a href="${BASE_PATH}front/professional/next_page.htm?selIds=${item.id }&item.sort=${item.sort}">下一页</a>
				<a href="${BASE_PATH}front/professional/toProfessional.htm">返回列表</a>
			</div>
			
		</div>
		<div class="cb"></div>
	</div>
	<script>
				//选中样式
				//合伙人和高级顾问
				if ("${typeSession}" != null && "${typeSession}" != "") {
					$("li[name=maxtype]").removeClass("cur");
					$("li[data1=${typeSession}]").addClass("cur");
				}
				$("li[name=maxtype]")
						.click(
								function() {
									//传入大类参数
									var type = $(this).attr("data1");
									//如果tpye等于1或者等于0为合伙人或者高级顾问，如果不等，则是律师
									window.location.href = "${BASE_PATH}front/professional/toProfessional.htm?condition.type="
											+ type;
								});
			</script>
	<jsp:include page="/WEB-INF/pages/front/comm/front_foot_v2.jsp"></jsp:include>
</body>
</html>
