package com.bestpay.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.spi.LoggerFactory;

import com.bestpay.model.CommModel;

/**
 *<pre>
 *project:heros
 *path:com.bestpay.util.BestpayUtil.java
 *description : 
 * 翼支付util .  
 * 参数：
 * 	request,
 *  产品费用，
 *  额外的费用，
 *  商户说明，
 *  用户信息
 *</pre>
 * @author J.殷嘉健
 * @date 2015年3月12日下午5:20:15
 */
public class BestpayUtil {
	
	public static CommModel createCommModel(HttpServletRequest request,String amount,String customerId) {
		CommModel commModel = new CommModel();
		commModel.setIpRemote(request.getRemoteAddr());
		commModel.setProductAmount(amount);
		commModel.setAttachAmount("0");
		commModel.setProductAmount(amount);
		commModel.setTransAmount(amount);
		commModel.setOrderDate(createOrderDate());
		commModel.setOrderID(createOrderId());
		commModel.setOrderReqTranSeq(createOrderReqTranSeq(commModel.getOrderID()));
		commModel.setCustomerId(customerId);
		
		String sourceStr="MERCHANTID="+commModel.getMerchantId()
				+"&ORDERSEQ="+commModel.getOrderID()
				+"&ORDERDATE="+commModel.getOrderDate()
				+"&ORDERAMOUNT="+amount
				+"&CLIENTIP="+commModel.getIpRemote()
				+"&KEY="+commModel.getCommKey();
		try {
			commModel.setMac(CryptTool.md5Digest(sourceStr));
		} catch (Exception e) {
			return null;
		}
		return commModel;
	}
	
	@Deprecated
	public static void pay(HttpServletRequest request,Double productAmountD,Double attachAmountD,String attach,String customerId) throws Exception {
//		CommModel commModel = new CommModel();
//		commModel.setOrderID(createOrderId());
//		commModel.setOrderReqTranSeq(createOrderReqTranSeq(commModel.getOrderID()));
//		commModel.setOrderDate(createOrderDate());
//		String productAmount = formatAmount(productAmountD);
//		String attachAmount = formatAmount(attachAmountD);
//		String transAmount = formatAmount(productAmountD+attachAmountD);
//		String curType = "RMB";
//		String encodeType = "1";
//		if(attach == null || "".equals(attach)) {
//			attach = "比赛报名";
//		}
//		String busiCode = "0001";
//		//业务标识 
//		String productId = "99";
//		//终端号码  
//		String tmNum = "99";
//		
//		//产品描述  *
//		String productDesc = "比赛报名";
////		String ipRemote=(String)request.getAttribute("ipRemote");
//		String ipRemote="116.228.55.237";
//		String commkey=commModel.getCommKey();
//		String sourceStr="MERCHANTID="+commModel.getMerchantID()+"&ORDERSEQ="+commModel.getOrderID()+"&ORDERDATE="+commModel.getOrderDate()+"&ORDERAMOUNT="+transAmount+"&CLIENTIP="+ipRemote+"&KEY="+commkey;
//		
//		String params = "?MERCHANTID="+commModel.getMerchantID()
//						+"&SUBMERCHANTID="+commModel.getMerchantID()
//						+"&ORDERSEQ="+commModel.getOrderID()
//						+"&ORDERREQTRANSEQ="+commModel.getOrderReqTranSeq()
//						+"&ORDERDATE="+commModel.getOrderDate()
//						+"&PRODUCTAMOUNT="+productAmount
//						+"&ATTACHAMOUNT="+attachAmount
//						+"&ORDERAMOUNT="+transAmount
//						+"&CURTYPE="+curType
//						+"&ENCODETYPE="+encodeType
//						+"&MERCHANTURL="+commModel.getMerchantUrl()
//						+"&BACKMERCHANTURL="+commModel.getBackMerchantUrl()
//						+"&ATTACH="+attach
//						+"&BUSICODE="+busiCode
//						+"&PRODUCTID="+productId
//						+"&TMNUM="+tmNum
//						+"&CUSTOMERID="+customerId
//						+"&PRODUCTDESC="+productDesc
//						+"&DIVDETAILS="						
//						+"&PEDCNT="						
//						+"&CLIENTIP="+ipRemote					
//						+"&MAC="+CryptTool.md5Digest(sourceStr)						
//						;
//		
//        URL url = null; 
//        HttpURLConnection con  =null; 
//        BufferedReader in = null; 
//        StringBuffer result = new StringBuffer(); 
//        try { 
//            url = new URL("https://webpaywg.bestpay.com.cn/payWeb.do"); 
//            con  = (HttpURLConnection) url.openConnection(); 
//            con.setUseCaches(false); 
//            con.setDoOutput(true); 
//            con.setRequestMethod("POST"); 
//            String paramsTemp = params; 
//            byte[] b = paramsTemp.getBytes(); 
//            con.getOutputStream().write(b, 0, b.length); 
//            con.getOutputStream().flush(); 
//            con.getOutputStream().close(); 
//            in = new BufferedReader(new InputStreamReader(con.getInputStream())); 
//            while (true) { 
//              String line = in.readLine(); 
//              if (line == null) { 
//                break; 
//              } 
//              else { 
//                  result.append(line); 
//              } 
//            } 
//        } catch (IOException e) { 
//            e.printStackTrace(); 
//        }finally{ 
//            try { 
//                if(in!=null){ 
//                    in.close(); 
//                } 
//                if(con!=null){ 
//                    con.disconnect(); 
//                } 
//            } catch (IOException e) { 
//                e.printStackTrace(); 
//            } 
//        } 
		
	}
	private static String createOrderId() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHMMSS");
		Date now =new Date(System.currentTimeMillis());
		return format.format(now);
	}
	private static String createOrderReqTranSeq(String orderId) {
		Random random = new Random();
		int i = random.nextInt(999);
		return orderId + String.format("%06d", i);   
	}
	private static String createOrderDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date now =new Date(System.currentTimeMillis());
		String orderDate = format.format(now);
		return orderDate;
	}
	private static String formatAmount(Double amount) {
		BigDecimal b = new BigDecimal(amount);
		double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return String.valueOf(f1);
	}
}
