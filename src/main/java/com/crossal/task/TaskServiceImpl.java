package com.crossal.task;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    private static final Logger logger = Logger.getLogger(TaskServiceImpl.class);

    private TaskRepository taskRepository;
    private RunTaskService runTaskService;

    @Autowired
    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Autowired
    public void setRunTaskService(RunTaskService runTaskService) {
        this.runTaskService = runTaskService;
    }

    @Override
    public Iterable<Task> listAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Integer id) {
        return taskRepository.findById(id).get();
    }

    @Override
    public Task saveTask(Task task) {
        task.setStatus(TaskStatus.RUNNING);
        Task savedTask = taskRepository.save(task);

//        RunTaskRunnable runTaskRunnable = new RunTaskRunnable(savedTask);
//        runT

        logger.debug("saveTask before running task");
        runTaskService.runTask(savedTask);
        logger.debug("saveTask after running task");

        return savedTask;
    }

    @Override
    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }

    //    public class MyThread extends Thread {
//
//        public void run(){
//            System.out.println("MyThread running");
//        }
//    }
}
