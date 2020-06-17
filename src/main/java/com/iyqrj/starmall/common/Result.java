package com.iyqrj.starmall.common;

import lombok.Data;

@Data
public class Result<T> {
    private Integer code = 200;
    private Boolean success = true;
    private String message = null;
    private T result = null;
    private long timestamp = System.currentTimeMillis();

    public static Result ok(){
        Result result = new Result();
        return result;
    }

    public static Result ok(String message){
        Result result = new Result();
        result.setMessage(message);
        return result;
    }

    public static Result ok(Object data){
        Result result = new Result();
        result.setResult(data);
        return result;
    }

    public static Result ok(String message, Object data){
        Result result = new Result();
        result.setMessage(message);
        result.setResult(data);
        return result;
    }

    public static Result error(){
        Result result = new Result();
        result.setCode(0);
        result.setSuccess(false);
        return result;
    }

    public static Result error(Integer code){
        Result result = new Result();
        result.setCode(code);
        result.setSuccess(false);
        return result;
    }

    public static Result error(String message){
        Result result = new Result();
        result.setCode(0);
        result.setSuccess(false);
        result.setMessage(message);
        return result;
    }

    public static Result error(Integer code, String message){
        Result result = new Result();
        result.setCode(code);
        result.setSuccess(false);
        result.setMessage(message);
        return result;
    }
}
