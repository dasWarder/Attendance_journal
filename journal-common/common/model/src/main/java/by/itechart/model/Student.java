package by.itechart.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "student")
public class Student {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq",
                                            initialValue = 1, allocationSize = 1)
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
}
