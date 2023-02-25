package br.com.cardoso.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class LocalDateTimeController {

    @GetMapping("/datetime")
    public String getDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
}
