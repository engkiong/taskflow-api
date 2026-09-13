package sg.edu.ntu.taskflow_api.service;

import sg.edu.ntu.taskflow_api.exception.TaskNotFoundException;
import sg.edu.ntu.taskflow_api.model.Task;
import sg.edu.ntu.taskflow_api.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    public Task findTaskById(Long id) {
        Task task = taskRepository.findById(id);
        if (task == null) {
            throw new TaskNotFoundException(id);
        }
        return task;
    }

    public Task createTask(Task task) {
        task.setId(null);
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task task) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        task.setId(id);
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        taskRepository.deleteById(id);
    }

    public Task markTaskAsComplete(Long id) {
        Task task = findTaskById(id);
        task.setCompleted(true);
        return taskRepository.save(task);
    }
}