package com.tocersoft.base.utils;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class SmsBase {
	String result="-20";
	// http://www.ztsms.cn:8800/sendXSms.do?username=haha&password=888888&mobile=13900000000&content=test&dstime=&productid=61341&xh=
	public String SendSms(String mobile,String content) throws HttpException, IOException{
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://www.ztsms.cn:8800/sendXSms.do");
		client.getParams().setParameter( HttpMethodParams.HTTP_ELEMENT_CHARSET, "UTF-8");
		client.getParams().setParameter( HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		
		NameValuePair[] tmpNameValuePairs = new NameValuePair[]{
				// 用户名
				new NameValuePair("username", "gowin365"),
				// 密码
				new NameValuePair("password", "Gowin365"),
				new NameValuePair("mobile", mobile),
				new NameValuePair("content", content),
				new NameValuePair("dstime", ""),
				// 产品ID号
				new NameValuePair("productid", "676766"),
				new NameValuePair("xh", "")
		};
		post.setQueryString(tmpNameValuePairs);
		int i = client.executeMethod(post);
		String res = post.getResponseBodyAsString();
		if (i == 200) {
			result = i+"";
			System.out.println("发送结果:"+res);
		}else {
			result = i+"";
			System.out.println("异常："+i);
			System.out.println(res);
		}
		return result;
	}
}
