package org.admin.controller;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RestResult<T> {
    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";

    private String status;
    private T data;
    private String error;

    @Builder.Default //빌더로 인스턴스 생성시 초기화할 값 지정
    private LocalDateTime timestamp = LocalDateTime.now();

}
