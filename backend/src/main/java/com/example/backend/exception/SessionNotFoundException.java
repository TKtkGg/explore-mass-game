package com.example.backend.exception;

public class SessionNotFoundException extends RuntimeException {
    public SessionNotFoundException() {
        super("セッションIDが存在しません。");
    }
}
