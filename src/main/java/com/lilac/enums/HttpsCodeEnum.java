package com.lilac.enums;

import lombok.Getter;

@Getter
public enum HttpsCodeEnum {
    SUCCESS(200, "操作成功"),
    SYSTEM_ERROR(500, "系统内部错误，请联系管理员"),

    BAD_REQUEST(400, "无效的请求参数"),
    USER_NAME_EXIST(40001, "用户名已存在"),
    USER_PHONE_EXIST(40002, "手机号已存在"),
    RESOURCE_NOT_FOUND(404, "请求的资源不存在");

    private final Integer code;
    private final String message;

    HttpsCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
