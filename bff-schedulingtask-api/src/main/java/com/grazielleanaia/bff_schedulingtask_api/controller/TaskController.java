package com.grazielleanaia.bff_schedulingtask_api.controller;


import com.grazielleanaia.bff_schedulingtask_api.business.TaskService;
import com.grazielleanaia.bff_schedulingtask_api.business.dto.in.TaskDTORequest;
import com.grazielleanaia.bff_schedulingtask_api.business.dto.out.TaskDTOResponse;
import com.grazielleanaia.bff_schedulingtask_api.infrastructure.enums.NotificationStatusEnum;
import com.grazielleanaia.bff_schedulingtask_api.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
@Tag(name = "Task", description = "Customer Task Registration")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)

public class TaskController {

    private final TaskService taskService;

    @PostMapping
    @Operation(summary = "Create customer task", description = "create a new task")
    @ApiResponse(responseCode = "200", description = "Task successfully created")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<TaskDTOResponse> createTask(@RequestBody TaskDTORequest taskDTO,
                                                      @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(taskService.createTask(taskDTO, token));
    }

    @GetMapping
    @Operation(summary = "Get customer task list by email", description = "get customer list by email")
    @ApiResponse(responseCode = "200", description = "customer task list successfully found")
    @ApiResponse(responseCode = "500", description = "Server error")
    @ApiResponse(responseCode = "403", description = "Customer email not found")
    @ApiResponse(responseCode = "401", description = "Customer not authorized")
    public ResponseEntity<List<TaskDTOResponse>> findTaskList(@RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(taskService.findTaskByEmail(token));
    }

    @GetMapping("/events")
    @Operation(summary = "Get customer task by period", description = "get customer task by period")
    @ApiResponse(responseCode = "200", description = "get customer task list by period successfully")
    @ApiResponse(responseCode = "500", description = "Server error")
    @ApiResponse(responseCode = "403", description = "Customer email not found")
    @ApiResponse(responseCode = "401", description = "Customer not authorized")
    public ResponseEntity<List<TaskDTOResponse>> findTaskByPeriod(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                  LocalDateTime initialDate,
                                                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                  LocalDateTime finalDate,
                                                                  @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(taskService.findTaskByPeriod(initialDate, finalDate, token));
    }

    @DeleteMapping
    @Operation(summary = "Delete customer task", description = "task successfully deleted")
    @ApiResponse(responseCode = "200", description = "task successfully deleted")
    @ApiResponse(responseCode = "500", description = "Server error")
    @ApiResponse(responseCode = "403", description = "task id not found")
    @ApiResponse(responseCode = "401", description = "Customer not authorized")
    public ResponseEntity<Void> deleteTaskById(@RequestParam("id") String id, @RequestHeader(value = "Authorization", required = false) String token) {
        taskService.deleteTaskById(id, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Update task", description = "task updated")
    @ApiResponse(responseCode = "200", description = "task successfully updated")
    @ApiResponse(responseCode = "500", description = "Server error")
    @ApiResponse(responseCode = "403", description = "task id not found")
    @ApiResponse(responseCode = "401", description = "Customer not authorized")
    public ResponseEntity<TaskDTOResponse> updateTask(@RequestBody TaskDTORequest taskDTO,
                                                      @RequestHeader(value = "Authorization", required = false) String token,
                                                      @RequestParam("id") String id) {
        return ResponseEntity.ok(taskService.updateTask(taskDTO, id, token));
    }

    @PatchMapping
    @Operation(summary = "Change task status", description = "task status changed")
    @ApiResponse(responseCode = "200", description = "task status successfully changed")
    @ApiResponse(responseCode = "500", description = "Server error")
    @ApiResponse(responseCode = "403", description = "task id not found")
    @ApiResponse(responseCode = "401", description = "Customer not authorized")
    public ResponseEntity<TaskDTOResponse> changeTaskNotification(@RequestParam("id") String id,
                                                                  @RequestParam("status") NotificationStatusEnum status,
                                                                  @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(taskService.changeNotificationStatus(id, status, token));
    }
}
