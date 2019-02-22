package com.crossal.environment;

public class EnvironmentDTO {

    private Integer id;
    private String path;

    public EnvironmentDTO() {}

    public EnvironmentDTO(Environment environment) {
        this.id = environment.getId();
        this.path = environment.getPath();
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
}
