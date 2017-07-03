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
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=26OSkHqb2jCicibhPn9BhqQU"></script>
<style type="text/css">
	#allmap,#allmap2,#allmap3,#allmap4{width: 820px;height: 400px;overflow: hidden;margin:0;font-family:"微软雅黑";}
	.scl-knowledge-showbox{opacity: 1;}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/pages/front_jp/comm/front_header.jsp"><jsp:param name="menu" value="2" /></jsp:include>
	<jsp:include page="/WEB-INF/pages/front_jp/comm/news_move.jsp"></jsp:include>
	<div class="act-index-middlebox">
		<div class="fl act-left-box">
			<div class="hehua-img" style="bottom:20px;"></div>
			<div class="act-tt-box">お問い合わせ</div>
			<ul class="act-left-ul">
				<li class="cur">
					<a href="javascript:void(0);" class="yew01-ico">
						上海事務所
					</a>
				</li>
				<li>
					<a href="javascript:void(0);" class="yew02-ico">
						北京事務所
					</a>
				</li>
				<li>
					<a href="javascript:void(0);" class="yew03-ico">
						ハルピン事務所
					</a>
				</li>
				<li>
					<a href="javascript:void(0);" class="yew03-ico">
						香港事務所
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
				<div class="scl-lxwm-ttbg mt5"></div>
			</div>
			<div class="knowledge-ccbox mt20">
				華誠は、上海·北京·ハルピン及び香港に事務所を設立しております。   
			</div>
			
			<div class="job-opp-cbox mt20" style="display:inline-block;">
				<div class="scl-knowledge-showbox" >
					<div class="scl-knolayout1">
						<div class="scl-layout2">
							上海事務所
						</div>
					</div>
					<div style="padding:30px;">
						<!-- <div id="allmap"></div> -->
						<div><img src="${BASE_PATH}/static/front_jp/comm/images/map1.jpg"></div>
						<div class="mt30">
							<div class="fl" style="width:510px;">
								<div class="yq-ttbox mb15">住所地：</div>
								<div class="contact-tbox">上海市徐匯区長楽路989号　世紀商貿広場26＆27階 </div>
								<div class="contact-tbox">電話： (86-21) 5292-1111; (86-21) 6350-0777</div>
								<div class="contact-tbox">ファックス：  (86-21)5292-1001; (86-21) 6272-6366</div>
								<div class="contact-tbox">E-mail: <a href="mailto:mail@watson-band.com.cn">mail@watson-band.com.cn</a> <a href="mailto:mailip@watson-band.com.cn">mailip@watson-band.com.cn</a> </div>
								<div class="contact-tbox">Webサイト：<a href="http://www.watson-band.com.cn">www.watson-band.com.cn</a></div>
								<div class="contact-tbox">連絡先電話番号： （86-21）5292-1111 * 126</div>
							</div>
<!-- 							<div class="fr" style="width:320px;"> -->
<!-- 								<div class="yq-ttbox mb15">交通路线</div> -->
<!-- 								<div class="contact-tbox">地铁一号线陕西路站2号出口步行15分钟</div> -->
<!-- 								<div class="contact-tbox">地铁二号线南京西路站4号出口步行10分钟</div> -->
<!-- 								<div class="yq-ttbox mb15 mt30">下载</div> -->
<!-- 								<div class="contact-tbox"><img src="static/front/css/images/xz-ico.png" class="vm mr5"><a href="#" class="vm">下载PDF文件</a></div> -->
<!-- 							</div> -->
							<div class="cb"></div>
						</div>
					</div>
				</div>
				<div class="scl-knowledge-showbox none">
					<div class="scl-knolayout1">
						<div class="scl-layout2">
							北京事務所
						</div>
					</div>
					<div style="padding:30px;">
						<div id="allmap2"></div>
						<div class="mt30">
							<div class="fl" style="width:510px;">
								<div class="yq-ttbox mb15">住所地</div>
								<div class="contact-tbox">北京市東城区朝陽門北大街8号富華ビルDブッロク5C 郵便番号：100027</div>
								<div class="contact-tbox">電話： (86-10) 66256025</div>
								<div class="contact-tbox">ファックス： (86-10) 6445-2797</div>
								<div class="contact-tbox">E-mail: <a href="beijing@watson-band.com.cn">beijing@watson-band.com.cn</a> <a href="mailto:mailip@watson-band.com.cn">mailip@watson-band.com.cn</a></div>
								<div class="contact-tbox">Webサイト：<a href="http://www.watson-band.com.cn">www.watson-band.com.cn</a></div>
								<div class="contact-tbox">連絡先電話番号： （86-10）6625-6025 * 601</div>
							</div>
<!-- 							<div class="fr" style="width:320px;"> -->
<!-- 								<div class="yq-ttbox mb15">交通路线</div> -->
<!-- 								<div class="contact-tbox">地铁一号线陕西路站2号出口步行15分钟</div> -->
<!-- 								<div class="contact-tbox">地铁二号线南京西路站4号出口步行10分钟</div> -->
<!-- 								<div class="yq-ttbox mb15 mt30">下载</div> -->
<!-- 								<div class="contact-tbox"><img src="static/front/css/images/xz-ico.png" class="vm mr5"><a href="#" class="vm">下载PDF文件</a></div> -->
<!-- 							</div> -->
							<div class="cb"></div>
						</div>
					</div>
				</div>
				<div class="scl-knowledge-showbox none">
					<div class="scl-knolayout1">
						<div class="scl-layout2">
							ハルピン事務所
						</div>
					</div>
					<div style="padding:30px;">
						<div id="allmap3"></div>
						<div class="mt30">
							<div class="fl" style="width:510px;">
								<div class="yq-ttbox mb15">住所地</div>
								<div class="contact-tbox">ハルピン市道里区西八道街37号馬迪尓ビル18階A2室　　　郵便番号：150010</div>
								<div class="contact-tbox">電話： (86-451) 8457-3032</div>
								<div class="contact-tbox">(86-451) 8457-3032</div>
								<div class="contact-tbox">E-mail: <a href="harbin@watson-band.com.cn">harbin@watson-band.com.cn</a> <a href="mailto:mailip@watson-band.com.cn">mailip@watson-band.com.cn</a></div>
								<div class="contact-tbox">Webサイト：<a href="http://www.watson-band.com.cn">www.watson-band.com.cn</a></div>
								<div class="contact-tbox">連絡先電話番号： （86-21）5292-1111 * 126</div>
							</div>
<!-- 							<div class="fr" style="width:320px;"> -->
<!-- 								<div class="yq-ttbox mb15">交通路线</div> -->
<!-- 								<div class="contact-tbox">地铁一号线陕西路站2号出口步行15分钟</div> -->
<!-- 								<div class="contact-tbox">地铁二号线南京西路站4号出口步行10分钟</div> -->
<!-- 								<div class="yq-ttbox mb15 mt30">下载</div> -->
<!-- 								<div class="contact-tbox"><img src="static/front/css/images/xz-ico.png" class="vm mr5"><a href="#" class="vm">下载PDF文件</a></div> -->
<!-- 							</div> -->
							<div class="cb"></div>
						</div>
					</div>
				</div>
				
				
				<div class="scl-knowledge-showbox none">
					<div class="scl-knolayout1">
						<div class="scl-layout2">
							香港事務所
						</div>
					</div>
					
					
					<div style="padding:30px;">
						<div id="allmap4"></div>
						<div class="mt30">
							<div class="fl" style="width:510px;">
								<div class="yq-ttbox mb15">住所地</div>
								<div class="contact-tbox">香港中環荷李活道32号 建業栄基センター2004号室</div>
								<div class="contact-tbox">電話： (86-21) 5292-1111*123; 852-3197-0091</div>
								<div class="contact-tbox">ファックス： (86-21) 5292-1001; 852-3197-0093</div>
								<div class="contact-tbox">E-mail: <a href=": mail@watson-band.com.cn">: mail@watson-band.com.cn</a> <a href="mailto:mailip@watson-band.com.cn">mailip@watson-band.com.cn</a></div>
								<div class="contact-tbox">Webサイト：<a href="http://www.watson-band.com.cn">www.watson-band.com.cn</a></div>
								<div class="contact-tbox">連絡先電話番号： （86-21）5292-1111 * 126</div>
							</div>
<!-- 							<div class="fr" style="width:320px;"> -->
<!-- 								<div class="yq-ttbox mb15">交通路线</div> -->
<!-- 								<div class="contact-tbox">地铁一号线陕西路站2号出口步行15分钟</div> -->
<!-- 								<div class="contact-tbox">地铁二号线南京西路站4号出口步行10分钟</div> -->
<!-- 								<div class="yq-ttbox mb15 mt30">下载</div> -->
<!-- 								<div class="contact-tbox"><img src="static/front/css/images/xz-ico.png" class="vm mr5"><a href="#" class="vm">下载PDF文件</a></div> -->
<!-- 							</div> -->
							<div class="cb"></div>
						</div>
					</div>
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
					$(this).click(function(){
						$(this).addClass("cur").siblings().removeClass("cur");
						$(".jobopp-ul li").eq(i).addClass("cur").siblings(".jobopp-ul li").removeClass("cur");
						$(".scl-knowledge-showbox").eq(i).show().siblings(".scl-knowledge-showbox").hide();
						loadMap2();
						loadMap3();
						loadMap4();
					});
				});
			});
			});	
			</script>
		</div>
		<div class="cb"></div>
	</div>
	<script type="text/javascript">
		function loadMap() {
			// 百度地图API功能
			var map = new BMap.Map("allmap");            // 创建Map实例
			map.centerAndZoom(new BMap.Point(121.453467,31.221885), 11);
			var local = new BMap.LocalSearch(map, {
			  renderOptions:{map: map, autoViewport:true}
			});
			local.search("上海市长乐路989号世纪商贸广场26楼");
			var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
			var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
			var top_right_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL}); //右上角，仅包含平移和缩放按钮
			map.addControl(top_left_control);        
			map.addControl(top_left_navigation);     
			map.addControl(top_right_navigation); 
		}  
	</script>
	<script type="text/javascript">
		function loadMap2() {
			// 百度地图API功能
			var map = new BMap.Map("allmap2");            // 创建Map实例
			map.centerAndZoom(new BMap.Point(116.4426490000, 39.9364730000), 11);
			var local = new BMap.LocalSearch(map, {
			  renderOptions:{map: map, autoViewport:true}
			});
			local.search("北京市东城区朝阳门北大街8号富华大厦D座3C");
			var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
			var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
			var top_right_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL}); //右上角，仅包含平移和缩放按钮
			map.addControl(top_left_control);        
			map.addControl(top_left_navigation);     
			map.addControl(top_right_navigation);   
	 	}
	</script>
	<script type="text/javascript">
		function loadMap3() {
			// 百度地图API功能
			var map = new BMap.Map("allmap3");   // 创建Map实例
			map.centerAndZoom(new BMap.Point(126.6267900000,45.7794550000), 11);
			var local = new BMap.LocalSearch(map, {
			  renderOptions:{map: map, autoViewport:true}
			});
			local.search("哈尔滨市道里区西八道街37号马迪尔大厦18层A2室");
			var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
			var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
			var top_right_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL}); //右上角，仅包含平移和缩放按钮
			map.addControl(top_left_control);        
			map.addControl(top_left_navigation);     
			map.addControl(top_right_navigation);    
		}
	</script>
	<script type="text/javascript">
		function loadMap4() {
			// 百度地图API功能
			var map = new BMap.Map("allmap4");            // 创建Map实例
			map.centerAndZoom(new BMap.Point(114.1648110000, 22.2853230000), 11);
			var local = new BMap.LocalSearch(map, {
			  renderOptions:{map: map, autoViewport:true}
			});
			local.search("香港中环荷李活道32号建业荣基中心2004室");
			var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
			var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
			var top_right_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL}); //右上角，仅包含平移和缩放按钮
			map.addControl(top_left_control);        
			map.addControl(top_left_navigation);     
			map.addControl(top_right_navigation);  
		}  
	</script>
	<jsp:include page="/WEB-INF/pages/front_jp/comm/front_foot_v2.jsp"></jsp:include>
</body>
</html>
