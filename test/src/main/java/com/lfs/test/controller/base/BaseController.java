package com.lfs.test.controller.base;

import com.lfs.common.vo.LogVo;
import com.lfs.test.constants.BzExceptConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.bind.ValidationException;
import java.util.List;

/**
 * BaseController
 *
 * @author fushenliao
 * @version 1.0.0
 * @create 2022/7/4
 * @modify 2022/7/4
 */
@Slf4j
public class BaseController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public BaseResp handBindException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        log.info("handle param error fieldErrors={} e={}", LogVo.newNoLimit(fieldErrors), e.getMessage(), e);
        return BaseResp.fail(BzExceptConst.CODE_PARAM_ERROR, BzExceptConst.DESC_PARAM_ERROR);
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public BaseResp handConstraintViolationException(ValidationException e) {
        log.info("handle param error e={}", e.getMessage(), e);
        return BaseResp.fail(BzExceptConst.CODE_PARAM_ERROR, BzExceptConst.DESC_PARAM_ERROR);
    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public BaseResp handThrowable(Throwable e) {
        log.error("handle sys throwable e={}", e.getMessage(), e);
        return BaseResp.fail(BzExceptConst.CODE_FAIL_ERROR, BzExceptConst.DESC_FAIL_ERROR);
    }
}