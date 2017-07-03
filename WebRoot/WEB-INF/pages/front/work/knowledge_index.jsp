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
	<jsp:include page="/WEB-INF/pages/front/comm/front_header.jsp"><jsp:param name="menu" value="3" /></jsp:include>
	<jsp:include page="/WEB-INF/pages/front/comm/news_move.jsp"></jsp:include>
	<div class="act-index-middlebox">
		<div class="fl act-left-box">
			<div class="hehua-img"></div>
			<div class="act-tt-box">业务领域</div>
			<ul class="act-left-ul">
				<li class="cur"><a href="javascript:void(0);" class="yew01-ico">公司商务业务</a></li>
				<li><a href="javascript:void(0);" class="yew02-ico">知识产权</a></li>
				<li><a href="javascript:void(0);" class="yew03-ico">诉讼仲裁业务</a></li>
				<li><a href="javascript:void(0);" class="yew04-ico">财富管理</a></li>
			</ul>
			<div class="mt10">
				<a class="pw-wa" href="cms/to_common_problem.htm">常见问题<i></i></a>
				<!-- <a class="dy-wa" href="#">华诚通讯订阅 --><i></i></a>
			</div>
		</div>
		<div class="fr actright-box">
			<div class="">
				<div class="fl scl-ywly-ttbg mt5"></div>
				<div class="fr scl-snav-box">
					<img src="static/front/css/images/shouse-ico.png" class="vm mr5">
					<a href="#" class="vm">首页</a>
					<label class="vm">></label>
					<span class="vm">业务领域</span>
				</div>
				<div class="cb"></div>
			</div>
			<div class="knowledge-ccbox mt20">华诚的专业人员具有丰富的经验，可以为每个客户提供个性化服务，满足其特殊需求。 我们的业务范围涉及知识产权的并购、维权及申请，以及经济和知识产权诉讼、投资、公司事务、银行、融资、保险、房地产、商业交易、税务、劳动争议以其他各种在中国的业务。</div>
			<ul class="knowledge-ul clb mt20">
				<li class="cur">公司商务业务</li>
				<li>知识产权</li>
				<li>诉讼仲裁业务</li>
				<li>财富管理</li>
			</ul>
			<div class="scl-knowledge-showbox" >
				<div class="fl mt25">
					<img src="static/front/css/images/qqbg.png">
				</div>
				<div class="fr" style="width:540px;margin-top:55px;">
					<div class="knowledge-ccbox">
						   &#12288;&#12288;公司商务部的客户包括众多世界范围内领先的跨国公司，涵盖了软件、汽车、钢铁、化工、制药、机械、电子、房地产、文化传媒、零售、金融等领域。
					</div>
					<div class="knowledge-ccbox mt10">
						   &#12288;&#12288;公司商务部提供的服务包括投资、公司重组和兼并、证券、期货、融资、合规、知识产权许可和转让、知识产权策略规划、维权和尽职调查、反商业贿赂、反垄断、劳动、破产与清算、商业不动产、税收筹划等法律服务。
					</div>
					<div class="knowledge-ccbox mt10">
						   &#12288;&#12288;同时为公司运作中的每一个环节提供详尽和全面的法律意见。
					</div>
					<div class="knowledge-ccbox mt10">
						   &#12288;&#12288;华诚精于专业和创新的法律和商业咨询服务，善于发挥与政府有关部门、专业中介机构长期联系与合作的优势，有针对地满足客户的不同需求。
					</div>
				</div>
				<div class="cb"></div>
				<div class="bmhd-tt-box" style="margin-top:50px;display:inline-block;">
				<div class="yy-jt-ico"></div>
				相关案例
				<a class="scl-more" href="pub_ListPage.htm">更多</a>
			</div>
			
			<ul class="knowledge-list-ul mt20">
				<c:forEach var="caf" items="${cmsArticleField1}" varStatus="cafstaus">
				<c:if test="${cafstaus.index < 5}">				
				<li>
					<div class="fl"><a href="cmsDetail.htm?item.id=${caf.id}">${dv:e(caf.name,100)}</a></div>
					<div class="fr scl-date-col"><fmt:formatDate value="${caf.createDate}" pattern="yyyy年MM月dd日"/></div>
					<div class="cb"></div>
				</li>
				</c:if>
				</c:forEach>
			</ul>
			</div>
			<div class="scl-knowledge-showbox none">
					<div class="fl" style="width:540px;margin-top:55px;">
						<div class="knowledge-ccbox">
							   &#12288;&#12288;华诚致力于成为为境内外当事人在知识产权领域提供全方位服务的中国律师事务所。其知识产权服务包括专利、商标、集成电路布图设计和著作权领域的申请、异议、无效、转让、检索、备案、商标显著性、专利性分析、侵权分析等。
						</div>
						<div class="knowledge-ccbox mt10">
							   &#12288;&#12288;华诚知识产权代理和咨询业务涵盖化学、生物、医药、机械、电子、通信、光学物理、外观设计、商标、著作权等领域，并为此建立了各类专业部门，依专业领域精心配备经验丰富且具有双语能力的专利代理人、商标代理人、律师、技术人员、专家顾问和翻译。
						</div>
						<div class="knowledge-ccbox mt10">
							   &#12288;&#12288;华诚针对知识产权代理业务特点，制定了规范详尽的业务操作流程和电子自动化管理系统，确保知识产权代理案件在程序上的即时、高效、优质和安全，以防止可能产生的权利冲突和程序差错。
						</div>
					</div>
					<div class="fr mt25">
						<img src="static/front/css/images/qqbg2.png">
					</div>
					<div class="cb"></div>
					<div class="bmhd-tt-box" style="margin-top:50px;display:inline-block;">
				<div class="yy-jt-ico"></div>
					相关案例
					<a class="scl-more" href="pub_ListPage.htm">更多</a>
				</div>
				<ul class="knowledge-list-ul mt20">
				<c:forEach var="caf" items="${cmsArticleField2}" varStatus="cafstaus">	
				<c:if test="${cafstaus.index < 5}">		
					<li>
						<div class="fl">
							<a href="cmsDetail.htm?item.id=${caf.id}">${dv:e(caf.name,100)}</a>
						</div>
						<div class="fr scl-date-col">
							<fmt:formatDate value="${caf.createDate}" pattern="yyyy年MM月dd日"/>
						</div>
						<div class="cb"></div>
					</li>
					</c:if>
					</c:forEach>
				</ul>
			</div>
			<div class="scl-knowledge-showbox none">
				<div class="knowledge-ccbox" style="margin-top:25px;">
					   &#12288;&#12288;华诚拥有一支经验丰富的专业诉讼和仲裁团队，其成员包括前法官、资深律师、法律和知识产权专家和教授。
				</div>
				<div class="knowledge-ccbox mt10">
					   &#12288;&#12288;华诚的诉讼仲裁业务涵盖专利纠纷、商标纠纷、著作权纠纷、不正当竞争及商业秘密纠纷、知识产权许可和转让纠纷、公司治理纠纷、上市公司股权纠纷、公司兼并纠纷、外商投资纠纷、保险和金融纠纷、联营纠纷、销售许可纠纷、贸易纠纷、海商纠纷、不动产纠纷、劳动和劳务纠纷、婚姻家事纠纷以及其他涉案金额巨大且具有社会影响力的民商事纠纷。
				</div>
				<div class="mt20 mb15">
					<img src="static/front/css/images/qqbg3.png">
				</div>
				<div class="knowledge-ccbox mt10">
					   &#12288;&#12288;为了保证服务的优质和高效，华诚根据业务的类型和性质，分别成立专利诉讼部、商标版权诉讼部、民商事诉讼部和证据收集调查部，配备专业律师和专职人员，切实有效执行事务所各业务部门间的紧密协作，在制订严谨的诉讼和仲裁策略基础上，以团队协同办案的方式最大限度地保护委托人的合法权益。
				</div>
				<div class="knowledge-ccbox mt10">
					   &#12288;&#12288;历年来，华诚代理了一系列具有重大影响和代表性的诉讼、仲裁案件，其中许多案件属于中国境内之前尚未出现过的新类型案件，包括但不限于国内首起外国当事人间的专利侵权纠纷案件、首起由法院认定驰名商标的商标侵权纠纷案件、首起外国当事人间因体育赞助合同所提起的合同侵权纠纷案件、涉及中外企业的跨境诉讼和仲裁商事纠纷案件，已成为业界参考和借鉴的典范。
				</div>
				<div class="bmhd-tt-box" style="margin-top:50px;display:inline-block;">
				<div class="yy-jt-ico"></div>
				相关案例
				<a class="scl-more" href="pub_ListPage.htm">更多</a>
			</div>
			
			<ul class="knowledge-list-ul mt20">
				<c:forEach var="caf" items="${cmsArticleField3}" varStatus="cafstaus">	
				<c:if test="${cafstaus.index < 5}">		
					<li>
						<div class="fl">
							<a href="cmsDetail.htm?item.id=${caf.id}">${dv:e(caf.name,100)}</a>
						</div>
						<div class="fr scl-date-col">
							<fmt:formatDate value="${caf.createDate}" pattern="yyyy年MM月dd日"/>
						</div>
						<div class="cb"></div>
					</li>
					</c:if>
					</c:forEach>
				</ul>
			</div>
			<div class="scl-knowledge-showbox none">
				<div class="knowledge-ccbox" style="margin-top:25px;">
				   <div class="title-pbk">财富管理</div><div class="mt10 mb10">华诚拥有一支专业的私人财富管理团队，专注于家族企业、名人明星、高净值人士等私人财富法律风险管理诉讼及非诉服务。与银行、保险公司、会计师事务所以及境内外信托机构、移民机构等财富管理机构合作，为家族企业定制个性化的私人财富管理及传承方案，提供专业化的法律服务。我们从客户角度出发提供高效可靠的法律服务及解决方案，深受客户信任。</div>
					<img src="static/front/css/images/cfgl.jpg">
					<div class="title-pbk">婚姻家事业务</div><div class=" mb10">我们拥有在中国国内较早进行婚姻家事专业化的团队，在上海具有较大影响力与较高社会认可度，亦屡获媒体报道。团队律师不仅理论功底深厚，而且实践经验丰富。业务涵盖婚前规划及婚姻维护、婚姻解除及抚养收养、涉外婚姻及移民教育等。</div>		
					<div class="title-pbk">财富传承业务</div><div class="mt10">我们不仅为您进行财富管理，更为您私人定制财富传承服务。业务涵盖遗嘱设计、起草及见证、遗嘱信托、慈善及遗嘱执行、遗产管理、调查及诉讼、家族企业财富传承规划及涉外、涉港澳台继承等。</div>	
				</div>
				<div class="bmhd-tt-box" style="margin-top:50px;display:inline-block;">
				<div class="yy-jt-ico"></div>相关案例<a class="scl-more" href="pub_ListPage.htm">更多</a>
			</div>
			
			<ul class="knowledge-list-ul mt20">
				<c:forEach var="caf" items="${cmsArticleField4}" varStatus="cafstaus">	
				<c:if test="${cafstaus.index < 5}">		
					<li>
						<div class="fl">
							<a href="cmsDetail.htm?item.id=${caf.id}">${dv:e(caf.name,100)}</a>
						</div>
						<div class="fr scl-date-col">
							<fmt:formatDate value="${caf.createDate}" pattern="yyyy年MM月dd日"/>
						</div>
						<div class="cb"></div>
					</li>
					</c:if>
					</c:forEach>
				</ul>
			</div>
			<script>
			var s='${i}';
			$(function(){
			if(s==null||s==""){
				s=0;
			}
				$(function(){
					$(".act-left-ul li").each(function(i){
						$(".knowledge-ul li").eq(s).addClass("cur").siblings(".knowledge-ul li").removeClass("cur");
						$(".scl-knowledge-showbox").eq(s).show().siblings(".scl-knowledge-showbox").hide();
						$(this).click(function(){
							$(this).addClass("cur").siblings().removeClass("cur");
							
							$(".scl-knowledge-showbox").eq(i).css("marginTop",20);
							$(".knowledge-ul li").eq(i).addClass("cur").siblings(".knowledge-ul li").removeClass("cur");
							$(".scl-knowledge-showbox").eq(i).show().siblings(".scl-knowledge-showbox").hide();
						});
					});
					$(".knowledge-ul li").each(function(i){
						$(".act-left-ul li").eq(s).addClass("cur").siblings(".act-left-ul li").removeClass("cur");
						$(".scl-knowledge-showbox").eq(s).show().siblings(".scl-knowledge-showbox").hide();
						$(this).click(function(){
							$(this).addClass("cur").siblings().removeClass("cur");
							
							$(".scl-knowledge-showbox").eq(i).css("marginTop",20);
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
	<jsp:include page="/WEB-INF/pages/front/comm/front_foot_v2.jsp"></jsp:include>
</body>
</html>
