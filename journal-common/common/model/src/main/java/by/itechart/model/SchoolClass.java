package by.itechart.model;

import by.itechart.model.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
@Getter
@Setter
@Builder
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

    @OneToMany(mappedBy = "schoolClass", fetch = FetchType.LAZY,
                                         cascade = CascadeType.REMOVE)
    private Set<Student> students;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public SchoolClass(String name) {
        this.name = name;
    }

    public SchoolClass(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public SchoolClass(String name, Set<Student> students) {
        this.name = name;
        this.students = students;
    }

    public SchoolClass(Long id, String name, Set<Student> students) {
        this.id = id;
        this.name = name;
        this.students = students;
    }

    public SchoolClass(Long id, String name, Set<Student> students, User user) {
        this.id = id;
        this.name = name;
        this.students = students;
        this.user = user;
    }
}
