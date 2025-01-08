package task.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "departments")
public class Department {
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "departments_id_gen")
//    @SequenceGenerator(name = "departments_id_gen", sequenceName = "departments_id_seq")
    private Integer id;

    @NotNull
    private String name;
}
