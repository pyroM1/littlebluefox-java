package io.littlebluefox;

public class UnexpectedResponseCode extends Exception {
    private Integer expectedCode;
    private Integer code;

    public UnexpectedResponseCode(Integer expectedCode, Integer code) {
        this.expectedCode = expectedCode;
        this.code = code;
    }

    public Integer getExpectedCode() {
        return this.expectedCode;
    }
    
    public Integer getCode() {
        return this.code;
    }
}
