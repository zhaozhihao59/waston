<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dv" uri="/WEB-INF/tld/default_value.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script>
	var base = "${BASE_PATH}";
</script>
<body style="background:#f1f1f1;">
	<div style="background:#f1f1f1;width:100%;height:100%;">
		<table style="width:100%;">
			<tr >
				<td style="width:20%;"></td>
				<td style="width:60%;">
					<div style="width:600px;height:138px;background:#fff;margin:20px auto 5px;">
					<div style="background:#a4ceab;width:100%;height:20px;"></div>
					<div style="padding:20px 15px 20px;">
						<div style="font-size:13px;color:#555;margin-bottom:20px;font-weight:bold;">${item.name }，您好！</div>
						<div style="font-size:13px;color:#555;margin-bottom:10px;">您已成功退订华诚订阅邮件，页面将于<i id="num">5</i>秒后自动跳转至华诚首页。</div>
						<div style="">
							<span style="font-size:13px;color:#999;"><fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd E" /> </span>
						</div>
					</div>
				</div>
				<div style="font-size:12px;width:600px;height:30px;margin:10px auto 5px;text-align:center;color:#aaa; ">© Copyright 2015-2025 All Rights Reserved 华诚律师事务所</div>
			</div>
				</td>
				<td style="width:20%;"></td>
			</tr>
	</table>
	<script language="javascript">
			 var t = 5;
			 var time = document.getElementById("num");
			 function fun(){
			  t--;
			  time.innerHTML = t;
			  if(t<=0){
			   location.href = base+"index.htm";
			   clearInterval(inter);
			  }
			 }
			 var inter = setInterval("fun()",1000);
		</script>
</body>