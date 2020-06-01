package com.example.ss.sstest;

import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private static final String message = "hello";

    public String getMessage(String name) {
        return message + " " + name;
    }
}
