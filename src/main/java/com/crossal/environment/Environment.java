package com.crossal.environment;

import com.crossal.task.Task;

import javax.persistence.*;

@Entity
public class Environment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String path;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    public Environment() {}

    public Environment(EnvironmentDTO environmentDTO) {
        this.id = environmentDTO.getId();
        this.path = environmentDTO.getPath();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
