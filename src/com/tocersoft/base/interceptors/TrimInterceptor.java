package com.tocersoft.base.interceptors;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * 拦截器 - 去除页面参数字符串两端的空格
 * @creator     zhangqiang
 * @email       zhangqiang@tocersoft.com
 * @company     www.tocersoft.com
 * @create-time Jun 20, 2012   4:42:14 PM
 * @version 0.1
 */
@Component("trimInterceptor")
public class TrimInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 2365641900033439481L;

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		Map<String, Object> parameters = actionInvocation.getInvocationContext().getParameters();
		for (String key : parameters.keySet()) {
			Object value = parameters.get(key);
			if (value instanceof String[]) {
				String[] values = (String[]) value;
				for (int i = 0; i < values.length; i++) {
					values[i] = values[i].trim();
				}
				parameters.put(key, values);
			}
		}
		return actionInvocation.invoke();
	}

}