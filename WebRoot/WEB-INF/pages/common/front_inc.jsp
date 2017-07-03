<%@ page language="java" pageEncoding="UTF-8"%>
<link rel="shortcut icon" type="image/x-icon">
<link href="static/base/base.css" rel="stylesheet" />
<link href="static/front/comm/common.css" rel="stylesheet" />
<script src="static/base/lib/jquery-1.5.2.min.js"></script>
<script src="static/front/comm/common_front.js"></script>
<script src="static/base/lib/artdialog/jquery.artDialog.source.js?t=${sysVersion}&skin=idialog"></script>
<script src="static/base/lib/artdialog/plugins/iframeTools.source.js?t=${sysVersion}"></script>
<script src="static/base/lib/jquery.form.3.5.js"></script>
<script src="static/base/lib/json2.js"></script>
<script src="static/base/lib/loadmask/jquery.loadmask.min.js"></script>
<script src="static/base/lib/loadmask/jquery.loadmask.js"></script>
<link href="static/base/lib/loadmask/jquery.loadmask.css" rel="stylesheet" />
<script src="static/base/lib/formvalidator/formValidator-4.1.1.js"></script>
<script src="static/base/lib/formvalidator/formValidatorRegex.js"></script>
<meta id="viewport" name="viewport" content="width=device-width, initial-scale=0.8, maximum-scale=1.0" />
<script type="text/javascript" src="static/base/lib/autocomplete/jquery.autocomplete.js"></script>
<script type="text/javascript" src="static/base/lib/kxbdMarquee/jquery.kxbdMarquee.js"></script>
<link rel="Stylesheet" href="static/base/lib/autocomplete/jquery.autocomplete.css" />
<script>
	var basePath = "${BASE_PATH}";
	var base = "${BASE_PATH}";
	browserRedirect();
    function browserRedirect(){
        var sUserAgent = navigator.userAgent.toLowerCase();  
        var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";  
        var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";  
        var bIsMidp = sUserAgent.match(/midp/i) == "midp";  
        var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";  
        var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";  
        var bIsAndroid = sUserAgent.match(/android/i) == "android";  
        var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";  
        var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";  
		
		var vp = document.getElementById('viewport');
        if (bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM) {  
        	vp.setAttribute('content','width=device-width, initial-scale=0.1, maximum-scale=1.0');
        }else if(bIsIpad){
            vp.setAttribute('content','width=device-width, initial-scale=0.6, maximum-scale=1.0');
        }else{
        	vp.setAttribute('content','width=device-width, initial-scale=0.6, maximum-scale=1.0');
        }
    }
</script>