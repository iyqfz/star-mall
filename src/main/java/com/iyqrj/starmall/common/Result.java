package com.iyqrj.starmall.common;

import lombok.Data;

@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T result;

    public static Result ok(){
        Result result = new Result();
        result.setCode(1200);
        result.setMessage("222");
        result.setResult(null);
        return result;
    }
}
