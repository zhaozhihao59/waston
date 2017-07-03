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
	<jsp:include page="/WEB-INF/pages/front_jp/comm/front_header.jsp"><jsp:param name="menu" value="4" /></jsp:include>
	<jsp:include page="/WEB-INF/pages/front_jp/comm/news_move.jsp"></jsp:include>
	<input id="b" value="0"  style="visibility:hidden"/>
	<div class="act-index-middlebox">
		<div class="fl act-left-box">
			<div class="hehua-img"></div>
			<div class="act-tt-box"> メンバー紹介</div>
			<ul class="act-left-ul">
				<li name="maxtype" class="cur" data1="0">
					<a href="javascript:void(0);" class="yew01-ico" >
						パートナー
					</a>
				</li>
				<li name="maxtype" data1="4">
					<a href="javascript:void(0);" class="yew02-ico"> 
						シニアパートナー
					</a>
				</li>
				<li name="maxtype" data1="5">
					<a href="javascript:void(0);" class="yew03-ico">
						マネジメントパートナー
					 </a>
				</li>
				<li name="maxtype" data1="6">
					<a href="javascript:void(0);" class="yew04-ico">
						高級パートナー
					</a>
				</li>
				<li name="maxtype"  data1="1">
					<a href="javascript:void(0);" class="yew02-ico"  >
						シニアコンサルタント
					</a>
				</li>
				<li  name="maxtype" data1="2">
					<a href="javascript:void(0);" class="yew04-ico"  >
						弁理士
					</a>
				</li>
				<li  name="maxtype" data1="3">
					<a href="javascript:void(0);" class="yew03-ico"  >
						弁護士
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
					华诚通讯订阅 
					<i></i>
				</a>-->
			</div>
		</div>
		<div class="fr actright-box">
			<div class="">
				<div class="scl-pro-ttbg-jp mt5"></div>
			</div>
			<div class="knowledge-ccbox mt20">
				華誠は経験豊富な弁護士により編成されています。その多くの弁護士は国内外でのさまざまな経験があり、語学も英語、日本語、イタリア語、またロシア語に精通するなど高い教育を受けています。優れた弁護士のチームワークと協力関係による高品位のサービスを提供することで、クライアントからご評価をいただいております。各メンバーは、それぞれ独自のバックグラウンドやクライアントとの業務を通じて、さまざまな経験があります。
			</div>
			<ul class="knowledge-ul clb mt20 data">
				<li name="maxtype" data1="0">パートナー</li>
				<li name="maxtype" data1="4">シニアパートナー</li>
				<li name="maxtype" data1="5">マネジメントパートナー</li>
				<li name="maxtype" data1="6">高級パートナー</li>
				<li name="maxtype" data1="1">シニアコンサルタント</li>
				<li name="maxtype" data1="2">弁理士</li>
				<li name="maxtype" data1="3">弁護士</li>
			</ul>
			<div class="knowledge-ccbox mt20">
				各弁護士の詳細はこちらをご覧ください:
			</div>
			<!-- 公司商务、知识产权、诉讼与仲裁等 -->
			<ul class="knowledge-ul clb mt20 data1">
				<!-- 分页 循环显示内容-->
					<c:forEach var="sysDictItem" items="${sysDictItemList}">	 
					<li name="mintype" data1 = ${sysDictItem.id }>${sysDictItem.name}</li>
				</c:forEach>

			</ul>
			<div class="knowledge-ccbox mt20">
				氏名を入力：
			</div>
			<div class="mt10">
				<div class="form-group fl" >
					<input id="searchNames" value="${condition.nameJp}" type="text" style="width:300px;" class="form-control" placeholder="搜索 &hellip;" >
				</div>
				<div onclick="search();return false;" id="search" class="wa-btn watson-index-ico w40 fl ml5" style="border: 1px solid #999999;"></div>
				<div class="cb"></div>
			</div>
			<div>
				<div class="sctr-right-box" style="margin-top:10px;">
					<div class="srb-nav-box2">検索結果</div>
				</div>
				
			 <input id="types" type="hidden">
		  	 <input id="qualification" type="hidden">
			<!-- 人员-->
 			<div id="pageinc">
				 <s:if test="pageResult.rows == 0">
					<div class="no-sj">暂无内容</div>
				</s:if>
				<!-- 分页 循环显示内容-->
				<c:forEach var="professional" items="${pageResult.result}">	 				
					<ul class="pro-imgul">
						<li>
							<div class="photo-h fl">
								<a href="front/professional_jp/toProfessionalDetail_jp.htm?selIds=${professional.id }"><img src="${professional.photo }" style="width:66px;Height:66px;" /></a>
							</div>
							<div class="fl photo-c">
								<div class="pc-name"><a href="front/professional_jp/toProfessionalDetail_jp.htm?selIds=${professional.id }">${professional.nameJp}</a></div>
								<div>${professional.qualification}</div>
								<%-- 	<c:if test="${fn:contains(professional.type, '4')}">
										<span class="pc-cfont">シニアパートナー</span>
										<c:if test="${fn:contains(professional.type, ',')}">,
										</c:if>
									</c:if>
									<c:if test="${fn:contains(professional.type, '5')}">
										<span class="pc-cfont">マネジメントパートナー</span>
										<c:if test="${fn:contains(professional.type, ',')}">,
										</c:if>
									</c:if>
									<c:if test="${fn:contains(professional.type, '6')}">
										<span class="pc-cfont">高級パートナー</span>
										<c:if test="${fn:contains(professional.type, ',')}">,
										</c:if>
									</c:if>
									<c:if test="${fn:contains(professional.type, '1')}">
										<span class="pc-cfont">シニアコンサルタント</span>
										<c:if test="${fn:contains(professional.type, ',')}">,
										</c:if>
									</c:if>
									<c:if test="${fn:contains(professional.type, '0')}">
										<span class="pc-cfont">パートナー</span>
										<c:if test="${fn:contains(professional.type, ',')}">,
										</c:if>
									</c:if>
									<c:if test="${fn:contains(professional.type, '3')}">
										<span class="pc-cfont">弁護士</span>
										<c:if test="${fn:contains(professional.type, ',')}">,
										</c:if>
									</c:if>
									<c:if test="${fn:contains(professional.type, '2')}">
										<span class="pc-cfont">弁理士</span>
										<c:if test="${fn:contains(professional.type, ',')}">,
										</c:if>
									</c:if>
									<c:if test="${fn:contains(professional.type, '7')}">
										<span class="pc-cfont">執行主管パートナー</span>
										<c:if test="${fn:contains(professional.type, ',')}">,
										</c:if>
									</c:if> --%>
								<div class="pc-cfont">${professional.email }</div>
							</div>
						</li>
					</ul>
				</c:forEach>
					<div class="cb"></div>
						<!-- 分页 -->
						<div class="pagination mt20" style="float:right;">
						<c:if test="${not empty pageResult.result and pageResult.allPages gt 0}">
								<div class="pagination mt20">
									<ul>
										<c:if test="${pageResult.currentPage>1}">
											<li>
												<a class="pageReslt"   title="首页">&lt;&lt;</a>
											</li>
											<li>
												<a class="pageReslt" currentPage="${pageResult.prePage}" title="上一页">&lt;</a>
											</li>
										</c:if>
										<c:forEach begin="${pageResult.pageBar[0]}" end="${pageResult.pageBar[1]}" varStatus="status">
											<c:if test="${pageResult.currentPage eq status.index}">
												<li>
													<a class="pageReslt" currentPage="${status.index}" style="background-color: #dfdfdf;" class="cur">${status.index }</a>
												</li>
											</c:if>
											<c:if test="${pageResult.currentPage != status.index}">
												<li>
													<a class="pageReslt" currentPage="${status.index}">${status.index }</a>
												</li>
											</c:if>
									</c:forEach>
									<c:if test="${pageResult.currentPage lt pageResult.allPages}">
										<li>
											<a class="pageReslt" currentPage="${pageResult.nextPage}" title="下一页">&gt;</a>
										</li>
										<li>
											<a class="pageReslt" currentPage="${pageResult.lastPage}" title="尾页">&gt;&gt;</a>
										</li>
									</c:if>
								</ul>
							</div>
						</c:if>
		<div class="cb"></div>
		</div>
		<!-- 分页 -->
			</div>
	 		<!--  -->
				
			</div>
			<script>
			//选中样式
			//合伙人和高级顾问
			
				if ("${typeSessionJp}" != null && "${typeSessionJp}" != "") {
					$("li[name=maxtype]").removeClass("cur");
					$("li[data1=${typeSessionJp}]").addClass("cur");
				}
				//业务类型
				if ("${businessSessionJp}" != null
						&& "${businessSessionJp}" != "") {
					$("li[name=mintype]").removeClass("cur");
					$("li[data1=${businessSessionJp}]").addClass("cur");
				}
				//分页
				$(".pageReslt")
						.click(
								function() {
									var currentPage = $(this).attr(
											"currentPage");
									if (currentPage == null || currentPage == 0) {
										currentPage = 1;
									}
									// 相关证书异步请求
									window.location.href = "${BASE_PATH}front/professional_jp/index.htm?model.page="
											+ currentPage;
								});

				function search() {
					var searchNames = $("#searchNames").val();
					window.location.href = "${BASE_PATH}front/professional_jp/index.htm?condition.nameJp="
							+ searchNames;
				}

				//如果更换大类，则小类选中取消
				$("li[name=maxtype]").click(function() {
					$("li[name=mintype]").removeClass("cur");
				});
				$("li[name=maxtype]")
						.click(
								function() {
									//传入大类参数
									var type = $(this).attr("data1");
									//如果tpye等于1或者等于0为合伙人或者高级顾问，如果不等，则是律师
									window.location.href = "${BASE_PATH}front/professional_jp/index.htm?condition.type="
											+ type;
								});

				$("li[name=mintype]")
						.click(
								function() {
									//传入小类参数
									var business = $(this).attr("data1");
									window.location.href = "${BASE_PATH}front/professional_jp/index.htm?condition.businessJp="
											+ business;
								});
			</script>
		</div>
		<div class="cb"></div>
	</div>
	
	<jsp:include page="/WEB-INF/pages/front_jp/comm/front_foot_v2.jsp"></jsp:include>
</body>
</html>
