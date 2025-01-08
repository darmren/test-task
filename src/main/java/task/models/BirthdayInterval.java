package task.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BirthdayInterval {
    private String from;
    private String until;
}
