package com.allianz.coreader.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseDTO<T> {

    private T data;
    private String status;
    private String code;
    private String message;

    public ResponseDTO(String status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public ResponseDTO setData(T data) {
        this.data = data;
        return this;
    }

    public T getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
