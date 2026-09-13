package sg.edu.ntu.taskflow_api.controller;

import sg.edu.ntu.taskflow_api.model.Task;
import sg.edu.ntu.taskflow_api.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sg.edu.ntu.taskflow_api.service.TaskSummaryService;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskSummaryService taskSummaryService;

    public TaskController(TaskService taskService, TaskSummaryService taskSummaryService) {
        this.taskService = taskService;
        this.taskSummaryService = taskSummaryService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.findAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.findTaskById(id);
        return ResponseEntity.ok(task);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task created = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        Task updated = taskService.updateTask(id, task);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<Task> markTaskAsComplete(@PathVariable Long id) {
        Task completed = taskService.markTaskAsComplete(id);
        return ResponseEntity.ok(completed);
    }

    @GetMapping("/summary")
    public ResponseEntity<String> getTaskSummary() {
        String summary = taskSummaryService.generateSummary();
        return ResponseEntity.ok(summary);
    }
}

