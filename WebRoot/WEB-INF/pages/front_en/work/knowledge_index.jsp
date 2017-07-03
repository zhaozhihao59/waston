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
<link rel="stylesheet" type="text/css" href="static/front_en/comm/en-wa-index.css" />
</head>
<body>
	<jsp:include page="/WEB-INF/pages/front_en/comm/front_header.jsp"><jsp:param name="menu" value="3" /></jsp:include>
	<jsp:include page="/WEB-INF/pages/front_en/comm/news_move.jsp"></jsp:include>
	<div class="act-index-middlebox">
		<div class="fl act-left-box">
			<div class="hehua-img"></div>
			<div class="act-tt-box">Business domain</div>
			<ul class="act-left-ul">
				<li class="cur"><a href="javascript:void(0);" class="yew01-ico">Business affairs</a></li>
				<li><a href="javascript:void(0);" class="yew02-ico">Intellectual property</a></li>
				<li><a href="javascript:void(0);" class="yew03-ico">Litigation arbitration</a></li>
				<li><a href="javascript:void(0);" class="yew04-ico">Wealth management</a></li>
			</ul>
			<div class="mt10">
				<a class="pw-wa" href="cms_en/to_common_problem_en.htm">Common problem<i></i></a>
				<!-- <a class="dy-wa" href="#">华诚通讯订阅 --><i></i></a>
			</div>
		</div>
		<div class="fr actright-box">
			<div class="">
				<div class="fl scl-ywly-ttbg mt5"></div>
				<div class="fr scl-snav-box">
					<img src="static/front/css/images/shouse-ico.png" class="vm mr5">
					<a href="en/index.htm" class="vm">HOME</a>
					<label class="vm">></label>
					<span class="vm">Business domain</span>
				</div>
				<div class="cb"></div>
			</div>
			<div class="knowledge-ccbox mt20">&nbsp;&nbsp;Watson & Band's qualified group of professionals has a wide range of expertise and experience that allows us to deliver a nuanced and personalized service to address each of our clients' specific needs. In addition to offering a full range of legal services relating to the acquisition, enforcement and prosecution of intellectual property assets, we also assist our clients with commercial and IP litigation, investment, corporate affairs, banking, financing, insurance, real property, commercial transactions, taxation, employment, and numerous other interests in China.</div>
			<ul class="knowledge-ul clb mt20">
				<li class="cur">Corporate and Commercial Services</li>
				<li>Intellectual Property Services</li>
				<li>Litigation and Arbitration Services</li>
				<li>Wealth Management Practice</li>
			</ul>
			<div class="scl-knowledge-showbox" >
				<div class="fl mt25">
					<img src="static/front_en/comm/images/qqbg.png">
				</div>
				<div class="fr" style="width:540px;margin-top:55px;">
					<div class="knowledge-ccbox">
						  &nbsp;&nbsp;Our Corporate and Commercial Department serves many leading multinational enterprises in various industries such as computer software, automobiles, iron & steel, chemicals, pharmaceuticals, machinery, electronics, real estate, culture & media, retail and finance. 
					</div>
					<div class="knowledge-ccbox mt10">
						   &nbsp;&nbsp;Corporate and Commercial Department provides services relating to investment, M&A and corporate restructuring, securities, futures, financing, compliance, IP rights licensing and assignment, IP strategy planning and enforcement, IP due diligence investigations, commercial bribery, anti-monopoly enforcement, Bankruptcy and Liquidation, commercial real estate, tax planning and other fields. 
					</div>
					<div class="knowledge-ccbox mt10">
						  &nbsp;&nbsp;We also provide detailed and comprehensive legal advice covering every step in business operations.
					</div>
					<div class="knowledge-ccbox mt10">
						   &nbsp;&nbsp;To meet particular needs and requirements of our clients, Watson & Band is unmatched in its ability to provide consistent, professional and innovative legal and commercial advice. It does so through its long-term cooperative relationships with government authorities and professional intermediary agencies. 
					</div>
				</div>
				<div class="cb"></div>
				<div class="bmhd-tt-box" style="margin-top:50px;display:inline-block;">
				<div class="yy-jt-ico"></div>
				Relevant Cases
				<a class="scl-more" href="en/pub_ListPage_en.htm">More</a>
			</div>
			
			<ul class="knowledge-list-ul mt20">
				<c:forEach var="caf" items="${cmsArticleField1}" varStatus="cafstaus">
				<c:if test="${cafstaus.index < 5}">				
				<li>
					<div class="fl"><a href="en/cmsDetail_en.htm?item.id=${caf.id}">${dv:e(caf.enName,100)}</a></div>
					<div class="fr scl-date-col"><fmt:formatDate value="${caf.createDate}" pattern="yyyy-MM-dd"/></div>
					<div class="cb"></div>
				</li>
				</c:if>
				</c:forEach>
			</ul>
			</div>
			<div class="scl-knowledge-showbox none">
					<div class="fl" style="width:540px;margin-top:55px;">
						<div class="knowledge-ccbox">
							  &nbsp;&nbsp;IP services offered by Watson & Band encompass the fields of chemistry, biotechnology, pharmaceuticals, machinery, electronics, computers & telecommunications, optical physics, design and trademarks/copyrights. Each division has been established based on the technical specialties of our staff members in their respective fields. They consist of not only bilingual patent and trademark agents with rich experience in their fields but also lawyers, technicians, expert counselors and translators.
						</div>
						<div class="knowledge-ccbox mt10">
							  &nbsp;&nbsp;IP services offered by Watson & Band encompass the fields of chemistry, biotechnology, pharmaceuticals, machinery, electronics, computers & telecommunications, optical physics, design and trademarks/copyrights. Each division has been established based on the technical specialties of our staff members in their respective fields. They consist of not only bilingual patent and trademark agents with rich experience in their fields but also lawyers, technicians, expert counselors and translators.
						</div>
						<div class="knowledge-ccbox mt10">
							  &nbsp;&nbsp;Taking into account clients’ needs for IP services, Watson & Band has established normative and detailed processing rules, and has developed an automatic electronic monitoring system to ensure procedural immediacy, high efficiency, superb quality and tight security for IP services, designed to avoid potential conflicts of interest and procedural errors.
						</div>
					</div>
					<div class="fr mt25">
						<img src="static/front_en/comm/images/qqbg2.png">
					</div>
					<div class="cb"></div>
					<div class="bmhd-tt-box" style="margin-top:50px;display:inline-block;">
				<div class="yy-jt-ico"></div>
					Relevant Cases
					<a class="scl-more" href="en/pub_ListPage_en.htm">More</a>
				</div>
				<ul class="knowledge-list-ul mt20">
				<c:forEach var="caf" items="${cmsArticleField2}" varStatus="cafstaus">	
				<c:if test="${cafstaus.index < 5}">		
					<li>
						<div class="fl">
							<a href="en/cmsDetail_en.htm?item.id=${caf.id}">${dv:e(caf.enName,100)}</a>
						</div>
						<div class="fr scl-date-col">
							<fmt:formatDate value="${caf.createDate}" pattern="yyyy-MM-dd"/>
						</div>
						<div class="cb"></div>
					</li>
					</c:if>
					</c:forEach>
				</ul>
			</div>
			<div class="scl-knowledge-showbox none">
				<div class="knowledge-ccbox" style="margin-top:25px;">
					  &nbsp;&nbsp;Watson & Band has an experienced professional litigation and arbitration team of former judges, senior attorneys, experts in legal and IP rights, and university professors.
				</div>
				<div class="knowledge-ccbox mt10">
					  &nbsp;&nbsp;The litigation and arbitration services of Watson & Band cover various disputes relating to patents, trademarks, copyrights, unfair competition, trade secrets, IP rights licensing and assignment, corporate management, equity of listed companies, corporate M&A, foreign investment, insurance, finance, joint ventures, distribution licensing transactions, maritime issues, real estate, labor, marriage and family, and other civil and commercial cases having significant social impact and involving large sums.
				</div>
				<div class="mt20 mb15">
					<img src="static/front/css/images/qqbg3.png">
				</div>
				<div class="knowledge-ccbox mt10">
					  &nbsp;&nbsp;To ensure the quality and efficiency of our services, we have established specialized departments based on the types of services we offer including patent litigation department, trademark and copyright litigation department, civil and commercial litigation department and evidence collection and investigation group. Professional attorneys and designated employees are assigned to particular departments based on their areas of expertise, so that intimate inter-departmental cooperation can be effectively carried out by well-designed litigation and arbitration strategies that offer maximum protection to our clients’ legitimate rights and interests through efficient teamwork.
				</div>
				<div class="knowledge-ccbox mt10">
					  &nbsp;&nbsp;Over the past several decades, Watson & Band has represented clients in a multitude of influential and representative litigation and arbitration cases, many of which have been successfully resolved as landmark precedents in their respective fields – the first patent infringement case between two foreign parties, the first trademark infringement case in which a disputed trademark was recognized as a “well-known trademark ” by a court, the first breach of contract case between two foreign parties over a sports sponsorship contract, and other cross-border commercial litigation and arbitration cases involving both domestic and foreign companies.
				</div>
				<div class="bmhd-tt-box" style="margin-top:50px;display:inline-block;">
				<div class="yy-jt-ico"></div>
				Relevant Cases
				<a class="scl-more" href="en/pub_ListPage_en.htm">More</a>
			</div>
			
			<ul class="knowledge-list-ul mt20">
				<c:forEach var="caf" items="${cmsArticleField3}" varStatus="cafstaus">	
				<c:if test="${cafstaus.index < 5}">		
					<li>
						<div class="fl">
							<a href="en/cmsDetail_en.htm?item.id=${caf.id}">${dv:e(caf.enName,100)}</a>
						</div>
						<div class="fr scl-date-col">
							<fmt:formatDate value="${caf.createDate}" pattern="yyyy-MM-dd"/>
						</div>
						<div class="cb"></div>
					</li>
					</c:if>
					</c:forEach>
				</ul>
			</div>
			<div class="scl-knowledge-showbox none">
				<div class="knowledge-ccbox" style="margin-top:25px;">
				   <div class="title-pbk">Wealth Management Practice</div><div class="mt10 mb10">Watson & Band’s professional personal wealth management team is dedicated to personal wealth legal risk management, litigation and non-litigation services for family businesses, celebrities and high net worth individuals, etc. By working closely with wealth managers including banks, insurance companies, accounting firms as well as domestic and foreign trusts and immigration agencies, we offer tailored private wealth management and inheritance schemes as well as professional legal services to family businesses. We provide highly efficient and reliable legal services and solutions from the client’s perspective, thereby winning our clients’ trust. </div>
					<img src="static/front/css/images/cfgl.jpg">
					<div class="title-pbk">Marriage and Family Practice</div><div class=" mb10">
Watson & Band’s marriage and family practice began offering marital and family related legal services early on, and has since acquired significant influence and a strong reputation through frequent media exposure in Shanghai. This practice is composed of attorneys who possess solid theoretical foundations as well as extensive practical experience. Our marriage and family practice covers areas ranging from premarital planning and marriage maintenance to divorce, child care, adoption, international marriage and migrant education.</div>		
					<div class="title-pbk">Inheritance Practice</div><div class="mt10">Watson & Band is not only well-equipped to provide wealth management services, but is also capable of offering customized inheritance plans based on each client's personal needs. Our inheritance services include will design, drafting and witnessing; will trusts and philanthropic projects; will execution; inheritance management, investigation and litigation; family business inheritance planning; and cross-border inheritance issues involving foreign countries as well as Hong Kong, Macao, and Taiwan.
</div>	
				</div>
				<div class="bmhd-tt-box" style="margin-top:50px;display:inline-block;">
				<div class="yy-jt-ico"></div>Relevant Cases<a class="scl-more" href="en/pub_ListPage_en.htm">More</a>
			</div>
			
			<ul class="knowledge-list-ul mt20">
				<c:forEach var="caf" items="${cmsArticleField4}" varStatus="cafstaus">	
				<c:if test="${cafstaus.index < 5}">		
					<li>
						<div class="fl">
							<a href="en/cmsDetail_en.htm?item.id=${caf.id}">${dv:e(caf.enName,100)}</a>
						</div>
						<div class="fr scl-date-col">
							<fmt:formatDate value="${caf.createDate}" pattern="yyyy-MM-dd"/>
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
							$(".scl-knowledge-showbox").eq(i).show().animate({opacity:'1',marginTop:'0'},1000).siblings(".scl-knowledge-showbox").hide();
						});
					});
					$(".knowledge-ul li").each(function(i){
						$(".act-left-ul li").eq(s).addClass("cur").siblings(".act-left-ul li").removeClass("cur");
						$(".scl-knowledge-showbox").eq(s).show().siblings(".scl-knowledge-showbox").hide();
						$(this).click(function(){
							$(this).addClass("cur").siblings().removeClass("cur");
							
							$(".scl-knowledge-showbox").eq(i).css("marginTop",20);
							$(".act-left-ul li").eq(i).addClass("cur").siblings(".act-left-ul li").removeClass("cur");
							$(".scl-knowledge-showbox").eq(i).show().animate({opacity:'1',marginTop:'0'},1000).siblings(".scl-knowledge-showbox").hide();
						});
					});
				});
			
			});	
			</script>
		</div>
		<div class="cb"></div>
	</div>
	<jsp:include page="/WEB-INF/pages/front_en/comm/front_foot_v2.jsp"></jsp:include>
</body>
</html>
