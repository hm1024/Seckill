package com.minghai.seckill.result;

import lombok.Data;

/**
 * @author minghai
 * @description
 * @date 2019/12/3
 */
@Data
public class ResponseVO<T> {
    private int code;
    private String msg;
    private T data;

    private ResponseVO(T data){
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    private ResponseVO(CodeMsg codeMsg) {
        if(codeMsg == null){
            return;
        }
        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMsg();
    }

    /**
     * @Author minghai
     * @Description 成功的时候调用
     * @Date 2019/12/3 19:01
     * @Param []
     * @return com.minghai.seckill.result.ResponseVO
     */
    public static <T> ResponseVO success(T data){
        return new ResponseVO<T>(data);
    }

    /**
     * @Author minghai
     * @Description 失败的时候调用
     * @Date 2019/12/3 19:02
     * @Param []
     * @return com.minghai.seckill.result.ResponseVO<T>
     */
    public static <T> ResponseVO<T> error(CodeMsg codeMsg){
        return new ResponseVO<T>(codeMsg);
    }
}
