package org.storehq.todoapi.module.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.storehq.todoapi.module.task.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
