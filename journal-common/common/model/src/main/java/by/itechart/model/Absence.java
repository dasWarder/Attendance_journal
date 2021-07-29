package by.itechart.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;


@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "absence")
public class Absence {

    @Id
    @Column
    @SequenceGenerator(name = "absence_seq", sequenceName = "absence_seq",
                       initialValue = 10000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "absence_seq")
    private Long id;

    @Column(name = "absence_date")
    private LocalDate absenceDate;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "absenceDates", cascade = CascadeType.ALL)
    private Set<Student> students;

    public Absence(LocalDate absenceDate) {
        this.absenceDate = absenceDate;
    }
}
