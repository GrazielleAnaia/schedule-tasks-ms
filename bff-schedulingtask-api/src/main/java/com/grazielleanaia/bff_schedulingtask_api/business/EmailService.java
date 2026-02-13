package com.grazielleanaia.bff_schedulingtask_api.business;


import com.grazielleanaia.bff_schedulingtask_api.business.dto.out.TaskDTOResponse;
import com.grazielleanaia.bff_schedulingtask_api.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class EmailService {

    private final EmailClient emailClient;


    public void sendEmail(TaskDTOResponse taskDTOResponse) {

        emailClient.sendEmail(taskDTOResponse);
    }

}
