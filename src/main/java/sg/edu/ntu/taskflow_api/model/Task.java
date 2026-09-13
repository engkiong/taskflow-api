package sg.edu.ntu.taskflow_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    private Long id;
    private String title;
    private String description;
    private boolean completed;
    private Priority priority;
    private LocalDate dueDate;

    public enum Priority {
        LOW, MEDIUM, HIGH
    }
}