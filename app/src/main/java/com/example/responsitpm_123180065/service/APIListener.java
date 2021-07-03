package com.example.responsitpm_123180065.service;

public interface APIListener<T> {
    void onSuccess(T body);
    void onFailed(String message);
}
