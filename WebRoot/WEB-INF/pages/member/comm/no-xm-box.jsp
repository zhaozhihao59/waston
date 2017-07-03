<%@ page language="java" contentType="text/html; charset=utf-8"%>
<div class="red-title mt15 transition"><span style="font-size:20px;font-weight:bold;margin:0 5px 0 0;">+</span>我缺少的资源</div>
<div class="no-xm-box clb none">
	<div class="fast-no-box fl ">
		<label>
			<input type="checkbox" class="vm">
			<span class="hxb-font">资金</span>						
		</label>
		<label>
			<input type="checkbox" class="vm">
			<span class="hxb-font">商业服务</span>						
		</label>
		<label>
			<input type="checkbox" class="vm">
			<span class="hxb-font">供应链</span>						
		</label>
		<label>
			<input type="checkbox" class="vm">
			<span class="hxb-font">团队</span>						
		</label>
		<label>
			<input type="checkbox" class="vm">
			<span class="hxb-font">客户</span>						
		</label>
		<label>
			<input type="checkbox" class="vm">
			<span class="hxb-font">产品</span>						
		</label>
		<label>
			<input type="checkbox" class="vm">
			<span class="hxb-font">行业</span>						
		</label>
		<label>
			<input type="checkbox" class="vm">
			<span class="hxb-font">其他</span>						
		</label>
	</div>	
	<div class="sed-no-box fl">
		<label>
			<input type="checkbox" class="vm">
			<span class="hxb-font">软件开发/网站开发/APP开发</span>						
		</label>
		<label>
			<input type="checkbox" class="vm">
			<span class="hxb-font">硬件设计/代工制造</span>						
		</label>
		<label>
			<input type="checkbox" class="vm">
			<span class="hxb-font">品牌设计/包装设计/平面设计</span>						
		</label>
		<label>
			<input type="checkbox" class="vm">
			<span class="hxb-font">市场推广/营销策划</span>						
		</label>
		<label>
			<input type="checkbox" class="vm">
			<span class="hxb-font">办公场地</span>						
		</label>
		<label>
			<input type="checkbox" class="vm">
			<span class="hxb-font">包装制作</span>						
		</label>
		<label>
			<input type="checkbox" class="vm">
			<span class="hxb-font">人才招聘/猎头服务</span>						
		</label>
		<label>
			<input type="checkbox" class="vm">
			<span class="hxb-font">企业IT服务（服务器租赁，IT系统维护）</span>						
		</label>
		<label>
			<input type="checkbox" class="vm">
			<span class="hxb-font">财务服务/税务咨询</span>						
		</label>
		<label>
			<input type="checkbox" class="vm">
			<span class="hxb-font">设备租赁</span>						
		</label>
	</div>
</div>		
<script>
	$(".no-xm-box label").click(function(){
		//判断里面的checkbox是否被选中
		var chked = $(this).find("input[type='checkbox']").attr("checked");
		var spanFont = $(this).children("span");
		if(chked){
			//如果被选中
			$(this).addClass("check-red-bg");
			$(spanFont).css("color","#ffffff");
		}else{
			$(this).removeClass("check-red-bg");
			$(spanFont).css("color","#776766");
		}
	});
	
	
	var check = 0
	$(".red-title").click(function(){
		if(check == 0){
			$(".no-xm-box").show(); 
			$(".red-title").addClass("check-no-box");
			 check = 1
		}else{
			$(".no-xm-box").hide(); 
			$(".red-title").removeClass("check-no-box");
			check = 0
		}	
	});
</script>
