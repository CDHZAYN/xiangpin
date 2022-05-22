package com.tencent.wxcloudrun.config;

import lombok.Data;

import java.util.HashMap;

@Data
public final class ApiResponse {

  private Integer code;
  private String errorCode;
  private Object data;

  private ApiResponse(int code, String errorCode, Object data) {
    this.code = code;
    this.errorCode = errorCode;
    this.data = data;
  }
  
  public static ApiResponse ok() {
    return new ApiResponse(0, "", new HashMap<>());
  }

  public static ApiResponse ok(Object data) {
    return new ApiResponse(0, "", data);
  }

  public static ApiResponse error(String errorCode, String errorMsg) {
    return new ApiResponse(1, errorCode, errorMsg);
  }
}
