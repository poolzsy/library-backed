package com.lilac.entity;

import lombok.Data;

@Data
public class Result {
    private String code;
    private String message;
    private Object data;

    public Result(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result success(Object data) {
        return new Result("200", "success", data);
    }

    public static Result success() {
        return new Result("200", "success", null);
    }

    public static Result error(String message) {
        return new Result("500", message, null);
    }

    public static Result error(String code, String message) {
        return new Result(code, message, null);
    }
}