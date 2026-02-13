package com.grazielleanaia.bff_schedulingtask_api.business;


import com.grazielleanaia.bff_schedulingtask_api.business.dto.in.LoginDTORequest;
import com.grazielleanaia.bff_schedulingtask_api.business.dto.out.TaskDTOResponse;
import com.grazielleanaia.bff_schedulingtask_api.infrastructure.enums.NotificationStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ChronService {

    private final TaskService taskService;
    private final EmailService emailService;
    private final CustomerService customerService;

    @Value("${customer.email}")
    private String email;

    @Value("${customer.password}")
    private String password;

    @Scheduled(cron = "${chron.time}")

    public void taskNextHour() {
        String token = login(convertToRequestDTO());
        LocalDateTime futureHour = LocalDateTime.now().plusHours(1);
        LocalDateTime futureHourPlusFive = LocalDateTime.now().plusHours(1).plusMinutes(5);

        List<TaskDTOResponse> taskResponseList = taskService.findTaskByPeriod(futureHour, futureHourPlusFive, token);
        taskResponseList.forEach(task -> {
            emailService.sendEmail(task);
            taskService.changeNotificationStatus(task.getId(), NotificationStatusEnum.NOTIFIED, token);
        });
    }

    public String login(LoginDTORequest loginDTORequest) {
        return customerService.loginCustomer(loginDTORequest);
    }

    public LoginDTORequest convertToRequestDTO() {
        return LoginDTORequest.builder()
                .email(email)
                .password(password)
                .build();
    }
}
