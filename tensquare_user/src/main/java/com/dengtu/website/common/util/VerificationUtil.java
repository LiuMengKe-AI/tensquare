package com.dengtu.website.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author LiChunLiang
 * @Date 2018/7/23 18:35
 */
@Slf4j
public class VerificationUtil {

    public static final String DOMAIN_REGEX = "^(?=^.{3,255}$)[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+$";

    public static final String SIGLE_DOMAIN_REGEX="^[a-zA-Z0-9][-a-zA-Z0-9]{0,62}$";

    public static final String MOBILE_REGEX = "^(13[0-9]|14[5-9]|15[0-3,5-9]|16[2,5,6,7]|17[0-8]|18[0-9]|19[0-3,5-9])\\d{8}$";

    public static final String EMAIL_REGEX = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";

    public static final String URI_REGEX = "^(https?|ftp|file):\\/\\/[-A-Za-z0-9+&@#/%?=~_|!:,\\.;]+[-A-Za-z0-9+&@#/%=~_|]";

    public static final String MASK_REGEX = "^(((1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9]).){1}((1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)" +
            ".){2})((1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)/(?:[1-9]|[12][0-9]|3[012])){1}$";

    public static final String PASSWORD_REGEX_LENGTH="[0-9A-Za-z]{8,32}";
    public static final String PASSWORD_REGEX_1="^(?=.*[0-9a-z])(?=.*[0-9A-Z])(?=.*[a-zA-Z])[a-zA-Z0-9]{1,}$";
    public static final String PASSWORD_REGEX_2="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9]{1,}$";

    public static final String SPEC_CHAR = "[`~!@#$%^&*()_+\\-={}\\[\\]\\\\;:'\",<.>?/]";

    public static final String USER_NAME ="^[A-Za-z0-9_\\-\\u4e00-\\u9fa5@#.*]{6,32}$";

    private static final Integer PORT_MIN = 1;

    private static final Integer PORT_MAX = 65535;

    private static final String ID_CARD= "(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$)";

    private final static Integer SCHEDULE_CONFIG_TTL_MIN = 1;
    private final static Integer SCHEDULE_CONFIG_TTL_MAX = 604800;
    private final static Integer SCHEDULE_CONFIG_WEIGHT_MIN = 1;
    private final static Integer SCHEDULE_CONFIG_WEIGHT_MAX = 100;

    /**
     * 验证密码合法性（安全性要求）
     * @param newPassword
     * @param userName
     * @param phone
     * @param email
     * @return
     */
    public static boolean verificationPassword(String newPassword, String userName, String phone, String email) {
        //新密码不能为空
        if(StringUtils.isEmpty(newPassword)) {
            log.error("密码不允许为空");
            return false;
        }
//        //新密码不能和旧密码相同
//        if(StringUtils.isNotBlank(oldPassword)) {
//            if(newPassword.equals(oldPassword)) {
//                log.error("密码不允许与旧密码一致");
//                return false;
//            }
//        }
        //密码应与用户名、手机号码、邮箱无关联
        if(StringUtils.isNotBlank(userName)){
            if(newPassword.toLowerCase().contains(userName.toLowerCase())) {
                log.error("密码不允许包含用户名");
                return false;
            }
        }
        if (StringUtils.isNotBlank(phone)){
            if (newPassword.toLowerCase().contains(phone.toLowerCase())){
                log.error("密码不允许包含手机号");
                return false;
            }
        }
        if(StringUtils.isNotBlank(email)) {
            if (newPassword.toLowerCase().contains(email.toLowerCase())){
                log.error("密码不允许包含邮箱");
                return false;
            }
        }
        //判断是否包含特殊字符
        Pattern p=Pattern.compile(SPEC_CHAR);
        Matcher m=p.matcher(newPassword);
        boolean special = m.find();
        String tempPassword1 = m.replaceAll("a");
        //检查密码是否达到指定长度
        if(!Pattern.matches(PASSWORD_REGEX_LENGTH, tempPassword1)) {
            return false;
        }
        String tempPassword2 = m.replaceAll("");
        if(special) {
            //包含特殊字符，则剩下的字符串必须要包含数字、大写字母、小写字母中的2个
            if(!Pattern.matches(PASSWORD_REGEX_1, tempPassword2)) {
                return false;
            }
        }else {
            //包含特殊字符，则剩下的字符串必须要包含数字、大写字母、小写字母
            if(!Pattern.matches(VerificationUtil.PASSWORD_REGEX_2, tempPassword2)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Create failure response response entity.
     *
     * @param domain the domain
     * @return the response verification
     */
    public static boolean verificationDomain(String domain) {
        return validate(domain, DOMAIN_REGEX);
    }
    /**
     * 验证域名可以为单级域名
     *
     * @param domain
     * @return
     */
    public static boolean verifacationSingleDomain(String domain){
        return validate(domain, DOMAIN_REGEX)||Pattern.matches(SIGLE_DOMAIN_REGEX, domain.trim());
    }

    /**
     * 验证泛域名正确性
     *
     * @param domain
     * @return
     */
    public static boolean verificationPanDomain(String domain) {
        String tmpStr = domain.substring(2);
        if("*.".equals(domain.substring(0, 2))) {
            if(domain.split("\\.").length<=2) {
                return Boolean.FALSE;
            }
            tmpStr = "www." + tmpStr;
        } else {
            tmpStr = domain;
        }
        return validate(tmpStr, DOMAIN_REGEX);
    }

    /**
     * Create failure response response entity.
     *
     * @param mobile the mobile
     * @return the response verification
     */
    public static boolean verificationMobile(String mobile) {
        return validate(mobile, MOBILE_REGEX);
    }



    /**
     *
     * @param name
     * @return 判断用户名
     */
    public static boolean verificationName(String name) {
        return validate(name, USER_NAME);
    }
    /**
     * Create failure response response entity.
     *
     * @param email the email
     * @return the response verification
     */
    public static boolean verificationEmail(String email) {
        return validate(email, EMAIL_REGEX);
    }

    /**
     *
     * @param uri
     * @return
     */
    public static boolean verificationURI(String uri) {
        return validate(uri, URI_REGEX);
    }
    /**
     *
     * @param password
     * @return
     */
    public static boolean verificationPassword(String password) {
        return validate(password, PASSWORD_REGEX_LENGTH);
    }
    /**
     *
     * @param card
     * @return
     */
    public static boolean verificationIdCard(String card) {
        return validate(card, ID_CARD);
    }
    /**
     * IPV4掩码校验
     * @param mask
     * @return
     */
    public static Boolean maskIpv4Validate(String mask) {
        return validate(mask, MASK_REGEX);
    }


    /**
     * 域名加端口号校验
     * @param domain
     * @return
     */
    public static Boolean domainWithPortValidate(String domain) {
        String[] parts = domain.split(":");
        return  parts.length == 2 && portValidate(parts[1]) && verificationDomain(parts[0]);
    }

    /**
     *  检验端口号
     * @param port
     * @return
     */
    public static boolean portValidate(int port){
        return port >= PORT_MIN && port <= PORT_MAX;
    }

    /**
     *
     * @param address
     * @param regex
     * @return
     */
    public static boolean validate(String address,String regex) {
        return address == null ? Boolean.FALSE : Pattern.matches(regex, address.trim());
    }

    private static Boolean portValidate(String value) {
        //检查端口
        Integer port = null;
        try {
            port = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return false;
        }
        return port >= PORT_MIN && port <= PORT_MAX;
    }

    public static void main(String[] args) {
        String ss = "410481199611313511";
        System.out.println(Pattern.matches(ID_CARD, ss.trim()));
        long Long = System.currentTimeMillis();
        long l = Long / 1000;
        System.out.println(Long);
        System.out.println(l);
    }
}
