package com.dengtu.website.common.error;


import com.dengtu.website.common.CommonResult;
import com.dengtu.website.common.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Type ExceptionHandle.java
 * @Desc 统一处理异常
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandle {

    /**
     * 捕获异常处理方法
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResult exceptionCatch(Exception exception) {
        if (exception instanceof PlatformException) {
            PlatformException platformException = (PlatformException) exception;
            return CommonResult.failed(platformException.getCode(), platformException.getMessage());
        }
        log.error("异常信息", exception);
        return CommonResult.failed(ResultCodeEnum.FAILED.getCode(), ResultCodeEnum.FAILED.getMessage());
    }
}
