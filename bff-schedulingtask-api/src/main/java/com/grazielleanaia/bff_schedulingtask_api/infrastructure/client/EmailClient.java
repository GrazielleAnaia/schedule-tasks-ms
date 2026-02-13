package com.grazielleanaia.bff_schedulingtask_api.infrastructure.client;


import com.grazielleanaia.bff_schedulingtask_api.business.dto.out.TaskDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-api", url = "${email.url}")

public interface EmailClient {

    @PostMapping
    void sendEmail(@RequestBody TaskDTOResponse taskDTOResponse);
}
