package com.minghai.seckill.exection;

import com.minghai.seckill.result.CodeMsg;
import com.minghai.seckill.result.ResponseVO;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author minghai
 * @description
 * @date 2020/3/21@ControllerAdvice
 */

@ResponseBody
public class GlobalExecptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseVO<String> execptionHandler(HttpServletRequest request, Exception e){
        if(e instanceof GlobalException){
            GlobalException ex = (GlobalException)e;
            return ResponseVO.error(ex.getCm());
        }

        if(e instanceof BindException){
            BindException ex = (BindException)e;
            List<ObjectError> errors = ex.getAllErrors();
            ObjectError error = errors.get(0);
            String msg = error.getDefaultMessage();

            return ResponseVO.error(CodeMsg.Bind_ERROR.fillArgs(msg));
        }else{
            return ResponseVO.error(CodeMsg.SERVER_ERROR);
        }
    }
}
