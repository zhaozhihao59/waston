$(document).ready(function(){
	var gh = $(window).height() - $('#operBar').height() - 91;
	$("#treeDiv").css("height",gh+68);
	resizeWidth();
	
	// 生成Ztree
	var setting = {
		async : {
			enable : true,
			url : treeLoadUrl,
			autoParam : ["id", "name", "isDelete","parentId"]
		},
		callback : {
			onRightClick : OnRightClick,
			onClick : treeClickFN,
			onAsyncSuccess : hideTipFN
		},
		view : {
			 nameIsHTML : true
		},
		check : {
			enable : false,
			chkStyle : "checkbox"
		}
	};
	
	$.fn.zTree.init($("#categoryTree"), setting);
	zTree = $.fn.zTree.getZTreeObj("categoryTree");
 	rMenu = $("#rMenu");
 	
 	// 修改类别
	$("#editNodeBtn").live("click",editNode);
	
	// 刷新选定节点
	$("#selNodeRefresh").live('click',function(){
		hideRMenu();
		if(curNode == null){
			$.dialog.alert("请选择要刷新的部门！");
			return ;
		}
		reloadNodes(curNode.id);
	});
	
	// 删除节点
	$("#selNodeDelete1").live('click',function(){
		hideRMenu();
		if(curNode == null){
			$.dialog.alert("请选择要删除的部门！");
			return;
		}
		
		//var location=curNode.location;
		//if(location ==3){
			$.dialog.confirm("确定要删除此部门吗？",function(){
				$("body",parent.document).mask("正在删除中，请稍候...");
				deleteSelectNode(curNode.id);
			});
		//}
		
		if(location == 2 || location == 1){
			$.dialog.confirm("确定要删除此部门及其子部门吗？",function(){
				$("body",parent.document).mask("正在删除中，请稍候...");
				deleteSelectNode(curNode.id);
			});
		}
	});
	
		// 删除栏目
	$("#selNodeDelete").live('click',function(){
		hideRMenu();
		if(curNode == undefined){
			$.dialog.alert("请选择部门！");
			return;
		}
		if(curNode.state == 1){
			$.dialog.alert("此类别为系统类别，不可删除");
			return;
		}
		if(curNode.isDelete == 1){
			$.dialog.alert("此类别下有子类别与部门，请先删除下级类别与部门，再删除该类别");
			return;
		}
		$.dialog.confirm("确定要删除此部门吗？",function(){
			$.post("admin/auth/depart/delDepartById.htm?item.id="+curNode.id,function(data){
				if(data.status=="success"){
					reloadNodes(curNode.parentId);
				}else{
					$.dialog.alert(data.message);
				}
			},'json');
		});
		
	});
	
	//左右拖拽
	var divsp = new Separator(document.getElementById("categoryTreeDiv"),document.getElementById("categoryContentDiv"),separatorCallbackFN,SP_LEFTRIGHT,4); 
	$(window).resize(function(){
		resizeWidth();
	});
	
	//渲染表格骨架
	$("#table").jqGrid({
	    url:'admin/auth/user/searchUserList.htm?m='+Math.random(),
	    datatype:"json",
	    colNames:[/*'主管',*/'操作','是否启用','账号','用户姓名','用户编号','用户角色','性别','出生日期','手机号码','电子邮箱','部门ID'],
		colModel:[ 
			/*{name:"isManager",index:"isManager",width:50,formatter:managerFormater},*/
			{name:"id",index:"id",width:150,formatter:optFormater},
			{name:"state",width:55,index:'state',formatter:stateFormater},
			{name:'account',index:'account',width:85},
			{name:'name',index:'name',width:68},
			{name:'staffId',index:'staffId',width:85},
			{name:"roleNames",index:'roleNames',width:158},
			{name:'sex',index:'sex',width:38,formatter:sexFormater},
			{name:'birthday',index:'birthday',width:88,formatter:dateFormater},
			{name:'mobile',index:'mobile',width:80},
			{name:'email',index:'email',width:208},
			{name:'departId',index:'departId',hidden:true}
		], 
		caption:"用户列表",
		loadComplete: function(data){
			var width = $("#categoryContentDiv").width();
			$(this).setGridWidth(width);
			fillEmptyRow('table',data);
	   	},
	   	height:gh,
		jsonReader:{id: "id",root:"resultList"}
  	});
  	$("#table").jqGrid("navGrid","#pagerBar");
		  
	// 随窗体大小改变而改变Grid宽度
	window.onresize = function _doResize(){
	 	var width = $("#categoryContentDiv").width();
	 	$("#table").jqGrid('setGridWidth', width);
	}
	
	//渲染分页工具条
	$("#table").jqGrid('navGrid','#pagerBar');
	
	//绑定添加角色事件
	$("#roles_pop_btn").click(function(){
		var roleIds = $("#roleIds");
		var rids = [];
		if(roleIds.val().length >0){
			rids = roleIds.val().split(",");
		}
		
		var dialog = $.dialog({
			title:"新增角色",
			ok:function(){
				var chks = [];
				var li = $("#roleListDv",document).find("li");
				li.each(function(i){
					var chk = $(this).find("input[type='checkbox']");
					if(chk.attr("checked")){
						var exists = false;
						for(var i=0;i<rids.length;i++){
							if(rids[i] == chk.val()){
								exists = true;
								break;
							}
						}
						if(!exists){
							chks[$(this).text()] = chk.val();	
						}
					}
				});
				
				var vals = [];
				for(var key in chks){
					vals.push(chks[key]);
					var cli = $("<li />");
					cli.attr("id","role_li_"+chks[key]);
					cli.append("<span>"+key+"</span>");
					cli.append('<a title="移除" class="del" href="javascript:;" onclick="removeCurRole(this);">X</a>');
					$("#roleListUl").append(cli);
				}
				
				//合并
				rids = rids.concat(vals);
				if(rids.length == 1){
					roleIds.val(rids[0]);
				}else{
					roleIds.val(rids.join(','));	
				}
			}
		});
		
		$.post("admin/auth/user/chooseRoles.htm?m="+Math.random(),{'roleIds':roleIds.val()}, function(data){
			dialog.content(data);
		});
	});
	//添加用户
	$(".btn_add_user,#add_user").click(function(){
		var loginInfoBar=$("#login_info_bar");
		$(loginInfoBar).show().addClass("infobar");
		var offsetTop=$("#lastBottomDom").offset().top;
		$("html").animate({scrollTop: offsetTop}, 800,function(){
			$(loginInfoBar).fadeIn("fast",function(){
				$(loginInfoBar).removeClass("infobar");
			});
		});
		$("#save_user_btn").show();	//显示保存按钮
		$("#userForm").clearForm();	//重置表单
		$("input[name='item.sex']:eq(0)").attr("checked",true);	//默认选中男
		$("#roleListUl").empty();	//清空角色列表
		$("#itemId").val("");$("#roleIds").val("");	//清空编号
		$("#resetPwdBtnDiv").hide();	//隐藏重置密码
		$("#addAccountDv").show();	//显示添加账号
		
		$("#errorlist").empty();	//清空错误信息
		
		//进行验证密码
		$("#itemPassword").unFormValidator(false);
		
	});
	
	//重置密码
	$("#resetpwd_btn").click(function(){
		var resetpasswordBar=$("#resetpassword_bar");
		$(resetpasswordBar).addClass("infobar");
		$(resetpasswordBar).show("fast",function(){
			var offsetTop=$("#lastBottomDom").offset().top;
			$("html").animate({scrollTop: offsetTop}, 800,function(){
				$(resetpasswordBar).fadeIn("fast",function(){
					$(resetpasswordBar).removeClass("infobar");
				});
			});
		});
	});

	
	//验证框架信息
	$.formValidator.initConfig({validatorGroup:"1",onError:function(msg,obj,errorlist){$.msg({wrapID:"errorlist",type:"error",messages:errorlist,time:"5000"});}});
	$("#searchKey").formValidator({validatorGroup:1,onShowText:'请输入账号/用户姓名',onShow:'',onFocus:'',onCorrect:''}).inputValidator({min:0,max:255,onErrorMax:"关键词长度过长"});
	$("#itemName").formValidator({validatorGroup:1}).inputValidator({min:1,max:255,onErrorMin:"姓名不能为空",onErrorMax:"姓名长度过长"});
	$("#itemTel").formValidator({empty:true}).regexValidator({regExp:"^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$",onError:"固定电话必须为数字与\"-\""});
	$("#itemMobile").formValidator({empty:true}).inputValidator({min:11,max:11,onError:"手机号码必须是11位的"}).regexValidator({regExp:"mobile",dataType:"enum",onError:"手机号码格式错误，请更正后保存"});
	//账号信息
	$("#itemAccount").formValidator({validatorGroup:1}).inputValidator({min:1,max:255,onErrorMin:"账号不能为空",onErrorMax:"账号长度过长"})
	.ajaxValidator({
		dataType : "text",
		type:'POST',
		async : true,
		url : "admin/auth/user/judgeAccountExist.htm?m="+Math.random(),
		data:{
			"item.id":function(){
				return $("#itemId").val();
			},
			"item.account":function(){
				return $("#itemAccount").val();
			}
		},
		success : function(data){
			return data == "true";
		},
		buttons: $("#save_user_btn"),
		error: function(jqXHR, textStatus, errorThrown){alert("服务器没有返回数据，可能服务器忙，请重试"+errorThrown);},
		onError : "该账号已经被使用",
		onWait : "正在校验，请稍候..."
	});
	$("#itemPassword").formValidator({validatorGroup:1}).inputValidator({min:6,max:50,empty:{leftEmpty:false,rightEmpty:false,emptyError:"密码两边不能有空符号"},onError:"密码长度不正确，最小6个字符，最多50个字符"});
	
    //异步表单提交设置
    $('#userForm').ajaxForm({
        dataType: 'json',
        success: function(data) {
        	if(data.status == "success"){
        		$("#login_info_bar").hide();
        	}
        	$.dialog.alert(data.message);
        	reloadGrid();
        }
    });
});

// 拖拽后回调函数
function separatorCallbackFN(){
	resizeWidth();
}

function hideTipFN(){
	$("#loadTip").hide();
}

function resizeWidth(){
	var ww = $(window).width();
	var tw = $("#categoryTreeDiv").width();
	var cw = ww-tw-34;
	$("#categoryContentDiv").width(cw);
	$("#table").setGridWidth(cw);
}

// 树单击事件
function treeClickFN(event,treeId,treeNode){
	//var nodes = zTree.getSelectedNodes();
	//var win = $.dialog.open.origin;
	//win.test(treeNode.id,treeNode.name);
	//$.dialog.location.reload;
//	$.dialog.close();
	curNode = treeNode;
	//$('#channelChoose').val(treeNode.name);
	//全部查询
	if(curNode.id!=0){
	$("#departId").val(curNode.id);
	}
	searchUserList();
	hideRMenu();
}

function addNewUser(){
	var departId=$("#departId").val();
	if(departId==null||departId==""){
		$.dialog.alert("请选择一个部门后再添加员工");
		return;	
	}
	var tv = {};
	tv.text = '新增用户';
	tv.url = 'admin/auth/user/to_add_user.htm?item.departId='+departId;
	tv.tabId = "tabli_add_user";
	tv.doc = parent.document;
	tv.refresh=true;
	showTab(tv);
}

//格式化操作单元格
function optFormater(cellvalue,options,rowObject){
	var btns = [];
	var updateBtn = "<a href='javascript:;' title='主管' class='no_unl' onclick=\"manager('"+rowObject.id+"');return false;\">查看/修改</a>";
	var updateBtn = "<a href='javascript:;' title='查看\修改' class='no_unl' onclick=\"updateFN('"+rowObject.id+"');return false;\">查看/修改</a>";
	var stateBtn = null;
	if(rowObject.state==1){
		stateBtn = "<a href='javascript:;'  class='no_unl' title='禁用' onclick=\"stateFN('"+rowObject.id+"',2);return false;\">禁用</a>";
	}else{
		stateBtn = "<a href='javascript:;' class='no_unl' title='启用' onclick=\"stateFN('"+rowObject.id+"',1);return false;\">启用</a>";
	}
	var removeBtn = "<a href='javascript:;' class='no_unl' title='删除' onclick=\"removeFN('"+rowObject.id+"');return false;\">删除</a>";
	btns.push(updateBtn);
	btns.push(stateBtn);
	btns.push(removeBtn);
	return btns.join(" ");
}

function stateFormater(cellvalue,options,rowObject){
	var str=null;
	if(cellvalue == 1){
		str = "<font color='green'>启用</font>";
	}else{
		str = "<font color='red'>禁用</font>";
	}
	return str;
}

function managerFormater(cellvalue,options,rowObject){
	var str=null;
	if(cellvalue == 1){
		str = "<span class='status-green'>主管</span>";
	}else{
		str = "<span class='status-gray'>--</span>";
	}
	return str;
}
function sexFormater(cellvalue,options,rowObject){
	var str = null;
	if(cellvalue == 1){
		str = "男";
	}else{
		str = "女";
	}
	return str;
}
//查询
function searchUserList(){
	var arr = $("#searchForm").serializeArray();
	var postData = {};
	for(var i=0;i<arr.length;i++){
		var entry = arr[i];
		postData[entry["name"]]=entry["value"];
	}
	$('#table').setGridParam({
		mtype:'post',
		postData:postData
	}).trigger('reloadGrid',[{page:1}]);
}

//启用/禁用
function stateFN(id,state){
	var xval= getBusyOverlay();
	$.post("admin/auth/user/updateState.htm?m="+Math.random(),{"item.id":id,"item.state":state},function(item){
		xval.remove();xval = null;
		reloadGrid();
	});
}
//截取时间
function dateFormater(cellvalue,options,rowObject){
	if(cellvalue && cellvalue.length > 0){
		var start = cellvalue.indexOf(" ");
		return cellvalue.substring(0,start);
	}
	return cellvalue;
}
// 修改
function updateFN(id){
	var tv = {};
	tv.text = '修改用户';
	tv.url = 'admin/auth/user/viewUser.htm?m='+Math.random()+'&item.id='+id;
	tv.tabId = "tabli_update_user_"+id;
	tv.doc = parent.document;
	tv.refresh=true;
	showTab(tv);
}

//修改
function updateOldFN(id){
	$.post("admin/auth/user/viewUser.htm?m="+Math.random(),{"item.id":id},function(item){
		//填充数值
		$("#itemId").val(item.id);
		$("#itemName").val(item.name);
		var birthday = item.birthday;
		if(null!=birthday){
			var start = birthday.indexOf(" ");
			$("#itemBirthday").val(birthday.substring(0,start));
		}
		$("#itemStaffId").val(item.staffId);
		var chkIndex = (item.sex == 1 ? 0 : 1);
		$("input[name='item.sex']").eq(chkIndex).attr("checked",true);
		$("#itemTel").val(item.tel);
		$("#itemMobile").val(item.mobile);
		$("#itemEmail").val(item.email);
		$("#contact").val(item.contact);
		$("#itemAccountTd").text(item.account);
		
		//显示角色
		$("#roleListUl").empty();
		$("#roleIds").val("");
		var roleIds = [];
		for(var i=0;i<item.roles.length;i++){
			var role = item.roles[i];
			roleIds.push(role.id);
			var cli = $("<li />");
			cli.attr("id","role_li_"+role.id);
			cli.append("<span>"+role.name+"</span>");
			cli.append('<a title="移除" class="del" href="javascript:;" onclick="removeCurRole(this);">X</a>');
			$("#roleListUl").append(cli);
		}
		if(roleIds.length>0){
			if(roleIds.length == 1){
				$("#roleIds").val(roleIds[0]);	
			}else{
				$("#roleIds").val(roleIds.join("-"));
			}
		}
		
		//$("#userForm input:not([id='itemBirthday'])").attr("readonly",false);
		$("#save_user_btn").show();	//显示保存按钮
		$("#addAccountDv").hide();	//隐藏添加账号
		$("#resetPwdBtnDiv").show();	//显示重置密码
		
		//不验证密码
		$("#itemPassword").unFormValidator(true);
		$("#itemAccount").unFormValidator(true);
		$.formValidator.pageIsValid("1");	//提前验证
		
		var loginInfoBar=$("#login_info_bar");
		$(loginInfoBar).show().addClass("infobar");
		var offsetTop=$("#lastBottomDom").offset().top;
		$("html").animate({scrollTop: offsetTop}, 800,function(){
			$(loginInfoBar).fadeIn("fast",function(){
				$(loginInfoBar).removeClass("infobar");
			});
		});
		
	});
}
//删除
function removeFN(id){
	//确认提示
	var dialog = $.dialog({
	    title: '确认删除',
	    content: '确认要删除吗？',
	    okVal:"删除",
	    cancelVal:"取消",
	    ok: function(){
	    	$.post("admin/auth/user/delUserById.htm?m="+Math.random(),{"item.id":id},function(data){
				 $.dialog.alert(data.message);
				 reloadGrid();
			});
	    },
	    cancel:function(){
	    	this.close();
	    }
	});
}
//批量删除
function doBatchDel(){
	$.dialog.confirm('确认要进行批量删除吗？',function(){
		var selIds = $("#table").jqGrid("getGridParam", "selarrrow");
		$.post("admin/auth/user/doBatchDelUser.htm?m="+Math.random(),{"model.selIds":selIds.join(",")},function(data){
			$.dialog.alert(data.message);
			reloadGrid();
		});
	});
	
}


//提交表单
function submitForm(frm,falg){
	var result = $.formValidator.pageIsValid(1);	//手动调用验证框架进行验证
	if(!result){
		return;
	}
	
	//确认提示
	var dialog = $.dialog({
		follow:document.getElementById("saveUserBtn"),
	    title: '确认提交',
	    content: '确认要保存吗？',
	    okVal:"保存",
	    cancelVal:"取消",
	    ok: function(){
	    	var itemId  = $("#itemId").val();
	    	if(null != itemId && "" != itemId){
	    		$(frm).attr("action","admin/auth/user/updateUser.htm");
		    	$(frm).submit();
	    	}else{
	    		$(frm).attr("action","admin/auth/user/saveUser.htm");
	    		$(frm).submit();
	    	}
	    },
	    cancel:function(){
	    	this.close();
	    }
	});
}
//删除角色
function removeCurRole(link){
	var li = $(link).parent("li");
	var role_id = li.attr("id").substr(8);
	li.remove();
	
	var roleIds = $("#roleIds");
	if(roleIds.val().length ==1){
		roleIds.val('');
	}else{
		var arr = roleIds.val().split(",");
		for(var i=0;i<arr.length;i++){
			if(arr[i] == role_id){
				arr.splice(i,1);
			}
		}
		
		roleIds.val(arr.join(","));
	
	}
}

//确认重置密码
function saveNewPwd(){
	var resetPwd = $("input[name='resetPwd']").val();
	if(resetPwd.length <6 || resetPwd.length > 50){
		//确认提示
		var resetConfirmBtn = $.dialog({
			follow:document.getElementById("saveNewPwdBtn"),
		    title: '提交失败',
		    content: '重置密码长度不正确',
		    okVal:"关闭",
		    ok: function(){
		    }
		});
		return false;
	}
	
	$.dialog({
			follow:document.getElementById("saveNewPwdBtn"),
		    title: '确认提交',
		    content: '确认要重置密码吗？',
		    okVal:"保存",
		    cancelVal:"取消",
		    ok: function(){
		    	var itemId = $("#itemId").val();
				var xval=getBusyOverlay();
				$.post("admin/auth/user/resetPwd.htm?m="+Math.random(),{"item.id":itemId,"item.password":resetPwd},function(data){
					xval.remove();xval = null;
					$("#resetpassword_bar").hide();//隐藏重置密码框
					$("input[name='resetPwd']").val("");//重置数据
					$.dialog.alert(data.message);					
				});
		    },
		    cancel:function(){
		    }
		    
		});
	
	
}
//重新加载
function reloadGrid(){
	$('#table').trigger('reloadGrid',[{page:1}]);
}

// 新增子类
function addChild(){
	hideRMenu();
	if(curNode == null){
		$.dialog.alert("请选择父类！");
		return;
	}
	
	// 隐藏右键菜单
	hideRMenu();
	// 若是多选，则只选中当前一个
	var location = curNode.location;
	location = parseInt(location);
	location++;
	var isParent = curNode.isParent;
	var isParentItem = 0;
	if(isParent){
		isParentItem = 1;
	}
	$.dialog.open(
		base+"admin/auth/depart/toUpdateDepart.htm?item.parentId="+curNode.id+"&item.isParent="+isParentItem,{
			title : "新增资源标签",
			width : "500px",
			height : "250px",
			lock : true
		}
	);
}

function editNode(){
	// 隐藏右键菜单
	hideRMenu();
	if(curNode == null){
		$.dialog.alert("请选择类别！");
		return;
	}
	if(curNode.id == "0"){
		$.dialog.alert("根节点不能修改");
		return;
	}
	$.dialog.open(
		base+"admin/auth/depart/toUpdateDepart.htm?item.id="+curNode.id,{
			title : "修改资源标签",
			width : "500px",
			height : "250px",
			lock : true
		}
	);
}

function treeLoadUrl(treeId, treeNode) {
	return treeNode == null ? "admin/auth/depart/list_depart.htm" : "admin/auth/depart/list_depart.htm?parentId ="+treeNode.id;
}

function OnRightClick(event, treeId, treeNode) {
	if (!treeNode && event.target.tagName.toLowerCase() != "button"
			&& $(event.target).parents("a").length == 0) {
		zTree.cancelSelectedNode();
		showRMenu("root", event.clientX, event.clientY);
		curNode = null;
	} else if (treeNode && !treeNode.noR) {
		// 暂时取消 右键选中 节点功能
		// curNode = treeNode;
		// zTree.selectNode(treeNode);
		showRMenu("node", event.clientX, event.clientY);
	}
}

/** 点击zTree查询 */
function OnClick(event, treeId, treeNode) {
	curNode = treeNode;
	searchCategoryList();
}

/** 单击类别树查询 */
function searchCategoryList() {
	var postData = {"condition.departId":curNode.id};
	$('#table').setGridParam({
		mtype:'post',
		postData:postData
	}).trigger('reloadGrid',[{page:1}]);
}

function showRMenu(type, x, y) {
	$("#rMenu ul").show();
	if (type == "root") {
		curNode = null;
		$("#m_del").hide();
		$("#m_check").hide();
		$("#m_unCheck").hide();
	} else {
		$("#m_del").show();
		$("#m_check").show();
		$("#m_unCheck").show();
	}
	rMenu.css({
		"top" : y + "px",
		"left" : x + "px",
		"visibility" : "visible"
	});

	$("body").bind("mousedown", onBodyMouseDown);
}

/** 重新加载 * */
function reloadTree() {
	hideRMenu();
	zTree.reAsyncChildNodes(null, "refresh");
}

/** 刷新指定节点 */
function reloadNodes(id) {
	var node = zTree.getNodeByParam('id', id);
	if (node != null) {
		if (node.open) {
			zTree.reAsyncChildNodes(node, 'refresh');
		} else {
			zTree.expandNode(node);
		}
	}
	hideRMenu();
}


function refreshNodes(id,obj){
	var node = zTree.getNodeByParam('id', id);
	var childNode = {"name":obj.item.name,"id":obj.item.id,"isDelete":0,"parentId":obj.item.parentId};
	zTree.addNodes(node,childNode);
	hideRMenu();
}

/** 隐藏右键菜单 */
function hideRMenu() {
	if (rMenu)
		rMenu.css({
					"visibility" : "hidden"
				});
	$("body").unbind("mousedown", onBodyMouseDown);
}

function onBodyMouseDown(event) {
	if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length > 0)) {
		rMenu.css({"visibility" : "hidden"});
	}
}

//条件模糊查询
function search(){
	var postData = {};
	// 快速搜索部分
	var nameKey = $("#searchKey").val();
	if(nameKey == "请输入属性名称模糊查询"){
		nameKey = "";
	}
	postData["condition.name"] = nameKey;
	postData["condition.mobile"] = nameKey;
	postData["condition.email"] = nameKey;
	
	$('#table').setGridParam({
		mtype:'post',
		postData:postData
	}).trigger('reloadGrid',[{page:1}]);
}

	function manager(){
			$.post("admin/auth/depart/delDepartById.htm?item.id="+curNode.id,function(data){
						if(data.status=="success"){
							reloadNodes(curNode.parentId);
						}else{
							$.dialog.alert(data.message);
						}
					});
	}

//设置是否用户主管
function manageUser(){
	var selIds = $("#table").jqGrid("getGridParam", "selarrrow");
	if(selIds == "" || selIds == null){
			$.dialog.alert("请选择要设置用户！");
			return;
	}
	$.post("admin/auth/user/doManagerContr.htm?m="+Math.random(),{"model.selIds":selIds.join(","),"model.managerId":curNode.id},function(data){
		$.dialog.alert(data.message);
		reloadGrid();
	});
}
//设置取消用户主管
function manageNoUser(){
	var selIds = $("#table").jqGrid("getGridParam", "selarrrow");
	if(selIds == "" || selIds == null){
			$.dialog.alert("请选择要设置用户！");
			return;
	}
	$.post("admin/auth/user/doManagerNoContr.htm?m="+Math.random(),{"model.selIds":selIds.join(","),"model.managerId":curNode.id},function(data){
		$.dialog.alert(data.message);
		reloadGrid();
	});
}

