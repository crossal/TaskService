package com.crossal.task;

import com.crossal.argument.Argument;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class RunTaskServiceImpl implements RunTaskService {

    private static final Logger logger = Logger.getLogger(RunTaskServiceImpl.class);

    private TaskRepository taskRepository;

    @Autowired
    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Async("processExecutor")
    @Transactional
    @Override
    public void runTask(Task task) {
        logger.debug("runTask task: " + task);
        Runtime rt = Runtime.getRuntime();
        Process pr = null;
        try {
            task.getArguments().sort(Comparator.comparing(Argument::getSort));
            String taskString = task.getCommand() + task.getArguments().stream().map(Argument::getArg).collect(Collectors.joining(" " ));
            logger.debug("runTask task command string: " + taskString);
            pr = rt.exec(taskString);

            int result = pr.waitFor();
            if (result == 0) {
                task.setStatus(TaskStatus.SUCCESS);
            } else {
                task.setStatus(TaskStatus.FAIL);
            }

            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();

            try {
                while ((line = input.readLine()) != null) {
                    sb.append(System.lineSeparator() + line);
                }
                task.setOutput(sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            input = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
            sb = new StringBuilder();

            try {
                while ((line = input.readLine()) != null) {
                    sb.append(System.lineSeparator() + line);
                }
                task.setError(sb.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

            taskRepository.save(task);
        } catch (Exception e) {
            logger.debug("runTask e: " + e);
            if (pr != null) {
                BufferedReader input = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
                String line = null;
                StringBuilder sb = new StringBuilder();

                try {
                    while ((line = input.readLine()) != null) {
                        sb.append(System.lineSeparator() + line);
                    }
                    task.setError(sb.toString());
                } catch (Exception e2) {
                    e.printStackTrace();
                }
            } else {
                task.setOutput(e.getMessage());
            }

            task.setStatus(TaskStatus.FAIL);
            taskRepository.save(task);

        }
    }
}
