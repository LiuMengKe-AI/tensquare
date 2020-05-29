package com.dengtu.website.common.util;



import com.dengtu.website.common.error.PlatformException;
import com.dengtu.website.common.security.component.SecurityUser;
import com.dengtu.website.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Type SecurityUtil.java
 * @Desc Spring Security相关的工具类，获取当前登陆账户的一些权限信息
 */
public class SecurityUtil {
    /**
     * Return null on anonymous login.
     *
     * @return
     */
    public static SecurityUser getCurrentUser() {
        SecurityUser currentUser = null;
        SecurityContext context = SecurityContextHolder.getContext();
        if (context != null) {
            Authentication authentication = context.getAuthentication();
            if (authentication != null) {
                Object current = authentication.getPrincipal();
                if (current instanceof SecurityUser) {
                    currentUser = (SecurityUser) current;
                }
            }
        }
        return currentUser;
    }

    public static String getToken() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
    }


    /**
     * Return null on anonymous login.
     *
     * @return
     */
    public static User getUser() {
        SecurityUser currentUser = getCurrentUser();
        if (currentUser == null){
            throw new PlatformException(403,"请先登录");
        }
        return currentUser.getUser();
    }
    public static Integer getUserId() {
        SecurityUser currentUser = getCurrentUser();
        if (currentUser == null){
            throw new PlatformException(403,"请先登录");
        }
        return currentUser.getUser().getId();
    }
}
