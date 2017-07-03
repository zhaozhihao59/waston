<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<c:if test="${pageResult.result != null}">
<s:iterator value="pageResult.result" var="photo">
<div class="adv-photo-item mr20 fl">
	<div class="adv-photo">
		<img src="${photo.path }" />
		<div class="photo-btn hide">
			<a class="adv-photo-btn J-ap-edit" href="javascript:void(0);" data="${photo.id }">编辑</a>
			<a class="adv-photo-btn J-ap-remove" href="javascript:void(0);" data="${photo.id }">删除</a>
		</div>
	</div>
	<div class="photo-desc mt5">
		<span class="pd-span" title="${photo.name}">名称：${photo.name}</span>
		<span class="pd-span" title="${photo.linkUrl}">链接：${photo.linkUrl}</span>
		<span class="pd-span" title="${photo.note}">说明：${photo.note}</span>
	</div>
</div>
</s:iterator>
</c:if>
<div class="adv-photo-item-btn mr20 fl">
	<a id="advPhotoAdd" class="adv-photo-add" href="javascript:void(0);">新增图片</a>
</div>
<div class="cb"></div>
