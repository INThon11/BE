package com.example.demo.common.dto;

import com.example.demo.common.constants.ErrorCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class HttpDataResponse<T> extends HttpResponse {
    private final T data;

    protected HttpDataResponse(T data) {
        super(true, ErrorCode.OK.getCode(), ErrorCode.OK.getMessage());
        this.data = data;
    }

    public static <T> HttpDataResponse<T> of(T data) {
        return new HttpDataResponse<>(data);
    }

    public static <T> HttpDataResponse<T> empty() {
        return new HttpDataResponse<>(null);
    }

}
