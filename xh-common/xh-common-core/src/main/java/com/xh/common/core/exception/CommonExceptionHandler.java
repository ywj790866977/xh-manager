package com.xh.common.core.exception;

import com.xh.common.core.response.ResponseHelper;
import com.xh.common.core.response.ResponseVO;
import java.net.BindException;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@ControllerAdvice
@Slf4j
public class CommonExceptionHandler {

    private static final String GLOBEL_ERROR_MSG = "服务端发生异常！";

    @ExceptionHandler(value = ServiceException.class)
    public ResponseVO<Object> serviceErrorHandler(HttpServletResponse resp, ServiceException ex) throws Exception {
    	log.error("ServiceException: ", ex);
        return ex.toResponseVO(ex);
    }

    @ExceptionHandler(value = SecurityException.class)
    public ResponseVO<String> securityErrorHandler(HttpServletResponse resp, SecurityException ex) throws Exception {
        log.warn("SecurityException: ", ex);
        return ResponseHelper.unauthorized(ex.getMessage());
    }

    @ExceptionHandler(value = ValidationException.class)
    public ResponseVO<String> validationErrorHandler(ValidationException ex) throws Exception {
    	log.error("ValidationException: ", ex);
    	return ResponseHelper.badRequest(ex.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseVO<String> defaultErrorHandler(HttpServletResponse resp, Exception ex) throws Exception {
    	String uuid = UUID.randomUUID().toString();
        log.error("[{}]Internal Server Error: ",uuid, ex);
        return ResponseHelper.error(GLOBEL_ERROR_MSG,uuid);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ResponseVO<?> bodyValidExceptionHandler(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        log.warn(fieldErrors.get(0).getDefaultMessage());
        return ResponseHelper.error(fieldErrors.get(0).getDefaultMessage());
    }

}