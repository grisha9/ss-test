package com.example.ss.sstest;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Created by grisha on 06.05.20.
 */
@RestController
@RequestMapping("/")
@Validated
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping(value = "/test/{name}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String get(@PathVariable("name")
                      @NotBlank
                      @Size(min = 4) String name) {
        return messageService.getMessage(name);
    }
}
