package by.itechart.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@Table(name = "student")
public class Student {

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq",
                                            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "class_id")
    private SchoolClass schoolClass;

    @ManyToMany
    @JoinTable(
            name = "student_absence",
            joinColumns = { @JoinColumn(name = "student_id") },
            inverseJoinColumns = { @JoinColumn(name = "absence_id") }
    )
    private List<Absence> absenceDates;

    @OneToOne(mappedBy = "student",
              cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private StudentDetails details;

    public Student(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Student(String name, String surname, SchoolClass schoolClass) {
        this.name = name;
        this.surname = surname;
        this.schoolClass = schoolClass;
    }

    public Student(Long id, String name, String surname, SchoolClass schoolClass) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.schoolClass = schoolClass;
    }

    public Student(Long id, String name, String surname, SchoolClass schoolClass, List<Absence> absenceDates) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.schoolClass = schoolClass;
        this.absenceDates = absenceDates;
    }

    public Student(String name, String surname, SchoolClass schoolClass, List<Absence> absenceDates) {
        this.name = name;
        this.surname = surname;
        this.schoolClass = schoolClass;
        this.absenceDates = absenceDates;
    }

    public Student(Long id, String name, String surname, SchoolClass schoolClass,
                   List<Absence> absenceDates, StudentDetails details) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.schoolClass = schoolClass;
        this.absenceDates = absenceDates;
        this.details = details;
    }
}
