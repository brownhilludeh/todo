package com.brownportal.todo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import com.brownportal.todo.model.Task;
import com.brownportal.todo.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping
    public String getTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        model.addAttribute("appName", appName);
        return "task/index";
    }

    @PostMapping
    public String createTask(@RequestParam String title){
        taskService.createTask(title);
        return "redirect:/";
    }

    @PostMapping("/{id}/update")
    public String updateTask(@PathVariable Long id, @RequestParam String title) {
        taskService.updateTask(id, title);
        return "redirect:/";
    }

    @PostMapping("/{id}/toggle")
    public String toggleTask(@PathVariable Long id) {
        taskService.toggleTask(id);
        return "redirect:/";
    }

    @PostMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/";
    }
}
