<%@ page language="java" contentType="text/html; charset=utf-8"%>
<script src="static/front/js/comm/pic-move.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--图片轮换--%>
<div class="picshow fl mt20">
	<div class="picwrap">
		<ul>
			<c:forEach items="${cmsAdvPhotos1}" var="photo">
				<li class="hide"><a target="_blank" href="${photo.linkUrl}"><img src="${photo.path}" style="width:663px;height:285px;"></a></li>
			</c:forEach>
		</ul>
	</div>
	<div class="butt buttons">
		<div class="trans_bg"></div>
		<ul>
			<c:forEach items="${cmsAdvPhotos1}" var="photo" varStatus="st">
				<li <c:if test="${st.index == 0}">class="on"</c:if>></li>
			</c:forEach>
		</ul>
	</div>
</div>
<%--图片轮换end--%>
<style>
/* picshow */
.picshow {
	position: relative;
	z-index: 1;
	height: 285px;
	width: 663px;
	overflow: hidden;
	z-index: 80;
}

.picshow .picwrap li {
	position: absolute;
	left: 0px;
	top: 0px;
	height: 237px;
	width: 443px;
	z-index: 60;
}
.picshow .picwrap li.hide {
	display: none;
}

.picshow .picwrap li img {
	height: 237px;
	width: 442px;
}

.picshow .butt {
	position: absolute;
	left: 0px;
	bottom: 0px;
	width: 100%;
	height: 35px;
	text-align: center;
	z-index: 70;
}

.picshow .butt .trans_bg {
	width: 100%;
	height: 100%;
	background: #000;
	opacity: 0.5;
	filter: alpha(opacity =   50);
}

.picshow .butt ul {
	position: absolute;
	right: 10px;
	text-align: center;
	top: 9px;
}

.picshow .butt li {
	 background: none repeat scroll 0 0 #373737;
    color: #fff;
    cursor: pointer;
    display: inline;
    float: left;
    font-size: 12px;
    height: 15px;
    margin-right: 10px;
    opacity: 0.5;
    position: relative;
    text-align: center;
    width: 15px;
    border-radius:50%;
}

.picshow .butt li img.menu {
	width: 116px;
	height: 30px;
	cursor: pointer;
}

.picshow .butt li img.bg {
	position: absolute;
	left: 0px;
	top: 0px;
	cursor: pointer;
}

.picshow .butt li.on {
	background: none repeat scroll 0 0 #e84b3b;
    color: #fff;
    cursor: pointer;
    opacity: 1;
}

.picshow .pic_titles {
	position: absolute;
	bottom: 40px;
	left: 0px;
	width: 100%;
}

.picshow .pic_titles ul {
	margin-left: 30px;
}

.picshow .pic_titles li {
	float: left;
	display: inline;
	width: 172px;
	height: 22px;
	overflow: hidden;
	text-align: center;
}

.picshow .pic_titles li p {
	display: none;
	width: 100%;
	height: 22px;
	overflow: hidden;
	line-height: 22px;
	text-align: center;
	color: #fff;
	font-size: 14px;
	font-weight: bold;
}
</style>
