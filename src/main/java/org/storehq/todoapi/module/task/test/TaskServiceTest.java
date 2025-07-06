package org.storehq.todoapi.module.task.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.storehq.todoapi.module.task.entity.Task;
import org.storehq.todoapi.module.task.mapper.TaskRequestDTO;
import org.storehq.todoapi.module.task.repository.TaskRepository;
import org.storehq.todoapi.module.task.service.TaskService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {
    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void getAllTasks_returnsListOfTasks() {
        List<Task> mockTasks = Arrays.asList(
                new Task(1, "Tarea 1", "Desc 1", false),
                new Task(2, "Tarea 2", "Desc 2", true)
        );

        when(taskRepository.findAll()).thenReturn(mockTasks);

        List<Task> result = taskService.getAllTasks();

        assertEquals(2, result.size());
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void getTaskById_whenExists_returnsTask() {
        Task task = new Task(1, "Mock", "Task", false);
        when(taskRepository.findById(1)).thenReturn(Optional.of(task));

        Task result = taskService.getTaskById(1);

        assertNotNull(result);
        assertEquals("Mock", result.getTitle());
    }

    @Test
    void getTaskById_whenNotFound_returnsNull() {
        when(taskRepository.findById(99)).thenReturn(Optional.empty());

        Task result = taskService.getTaskById(99);

        assertNull(result);
    }

    @Test
    void createTask_shouldReturnCreatedTask() {
        TaskRequestDTO dto = new TaskRequestDTO("Nueva tarea", "Desc", false);
        Task savedTask = new Task(1, dto.getTitle(), dto.getDescription(), dto.isDone());

        when(taskRepository.save(any(Task.class))).thenReturn(savedTask);

        Task result = taskService.addTask(dto);

        assertNotNull(result);
        assertEquals("Nueva tarea", result.getTitle());
    }

    @Test
    void updateTask_whenExists_shouldUpdate() {
        Task existing = new Task(1, "Old", "Old Desc", false);
        TaskRequestDTO dto = new TaskRequestDTO("Updated", "Updated Desc", true);

        when(taskRepository.findById(1)).thenReturn(Optional.of(existing));
        when(taskRepository.save(any(Task.class))).thenReturn(existing);

        Task updated = taskService.updateTask(1, dto);

        assertNotNull(updated);
        assertEquals("Updated", updated.getTitle());
        assertTrue(updated.isDone());
    }

    @Test
    void updateTask_whenNotFound_shouldReturnNull() {
        TaskRequestDTO dto = new TaskRequestDTO("X", "Y", false);

        when(taskRepository.findById(99)).thenReturn(Optional.empty());

        Task updated = taskService.updateTask(99, dto);

        assertNull(updated);
    }

    @Test
    void deleteTask_whenExists_shouldReturnTrue() {
        when(taskRepository.existsById(1)).thenReturn(true);

        boolean result = taskService.deleteTask(1);

        assertTrue(result);
        verify(taskRepository).deleteById(1);
    }

    @Test
    void deleteTask_whenNotFound_shouldReturnFalse() {
        when(taskRepository.existsById(99)).thenReturn(false);

        boolean result = taskService.deleteTask(99);

        assertFalse(result);
        verify(taskRepository, never()).deleteById(any());
    }
}
