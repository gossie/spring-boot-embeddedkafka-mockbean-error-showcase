package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class OtherService {

    private final ThirdService thirdService;

    private String text;

    public OtherService(ThirdService thirdService) {
        this.thirdService = thirdService;
    }

    public void enhance(String data) {
        this.text = data + " " + thirdService.get();
    }

    public String getText() {
        return text;
    }
}
