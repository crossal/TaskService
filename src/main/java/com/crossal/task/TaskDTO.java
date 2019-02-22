package com.crossal.task;

import com.crossal.argument.ArgumentDTO;
import com.crossal.environment.EnvironmentDTO;

import java.util.List;
import java.util.stream.Collectors;

public class TaskDTO {

    private Integer id;
    private String command;
    private List<ArgumentDTO> arguments;
    private String path;
    private String workingDirectory;
    private TaskStatus status;
    private String output;
    private String error;

    public TaskDTO() {}

    public TaskDTO(Task task) {
        this.id = task.getId();
        this.command = task.getCommand();
        this.arguments = task.getArguments().stream().map(ArgumentDTO::new).collect(Collectors.toList());
        this.path = task.getEnvironment().getPath();
        this.workingDirectory = task.getWorkingDirectory();
        this.status = task.getStatus();
        this.output = task.getOutput();
        this.error = task.getError();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public List<ArgumentDTO> getArguments() {
        return arguments;
    }

    public void setArguments(List<ArgumentDTO> arguments) {
        this.arguments = arguments;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getWorkingDirectory() {
        return workingDirectory;
    }

    public void setWorkingDirectory(String workingDirectory) {
        this.workingDirectory = workingDirectory;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
