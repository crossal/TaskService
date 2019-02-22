package com.crossal.task;

import org.springframework.data.repository.CrudRepository;

public interface TaskRepository  extends CrudRepository<Task, Integer> {

}
