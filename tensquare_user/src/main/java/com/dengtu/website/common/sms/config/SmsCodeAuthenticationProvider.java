package com.dengtu.website.common.sms.config;

import com.dengtu.website.common.sms.service.AuthCodeService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Objects;

@Data
@Slf4j
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

	private UserDetailsService userDetailsService;


    private AuthCodeService authCodeService;
    public static final String AUTH_CODE_KEY_PREFIX= "authcode:";

    private RedisTemplate redisTemplate;
	
	@Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;

        String mobile = (String) authenticationToken.getPrincipal();
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        String inputCode = request.getParameter("code");
        //判断验证码
//        verifyAuthCode(SendAuthCodeType.LOGIN,mobile,inputCode);

        /**
         * 调用 {@link UserDetailsService}
         */
        UserDetails user = userDetailsService.loadUserByUsername(mobile);

        if (Objects.isNull(user)) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }

        SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(user, user.getAuthorities());

        authenticationResult.setDetails(authenticationToken.getDetails());
        SecurityContextHolder.getContext().setAuthentication(authenticationResult);

        return authenticationResult;
    }
//    public Boolean verifyAuthCode(SendAuthCodeType type, String phone, String code) {
//        String key = AUTH_CODE_KEY_PREFIX + type.getValue().toLowerCase() + ":" + phone;
//        String value = (String) redisTemplate.opsForValue().get(key);
//        if(!StringUtils.isEmpty(value)) {
//            if (value.equals(code)) {
//                return true;
//            }
//            log.error(phone + "输入的验证码" + code + "不正确");
//        }else {
//            log.error(phone + "找不到对应的验证码，验证码可能失效了");
//        }
//        throw new PlatformException(ResultCodeEnum.TENANT_AUTHCODE_ERROR);
//    }
	@Override
	public boolean supports(Class<?> authentication) {
		return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
