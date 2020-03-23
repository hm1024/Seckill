package com.minghai.seckill.exection;

import com.minghai.seckill.result.CodeMsg;
import com.sun.org.apache.bcel.internal.classfile.Code;
import lombok.Data;

/**
 * @author minghai
 * @description
 * @date 2020/3/21
 */
public class GlobalException extends  RuntimeException{

    private static final long serialVersionUID = 1L;

    private CodeMsg cm;

    public GlobalException(CodeMsg cm){
        super(cm.toString());
        this.cm = cm;
    }

    public CodeMsg getCm() {
        return cm;
    }
}
