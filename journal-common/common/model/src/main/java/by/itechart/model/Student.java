package by.itechart.model;


import lombok.*;

import javax.persistence.*;


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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private SchoolClass schoolClass;

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
}
