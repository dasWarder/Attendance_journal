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
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq",
                                            initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
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
