package task.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "groups")
public class Group {
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "groups_id_gen")
//    @SequenceGenerator(name = "groups_id_gen", sequenceName = "groups_id_seq")
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    @JoinColumn(name = "department_id")
    @ManyToOne
    private Department department;

    @NotNull
    private Integer enteringYear;
}
