package com.grazielleanaia.bff_schedulingtask_api.business;

import com.grazielleanaia.bff_schedulingtask_api.business.dto.in.TaskDTORequest;
import com.grazielleanaia.bff_schedulingtask_api.business.dto.out.TaskDTOResponse;
import com.grazielleanaia.bff_schedulingtask_api.infrastructure.client.TaskClient;
import com.grazielleanaia.bff_schedulingtask_api.infrastructure.enums.NotificationStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class TaskService {
    private final TaskClient taskClient;


    public TaskDTOResponse createTask(TaskDTORequest taskDTO, String token) {
        return taskClient.createTask(taskDTO, token);
    }

    public void deleteTaskById(String id, String token) {
        taskClient.deleteTaskById(id, token);
    }

    public List<TaskDTOResponse> findTaskByEmail(String token) {
        return taskClient.findTaskList(token);
    }

    public List<TaskDTOResponse> findTaskByPeriod(LocalDateTime initialDate, LocalDateTime finalDate, String token) {
        return taskClient.findTaskByPeriod(initialDate, finalDate, token);
    }

    public TaskDTOResponse updateTask(TaskDTORequest taskDTO, String id, String token) {
        return taskClient.updateTasks(id, taskDTO, token);
    }

    public TaskDTOResponse changeNotificationStatus(String id, NotificationStatusEnum status, String token) {
        return taskClient.changeNotificationStatus(id, status, token);
    }


}
