package com.crossal.task;

import com.crossal.argument.Argument;
import com.crossal.environment.Environment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;
    private String command;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Argument> arguments = new ArrayList<>();
    @OneToOne(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Environment environment;
    private String workingDirectory;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    private String output;
    private String error;

    public Task() {}

    public Task(TaskDTO taskDTO) {
        this.id = taskDTO.getId();
        this.command = taskDTO.getCommand();
        if (taskDTO.getArguments() != null) {
            this.arguments = taskDTO.getArguments().stream().map(Argument::new).collect(Collectors.toList());
        }
        this.environment = new Environment();
        this.environment.setPath(taskDTO.getPath());
        this.environment.setTask(this);
        this.workingDirectory = taskDTO.getWorkingDirectory();
        this.status = taskDTO.getStatus();
        this.output = taskDTO.getOutput();
        this.error = taskDTO.getError();
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

    public List<Argument> getArguments() {
        return arguments;
    }

    public void setArguments(List<Argument> arguments) {
        this.arguments = arguments;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
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
