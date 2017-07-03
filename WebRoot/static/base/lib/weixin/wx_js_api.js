
/***
 * 微信JS-SDK初始化
 */
wx.config({
    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    appId: appid, // 必填，公众号的唯一标识
    timestamp: timestamp, // 必填，生成签名的时间戳
    nonceStr: nonceStr, // 必填，生成签名的随机串
    signature: signature,// 必填，签名，见附录1
    jsApiList: ['openLocation','getLocation'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
});

wx.ready(function () {
	var gpsCookie = getCookie('lc');
	if(gpsCookie == null || gpsCookie == ''){
		wx.getLocation({
	      success: function (res) {
	    	  var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
	          var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
	          var lc = latitude+"|"+longitude;
	    	  setCookie("lc",lc,1);
	    	  //alert(lc);
	        //alert(JSON.stringify(res));
	      },
	      cancel: function (res) {
	        //alert('用户拒绝授权获取地理位置');
	      }
	    });
	}
	
});


