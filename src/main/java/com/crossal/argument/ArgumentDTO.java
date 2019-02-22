package com.crossal.argument;

public class ArgumentDTO {

    private Integer id;
    private Integer sort;
    private String arg;

    public ArgumentDTO() {}

    public ArgumentDTO(Argument argument) {
        this.id = argument.getId();
        this.sort = argument.getSort();
        this.arg = argument.getArg();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getArg() {
        return arg;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }
}
