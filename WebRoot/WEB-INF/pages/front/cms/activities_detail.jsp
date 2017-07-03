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
<script type="text/javascript" src="static/front/js/img-move/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="static/front/js/img-move/jquery.banner.revolution.min.js"></script>
<script type="text/javascript" src="static/front/js/img-move/banner.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/pages/front/comm/front_header.jsp"><jsp:param name="menu" value="6" /></jsp:include>
	<jsp:include page="/WEB-INF/pages/front/comm/news_move.jsp"></jsp:include>
	<div class="act-index-middlebox">
		<div class="fl act-left-box">
			<div class="zz-img"></div>
			<div class="act-tt-box">讲座活动</div>
			<ul class="act-left-ul">
				<li class="cur">
					<a href="#" class="jzhd-ico">
						讲座活动
					</a>
				</li>
				<li>
					<a href="#" class="jzhd-ico">
						华诚新闻
					</a>
				</li>
				<li>
					<a href="#" class="jzhd-ico">
						行业新闻
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
			<div class="sctr-right-box">
				<div class="srb-nav-box fl">讲座活动</div>
				<div class="fr srb-ncbox mr15">
					<a href="#">首页</a>
					<label>></label>
					<span>讲座活动</span>
				</div>
				<div class="cb"></div>
			</div>
			
			<div class="bmhd-tt-box">
				<div class="yy-jt-ico"></div>
				正在报名的活动
			</div>
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
					<a href="#" class="goback-ico">返回</a>
				</div>
				<div class="cb"></div>
			</div>
			
			
			<div class="scl-ljbm-box">
				<div class="form-item">
					<span class="item-title w80"><em>*</em>邮箱：</span>
					<input type="text" class="act-text w300" />
				</div>
				<div class="form-item">
					<span class="item-title w80"><em>*</em>姓名：</span>
					<input type="text" class="act-text w150" />
				</div>
				<div class="form-item">
					<span class="item-title w80">职务：</span>
					<input type="text" class="act-text w150" />
				</div>
				<div class="form-item">
					<span class="item-title w80"><em>*</em>公司：</span>
					<input type="text" class="act-text w300" />
				</div>
				<div class="form-item">
					<span class="item-title w80">电话：</span>
					<input type="text" class="act-text w150" />
				</div>
				<div class="form-item">
					<span class="item-title w80">手机：</span>
					<input type="text" class="act-text w150" />
				</div>
				<div class="form-item">
					<span class="item-title w80"></span>
					<a href="#" class="ljbm-btn fl ">立即报名</a>
				</div>
			</div>
		</div>
		<div class="cb"></div>
	</div>
	<jsp:include page="/WEB-INF/pages/front/comm/front_foot_v2.jsp"></jsp:include>
</body>
</html>
