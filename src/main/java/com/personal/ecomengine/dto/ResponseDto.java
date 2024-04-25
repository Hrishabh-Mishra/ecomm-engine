package com.personal.ecomengine.dto;

public class ResponseDto<T> {
    T t;
    public ResponseDto(T t){
        this.t = t;
    }
}
