package sg.edu.ntu.taskflow_api.repository;

import sg.edu.ntu.taskflow_api.model.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TaskRepository {

    private final Map<Long, Task> tasks = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(0);

    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    public Task findById(Long id) {
        return tasks.get(id);
    }

    public Task save(Task task) {
        if (task.getId() == null) {
            task.setId(idCounter.incrementAndGet());
        }
        tasks.put(task.getId(), task);
        return task;
    }

    public void deleteById(Long id) {
        tasks.remove(id);
    }

    public boolean existsById(Long id) {
        return tasks.containsKey(id);
    }
}