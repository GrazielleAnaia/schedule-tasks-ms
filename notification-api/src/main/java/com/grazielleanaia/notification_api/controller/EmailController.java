package com.grazielleanaia.notification_api.controller;


import com.grazielleanaia.notification_api.business.EmailService;
import com.grazielleanaia.notification_api.business.dto.TaskDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor

public class EmailController {

    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<Void> sendEmail(@RequestBody TaskDTO taskDTO) {
        emailService.sendEmail(taskDTO);
        return ResponseEntity.ok().build();
    }
}
