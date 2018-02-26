package com.shichuang.goodov.entify;

public class AMBasePlusDto<T> extends AMBaseDto {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
