package com.grazielleanaia.bff_schedulingtask_api.infrastructure.client;


import com.grazielleanaia.bff_schedulingtask_api.business.dto.in.TaskDTORequest;
import com.grazielleanaia.bff_schedulingtask_api.business.dto.out.TaskDTOResponse;
import com.grazielleanaia.bff_schedulingtask_api.infrastructure.enums.NotificationStatusEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "task", url = "${task.url}")

public interface TaskClient {

    @PostMapping
    TaskDTOResponse createTask(@RequestBody TaskDTORequest taskDTO,
                               @RequestHeader("Authorization") String token);

    @GetMapping
    List<TaskDTOResponse> findTaskList(@RequestHeader("Authorization") String token);

    @GetMapping("/events")
    List<TaskDTOResponse> findTaskByPeriod(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                           LocalDateTime initialDate,
                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                           LocalDateTime finalDate,
                                           @RequestHeader("Authorization") String token);

    @DeleteMapping
    void deleteTaskById(@RequestParam("id") String id,
                        @RequestHeader("Authorization") String token);

    @PutMapping
    TaskDTOResponse updateTasks(@RequestParam("id") String id,
                                @RequestBody TaskDTORequest taskDTO,
                                @RequestHeader("Authorization") String token);

    @PatchMapping
    TaskDTOResponse changeNotificationStatus(@RequestParam("id") String id,
                                             @RequestParam("status") NotificationStatusEnum status,
                                             @RequestHeader("Authorization") String token);


}
