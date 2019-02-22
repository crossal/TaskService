package com.crossal.argument;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Argument {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer sort;
    private String arg;

    public Argument() {}

    public Argument(ArgumentDTO argumentDTO) {
        this.id = argumentDTO.getId();
        this.sort = argumentDTO.getSort();
        this.arg = argumentDTO.getArg();
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
