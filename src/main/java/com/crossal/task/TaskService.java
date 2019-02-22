package com.crossal.task;

public interface TaskService {

    Iterable<Task> listAllTasks();

    Task getTaskById(Integer id);

    Task saveTask(Task task);

    void deleteTask(Integer id);
}
