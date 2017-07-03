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
			<div class="zz-img"></div>
			<div class="act-tt-box">业务领域</div>
			<ul class="act-left-ul">
				<li><a href="cms/business_field.htm?i=0" class="jzhd-ico">公司商务业务</a></li>
				<li><a href="cms/business_field.htm?i=1" class="jzhd-ico">知识产权</a></li>
				<li><a href="cms/business_field.htm?i=2" class="jzhd-ico">诉讼仲裁业务</a></li>
			</ul>
			<div class="mt10">
				<a class="pw-wa" href="cms/to_common_problem.htm">常见问题<i></i></a>
				<!-- <a class="dy-wa" href="#">华诚通讯订阅<i></i></a> -->
			</div>
		</div>
		<div class="fr actright-box">
			<div class="sctr-right-box">
				<div class="srb-nav-box fl">讲座活动</div>
				<div class="fr srb-ncbox mr15">
					<a href="#">首页</a>
					<label>></label>
					<span>讲座活动</span>
				</div>
				<div class="cb"></div>
			</div>
			
			<div class="bmhd-tt-box"><div class="yy-jt-ico"></div>正在报名的活动</div>
			<div class="hd-list-box">
				<div class="hd-imgbox fl">
					<a href="#"><img src="static/front/css/images/test-img.png"></a>
				</div>
				<div class="fr hd-ccbox">
					<div class="hd-cc-tt"><a href="#">哈哈讲堂-商标的申请和保护</a></div>
					<div class="addr-date mt5">
						<label>活动日期：</label><span>2015-03-15 ~ 2015-03-28</span>
						<label class="ml30">活动地点：</label><span>上海胜伦斯大酒店3楼</span>
					</div>
					<div class="scl-act-content mt5">
						${dv:e("应上海福寿园实业发展有限公司的邀请，我所高级顾问柯晓军与商标外观部律师梅远于2015年3月18日下午在福寿园公司青浦福苑山庄闻礼厅为福寿园公司的中高层管理人员进行了企业商标实务培训。本次培训围绕商",180)}
					</div>
					<a href="#" class="ljbm-btn">立即报名</a>
				</div>
				<div class="cb"></div>
			</div>
			<div class="bmhd-tt-box">
				<div class="yy-jt-ico"></div>
				活动预告
			</div>
			<div class="scl-hdyg-box">
				<ul class="sclhdyg-ul clb">
					<li>
						<div class="sclhdyg-imgbox"><a href="#"><img src="static/front/css/images/test-img.png"></a></div>
						<div class="sclhdyg-name">
							<a href="activitiesDetail.htm">${dv:e("华诚所入选上海法院首批一级破产管理人名册",40)}</a>
						</div>
						<div class="sclhdyg-time-box">
							时间：2015-03-15 ~ 2015-03-28
						</div>
					</li>
					<li>
						<div class="sclhdyg-imgbox"><a href="#"><img src="static/front/css/images/test-img.png"></a></div>
						<div class="sclhdyg-name">
							<a href="#">${dv:e("华诚所入选上海法院首批一级破产管理人名册",40)}</a>
						</div>
						<div class="sclhdyg-time-box">
							时间：2015-03-15 ~ 2015-03-28
						</div>
					</li>
					<li>
						<div class="sclhdyg-imgbox"><a href="#"><img src="static/front/css/images/test-img.png"></a></div>
						<div class="sclhdyg-name">
							<a href="#">${dv:e("华诚所入选上海法院首批一级破产管理人名册",40)}</a>
						</div>
						<div class="sclhdyg-time-box">
							时间：2015-03-15 ~ 2015-03-28
						</div>
					</li>
					<li>
						<div class="sclhdyg-imgbox"><a href="#"><img src="static/front/css/images/test-img.png"></a></div>
						<div class="sclhdyg-name">
							<a href="#">${dv:e("华诚所入选上海法院首批一级破产管理人名册",40)}</a>
						</div>
						<div class="sclhdyg-time-box">
							时间：2015-03-15 ~ 2015-03-28
						</div>
					</li>
				</ul>
			</div>
			
			<div class="bmhd-tt-box">
				<div class="yy-jt-ico"></div>
				历届讲座
				<a href="#" class="scl-more">更多</a>
			</div>
			<div class="scl-hdyg-box">
				<ul class="sclhdyg-ul clb">
					<li>
						<div class="sclhdyg-imgbox"><a href="#"><img src="static/front/css/images/test-img.png"></a></div>
						<div class="sclhdyg-name">
							<a href="#">${dv:e("华诚所入选上海法院首批一级破产管理人名册",40)}</a>
						</div>
						<div class="sclhdyg-time-box">
							时间：2015-03-15 ~ 2015-03-28
						</div>
					</li>
					<li>
						<div class="sclhdyg-imgbox"><a href="#"><img src="static/front/css/images/test-img.png"></a></div>
						<div class="sclhdyg-name">
							<a href="#">${dv:e("华诚所入选上海法院首批一级破产管理人名册",40)}</a>
						</div>
						<div class="sclhdyg-time-box">
							时间：2015-03-15 ~ 2015-03-28
						</div>
					</li>
					<li>
						<div class="sclhdyg-imgbox"><a href="#"><img src="static/front/css/images/test-img.png"></a></div>
						<div class="sclhdyg-name">
							<a href="#">${dv:e("华诚所入选上海法院首批一级破产管理人名册",40)}</a>
						</div>
						<div class="sclhdyg-time-box">
							时间：2015-03-15 ~ 2015-03-28
						</div>
					</li>
					<li>
						<div class="sclhdyg-imgbox"><a href="#"><img src="static/front/css/images/test-img.png"></a></div>
						<div class="sclhdyg-name">
							<a href="#">${dv:e("华诚所入选上海法院首批一级破产管理人名册",40)}</a>
						</div>
						<div class="sclhdyg-time-box">
							时间：2015-03-15 ~ 2015-03-28
						</div>
					</li>
					<li>
						<div class="sclhdyg-imgbox"><a href="#"><img src="static/front/css/images/test-img.png"></a></div>
						<div class="sclhdyg-name">
							<a href="#">${dv:e("华诚所入选上海法院首批一级破产管理人名册",40)}</a>
						</div>
						<div class="sclhdyg-time-box">
							时间：2015-03-15 ~ 2015-03-28
						</div>
					</li>
					<li>
						<div class="sclhdyg-imgbox"><a href="#"><img src="static/front/css/images/test-img.png"></a></div>
						<div class="sclhdyg-name">
							<a href="#">${dv:e("华诚所入选上海法院首批一级破产管理人名册",40)}</a>
						</div>
						<div class="sclhdyg-time-box">
							时间：2015-03-15 ~ 2015-03-28
						</div>
					</li>
				</ul>
			</div>
			
			
			
			<div class="pagination" style="margin-top:20px;">
			    <ul class="fl">
				    <li><a href="#">《</a></li>
				    <li><a href="#">1</a></li>
				    <li><a href="#">2</a></li>
				    <li><a href="#">3</a></li>
				    <li><a href="#">4</a></li>
				    <li><a href="#">5</a></li>
				    <li><a href="#">》</a></li>
			    </ul>
			    <div class="fl pagebar-lbox">
			    	<label class="ml20">共33页</label>
			    	<label class="ml5">跳转至</label>
			    	<input type="text" class="pagebar-text">
			    	<label>页</label>
			    	<a href="#" class="qd-btn">确定</a>
			    </div>
		    </div>
		</div>
		<div class="cb"></div>
	</div>
	<jsp:include page="/WEB-INF/pages/front/comm/front_foot_v2.jsp"></jsp:include>
</body>
</html>
