package sg.edu.ntu.taskflow_api.service;

import sg.edu.ntu.taskflow_api.model.Task;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskSummaryService {

    private final ChatClient chatClient;
    private final TaskService taskService;

    public TaskSummaryService(ChatClient.Builder chatClientBuilder, TaskService taskService) {
        this.chatClient = chatClientBuilder.build();
        this.taskService = taskService;
    }

    public String generateSummary() {
        List<Task> tasks = taskService.findAllTasks();

        if (tasks.isEmpty()) {
            return "There are no tasks yet.";
        }

        String taskListText = tasks.stream()
                .map(task -> "- " + task.getTitle()
                        + " (priority: " + task.getPriority()
                        + ", completed: " + task.isCompleted() + ")")
                .collect(Collectors.joining("\n"));

        String prompt = "Here is a list of tasks:\n" + taskListText
                + "\n\nWrite a short, plain-English summary of what is pending and what is done.";

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}