package com.fl.fastLease.common.entity;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * &#064;Description
 * &#064;Author Evan
 * &#064;Date 2025/2/18 21:22
 */
@Data
public class ResultEntity<T> implements Serializable {

    private int code;

    private String msg;

    private T data;

    public ResultEntity(int code , String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public boolean isSuccess(){
        return this.code == HttpStatus.OK.value();
    }

    public static <T>ResultEntity<T> success(){
        return ResultEntity.success("操作成功", null);
    }

    public static <T>ResultEntity<T> success(T data){
        return ResultEntity.success("操作成功", data);
    }
    public static <T>ResultEntity<T> success(String msg, T data){
        return ResultEntity.success(HttpStatus.OK.value(), msg, data);
    }
    public static <T>ResultEntity<T> success(int code, String msg, T data){
        return new ResultEntity<>(code, msg, data);
    }

    public static <T> ResultEntity<T> error() {
        return ResultEntity.error("操作失败", null);
    }

    public static <T>ResultEntity<T> error(T data){
        return ResultEntity.error("操作失败", data);
    }

    public static <T>ResultEntity<T> error(int code, String msg){
        return ResultEntity.error(code, msg, null);
    }

    public static <T>ResultEntity<T> error(String msg){
        return ResultEntity.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, null);
    }
    public static <T>ResultEntity<T> error(String msg, T data){
        return ResultEntity.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, data);
    }
    public static <T>ResultEntity<T> error(int code, String msg, T data){
        return new ResultEntity<>(code, msg, data);
    }

}
