package com.crossal.task;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class TaskController {

    private static final Logger logger = Logger.getLogger(TaskController.class);

    private TaskService taskService;

    @Autowired
    public void setProductService(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * List all products.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public String list(Model model) {
        logger.debug("/tasks GET");

        List<TaskDTO> tasks = StreamSupport.stream(taskService.listAllTasks().spliterator(), false)
                .map(TaskDTO::new)
                .collect(Collectors.toList());

        model.addAttribute("tasks", tasks);

        logger.debug("Returning tasks");
        return "tasks";
    }

    /**
     * New task.
     *
     * @param model
     * @return
     */
    @RequestMapping("tasks/new")
    public String newTask(Model model) {
        logger.debug("/tasks/new");

        model.addAttribute("task", new TaskDTO());
        return "taskform";
    }

    /**
     * Save task to database.
     *
     * @param taskDTO
     * @return
     */
    @RequestMapping(value = "tasks", method = RequestMethod.POST)
    public String saveTask(TaskDTO taskDTO) {
        logger.debug("tasks POST");

        Task task = taskService.saveTask(new Task(taskDTO));
        return "redirect:/tasks/" + task.getId();
    }

    /**
     * View a specific task by its id.
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("tasks/{id}")
    public String showTask(@PathVariable Integer id, Model model) {
        model.addAttribute("task", new TaskDTO(taskService.getTaskById(id)));
        return "taskshow";
    }
}
