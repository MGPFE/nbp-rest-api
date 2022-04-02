package com.mg.demo.exceptions;

public class ExchangeRatesException extends RuntimeException {
    public ExchangeRatesException(String msg) {
        super(msg);
    }
}
