package by.itechart.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "school_class")
public class SchoolClass {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq",
            initialValue = 1, allocationSize = 1)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "schoolClass")
    private Set<Student> students;

    public SchoolClass(String name) {
        this.name = name;
    }

    public SchoolClass(String name, Set<Student> students) {
        this.name = name;
        this.students = students;
    }
}
