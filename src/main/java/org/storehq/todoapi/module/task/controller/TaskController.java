package org.storehq.todoapi.module.task.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.storehq.todoapi.exception.ResourceNotFoundException;
import org.storehq.todoapi.module.task.mapper.TaskRequestDTO;
import org.storehq.todoapi.module.task.entity.Task;
import org.storehq.todoapi.module.task.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        if (tasks == null || tasks.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron tareas registradas");
        }
        return tasks;
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable int id) {
        Task task = taskService.getTaskById(id);
        if (task == null) {
            throw new ResourceNotFoundException("Tarea con ID " + id + " no encontrada");
        }
        return task;
    }

    @PostMapping
    public Task addTask(@Valid @RequestBody TaskRequestDTO taskRequest) {
        Task createdTask = taskService.addTask(taskRequest);
        if (createdTask == null) {
            throw new RuntimeException("Error al crear la tarea");
        }
        return createdTask;
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable int id, @Valid @RequestBody TaskRequestDTO taskRequest) {
        Task updated = taskService.updateTask(id, taskRequest);
        if (updated == null) {
            throw new ResourceNotFoundException("No se puede actualizar. Tarea con ID " + id + " no encontrada");
        }
        return updated;
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable int id) {
        boolean deleted = taskService.deleteTask(id);
        if (!deleted) {
            throw new ResourceNotFoundException("No se puede eliminar. Tarea con ID " + id + " no encontrada");
        }
    }
}
