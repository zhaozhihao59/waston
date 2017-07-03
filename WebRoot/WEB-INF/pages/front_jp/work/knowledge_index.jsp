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
	<jsp:include page="/WEB-INF/pages/front_jp/comm/front_header.jsp"><jsp:param name="menu" value="3" /></jsp:include>
	<jsp:include page="/WEB-INF/pages/front_jp/comm/news_move.jsp"></jsp:include>
	<div class="act-index-middlebox">
		<div class="fl act-left-box">
			<div class="hehua-img"></div>
			<div class="act-tt-box">業務分野</div>
			<ul class="act-left-ul">
				<li class="cur"><a href="javascript:void(0);" class="yew01-ico">会社商事業務</a></li>
				<li><a href="javascript:void(0);" class="yew02-ico">知的財産権業務</a></li>
				<li><a href="javascript:void(0);" class="yew03-ico">訴訟仲裁業務</a></li>
				<li><a href="javascript:void(0);" class="yew04-ico">資産管理業務</a></li>
			</ul>
			<div class="mt10">
				<a class="pw-wa" href="cms_jp/to_common_problem_jp.htm">よくある問題<i></i></a>
				<!-- <a class="dy-wa" href="#">华诚通讯订阅 --><i></i></a>
			</div>
		</div>
		<div class="fr actright-box">
			<div class="">
				<div class="fl scl-ywly-ttbg mt5"></div>
				<div class="fr scl-snav-box">
					<img src="static/front/css/images/shouse-ico.png" class="vm mr5">
					<a href="jp/index.htm" class="vm">ホーム</a>
					<label class="vm">></label>
					<span class="vm">業務分野</span>
				</div>
				<div class="cb"></div>
			</div>
			<div class="knowledge-ccbox mt20">華誠の専門スタッフには豊富な経験があり、クライアント毎に個別のサービスを提供することで、それぞれ個別のご要望に満足をいただくことができます。華誠の提供するサービスの範囲は、知的財産権の売買、出願および維持、知的財産権訴訟や経済訴訟、投資、会社事務、銀行、融資、保険、不動産、商業取引、税務、労働争議およびその他中国における業務です。</div>
			<ul class="knowledge-ul clb mt20">
				<li class="cur">会社商事業務</li>
				<li>知的財産権業務</li>
				<li>訴訟仲裁業務</li>
				<li>資産管理業務</li>
			</ul>
			<div class="scl-knowledge-showbox" >
				<div class="fl mt25">
					<img src="static/front_jp/comm/images/qqbg.png">
				</div>
				<div class="fr" style="width:540px;margin-top:55px;">
					<div class="knowledge-ccbox">
						   &#12288;&#12288;会社商事部門のクライアントには世界トップクラスの多国籍企業が数多く含まれ、その業務領域はソフトウェア、自動車、鋼鉄、化学、製薬、機械、電子、不動産、文化とメディア、取引関係、金融とファイナンスなどに及びます。
					</div>
					<div class="knowledge-ccbox mt10">
						   &#12288;&#12288;企業商務部門が提供する法律サービスは投資、企業再編と合併、証券、先物取引、融資、コンプライアンス、知的財産権のライセンスと譲渡、知的財産権戦略の企画、権利保護とDD調査、反商業賄賂、独占禁止、労働、破産・清算、不動産、税務戦略の策定関連などを含みます。
					</div>
					<div class="knowledge-ccbox mt10">
						   &#12288;&#12288;また、会社経営管理の各段階で詳細かつ全面的な法律意見を提供します。
					</div>
					<div class="knowledge-ccbox mt10">
						   &#12288;&#12288;華誠は専門的で創造的なビジネス法務コンサルティングに精通しています。政府の関係部門、専門仲介機関との長期的な連携と協力関係を生かし、クライアントの異なるニーズに対応します。

					</div>
				</div>
				<div class="cb"></div>
				<div class="bmhd-tt-box" style="margin-top:50px;display:inline-block;">
				<div class="yy-jt-ico"></div>
				関連事例
				<a class="scl-more" href="jp/pub_ListPage_jp.htm">更に詳しく</a>
			</div>
			
			<ul class="knowledge-list-ul mt20">
				<c:forEach var="caf" items="${cmsArticleField1}" varStatus="cafstaus">
				<c:if test="${cafstaus.index < 5}">				
				<li>
					<div class="fl"><a href="jp/cmsDetail_jp.htm?item.id=${caf.id}">${dv:e(caf.jpName,100)}</a></div>
					<div class="fr scl-date-col"><fmt:formatDate value="${caf.createDate}" pattern="yyyy-MM-dd"/></div>
					<div class="cb"></div>
				</li>
				</c:if>
				</c:forEach>
			</ul>
			</div>
			<div class="scl-knowledge-showbox none">
					<div class="fl" style="width:540px;margin-top:55px;">
						<div class="knowledge-ccbox">
							   &#12288;&#12288;華誠は、国内外の企業に知的財産権分野における全面的なサービスを提供することを目指しております。知的財産権サービスには、特許、商標、集積回路配置図設計及び著作権分野での出願や登録申請、異議申立、無効審判、譲渡、調査、届出、商標顕著性鑑定、特許有効性及び侵害鑑定が含まれます。
						</div>
						<div class="knowledge-ccbox mt10">
							   &#12288;&#12288;知的財産権代理業務とコンサルティング業務は化学、生物、医薬、機械、電子、通信、光学物理、意匠、商標、著作権等の領域をカバーします。各専門部門の経験豊富で複数国の言語に対応できる弁理士、商標代理人、弁護士、技術者、専門スタッフ、翻訳者が案件を直接担当します。
						</div>
						<div class="knowledge-ccbox mt10">
							   &#12288;&#12288;華誠は知的財産権代理業務に統一した事務手続きを詳細に定めるとともに、高度に電子化された自動管理システムを構築しております。これにより知的財産権代理案件の手続をタイムリーかつ効率的、高品質、安全に行うとともに、コンフリクト防止及び手続上のミスの防止に取り組んでいます。
						</div>
					</div>
					<div class="fr mt25">
						<img src="static/front_jp/comm/images/qqbg2.png">
					</div>
					<div class="cb"></div>
					<div class="bmhd-tt-box" style="margin-top:50px;display:inline-block;">
				<div class="yy-jt-ico"></div>
					関連事例
					<a class="scl-more" href="jp/pub_ListPage_jp.htm">更に詳しく</a>
				</div>
				<ul class="knowledge-list-ul mt20">
				<c:forEach var="caf" items="${cmsArticleField2}" varStatus="cafstaus">	
				<c:if test="${cafstaus.index < 5}">		
					<li>
						<div class="fl">
							<a href="jp/cmsDetail_jp.htm?item.id=${caf.id}">${dv:e(caf.jpName,100)}</a>
						</div>
						<div class="fr scl-date-col">
							<fmt:formatDate value="${caf.createDate}" pattern="yyyy-MM-dd"/>
						</div>
						<div class="cb"></div>
					</li>
					</c:if>
					</c:forEach>
				</ul>
			</div>
			<div class="scl-knowledge-showbox none">
				<div class="knowledge-ccbox" style="margin-top:25px;">
					   &#12288;&#12288;華誠は、経験豊富な専門的訴訟チームと仲裁チームを擁し、そのメンバーには元裁判官、シニア弁護士、知的財産権専門家、大学教授が含まれます。
				</div>
				<div class="knowledge-ccbox mt10">
					   &#12288;&#12288;訴訟・仲裁業務は、特許紛争、商標紛争、著作権紛争、不正競争及び営業秘密紛争、知的財産権ライセンスと譲渡紛争、会社管理上の紛争、上場会社の株主制限紛争、M&A紛争、外国投資家の投資紛争、保険と金融紛争、共同経営紛争、販売許諾紛争、取引紛争、海商紛争、不動産紛争、労働と労務紛争、婚姻・家庭紛争、及びその他、係争金額が巨額かつ社会的に影響力のある商事紛争を対応しております。
				</div>
				<div class="mt20 mb15">
					<img src="static/front/css/images/qqbg3.png">
				</div>
				<div class="knowledge-ccbox mt10">
					   &#12288;&#12288;高品質で効率的なサービスを提供できるよう、業務の分類と性質に応じて特許訴訟部、商標著作権訴訟部、民商事訴訟部、証拠収集調査部がそれぞれ設立し、専門弁護士と専任スタッフを配置しました。各業務部門間は緊密かつ効果的に連結し、厳密な訴訟・仲裁戦略を作成し、チームで事件に対応することでクライアントの合法的な権利利益の保護を最大限に図ります。
				</div>
				<div class="knowledge-ccbox mt10">
					   &#12288;&#12288;華誠は長年にわたり、社会的に重大な影響力のある、あるいは、代表的事例となる一連の訴訟・仲裁案件を代理してきました。また、中国国内では見られなかった新しいタイプの事件も多数代理し、中国初の外国企業同士の特許権侵害紛争事件、中国初の裁判所による馳名商標認定商標権侵害紛争事件等、外国スポーツ・スポンサー契約に基く中国初の契約違反紛争事件、中国企業と外国企業にクロス・ボーダー訴訟と商事紛争仲裁案件などの経験があります。これらの事件はこれまでしばしば法曹界で引用例、模範事例とされています。
				</div>
				<div class="bmhd-tt-box" style="margin-top:50px;display:inline-block;">
				<div class="yy-jt-ico"></div>
				関連事例
				<a class="scl-more" href="jp/pub_ListPage_jp.htm">更に詳しく</a>
			</div>
			
			<ul class="knowledge-list-ul mt20">
				<c:forEach var="caf" items="${cmsArticleField3}" varStatus="cafstaus">	
				<c:if test="${cafstaus.index < 5}">		
					<li>
						<div class="fl">
							<a href="jp/cmsDetail_jp.htm?item.id=${caf.id}">${dv:e(caf.jpName,100)}</a>
						</div>
						<div class="fr scl-date-col">
							<fmt:formatDate value="${caf.createDate}" pattern="yyyy-MM-dd"/>
						</div>
						<div class="cb"></div>
					</li>
					</c:if>
					</c:forEach>
				</ul>
			</div>
			<div class="scl-knowledge-showbox none">
				<div class="knowledge-ccbox" style="margin-top:25px;">
				   <div class="title-pbk">資産管理業務</div><div class="mt10 mb10">華誠には専門な個人資産管理チームがあり、家族企業、有名人及びスター、富裕層等の個人資産の法律リスク管理訴訟及び訴訟以外のサービスを提供しております。銀行や保険会社、会計士事務所、国内外の信託機関、移民機関等資産管理機関と提携し、家族企業に個性ある個人資産管理及び継承プランを策定し、専門的法律サービスを提供しております。華誠はクライアントの立場に立った効率的で信頼性のある法律サービス及び解決策を提供し、クライアントの信頼を核としております。</div>
					<img src="static/front/css/images/cfgl.jpg">
					<div class="title-pbk">婚姻・家庭業務</div><div class=" mb10">華誠は中国でも比較的早い時期から婚姻・家庭事件の専門化チームがあり、上海で比較的大きな影響力と比較的高い社会的認可を得ているばかりでなく、メディアにも度々報道されています。チームの弁護士は理論的な知識が豊富であるのみならず、実務においても豊富な経験があり、その業務は婚前計画、婚姻保護、婚姻解除、子育て・養子縁組、渉外婚姻及び移民教育等を対象としています。</div>		
					<div class="title-pbk">資産継承業務</div><div class="mt10">華誠は資産管理サービスを提供するのみならず、個別の資産相続業務サービスも提供しております。その業務は相続計画及びドラフト作成と立会い、相続信託、慈善活動及び相続手続き、遺産管理、調査及び訴訟、家族企業資産継承計画及び外国・香港・マカオ・台湾での継承等を対象としています。</div>	
				</div>
				<div class="bmhd-tt-box" style="margin-top:50px;display:inline-block;">
				<div class="yy-jt-ico"></div>関連事例<a class="scl-more" href="jp/pub_ListPage_jp.htm">更に詳しく</a>
			</div>
			
			<ul class="knowledge-list-ul mt20">
				<c:forEach var="caf" items="${cmsArticleField4}" varStatus="cafstaus">	
				<c:if test="${cafstaus.index < 5}">		
					<li>
						<div class="fl">
							<a href="jp/cmsDetail_jp.htm?item.id=${caf.id}">${dv:e(caf.jpName,100)}</a>
						</div>
						<div class="fr scl-date-col">
							<fmt:formatDate value="${caf.createDate}" pattern="yyyy-MM-dd"/>
						</div>
						<div class="cb"></div>
					</li>
					</c:if>
					</c:forEach>
				</ul>
			</div>
			<script>
			var s='${i}';
			$(function(){
			if(s==null||s==""){
				s=0;
			}
				$(function(){
					$(".act-left-ul li").each(function(i){
						$(".knowledge-ul li").eq(s).addClass("cur").siblings(".knowledge-ul li").removeClass("cur");
						$(".scl-knowledge-showbox").eq(s).show().siblings(".scl-knowledge-showbox").hide();
						$(this).click(function(){
							$(this).addClass("cur").siblings().removeClass("cur");
							$(".scl-knowledge-showbox").eq(i).css("marginTop",20);
							$(".knowledge-ul li").eq(i).addClass("cur").siblings(".knowledge-ul li").removeClass("cur");
							$(".scl-knowledge-showbox").eq(i).show().siblings(".scl-knowledge-showbox").hide();
						});
					});
					$(".knowledge-ul li").each(function(i){
						$(".act-left-ul li").eq(s).addClass("cur").siblings(".act-left-ul li").removeClass("cur");
						$(".scl-knowledge-showbox").eq(s).show().siblings(".scl-knowledge-showbox").hide();
						$(this).click(function(){
							$(this).addClass("cur").siblings().removeClass("cur");
							
							$(".scl-knowledge-showbox").eq(i).css("marginTop",20);
							$(".act-left-ul li").eq(i).addClass("cur").siblings(".act-left-ul li").removeClass("cur");
							$(".scl-knowledge-showbox").eq(i).show().siblings(".scl-knowledge-showbox").hide();
						});
					});
				});
			
			});	
			</script>
		</div>
		<div class="cb"></div>
	</div>
	<jsp:include page="/WEB-INF/pages/front_jp/comm/front_foot_v2.jsp"></jsp:include>
</body>
</html>
