package com.dengtu.website.common.sms.config;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	public static String SPRING_SECURITY_FORM_MOBILE_KEY = "phone";
	private boolean postOnly = true;

	public SmsCodeAuthenticationFilter() {
        super(new AntPathRequestMatcher("/sms/login", "POST"));
    }

	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		// 获取请求中的参数值
		String mobile = obtainMobile(request);

		if (Objects.isNull(mobile)) {
			mobile = "";
		}

		mobile = mobile.trim();
		
		SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(mobile);

		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);

		return this.getAuthenticationManager().authenticate(authRequest);
	}

	/**
     * 获取手机号
     */
	protected String obtainMobile(HttpServletRequest request) {
		return request.getParameter(SPRING_SECURITY_FORM_MOBILE_KEY);
	}

	protected void setDetails(HttpServletRequest request, SmsCodeAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}

	public void setMobileParameter(String mobileParameter) {
		Assert.hasText(mobileParameter, "Mobile parameter must not be empty or null");
		this.SPRING_SECURITY_FORM_MOBILE_KEY = mobileParameter;
	}

	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}

	public final String getMobileParameter() {
		return SPRING_SECURITY_FORM_MOBILE_KEY;
	}

}