package org.storehq.todoapi.module.task.service;

import org.springframework.stereotype.Service;
import org.storehq.todoapi.module.task.mapper.TaskRequestDTO;
import org.storehq.todoapi.module.task.entity.Task;
import org.storehq.todoapi.module.task.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final List<Task> tasks = new ArrayList<>();
    private int currentId = 3;
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        /*
           tasks.add(new Task(1,"Aprender Spring Boot","Completar el tutorial oficial",false));
           tasks.add(new Task(2,"Aprender JWT","Hacer una gestion de usuarios",true));
         */
        this.repository = repository;
    }
    public List<Task> getAllTasks() {
        return repository.findAll();
    }
    public Task getTaskById(int id) {
        /*return tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElse(null);*/
        return repository.findById(id).orElse(null);
    }
    public Task addTask(TaskRequestDTO dto) {
        /*Task newTask = new Task(currentId++, dto.getTitle(), dto.getDescription(), dto.isDone());
        tasks.add(newTask);
        return newTask;*/
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setDone(dto.isDone());
        return repository.save(task);
    }
    public Task updateTask(int id,TaskRequestDTO dto) {
        /*Task existingtask = getTaskById(id);
        if (existingtask == null) {
            return null;
        }
        existingtask.setTitle(dto.getTitle());
        existingtask.setDescription(dto.getDescription());
        existingtask.setDone(dto.isDone());

        return existingtask;*/
        Optional<Task> optional = repository.findById(id);
        if (optional.isPresent()) {
            Task task = optional.get();
            task.setTitle(dto.getTitle());
            task.setDescription(dto.getDescription());
            task.setDone(dto.isDone());
            return repository.save(task);
        }
        return null;
    }
    public boolean deleteTask(int id) {
        /*Task existingtask = getTaskById(id);
        if (existingtask != null) {
            tasks.remove(existingtask);
            return true;
        }
        return false;*/
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

}
