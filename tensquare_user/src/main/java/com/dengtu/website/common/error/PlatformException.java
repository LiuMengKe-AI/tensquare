package com.dengtu.website.common.error;


import com.dengtu.website.common.ResultCodeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @Type PlatformException.java
 * @Desc 平台基础异常基类
 */
@Getter
@Setter
public class PlatformException extends RuntimeException {
    private Integer code;

    public PlatformException(ResultCodeEnum exceptionType) {
        super(exceptionType.getMessage());
        this.code = exceptionType.getCode();
    }

    public PlatformException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }


}
